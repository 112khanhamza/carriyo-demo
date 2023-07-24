package com.hamza;

public class BinaryTree {

    private Node root;

    public void printLeft() {
        printLeft(root);
    }

    private void printLeft(Node node) {
        System.out.println(node.value);

    }

    private static class Node {

        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }
}
