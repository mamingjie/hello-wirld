package com.ly.test.helloWorld;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.close();
		
		int total = 0;
		for (int i=0; i*i <= n; i++) {
			int squqre = n - i * i;
			Double root = Math.sqrt(squqre);
			if (root.intValue() == root) {
				if (i == 0 || root == 0) {
					total += 2;
				} else {
					total += 4;
				}
			}
		}
		System.out.println(total);
	}
}
