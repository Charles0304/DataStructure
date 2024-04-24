package Chap3_검색;

//comparator 구현 실습
/*
* 교재 121 실습 3-6 
* 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
* 함수(메소드) 전체를 작성하는 훈련 
*/
import java.util.Arrays;
public class train_실습3_6_0스트링배열정렬이진탐색 {


	static void reverse(String[] a) {//교재 67페이지
		for(int i=0;i<a.length-1;i++) {
			for(int j=i+1;j<a.length;j++) {
				if(a[i].compareTo(a[j])<1) {
					String tmp = a[i];
					a[i] = a[j];
					a[j] = tmp;
				}
			}
		}
	}
	public static void main(String[] args) {
		String []data = {"사과","포도","복숭아", "감", "산딸기", "블루베리", "대추", "수박", "참외"};//홍봉희 재배 과수

		showData("정렬전", data);
		sortData(data);
		showData("정렬후", data);
		reverse(data);//역순으로 재배치
		showData("역순 재배치후", data);
		Arrays.sort(data);//Arrays.sort(Object[] a);
		showData("Arrays.sort()로 정렬후",data);
      
		String key = "포도";
		int resultIndex = linearSearch(data, key);
		System.out.println("\nlinearSearch(포도): result = " + resultIndex);

		key = "배";
		/*
		 * 교재 109~113
		 */
		resultIndex = binarySearch(data, key);
		System.out.println("\nbinarySearch(배): result = " + resultIndex);
		key = "산딸기";
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		resultIndex = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(산딸기): result = " + resultIndex);

	}
	private static int binarySearch(String[] data, String key) {
		int pl = 0;
		int pr = data.length-1;
		int mid = 0;
		while(pl<=pr) {
			mid = (pl+pr)/2;
			if(data[mid].equals(key)) return mid;
			else if(data[mid].compareTo(key)<0) pl=mid+1;
			else pr=mid-1;
		}
		return -1;
	}
	private static int linearSearch(String[] data, String key) {
		// TODO Auto-generated method stub
		for(int i=0;i<data.length;i++) {
			if(data[i]==key) {
				return i;
			}
		}
		return -1;
	}
	private static void sortData(String[] data) {
		// TODO Auto-generated method stub
		for(int i=0;i<data.length-1;i++) {
			for(int j=i+1;j<data.length;j++) {
				if(data[i].compareTo(data[j])>1) {
					String tmp = data[i];
					data[i] = data[j];
					data[j] = tmp;
				}
			}
		}
	}
	private static void showData(String string, String[] data) {
		// TODO Auto-generated method stub
		System.out.println(string);
		for(String s:data) {
			System.out.print(s+" ");
		}
	}


}
