package Chap3_검색;

/*
 * Comparable 구현
 */
/*
 * 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
 * 함수(메소드) 전체를 작성하는 훈련 
 */
import java.util.Arrays;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
class PhyscData2 implements Comparable<PhyscData2>{
	String name;
	int height;
	double vision;

	public PhyscData2(String string, int i, double d) {
		name = string;
		height = i;
		vision = d;
	}
	@Override
	public String toString() {
		return String.format("name : %s, height : %d, vision : %.1f", name, height, vision);
	}
	@Override
	public int compareTo(PhyscData2 p) {
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
	public int equals(PhyscData2 p) {
		if(this.name==p.name&&this.height==p.height&&this.vision==p.vision) //이름
			return 1;
		else return 0;
	}
}
public class train_실습3_6_1객체배열이진탐색 {


	public static void main(String[] args) {
		PhyscData2[] data = {
				new PhyscData2("홍길동", 162, 0.3),
				new PhyscData2("나동", 164, 1.3),
				new PhyscData2("최길", 152, 0.7),
				new PhyscData2("김홍길동", 172, 0.3),
				new PhyscData2("박동", 182, 0.6),
				new PhyscData2("이동", 167, 0.2),
				new PhyscData2("길동", 167, 0.5),
		};
		showData("정렬전", data);
		sortData(data);
		showData("정렬후", data);
		reverse(data);
		showData("역순 재배치후", data);
		Arrays.sort(data);//사용된다 그 이유는?
		showData("Arrays.sort() 정렬후", data);
		
		PhyscData2 key = new PhyscData2("길동", 167, 0.5);
		int resultIndex = linearSearch(data, key);
		System.out.println("\nlinearSearch(<길동,167,0.5>): result = " + resultIndex);
		
		key = new PhyscData2("박동", 182, 0.6);
		/*
		 * 교재 109~113
		 */
		resultIndex = binarySearch(data, key);//comparable를 사용
		System.out.println("\nbinarySearch(<박동,182,0.6>): result = " + resultIndex);
		key = new PhyscData2("이동", 167, 0.6);
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		resultIndex = Arrays.binarySearch(data, key);//comparable를 사용
		System.out.println("\nArrays.binarySearch(<이동,167,0.6>): result = " + resultIndex);
	}

	private static int linearSearch(PhyscData2[] data, PhyscData2 key) {
		// TODO Auto-generated method stub
		for(int i = 0;i<data.length;i++) {
			if(data[i].equals(key)==1)
				return i;
		}
		return -1;
	}

	private static int binarySearch(PhyscData2[] data, PhyscData2 key) {
		// TODO Auto-generated method stub
		int pl = 0;
		int pr = data.length-1;
		int mid = 0;
		
		while(pl<=pr) {
			mid=pl+pr/2;
			if(data[mid].equals(key)==1)
				return mid;
			else if(data[mid].compareTo(key)>0) {
				pl = mid+1;
			}else {
				pr = mid-1;
			}
		}
		return -1;
	}

	private static void reverse(PhyscData2[] data) {
		// TODO Auto-generated method stub
		for(int i = 0;i<data.length-1;i++) {
			for(int j=i+1;j<data.length;j++) {
				if(data[i].compareTo(data[j])<0) {
					PhyscData2 tmp = data[i];
					data[i] = data[j];
					data[j] = tmp;
				}
			}
		}
	}

	private static void sortData(PhyscData2[] data) {
		// TODO Auto-generated method stub
		for(int i = 0;i<data.length-1;i++) {
			for(int j=i+1;j<data.length;j++) {
				if(data[i].compareTo(data[j])>0) {
					PhyscData2 tmp = data[i];
					data[i] = data[j];
					data[j] = tmp;
				}
			}
		}
		
	}

	private static void showData(String string, PhyscData2[] data) {
		// TODO Auto-generated method stub
		System.out.println(string);
		for(PhyscData2 p:data) {
			System.out.println(String.format("name : %s,height = %d, vision : %.2f", p.name,p.height,p.vision));
		}
	}
	
	

}
