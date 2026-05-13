package inflean.algorithm_string;

import java.util.Scanner;

public class find_char {
    public static void main(String[] args) {
        int find_count = 0;
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String find_string = sc.nextLine();
        s = s.toUpperCase();
        find_string = find_string.toUpperCase();
        int find_index = 0;
        while ((find_index = s.indexOf(find_string, find_index)) != -1) {
            find_count++;
            find_index = find_index + find_string.length();
        }
        System.out.println(find_count);
    }
}
