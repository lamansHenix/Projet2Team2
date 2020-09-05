
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.support.SoapUIException;


import org.apache.xmlbeans.XmlException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



	
//	//@Test
//		public void testRunner() throws Exception {
//			SoapUITestCaseRunner runner = new SoapUITestCaseRunner();
//			runner.setProjectFile("src/test/resources/SoapJunit.xml");
//			runner.run();
//		}
//	
//		@Test
//		public void testTestCaseSelecter() throws Exception
//		{
//			WsdlProject project = new WsdlProject("src/test/resources/LogicalDoc-soapui-project.xml");
//			TestSuite TS = project.getTestSuiteByName("Cas Nominal");
//			TestCase TC = TS.getTestCaseByName("Creer des repertoires");
//			TestRunner runner = TC.run(new PropertiesMap(), false);
//			assertEquals(Status.FINISHED, runner.getStatus());
//			
//		}
//	
	
	@RunWith(Parameterized.class)
	public class LogicalDocTest {
		private String testCaseName;
	

		public LogicalDocTest(String testCaseName) {
			this.testCaseName = testCaseName;
		}

		@Parameters(name = "{0}")
		public static Collection<String[]> getTestCases() throws XmlException, IOException, SoapUIException {
			final ArrayList<String[]> testCases = new ArrayList<String[]>();
			WsdlProject project = new WsdlProject("src/test/resources/LogicalDoc-soapui-project.xml");
			TestSuite TS = project.getTestSuiteByName("Cas Nominal 2");
			//List<TestSuite> testSuites = project.getTestSuiteList();
			//for (TestSuite suite : testSuites) {
				//List<TestCase> lTestCases = suite.getTestCaseList();
				//
			List<TestCase> TCListe = TS.getTestCaseList();
			for (TestCase testCase : TCListe) 
				{
				if (!testCase.isDisabled()) 
					{
						testCases.add(new String[] { testCase.getName() });
					}
				}
			//}
			return testCases;
			
		}

		@Test
		public void testSoapUITestCase() throws XmlException, IOException, SoapUIException {
			System.out.println("\n[START] Nom du cas de test SoapUI : " + testCaseName.toUpperCase());
			assertTrue(true);
			assertTrue(runSoapUITestCase(this.testCaseName));
		}

		public static boolean runSoapUITestCase(String testCase) throws XmlException, IOException, SoapUIException {
			TestRunner.Status exitValue = TestRunner.Status.INITIALIZED;
			WsdlProject soapuiProject = new WsdlProject("src/test/resources/LogicalDoc-soapui-project.xml");
			TestSuite TS = soapuiProject.getTestSuiteByName("Cas Nominal 2");
				System.out.println("[SEARCH] recherche du TC ["+testCase+"] dans la suite "+ TS.getName().toUpperCase());
				TestCase soapuiTestCase = TS.getTestCaseByName(testCase);
				if (soapuiTestCase == null)
				{
					System.out.println("[NOT FOUND YET] runner soapUI, le cas de test [" + testCase+ "] n'est pas dans cette suite de test");
				} 
				else 
				{
					System.out.println("[RUN] Running SoapUI test [" + testCase + "]");
					TestCaseRunner runner = soapuiTestCase.run(new PropertiesMap(), false);
					exitValue = runner.getStatus();
					if(exitValue == TestRunner.Status.FINISHED) 
					{
						System.out.println("[END] : Cas de test soapUI terminé ('" + TS.getName().toUpperCase() + "':[" + testCase + "]) : " + exitValue);
//						String testCasePropertyDossier = TS.getPropertyValue("recup_IdDossier").toString(); 
//						String testCasePropertyFichier = TS.getPropertyValue("FichierEnvoi").toString();
//						System.out.println();
//						System.out.println("recup_IdDossier = " + testCasePropertyDossier + " ");
//						System.out.println();
//						System.out.println("FichierEnvoi = " + testCasePropertyFichier + " ");
						return true;
					}
				}

		
				System.out.println("[END] : Cas de test soapUI terminé ('" + TS.getName().toUpperCase() + "':[" + testCase + "]) Status : " + exitValue);
//				String testCasePropertyDossier = TS.getPropertyValue("recup_IdDossier").toString(); 
//				String testCasePropertyFichier = TS.getPropertyValue("FichierEnvoi").toString();
//				System.out.println();
//				System.out.println("recup_IdDossier = " + testCasePropertyDossier + " ");
//				System.out.println();
//				System.out.println("FichierEnvoi = " + testCasePropertyFichier + " ");
				return false;
			}
		
		
		
		}


