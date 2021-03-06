/*******************************************************************************
 * Copyright (c) 2021 CEA
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
package fr.cea.nabla.ir.interpreter

import fr.cea.nabla.ir.ir.ExtensionProvider
import java.util.HashMap

/**
 * Native library (.so) can only be loaded once.
 * Consequently, JNI classes, containing the static loadLibrary instruction,
 * must not be load more than once to prevent IllegalAccessException.
 * Libraries are unloaded when associated classLoader is deleted (need 2 calls to System.gc)
 */
class ExtensionProviderCache
{
	val classByProviders = new HashMap<ExtensionProvider, ExtensionProviderHelper>

	new(Iterable<ExtensionProvider> providers, String wsPath)
	{
		for (p : providers)
		{
			try
			{
				val c = switch p
				{
					case p.extensionName == "Math": new MathExtensionProviderHelper
					case p.linearAlgebra: new LinearAlgebraExtensionProviderHelper(p, wsPath)
					default: new DefaultExtensionProviderHelper(p, wsPath)
				}
				c.initFunctions(p.functions)
				classByProviders.put(p, c)
			}
			catch (ClassNotFoundException e)
			{
				throw new ExtensionProviderNotFound(p, e)
			}
		}
	}

	def ExtensionProviderHelper get(ExtensionProvider p)
	{
		classByProviders.get(p)
	}
}

