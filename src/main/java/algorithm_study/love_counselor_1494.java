package algorithm_study;

import java.util.*;
import java.io.*;

//백터 : 방향이 있는 x,y 좌표
//백터의 크기 : x^2 + y^2의 제곱근
//백터의 합 : 두 백터의 x,y좌표를 더한 뒤에 백터의 크기를 구한 값
public class love_counselor_1494 {

    static int N;
    static long[][] cor;
    static long totalX, totalY;
    static long min;
    static long sumX = 0;
    static long sumY = 0;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
    	
        int TC = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= TC; t++) {
            N = Integer.parseInt(br.readLine());

            cor = new long[N][2];
            totalX = 0;
            totalY = 0;
            min = Long.MAX_VALUE;
            sumX = 0;
            sumY = 0;

            // 지렁이 좌표 입력 및 전체 좌표합 계산
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                cor[i][0] = Long.parseLong(st.nextToken());
                cor[i][1] = Long.parseLong(st.nextToken());

                totalX += cor[i][0];
                totalY += cor[i][1];
            }

            // 0번 지렁이를 도착점으로 고정
            dfs(0, 0);

            System.out.println("#" + t + " " + min);
        }
    }

    // idx: 현재 검사할 지렁이 인덱스
    // count: 도착점으로 선택한 지렁이 수
    // sumX, sumY: 도착점 좌표합
    static void dfs(int idx, int count) {

        // 도착점 N/2개 선택 완료
        if (count == N / 2) {

            // 출발점 좌표합
            long startX = totalX - sumX;
            long startY = totalY - sumY;

            // 도착점 - 출발점 = 최종 이동 벡터
            long vx = sumX - startX;
            long vy = sumY - startY;

            // 벡터 크기 계산 (SWEA 정의)
            long value = vx * vx + vy * vy;

            min = Math.min(min, value);
            return;
        }

        // 모든 지렁이를 검사한 경우
        if (idx == N)
            return;

        // 남은 지렁이를 모두 선택해도 N/2를 못 채우는 경우 (가지치기)
        if (count + (N - idx) < N / 2)
            return;

        // 현재 지렁이를 도착점으로 선택
        sumX += cor[idx][0];
        sumY += cor[idx][1];

        dfs(idx + 1, count + 1);

        // 백트래킹: 선택 이전 상태로 복구
        sumX -= cor[idx][0];
        sumY -= cor[idx][1];

        // 현재 지렁이를 도착점으로 선택하지 않음 (출발점)
        dfs(idx + 1, count);
    }
}

//로직 (논리 + 코드 레벨)
/*
구하려는 값은 백터의 합의 최소값이다.
백터를 지렁이 쌍 당 하나씩 나오니 N/2의 갯수만큼 생성된다.
백터의 총 합은 v1 + v2 + v3 + v4 ... vN-1이다.
이때 v는 end - start로 나타낼 수 있다.
특정 start 좌표 그룹과 end 좌표 그룹을 계산할 때, 하나의 백터를 구성하는
end-start 좌표를 각각 특정하지 않아도 되는 이유는 어차피 연산으로 구한 백터를 모두 더할 것이기에
(end1 - start2) + (end2 - start1)을 하든 (end2 - start1) + (end1 - start2)를 하든
연산의 차이가 발생하지 않는다.
이러한 특징으로 한 발짝 더 나가면 (end1 + end2) - (start1 + start2)연산 식을 세우는 것도 가능하다.

그렇기에 전체 좌표의 총합을 구하고, dfs로 start좌표의 총합을 구함 
-> 전체 총합 - start총합으로 end 총합 구함 -> end 총합 - start 총합으로 백터의 합 구함
-> 구한 백터의 합으로 백터의 크기 구함, 이 가능하다.

----- gpt로 다듬음


/*
[핵심 목표]
N마리의 지렁이를 N/2개의 쌍으로 연결했을 때,
생성되는 모든 벡터의 합의 크기(제곱)를 최소화한다.

[1. 벡터의 합 계산]
- 두 지렁이를 연결하면 하나의 벡터가 생성된다.
- 벡터 = 도착점 좌표 - 출발점 좌표
- 총 N/2개의 벡터가 생성되며, 이 벡터들을 모두 더해야 한다.

예를 들어 4마리의 지렁이가 있다면
V1 = end1 - start1
V2 = end2 - start2

V1 + V2
= (end1 - start1) + (end2 - start2)
= (end1 + end2) - (start1 + start2)

즉, 각 지렁이를 어떤 지렁이와 연결하는지는 중요하지 않다.
도착점 그룹과 출발점 그룹만 정하면 모든 벡터의 합을 계산할 수 있다.

[2. 전체 좌표합 활용]
모든 지렁이는 출발점 또는 도착점 중 하나에 속한다.

전체 좌표합 = 출발점 좌표합 + 도착점 좌표합

따라서 도착점 N/2개를 선택해 좌표합을 구하면
나머지 출발점의 좌표합도 계산할 수 있다.

출발점 좌표합 = 전체 좌표합 - 도착점 좌표합

최종 벡터의 합 = 도착점 좌표합 - 출발점 좌표합

[3. DFS + 백트래킹]
- DFS로 N마리 중 N/2마리를 도착점으로 선택한다.
- 선택한 지렁이의 좌표를 sumX, sumY에 누적한다.
- N/2마리를 선택하면 다음 순서로 계산한다.

  ① 도착점 좌표합 = (sumX, sumY)
  ② 출발점 좌표합 = 전체 좌표합 - 도착점 좌표합
  ③ 최종 벡터 = 도착점 좌표합 - 출발점 좌표합
  ④ 벡터 크기의 제곱 = vx * vx + vy * vy
  ⑤ 최솟값 갱신

- 백트래킹으로 선택을 되돌리며 모든 조합을 탐색한다.

[핵심 정리]
지렁이 쌍을 직접 구성할 필요 없이,
N/2마리를 도착점으로 선택하는 조합만 탐색하면 된다.

최종 벡터 = 2 * 도착점 좌표합 - 전체 좌표합
*/

*/



//배운 것
/*

*/

//input
/*

(output)

*/

// 다른 사람 코드
/*

*/

