package fr.cea.nabla.tests

import com.google.inject.Inject
import fr.cea.nabla.ir.interpreter.ModuleInterpreter
import fr.cea.nabla.ir.interpreter.NV0Real
import java.util.logging.ConsoleHandler
import java.util.logging.Level
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith

import static fr.cea.nabla.tests.TestUtils.*

@RunWith(XtextRunner)
@InjectWith(NablaInjectorProvider)
class ModuleInterpreterTest 
{
	@Inject CompilationChainHelper compilationHelper

	@Test
	def void testInterpreteModule()
	{
		val model = TestUtils::getTestModule(10, 10)
		+
		'''
		// Simulation options
		const ℝ option_stoptime = 0.2;
		const ℕ option_max_iterations = 20000;

		ℕ iterationN;
		iterate n counter iterationN while (t^{n} < option_stoptime && iterationN < option_max_iterations);

		InitT: t^{n=0} = 0.;
		ComputeTn: t^{n+1} = t^{n} + 0.01;
		'''

		val irModule = compilationHelper.getIrModule(model, TestUtils::testGenModel)
		val handler = new ConsoleHandler
		handler.level = Level::OFF
		val moduleInterpreter = new ModuleInterpreter(irModule, handler)
		val context = moduleInterpreter.interprete
		assertVariableValueInContext(irModule, context, "t_n", new NV0Real(0.2))
	}
}