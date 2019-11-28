package trials;

import java.util.ArrayList;

public class BST<T extends Comparable<T>> {
         BSTNode<T> root;
         int size;
        /* public void insert(T target) throws IllegalArgumentException{
        	 BSTNode<T> ptr = root, prev = null;
        	 int c = 0;
        	 while(ptr != null) {
        		 c = target.compareTo(ptr.data);
        		 if(c == 0) {
        			 throw new IllegalArgumentException("Duplicate");
        		 }
        		 prev = ptr;
        		 ptr = c < 0 ? ptr.left:ptr.right;
        	 }
        	 BSTNode<T> temp = new BSTNode(target);
        	 size++;
        	 if(root == null) {
        		 root = temp;
        		 return;
        	 }
        	 if(c<0) {
        		 prev.left = temp;
        	 }else {
        		 prev.right = temp;
        	 }
         }
         */
         public void insert(T target) {
        	 root = insert(target, root);
        	 size++;
         }
         public BSTNode<T> insert(T target, BSTNode<T> root){
        	 if(root == null) {
        		 BSTNode<T> temp = new BSTNode(target);
        		 return temp;
        	 }
        	 int c = target.compareTo(root.data);
        	 if(c==0){
        		 throw new IllegalArgumentException("Duplicate");
        	 }
        	 if(c <0) { 
        		 root.left = insert(target,root.left);
        	 }else {
        		 root.right = insert(target,root.right);
        	 }
        	 return root;
         }

         public void inorder(BSTNode<T> root) {
	       if(root == null) {
	       return;
         }
          inorder(root.left);  
          System.out.print(root.data + "\n");  
          inorder(root.right);
          
          return;
         }
         public void reverseKeys(BSTNode<T> root) {
        	 if(root == null) {
        		 return;
        	 }
        	 reverseKeys(root.left);
        	 BSTNode<T> ptr = root.right;
        	 root.right = root.left;
        	 root.left = ptr;
        	 reverseKeys(root.right);
         }
         /*public void KeysInRange(BSTNode<T> root, T min, T max, ArrayList<T> result) {
        	 
        	 if(root == null) {
        		 return;
        	 }
        	 KeysInRange(root.left, min, max , result);
        	 int c1 = min.compareTo(root.data);
        	 int c2 = max.compareTo(root.data);
        	 if(c1 <= 0 && c2 >= 0) {
        		 result.add(root.data);
        	 }
        	 KeysInRange(root.right, min, max , result);
             return;	 
         }
         */
public void KeysInRange(BSTNode<T> root, T min, T max, ArrayList<T> result) {
        	 
        	 if(root == null) {
        		 return;
        	 }
        	 
        	 int c1 = min.compareTo(root.data);
        	 int c2 = max.compareTo(root.data);
        	 if(c1 <= 0 && c2 >= 0) {
        		 result.add(root.data);
        	 }
        	 if(c1 < 0) {
        		 KeysInRange(root.left, min, max , result);
        	 }
        	 if(c2 > 0) {
        	 KeysInRange(root.right, min, max , result);
        	 }
             return;	 
         }

}
