package SSAFY.빡구현;

import java.util.*;
import java.io.*;

            /*
            90도: (y,x) <- 단순 x, y 위치 변경 x, (1,2) -> (2, 4) / (3, 4) -> (4,2) / (4,1) -> (1,1) //
            = x가 y자리에 오며 N - 1 - y가 x위치에 온다 (수학적인 원리는 모름 귀납적으로 품) (왜 90도 회전하면 x가 y가 될까?)
            180도 회전: 90도 두 번
            270도 회전: 90도 세 번
            6 9 4 7 0 5
            8 9 9 2 6 5
            6 8 5 4 9 8
            2 2 7 7 8 4
            7 5 1 9 7 9
            8 9 3 9 7 6

            1) 테스트 횟수 T 받기
            2) N 받기
            3) 90도 map, 180 map, 270 map 각각 만들기
            4) 각 맵에 맞게 90도 회적 적용 (1번, 2번 3번)
            5) StringBuilder에 append할 때 map 3개를 적절히 배치해서 넣어도 되지만 연습 차원에서 합친 map 만들기
            6) 합친 map을 출력하기

            사용 변수: 입출력 객체, map 3개, N, M
            */

public class matric_rotation {
    // static을 붙이지 않은 경우 matric_rotation 객체를 만든 후
    // 해당 객체를 이용해서 내부 class를 생성할 수 있다.
    public static class Solution {

        private List<List<Integer>> rotation_90(List<List<Integer>> map, int N) {
            // y가 x자리가 오며 N - x가 y위치에 온다

            List<List<Integer>> temp = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                temp.add(new ArrayList<Integer>(map.get(i)));
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int y = j;
                    int x = N - i - 1;
                    temp.get(y).set(x, map.get(i).get(j));
                }
            }
            return temp;
        }

        /*
        회전시킨 map의 원소 타입이 Integer이기에 map 사이사이에 공백을 넣을 수 없다.
        StringBuilder을 만드는 시점에 넣어야 할 것 같다.
        private List<List<Integer>> merge_map (List<List<Integer>> map90,
                                               List<List<Integer>> map180, List<List<Integer>> map270) {

            List<List<Integer>> merged = new ArrayList<>();

            merged.addAll(map90);
            merged.addAll(map180);
            merged.addAll(map270);

            return merged;
        }
        */

        private void solve() throws Exception {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer token;

            List<List<Integer>> map90 = new ArrayList<>();
            List<List<Integer>> map180 = new ArrayList<>();
            List<List<Integer>> map270 = new ArrayList<>();

            // BufferedReader를 사용하려면 java.io 패키지를 사용해야 하며 throws Exception읗 method에 정의해야 한다.
            int T = Integer.parseInt(br.readLine());
            for (int t = 0; t < T; t++) {
                int N = Integer.parseInt(br.readLine());

                map90.clear();
                map180.clear();
                map270.clear();

                for (int i = 0; i < N; i++) {
                    map90.add(new ArrayList<Integer>(N));
                    map180.add(new ArrayList<Integer>(N));
                    map270.add(new ArrayList<Integer>(N));
                }
                for (int i = 0; i < N; i++) {
                    token = new StringTokenizer(br.readLine());
                    for (int j = 0; j < N; j++) {
                        Integer unit = Integer.valueOf(token.nextToken());
                        map90.get(i).add(unit);
                        map180.get(i).add(unit);
                        map270.get(i).add(unit);
                    }
                }

                map90 = rotation_90(map90, N);

                map180 = rotation_90(map180, N);
                map180 = rotation_90(map180, N);

                map270 = rotation_90(map270, N);
                map270 = rotation_90(map270, N);
                map270 = rotation_90(map270, N);

                sb.append("#").append(t + 1).append('\n');
                for (int i = 0; i < N; i++) {

                    for (int num : map90.get(i)) {
                        sb.append(num);
                    }
                    sb.append(" ");
                    for (int num : map180.get(i)) {
                        sb.append(num);
                    }
                    sb.append(" ");
                    for (int num : map270.get(i)) {
                        sb.append(num);
                    }
                    if (i + 1 != N)
                        sb.append('\n');
                }
                if (t + 1 != T)
                    sb.append('\n');
            }
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] arg) throws Exception {
        Solution ob = new Solution();
        ob.solve();
    }
}
