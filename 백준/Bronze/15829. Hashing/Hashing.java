import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String s = br.readLine();
		long result = 0;
		long pow = 1;
		for(int i = 0; i < n; i++) {
			result += ((s.charAt(i) - 96) * pow);
			pow = (pow * 31) % 1234567891;
		}
		System.out.println(result % 1234567891);
	}

}