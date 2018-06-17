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
import java.awt.Graphics2D;

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
    private Game game;

    public Inimigos(Game game, int type) {
            this.game= game;
            this.type = type;
    }

    public Inimigos(Game game, int type, int x, int y) {
            this.game= game;
            this.type = type;
            this.x = x;
            this.y = y;
    }

    public Inimigos(Game game, int type, int x, int y, int mapaX, int mapaY) {
            this.game= game;
            this.type = type;
            this.x = x;
            this.y = y;
            this.mapaX = mapaX;
            this.mapaY = mapaY;
    }
    
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
    }

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

    public void paint(Graphics2D g) {
            g.fillOval(this.x, this.y, 30, 30);
            /*
            g.fillOval(x+30, y, 5, 5);
            g.fillOval(x-30, y, 5, 5);
            g.fillOval(x, y-30, 5, 5);
            g.fillOval(x, y+30, 5, 5);
            */
    }

    public boolean colidiuInimigo(Player player, Inimigos inimigo){
        if (player.x + 20 > inimigo.x && player.x - 20 < inimigo.x)
            if (player.y + 40 > inimigo.y && player.y - 40 < inimigo.y)
                return true;
        return false;
    }

    public void findMatrixPosition( int x, int y, mapaEstrela mapa){

        int matrizTela[][] = new int[16][16];

        for (int i = 0; i < matrizTela.length; i++) {

            if(x > (i*50)+14 && x <= ((i+1)*50)+14) Mx = i+1;
            if(x <= 14) Mx = 0;
        }

        for (int i = 0; i < matrizTela.length; i++) {

            if(y > (i*50)+1 && y <= ((i+1)*50)+1) My = i+1;
            if(y <= 1) My = 0;
        }
        
        this.caminho = mapa.buscaPlayer(Mx, My);       
        //System.out.println(mapa.buscaPlayer(Mx, My));
    }
}