package programas;

import java.util.*;

public class Hide_cactus {
    public static void main(String[] args) {
        int[][] drops = {
                {0, 0},
                {3, 1},
                {1, 3},
                {2, 4},
                {1, 1},
                {2, 2},
                {2, 3},
                {0, 4}
        };
        // non-static inner class를 생성하려면 바깥 클래스가 필요하다.
        Hide_cactus outer = new Hide_cactus();
        Solution ob = outer.new Solution();
        int[] answer = ob.solution(4, 5, 2, 2, drops);
        System.out.println(Arrays.toString(answer));
    }

    class Solution {
        public int[] solution(int m, int n, int h, int w, int[][] drops) {
            int dropCount = drops.length;
            int INF = dropCount + 1;

            // rainTime[r][c] = 해당 칸에 비가 오는 순서
            // 1차원 배열로 관리: index = r * n + c
            int[] rainTime = new int[m * n];
            Arrays.fill(rainTime, INF);

            for (int i = 0; i < dropCount; i++) {
                int r = drops[i][0];
                int c = drops[i][1];
                rainTime[r * n + c] = i + 1;
            }

            int possibleRows = m - h + 1;
            int possibleCols = n - w + 1;

            // rowMin[r][c] = r행에서 c열부터 w칸의 최솟값
            // 크기: m x possibleCols
            int[] rowMin = new int[m * possibleCols];

            int[] deque = new int[Math.max(m, n)];

            // 1단계: 각 행마다 가로 w칸 슬라이딩 최솟값 구하기
            for (int r = 0; r < m; r++) {
                int head = 0;
                int tail = 0;
                int base = r * n;

                for (int c = 0; c < n; c++) {
                    // 현재 윈도우 범위를 벗어난 인덱스 제거
                    while (head < tail && deque[head] <= c - w) {
                        head++;
                    }

                    int currentValue = rainTime[base + c];

                    // 뒤쪽에서 현재값보다 크거나 같은 값 제거
                    // deque 안에는 값이 증가하는 순서로 유지됨
                    while (head < tail && rainTime[base + deque[tail - 1]] >= currentValue) {
                        tail--;
                    }

                    deque[tail++] = c;

                    // c가 w - 1 이상이면 가로 w칸짜리 구간이 완성됨
                    if (c >= w - 1) {
                        int left = c - w + 1;
                        rowMin[r * possibleCols + left] = rainTime[base + deque[head]];
                    }
                }
            }

            int bestTime = -1;
            int bestR = 0;
            int bestC = 0;

            // 2단계: rowMin에서 세로 h칸 슬라이딩 최솟값 구하기
            for (int c = 0; c < possibleCols; c++) {
                int head = 0;
                int tail = 0;

                for (int r = 0; r < m; r++) {
                    // 현재 세로 윈도우 범위를 벗어난 행 제거
                    while (head < tail && deque[head] <= r - h) {
                        head++;
                    }

                    int currentValue = rowMin[r * possibleCols + c];

                    // 뒤쪽에서 현재값보다 크거나 같은 값 제거
                    while (head < tail && rowMin[deque[tail - 1] * possibleCols + c] >= currentValue) {
                        tail--;
                    }

                    deque[tail++] = r;

                    // r이 h - 1 이상이면 세로 h칸짜리 구간이 완성됨
                    if (r >= h - 1) {
                        int top = r - h + 1;

                        // 왼쪽 위가 (top, c)인 h x w 구역의 첫 비 맞는 시점
                        int firstRainTime = rowMin[deque[head] * possibleCols + c];

                        // 더 늦게 비를 맞는 위치를 선택
                        // 동점이면 더 위쪽, 그래도 같으면 더 왼쪽
                        if (
                                firstRainTime > bestTime ||
                                        (
                                                firstRainTime == bestTime &&
                                                        (top < bestR || (top == bestR && c < bestC))
                                        )
                        ) {
                            bestTime = firstRainTime;
                            bestR = top;
                            bestC = c;
                        }
                    }
                }
            }
            return new int[] { bestR, bestC };
        }
    }
}