/*@RunWith(Parameterized.class)
public class LogicalDocTest {
	private String testCaseName;

	public LogicalDocTest(String testCaseName) {
		this.testCaseName = testCaseName;
	}

	@Parameters(name = "{0}")
	public static Collection<String[]> getTestCases() throws XmlException, IOException, SoapUIException {
		final ArrayList<String[]> testCases = new ArrayList<String[]>();
		WsdlProject project = new WsdlProject("src/test/resources/GaelLogicalDoc.xml");
		List<TestSuite> testSuites = project.getTestSuiteList();
		for (TestSuite suite : testSuites) {
			List<TestCase> lTestCases = suite.getTestCaseList();
			for (TestCase testCase : lTestCases) {
				if (!testCase.isDisabled()) {
					testCases.add(new String[] { testCase.getName() });
				}
			}
		}
		return testCases;

	}

	@Test
	public void testSoapUITestCase() throws XmlException, IOException, SoapUIException {
		System.out.println("\n[START] Nom du cas de test SoapUI : " + testCaseName.toUpperCase());
		assertTrue(true);
		assertTrue(runSoapUITestCase(this.testCaseName));
	}

	public static boolean runSoapUITestCase(String testCase) throws XmlException, IOException, SoapUIException {
		TestRunner.Status exitValue = TestRunner.Status.INITIALIZED;
		WsdlProject soapuiProject = new WsdlProject("src/test/resources/GaelLogicalDoc.xml");
		List<TestSuite> testSuites = soapuiProject.getTestSuiteList();
		if (testSuites.size()==0) {
			System.err.println("[ERROR] runner soapUI, Aucune suite de test dans le projet ");
			return false;
		}
		for (TestSuite suite : testSuites) {
			System.out.println("[SEARCH] recherche du TC ["+testCase+"] dans la suite "+ suite.getName().toUpperCase());
			TestCase soapuiTestCase = suite.getTestCaseByName(testCase);	
			if (soapuiTestCase == null) {
				System.out.println("[NOT FOUND YET] runner soapUI, le cas de test [" + testCase+ "] n'est pas dans cette suite de test");
			} else {
				System.out.println("[RUN] Running SoapUI test [" + testCase + "]");
				TestCaseRunner runner = soapuiTestCase.run(new PropertiesMap(), false);
				exitValue = runner.getStatus();
				if(exitValue == TestRunner.Status.FINISHED) {
					System.out.println("[END] : Cas de test soapUI terminé ('" + suite.getName().toUpperCase() + "':[" + testCase + "]) Status : " + exitValue);
					String testCasePropertyDossier = suite.getPropertyValue("recup_IdDossier"); 
					String testCasePropertyFichier = suite.getPropertyValue("FichierEnvoi");
					System.out.println();
					System.out.println("recup_IdDossier = " + testCasePropertyDossier + " ");
					System.out.println();
					System.out.println("FichierEnvoi = " + testCasePropertyFichier + " ");
					return true;
				}
				System.out.println("[END] : Cas de test soapUI terminé ('" + suite.getName().toUpperCase() + "':[" + testCase + "]) Status : " + exitValue);
				String testCasePropertyDossier = suite.getPropertyValue("recup_IdDossier"); 
				String testCasePropertyFichier = suite.getPropertyValue("FichierEnvoi");
				System.out.println();
				System.out.println("recup_IdDossier = " + testCasePropertyDossier + " ");
				System.out.println();
				System.out.println("FichierEnvoi = " + testCasePropertyFichier + " ");
			}

		}
		
			return false;
		}
	}*/
	
	
	
	

