#
#!/bin/sh
#

### install leveldb
echo ------------------ Start Install LEVELDB ----------------
cd $HOME
mkdir leveldb; cd leveldb
wget http://github.com/google/leveldb/archive/1.22.tar.gz -O leveldb.tar.gz; tar -zxf leveldb.tar.gz; rm leveldb.tar.gz
mkdir install; mkdir build; cd build
cmake ../leveldb-1.22 -DCMAKE_BUILD_TYPE=Release -DCMAKE_INSTALL_PREFIX=$HOME/leveldb/install
make; make install
echo ------------------ Install LEVELDB OK ------------------ 

### install hwloc
echo ------------------ Start Install HWLOC ------------------
sudo apt-get update; sudo apt-get install -y libhwloc-dev --fix-missing
echo ------------------ Install HWLOC OK ------------------

### install kokkos
echo ------------------ Start Install KOKKOS ------------------
cd $HOME
mkdir kokkos; cd kokkos
wget http://github.com/kokkos/kokkos/archive/refs/tags/3.0.00.tar.gz -O kokkos.tar.gz; tar -zxf kokkos.tar.gz; rm kokkos.tar.gz
mkdir install; mkdir build; cd build
cmake ../kokkos-3.0.00 -DCMAKE_CXX_COMPILER=g++ -DKokkos_CXX_STANDARD=17 -DCMAKE_INSTALL_PREFIX=$HOME/kokkos/install -DKokkos_ENABLE_OPENMP=On -DKokkos_ENABLE_HWLOC=On
make; make install
cd $HOME/kokkos
wget http://github.com/kokkos/kokkos-kernels/archive/refs/tags/3.0.00.tar.gz -O kernels.tar.gz; tar -zxf kernels.tar.gz; rm kernels.tar.gz
rm -rf build; mkdir build; cd build
cmake ../kokkos-kernels-3.0.00 -DCMAKE_CXX_COMPILER=g++ -DCMAKE_INSTALL_PREFIX=$HOME/kokkos/install -DKokkos_ROOT=$HOME/kokkos/install -DKokkos_DIR=$HOME/kokkos/install 
make; make install
echo ------------------ Install KOKKOS OK ------------------ 
