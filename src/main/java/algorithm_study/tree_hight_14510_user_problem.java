package algorithm_study;

import java.util.*;
import java.io.*;

public class tree_hight_14510_user_problem {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static List<Integer> list = new ArrayList<>();
    static StringTokenizer st;

    static int N;

    public static void main(String[] args) throws Exception {

        int TC = Integer.parseInt(br.readLine());

        for (int t = 1; t <= TC; t++) {
            N = Integer.parseInt(br.readLine());

            // 초기화
            list.clear();
            st = new StringTokenizer(br.readLine());
            int max = 0;

            // 나무 높이 입력 + 최대 높이 찾기
            for (int i = 0; i < N; i++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp > max) {
                    max = temp;
                }
                list.add(temp);
            }
            
            // 각 나무가 추가로 자라야 하는 높이로 변경
            for (int i = 0; i < N; i++) {
                int temp = list.get(i);
                list.set(i, max - temp);
            }

            int day = 1;

            // 모든 나무가 목표 높이에 도달할 때까지 반복
            while (!allSame()) {

                // 현재 남아있는 값 중 2의 개수 확인
                int two = 0;
                int total = 0;
                for (int i = 0; i < N; i++) {
                    int temp = list.get(i);
                    if (temp != 0) {
                        total++;
                        if (temp == 2) {
                            two++;
                        }
                    }
                }

                // 홀수 날에는 1만큼 성장
                if (day % 2 == 1) {
                    boolean water = false;
                    
                    // 값 1이 있다면 우선적으로 1 감소
                    for (int i = 0; i < N; i++) {
                        int temp = list.get(i);
                        if (temp == 1) {
                            list.set(i, temp - 1);
                            water = true;
                            break;
                        }
                    }
                    
                    // 0과 2가 아닌 값을 찾아서 1 감소
                    if (!water) {
                        for (int i = 0; i < N; i++) {
                            int temp = list.get(i);
                            if (temp != 0 && temp != 2) {
                                list.set(i, temp - 1);
                                water = true;
                                break;
                            }
                        }
                    }

                    // 남아있는 값이 전부 2인 경우
                    if (!water) {

                        // 2가 2개 이상이라면 하나를 1 감소
                        if (two >= 2) {
                            for (int i = 0; i < N; i++) {
                                if (list.get(i) == 2) {
                                    list.set(i, 1);
                                    break;
                                }
                            }
                        }
                        // 2가 하나만 남았다면 물을 주지 않고 패스
                    }
                }

              
                // 짝수 날에는 2만큼 성장
                else if (day % 2 == 0) {
                	
                    // 2 이상인 값을 찾아서 2 감소
                    for (int i = 0; i < N; i++) {
                        int temp = list.get(i);
                        if (temp >= 2) {
                            list.set(i, temp - 2);
                            break;
                        }
                    }
                    // 모든 값이 0 또는 1이라면 물을 주지 않고 패스
                }
                day++;
            }

            System.out.println("#" + t + " " + (day - 1));
        }
    }

    // 모든 나무가 최대 높이에 도달했는지 확인
    static boolean allSame() {
        for (int a : list) {
            if (a != 0) {
                return false;
            }
        }
        return true;
    }
}

//로직 (논리 + 코드 레벨)
/*

*/

//배운 것
/*

*/

//input
/*
4
1
5
3
1 4 5
8
2 7 3 10 6 10 1 8
20
3 11 7 15 2 14 9 6 15 1 12 8 4 10 5 13 7 2 14 9
(output)
#1 0
#2 4
#3 22
#4 89
*/

//다른 사람 코드
/*

*/