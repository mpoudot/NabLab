package fr.cea.nabla.generator.ir

import com.google.inject.Inject
import fr.cea.nabla.ir.ir.BaseType
import fr.cea.nabla.ir.ir.ConnectivityType
import fr.cea.nabla.ir.ir.IrFactory
import fr.cea.nabla.ir.ir.IrType
import fr.cea.nabla.typing.NSTArray1D
import fr.cea.nabla.typing.NSTArray2D
import fr.cea.nabla.typing.NSTDimension
import fr.cea.nabla.typing.NSTScalar
import fr.cea.nabla.typing.NablaConnectivityType
import fr.cea.nabla.typing.NablaSimpleType
import fr.cea.nabla.typing.NablaType

class NablaType2IrType 
{
	@Inject extension Nabla2IrUtils
	@Inject extension IrConnectivityFactory
	@Inject extension IrDimensionFactory

	def IrType toIrType(NablaType t)
	{
		switch t
		{
			case null: null // Undefined type
			NablaSimpleType: t.toIrBaseType
			NablaConnectivityType: t.toIrConnectivityType
		}
	}

	def dispatch BaseType toIrBaseType(NSTScalar t)
	{
		IrFactory.eINSTANCE.createBaseType =>
		[
			primitive = t.primitive.toIrPrimitiveType
		]
	}

	def dispatch BaseType toIrBaseType(NSTArray1D t)
	{
		IrFactory.eINSTANCE.createBaseType =>
		[
			primitive = t.primitive.toIrPrimitiveType
			sizes += t.size.toIrDimension
		]
	}

	def dispatch BaseType toIrBaseType(NSTArray2D t)
	{
		IrFactory.eINSTANCE.createBaseType =>
		[
			primitive = t.primitive.toIrPrimitiveType
			sizes += t.nbRows.toIrDimension
			sizes += t.nbCols.toIrDimension
		]
	}

	private def ConnectivityType toIrConnectivityType(NablaConnectivityType t)
	{
		IrFactory.eINSTANCE.createConnectivityType =>
		[
			base = t.simple.toIrBaseType
			t.supports.forEach[x | connectivities += x.toIrConnectivity]
		]
	}

	private def toIrDimension(NSTDimension d)
	{
		if (d.isInt)
			IrFactory::eINSTANCE.createDimensionInt => [ value = d.intValue ]
		else
			d.dimensionValue.toIrDimension
	}
}