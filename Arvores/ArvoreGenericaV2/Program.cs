using System;
using System.Collections;


class Program{
    public static void Main (string[] args){
        ArvoreG av = new ArvoreG(1);
        av.AddChild(av.Root(), 2);
        av.AddChild((av.Root()), 3); 
        var enumerator = av.Children(av.Root());
        while(enumerator.MoveNext()){
            Console.WriteLine(enumerator.Current);
        }
    }
}

class ArvoreG{
    public class Node{
        private Object elemento;
        private Node pai;
        private ArrayList filhos = new ArrayList();

        public Node(Node pai, Object elemento){
            this.pai = pai;
            this.elemento = elemento;
        }

        public Object GetElement(){
            return elemento;
        }

        public Node Parent(){
            return pai;
        }
        public void SetElement(Object o){
            elemento = o;
        }
        public void AddChild(Node o){
            filhos.Add(o);
        }
        public void RemoveChild(Node o){
            filhos.Remove(o);
        }
        public int ChildrenNumber(){
            return filhos.Count;
        } 
        public IEnumerator Children(){
            return this.filhos.GetEnumerator();
        }
        public override string ToString()
        {
            return $"{elemento}";
        }
    } 
    Node raiz;
    int tamanho;
    public ArvoreG(Object o){
        raiz = new Node(null!, o);
        tamanho = 1;
    }

    // metodos de acesso
    public Node Root(){
        return raiz;
    }
    public Node Parent(Node v){
        return v.Parent();
    }
    public IEnumerator Children(Node v){
        return v.Children();
    }

    // metodos de consulta
    public bool IsInternal(Node v){
        return (v.ChildrenNumber() > 0);
    }
    public bool IsExternal(Node v){
        return (v.ChildrenNumber() == 0);
    }

    public bool IsRoot(Node v){
        return (v == raiz);
    }
    public int Depth(Node v){
        int profundidade = Profundidade(v);
        return profundidade;
    }
    
    // metodos genericos
    public int Height(Node v){
        // fazer
        if (IsExternal(v)){
            return 0;
        }
        else{
            int h = 0;
            while(Children(v).MoveNext()){
                h = Math.Max(h, Height(v));
            }
            return (1 + h);
        }
    }

    public int Size(){
        return tamanho;
    }

    public Boolean IsEmpty(){
        return tamanho == 1;
    } 

    private int Profundidade(Node v){
        if ((v == raiz)){
            return 0;
        }
        else{
            return(1 + Profundidade(v.Parent()));
        }
    }

    // metodos de atualização
    public void AddChild(Node v, Object o){
        Node novo = new Node(v, o);
        v.AddChild(novo);
        tamanho++;
    }
    public Object Remove(Node v){
        Node pai = v.Parent();
        if (((pai != null) || IsExternal(v))){
            pai.RemoveChild(v);
        }
        else{
            throw new SystemException();
        }
        Object o = v.GetElement();
        tamanho--;
        return o;
    }

    public Object Replace(Node v, Object o){
        v.SetElement(o);
        return o;
    }


}