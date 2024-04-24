package Chap11_그래프;

/*
 Graph Representation
 Adjacency Lists + BFS + DFS
*/

import java.util.Scanner;

class ListNode {
	int data;
	ListNode link;

	public ListNode(int data) {
		this.data = data;
		link = null;
	}
}

class List {
	public List() {
		first = null;
	}
	ListNode first;
	void Insert(int k) {//같은 값을 체크하지 않아 중복 입력됨
		// 구현할 부분
		ListNode newNode = new ListNode(k);
		ListNode p = first;
		if(p==null) {
			first = newNode;
			return;
		}
		
		while(p.link!=null) {
			p=p.link;
		}
		p.link=newNode;
		
	}
	void Delete(int k) {
		// 구현할 부분
		ListNode p = first;
		if(p==null) return;
		if(p.data==k) {
			first = p.link;
			return;
		}
		
		while(p!=null) {
			if(p.link.data==k) {
				p.link=p.link.link;
				return;
			}
		}
		
	}
}

class ListIterator {

	private List list;
	private ListNode current;

	public ListIterator(List l) {
		list = l;
		current = list.first;
	}

	int First() {
		if (current != null)
			return current.data;
		else
			return 0;
	}

	int Next() {
		int data = current.data;
		current = current.link;
		return data;
	}

	boolean NotNull() {
		if (current != null)
			return true;
		else
			return false;
	}

	boolean NextNotNull() {
		if (current.link != null)
			return true;
		else
			return false;
	}


}

class QueueNode {
	int data;
	QueueNode link;

	QueueNode(int data, QueueNode link) {
		this.data = data;
		this.link = link;
	}
}

class Queue {
	private QueueNode front, rear;

	void QueueEmpty() {
		front = rear = null;
	}

	public Queue() {
		QueueEmpty();
	}

	boolean IsEmpty() {
		if (front == null)
			return true;
		else
			return false;
	}

	void Insert(int y) {
		// 구현할 부분
		QueueNode newNode = new QueueNode(y,rear);
		if(front==null) {
			front = newNode;
			return;
		}
		QueueNode p = front;
		while(p.link!=rear) {
			p=p.link;
		}
		p.link=newNode;
	}

	int Delete() {
	// 구현할 부분
		int n = front.data;
		front = front.link;
		return n;
	}
}
class StackNode {
	ListNode data;
	StackNode link;

	StackNode(ListNode data, StackNode link) {
		this.data = data;
		this.link = link;
	}
}
class Stack {
	private StackNode top;

	void StackEmpty() {
		top = null;
	}

	public Stack() {
		StackEmpty();
	}

	boolean IsEmpty() {
		if (top == null)
			return true;
		else
			return false;
	}

	void Insert(ListNode y) {
		// 구현할 부분
		if(IsEmpty()) {
			top = new StackNode(y,null);
			return;
		}
		top = new StackNode(y,top);
	}

	ListNode Delete()
	// delete the top node in stack and return its data
	{
		// 구현할 부분
		ListNode n = top.data;
		top = top.link;
		return n;
	}
}


class Graph {
	private List[] HeadNodes;
	int n;
	boolean[] visited;

	public Graph(int vertices) {
		n = vertices; 
		HeadNodes = new List[n]; //n개의 리스트
		visited = new boolean[n]; //n개의 요소 방문 여부
		for (int i = 0; i < n; i++) {
			HeadNodes[i] = new List();
			visited[i] = false;
		}
		
	}

	void displayAdjacencyLists() {
		for (int i = 0; i < n; i++) {
			// 구현할 부분
			System.out.print(i+" : ");
			ShowList(HeadNodes[i]);
			System.out.println();
			
		}
		System.out.println();
	}

	void InsertVertex(int start, int end) {
		if (start < 0 || start >= n || end < 0 || end >= n) {
			System.out.println("the start node number is out of bound.");
			return;
		}
		HeadNodes[start].Insert(end);
		// 구현할 부분
	}

