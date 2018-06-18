package thepawnssaga;

import java.awt.Graphics2D;

public class MovimentaMatriz extends Inimigos{
    
    int vetorTelas[] = new int[9];
    int matrizTela[][] = new int[16][16];
    int Mx = 0, My = 0;

    public MovimentaMatriz(Game game, int type, int x, int y, int mapaX, int mapaY) {
        super(game, type, x, y, mapaX, mapaY);
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
