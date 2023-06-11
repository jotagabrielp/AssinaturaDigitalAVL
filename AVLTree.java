class AVLTree {
    private AVLNode root;

    AVLTree() {
        root = null;
    }

    private int getHeight(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalanceFactor(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private AVLNode rotateLeft(AVLNode y) {
        AVLNode x = y.right;
        AVLNode T2 = x.left;

        x.left = y;
        y.right = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    private AVLNode rotateRight(AVLNode x) {
        AVLNode y = x.left;
        AVLNode T2 = y.right;

        y.right = x;
        x.left = T2;

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    private AVLNode insertNode(AVLNode node, String data) {
        if (node == null) {
            return new AVLNode(data);
        }

        if (data.compareTo(node.data) < 0) {
            node.left = insertNode(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insertNode(node.right, data);
        } else {
            return node;
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1 && data.compareTo(node.left.data) < 0) {
            return rotateRight(node);
        }

        if (balanceFactor < -1 && data.compareTo(node.right.data) > 0) {
            return rotateLeft(node);
        }

        if (balanceFactor > 1 && data.compareTo(node.left.data) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balanceFactor < -1 && data.compareTo(node.right.data) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    private String calculateRootHash() {
        if (root == null) {
            return null;
        }
        return root.hash;
    }

    private boolean searchNode(AVLNode node, String data) {
        if (node == null) {
            return false;
        }

        if (data.compareTo(node.data) < 0) {
            return searchNode(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            return searchNode(node.right, data);
        } else {
            return true;
        }
    }

    void insert(String data) {
        root = insertNode(root, data);
    }

    String getRootHash() {
        return calculateRootHash();
    }

    boolean search(String data) {
        return searchNode(root, data);
    }
}