
package thepawnssaga;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.ImageIcon;

public class Menu {

    int x = -5;
    int y = -5;

    Image image;
    Game game;
    
    public Menu(Game g) {
        this.game = g;
        URL loc = this.getClass().getResource("menu1.png");
        
        ImageIcon iia = new ImageIcon(loc);
        this.image = iia.getImage();
        
    }
    
    public void paint(Graphics g) {
        if (this.game.gameMode == 0){
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(image, x, y, game);
            g2d.dispose();
        }
    }
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_O){
                this.game.gameMode = 2;
                System.out.println("gameMode:" + this.game.gameMode);
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
                this.game.gameMode = 1;   
                System.out.println("gameMode:" + this.game.gameMode);
        }
        
    }
    
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_O)
                this.game.gameMode = 2;
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
                this.game.gameMode = 1;   
    }

    
}
