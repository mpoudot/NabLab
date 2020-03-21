package fr.cea.nabla.ir.generator

import fr.cea.nabla.ir.ir.Interval
import fr.cea.nabla.ir.ir.Iterator

import static extension fr.cea.nabla.ir.generator.SizeTypeContentProvider.*
import static extension fr.cea.nabla.ir.generator.Utils.*

class IterationBlockExtensions 
{
	static def dispatch getIndexName(Iterator it)
	{
		index.name
	}

	static def dispatch getIndexName(Interval it)
	{
		index.name
	}

	static def dispatch getNbElems(Iterator it)
	{
		if (container.connectivity.indexEqualId)
			container.connectivity.nbElems
		else
			'nbElems' + indexName.toFirstUpper
	}

	static def dispatch getNbElems(Interval it)
	{
		nbElems.content
	}
}