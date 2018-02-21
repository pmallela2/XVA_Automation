package test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import util.ExcelUtil;
import util.ExcelUtilXSSF;


public class ExecuteTests {

	public static String targetServerURL = null;
	
	public enum XVASelection {
		XVAFeature, Description, Execute
	}

	public static void main(String[] args) throws Exception {
		
		if(args != null)
		{
			//targetServerURL=args[0];
			targetServerURL="Url";
			System.out.println(targetServerURL);
		}
		
		
		Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
		
		//String packageName = ExecuteTests.class.getClass().getPackage().getName();
		
		ExcelUtil executeTestDataExcel = new ExcelUtilXSSF(
				System.getProperty("user.dir")
						+ "/TestData/RunSelective.xlsx");
		
		List<Row> filterDataCollection=executeTestDataExcel.getRowsCollection("RunSelective");
		
		XmlSuite suite = new XmlSuite();
        suite.setName("XVASuite");
        
        XmlTest test = new XmlTest(suite);
        test.setName("XVASanityTests");
        List<XmlClass> classes = new ArrayList<XmlClass>();
       
        
		for(Row row:filterDataCollection){
			if(row.getCell(XVASelection.Execute.ordinal()).toString().equalsIgnoreCase("Y")){
				 classes.add(new XmlClass("test" + "." +row.getCell(XVASelection.XVAFeature.ordinal()).toString()));
			}
		}
		
        test.setXmlClasses(classes) ;
        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(suite);
		TestNG testng = new TestNG();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM_dd_EEE_hh_mm");
		System.setProperty("results.folder.name", dateFormat.format(new Date()));
		String baseResultsFolder = System.getProperty("results.folder.name");
		String TestngFilePath = System.getProperty("user.dir") + "\\ExecutionResults\\" + baseResultsFolder
						+ "\\test-output\\";

		testng.setOutputDirectory(TestngFilePath);
        testng.setXmlSuites(suites);
        ITestNGListener listener = new util.ExtentReporterNG();
		testng.addListener(listener);
		testng.run();	
		
		/*String path = FileSystemView.getFileSystemView().getHomeDirectory()
				.getPath();
		path = path.replace("Desktop", "Downloads");
		String executionResultsPath=System.getProperty("strDownload_Path");
		File destinationDir = new File(executionResultsPath);
		File file = new File(path);
		File[] files = file.listFiles();
		if (files == null || files.length == 0) 
		{
		}
		else 
		{
			FileUtils.copyDirectory(file, destinationDir);
		}
		try {
			FileUtils.cleanDirectory(new File(path));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
	}		
}
