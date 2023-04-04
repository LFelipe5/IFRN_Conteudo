
import java.lang.RuntimeException;
public class ArvoreBinariaDePesquisa {
    No raiz;

    public ArvoreBinariaDePesquisa() {
        this.raiz = null;
    }

    public void adicionar(int valor) {
        this.raiz = adicionarRecursivo(this.raiz, valor);
    }

    private No adicionarRecursivo(No noAtual, int valor) {
        if (noAtual == null) {
            return new No(valor);
        }

        if (valor < noAtual.valor) {
            noAtual.esquerda = adicionarRecursivo(noAtual.esquerda, valor);
        } else if (valor > noAtual.valor) {
            noAtual.direita = adicionarRecursivo(noAtual.direita, valor);
        } else {
            System.out.println("Valor já existe na árvore!");
        }

        return noAtual;
    }

    public void imprimirEmOrdem() {
        imprimirEmOrdemRecursivo(this.raiz);
    }

    private void imprimirEmOrdemRecursivo(No noAtual) {
        if (noAtual != null) {
            imprimirEmOrdemRecursivo(noAtual.esquerda);
            System.out.println(noAtual.valor);
            imprimirEmOrdemRecursivo(noAtual.direita);
        }
    }
    public No pesquisar(int chave) {
        return pesquisarRecursivo(this.raiz, chave);
    }

    private No pesquisarRecursivo(No noAtual, int chave) {
        if (noAtual.isExternal()) {
            return noAtual;
        }

        if (chave < noAtual.valor) {
            return pesquisarRecursivo(noAtual.esquerda, chave);
        } else if (chave == noAtual.valor) {
            return noAtual;
        } else {
            return pesquisarRecursivo(noAtual.direita, chave);
        }
    }

    public int remover(int chave){
        No no = pesquisarRecursivo(this.raiz, chave);
        if (no == null) {
            throw new ChaveNaoExisteException("A chave " + chave + " não existe na árvore!");
        }
        if (isExternal(no)) { // nó folha
            int retorna = no.valor;
            if (isFilhoDireito(no)) {
                no.pai.direita = null;
            } else {
                no.pai.esquerda = null;
            }
            return retorna;
        }
        if (temSoUmFilho(no)) { // somente um filho esq ou dir
            No filho;
            if (temFilhoEsquerdo(no)) {
                filho = no.esquerda;
            } else {
                filho = no.direita;
            }
            if (isFilhoDireito(no)) {
                no.pai.direita = filho;
            } else {
                no.pai.esquerda = filho;
            }
            filho.pai = no.pai;
            return no.valor;
        }
        // dois filhos
        if (temDoisFilhos(no)){
            int retorna = no.valor;
            No sucessor = sucessor(no.getDireita());
        }

    }

    private boolean isExternal(No no) {
        return no.esquerda == null && no.direita == null;
    }

    private boolean isFilhoDireito(No no) {
        return no == no.pai.direita;
    }

    private boolean temSoUmFilho(No no) {
        return (no.esquerda == null && no.direita != null) || (no.esquerda != null && no.direita == null);
    }

    private boolean temFilhoEsquerdo(No no) {
        return no.esquerda != null;
    }

    private No sucessor(No no) {
        if (no.esquerda != null) {
            return sucessor(no.esquerda);
        } else {
            return no;
        }
    }

    public void imprimirPreOrdem() {
        imprimirPreOrdemRecursivo(this.raiz);
    }

    private void imprimirPreOrdemRecursivo(No noAtual) {
        if (noAtual != null) {
            System.out.println(noAtual.valor);
            imprimirPreOrdemRecursivo(noAtual.esquerda);
            imprimirPreOrdemRecursivo(noAtual.direita);
        }
    }
}

