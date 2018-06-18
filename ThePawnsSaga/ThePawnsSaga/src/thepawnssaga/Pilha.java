
package thepawnssaga;
   
public class Pilha {

    private No elementos[];
    private int topo;

    public Pilha(int tam) {
        this.elementos = new No[tam];
        this.topo = -1;
    }

    public void Push(No elemento){

        this.topo++;
        this.elementos[this.topo] = elemento;

    }

    public No Pop(){

        No elemento = this.elementos [this.topo];
        this.topo--;
        return elemento;
    }

    public boolean isEmpty(){
        if (this.topo == -1)
            return true;
        else 
            return false;

    }
}

   

