/*******************************************************************************
 * Copyright (c) 2021 CEA
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors: see AUTHORS file
 *******************************************************************************/
package fr.cea.nabla.generator.ir

import com.google.inject.Inject
import com.google.inject.Singleton
import fr.cea.nabla.ir.ir.IrFactory
import fr.cea.nabla.nabla.BaseType
import fr.cea.nabla.nabla.Connectivity
import fr.cea.nabla.nabla.ItemType
import fr.cea.nabla.nabla.NablaExtension
import fr.cea.nabla.nabla.PrimitiveType

@Singleton
class IrBasicFactory
{
	@Inject extension IrAnnotationHelper
	@Inject extension IrExpressionFactory

	// No create method to ensure a new instance every time (for n+1 time variables)
	def fr.cea.nabla.ir.ir.BaseType toIrBaseType(BaseType t)
	{
		IrFactory::eINSTANCE.createBaseType =>
		[
			primitive = t.primitive.toIrPrimitiveType
			t.sizes.forEach[x | sizes += x.toIrExpression]
		]
	}

	def toIrPrimitiveType(PrimitiveType t)
	{
		val type = fr.cea.nabla.ir.ir.PrimitiveType::get(t.value + 1) // First literal is VOID in the IR model
		if (type === null) throw new RuntimeException('Conversion Nabla --> IR impossible : type inconnu ' + t.literal)
		return type
	}

	def toIrConnectivity(Connectivity c)
	{
		c.name.toIrConnectivity =>
		[
			if (annotations.empty) annotations += c.toIrAnnotation
			returnType = c.returnType.toIrItemType
			if (inTypes.empty) inTypes += c.inTypes.map[toIrItemType]
			multiple = c.multiple
		]
	}

	def toIrItemType(ItemType i)
	{
		i.name.toIrItemType => [annotations += i.toIrAnnotation]
	}

	def create IrFactory::eINSTANCE.createExtensionProvider toIrExtensionProvider(NablaExtension ext)
	{
		extensionName = ext.name
	}

	/** 
	 * Return an ItemType instance.
	 * PK is name (instead of nabla.ItemType) to avoid 
	 * multiple IR instances in case of multiple NablaModule.
	 */
	private def create IrFactory::eINSTANCE.createItemType toIrItemType(String itemTypeName)
	{
		name = itemTypeName
	}

	/** 
	 * Return a Connectivity instance.
	 * PK is name (instead of nabla.Connectivity) to avoid 
	 * multiple IR instances in case of multiple NablaModule.
	 */
	private def create IrFactory::eINSTANCE.createConnectivity toIrConnectivity(String connectivityName)
	{
		name = connectivityName
	}
}