package Chap6_Sorting;

class Polynomial implements Comparable<Polynomial>{
    double coef;           // 계수
    int    exp;            // 지수
    
	public Polynomial(double coef, int exp) {
		// TODO Auto-generated constructor stub
		this.coef = coef;
		this.exp = exp;
	}
	@Override
	public String toString() {
        return String.format("%.1fx**%d", coef,exp);
    }
	@Override
	public int compareTo(Polynomial o) {
		// TODO Auto-generated method stub
		return this.exp-o.exp;
	}


}
public class Test_다항식merge연산 {

	static void merge(Polynomial[] a, int lefta, int righta, int leftb, int rightb ) {
		Polynomial[] tmp = new Polynomial[a.length];
		int ix = 0;
		int p = lefta;
		int q = leftb;
		while(p<=righta&&q<=rightb) {
			if(a[p].compareTo(a[q])<0) tmp[ix++] = a[q++];
			else if(a[p].compareTo(a[q])>0) tmp[ix++] = a[p++];
			else {
				tmp[ix++] = a[p++];
				tmp[ix++] = a[q++];
			}
		}
		while(p<=righta) tmp[ix++] = a[p++];
		while(q<=rightb) tmp[ix++] = a[q++];
		
		for(int i=0;i<ix;i++)
			a[lefta + i] = tmp[i];
		
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(Polynomial[] a, int left, int right) {
		int mid = (left+right)/2;
		if (left == right) return;
		MergeSort(a, left, mid);
		MergeSort(a, mid+1, right);
		merge(a, left, mid, mid+1, right);
		return;
	}

	public static void main(String[] args) {
		Polynomial[] x = {
		         new Polynomial(1.5, 3),
		         new Polynomial(2.5, 7),
		         new Polynomial(3.3, 2),
		         new Polynomial(4.0, 1),
		         new Polynomial(2.2, 0),
		         new Polynomial(3.1, 4),
		         new Polynomial(3.8, 5),
		     };
		Polynomial[] y = {
		         new Polynomial(1.5, 1),
		         new Polynomial(2.5, 2),
		         new Polynomial(3.3, 3),
		         new Polynomial(4.0, 0),
		         new Polynomial(2.2, 4),
		         new Polynomial(3.1, 5),
		         new Polynomial(3.8, 6),
		     };
		int nx = x.length;

		System.out.print("X = ");
		ShowPolynomial(x);
		System.out.print("Y = ");
		ShowPolynomial(y);
		MergeSort(x, 0, x.length - 1); // 배열 x를 퀵정렬
		MergeSort(y, 0, y.length - 1); // 배열 x를 퀵정렬
		System.out.print("X = ");
		ShowPolynomial(x);
		System.out.print("Y = ");
		ShowPolynomial(y);
		Polynomial[] z = new Polynomial[20];
		AddPolynomial(x,y,z);//다항식 덧셈 z = x + y
		System.out.print("Z = ");
		ShowPolynomial(z);
		z = new Polynomial[20];
		MultiplyPolynomial(x,y,z);//다항식 곱셈 z = x * y
		System.out.print("Z = ");
		ShowPolynomial(z);
		int result = EvaluatePolynomial(z, 10);//다항식 값 계산 함수 z(10) 값 계산한다 
		System.out.println(" result = " + result );
	}

	private static int EvaluatePolynomial(Polynomial[] z, int i) {
		
		// TODO Auto-generated method stub
		return 0;
	}

	private static void MultiplyPolynomial(Polynomial[] x, Polynomial[] y, Polynomial[] z) {
		// TODO Auto-generated method stub
		for(int i=0;i<x.length;i++) {
			for(int j=0;j<y.length;j++) {
				Polynomial[] add = {new Polynomial(x[i].coef*y[j].coef, x[i].exp+y[j].exp)};
				AddPolynomial(z, add, z);
			}
		}
		
	}

	private static void AddPolynomial(Polynomial[] x, Polynomial[] y, Polynomial[] z) {
		// TODO Auto-generated method stub
		int ix=0;
		int iy=0;
		int idx=0;
		int xlen = 0;
		for(int i=0;i<x.length;i++)
			if(x[ix]==null) {
				xlen = i-1;
				break;
			}
		xlen = xlen<x.length?xlen:x.length;
		while(ix<x.length&&iy<y.length) {
			if(x[ix]==null) break;
			if(x[ix].exp>y[iy].exp) {//x의 차수가 더 크면
				z[idx++]=new Polynomial(x[ix].coef, x[ix].exp);
				ix++;
			}else if(x[ix].exp<y[iy].exp) {//y의 차수가 더 크면
				z[idx++]=new Polynomial(y[iy].coef, y[iy].exp);
				iy++;
			}else {//차수가 같을때
				z[idx++]=new Polynomial(y[iy].coef+x[ix].coef, x[ix].exp);
				ix++;
				iy++;
			}
			
		}
		//System.out.println(ix+" "+iy);
		while(ix<xlen) {
			z[idx++]=x[ix++];
		}
		while(iy<y.length) {
			z[idx++]=y[iy++];
		}
		
	}

	private static void ShowPolynomial(Polynomial[] x) {
		// TODO Auto-generated method stub
		for(Polynomial p:x)
			if(p!=null)
				System.out.print(p+" ");
		System.out.println();
	}
}
