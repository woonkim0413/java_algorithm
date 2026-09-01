package algorithm_study;

import java.util.*;

public class Queue_MyChewTest {
	public static void main(String[] args) {
		
		int N = 20;
		int person = 0;
		Deque<int[]> queue = new ArrayDeque<>();
		
		queue.addLast(new int[] {++person, 1});
		
		while (N > 0) {
			int[] p = queue.pollFirst();
			int availCnt = (N >= p[1]) ? p[1] : N;
			N--;
			
			if (N == 0) { // 줄 수 있는 마이쮸 하나도 없음
				
			} else { // 계속해서 마이쮸 배급
				System.out.println(p[0]+"번이 마이쮸를 "+availCnt+"개수 만큼 가져갑니다. 남은 수 : "+N);
				++p[1];
				queue.addLast(p);
				queue.addLast(new int[] {++person, 1});
			}
		}
		
	}
}

//이해
/*

*/

//로직 (논리 + 코드 레벨)
/*

*/

//배운 것
/*
 1) Deque method 조정
   addFirst, addLast는 에러 발생 시 예외를 터트리고
   offerFist, offerLast는 에러 발생 시 리턴 값으로 확인한다.
   (+ poll 계열도 리턴 값으로 확인한다)
   
   그니까 offer, poll 계열 method를 사용하는 것이 좋다.
*/

//input
/*

(output)

*/

//다른 사람 코드
/*

*/
