public class Main {
    public static void main(String[] args) {
        ArvoreBinariaDePesquisa arvore = new ArvoreBinariaDePesquisa();

        arvore.adicionar(5);
        arvore.adicionar(2);
        arvore.adicionar(8);
        arvore.adicionar(1);
        arvore.adicionar(7);

        arvore.imprimirEmOrdem();
    }
}