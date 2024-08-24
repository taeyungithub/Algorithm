import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] maze = new int[N][M];
        int[][] dist = new int[N][M];
        sc.nextLine();

        for (int i = 0; i < N; i++) {
            String str = sc.next();
            for (int j = 0; j < M; j++) {
                maze[i][j] = str.charAt(j) - '0';
                dist[i][j] = -1;
            }
        }

        // 상 하 좌 우 계산할 때 사용하기 위해
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // bfs 큐 이용, 좌표값을 쉽게 저장하기 위해 Pair클래스 구현
        Queue<Pair> queue = new LinkedList<>();
        // 처음 값 넣기
        Pair startPair = new Pair(0, 0);
        dist[0][0] = 0;
        queue.offer(startPair);

        // 큐가 빌때까지
        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            // 상하좌우 계산
            for (int i = 0; i < 4; i++) {

                int mx = p.x + dx[i];
                int my = p.y + dy[i];

                // 범위 못넘어가게 하기
                if (mx < 0 || mx >= N || my < 0 || my >= M) {
                    continue;
                }

                // 막다른 길, 이미 방문함
                // (최단거리가 있으면 먼저 도착해서 dist를 바꿔놨기 때문에 나중에 도착한 값으로는 못바꾼다.)
                if (maze[mx][my] == 0 || dist[mx][my] != -1) {
                    continue;
                }

                queue.offer(new Pair(mx, my));

                dist[mx][my] = dist[p.x][p.y] + 1;
            }
        }

            System.out.print((dist[N - 1][M - 1] + 1));
    }

    public static class Pair {
        private int x;
        private int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

    }
}