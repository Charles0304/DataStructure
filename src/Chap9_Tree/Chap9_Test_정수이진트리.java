package Chap9_Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

class TreeNode5 {
	TreeNode5 LeftChild;
	int data;
	TreeNode5 RightChild;

	public TreeNode5() {
		LeftChild = RightChild = null;
	}

	public TreeNode5(int x) {
		// TODO Auto-generated constructor stub
		data = x;
	}
}

class ObjectStack5 {
	// --- 실행시 예외: 스택이 비어있음 ---//
	// generic class는 Throwable을 상속받을 수 없다 - 지원하지 않는다
	public class EmptyGenericStackException extends Exception {
		private static final long serialVersionUID = 1L;

		public EmptyGenericStackException() {
			super();
		}
	}

	// --- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowGenericStackException extends RuntimeException {
		public OverflowGenericStackException() {
		}
	}

	private List<TreeNode5> data; // list를 사용: 배열은 크기를 2배로 늘리는 작업 필요
	// private List<T> data;
	private int capacity; // 스택의 크기
	private int top; // 스택 포인터

//--- 생성자(constructor) ---//
	public ObjectStack5(int capacity) {
		top = 0;
		this.capacity = capacity;
		// this.data = new T[capacity]; // 스택 본체용 배열을 생성
		try {
			data = new ArrayList<>(capacity);
		} catch (OutOfMemoryError e) {
			capacity = 0;
		}
	}

//--- 스택에 x를 푸시 ---//
	public boolean push(TreeNode5 x) throws OverflowGenericStackException {
		System.out.println("top = " + top + "capacity = " + capacity);
		if (top >= capacity)
			throw new OverflowGenericStackException();
		top++;
		return data.add(x);

	}

//--- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public TreeNode5 pop() throws EmptyGenericStackException {
		if (top < 0)
			throw new EmptyGenericStackException();
		return data.remove(--top);
	}

//--- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public TreeNode5 peek() throws EmptyGenericStackException {
		if (top <= 0)
			throw new EmptyGenericStackException();
		return data.get(top - 1);
	}

//--- 스택을 비움 ---//
	public void clear() {
		top = 0;
	}

//--- 스택에서 x를 찾아 인덱스(없으면 –1)를 반환 ---//
	public int indexOf(TreeNode5 x) {
		for (int i = top - 1; i >= 0; i--) // 꼭대기 쪽부터 선형 검색
			if (data.get(i).equals(x))
				return i; // 검색 성공
		return -1; // 검색 실패
	}

//--- 스택의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

//--- 스택에 쌓여있는 데이터 갯수를 반환 ---//
	public int size() {
		return top;
	}

//--- 스택이 비어있는가? ---//
	public boolean isEmpty() {
		return top <= 0;
	}

//--- 스택이 가득 찼는가? ---//
	public boolean isFull() {
		return top >= capacity;
	}

//--- 스택 안의 모든 데이터를 바닥 → 꼭대기 순서로 출력 ---//
	public void dump() {
		if (top <= 0)
			System.out.println("stack이 비어있습니다.");
		else {
			for (int i = 0; i < top; i++)
				System.out.print(data.get(i) + " ");
			System.out.println();
		}
	}
}

