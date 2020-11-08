/*******************************************************************************
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
package fr.cea.nabla.ir

import fr.cea.nabla.ir.ir.Affectation
import fr.cea.nabla.ir.ir.ArgOrVarRef
import fr.cea.nabla.ir.ir.ExecuteTimeLoopJob
import fr.cea.nabla.ir.ir.IrPackage
import fr.cea.nabla.ir.ir.Job
import fr.cea.nabla.ir.ir.TimeLoopCopyJob
import fr.cea.nabla.ir.ir.Variable
import java.util.HashSet

import static extension fr.cea.nabla.ir.ArgOrVarExtensions.*

abstract class VarDependencies
{
	abstract def Iterable<Variable> getExecuteTimeLoopJobOutVars(ExecuteTimeLoopJob it)
	abstract def Iterable<Variable> getExecuteTimeLoopJobInVars(ExecuteTimeLoopJob it)

	def Iterable<Variable> getOutVars(Job it)
	{
		switch it
		{
			ExecuteTimeLoopJob: executeTimeLoopJobOutVars
			TimeLoopCopyJob: copies.map[destination]
			default: eAllContents.filter(Affectation).map[left.target].filter(Variable).filter[global].toSet
		}
	}

	/** For ExecuteTimeLoopJob, copies are ignored to avoid cycles */
	def Iterable<Variable> getInVars(Job it)
	{
		switch it
		{
			ExecuteTimeLoopJob: executeTimeLoopJobInVars
			TimeLoopCopyJob: copies.map[source]
			default:
			{
				val allVars = eAllContents.filter(ArgOrVarRef).filter[x|x.eContainingFeature != IrPackage::eINSTANCE.affectation_Left].map[target]
				allVars.filter(Variable).filter[global].toSet
			}
		}
	}
}

class DefaultVarDependencies extends VarDependencies
{
	override Iterable<Variable> getExecuteTimeLoopJobOutVars(ExecuteTimeLoopJob it)
	{
		val outVars = new HashSet<Variable>
		innerJobs.forEach[x | outVars += x.outVars]
		return outVars
	}

	override Iterable<Variable> getExecuteTimeLoopJobInVars(ExecuteTimeLoopJob it)
	{
		val inVars = new HashSet<Variable>
		innerJobs.forEach[x | inVars += x.inVars]
		return inVars
	}
}

class JobDispatchVarDependencies extends VarDependencies
{
	override Iterable<Variable> getExecuteTimeLoopJobOutVars(ExecuteTimeLoopJob it)
	{
		copies.map[source]
	}

	override Iterable<Variable> getExecuteTimeLoopJobInVars(ExecuteTimeLoopJob it)
	{
		copies.map[destination]
	}
}