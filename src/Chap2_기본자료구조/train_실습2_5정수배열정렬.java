package Chap2_기본자료구조;

//교재 67 - 실습 2-5
//2번 실습
import java.util.Random;
public class train_실습2_5정수배열정렬 {

	public static void main(String[] args) {
		int []data = new int[10];
		inputData(data);
		showData(data);
		/*
		sortData(data);
		showData(data);
		*/
		reverse(data);//역순으로 재배치 - 정렬 아님 
		showData(data);

		reverseSort(data);//역순으로 재배치 - 정렬 아님 
		showData(data);
	}
	static void showData(int[]data) {
		for(int n : data)
			System.out.print(n+" ");
		System.out.println();

	}
	static void inputData(int []data) { //난수 생성 데이터 입력
		Random rnd = new Random();
		for(int i = 0;i<data.length;i++)
			data[i] = rnd.nextInt(100);
			
	}
	static void swap(int[]arr, int ind1, int ind2) {//교재 67페이지
		int t=arr[ind1];
		arr[ind1]=arr[ind2];
		arr[ind2]=t;
	}
	static void sortData(int []arr) {
		for(int i = 0;i<arr.length-1;i++) {
			for(int j =i+1;j<arr.length;j++) {
				if(arr[i]>arr[j]) {
					swap(arr,i,j);
				}
			}
		}

	}
	static void reverse(int[] a) {//교재 67페이지
		for(int i=0;i<a.length/2;i++)
			swap(a,i,a.length-1-i);
	}
	static void reverseSort(int []arr) {
		for(int i = 0;i<arr.length-1;i++) {
			for(int j =i+1;j<arr.length;j++) {
				if(arr[i]<arr[j]) {
					swap(arr,i,j);
				}
			}
		}
	}

}
