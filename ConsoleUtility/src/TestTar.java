import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;

class TestTar {

	@Test
	void out() throws IOException {
		
		String[] inputPaths = {"C:\\repo\\ConsoleUtility\\src\\input\\i.txt",
				"C:\\repo\\ConsoleUtility\\src\\input\\n.txt",
				"C:\\repo\\ConsoleUtility\\src\\input\\put.txt"};
		String[] names = {"i.txt", "n.txt", "put.txt"};
		String output = "C:\\repo\\ConsoleUtility\\src\\output\\output.txt";
		
		Tar.out(inputPaths, output);
		
		InputStream inputStream = new FileInputStream(output);
		InputStreamReader inputStreamReader =
				new InputStreamReader(inputStream,Charset.forName("utf-8"));
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		for (int i = 0; i < inputPaths.length; i++) {
			
			InputStream inStr = new FileInputStream(inputPaths[i]);
			InputStreamReader inStrReader =
					new InputStreamReader(inStr,Charset.forName("utf-8"));
			BufferedReader bufReader = new BufferedReader(inStrReader);
			
			String str = bufferedReader.readLine();
			assertEquals(str, names[i]);
			
			String str1;
			while ((str1 = bufReader.readLine()) != null) {
				
				str = bufferedReader.readLine();
				assertEquals(str, str1);
				
			}
			bufReader.close();
		}
		bufferedReader.close();
	}
	
	@Test
	void u() throws IOException {
		
		String input = "C:\\repo\\ConsoleUtility\\src\\output\\output.txt";
		String[] names = {"i.txt", "n.txt", "put.txt"};
		
		File inputFile = new File(input);
		String inputPath = inputFile.getParentFile().getPath() + "\\";
		
		Tar.u(input);
		
		InputStream inputStream = new FileInputStream(input);
		InputStreamReader inputStreamReader =
				new InputStreamReader(inputStream,Charset.forName("utf-8"));
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		String str = bufferedReader.readLine();
		String str1;
		
		int count = 0;
		
		while (str != null) {
			
			InputStream inStr = new FileInputStream(inputPath + names[count]);
			InputStreamReader inStrReader =
					new InputStreamReader(inStr,Charset.forName("utf-8"));
			BufferedReader bufReader = new BufferedReader(inStrReader);
			
			assertEquals(str, names[count]);
			
			while ((str1 = bufReader.readLine()) != null) {
				
				str = bufferedReader.readLine();
				assertEquals(str, str1);
				
			}
			count++;
		}	
	}
}
