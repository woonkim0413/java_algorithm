package inflean.Greedy;

import java.io.*;
import java.util.*;

public class wrestler {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i ++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int count = 0;
        for (int i = 0; i < N; i ++) {
            int ability1 = arr[i][0];
            int ability2 = arr[i][1];
            int flag = 0;
            for (int j = 0; j < N; j ++) {
                if (ability1 < arr[j][0]) {
                    if (ability2 < arr[j][1]) {
                        flag = 1;
                        break ;
                    }
                }
            }
            if (flag == 0)
                count ++;
        }
        System.out.println(count);
    }
}
