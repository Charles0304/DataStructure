package Chap3_검색;

//중복이 없는 리스트를 merge하는 버젼

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Test_중복없는리스트합병 {
//string 정렬, binary search 구현
//1단계: string, 2단계: string 객체,  Person 객체들의 list\
//file1: 서울,북경,상해,서울,도쿄, 뉴욕,부산
//file2: 런던, 로마,방콕, 도쿄,서울,부산
//file > string split() > 배열 > ArrayList > sort > iterator 사용하여 merge > 중복 제거 > string 배열 > file에 저장

	static ArrayList<String> removeDuplicate(ArrayList<String> al) {
		//구현할 부분 : 리스트에서 중복을 제거한다, 정렬후 호출하는 것을 전제로 구현
		for(int i=0;i<al.size()-1;i++)
			for(int j=al.size()-1;j>=i+1;j--)
				if(al.get(i).equals(al.get(j)))
					al.remove(j);
		
		return al;
	}
	
	static void trimSpace(String[]arr) {
		for(int i=0;i<arr.length;i++) {
				arr[i].trim();
			}
	}
	static void makeList(String[] sarray1, List<String>list1) {
		for(String str:sarray1) {
			list1.add(str);
		}
	}
	
	static List<String> mergeList(List<String> list1, List<String> list2) {
		ArrayList<String> list3 = new ArrayList<>();
		Iterator<String> iter1 = list1.iterator();
		Iterator<String> iter2 = list2.iterator();
		String str1 = iter1.next();
		String str2 = iter2.next();
		while(iter1.hasNext()&&iter2.hasNext()) {
			if(str1.compareTo(str2)>=0) {
				list3.add(str2);
				str2 = iter2.next();
			}
			else {
				list3.add(str1);
				str1=iter1.next();
			}
		}
		iter1.forEachRemaining((str)->list3.add(str));

		iter2.forEachRemaining((str)->list3.add(str));
		/*int i=0;
		int j=0;
		while(true) {
			if(i==list1.size()&&j==list2.size()) break;
			else if(list1.size()==i&&list2.size()!=j) {
				for(int k=j;k<list2.size();k++) {
					list3.add(list2.get(k));
					j++;
				}
			}else if(list1.size()!=i&&list2.size()==j) {
				for(int k=i;k<list1.size();k++) {
					list3.add(list1.get(k));
					i++;
				}
			}
			else if(list1.get(i).compareTo(list2.get(j))>0) {
				list3.add(list2.get(j++));
			}else if(list1.get(i).compareTo(list2.get(j))<=0) {
				list3.add(list1.get(i++));
			}
		}*/
		removeDuplicate(list3);
		return list3;
		
	}
	public static void main(String[] args) {
		try {
			Path input1 = Paths.get("a1.txt");
			byte[] bytes1 = Files.readAllBytes(input1);

			Path input2 = Paths.get("a2.txt");
			byte[] bytes2 = Files.readAllBytes(input2);
			
			String s1 = new String(bytes1);
			String s2 = new String(bytes2);
			System.out.println("입력 스트링: s1 = " + s1);
			System.out.println("입력 스트링: s2 = " + s2);
			String[] sarray1 = s1.split("[,\\s]+");// 자바 regex \n으로 검색
			String[] sarray2 = s2.split("[,\\s]+");//file에서 enter키는 \r\n으로 해야 분리됨
			showData("스트링 배열 sarray1 : ", sarray1);
			showData("스트링 배열 sarray2 : ", sarray2);
			trimSpace(sarray1);
			trimSpace(sarray2);

			showData("trimSpace() 실행후 :스트링 배열 sarray1", sarray1);
			showData("trimSpace() 실행후 :스트링 배열 sarray2", sarray2);
			System.out.println("+++++++\n");
			// file1에서 read하여 list1.add()한다.
			// 배열을 list로 만드는 방법
			// 방법1:
			ArrayList<String> list1 = new ArrayList<>();
			makeList(sarray1, list1);
			showList("리스트1: ", list1);
			// 방법2
			ArrayList<String> list2 = new ArrayList<>(Arrays.asList(sarray2));
			showList("리스트2: ", list2);
			
			System.out.println("+++++++ collection.sort()::");
			Collections.sort(list1);
			showList("정렬후 리스트1: ", list1);

			//Arrays.sort(list2, null);//에러 발생 확인하고 이유는?
			Collections.sort(list2);
			showList("정렬후 리스트2: ", list2);	
	
			//정렬된 list에서 중복 제거 코드
			list1 = removeDuplicate(list1);
			list2 = removeDuplicate(list2);
			showList("중복 제거후 리스트1: ", list1);	
			showList("중복 제거후 리스트2: ", list2);	
	
	
			List<String> list3 = new ArrayList<>();
			
			// 방법3:
			list3 = mergeList(list1, list2);
			showList("merge후 합병리스트: ", list3);	

			// ArrayList를 배열로 전환
			String[] st = list3.toArray(new String[list3.size()]);
			// binary search 구현
			// binSearch(st, st.length, "key");
			// 정렬된 list3을 file에 출력하는 코드 완성
			System.out.println("\n" + "file에 출력:");
			int bufferSize = 10240;
			
			ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
			writeFile(list3, buffer);
			
			FileOutputStream file = new FileOutputStream("c.txt");
			FileChannel channel = file.getChannel();
			channel.write(buffer);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void showList(String string, List<String> list3) {
		// TODO Auto-generated method stub
		System.out.print(string);
		for(String str:list3) {
			System.out.print(str+" ");
		}
		System.out.println();
	}

	private static void showData(String string, String[] sarray1) {
		// TODO Auto-generated method stub
		System.out.print(string);
		for(String str:sarray1) {
			System.out.print(str+" ");
		}
		System.out.println();
		
	}

	static void writeFile(List<String> list3, ByteBuffer buffer) {
		String b = " ";
		for (String sx : list3) {
			System.out.println(" " + sx);
			buffer.put(sx.getBytes());
			buffer.put(b.getBytes());
		}
		buffer.flip();
	}
}
