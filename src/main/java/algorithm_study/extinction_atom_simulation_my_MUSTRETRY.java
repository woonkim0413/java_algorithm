package algorithm_study;

import java.io.*;
import java.util.*;

public class extinction_atom_simulation_my_MUSTRETRY {
	
	static Map<Integer, Integer> boomCheck = new HashMap<>();
	static Deque<Atom> atoms = new ArrayDeque<>();
	
	// io
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	// dir vector
	static int[] dx = {0, 0, -1, 1}; 
	static int[] dy = {1, -1, 0, 0}; 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int TC = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= TC; t ++) {
			int N = Integer.parseInt(br.readLine());
			int result = 0;
			
			// 초기화
			atoms.clear();
			boomCheck.clear();
			
			// atom 받기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) << 1;
				int y = Integer.parseInt(st.nextToken()) << 1;
				int dir = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				
				Atom atom = new Atom(x, y, dir, e);
				atoms.addLast(atom);
			}
			
			// 시뮬
			for (int i = 0; i < 4000; i++) {
				
				// 초기화
				boomCheck.clear();
				int size = atoms.size();
				
				// atoms 순회하면서 dir에 따라 이동
				for (int j = 0; j < size; j ++) {
					
					Atom atom = atoms.pollFirst();
					
                    atom.x += dx[atom.dir];
                    atom.y += dy[atom.dir];
                    
					// 범위를 벗어난 경우 제거
					if (atom.x > 2000 || atom.x < -2000 || atom.y > 2000 || atom.y < -2000)
						continue;
					
                    // 해당 좌표의 에너지 총합 기록
                    boomCheck.put(
                            getKey(atom.x, atom.y),
                            boomCheck.getOrDefault(getKey(atom.x, atom.y), 0) + atom.e
                    );

                    // 이동한 원자를 다시 queue에 넣음
                    atoms.addLast(atom);
				}
				
				// 충돌 확인
				size = atoms.size();
				
                for (int j = 0; j < size; j++) {
                    Atom atom = atoms.pollFirst();
                    
                    // 충돌한 경우
                    if (boomCheck.get(getKey(atom.x, atom.y)) != atom.e) {
                        result += atom.e;
                        continue;
                    }

                    // 충돌하지 않았으면 살아남음
                    atoms.addLast(atom);
                }
			}
			System.out.println("#"+t+" "+result);
		}
	}
	
	static class Atom {
		int x;
		int y;
		int dir;
		int e;
		
		public Atom(int x, int y, int dir, int e) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.e = e;
		}
	}
	
	// x,y는 -2000 ~ 2000 범위이다.
	// + 2000을 하면 bit로 0 ~ 12 안에서 표현된다.
	// Integer을 표현하는 bit가 32니 x,y를 각각 16bit로 표현하면 된다.
	static int getKey(int x, int y) {
		return (x + 2000) << 16 | (y + 2000);
	}
}

//로직 (논리 + 코드 레벨)
/*
	1) 양 모서리 끝에서 움직이는 경우가 있으니 2000초까지 for을 돌린다.
	2) 매 초마다 전 맵을 탐색해야 하니 초당 탐색해야 하는 맵의 크기는 2,000 x 2,000 = 400만이다.
	2) 0.5초를 표현하기 위해 x,y 범위를 두 배로 하면 4,000 X 4,000 = 1,600만이다. (64MB)
	2) -> 완탐으로는 풀 수 없는 문제이다.
	2) 매 초마다 원자를 map에 표시한다.
	3) 특정 좌표에 원자가 하나 있으면 1, 두 개 있으면 2다 아무것도 없으면 0이다.
	4) 값이 2인 경우 충돌한 것이니 
*/

//배운 것
/*
	1) HashMap에 class 사용 조건
	선행적으로 equals와 hashCode를 Override 해야한다.
	hashCode는 버킷 index를 만들 때 사용되고 equals는 버킷 안에 들어있는 key들과 넣으려는
	값이 같은지 확인하기 위해서다
	
	추가적으로 hashMap에 넣은 객체들은 값을 변경하면 안된다.
	예를 들어 좌표 객체라면 (3,5)에서 넣고 (4,5)로 변경한 다음 해당 좌표 객체를 사용해서 조회하면
	hashCode가 달라져서 다른 버킷에 접근하게 된다. 
	
	2) 두 값을 조합하여 HashMap Key 만들기
	((Long) x) << 32 | (y & 0xFFFFFFFFL) 
	위처럼 식을 사용하면 x는 상위 bit를, y는 하위 bit를 사용하여 두 수를 보존하고 있는
	Long을 만들 수 있다.
	
	또는 아래처럼 record를 사용해서 객체를 선언해도 된다. (JDK 14부터 지원이라 JDK 8 기준이면 사용 불가)
	record를 사용하면 내부적으로 equals() 및 hashCode()가 arg를 기준으로 생성된다.
	record Key(int x, int y, int dir) {}
	또는 String을 사용해도 내부적으로 equals, hashCode method를 정의하고 있기에 가능한다.
	-> 근데 String을 key로 사용하니 타임아웃 떠서 결국 HashMap key로 사용했다.
	
*/

//input
/*
2
4
-1000 0 3 5
1000 0 2 3
0 1000 1 7
0 -1000 0 9
4
-1 1 3 3
0 1 1 1
0 0 2 2
-1 0 0 9
(output)

*/

//다른 사람 코드
/*

*/