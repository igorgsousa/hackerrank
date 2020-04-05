package br.com.igorgsousa.main;


import java.util.Scanner;

/**
 * {@link https://www.hackerrank.com/challenges/tree-height-of-a-binary-tree/problem}
 * */

class Node {
    Node left;
    Node right;
    int data;
    
    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
public class Main {

	
	public static int height(Node root) {
      	return calcHeight(root) -1 ;
    }
	
	public static int calcHeight(Node root) {
		
		int ret = 1;
		
		int increment = 0;
		
		if(root.left != null) {
			int height = calcHeight(root.left);
			increment = height;
		}
		
		if(root.right != null) {
			int height = calcHeight(root.right);
			increment = height >= increment ? height : increment;
		}
		
      	return ret + increment;
    }

	public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        int height = height(root);
        System.out.println(height);
    }	
}

