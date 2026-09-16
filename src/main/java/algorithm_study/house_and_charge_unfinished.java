package algorithm_study;

import java.util.*;
import java.io.*;

public class house_and_charge_unfinished {
	    static List<int[]> house = new ArrayList<>(); // 0번 x, 1번 y, 2번 최소 충전소 거리
	    static List<int[]> charge = new ArrayList<>(); // 0번 x, 1번 y
	    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    static Deque<int[]> queue = new ArrayDeque<>();
	    static boolean[][] visited = new boolean[31][31];
	    static StringTokenizer st;
	    static int min = -1;
	    static int N;
	    static int minChargeCount;

	    // 북, 동, 남, 서
	    static int[] dx = {0, 1, 0, -1};
	    static int[] dy = {-1, 0, 1, 0};

	    public static void main(String args[]) throws Exception {
	         int ST = Integer.parseInt(br.readLine());

	         // test case 순회
	         for (int t = 1; t <= ST; t++) {
	             N = Integer.parseInt(br.readLine()); // 집 갯수

	             queue.clear();
	             house.clear();
	             charge.clear();
	             
	             minChargeCount = 3;

	             // 집 좌표 저장
	             for (int i = 0; i < N; i ++) {
	                 st = new StringTokenizer(br.readLine());

	                 int x = Integer.parseInt(st.nextToken()) + 15;
	                 int y = Integer.parseInt(st.nextToken()) + 15;
	                 int Cdistance = Integer.parseInt(st.nextToken());

	                 house.add(new int[] {x, y, Cdistance});
	             }

	             dfs(0);

	             System.out.println("#"+t+" "+min);
	         }
	    }

	    
	    // 집 순회
	    static void dfs(int houseIndex) {
	    	// 모든 집을 확인한 후에 거리 확인
	        if (houseIndex == N) {
	        	
	        	int chargeCount = charge.size();
	            int curMin = 0;
	            
	            if (chargeCount < minChargeCount) {

	                minChargeCount = chargeCount;
	                min = calculateDistance();
	            }
	            
	            else if (chargeCount == minChargeCount) {
	                min = Math.min(min, calculateDistance());
	            }
	            return;
	        }
	        
	        // 현재 집의 정보 저장
	        int houseX = house.get(houseIndex)[0];
	        int houseY =  house.get(houseIndex)[1];
	        int distance = house.get(houseIndex)[2];

	        
	        // 충전소 설치 안 해도 커버가 되는 경우 체크
	        if (isCoverd(houseX, houseY, distance)) {
	        	dfs(houseIndex + 1);
	        	return ;
	        }
	        
	        // 커버되지 않는 경우에 charge가 두 개라면 가지치기
	        if (charge.size() == 2) {
	            return;
	        }
	               
	        /*
	         * 이미 충전소 1개짜리 정답을 찾았다면
	         * 2개가 되는 경우의 수는 볼 필요 없음
	         */
	        if (minChargeCount == 1 && charge.size() == 1) {
	            return;
	        }
	        
	        // queue while 문
	        int[] list = new int[2];
	        list[0] = houseX;
	        list[1] = houseY;
	        
	        visited[houseY][houseX] = true;
	        queue.addLast(list);
	        
	        // 특정 집에서 charge를 설치하기 위한 while
	        while(!queue.isEmpty()) {
	            list = queue.pollFirst();
	            	                  
	            // charge 설치할 수 있는지 체크 후 다른 집 순회
	            if (canCharge(list)) {
	                charge.add(list);
	                dfs(houseIndex + 1);
	                charge.remove(charge.size() - 1);
	            }
	            

	            for (int i = 0; i < 4; i ++) {
	            	
	                int dx1 = list[0] + dx[i];
	                int dy1 = list[1] + dy[i];
	                
	                int dis1 = (int) Math.abs(dx1 - houseX) + (int) Math.abs(dy1 - houseY);
	                
	                if (dx1 < 31 && dy1 < 31 && dx1 > -1 && dy1 > -1 && dis1 <= distance) {
	                	continue ;
	                }
	                
	                if (visited[dx1][dy1]) {
	                	visited[dx1][dy1] = true;
	                	queue.addLast(new int[] {dx1, dy1});
	                }
	            }
	        }
	    }

	    
		static int calculateDistance() {
			int sum = 0;

            for (int i = 0; i < N; i ++) {
                int Hx = house.get(i)[0];
                int Hy = house.get(i)[1];

                int value = Integer.MAX_VALUE;
                
                for (int j = 0; j < charge.size(); j ++) {
                    int Cx = charge.get(j)[0];
                    int Cy = charge.get(j)[1];
                    int temp = (int) Math.abs(Hx - Cx) + (int) Math.abs(Hy - Cy);
                    if (value > temp)
                        value = temp; 
                }
                sum += value;
            }
            return sum;
		}
	    
		
	    // 해당 집이 이미 충전기로 커버되는 위치에 있는지 체크
	    static boolean isCoverd(int houseX, int houseY, int distance) {
	    	for (int i = 0; i < charge.size(); i ++) {
	    		
	    		int dis = (int) Math.abs(houseX - charge.get(i)[0]) + 
	    				(int) Math.abs(houseY - charge.get(i)[1]);
	    		
	    		if (dis <= distance) {
	    			return true;
	    		}
	    	}
	    	return false;
	    }
	    

