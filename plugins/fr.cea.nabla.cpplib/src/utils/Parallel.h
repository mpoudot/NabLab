/*******************************************************************************
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
#ifndef UTILS_PARALLEL_H_
#define UTILS_PARALLEL_H_

#include <functional>
#include <thread>
#include <future>
#include <algorithm>
#include <numeric>

namespace nablalib
{
  
namespace parallel
{
  
// ------------------------------ Would love to be private  ------------------------------ 
namespace internal
{
// Internal call for parallel process, not meant to be use outside the parallel_exec
template <typename F>
void parallel_exec_internal(const int& nb_thread, const int& nb_elmt,
                            const int& begin, const int& end, F lambda) noexcept
{
  const int chunck_size(std::floor(nb_elmt / nb_thread));  // chuncks are simples, we should do better...
  const int len(end - begin);
  // if every chuncks have been computed
  if (len <= chunck_size) {
    for (int i(begin); i < end; ++i)
      lambda(i);
    return;
  }

  // std::cout << "BEG = " << begin << ", END = " << end << std::endl;

  // else spawn a new thread asynchronously
  const int next(begin + chunck_size < end ? begin + chunck_size : end);
  auto future = std::async(std::launch::async, parallel_exec_internal<F>, nb_thread, nb_elmt, next, end, lambda);
  parallel_exec_internal(nb_thread, nb_elmt, begin, next, lambda);
  future.get();
  return;
}

// Internal call for parallel reduce, not meant to be use outside the parallel_reduce
template <typename T, typename V, typename BinOp>
T parallel_reduce_internal(const int& nb_thread, const int& nb_elmt, const V& var,
                           const int& begin, const int& end, const T init_val, BinOp bin_op) noexcept
{
  const int chunck_size(std::floor(nb_elmt / nb_thread));  // chuncks are simples, we should do better...
  const int len(end - begin);
  // if every chuncks have been computed
  if (len <= chunck_size)
    return std::accumulate(&var[begin], &var[end], init_val, bin_op);

  // std::cout << "BEG = " << begin << ", END = " << end << std::endl;

  // else spawn a new thread asynchronously
  const int next(begin + chunck_size < end ? begin + chunck_size : end);
  auto future = std::async(std::launch::async, parallel_reduce_internal<T, V, BinOp>,
                           nb_thread, nb_elmt, var, next, end, init_val, bin_op);
  auto result = parallel_reduce_internal(nb_thread, nb_elmt, var, begin, next, init_val, bin_op);
  return bin_op(result, future.get());
}
}  // ------------------------------ end of namespace internal  ------------------------------ 

// Some kind of bad static openMP parallel for
// Given a range number of elements, the lambda function parameter is called upon each element.
// Data is processed by chuncks and each chunck has is own dedicated thread.
template <typename F>
void parallel_exec(const int nb_elmt, F&& lambda) noexcept
{
  // Getting number of concurrent threads supported.
  int nb_thread(2);  // Hyper thread support by default
  if (std::thread::hardware_concurrency() > 0)
    nb_thread = std::thread::hardware_concurrency();
  else
    std::cerr << "WARNING: can't figure out optimal threads number, using 2 by default." << std::endl;
    
  // Actually calling multithreaded lambda function over nb_elmt
  internal::parallel_exec_internal(nb_thread, nb_elmt, 0, nb_elmt, lambda);
}

// Some kind of bad static openMP parallel reduce
// Given a number of elements, binary operation is called upon each couple of elements of variable V.
// Data is processed by chuncks and each chunck has is own dedicated thread.
template <typename T, typename V, typename BinOp>
T parallel_reduce(const int nb_elmt, const V& var, const T init_val, BinOp&& bin_op) noexcept
{
  // Getting number of concurrent threads supported.
  int nb_thread(2);  // Hyper thread support by default
  if (std::thread::hardware_concurrency() > 0)
    nb_thread = std::thread::hardware_concurrency();
  else
    std::cerr << "WARNING: can't figure out optimal threads number, using 2 by default." << std::endl;
    
  // Actually calling multithreaded lambda function over nb_elmt
  return internal::parallel_reduce_internal(nb_thread, nb_elmt, var, 0, nb_elmt, init_val, bin_op);
}

}  // end of namespace parallel

}  // end of namespace naballib

#endif  // UTILS_PARALLEL_H_