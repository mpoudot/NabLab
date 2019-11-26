package fr.cea.nabla.tests

import com.google.inject.Inject
import fr.cea.nabla.ir.interpreter.ModuleInterpreter
import fr.cea.nabla.ir.interpreter.NV0Real
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

import static fr.cea.nabla.tests.TestUtils.*

@RunWith(XtextRunner)
@InjectWith(NablaInjectorProvider)
class JobInterpreterTest 
{
	@Inject CompilationChainHelper compilationHelper

	@Test
	def void testInterpreteInSituJob()
	{
		Assert.fail("Not implemented yet")
	}

	@Test
	def void testInterpreteInstructionJob()
	{
		val model = TestUtils::testModule
		+
		'''
		initT : t = 5.;
		'''

		val irModule = compilationHelper.getIrModule(model, TestUtils::testGenModel)
		val moduleInterpreter = new ModuleInterpreter(irModule)
		val context = moduleInterpreter.interprete

		assertVariableValueInContext(irModule, context, "t", new NV0Real(5.0))
	}

	@Test
	def void testInterpreteEndOfTimeLoopJob()
	{
		val model = TestUtils::testModule
		+
		'''
		InitT: t = 0.;
		ComputeTn: t^{n+1} = t + 0.01;
		'''

		val irModule = compilationHelper.getIrModule(model, TestUtils::testGenModel)
		val moduleInterpreter = new ModuleInterpreter(irModule)
		val context = moduleInterpreter.interprete
		assertVariableValueInContext(irModule, context, "t", new NV0Real(0.01))
	}

	@Test
	def void testInterpreteEndOfInitJob()
	{
		val model = TestUtils::testModule
		+
		'''
		InitT: t = 0.;
		'''
		val irModule = compilationHelper.getIrModule(model, TestUtils::testGenModel)
		val moduleInterpreter = new ModuleInterpreter(irModule)
		moduleInterpreter.interprete
		Assert.fail("On ne passe pas dans interpreteEndOfInitJob avec ce test-ci")	}
}