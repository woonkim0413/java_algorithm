package algorithm_study;

public class Millionaire_Projec {
	
}

// 이해
// total / object_count
// 연속된 숫자에 대해 숫자가 낮은 날에는 total에서 값을 뺀 뒤 object_count를 늘려서 물건을 챙기고
// 숫자가 높은 날에는 물건을 팔아서 total을 늘리는 문제이다.
// 연속된 숫자에 대해 total 값이 가장 많이 생성될 수 있는 알고리즘을 작성해야 한다.

// 로직
// 연속된 숫자 n개 중 자가 가장 큰 값이 있는 index가 x1이라고 하면 x1까지 물건을 사고 x1에 물건을 모두 판다.
// 그 다음 숫자 중 가장 큰 값이 x2라면 x2까지 물건을 사고 x2 index에서 물건을 모두 판다.
// 해당 행위를 index가 n이 될 때까지 반복한다
// 모든 숫자가 내림차순인 경우 값은 0이다.
// 

// 코드 레벨 (머릿속으로 코드 레벨 로직 구상하기)
// 



// 읽기
//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//StringTokenizer st = new StringTokenizer(br.readLine());
//
//while (st.hasMoreTokens()) {
//    int num = Integer.parseInt(st.nextToken());
//    System.out.println(num);
//}