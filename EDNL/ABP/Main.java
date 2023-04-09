public class Main {
    public static void main(String[] args) {
        ArvoreBinariaDePesquisa arvore = new ArvoreBinariaDePesquisa();

        arvore.adicionar(5);
        arvore.adicionar(8);
        arvore.adicionar(3);
        arvore.adicionar(12);
        arvore.adicionar(4);
        arvore.adicionar(6);
        arvore.imprimir();
        System.out.println("===========================");
        System.out.println("===========================");
        arvore.remover(6);
        arvore.imprimir();
    }
}
