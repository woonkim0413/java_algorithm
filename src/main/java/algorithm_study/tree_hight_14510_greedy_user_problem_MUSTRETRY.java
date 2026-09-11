package algorithm_study;

import java.io.*;
import java.util.*;

public class tree_hight_14510_greedy_user_problem_MUSTRETRY {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] tree = new int[N];
            int max = 0;

            // 나무 리스트 찾으면서 최대 높이 찾기
            for (int i = 0; i < N; i++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp > max) {
                    max = temp;
                }
                tree[i] = temp;
            }

            int one = 0; // 필요한 최소 홀수 날을 구한다.
            int two = 0;

            // 필요한 +1, +2 작업 개수 계산 
            // one이 최소가 되는 경우의 수가 구해진다. 
            // -> 최적화 매우 안되어 있다. ex) two는 9, one는 2라면 18일 이후에나 조건을 만족할 수 있다.
            // 반대로 two 7, one 6이라면 14일 이후에 조건을 만족시킬 수 있다.(two 6, one 8은 15일로 오히려 안좋아짐)
            for (int i = 0; i < N; i++) {
                int diff = max - tree[i];
                one += diff % 2;
                two += diff / 2;
            }

            // +2가 너무 많으면 일부를 +1 두 개로 변환한다
            while (two > one + 1) {
                two--;
                one += 2;
            }

            long finishDay;
            
            // 홀 수 날이 더 많은 경우엔 홀수날 완료 기준으로 전체 날 수를 계산한다.
            if (one > two) {
            	finishDay = one * 2 - 1;
            // 짝 수 날이 더 많거나 같은 경우엔 짝수날 완료 기준으로 전체 날 수를 계산한다.
            } else {
            	finishDay = two * 2;
            }

            System.out.println("#" + tc + " " + finishDay);
        }
    }
}

//로직 (논리 + 코드 레벨)
/*

*/

//배운 것
/*
 1) 스케쥴링 알고리즘 (공부하기)
 해당 문제는 결국 성장시켜야 하는 총 나무 갯수와 가장 최소 홀수 날과 짝수 날을 구한 뒤에
 조건에 맞게 스케쥴링 하는 문제였다.
 이 문제는 홀 수 날과 짝 수 날의 스케쥴링 문제로 접근해서 균형을 유지하면서 최소 날을 구하는 식으로
 접근을 구하는 것이 포인트였다. 난 접근 못 하고 조건을 나열하여 문제를 풀었다.
 -> 다시 풀어봐야 할 듯.
 
 2) 문제에서 로직 뽑아내기 (로직화)
 시작은 나무가~, 물을 몇 일~ 이런 식으로 자연어로 문제를 이해하지만
 문제를 이해한 뒤 코드 레벨로 전환하는 시점엔 자연어적인 이해에서 논리적인 이해로 바꿔야 한다.
 예를 들어 해당 문제를 자연어 레벨에서 이해하면 매일 물을 줘야하니 for문을 돌리며 오늘이 홀숫날인지 짝숫날인지
 파악한 후 적절한 나무를 찾아 물을 줘야 할 것 같지만
 시뮬레이션 알고리즘임을 파악하고 로직으로 뽑아날 때엔 홀숫날과 짝숫날의 적절한 균형을 찾는 식으로 접근하게 된다.
*/

//input
/*

(output)

*/

//다른 사람 코드
/*

*/
