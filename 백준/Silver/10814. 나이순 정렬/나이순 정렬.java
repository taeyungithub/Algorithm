import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[][] array = new String[n][2];
        for (int i = 0; i < n ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            array[i][0] = st.nextToken();
            array[i][1] = st.nextToken();
        }

        Arrays.sort(array, new Comparator<String[]>() {
			@Override
			public int compare(String[]o1, String[]o2) {
				return Integer.parseInt(o1[0]) - Integer.parseInt(o2[0]);
			}
		});
        for(int i = 0; i < array.length; i++){
            System.out.println(array[i][0] + " " + array[i][1]);
        }
    }
}