	    // 해당 위치에 charge를 설치할 수 있는지
	    static boolean canCharge(int[] list) {
	        for (int i = 0; i < N; i ++) {
	            if (list[0] == house.get(i)[0] && list[1] == house.get(i)[1])
	                return false;
	        }

	        for (int i = 0; i < charge.size(); i ++) {
	            if (list[0] == charge.get(i)[0] && list[1] == charge.get(i)[1])
	                return false;
	        }
	        return true;
	    }
}

/*
2차원 map이 주어지며 (-15 ~ 15) map에는 주민의 집이 위치한다 (2~20)
마을 주민들은 일정 거리 이내에 전기 충전소가 있어야만 전기차 사용함
주민 모두가 전기차를 사용할 수 있도록 최대 2개까지 충전소를 짓는 코드를 작성하라

 충전소와 N개의 집 사이 거리의 합이 최소가 되는 값을 출력
 만약 충전소를 두 개 지어도 충전을 할 수 없는 집이 있다면 -1을 출력한다
 
 코드
 map은 음수 index를 사용할 수 없으니
 모든 좌표에  + 15를 하여 계산한다 (상대적인 위치니 상관 없음)
 map에서 그냥 공간은 0, 집은1, 충전소는 2로 저장한다.
 이때 집의 좌표도 list<int[]>를 사용해서 저장한다.
 
 집을 기준으로 dfs를 돌려야 할 것 같다.
 1번 집에서 충전소를 저장할 수 있는 좌표를 순회하는 방법은 stack while문으로 돌려서 순회하자.
 (충전소의 좌표도 집 좌표처럼 저장해야 할 듯)
 
 dfs로 2번 집을 탐색할 땐 충전소 좌표 리스트를 순회하며 거리가 허용 범위 이내이면 넘어가고 아니라면 여기서도 stack while문으로 돌려서 순회
 이때 충전소 list 크기가 3이상이며 최소 거리에 저장된 값이 없다면 -1을 저장한다.
 
 모든 집을 돈 다음엔 거리를 한 번 더 계산하여 모든 충전소가 배치되었을 때 최소 거리를 구한다.
 
 충전소를 설치할 땐 현재 위치에 이미 충전소나 집이 있는지 확인하여야 한다.

 
//로직 (논리 + 코드 레벨)
/*
 
*/

