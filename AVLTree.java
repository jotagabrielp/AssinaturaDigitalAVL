import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

class Node {
    String value;
    int height;
    Node left, right;

    Node(String item) {
        value = item;
        height = 1;
    }
}

class AVLTree {
    Node root;

    int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }
    int max(int a, int b) {
        return Math.max(a, b);
    }
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }
    int getBalance(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }
    Node insert(Node node, String value) {
        if (node == null)
            return (new Node(value));

        if (value.compareTo(node.value) < 0)
            node.left = insert(node.left, value);
        else if (value.compareTo(node.value) > 0)
            node.right = insert(node.right, value);
        else
            return node;

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && value.compareTo(node.left.value) < 0)
            return rightRotate(node);

        if (balance < -1 && value.compareTo(node.right.value) > 0)
            return leftRotate(node);

        if (balance > 1 && value.compareTo(node.left.value) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && value.compareTo(node.right.value) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }


    String generateHash() throws NoSuchAlgorithmException{
        StringBuilder sb = new StringBuilder();
        generateHash(root, sb);
        return sb.toString();
    }

    void generateHash(Node node, StringBuilder sb) throws NoSuchAlgorithmException {
        if (node != null) {
            generateHash(node.left, sb);
                sb.append(Autenticador.sha1(node.value));
            generateHash(node.right, sb);
        }
    }

}