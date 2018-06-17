
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
    int x = 240;
    int y = 240;
    int xa = 0;
    int ya = 0;
    
    int Mx = 0, My = 0;
    
    boolean vivo = true;
    
    Image image;
    Game game;

    
    public Player(Game g) {
        this.game = g;
        URL loc = this.getClass().getResource("playerPawnIdle.png");
        
        ImageIcon pawn = new ImageIcon(loc);
        this.image = pawn.getImage();
        //this.clickPoint = new Point(100, 100);
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
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(image, x, y, game);
            g2d.dispose();
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
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xa = -2;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = 2;
                if (e.getKeyCode() == KeyEvent.VK_UP)
			ya = -2;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			ya = 2;
	}
        
        public void findMatrixPosition( int x, int y){
            
        int matrizTela[][] = new int[16][16];
        
        for (int i = 0; i < matrizTela.length; i++) {
            
            if(x > (i*50)+14 && x <= ((i+1)*50)+14) Mx = i+1;
            if(x <= 14) Mx = 0;
        }
        
        for (int i = 0; i < matrizTela.length; i++) {
            
            if(y > (i*50)+1 && y <= ((i+1)*50)+1) My = i+1;
            if(y <= 1) My = 0;
        }
        
        System.out.println("Mx = "+Mx+"       My = "+My);
        System.out.println("x= "+x+"      y = "+y);
    }

    
}