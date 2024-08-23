package algorithm;

import java.util.*;

public class Maze {

    public static void main(String[] args) {
        // 10x10 미로
        int[][] maze = {
                { 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0 },
                { 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1 },
                { 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0 },
                { 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0 }
        };

        // 상 하 좌 우 계산할 때 사용하기 위해
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        // 거리계산 하기 위해 (0,0)은 시작지점
        int[][] dist = {
                { 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }
        };

        // bfs 큐 이용, 좌표값을 쉽게 저장하기 위해 Pair클래스 구현
        Queue<Pair> queue = new LinkedList<>();
        // 처음 값 넣기
        Pair startPair = new Pair(0, 0);
        queue.offer(startPair);

        // 큐가 빌때까지
        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            // 상하좌우 계산
            for (int i = 0; i < 4; i++) {

                int mx = p.x + dx[i];
                int my = p.y + dy[i];

                // 범위 못넘어가게 하기
                if (mx < 0 || mx > 9 || my < 0 || my > 9) {
                    continue;
                }

                // 막다른 길, 이미 방문함
                // (최단거리가 있으면 먼저 도착해서 dist를 바꿔놨기 때문에 나중에 도착한 값으로는 못바꾼다.)
                if (maze[mx][my] == 1 || dist[mx][my] != -1) {
                    continue;
                }

                queue.offer(new Pair(mx, my));

                dist[mx][my] = dist[p.x][p.y] + 1;
            }
        }

        // 거리 확인용
        // for (int i = 0; i < 10; i++) {
        //     for (int j = 0; j < 10; j++) {
        //         System.out.print(dist[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        // 성공
        if (dist[9][9] != -1) {
            // 마지막 지점의 거리 + 1
            System.out.print("Pass, " + (dist[9][9] + 1));
        }
        // 실패
        else {
            int max = 0;
            // 많은 칸을 이동한 값을 출력
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (dist[i][j] > max) {
                        max = dist[i][j];
                    }
                }
            }
            System.out.println("Fail, " + max);
        }
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