//배운 것
/*
 1) 반복문 시간복잡도
 for문 1억번 까지는 단순 연산할 때는 괜찮다.
 
 2) 재귀 dfs + queue bfs일 때 자료구조 사용 범위
 현재 코드는 특정 house에서 charge를 설치할 수 있는 위치를 찾는 bfs에서
 queue 및 visited을 static으로 관리하고 있다.
 그러나 이렇게 코드를 짜면 house 별 charge를 찾는 dfs에서 bfs 자료구조를 전역적으로
 사용하게 되어서 상태 값이 겹쳐서 오류가 발생한다.
 그러므로 queue 및 visited는 dfs method에서 지역변수로 생성하여 사용해야 한다.
 -> dfs의 각 단계에서 bfs를 사용하는 경우 서로의 상태가 얽히지 않도록 특히 신경써야 한다
 
 3) bfs 사용 판단 방법
 집 근처에서 charge를 설치할 수 있는 조건은 집이 가지고 있는 distance(전기차 운용 가능 범위)
 보다 멘허튼 거리가 |houseX - x1| + |houseY - y1|보다 같거나 작어야 한다는 것이다.
 즉, 탐색 작업 없이 집과 charge후보 좌표만 있다면 실제 후보 좌표에 charge를 설치할 수 있는지에
 대한 유무를 판단할 수 있다.
 이때는 bfs를 사용할 필요 없이 for문만 돌려도 됐다. 
 (맵 크기는 31x31, 약 900칸으로 for문을 돌려도 성능적 문제가 전혀 발생하지 않는다)
 
 bfs를 사용할지 말지에 대한 판단 기준은 1) 가장 가까운 거리가 필요한가? 2) 맵에 장애물/특정 경로 의존
 등의 조건이 있어서 멘허튼 거리 식으로 한 번에 계산할 수 없고 탐색을 해야만 하는가? 이다.
 둘 다 YES라면 bfs를 사용하는 것이 맞다.
 
 4) 변수명 실수, 조건 실수
 가장 근본적인 원인은 문제 구현을 할 때 적절하지 않은 알고리즘을 사용하여
 문제의 복잡도가 크게 증가하여 사용 변수 및 로직이 많아졌고, 그래서 주의력이 떨어졌다는 것이다.
 즉 구현 레벨에서 적절한 알고리즘을 선택할 줄 아는 능력을 키우는 것이 변수명 및 조건 실수를 줄일 수 있다.
 
 추가적으로 문제에서 사용하는 조건문이 많아지는 경우엔 조건식 위에 주석으로 조건문의 의미를 
 적어놓는 습관을 들이는 것이다.
 이러면 틀리는 빈도도 줄어들고 틀리더라도 빠르게 디버깅을 할 수 있게 된다.
 
 5) 단위별 디버깅 하는 법
 문제가 생겼을 때 상황에 따라 함수 단위별 디버깅을 해야할 경우도 생기는 것 같다.
 아래 코드처럼 단위 test 코드 + 결과를 씀으로써 함수 단위로 디버깅을 할 수 있다.
 - getDistance 검사
 System.out.println(getDistance(13, 15, 14, 15)); // 예상: 1
 System.out.println(getDistance(13, 15, 16, 18)); // 예상: 6
 
 - isCoverd 검사
 charge.add(new int[]{15, 15});
 System.out.println(isCoverd(16, 15, 1)); // 예상 true
 System.out.println(isCoverd(17, 15, 1)); // 예상 false
 
 
*/

//input
/*
2
2
-2 0 1
1 3 2
2
-1 -1 1
1 0 2
(output)

*/