//정수를 저정하는 이진트리 만들기 실습
class ObjectQueue5 {
	private TreeNode5[] que;// 큐는 배열로 구현
	// private List<Integer> que; // 수정본
	private int capacity; // 큐의 크기
	private int front; // 맨 처음 요소 커서
	private int rear; // 맨 끝 요소 커서
	private int num; // 현재 데이터 개수

//--- 실행시 예외: 큐가 비어있음 ---//
	public class EmptyQueueException extends RuntimeException {
		public EmptyQueueException(String msg) {
			super(msg);
		}
	}

//--- 실행시 예외: 큐가 가득 찼음 ---//
	public class OverflowQueueException extends RuntimeException {
		public OverflowQueueException(String msg) {
			super(msg);
		}
	}

//--- 생성자(constructor) ---//
	public ObjectQueue5(int maxlen) {
		num = front = rear = 0;
		capacity = maxlen;
		try {
			que = new TreeNode5[maxlen];
		} catch (OutOfMemoryError e) { // 생성할 수 없음
			capacity = 0;
		}
	}

//--- 큐에 데이터를 인큐 ---//
	public int enque(TreeNode5 x) throws OverflowQueueException {
		if (num >= capacity)
			throw new OverflowQueueException("queue full"); // 큐가 가득 찼음
		que[rear++] = x;
		num++;

		return 1;
	}

//--- 큐에서 데이터를 디큐 ---//
	public TreeNode5 deque() throws EmptyQueueException {
		if (num <= 0)
			throw new EmptyQueueException("queue empty"); // 큐가 비어있음
		TreeNode5 x = que[front++];
		num--;

		return x;
	}

//--- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
	public TreeNode5 peek() throws EmptyQueueException {
		if (num <= 0)
			throw new EmptyQueueException("queue empty"); // 큐가 비어있음
		return que[front];
	}

//--- 큐를 비움 ---//
	public void clear() {
		num = front = rear = 0;
	}

//--- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
	public int indexOf(TreeNode5 x) {
		for (int i = 0; i < num; i++) {
			int idx = (i + front) % capacity;
			if (que[idx].equals(x)) // 검색 성공
				return idx;
		}
		return -1; // 검색 실패
	}

//--- 큐의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

//--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {
		return num;
	}

//--- 큐가 비어있는가? ---//
	public boolean isEmpty() {
		return num <= 0;
	}

//--- 큐가 가득 찼는가? ---//
	public boolean isFull() {
		return num >= capacity;
	}

//--- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
	public void dump() {
		if (num <= 0)
			System.out.println("큐가 비어있습니다.");
		else {
			for (int i = 0; i < num; i++)
				System.out.print(que[((i + front) % capacity)] + " ");
			System.out.println();
		}
	}
}

class Tree5 {
	TreeNode5 root;

	Tree5() {
		root = null;
	}

	TreeNode5 inorderSucc(TreeNode5 current) {
		TreeNode5 temp = current.RightChild;
		if (current.RightChild != null)
			while (temp.LeftChild != null)
				temp = temp.LeftChild;
		System.out.println("inordersucc:: temp.data = " + temp.data);
		return temp;
	}

	boolean isLeafNode(TreeNode5 current) {
		if (current.LeftChild == null && current.RightChild == null)
			return true;
		else
			return false;
	}

	void inorder() {
		inorder(root);
	}

	void preorder() {
		preorder(root);
	}

	void postorder() {
		postorder(root);
	}

	void inorder(TreeNode5 CurrentNode) {
		if (CurrentNode != null) {
			inorder(CurrentNode.LeftChild);
			System.out.print(" " + CurrentNode.data);
			inorder(CurrentNode.RightChild);
		}
	}

	void preorder(TreeNode5 CurrentNode) {
		if (CurrentNode != null) {
			System.out.print(CurrentNode.data + " ");
			preorder(CurrentNode.LeftChild);
			preorder(CurrentNode.RightChild);
		}
	}

	void postorder(TreeNode5 CurrentNode) {
		if (CurrentNode != null) {
			postorder(CurrentNode.LeftChild);
			postorder(CurrentNode.RightChild);
			System.out.print(CurrentNode.data + " ");
		}
	}

