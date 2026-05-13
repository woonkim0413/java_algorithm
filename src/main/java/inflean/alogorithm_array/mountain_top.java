package inflean.alogorithm_array;

import java.util.*;

public class mountain_top {
    private static boolean count_neighbor (List<Integer>[] arr, int i, int j, int N) {
        int count = 0;
        if (i > 0) {
            if (arr[i - 1].get(j) < arr[i].get(j))
                count++;
        } else {
            count++;
        }
        if (i < N - 1) {
            if (arr[i + 1].get(j) < arr[i].get(j))
                count++;
        } else {
            count++;
        }
        if (j > 0) {
            if (arr[i].get(j - 1) < arr[i].get(j))
                count++;
        }  else {
            count++;
        }
        if (j < N - 1) {
            if (arr[i].get(j + 1) < arr[i].get(j))
                count++;
        } else {
            count++;
        }
        if (count == 4)
            return (true);
        else
            return (false);
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int mount_top_number = 0;
        boolean it_top = false;

        int N = Integer.parseInt(kb.nextLine());
        List<Integer>[] arr = new ArrayList[N];
        for (int i = 0; i < N; i ++) {
            arr[i] = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                arr[i].add(Integer.valueOf(kb.next()));
            }
        }

        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < N; j++) {
                it_top = count_neighbor(arr, i, j, N);
                if (it_top)
                    mount_top_number ++;
            }
        }
        System.out.println(mount_top_number);
    }
}