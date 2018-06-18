
package thepawnssaga;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.ImageIcon;

public class Player {
    int x = 540;
    int y = 540;
    int xa = 0;
    int ya = 0;
    
    int Mx = 0, My = 0;
    int MxAnt = 0, MyAnt = 0;
    
    boolean vivo = true;
    //---------variaveis para o ataque----------
    
    int lastMov = 1; //orienta os controles e direção do ataque
    
    boolean atkUL = false;
    boolean atkUR = false;
    boolean atkDL = false;
    boolean atkDR = false;
    
    Image atkSpriteUL;
    Image atkSpriteUR;
    Image atkSpriteDL;
    Image atkSpriteDR;
    
    int count = 0;
    //-------------------------------------------
    Image image;
    Game game;

    
    public Player(Game g) {
        this.game = g;
        URL loc = this.getClass().getResource("playerPawnIdle.png");
        
        ImageIcon pawn = new ImageIcon(loc);
        this.image = pawn.getImage();
        //this.clickPoint = new Point(100, 100);
        
        URL aUL = this.getClass().getResource("player-swingUL.png");
        ImageIcon upLeftSwing = new ImageIcon(aUL);
        this.atkSpriteUL = upLeftSwing.getImage();
        
        URL aUR = this.getClass().getResource("player-swingUR.png");
        ImageIcon upRightSwing = new ImageIcon(aUR);
        this.atkSpriteUR = upRightSwing.getImage();
        
        URL aDL = this.getClass().getResource("player-swingDL.png");
        ImageIcon downLeftSwing = new ImageIcon(aDL);
        this.atkSpriteDL = downLeftSwing.getImage();
        
        URL aDR = this.getClass().getResource("player-swingDR.png");
        ImageIcon downRightSwing = new ImageIcon(aDR);
        this.atkSpriteDR = downRightSwing.getImage();
    }
    
    public void move() {
        if(vivo){
            if (x + xa > 0 && x + xa < game.getWidth()-30)
                    x = x + xa;
            if (y + ya > 0 && y + ya < game.getHeight()-30)
                    y = y + ya;
        }
    }
    
    public void paint(Graphics g) {
        if (vivo){
            
            //if(count==121)count =0;
            if(atkUL){
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.drawImage(atkSpriteUL, x-36, y-22, game);
                count++;
                if(count%20 == 0){
                    g2d.dispose();
                    atkUL = false;
                }
            }
            else if (atkUR) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.drawImage(atkSpriteUR, x+10, y-22, game);
                count++;
                if(count%20 == 0){
                    g2d.dispose();
                    atkUR = false;
                }
            }
            else if (atkDL) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.drawImage(atkSpriteDL, x-36, y+1, game);
                count++;
                if(count%20 == 0){
                    g2d.dispose();
                    atkDL = false;
                }
            }
            else if (atkDR) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.drawImage(atkSpriteDR, x+10, y+1, game);
                count++;
                if(count%10 == 0){
                    g2d.dispose();
                    atkDR = false;
                }
            }
            else{
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(image, x, y, game);
            g2d.dispose();
            count = 0;
            }
        }
    }
    
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            if (xa<0 )
                xa = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            if (xa>0 )
                xa = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP){
            if (ya<0)
                ya = 0;

        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            if (ya>0)
                ya = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_F5){
            this.vivo = true;
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            xa = -2;
            ya = 0;
            lastMov = -2; 
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            xa = 2;
            ya = 0;
            lastMov = 2;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP){
            xa = 0;
            ya = -2;
            lastMov = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            xa = 0;
            ya = 2;
            lastMov = -1;
        }
        if(vivo){
            if (e.getKeyCode() == KeyEvent.VK_Z){
                if(lastMov == 1) atkUL = true;
                if(lastMov == -1) atkDL = true;
                if(lastMov == 2) atkUR = true;
                if(lastMov == -2) atkDL = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_X){
                if(lastMov == 1) atkUR = true;
                if(lastMov == -1) atkDR = true;
                if(lastMov == 2) atkDR = true;
                if(lastMov == -2) atkUL = true;
            }
        }
    }

    public boolean findMatrixPosition( int x, int y,  mapaEstrela mapa, mapaEstrela mapaBispo){

        int matrizTela[][] = new int[16][16];

        for (int i = 0; i < matrizTela.length; i++) {

            if(x > (i*50)+14 && x <= ((i+1)*50)+14) Mx = i+1;
            if(x <= 14) Mx = 0;
        }

        for (int i = 0; i < matrizTela.length; i++) {

            if(y > (i*50)+1 && y <= ((i+1)*50)+1) My = i+1;
            if(y <= 1) My = 0;
        }
        
        if(this.MxAnt != this.Mx || this.MyAnt != this.My){
            mapaBispo.removePlayer(MxAnt,MyAnt);
            mapa.removePlayer(MxAnt,MyAnt);
            this.MxAnt = this.Mx;
            this.MyAnt = this.My;            
            mapaBispo.adicionaPlayer(MxAnt,MyAnt);
            mapa.adicionaPlayer(MxAnt,MyAnt);
            return true;
        }
        return false;
    }    
}