package inflean.Greedy;

import java.io.*;
import java.util.*;

public class wrestler_use_comapre {
    static class Body implements Comparable<Body> {
        int h;
        int w;

        @Override
        // 키 순으로 정렬
        public int compareTo(Body o) {
            return this.h - o.h;
        }

        Body (int _h, int _w) {
            h = _h;
            w = _w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        List<Body> arr = new ArrayList<>();
        for (int i = 0; i < N; i ++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            arr.add(new Body(h, w));
        }

        // 내림차순으로 정렬할 땐 인자로 Comparator.reverseOrder()
        arr.sort(Comparator.reverseOrder());

        int max = Integer.MIN_VALUE;
        int count = 0;
        for (Body element : arr) {
            if (element.w > max) {
                max = element.w;
                count ++;
            }
        }
        System.out.println(count);
    }
}
