package SSAFY.빡구현;

import java.util.*;

public class Two_sequences_of_numbers {

    static int[] Aj;
    static int[] Bj;
    static int[] Long;
    static int[] Short;

    public static void main(String[] arg) throws Exception {
        // T -> N,M -> Aj,Bj
        // 알고리즘: 배열 빡구현
        /*
        N,M 중 무엇이 더 큰지 확인한다.
        2중 for문으로 작은 쪽은 고정한 채로 큰 쪽의 접근 index를 1씩 증가시키며 작은쪽이랑 곱한다.
        loop의 마지막 index가 m-1에 닿으면 순환을 종료한다.
        loop를 돌리며 얻은 최고값을 출력한다.
         */
        /*
        문제 풀며 느낀 내 약점:
        1) 이미지 or 자연어로 설명 가능한 동작을 코드로 표현하는 단계가 매끄럽지 않다.
        2) 정형화 된 탄탄한 코드가 없고 그때 그때 즉석에서 가능할 것 같은 로직을 쥐어짜서 문제를 푼다.
         */

        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            int max = Integer.MIN_VALUE;
            int N = sc.nextInt();
            int M = sc.nextInt();

            Aj = new int[N];
            Bj = new int[M];

            for (int i = 0; i < N; i++) {
                Aj[i] = sc.nextInt();
            }
            for (int i = 0; i < M; i++) {
                Bj[i] = sc.nextInt();
            }

            if (N > M) {
                Long = Aj;
                Short = Bj;
            } else {
                Long = Bj;
                Short = Aj;
            }

            for (int i = 0; i <= Long.length - Short.length; i ++) {
                int temp = 0;
                for (int j = 0; j < Short.length; j ++) {
                    temp += Long[j + i] * Short[j];
                }
                if (max < temp)
                    max = temp;
            }
            sb.append("#").append(t + 1).append(" ").append(max).append('\n');
        }
        System.out.println(sb.toString());
    }
}
