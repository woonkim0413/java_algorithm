package algorithm_study;

import java.util.*;
import java.io.*;

public class N_Queen_2806 {

    static int N;
    static int count;

    // queen[row] = col
    // row행의 퀸이 몇 번째 열에 있는지 저장
    static int[] queen;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            queen = new int[N];
            count = 0;

            dfs(0);

            System.out.println("#" + tc + " " + count);
        }
    }

    static void dfs(int row) {

        // 모든 행에 퀸을 하나씩 배치했다면 성공
        if (row == N) {
            count++;
            return;
        }

        // 현재 row에서 퀸을 놓을 열 선택
        for (int col = 0; col < N; col++) {

            queen[row] = col;

            // 현재 위치가 가능한 경우에만 다음 행으로 이동
            if (isPossible(row)) {
                dfs(row + 1);
            }
        }
    }

    static boolean isPossible(int row) {

        // 현재 row보다 위쪽에 있는 퀸들과 비교
        for (int prevRow = 0; prevRow < row; prevRow++) {

            // 같은 열
            if (queen[row] == queen[prevRow]) {
                return false;
            }

            // 같은 대각선
            if (Math.abs(row - prevRow)
                    == Math.abs(queen[row] - queen[prevRow])) {
                return false;
            }
        }

        return true;
    }
}


//이해
/*
NxN의 체스판에 N개의 퀸을 서로 공격하지 못하도록 배치하는 경우의 수를 구한다.

퀸은
1. 같은 행
2. 같은 열
3. 같은 대각선
에 있는 다른 말을 공격할 수 있다.

한 행에 퀸을 하나씩만 배치하면
'같은 행' 문제는 자동으로 해결된다.

따라서 현재 퀸을 놓을 때
- 같은 열에 기존 퀸이 있는지
- 같은 대각선에 기존 퀸이 있는지
만 확인하면 된다.
*/


//로직 (논리 + 코드 레벨)
/*
1. 0번째 행부터 시작한다.

2. 현재 행에서 모든 열을 하나씩 시도한다.

   for (int col = 0; col < N; col++)

3. queen[row] = col 로 현재 위치에 퀸을 놓아본다.

4. 위쪽 행에 이미 놓인 퀸들과 비교한다.

   - 같은 열이라면 실패
   - 같은 대각선이라면 실패

5. 현재 위치가 가능하다면 다음 행을 탐색한다.

   dfs(row + 1)

6. 어느 위치에서도 놓을 수 없다면
   현재 dfs 함수가 종료되고 이전 행으로 돌아간다.

   이것이 백트래킹이다.

7. row == N이 되었다는 것은
   0 ~ N-1행까지 모든 퀸을 정상적으로 배치했다는 의미이다.

   따라서 count++ 한다.
*/


//배운 것
/*
1. N-Queen에서는 2차원 체스판을 꼭 만들 필요가 없다.

queen[row] = col

형태로 저장하면
각 행의 퀸 위치만 기록할 수 있다.


2. 같은 열 판단

queen[row] == queen[prevRow]


3. 같은 대각선 판단

행의 차이 == 열의 차이

Math.abs(row - prevRow)
==
Math.abs(queen[row] - queen[prevRow])


4. 백트래킹의 기본 구조

선택
→ 가능한지 검사
→ 가능하면 다음 단계
→ 막히면 이전 단계로 돌아와 다른 선택


5. dfs(row)는

"현재 row행에 퀸을 어디에 놓을지 결정한다"

라고 생각하면 이해하기 쉽다.
*/


//input
/*
3
1
2
4

(output)
#1 1
#2 0
#3 2
*/


//다른 사람 코드
/*

*/