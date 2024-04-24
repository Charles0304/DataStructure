package Chap5_Recursive;

import java.util.Scanner;

public class Recur {
	static void recur(int n) {
		while(n>0) {
			recur(n-1);
			System.out.println(n);
			n=n-2;
		}
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("정수를 입력하세요.: ");
		int x = stdIn.nextInt();
		
		recur(x);
	}
}
