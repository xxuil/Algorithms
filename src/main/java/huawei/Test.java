package main.java.huawei;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.nextLine().substring(2);
            System.out.println(Integer.parseInt(s, 16));
        }
    }
}