	void NonrecInorder()// void Tree5::inorder(TreeNode5 *CurrentNode)와 비교
	// stack을 사용한 inorder 출력
	{
		ObjectStack5 s = new ObjectStack5(20);
		TreeNode5 CurrentNode = root;
		while (true) {
			while (CurrentNode != null) {
				s.push(CurrentNode);
				CurrentNode = CurrentNode.LeftChild;
			}
			if (!s.isEmpty()) {
				try {
					CurrentNode = s.pop();
				} catch (Chap9_Tree.ObjectStack5.EmptyGenericStackException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(" " + CurrentNode.data);
				CurrentNode = CurrentNode.RightChild;
			} else
				break;
		}
	}

	int getHeight(TreeNode5 root) {

		if (root == null) {
			return 0;
		} else {
			int leftHeight = getHeight(root.LeftChild);
			int rightHeight = getHeight(root.RightChild);

			return Math.max(leftHeight, rightHeight) + 1;
		}
	}

	void levelOrder() // level 별로 출력한다. level이 증가하면 다음줄에 출력한다
	// 난이도: 최상급 구현
	{
		ObjectQueue5 q = new ObjectQueue5(500);
		Queue<Integer> que = new LinkedList<>();
		int oldLevel = 0, newLevel = 0;
		que.add(oldLevel + 1);
		q.enque(root);
		TreeNode5 CurrentNode; // = root;
		int height = getHeight(root);
		//System.out.println("height : "+height);
		oldLevel=1;
		int n=(int) Math.pow(2,height-1);
		for(int i=0;i<n-1;i++)
			System.out.print("  ");
		while(!q.isEmpty()) {
			CurrentNode = q.deque();
			newLevel=que.remove();
			if(newLevel!=oldLevel) {//레벨이 바뀌면 다음줄
				System.out.println();
				n=height-newLevel;
				for(int i=0;i<(int) Math.pow(2,n)-1;i++) { //줄바꿈 후 공백
					System.out.print("  ");
				
				}
			}
			
			if(CurrentNode.data==-1)
				System.out.print("  ");
			else
				System.out.print(String.format("%2d", CurrentNode.data));
			n=height-newLevel+1;
			for(int i=0;i<(int) Math.pow(2,n)-1;i++) {// 글자간 공백
				System.out.print("  ");
			}
			oldLevel=newLevel;
			if(oldLevel>height)
				break;
			TreeNode5 lchild=CurrentNode.LeftChild==null?new TreeNode5(-1):CurrentNode.LeftChild;
			TreeNode5 rchild=CurrentNode.RightChild==null?new TreeNode5(-1):CurrentNode.RightChild;
			q.enque(lchild);
			q.enque(rchild);
			
			que.add(newLevel+1);
			que.add(newLevel+1);
			}
		

	}

	boolean insert(int x) {// binary search tree를 만드는 입력 : left subtree < 노드 x < right subtree
		// inorder traversal시에 정렬된 결과가 나와야 한다
		TreeNode5 p = root;
		TreeNode5 q = null;
		TreeNode5 newNode = new TreeNode5(x);
		if (root == null) {
			root = newNode;
			return true;
		}
		while (true) {

			if (p.data > x) {
				if (p.LeftChild == null) {
					p.LeftChild = newNode;
					break;
				}
				p = p.LeftChild;
			} else if (p.data < x) {
				if (p.RightChild == null) {
					p.RightChild = newNode;
					break;
				}
				p = p.RightChild;
			} else {
				return false;
			}

		}

		return true;
	}

	boolean delete(int num) {// binary search tree에서 임의 값을 갖는 노드를 찾아 삭제한다.
		// 삭제 대상이 leaf node인 경우, non-leaf node로 구분하여 구현한다
		TreeNode5 p = root, q = null, parent = null;
		int branchMode = 0; // 1은 left, 2는 right
		if (root == null)
			return false;
		while (p != null) {
			if (p.data == num) {
				break;
			} else if (p.data > num) {
				parent = p;
				p = p.LeftChild;
			} else {
				parent = p;
				p = p.RightChild;
			}
		}
		if (p == null) {
			return false;
		}
		if (isLeafNode(p) == true) {// leag 노드이면
			if (parent.data > p.data)
				parent.LeftChild = null;
			else
				parent.RightChild = null;
			return true;
		} else if (p.RightChild == null) { // 왼쪽 노드만 있을때

			if (parent.data > p.data)
				parent.LeftChild = p.LeftChild;
			else
				parent.RightChild = p.LeftChild;
			p = null;
			return true;

		} else { // 둘다 있을때 문제 : q가 이동하고 원래있던 q가 삭제가 안됨
			q = inorderSucc(p); // q는 p 대체할거

			TreeNode5 qp = p.RightChild;
			if (qp.LeftChild != null)
				while (qp.LeftChild.LeftChild != null)
					qp = qp.LeftChild;

			TreeNode5 tmp = new TreeNode5(q.data);
			tmp.LeftChild = p.LeftChild;
			tmp.RightChild = p.RightChild;// q복사

			if (qp.data > q.data)
				qp.LeftChild = q.RightChild;
			else
				qp.RightChild = q.RightChild;
			if (parent.data > p.data) {
				parent.LeftChild = tmp;
				p = null;
				return true;
			} else {
				parent.RightChild = tmp;
				p = null;
				return true;
			}

		}

		// 삭제할거 p p의 부모는 parent

	}

	boolean search(int num) {// num 값을 binary search tree에서 검색
		TreeNode5 p = root;
		while (p != null) {
			if (p.data == num)
				return true;
			if (p.data > num)
				p = p.LeftChild;
			else
				p = p.RightChild;
		}
		return false;

	}
}

public class Chap9_Test_정수이진트리 {
	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), InorderPrint("정렬출력"), LevelorderPrint("레벨별출력"),
		StackInorderPrint("스택정렬출력"), PreorderPrint("prefix출력"), PostorderPrint("postfix출력"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) { // 생성자(constructor)
			message = string;
		}

