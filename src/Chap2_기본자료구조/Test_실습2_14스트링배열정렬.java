package Chap2_기본자료구조;

public class Test_실습2_14스트링배열정렬 {

	public static void main(String[] args) {
		String []data = {"apple","grape","persimmon", "pear","blueberry", "strawberry", "melon", "oriental melon"};

		showData(data);
		sortData(data);
		showData(data);
	}
	static void showData(String[]arr) {
		for(String s:arr) {
			System.out.print(s+" ");
		}
		System.out.println();
	}

	static void swap(String[]arr, int ind1, int ind2) {
		String tmp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = tmp;
	}
	static void sortData(String []arr) {
		for(int i = 0;i<arr.length-1;i++) {
			for(int j=i+1;j<arr.length;j++) {
				if(arr[i].compareTo(arr[j])>0)
					swap(arr,i,j);
			}
		}
	}

}
