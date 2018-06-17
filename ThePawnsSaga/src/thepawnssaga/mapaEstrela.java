package thepawnssaga;

import java.util.ArrayList;

/**
 *
 * @author jlsl4
 */
public class mapaEstrela {
    ArrayList<No> aberta = new ArrayList<No>();
    ArrayList<No> fechada= new ArrayList<No>();;
    No inicio;
    No destino;
    ArrayList<No> vetores = new ArrayList<No>();
    public mapaEstrela(){
        aberta.clear();
        fechada.clear();               
        
        int x =0;
        int y =0;
        for(int indice = 0 ; indice < 256 ; indice++){
            if(x == 16){
                x = 0;
                y++;
            }
            this.vetores.add(new No(x,y,0,indice));
            x++;
            
        }
        
        //vizinhos
        for(No atual: this.vetores){
            //vizinho cima
            if(atual.ident > 15){
                atual.vizinho.add(this.vetores.get(atual.ident - 16));
            }
            
            //direita
            if(atual.getX() < 15){                
                atual.vizinho.add(this.vetores.get(atual.ident+1));
            }
            
            //esquerda
            if(atual.getX() > 1){
                atual.vizinho.add(this.vetores.get(atual.ident-1));
            }
            
            //vizinho baixo
            if(atual.ident < 239){                
                if (this.vetores.get(atual.ident+16)!= null)
                    atual.vizinho.add(this.vetores.get(atual.ident+16));
            }
        }
        
    }
    
    public void mostraInimigo(){
        for(No procura : this.vetores){
            if(procura.getTipo() == 3){
                System.out.println(procura.getX() + " - " + procura.getY());
            }
        }
    }
    
    public void mostraPlayer(){
        for(No procura : this.vetores){
            if(procura.getTipo() == 1){
                System.out.println(procura.getX() + " - " + procura.getY());
            }
        }
    }
    
    public No posicaoPlayer(){
        for(No procura : this.vetores){
            if(procura.getTipo() == 1){
                return procura;
            }
        }
        return null;
    }
    
    public void limpaMapa(){
        for(No espaco : this.vetores){            
            if(espaco.getTipo() != 1){
                espaco.setTipo(0);
            }            
        }
    }
    
    public void adicionaObs(int x, int y){
        for(No procura : this.vetores){
            if(procura.getX() == x && procura.getY() == y){
                procura.setTipo(2);
                return;
            }
        }
    }
    
    public void adicionaPlayer(int x, int y){
        for(No procura : this.vetores){
            if(procura.getX() == x && procura.getY() == y){
                procura.setTipo(1);
                return;
            }
        }
    }
    
    public void adicionaCaminho(No caminho){
        for(No procura : this.vetores){
            if(procura.getX() == caminho.getX() && procura.getY() == caminho.getY()){
                procura.setTipo(4);
                return;
            }
        }
    }
    
    public void removePlayer(int x, int y){
        for(No procura : this.vetores){
            if(procura.getX() == x && procura.getY() == y){
                procura.setTipo(0);
                return;
            }
        }
    }
    
    public void removeObs(int x, int y){
        for(No procura : this.vetores){
            if(procura.getX() == x && procura.getY() == y){
                procura.setTipo(0);
                return;
            }
        }
    }
    
    public No busca(No inicio, No destino){
        aberta.clear();
        fechada.clear(); 
                
        No atual =  inicio;                
        atual.setG(0);
        atual.setParent(null);
        aberta.add(atual);
        
        while(!aberta.isEmpty()){           
            atual = aberta.remove(0);            
            fechada.add(atual);                                  

            
            if (atual.getTipo() == 1 || atual.getX() == destino.getX() && atual.getY() == destino.getY()){
                return atual;               
            }
            
            for(No vizinho: atual.vizinho){               
                if((vizinho.getTipo() != 0 && vizinho.getTipo() != 1 )|| contemVizinhoFechado(vizinho.ident)){
                    continue;
                }
                //if (!aberta.contains(vizinho.ident)){
                if (!this.contemVizinhoAberto(vizinho.ident)){
                    vizinho.setParent(atual);
                    vizinho.calcH(destino);
                    vizinho.calcG(atual);
                    vizinho.setF(vizinho.getG() + vizinho.getH());
                    aberta.add(vizinho);
                }else if(this.contemVizinhoAberto(vizinho.ident) && atual.getG() + atual.custoMov < vizinho.getG()){
                    vizinho.calcG(atual);
                    vizinho.setF(vizinho.getG() + vizinho.getH());
                    vizinho.setParent(atual);
                }
            }
            
        }
        
        return null;        
    }
    
    public boolean contemVizinhoAberto(int identificador){
        
        for(No busca: aberta){
            if(busca.ident == identificador)
                return true;
        }
        
        return false;
    }
    
    public boolean contemVizinhoFechado(int identificador){
        for(No busca: fechada){
            if(busca.ident == identificador)
                return true;
        }
        
        return false;
    }
    
    public No buscaPlayer(int x, int y){
        No inimigo = null;
        No player = null;
        
        this.limpaMapa();
        
        for(No procura : this.vetores){
            if(procura.getX() == x && procura.getY() == y){
                inimigo = procura;
                break;
            }
        }
               
        for(No procura : this.vetores){
            if(procura.getTipo() == 1){
                player = procura;
                break;
            }
        }                 
                       
        if (inimigo != null && player != null){
            this.mostraMapaCaminho(this.busca(inimigo,player));
            this.mostraMapa();
            // Isso aqui é o que tem que retornar
            return this.busca(inimigo,player);
        }else{
            return null;
        }        
    }
    
    public void mostraMapa(){
        
        for(No indice: this.vetores){
            System.out.print(indice.getTipo());
            if(indice.getX() == 15)
                   System.out.println("");
        }
        System.out.println("fim");
        this.mostraPlayer();
    }
    
    public void mostraMapaCaminho(No ultimo){
        if (ultimo == null){
            return;
        }
        No aux = ultimo;
        while(aux.getParent() != null){
            this.adicionaCaminho(aux);
            aux = aux.getParent();
        }
    }
}
