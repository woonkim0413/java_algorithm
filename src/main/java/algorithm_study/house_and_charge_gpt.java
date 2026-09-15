package algorithm_study;

import java.util.*;
import java.io.*;

public class house_and_charge_gpt {

    // house[i] = {x, y, 허용 최대 거리}
    static List<int[]> house = new ArrayList<>();
    // charge[i] = {x, y}
    static List<int[]> charge = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;

    static int min;
    static int minChargeCount;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());

            house.clear();
            charge.clear();

            min = Integer.MAX_VALUE;

            // 아직 답을 못 찾았으므로 3으로 설정
            // 실제 충전소는 최대 2개
            minChargeCount = 3;

            for (int i = 0; i < N; i++) {

                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken()) + 15;
                int y = Integer.parseInt(st.nextToken()) + 15;
                int distance = Integer.parseInt(st.nextToken());

                house.add(new int[]{x, y, distance});
            }

            dfs(0);

            if (min == Integer.MAX_VALUE) {
                min = -1;
            }
            System.out.println("#" + t + " " + min);
        }
    }

    static void dfs(int houseIndex) {

        if (houseIndex == N) {
            int chargeCount = charge.size();

            /*
             * 더 적은 충전소를 사용하는 경우 발견
             * 예:
             * 기존에 충전소 2개짜리 답을 찾았는데
             * 나중에 충전소 1개짜리 답을 발견한 경우
             */
            if (chargeCount < minChargeCount) {

                minChargeCount = chargeCount;
                min = calculateDistance();
            }

            /*
             * 충전소 개수가 같은 경우 거리 합이 더 작은 것을 선택
             */
            else if (chargeCount == minChargeCount) {
                min = Math.min(min, calculateDistance());
            }
            return;
        }


        /*
         * 현재 집이 기존 충전소에 의해
         * 이미 충전 가능한지 확인
         */
        if (isCovered(houseIndex)) {
            dfs(houseIndex + 1);
            return;
        }


        /*
         * 현재 집은 기존 충전소로 충전되지 않는데
         * 이미 충전소가 2개 있으므로 실패
         */
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


        int houseX = house.get(houseIndex)[0];
        int houseY = house.get(houseIndex)[1];
        int maxDistance = house.get(houseIndex)[2];


        /*
         * 현재 집에서 maxDistance 이하인
         * 모든 위치에 충전소 설치 시도
         * 맨해튼 거리:
         * |x - houseX| + |y - houseY| <= maxDistance
         */
        for (int x = 0; x < 31; x++) {
            for (int y = 0; y < 31; y++) {
                int distance = Math.abs(houseX - x) + Math.abs(houseY - y);

                /*
                 * 현재 집의 충전 가능 범위를 벗어남
                 */
                if (distance > maxDistance) {
                    continue;
                }

                /*
                 * 해당 위치에 충전소 설치 불가능 (이미 충전소가 있거나 집이 있는 경우)
                 */
                if (!canCharge(x, y)) {
                    continue;
                }


                /*
                 * 충전소 설치
                 */
                charge.add(new int[]{x, y});

                /*
                 * 현재 집은 방금 설치한 충전소로
                 * 충전 가능하므로 다음 집으로 이동
                 */
                dfs(houseIndex + 1);


                /*
                 * backtracking의 상태 복구
                 */
                charge.remove(charge.size() - 1);
            }
        }
    }


    /*
     * 현재 집이 기존 충전소 중 하나에 의해
     * 충전 가능한지 확인
     */
    static boolean isCovered(int houseIndex) {
        int houseX = house.get(houseIndex)[0];
        int houseY = house.get(houseIndex)[1];
        int maxDistance = house.get(houseIndex)[2];

        for (int i = 0; i < charge.size(); i++) {
            int chargeX = charge.get(i)[0];
            int chargeY = charge.get(i)[1];

            int distance = Math.abs(houseX - chargeX) + Math.abs(houseY - chargeY);

            if (distance <= maxDistance) {
                return true;
            }
        }
        return false;
    }


    /*
     * x, y 위치에 충전소를 설치할 수 있는지 확인
     */
    static boolean canCharge(int x, int y) {

        /*
         * 집이 있는 위치에는
         * 충전소 설치 불가
         */
        for (int i = 0; i < N; i++) {
            if (x == house.get(i)[0] && y == house.get(i)[1]) {
                return false;
            }
        }

        /*
         * 이미 충전소가 있는 위치
         */
        for (int i = 0; i < charge.size(); i++) {
            if (x == charge.get(i)[0] && y == charge.get(i)[1]) {
                return false;
            }
        }
        return true;
    }


    /*
     * 현재 설치된 충전소들을 기준으로
     *
     * 각 집에서 가장 가까운 충전소까지의
     * 거리 합 계산
     */
    static int calculateDistance() {

        int sum = 0;

        for (int i = 0; i < N; i++) {

            int houseX = house.get(i)[0];
            int houseY = house.get(i)[1];

            int minDistance = Integer.MAX_VALUE;

            for (int j = 0; j < charge.size(); j++) {

                int chargeX = charge.get(j)[0];
                int chargeY = charge.get(j)[1];

                int distance = Math.abs(houseX - chargeX) + Math.abs(houseY - chargeY);

                minDistance = Math.min(minDistance, distance);
            }
            sum += minDistance;
        }
        return sum;
    }
}
