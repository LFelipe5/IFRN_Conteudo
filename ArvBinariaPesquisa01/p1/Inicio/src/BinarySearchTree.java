public class BinarySearchTree {
    private Node root;

    private class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    // Método de inserção
    public void insert(int data) {
        root = insert(root, data);
    }

    private Node insert(Node node, int data) {
        if (node == null) {
            node = new Node(data);
        } else if (data < node.getData()) {
            node.setLeft(insert(node.getLeft(), data));
        } else if (data > node.getData()) {
            node.setRight(insert(node.getRight(), data));
        }
        return node;
    }

    // Método de remoção
    public void remove(int data) {
        root = remove(root, data);
    }

    private Node remove(Node node, int data) {
        if (node == null) {
            return null;
        }
        if (data == node.getData()) {
            // Verifica se o nó é uma folha ou não tem uma subárvore esquerda,
            // caso positivo, retorna sua subárvore direita.
            if (node.getLeft() == null) {
                return node.getRight();
            }
            // Verifica se o nó não tem uma subárvore direita,
            // caso positivo, retorna sua subárvore esquerda.
            if (node.getRight() == null) {
                return node.getLeft();
            }
            // Se o nó tiver duas subárvores, substitui seu valor pelo do menor nó
            // na subárvore direita e remove esse nó da árvore.
            int smallestValueInRightSubtree = findSmallestValue(node.getRight());
            node.setData(smallestValueInRightSubtree);
            node.setRight(remove(node.getRight(), smallestValueInRightSubtree));
            return node;
        } else if (data < node.getData()) {
            node.setLeft(remove(node.getLeft(), data));
            return node;
        } else {
            node.setRight(remove(node.getRight(), data));
            return node;
        }
    }

    private int findSmallestValue(Node node) {
        return node.getLeft() == null ? node.getData() : findSmallestValue(node.getLeft());
    }

    // Método de busca
    public boolean search(int data) {
        return search(root, data);
    }

    private boolean search(Node node, int data) {
        if (node == null) {
            return false;
        } else if (data == node.getData()) {
            return true;
        } else if (data < node.getData()) {
            return search(node.getLeft(), data);
        } else {
            return search(node.getRight(), data);
        }
    }
    public void preOrderTraverse(Node node) {
        if (node != null) {
            System.out.println(node.getData());
            preOrderTraverse(node.getLeft());
            preOrderTraverse(node.getRight());
        }
    }
}