// 다른 사람 코드
/*

 
 -------------------------------- 문제 ------------------------------------------
제한 조건

실행시간 : 50개의 테스트 케이스를 합쳐서 C/C++/JAVA의 경우 3초, 파이썬 10초

 

메 모 리 : Heap, Global, Stack 등을 모두 합해 최대 256MB까지 사용 가능 (단, 스택은 최대 1MB까지 사용 가능)

제출 횟수 제한

무제한

채점

답안을 제출하면 결과를 판정해서 실시간으로 알려주며 그 의미는 다음과 같다.

 

Accpet : Sample input에 대해 제출 완료

 

Fail : 오답 또는 부분점수 획득

평가

sample_input은 편의를 위해 제공하며, 실제 채점은 별도의 평가용 input으로 이루어진다.

 

제한 시간 이내에 평가용 input의 모든 테스트 케이스에 대해 정답을 도출하면 Pass 이며,

 

그 외에 오답, 부분 정답, 시간 제한을 초과하는 경우 1회 Fail이 된다.

마을 사무소에서는 마을의 공해를 줄이기 위해 주민들에게 한 집당 한 대씩 전기자동차를 보급하고, 전기자동차 충전소를 만들려고 한다.

마을에는 N개의 집이 있으며 ( 2 ≤ N ≤ 20 ),
마을의 전체 좌표는 아래 [ 그림 1 ]과 같이 X축과 Y축 방향 모두 -15 에서 15 까지이다. 
이 안에, 각각 집의 위치는 ( x , y )로 주어진다. ( -15 ≤ x, y ≤ 15 )

                          [ 그림 1 ]
마을 주민들은 집으로부터 일정 거리 이내에 충전소가 있을 경우에만 전기 자동차를 사용한다고 한다.
그래서 마을 사무소에선 주민들의 집과 충전소 간 거리를 감안하여, 충전소 위치를 정하여 만들려고 한다.

집과 충전소 간 거리를 구하는 방식은 다음과 같다.
예를 들어, 집의 위치가 (XH, YH) 이고 충전소의 위치가 (XC, YC) 이면, 집과 충전소 간 거리는
|XH – XC| + |YH – YC|
가 된다.

마을 내 모든 집에 전기 자동차가 보급될 수 있도록 충전소의 위치를 선정할 때,
▶ 충전소와 N개의 집 사이 거리의 합이 최소가 되는 값을 출력하는 프로그램을 작성하라.

단!

①  충전소는 최소한의 개수만 지어야 하며, 최대 2개까지 지을 수 있다.
     만약 충전소를 1개만 지어도, 마을 내 모든 집에 전기 자동차를 보급할 수 있다면, 충전소는 반드시 1개만 지어야 한다.

②  충전소를 2개 지을 경우, 각 집과 충전소간 거리는 가까운 충전소를 기준으로 한다.
     예를 들어 아래 [그림 2]와 같이 2 개의 집과 2 개의 충전소가 존재할 경우,
      A집과 충전소간 거리는 3 이 되고, B집과 충전소간 거리는 4 가 된다.

                                               [ 그림 2 ]

③ 만약 충전소를 2 개 지어도, 전기 자동차를 구입할 수 없는 집이 있을 경우에는, -1 를 출력한다.

④ 집이 있는 위치에는 충전소를 지을 수 없다.

아래의 [ 그림 3-1 ] 과 같이 A집( -2, 0 )과 B집( 1, 3 )이 있다고 가정하자.
A집에서 충전소까지 허용 가능한 최대 거리는 1 이고, B집에서 충전소까지 허용 가능한 최대 거리는 2 이다.

                                         [ 그림 3-1 ]
 
이와 같은 경우는 [ 그림 3-2 ]와 같이, 반드시 2개의 충전소가 필요하며, 이 때 충전소와 각 집 사이의 거리 합의 최소값은 2가 된다.

                                         [ 그림 3-2 ]

 
아래 [ 그림 4 ] 는 또 다른 예이다.
A집의 위치는 ( -1, -1 ) 이며 충전소까지 허용 가능한 최대 거리는 1 이고, B집의 위치는 ( 1, 0 )이며 충전소까지 허용 가능한 최대 거리는 2 이다.
이 경우 1 개의 충전소 건설( 붉은 색 원표시 부분 ) 만으로 충분하므로, 정답은 3 이 된다.

                                      [ 그림 4 ]                                            



 
아래 [ 그림 5 ]와 같은 경우, 2개의 충전소 건설로도, 전기 자동차를 보급 할 수 없는 집이 생길 경우는 -1이 정답이 된다.

                         [ 그림 5 ]
[제약 사항]

집의 개수 N은, 2 ≤ N ≤ 20 범위의 정수이다.
집의 좌표 (x, y)는, -15 ≤ x, y ≤ 15 범위의 정수이다.
각 집에서 충전소까지의 허용 가능한 거리 d는, 1 ≤ d ≤ 30 범위의 정수이다.
[입력]
첫 줄에는 테스트 케이스의 개수 T가 주어진다.
그 다음 줄부터 각 테스트 케이스가 주어지며, 각 테스트 케이스의 첫째 줄에는 N이 오고,
다음 N줄에는 집의 위치 x, y 그리고 각 집에서 충전소까지 허용 가능한 거리 d가 한 칸씩 공백을 두고 주어진다.

[출력]
테스트 케이스의 결과는 "#C"를 찍고 한 칸 띄고 정답을 출력한다.
(단, C는 테스트 케이스의 번호를 의미하며 1 부터 시작한다.)

[입출력 예]

*/
