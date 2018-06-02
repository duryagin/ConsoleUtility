import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;

/* сначала запускаем out - соединяем текстовые файлы;
 * затем запускаем u, разбивая соединённый текст на
 * несколько первоначальных
 */
class TestTar {

	@Test
	void out() throws IOException { // соединяем заданные файлы
		
		String[] inputPaths = {"src/inputAndOutput/i.txt",
				"src/inputAndOutput/n.txt",
				"src/inputAndOutput/put.txt"};
		String[] names = {"i.txt", "n.txt", "put.txt"};
		String output = "src/inputAndOutput/output.txt";
		
		Tar.out(inputPaths, output);
		
		InputStream inputStream = new FileInputStream(output);
		InputStreamReader inputStreamReader =
				new InputStreamReader(inputStream,Charset.forName("utf-8"));
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		String str, str1;
		
		for (int i = 0; i < inputPaths.length; i++) {
			
			InputStream inStr = new FileInputStream(inputPaths[i]);
			InputStreamReader inStrReader =
					new InputStreamReader(inStr,Charset.forName("utf-8"));
			BufferedReader bufReader = new BufferedReader(inStrReader);
			
			str = bufferedReader.readLine();
			assertEquals(str, "fileName");
			
			str = bufferedReader.readLine();
			assertEquals(str, names[i]);
			
			while ((str1 = bufReader.readLine()) != null) {
				
				str = bufferedReader.readLine();
				assertEquals(str, str1);
				
			}
			bufReader.close();
		}
		bufferedReader.close();
	}
	
	
	@Test
	void u() throws IOException { // разбиваем входной файл, полученный в out
		
		String input = "src/inputAndOutput/output.txt";
		String[] names = {"i.txt", "n.txt", "put.txt"};
		
		File inputFile = new File(input);
		String inputPath = inputFile.getParentFile().getPath() + "/";
		
		Tar.u(input);
		
		InputStream inputStream = new FileInputStream(input);
		InputStreamReader inputStreamReader =
				new InputStreamReader(inputStream,Charset.forName("utf-8"));
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		String str, str1;
		
		int count = 0;
		
		while ((str = bufferedReader.readLine()) != null) {
			
			InputStream inStr = new FileInputStream(inputPath + names[count]);
			InputStreamReader inStrReader =
					new InputStreamReader(inStr,Charset.forName("utf-8"));
			BufferedReader bufReader = new BufferedReader(inStrReader);
			
			assertEquals(str, "fileName");
			
			str = bufferedReader.readLine();
			assertEquals(str, names[count]);
			
			while ((str1 = bufReader.readLine()) != null) {
				
				str = bufferedReader.readLine();
				assertEquals(str, str1);
				
			}
			count++;
		}	
	}
}