package EustroSoft;

import java.util.*;
import java.io.*;
import java.lang.annotation.*;
import java.nio.*;
import java.nio.file.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

public class ZJsonTest {
	
	@Test
	public void clearTest() throws ArrayIndexOutOfBoundsException {
		
		ArrayIndexOutOfBoundsException ae = null;
		ZJson zjson = new ZJson();
		zjson.addItem("NameItem", 1);
		zjson.clear();

		try {
			assertTrue((zjson.getItemName(0) == null) && (zjson.getItemName(0) == null));
		} catch (ArrayIndexOutOfBoundsException ex) {
			ae = ex;
		}

		assertNotNull(ae);
	}

	@Test
	public void testAddItem() {
		ZJson zjson = new ZJson();
		zjson.addItem("String");

		assertEquals(zjson.getItem(0), "String");
		assertNull(zjson.getItemName(0));
	}

	@Test
	public void writeJSONString() {
		try {
			ZJson zjson = new ZJson();
			zjson.addItem("a", Boolean.TRUE);
			StringWriter sbw = new StringWriter();
			StringBuffer sb = sbw.getBuffer();

			zjson.writeJSONString(sbw);
			ZJson zjson_out = new ZJson();
			zjson_out.parseJSONString(sbw.toString());

			assertEquals(zjson.getItem(0), zjson_out.getItem(0));
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Test
	public void isCharInClass() {
		assertTrue(ZJson.isCharInClass(65, "A") == true);
	}

	@Test
	public void isCharInClassTest() {
		assertFalse(ZJson.isCharInClass(65, "B") == true);
	}

	@Test
	public void isCharSpaceAny() {
		assertTrue(ZJson.isCharSpaceAny('\n'));
		assertTrue(ZJson.isCharSpaceAny('\t'));
		assertTrue(ZJson.isCharSpaceAny('\r'));
		assertTrue(ZJson.isCharSpaceAny(' '));
	}

	@Test
	public void isCharSpaceOnly() {
		assertTrue(ZJson.isCharSpaceOnly('\t'));
	}

	@Test
	public void parseException() {
		IOException ex = ZJson.parseException(1, 'a');
		assertNotNull(ex);
	}
/*
	@Test
	public void parseJSONString() throws IOException {
		zjson.parseJSONString("{ \"name\" : false }");

		assertTrue(zjson.getItemName(0) != "exception");
	}*/

	/*
	  @Test public void parseJSONReaderRight() throws IOException { StringReader
	  jsonReader; int endCode = 0; int stringCounter = 1; int errorString = 0;
	  
	 //Right string for parsing 
	  rightJSONStrings = new String[]{
	 "{\"name\": \"\\u0123\"}", "{\"name\": \"a\"}", "{\"name\": \"\"}",
	  "{\"name\": \"true\"}", "{\"name\": true }", "{\"name\": \"1231\" }",
	  "{\"name\": [\"Hello\",true,false,123 ]}",
	  "{\"name\" : [\"name\" : [true,false,12]  123 , true]}",
	  "{\"name\" : [\"name\" : [true,false,12]]}",
	  
	  };
	  
	  for(String testingString : rightJSONStrings) { try { jsonReader = new
	  StringReader(testingString); endCode = zjson.parseJSONReader(jsonReader);
	  
	  System.out.println("String number " + stringCounter + " : " + testingString);
	  } catch (IOException ex) { System.out.println("String number " +
	  stringCounter + " : " + testingString +" "+ex.getMessage()); errorString++; }
	  stringCounter++; }
	  System.out.println("How many strings have not been passed : " + errorString);
	  assertTrue(errorString==0); }

	 
	/*@Test 
	public void parseJSONReaderWrong() throws IOException { 
		StringReader jsonReader; int endCode = 0; 
		int stringCounter = 1; int errorString = 0; //
		String [] wrongJSONStrings = new String[]{
				"{\"name\": \"\\u90xx\"}", "{\"name\": asdsasd }", "{\"name\": tru }",
				"{[\"name\": \"a\"]}", "{\"name\": true ", "{ name : \"true\"}",
				"{\"name\" true}", "{\"name\" : [\"name\":[\"gloomy\",\"ear\"]]}",
				"{\"name\": [\"Hello\",{true},false,123 ]}" };
	  
	  for(String testingString : wrongJSONStrings) { try { jsonReader = new
	  StringReader(testingString); endCode = zjson.parseJSONReader(jsonReader);
	  
	  System.out.println("String number " + stringCounter + " : " + testingString);
	  errorString++; } catch (IOException ex) { System.out.println("String number "
	  + stringCounter + " : " + testingString +" "+ex.getMessage());}
	  stringCounter++; } System.out.println("How many strings have been passed : "
	  + errorString); assertTrue(errorString==0); }
	  
	 /* @Test public void readJString() throws IOException { Reader jsonReader = new
	 * StringReader("hll\\\\g"); StringWriter sbw = new StringWriter(); StringBuffer
	 * sb = sbw.getBuffer();
	 * 
	 * int c = ZJson.readJString(jsonReader,sb); System.out.println(sbw);
	 * assertTrue(c==-1); }
	 */
	@Test
	public void readJLiteral() throws IOException {
		Reader jsonReader = new StringReader("s1\"llen");
		StringWriter sbw = new StringWriter();
		StringBuffer sb = sbw.getBuffer();

		int c = ZJson.readJLiteral(jsonReader, sb);

		assertTrue(c == 34);
	}

	@Test
	public void literal2value() {
		Boolean s = true;
		Boolean s1 = false;
		Long k = 51551425156L;
		double j = 2152.5;
		Object d = null;
		Object d1 = " ";
		
		Object[] testingObjects = { ZJson.literal2value("true"), ZJson.literal2value("false"),
				ZJson.literal2value("51551425156"), ZJson.literal2value("2152.5F"), ZJson.literal2value("null"),
				ZJson.literal2value(" ") };
		assertEquals(s, testingObjects[0]);
		assertEquals(s1, testingObjects[1]);
		assertEquals(k, testingObjects[2]);
		assertEquals(j, testingObjects[3]);
		assertEquals(d, testingObjects[4]);
		assertEquals(d1, testingObjects[5]);
	}

	@Test
	public void setType() {
		ZJson zjson = new ZJson();
		zjson.setType(1);

		assertTrue(zjson.getType() >= 0 && zjson.getType() <= 4);
	}

	@Test
	public void setPrintMode() {
		ZJson zjson = new ZJson();
		
		zjson.setPrintMode(0);

		assertTrue(zjson.getPrintMode() >= 0 && zjson.getPrintMode() <= 1);
	}
	
	@Test
	public void parsingRightJsons() throws IOException {
		//Code for finding all executable tests
		//If(file does not exists) -> file creates with no structure
		rightTest:{
		System.out.println("STARTING RIGHT JSON FILES TESTING!!!\nConfigure file:");
	
		ZJson zjson = new ZJson();
		String buffer;
		StringBuilder strBuilder = 
				new StringBuilder();
		File bufferForCreateTests = 
				new File("Resources/RightJSONS");
		File configureFile = 
				new File("Resources/RightJSONS/SignatureForRightJSONS");
		BufferedReader reader = 
				new BufferedReader(new FileReader(configureFile.getAbsolutePath()));
		try {
			if (configureFile.exists()) {
				while ((buffer = reader.readLine()) != null) {
					strBuilder.append(buffer);
				}
			}else {
				System.out.println("Configure file does not exists!"
						+ "\nCreate new configure file in Resources/RightJSONS/ file path!");
				break rightTest;
			}
			zjson = new ZJson(strBuilder.toString());
			strBuilder = null;
			buffer = null;

			ZJson forFilePaths = new ZJson(zjson.getItem(2).toString());
			
			for (int i = 0; i < forFilePaths.size(); i++) {
				System.out.println(forFilePaths.getItem(i));

				if (!new File(bufferForCreateTests.toString() + "/"
						+ new ZJson(forFilePaths.getItem(i).toString()).getItem(0)).exists()) {
					File f = new File(bufferForCreateTests.toString() + "/"
							+ new ZJson(forFilePaths.getItem(i).toString()).getItem(0));
					System.out.println(f);
					Files.createFile(f.toPath());
					
					try(FileWriter fw = new FileWriter(f)){
						fw.append("{\n \"comment\" : \"Пустой комментарий\",\n "
								+ "\"Result\" : \"Тест не проведен\", \n}");
						fw.flush();
					}catch(Exception ex) {
						System.out.println(ex.getMessage());
					}
				}	
			}
			//Read all executable tests from configure file
			ZJson executableTests = new ZJson(zjson.getItem(1).toString());	
			int errorFile = 0;
			int fileCounter = 1; 
			for(int i = 0; i < executableTests.size(); i++) {
				System.out.println("START FILE TESTING");
				String pathsToTests = bufferForCreateTests +"/" + executableTests.getItem(i);
				File bufferForTest = new File(pathsToTests);
				
				String pathToTestDir = bufferForCreateTests+"/Results/";
				File bufferForTestResult = new File(pathToTestDir);
				if(bufferForTestResult.exists());
				else {bufferForTestResult.mkdir();}
				
				reader = new BufferedReader(new FileReader(bufferForTest));
				strBuilder = new StringBuilder();
				
				while((buffer = reader.readLine()) != null) {
					if(buffer.contains("//"));
					else
					strBuilder.append(buffer);
				}
				
				String zjson_testing_string = strBuilder.toString();							
				StringReader jsonReader; 
				int endCode = 0; 
				
				try { jsonReader = new StringReader(zjson_testing_string); 
				      endCode = zjson.parseJSONReader(jsonReader);
				      System.out.println
				      ("Right Json file number " + (executableTests.getItem(i)) + " : " + zjson_testing_string);
				      errorFile++;
				      String pthToTest = bufferForCreateTests+"/Results/"+executableTests.getItem(i)+"_Result.txt";
				      try(FileWriter fw = new FileWriter(bufferForCreateTests+"/Results/"+executableTests.getItem(i)+"Result"))
						{ fw.append("\"Test result:\" : Test completed with good result! "); fw.flush();fw.close();}} 
				catch (IOException ex) { 
					System.out.println("Right Json file number "
					+ (executableTests.getItem(i)) + " : " + zjson_testing_string +" "+ex.getMessage());
					try(FileWriter fw = new FileWriter(bufferForCreateTests+"/Results/"+executableTests.getItem(i)+"Result"))
					{ fw.append("\"Test result:\" : Test completed with bad result! : \n" + ex.getMessage()); fw.flush();fw.close();}}
					fileCounter++;}
			
			strBuilder = null;
			reader.close();
			System.out.println("How many files have not been passed : "
					+ (fileCounter - 1 - errorFile) +" of " + (fileCounter - 1)); 
			System.out.println("See results in /Results/ path!");
			System.out.println();
			
			assertTrue(errorFile==0);
		} catch (Exception ex) {System.out.println(ex.getMessage());}
		}
	}

	@Test
	public void parsingWrongJsons() throws IOException {
		//Code for finding all executable tests
		//If(file does not exists) -> file creates with no structure
		wrongTest:{
		System.out.println("STARTING WRONG JSON FILES TESTING!!!\nConfigure file:");
		
		ZJson zjson = new ZJson();
		String buffer;
		StringBuilder strBuilder = 
				new StringBuilder();
		File bufferForCreateTests = 
				new File("Resources/WrongJSONS");
		File configureFile = 
				new File("Resources/WrongJSONS/SignatureForWrongJSONS");
		BufferedReader reader = 
				new BufferedReader(new FileReader(configureFile.getAbsolutePath()));
		try {
			if (configureFile.exists()) {
				while ((buffer = reader.readLine()) != null) {
					strBuilder.append(buffer);
				}
			}else {
				System.out.println("Configure file does not exists!"
						+ "\nCreate new configure file in Resources/WrongJSONS/ file path!");
				break wrongTest;
			}
			zjson = new ZJson(strBuilder.toString());
			strBuilder = null;
			buffer = null;

			ZJson forFilePaths = new ZJson(zjson.getItem(2).toString());
			
			for (int i = 0; i < forFilePaths.size(); i++) {
				System.out.println(forFilePaths.getItem(i));
				
				if (!new File(bufferForCreateTests.toString() + "/"
						+ new ZJson(forFilePaths.getItem(i).toString()).getItem(0)).exists()) {
					File f = new File(bufferForCreateTests.toString() + "/"
							+ new ZJson(forFilePaths.getItem(i).toString()).getItem(0));
					System.out.println(f);
					Files.createFile(f.toPath());
					
					try(FileWriter fw = new FileWriter(f)){
						fw.append("{\n \"comment\" : \"Пустой комментарий\",\n "
								+ "\"Result\" : \"Тест не проведен\", \n}");
						fw.flush();
					}catch(Exception ex) {System.out.println(ex.getMessage());}
				}	
			}
			//Read all executable tests from configure file
			ZJson executableTests = new ZJson(zjson.getItem(1).toString());
			int errorFile = 0;
			int fileCounter = 1; 
			for(int i = 0; i < executableTests.size(); i++) {
				System.out.println("START FILE TESTING");
				String pathsToTests = bufferForCreateTests +"/" + executableTests.getItem(i);
				File bufferForTest = new File(pathsToTests);
				reader = new BufferedReader(new FileReader(bufferForTest));
				strBuilder = new StringBuilder();
				
				while((buffer = reader.readLine()) != null) {
					if(buffer.contains("//"));
					else
					strBuilder.append(buffer);}
				
				String pathToTestDir = bufferForCreateTests+"/Results/";
				File bufferForTestResult = new File(pathToTestDir);
				if(bufferForTestResult.exists());
				else {bufferForTestResult.mkdir();}
				
				String zjson_testing_string = strBuilder.toString();							
				StringReader jsonReader; int endCode = 0; 
				
				try { jsonReader = new StringReader(zjson_testing_string); 
				      endCode = zjson.parseJSONReader(jsonReader);
				      System.out.println
				      ("Wrong Json file number " + (executableTests.getItem(i)) + " : " + zjson_testing_string);
				      errorFile++;
				      try(FileWriter fw = new FileWriter(bufferForCreateTests+"/Results/"+executableTests.getItem(i)+"Result"))
					  { fw.append("\"Test result:\" : Test failed! :\nJaon passed this test"); fw.flush(); fw.close(); }} 
				catch (IOException ex) { 
					System.out.println("Wrong Json file number "
					+ (executableTests.getItem(i)) + " : " + zjson_testing_string +" "+ex.getMessage());
					try(FileWriter fw = new FileWriter(bufferForCreateTests+"/Results/"+executableTests.getItem(i)+"Result"))
					{ fw.append("\"Test result:\" : Test completed with good result! \n:" + ex.getMessage()); fw.flush(); fw.close();}}
					fileCounter++;}
			
			strBuilder = null;
			reader.close();
			System.out.println("How many files have been passed : "
					+ errorFile + " of "+(fileCounter-1));
			System.out.println("See results in /Results/ path!");
			System.out.println();
			
			assertTrue(errorFile==0);
		} catch (Exception ex) {System.out.println(ex.getMessage());}
		}
	}	
}
