class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right = null;
    }
}

class BST {
    Node root;

    BST() {
        root = null;
    }

    void insert(int data) {
        root = insertRec(root, data);
    }

    Node insertRec(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data < root.data)
            root.left = insertRec(root.left, data);
        else if (data > root.data)
            root.right = insertRec(root.right, data);

        return root;
    }

    boolean search(int data) {
        return searchRec(root, data) != null;
    }

    Node searchRec(Node root, int data) {
        if (root == null || root.data == data)
            return root;

        if (root.data > data)
            return searchRec(root.left, data);

        return searchRec(root.right, data);
    }

    void delete(int data) {
        root = deleteRec(root, data);
    }

    Node deleteRec(Node root, int data) {
        if (root == null)
            return root;

        if (data < root.data)
            root.left = deleteRec(root.left, data);
        else if (data > root.data)
            root.right = deleteRec(root.right, data);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.data = minValue(root.right);
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    int minValue(Node root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    void inOrder() {
        inOrderRec(root);
        System.out.println();
    }

    void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.data + " ");
            inOrderRec(root.right);
        }
    }

    public static void main(String[] args) {
        BST tree = new BST();

        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        System.out.println("Inorder traversal:");
        tree.inOrder();

        System.out.println("Search 40: " + (tree.search(40) ? "Found" : "Not Found"));
        System.out.println("Search 25: " + (tree.search(25) ? "Found" : "Not Found"));

        tree.delete(20);
        System.out.println("Inorder traversal after deleting 20:");
        tree.inOrder();

        tree.delete(30);
        System.out.println("Inorder traversal after deleting 30:");
        tree.inOrder();

        tree.delete(50);
        System.out.println("Inorder traversal after deleting 50:");
        tree.inOrder();
    }
}
