package algorithm_study;

import java.util.*;
import java.io.*;

// 파라미터 서치 (이분 탐색)
public class carver_wood_7730 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] wood;
	
	public static void main(String[] args) throws Exception {
		int CT = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= CT; t ++) {
			
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken()); // 나무 수
			int M = Integer.parseInt(st.nextToken()); // 원하는 원목 길이
			
			wood = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i ++) {
				wood[i] = Integer.parseInt(st.nextToken());
			}
			
			long s = 0;
			long e = 1_000_000_000L - 1; // 10 << 8은 10 x 2^8이다.
			long target = 0;
			long high = 0;
			
			// e와 s의 차이가 1 이하가 될 때 종료
			while (e - s > 1) {
				target = (s + e) / 2;
				high = curve(target, N);
				
				if (high >= M)
					s = target;
				else
					e = target;
			}
			
			System.out.println("#"+t+" "+s);
		}
	}
	static long curve(long target, int N) {
		long high2 = 0;
		
		for (int i = 0; i < N; i ++) {
			high2 += (wood[i] - target > 0) ? wood[i] - target : 0; 
		}
		
		return high2;
	}
}

/*
 * 1) (s + e) / 2 를 사용해서 각 나무를 순회해서 높이 측정
 * 2) M보다 크다면 (s + e) / 2를 e로 사용, M보다 작다면 (s + e) / 2를 s로 사용
 * 3) s와 e가 1 차이나거나 동일해졌을 때 target이 M보다 크면 이 값 사용, M보다 작으면 index 하나 증가시켜서 사용
 *  
 *  
 *  변수의 추상적인 역할을 기억하는 능력 부족함
 *  -> gpt는 내가 구체적인 실행 흐름은 꽤 잘 따라가는데, 중간 변수를 추상적인 역할로 압축해서 
 *  기억하는 단계에서 자주 어려움을 겪는다고 한다.
 *  즉 s,e는 각각 조건을 만족하는 이분탐색 할 때의 오른쪽 높이 값, 조건을 만족하지 못하는 이분탐색 할 때의 왼쪽 값인데
 *  나는 이 의미를 계속 유지하지 못하고 s는 시작 값, e는 끝 값으로 기억하여 의미를 놓치고 식을 쓰다가 흐름을 놓쳐버린다.
 *  -> 인지 훈련
 *  1) 변수 이름을 일부러 의미 있는 이름으로 바꿔보는 것 s -> possible
 *  2) 계속 변수의 추상적 의미를 상기할 것
 *  3) 일상 생활에서도 무언가를 이해하면 반드시 한 문장으로 압축하는 연습 하기
 */
