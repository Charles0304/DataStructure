package Chap2_기본자료구조;

import java.util.Arrays;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
class PhyscData implements Comparable<PhyscData>{
	String name;
	int height;
	double vision;

	public PhyscData(String name, int height, double vision) {
		this.name=name;
		this.height = height;
		this.vision = vision;
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return String.format("name : %s, height : %d, vision : %.1f", name, height, vision);

	}
	@Override
	public int compareTo(PhyscData p) {
		//return this.height-p.height; //키순
		if(this.name.compareTo(p.name)==0) {//이름먼저 비교
			if(this.height==p.height) {//이름이 같으면 키 비교
				if(this.vision==p.vision) { //키도 같으면 시력비교
					return 0;
				}else {
					return this.vision>p.vision?1:-1; //시력 대소비교 리턴 1과 -1바꾸면 역순
				}
			}else {
				return this.height>p.height?1:-1;//키 대소비교 리턴
			}
		}else {
			return this.name.compareTo(p.name);//이름 대소비교 리턴
		}
		
	}
	public int equals(PhyscData p) {
		//if(this.height==p.height) //키
		if(this.name==p.name&&this.height==p.height&&this.vision==p.vision) //이름
			return 1;
		else return 0;
	}
}
public class Test_실습2_14객체배열정렬 {
	static void swap(PhyscData[]arr, int ind1, int ind2) {
		PhyscData tmp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = tmp;
	}
	static void sortData(PhyscData []arr) {
		for(int i=0;i<arr.length-1;i++) {
			for(int j=i+1;j<arr.length;j++) {
				if(arr[i].compareTo(arr[j])<0) swap(arr,i,j);
			}
		}
	}
	public static void main(String[] args) {
		PhyscData[] data = {
				new PhyscData("홍길동", 162, 0.3),
				new PhyscData("홍동", 164, 1.3),
				new PhyscData("홍길", 152, 0.7),
				new PhyscData("김홍길동", 172, 0.3),
				new PhyscData("이길동", 182, 0.6),
				new PhyscData("박길동", 167, 0.2),
				new PhyscData("최길동", 169, 0.5),
				new PhyscData("최길동", 179, 0.5),
				new PhyscData("최길동", 169, 0.7),
				new PhyscData("최길동", 179, 1.5),
				new PhyscData("최길동", 169, 0.4),
		};
		showData(data);
		sortData(data);
		Arrays.sort(data);//comparator가 필요하다 
		showData(data);
	}
	static void showData(PhyscData[]arr) {
		System.out.println("------------------------------");
		for(PhyscData p:arr)
			System.out.println(p);
	}

}
