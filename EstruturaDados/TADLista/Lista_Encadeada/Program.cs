using System;



 class Program{
    public static void Main(){
        TADLista l = new TADLista();
        l.InsertFirst("botas");
        l.InsertLast(123);
        l.InsertFirst("internet");
        l.Print();
        l.InsertBefore(l.GetNode(1), "maçã");
        l.Print();
    }
}


class TADLista{
    public class Node{
        private object elemento;
        private Node prev;
        private Node next;

        public Node(object elemento, Node prev, Node next){
            this.elemento = elemento;
            this.prev = prev;
            this.next = next;
        }

        public object getElemento( ) { return elemento; }
        public Node getPrev( ) { return prev; }
        public Node getNext( ) { return next; }
        public void SetPrev(Node p) { prev = p; }
        public void SetNext(Node n) { next = n; }

        public override string ToString()
        {
            return $"{elemento}";
        }

    }
    private Node inicio;
    private Node fim;
    private int size = 0;


    public TADLista(){
        inicio = new Node(null!, null!, null!);
        fim = new Node(null!, inicio, null!);
        inicio.SetNext(fim);
    }
    // métodos genéricos
    public Boolean IsEmpty() {return size == 0;}
    public int Size(){return size;}

    //métodos de acesso
    public Node GetNode(int i){
        Node tr = fim;
        while (i <= Size() - 1){
            tr = tr.getPrev();
            i++;
        }
        return tr;
    }

    public object First(){
        if (IsEmpty()){return null!;}
        return inicio.getNext().getElemento();
    }
    public object Last(){
        if (IsEmpty()){return null!;}
        return fim.getPrev().getElemento();
    }
    //métodos de acesso de atualização
    private void AddBetween(object elemento, Node anterior, Node proximo){
        Node novo = new Node(elemento, anterior, proximo);
        anterior.SetNext(novo);
        proximo.SetPrev(novo);
        size++;
    }

    public void InsertFirst(object elemento){
        AddBetween(elemento, inicio, inicio.getNext());
    }
    public void InsertLast(object elemento){
        AddBetween(elemento, fim.getPrev(), fim);
    }

    
    public void InsertBefore(Node n, object elemento){
        Node tr = fim;
        int i = 0;
        while ( i <= Size() - 1){
            if(n.Equals(tr)){
                break;
            }
            tr = tr.getPrev();
            i++;
        }
        AddBetween(elemento,tr.getPrev(), tr);
    }
    public void InsertAfter(Node n, object elemento){
        Node tr = fim;
        int i = 0;
        while ( i <= Size() - 1){
            if(n.Equals(tr)){
                break;
            }
            tr = tr.getPrev();
            i++;
        }
        AddBetween(elemento,tr, tr.getNext());
    }
    public void Print(){
        int contador = 0;
        Node tr = inicio;
        
        while (contador != (Size())){
            Console.Write(tr.getNext().getElemento());
            if (contador != Size() - 1){Console.Write(" -> ");}
            tr = tr.getNext();
            contador++;
        }
        Console.WriteLine();
    }
        
} 