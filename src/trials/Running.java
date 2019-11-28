package trials;

import java.util.ArrayList;

public class Running {
	public static void main(String[] args) {
	BST<Integer> MyClass = new BST<Integer>();
	MyClass.insert(20);
	MyClass.insert(15);
	MyClass.insert(16);
	MyClass.insert(13);
	MyClass.insert(24);
	MyClass.insert(23);
	System.out.print(MyClass.size + "\n");
	MyClass.reverseKeys(MyClass.root);
	MyClass.inorder(MyClass.root);
	ArrayList<Integer> AStudents =new ArrayList<Integer>();
	MyClass.KeysInRange(MyClass.root, 4, 21, AStudents);
	System.out.println(AStudents);
	}
}
