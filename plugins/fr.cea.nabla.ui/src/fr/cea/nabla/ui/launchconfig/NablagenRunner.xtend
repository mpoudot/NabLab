/*******************************************************************************
 * Copyright (c) 2020 CEA
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
package fr.cea.nabla.ui.launchconfig

import com.google.inject.Inject
import com.google.inject.Provider
import com.google.inject.Singleton
import fr.cea.nabla.generator.NablagenInterpreter
import fr.cea.nabla.nablagen.NablagenConfig
import fr.cea.nabla.nablagen.NablagenModule
import fr.cea.nabla.ui.NabLabConsoleFactory
import org.eclipse.core.resources.IResource
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.ui.console.ConsolePlugin
import org.eclipse.ui.console.MessageConsole

@Singleton
class NablagenRunner
{
	@Inject Provider<ResourceSet> resourceSetProvider
	@Inject Provider<NablagenInterpreter> interpreterProvider
	@Inject NabLabConsoleFactory consoleFactory

	def launch(NablagenConfig config, String baseDir)
	{
		val interpreter = interpreterProvider.get
		consoleFactory.openConsole
		val console = ConsolePlugin.^default.consoleManager.consoles.filter(MessageConsole).findFirst[x | x.name == NabLabConsoleFactory.ConsoleName]
		if (console !== null)
		{
			console.activate
			val stream = console.newMessageStream
			interpreter.traceListeners += [String msg | stream.print(msg)]
		}
		val irModule = interpreter.buildIrModule(config, baseDir)
		interpreter.generateCode(irModule, config.targets, config.simulation.iterationMax.name, config.simulation.timeMax.name, baseDir)
	}

	package def launch(IResource eclipseResource)
	{
		val plaftormUri = URI::createPlatformResourceURI(eclipseResource.project.name + '/' + eclipseResource.projectRelativePath, true)
		val resourceSet = resourceSetProvider.get
		val uriMap = resourceSet.URIConverter.URIMap
		uriMap.put(URI::createURI('platform:/resource/fr.cea.nabla/'), URI::createURI('platform:/plugin/fr.cea.nabla/'))
		val emfResource = resourceSet.createResource(plaftormUri)
		EcoreUtil::resolveAll(resourceSet)
		emfResource.load(null)
		for (module : emfResource.contents.filter(NablagenModule))
			if (module.config !== null) 
				launch(module.config, eclipseResource.project.location.toString)

		eclipseResource.project.refreshLocal(IResource::DEPTH_INFINITE, null)
	}

//	/** Refresh du répertoire s'il est contenu dans la resource (évite le F5) */
//	private static def refreshResourceDir(IProject p, String fileAbsolutePath)
//	{
//		p.refreshLocal(IResource::DEPTH_INFINITE, null)
//		val uri = java.net.URI::create(fileAbsolutePath)
//		val files = p.workspace.root.findFilesForLocationURI(uri)
//		if (files !== null && files.size == 1) files.head.parent.refreshLocal(IResource::DEPTH_INFINITE, null)
//	}

}