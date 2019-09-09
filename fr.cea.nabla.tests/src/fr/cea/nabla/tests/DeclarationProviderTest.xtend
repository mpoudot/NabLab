package fr.cea.nabla.tests

import com.google.inject.Inject
import fr.cea.nabla.DeclarationProvider
import fr.cea.nabla.nabla.Function
import fr.cea.nabla.nabla.FunctionCall
import fr.cea.nabla.nabla.NablaModule
import fr.cea.nabla.nabla.PrimitiveType
import fr.cea.nabla.nabla.Reduction
import fr.cea.nabla.nabla.ReductionCall
import fr.cea.nabla.typing.UndefinedType
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(NablaInjectorProvider))
class DeclarationProviderTest 
{
	@Inject extension ParseHelper<NablaModule>
	@Inject extension DeclarationProvider
	@Inject extension ValidationTestHelper
	
	@Test
	def void testFunctions() 
	{
		val model =
		'''
		module Test;

		items { cell }

		connectivities {
			cells: → {cell};
		}

		functions
		{
			f:	→ ℕ, ℕ → ℕ, ℝ → ℝ, ℝ[2] → ℝ[2];
			g:	a | ℝ[a] → ℝ[a], a, b | ℝ[a, b] → ℝ[a*b], a, b | ℝ[a] × ℝ[b] → ℝ[a+b];
		}
		'''
		+ TestUtils::mandatoryOptions +
		'''
			
		ℝ a{cells};
		ℝ x{cells};
		ℝ[2] x2{cells};

		// --- TEST DE F ---
		J0: { ℕ x = f(); }
		J1: { ℕ x = f(2); }
		J2: { ℝ x = f(3.0); }
		J3: {
				ℝ[2] a = [1.1, 2.2];
				ℝ[2] x = f(a);
		}
		J4: { ℝ x = f(3.0, true); }

		// --- TEST DE G ---
		J5: {
				ℝ[2] a = [1.1, 2.2];
				ℝ[2] x = g(a);
		}
		J6: {
				ℝ[2, 3] a = [[1.1, 2.2, 3.3], [4.4, 5.5, 6.6]];
				ℝ[6] x = g(a);
		}
		J7: {
				ℝ[2] a = [1.1, 2.2];
				ℝ[3] b = [3.3, 4.4, 5.5];
				ℝ[5] x = g(a, b);
		}
		J8: { a = g(x); }
		J9: { a = g(x, x); }
		J10: { a = g(x2); }
		'''

		println(model)
		val module = model.parse
		Assert.assertNotNull(module)
		module.assertNoErrors
		
		val f = module.functions.filter(Function).get(0)
		val cells = module.connectivities.get(0)

		val j0Fdecl = getFunctionDeclarationOfJob(module, 0)
		Assert.assertEquals(f.argGroups.get(0), j0Fdecl.model)
		val j1Fdecl = getFunctionDeclarationOfJob(module, 1)
		Assert.assertEquals(f.argGroups.get(1), j1Fdecl.model)
		val j2Fdecl = getFunctionDeclarationOfJob(module, 2)
		Assert.assertEquals(f.argGroups.get(2), j2Fdecl.model)
		val j3Fdecl = getFunctionDeclarationOfJob(module, 3)
		Assert.assertEquals(f.argGroups.get(3), j3Fdecl.model)
		val j4Fdecl = getFunctionDeclarationOfJob(module, 4)
		Assert.assertNull(j4Fdecl)
		
		val g = module.functions.filter(Function).get(1)		
		val j5Gdecl = getFunctionDeclarationOfJob(module, 5)
		Assert.assertEquals(g.argGroups.get(0), j5Gdecl.model)
		TestUtils.assertEquals(PrimitiveType::REAL, #[2], #[], j5Gdecl.returnType)
		val j6Gdecl = getFunctionDeclarationOfJob(module, 6)
		Assert.assertEquals(g.argGroups.get(1), j6Gdecl.model)
		TestUtils.assertEquals(PrimitiveType::REAL, #[6], #[], j6Gdecl.returnType)
		val j7Gdecl = getFunctionDeclarationOfJob(module, 7)
		Assert.assertEquals(g.argGroups.get(2), j7Gdecl.model)
		TestUtils.assertEquals(PrimitiveType::REAL, #[5], #[], j7Gdecl.returnType)
		val j8Gdecl = getFunctionDeclarationOfJob(module, 8)
		Assert.assertEquals(g.argGroups.get(0), j8Gdecl.model)
		TestUtils.assertEquals(PrimitiveType::REAL, #[], #[cells], j8Gdecl.returnType)
		val j9Gdecl = getFunctionDeclarationOfJob(module, 9)
		Assert.assertEquals(g.argGroups.get(2), j9Gdecl.model)
		Assert.assertTrue(j9Gdecl.returnType instanceof UndefinedType)
		val j10Gdecl = getFunctionDeclarationOfJob(module, 10)
		Assert.assertEquals(g.argGroups.get(1), j10Gdecl.model)
		Assert.assertTrue(j10Gdecl.returnType instanceof UndefinedType)
	}
	
	@Test
	def void testReductions() 
	{
		val model =
		'''
		module Test;

		items { cell }

		connectivities { cells: → {cell}; }
			
		functions
		{
			f: (0.0, ℝ) → ℝ, x | (0.0, ℝ[x]) → ℝ[x];
		}
		'''
		+ TestUtils::mandatoryOptions +
		'''
		ℝ u{cells};
		ℝ[2] u2{cells};
		ℕ bidon{cells};

		// --- TEST DE F ---
		J0: { ℝ x = f{j ∈ cells()}(u{j}); }
		J1: { ℝ[2] x = f{j ∈ cells()}(u2{j}); }
		J2: { ℝ x = f{j ∈ cells()}(bidon{j}); }
		'''
		
		val module = model.parse
		Assert.assertNotNull(module)
		module.assertNoErrors
		
		val f = module.functions.filter(Reduction).get(0)
		val j0Fdecl = getReductionDeclarationOfJob(module, 0)
		Assert.assertEquals(f.argGroups.get(0), j0Fdecl.model)
		val j1Fdecl = getReductionDeclarationOfJob(module, 1)
		Assert.assertEquals(f.argGroups.get(1), j1Fdecl.model)
		TestUtils.assertEquals(PrimitiveType::REAL, #[2], #[], j1Fdecl.returnType)
		val j2Fdecl = getReductionDeclarationOfJob(module, 2)
		Assert.assertNull(j2Fdecl)
	}
	
	private def getFunctionDeclarationOfJob(NablaModule m, int jobIndex)
	{
		val fcall = m.jobs.get(jobIndex).eAllContents.filter(FunctionCall).head
		fcall.declaration
	}

	private def getReductionDeclarationOfJob(NablaModule m, int jobIndex)
	{
		val fcall = m.jobs.get(jobIndex).eAllContents.filter(ReductionCall).head
		fcall.declaration
	}
}