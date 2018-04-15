import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;

public class Tar {
	
	public static void  out(String[] inputPaths, String outputPath) throws IOException {
		
		File outputFile = new File(outputPath);
		outputFile.createNewFile();
		PrintWriter output = new PrintWriter(outputPath);
		
		for (String inputPath : inputPaths) {
			
			InputStream inputStream = new FileInputStream(inputPath);
			InputStreamReader inputStreamReader =
					new InputStreamReader(inputStream,Charset.forName("utf-8"));
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String str;
			while ((str = bufferedReader.readLine()) != null) {
				
				output.println(str);
				
			}
			
			bufferedReader.close();
			
		}
		
		output.close();
		
	}
	
	
	public static void u(String inputPath) throws IOException {
		
		File inputFile = new File(inputPath);
		
		InputStream inputStream = new FileInputStream(inputPath);
		InputStreamReader inputStreamReader =
				new InputStreamReader(inputStream,Charset.forName("utf-8"));
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		while(true) {
			
			String outputPath = inputFile.getAbsolutePath();
			
			File outputFile = new File(outputPath);
			outputFile.createNewFile();
			PrintWriter output = new PrintWriter(outputPath);
			
			String str;
			while ((str = bufferedReader.readLine()) != null) {
				
				output.println(str);
				
			}
			
			bufferedReader.close();
			output.close();
			
		}
		
	}
	
}
