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
public class No {
    private int X; //X da Matriz
    private int Y; //Y da Matriz
    private int F; //Valor
    private int G; //Custo
    private int H; //Euristica
    
    int ident;
    private int tipo;
    private No parent; //Nó Pai
    ArrayList<No> vizinho = new ArrayList<No>();;
    int custoMov = 10;
    
    public No(){
        X = 0;
        Y = 0;
    }
    
    public No(int x, int y){
        this.X = x;
        this.Y = y;
    }
    
    public No(int x, int y, int tipo){
        this.X = x;
        this.Y = y;
        this.tipo = tipo;
    }
    
    public No(int x, int y, int tipo,int ident){
        this.X = x;
        this.Y = y;
        this.tipo = tipo;
        this.ident = ident;
    }
    
    /**
     * @return the X
     */
    public int getX() {
        return X;
    }

    /**
     * @param X the X to set
     */
    public void setX(int X) {
        this.X = X;
    }

    /**
     * @return the Y
     */
    public int getY() {
        return Y;
    }

    /**
     * @param Y the Y to set
     */
    public void setY(int Y) {
        this.Y = Y;
    }

    /**
     * @return the F
     */
    public int getF() {
        return F;
    }

    /**
     * @param F the F to set
     */
    public void setF(int F) {
        this.F = F;
    }

    /**
     * @return the G
     */
    public int getG() {
        return G;
    }

    /**
     * @param G the G to set
     */
    public void setG(int G) {
        this.G = G;
    }

    /**
     * @return the H
     */
    public int getH() {
        return H;
    }

    /**
     * @param H the H to set
     */
    public void setH(int H) {
        this.H = H;
    }

    /**
     * @return the parent
     */
    public No getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(No parent) {
        this.parent = parent;
    }
    
    public void calcH(No destPoint) {
        this.H = (Math.abs(this.X - destPoint.X) + Math.abs(this.Y - destPoint.Y)) * this.custoMov;
    }
    
    public void calcG(No point) {
        this.G = point.getG() + this.custoMov;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String toString() {
        return "{" + "elem=" + this.ident +"," +this.parent + '}';
    }
}
