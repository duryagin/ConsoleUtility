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
			
			File file = new File(inputPath);
			
			output.println("fileName");
			output.println(file.getName());
			
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
		
		String string = bufferedReader.readLine();
		if (!string.contains("fileName")) {
			return;
		}
		
		while(true) {
			
			String str = null;
			
			String name = bufferedReader.readLine();
			if (name == null) {
				break;
			}
			
			String outputPath = inputFile.getParentFile().getAbsolutePath() + "/" + name;
			
			File outputFile = new File(outputPath);
			outputFile.createNewFile();
			PrintWriter output = new PrintWriter(outputPath);
			
			while ((str = bufferedReader.readLine()) != null) {
				
				if (str.contains("fileName")) {
					break;
				}
				
				output.println(str);
				
			}	
			output.close();	
		}	
	}
}
