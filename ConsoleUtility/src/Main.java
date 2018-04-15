import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		int length = args.length;
		
		if (args[length - 2].equals("-out")) {
			
			String[] inputPaths = new String[length - 2];
			
			for (int i = 0; i < length - 2; i++) {
				
				inputPaths[i] = args[i];
				
			}
			
			Tar.out(inputPaths, args[length - 1]);
			
			
		} else if (args[0].equals("-u")) {
			
			Tar.u(args[1]);
			
		}
		
	}

}
