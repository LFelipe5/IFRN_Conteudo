public class No {
    int valor;
    No esquerda;
    No direita;
    No pai;

    public No(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
        this.pai = null;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }
}