		String getMessage() { // 표시할 문자열을 반환
			return message;
		}
	}

	// --- 메뉴 선택 ---//
	static Menu SelectMenu() {
		Scanner stdIn = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values())
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
			System.out.print(" : ");
			key = stdIn.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());

		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Random rand = new Random();
		Scanner stdIn = new Scanner(System.in);
		Tree5 t = new Tree5();
		Menu menu; // 메뉴
		int count = 20;
		int num;
		boolean result;
		do {
			switch (menu = SelectMenu()) {
			case Add: //
				int[] input = new int[count];
				for (int ix = 0; ix < count; ix++) {
					input[ix] = rand.nextInt(50);
				}
				for (int n : input)
					System.out.print(n + " ");
				for (int i = 0; i < count; i++) {
					if (!t.insert(input[i]))
						System.out.println("Insert Duplicated data");
				}
				System.out.println(t.root.data);
				break;

			case Delete: // 임의 정수 삭제
				System.out.println("삭제할 데이터:: ");
				num = stdIn.nextInt();
				if (t.delete(num))
					System.out.println("삭제 데이터 = " + num + " 성공");
				else
					System.out.println("삭제 실패");
				;
				break;

			case Search: // 노드 검색
				System.out.println("검색할 데이터:: ");

				num = stdIn.nextInt();
				result = t.search(num);
				if (result)
					System.out.println(" 데이터 = " + num + "존재합니다.");
				else
					System.out.println("해당 데이터가 없습니다.");
				break;

			case InorderPrint: // 전체 노드를 키값의 오름차순으로 표시
				t.inorder();
				System.out.println();
				// t.NonrecInorder();
				break;
			case LevelorderPrint: //
				t.levelOrder();
				System.out.println();
				// t.NonrecInorder();
				break;
			case StackInorderPrint: // 전체 노드를 키값의 오름차순으로 표시
				t.NonrecInorder();
				break;
			case PreorderPrint:// prefix 출력
				t.preorder();
				System.out.println();
				break;
			case PostorderPrint:// postfix로 출력
				t.postorder();
				System.out.println();
				break;
			}
		} while (menu != Menu.Exit);
	}
}
