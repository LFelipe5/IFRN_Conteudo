public class Main {
    public static void main(String[] args) {

        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        System.out.println("Buscando o valor 50: " + tree.search(50)); // True
        System.out.println("Buscando o valor 90: " + tree.search(90)); // False

        tree.remove(50);

        System.out.println("Buscando o valor 50 após a remoção: " + tree.search(50)); // False

        tree.preOrderTraverse(null);
    }
}