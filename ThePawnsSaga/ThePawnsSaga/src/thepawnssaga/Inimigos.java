/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thepawnssaga;

/**
 *
 * @author acronuslg_sup
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Inimigos {
    int x = 0;
    int y = 0;
    int xa = 0;
    int ya = 0;
    int type = 0;
    int mapaX = 0;
    int mapaY = 0;
    int Mx = 0;
    int My = 0;
    No caminho= new No();
    
    Image bispo;
    Image torre;
    Image cavalo;
    
    boolean vivo = true;
    
    Pilha p = new Pilha(1000);
    boolean chegouX = true;
    boolean chegouY = true;
    No moveTo = null;
    int xTo=0, yTo=0;
    
    private Game game;
/*
    public Inimigos(Game game, int type) {
            this.game= game;
            this.type = type;
    }
*/
    public Inimigos(Game game, int type, int x, int y) {
            this.game= game;
            this.type = type;
            this.x = x;
            this.y = y;
            
        URL bishop = this.getClass().getResource("Bispo.png");
        ImageIcon bishopSprite = new ImageIcon(bishop);
        this.bispo = bishopSprite.getImage();
        
        URL tower = this.getClass().getResource("Torre.png");
        ImageIcon towerSprite = new ImageIcon(tower);
        this.torre = towerSprite.getImage();
        
        URL horse = this.getClass().getResource("Cabalo.png");
        ImageIcon horseSprite = new ImageIcon(horse);
        this.cavalo = horseSprite.getImage();
    }

    public Inimigos(Game game, int type, int x, int y, int mapaX, int mapaY) {
        this.game= game;
        this.type = type;
        this.x = x;
        this.y = y;
        this.mapaX = mapaX;
        this.mapaY = mapaY;
            
        URL bishop = this.getClass().getResource("Bispo.png");
        ImageIcon bishopSprite = new ImageIcon(bishop);
        this.bispo = bishopSprite.getImage();
        
        URL tower = this.getClass().getResource("Torre.png");
        ImageIcon towerSprite = new ImageIcon(tower);
        this.torre = towerSprite.getImage();
        
        URL horse = this.getClass().getResource("Cabalo.png");
        ImageIcon horseSprite = new ImageIcon(horse);
        this.cavalo = horseSprite.getImage();
    }
    /*
    void move(Player player) {
        switch(this.type){            
            case 1: //""""""""""bispo""""""""""""
                if (this.x > player.x + 40){
                    this.xa = -1;
                }else if(this.x < player.x - 40){
                    this.xa = 1;
                }

                if (this.y > player.y + 40){
                    this.ya = -1;
                }else if(this.y < player.y - 40){
                    this.ya = 1;
                }

                if (this.x + this.xa > game.getWidth() - 30)
                    this.xa = -1;
                if (this.y + this.ya > game.getHeight() - 30)
                    this.ya = -1;

                this.x = this.x + this.xa;
                this.y = this.y + this.ya;
                break;
            case 2://Torre
                double rnd = Math.random();
                if (this.y > 400 || this.ya == 0 ){
                    this.ya = 0;
                    if (this.x > player.x +2){
                        this.xa = -1;
                    }else if(this.x < player.x + -2){
                        this.xa = 1;
                    }else{
                        this.xa = 0;
                    }
                }                    
                if(this.x > 400 || this.xa == 0 ){
                    if (this.y > player.y){
                        this.xa = 0;
                        this.ya = -1;
                    }else if(this.y < player.y){
                        this.xa = 0;
                        this.ya = 1;
                    }else{
                        this.ya = 0;
                    }
                }


                if (this.x + this.xa > game.getWidth() - 30)
                    this.xa = -1;
                if (this.y + this.ya > game.getHeight() - 30)
                    this.ya = -1;

                this.x = this.x + this.xa;
                this.y = this.y + this.ya;
                break;
        }
    }*/

    void move() {

            if (this.x + this.xa < 0)
                    this.xa = 1;
            if (this.x + this.xa > game.getWidth() - 30)
                    this.xa = -1;
            if (this.y + this.ya < 0)
                    this.ya = 1;
            if (this.y + this.ya > game.getHeight() - 30)
                    this.ya = -1;

            this.x = this.x + this.xa;
            this.y = this.y + this.ya;
    }
    
    //====================================================================
    void move(Player player) {
        if(p.isEmpty())return;
        //if(game.gameMode!=1)return;
        //System.out.println(chegou);
        if(this.type == 1){ //""""""""""bispo""""""""""""
            if(chegouX && chegouY){
                moveTo = p.Pop();
                xTo = moveTo.getX();
                yTo = moveTo.getY();
                chegouX = false;
                chegouY = false;
                System.out.println("entrou");
            }        
            System.out.println("X:" + moveTo.getX());
            if(this.x < (xTo - 1)*50 +39)
                this.xa = 1;
            if(this.x > (xTo - 1)*50 +39)
                this.xa = -1;
            if(this.y < yTo*50)
                this.ya = 1;
            if(this.y > yTo*50)
                this.ya = -1;

            if(this.x == (moveTo.getX() - 1)*50 +39){
                chegouX = true;;
            }
            if(this.y < moveTo.getY()*50){
                chegouY = true;
            }

            this.x = this.x + this.xa;
            this.y = this.y + this.ya;
        }
        
        if(this.type == 2){//---------Torre-----------
            if(chegouX && chegouY){
                moveTo = p.Pop();
                xTo = moveTo.getX();
                yTo = moveTo.getY();
                chegouX = false;
                chegouY = false;
                System.out.println("entrou");
            }                
            //System.out.println("X:" + moveTo.getX());
            if(this.x < (xTo - 1)*50 +39)
                this.xa = 1;
            if(this.x > (xTo - 1)*50 +39)
                this.xa = -1;
            if(this.y < yTo*50)
                this.ya = 1;
            if(this.y > yTo*50)
                this.ya = -1;

            if(this.x == (moveTo.getX() - 1)*50 +39){
                chegouX = true;
            }
            if(this.y < moveTo.getY()*50){
                chegouY = true;
            }
            
            this.x = this.x + this.xa;
            this.y = this.y + this.ya;
        }
        
    }
    //====================================================================
    
    

    public void paint(Graphics g) {
        if(this.type == 1){ //""""""""""bispo""""""""""""
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(bispo, x, y, game);
            g2d.dispose();
        }
        if(this.type == 2){ //---------Torre-----------
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(torre, x, y, game);
            g2d.dispose();
        }
        if(this.type == 3){ //=========Cavalo=========
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(cavalo, x, y, game);
            g2d.dispose();
        }
    }

    public boolean colidiuInimigo(Player player, Inimigos inimigo){
        if (player.x + 20 > inimigo.x && player.x - 20 < inimigo.x)
            if (player.y + 40 > inimigo.y && player.y - 40 < inimigo.y){
                player.count = 20;
                return true;
            }
        return false;
    }
    
    public boolean colidiuHit(Player player, Inimigos inimigo){
            
            if (player.atkUL == true){
                if(inimigo.x>=player.x-70 && inimigo.x<=player.x){
                    if(inimigo.y>=player.y-70 && inimigo.y<=player.y)
                        return true;
                }
            }
            if (player.atkUR == true){
                if(inimigo.x<=player.x+70 && inimigo.x>=player.x){
                    if(inimigo.y>=player.y-70 && inimigo.y<=player.y)
                        return true;
                }
            }
            if (player.atkDL == true){
                if(inimigo.x>=player.x-70 && inimigo.x<=player.x){
                    if(inimigo.y<=player.y+70 && inimigo.y>=player.y)
                        return true;
                }
            }
            if (player.atkDR == true){
                if(inimigo.x<=player.x+70 && inimigo.x>=player.x){
                    if(inimigo.y<=player.y+70 && inimigo.y>=player.y)
                        return true;
                }
            }
            
            return false;
        }

    public void findMatrixPosition( int x, int y, mapaEstrela mapa, mapaEstrela mapaBispo){

        int matrizTela[][] = new int[16][16];

        for (int i = 0; i < matrizTela.length; i++) {

            if(x > (i*50)+14 && x <= ((i+1)*50)+14) Mx = i+1;
            if(x <= 14) Mx = 0;
        }

        for (int i = 0; i < matrizTela.length; i++) {

            if(y > (i*50)+1 && y <= ((i+1)*50)+1) My = i+1;
            if(y <= 1) My = 0;
        }
        
        if(this.type == 1){
            this.caminho = mapaBispo.buscaPlayer(Mx, My);
            mostraMapaCaminho(caminho);
        }else{
            this.caminho = mapa.buscaPlayer(Mx, My);
            mostraMapaCaminho(caminho);
        }        
                
        //System.out.println(mapa.buscaPlayer(Mx, My));
    }
    
    public void mostraMapaCaminho(No ultimo){
        if (ultimo == null){
            return;
        }
        No aux = ultimo;
        while(aux.getParent() != null){
            p.Push(aux);
            aux = aux.getParent();
        }
    }
}