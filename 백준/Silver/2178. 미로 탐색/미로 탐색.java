import java.awt.Point;
import java.util.*;

public class Main {
    static int n, m;    
    static int[][] arr; 
    static boolean[][] visited; 
    static int[] dx = {-1, 1, 0, 0};  
    static int[] dy = {0, 0, -1, 1}; 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n][m];
        visited = new boolean[n][m];
        sc.nextLine(); 
        
        for (int i = 0; i < n; i++) {
            String line = sc.next();    
            for (int j = 0; j < m; j++) {
                arr[i][j] = line.charAt(j) - '0'; 
            }
        }
        bfs(0, 0);   
        System.out.println(arr[n - 1][m - 1]); 
    }
   
    static void bfs(int x, int y) {
       
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));
        visited[x][y] = true;   

        while (!queue.isEmpty()) {
            Point currentPoint = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                int nextX = currentPoint.x + dx[i];
                int nextY = currentPoint.y + dy[i];

                // 1. 범위 이내에 있는가?
                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m)
                    continue;  
                // 2. 막힌 길인가?
                if (arr[nextX][nextY] == 0)
                    continue;   
                // 3. 이미 방문 했나?
                if (visited[nextX][nextY])
                    continue;   
                
                queue.offer(new Point(nextX, nextY));
                visited[nextX][nextY] = true;
                
                arr[nextX][nextY] = arr[currentPoint.x][currentPoint.y] + 1;
            }
        }
    }
}