	void BFS(int v) {
		boolean[] visited = new boolean[n]; // visited is declared as a Boolean 
		for (int i = 0; i < n; i++)
			visited[i] = false; // initially, no vertices have been visited
		// 구현할 부분
		
		visited[v] = true;
		System.out.print(v+" ");
		Queue q = new Queue();
		q.Insert(v);
		
		while(!q.IsEmpty()) {
			v=q.Delete();
			ListIterator li = new ListIterator(HeadNodes[v]);
			if(!li.NotNull()) continue;
			int w=li.First();
			while(true) {
				if(!visited[w]) {
					q.Insert(w);
					visited[w]=true;
					System.out.println(w+" ");
				}
				if(li.NextNotNull()) {
					w=li.Next();
				}else break;
			}
		}
	}
	void ShowList(List l) {
		ListIterator li = new ListIterator(l);
		// 구현할 부분
		if (!li.NotNull())
			return;
		int w = li.First();
		while (true) {
			
			if (li.NotNull())
				w = li.Next();
			else
				break;
			System.out.print(w+" ");
		}
	}

	// Driver
	void DFS(int v) {
		for (int i = 0; i < n; i++)
			visited[i] = false; // initially, no vertices have been visited

		//_DFS(v); // start search at vertex 0
		_NonRecursiveDFS(v);

	}

	// Workhorse
	void _DFS(int v)
	// visit all previously unvisited vertices that are reachable from vertex v
	{
		visited[v] = true;
		System.out.println(v + ", ");
		ListIterator li = new ListIterator(HeadNodes[v]);
		if (!li.NotNull())
			return;
		int w = li.First();
		while (true) {
			if (!visited[w])
				_DFS(w);
			if (li.NotNull())
				w = li.Next();
			else
				return;
		}
	}
	// Workhorse 2
		void _NonRecursiveDFS(int v)
		// visit all previously unvisited vertices that are reachable from vertex v
		{
			// 구현할 부분
			
		}
}
public class Chap11_test_그래프DFS_BFS {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int select = 10, n, startEdge = -1, endEdge = -1;
		int startBFSNode = 0;// the start node to BFS

		System.out.println("vertex 숫자 입력: ");

		n = sc.nextInt();

		Graph g = new Graph(n);

		while (select != '0') {
			System.out.println("\n명령 선택 1: edge 추가, 2: Adjacency Lists 출력, 3: BFS, 4: DFS, 5: 종료 => ");
			select = sc.nextInt();
			switch (select) {
			case 1:
				
				int [][] input = {{0,1},{0,2},{1,0},{1,3},{1,4},{2,0},{2,5},{2,6},{3,1},{3,7},{4,1},{4,7},{5,2},{5,7},{6,2},{6,7},{7,3},{7,4},{7,5},{7,6}};
				for(int i=0;i<input.length;i++) {
					g.InsertVertex(input[i][0], input[i][1]);
				}
//				System.out.println("edge 추가: from vertext: ");
//				startEdge = sc.nextInt();
//				System.out.println("to vertex: ");
//				endEdge = sc.nextInt();
//				if (startEdge < 0 || startEdge >= n || endEdge < 0 || endEdge >= n) {
//					System.out.println("the input node is out of bound.");
//					break;
//				}
//				// get smallest start node.
//				if (startEdge < startBFSNode)
//					startBFSNode = startEdge;
//				if (endEdge < startBFSNode)
//					startBFSNode = endEdge;
//				g.InsertVertex(startEdge, endEdge);
				break;
			case 2:
				// display
				g.displayAdjacencyLists();
				break;

			case 3:
				System.out.println("Start BFS from node: " + startBFSNode);
				g.BFS(startBFSNode);
				break;
			case 4:
				System.out.println("Start DFS from node: " + startBFSNode);
				g.DFS(startBFSNode);
				break;
			case 5:
				System.exit(0);
				break;
			default:
				System.out.println("WRONG INPUT  " + "\n" + "Re-Enter");
				break;
			}
		}

		return;
	}
}
