
package Chap6_Sorting;
/*
 * 6장 구현 실습과제1 
 */
class PhyscData implements Comparable<PhyscData>{
	String name;              // 이름
    int    height;            // 키
    double vision;            // 시력

    public PhyscData(String name, int height, double vision) {
		// TODO Auto-generated constructor stub
    	this.name = name;
    	this.height = height;
    	this.vision = vision;
	}
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return String.format("name : %s height : %d vision : %f", name,height,vision);
    }
	@Override
	public int compareTo(PhyscData o) {
		// TODO Auto-generated method stub
		if(this.name==o.name) {
			if(this.height==o.height) {
				if(this.vision==o.vision) {
					return 0;
				}else {
					return (int) (this.vision-o.vision);
				}
			}else {
				return this.height-o.height;
			}
		}else {
			return this.name.compareTo(o.name);
		}
	}

}
public class Test_객체merge정렬 {
	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void merge(PhyscData[] a, int lefta, int righta, int leftb, int rightb ) {
		PhyscData tmp[] = new PhyscData[a.length];
		int ix = 0;
		int p=lefta;
		int q=leftb;
		while(p<=righta && q<=rightb) {
			System.out.println(a[p]+" "+p+" "+q);
			if(a[p].compareTo(a[q])>0) tmp[ix++] = a[q++];
			else if(a[p].compareTo(a[q])<0) tmp[ix++] = a[p++];
			else {
				tmp[ix++]=a[p++]; tmp[ix++]=a[q++];
			}
		}
		while(p<=righta) tmp[ix++]=a[p++];
		while(q<=rightb) tmp[ix++]=a[q++];
		
		for(int i=0;i<ix;i++) {
			a[lefta+i]=tmp[i];
		}

	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(PhyscData[] a, int left, int right) {
		int mid = (left+right)/2;
		if (left == right) return;
		MergeSort(a, left, mid);
		MergeSort(a, mid+1, right);
		merge(a, left, mid, mid+1, right);
		return;
	}

	public static void main(String[] args) {
		PhyscData[] x = {
		         new PhyscData("강민하", 162, 0.3),
		         new PhyscData("김찬우", 173, 0.7),
		         new PhyscData("박준서", 171, 2.0),
		         new PhyscData("유서범", 171, 1.5),
		         new PhyscData("이수연", 168, 0.4),
		         new PhyscData("장경오", 171, 1.2),
		         new PhyscData("황지안", 169, 0.8),
		     };
		int nx = x.length;

		   MergeSort(x, 0, nx - 1); // 배열 x를 퀵정렬
			System.out.println("오름차순으로 정렬했습니다.");
		     System.out.println("■ 신체검사 리스트 ■");
		     System.out.println(" 이름     키  시력");
		     System.out.println("------------------");
		     for (int i = 0; i < x.length; i++)
		         System.out.printf("%-8s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);
	}
}
