/*******************************************************************************
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
package fr.cea.nabla.workflow

import fr.cea.nabla.ir.generator.cpp.Ir2Cpp
import fr.cea.nabla.ir.generator.cpp.KokkosBackend
import fr.cea.nabla.ir.generator.cpp.KokkosTeamThreadBackend
import fr.cea.nabla.ir.generator.cpp.SequentialBackend
import fr.cea.nabla.ir.generator.cpp.StlThreadBackend
import fr.cea.nabla.ir.generator.java.Ir2Java
import fr.cea.nabla.nablagen.Cpp
import fr.cea.nabla.nablagen.Java
import fr.cea.nabla.nablagen.Target
import java.io.File

class TargetCodeGeneratorProvider
{
	static def get(Target it, String baseDir)
	{
		switch it
		{
			Java: new Ir2Java
			Cpp: new Ir2Cpp(new File(baseDir + outputDir), backend)
			default : throw new RuntimeException("Unsupported language " + class.name)
		}
	}

	private static def getBackend(Cpp it)
	{
		switch programmingModel
		{
			case SEQUENTIAL: new SequentialBackend(maxIterationVar.name , stopTimeVar.name, compiler.literal, compilerPath)
			case STL_THREAD: new StlThreadBackend(maxIterationVar.name , stopTimeVar.name, compiler.literal, compilerPath)
			case OPEN_MP: throw new RuntimeException('Not yet implemented')
			case KOKKOS: new KokkosBackend(maxIterationVar.name , stopTimeVar.name, compiler.literal, compilerPath, kokkosPath)
			case KOKKOS_TEAM_THREAD: new KokkosTeamThreadBackend(maxIterationVar.name , stopTimeVar.name, compiler.literal, compilerPath, kokkosPath)
		}
	}
}