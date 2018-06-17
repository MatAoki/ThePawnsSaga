
package thepawnssaga;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class ScreenFrag {

    Image image;
    Game game;

    public ScreenFrag(Game g) {
        this.game = g;
    }
    
    public void paint(Graphics g) {
        if (this.game.gameMode == 1){
            if (this.game.fundo == 1){
                URL loc = this.getClass().getResource("fundo_b.png");
        
                ImageIcon fundoB = new ImageIcon(loc);
                this.image = fundoB.getImage();
                
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.drawImage(image, -1, -10, game);
                g2d.dispose();
                
            }
            if (this.game.fundo == -1){
                URL loc2 = this.getClass().getResource("fundo_p.png");
        
                ImageIcon fundoP = new ImageIcon(loc2);
                this.image = fundoP.getImage();
                
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.drawImage(image, -1, -10, game);
                g2d.dispose();
            }
            
        }
    }
    
}
