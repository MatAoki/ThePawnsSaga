/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thepawnssaga;

import java.util.ArrayList;

/**
 *
 * @author joao.lslima1
 */
public class AEstrela {
    ArrayList<No> aberta = new ArrayList<No>();
    ArrayList<No> fechada= new ArrayList<No>();;
    
    public AEstrela(){
        aberta.clear();
        fechada.clear();               
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
            
            if (atual.getX() == destino.getX() && atual.getY() == destino.getY()){
                System.out.println(atual);
               // return true;
            }
            for(No vizinho: atual.vizinho){
                if(vizinho.getTipo() != 0 || fechada.contains(vizinho))
                    continue;
                if (!aberta.contains(vizinho)){
                    vizinho.setParent(atual);
                    vizinho.calcH(destino);
                    vizinho.calcG(atual);
                    vizinho.setF(vizinho.getG() + vizinho.getH());
                    aberta.add(vizinho);
                }else if(aberta.contains(vizinho) && atual.getG() + atual.custoMov < vizinho.getG()){
                    vizinho.calcG(atual);
                    vizinho.setF(vizinho.getG() + vizinho.getH());
                    vizinho.setParent(atual);
                }
            }
            
        }
        
        return null;
        //return false;
    }
     
    
}
