//import java.lang.RuntimeException;
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
            noAtual.esquerda.pai = noAtual; // atualiza a referência do pai
        } else if (valor > noAtual.valor) {
            noAtual.direita = adicionarRecursivo(noAtual.direita, valor);
            noAtual.direita.pai = noAtual; // atualiza a referência do pai
        } else {
            System.out.println("Valor já existe na árvore!");
        }
        return noAtual;
    }
    

    public No pesquisar(int chave) {
        return pesquisarRecursivo(this.raiz, chave);
    }

    private No pesquisarRecursivo(No noAtual, int chave) {
        if (noAtual == null) {
            return null;
        }
        if (chave < noAtual.valor) {
            return pesquisarRecursivo(noAtual.esquerda, chave);
        } else if (chave > noAtual.valor) {
            return pesquisarRecursivo(noAtual.direita, chave);
        } else {
            return noAtual;
        }
    }

    public int remover(int chave) {
        No no = pesquisarRecursivo(this.raiz, chave);
        if (no == null) {
            throw new ChaveNaoExisteException("A chave " + chave + " não existe na árvore!");
        }
        if (isExternal(no)) { // nó folha
            int retorna = no.valor;
            if (no == raiz) {
                raiz = null;
            } else if (isFilhoDireito(no)) {
                no.pai.direita = null;
            } else {
                no.pai.esquerda = null;
            }
            return retorna;
        }
        if (temSomenteUmFilho(no)) { // somente um filho esq ou dir
            No filho;
            if (no.esquerda != null) {
                filho = no.esquerda;
            } else {
                filho = no.direita;
            }
            if (no == raiz) {
                raiz = filho;
            } else if (isFilhoDireito(no)) {
                no.pai.direita = filho;
            } else {
                no.pai.esquerda = filho;
            }
            filho.pai = no.pai;
            return no.valor;
        }
        
        // dois filhos
        if (temDoisFilhos(no)) {
            int retorna = no.valor;
            No sucessor = sucessor(no.direita);
            remover(sucessor.valor);
            no.valor = sucessor.valor;
            return retorna;
        }
        return -1;
    }

    public boolean isExternal(No no) {
        return no.esquerda == null && no.direita == null;
    }

    public boolean isFilhoDireito(No no) {
        return no.pai != null && no.pai.direita == no;
    }

    public boolean isFilhoEsquerdo(No no) {
        return no.pai != null && no.pai.esquerda == no;
    }

    public boolean temSomenteUmFilho(No no) {
        return (no.esquerda == null && no.direita != null) ||
                (no.esquerda != null && no.direita == null);
    }

    public boolean temFilhoEsquerdo(No no) {
        return no.esquerda != null;
    }

    public boolean temDoisFilhos(No no) {
        return no.esquerda != null && no.direita != null;
    }

    private No sucessor(No no) {
        if (no.esquerda != null) {
            return sucessor(no.esquerda);
        } else {
            return no;
        }
    }
    
    public void imprimir() {
        imprimirRecursivo(raiz, 0);
    }

    private void imprimirRecursivo(No no, int nivel) {
        if (no == null) {
            return;
        }
        imprimirRecursivo(no.direita, nivel + 1);
        if (nivel > 0) {
            System.out.print("   ".repeat(nivel - 1));
            System.out.print("---");
        }
        System.out.println(no.valor);
        imprimirRecursivo(no.esquerda, nivel + 1);
    }
    
}
