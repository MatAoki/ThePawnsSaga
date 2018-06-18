package thepawnssaga;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JPanel {
    mapaEstrela estrela = new mapaEstrela(1);
    mapaEstrela estrelaBispo = new mapaEstrela(2);
        
    Menu menu = new Menu(this);
    Player player = new Player(this);
    ScreenFrag bg = new ScreenFrag(this);
    //Inimigos ball = new Inimigos(this,1);
    //Inimigos ball2 = new Inimigos(this,2);    
    
    //Inimigos pecas[] =  new Inimigos[10]; 
    ArrayList<Inimigos>pecas   = new ArrayList();
    ArrayList<Inimigos>pecasAtu   = new ArrayList();
    
    int gameMode = 0;
    int screenFrag = 99;
    int fundo = -1;
    int mapaX = 1;
    int mapaY = 1;
    int score = 0;
    
    boolean win = false;
    Image Ganhou;
    
    JFrame frame;
    
    Image scoreHud;
    Image n0;
    Image n1;
    Image n2;
    Image n3;
    Image n4;
    Image n5;
    Image n6;
    Image n7;
    Image n8;
    Image n9;
    
    

    public Game() throws FileNotFoundException, IOException {
        addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				menu.keyReleased(e);
                                player.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				menu.keyPressed(e);
                                player.keyPressed(e);
			}
		});
        setFocusable(true);
        
        //Config Tela
        frame = new JFrame("The Pawn's Saga");
        
        frame.add(this);
        frame.setSize(810, 825);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        
        //Musica
        audio som = new audio(); 
        
        //Config inimigos
        FileReader arquivoInimigos = new FileReader("arquivoInimigos.txt");
        BufferedReader bufferArquivo = new BufferedReader(arquivoInimigos);
        
        System.out.println("arquivo de Inimigos aberto corretamente");
        
        String linha = bufferArquivo.readLine(); // lê a primeira linha
        
        int indiceLinha = 0;
        //percorre enquanto a linha não estiver nula
        while (linha != null) {
            //divide a linha atual em um array utilizando " " como separador
            String linhas[] = linha.split(" ");
            //pecas[indiceLinha] = new Inimigos(this,Integer.parseInt(linhas[0]));
            pecas.add(new Inimigos(this,Integer.parseInt(linhas[0]),Integer.parseInt(linhas[1]),Integer.parseInt(linhas[2]),Integer.parseInt(linhas[3]),Integer.parseInt(linhas[4])));
            indiceLinha++;
            linha = bufferArquivo.readLine(); // lê proxima linha
        }   
        //System.out.println(indiceLinha);
        
        this.spawnInimigo();
        
        //score
        URL SC = this.getClass().getResource("score.png");
        ImageIcon SCORE = new ImageIcon(SC);
        this.scoreHud = SCORE.getImage();
        
        URL N1 = this.getClass().getResource("1.png");
        ImageIcon num1 = new ImageIcon(N1);
        this.n1 = num1.getImage();
        
        URL N2 = this.getClass().getResource("2.png");
        ImageIcon num2 = new ImageIcon(N2);
        this.n2 = num2.getImage();
        
        URL N3 = this.getClass().getResource("3.png");
        ImageIcon num3 = new ImageIcon(N3);
        this.n3 = num3.getImage();
        
        URL N4 = this.getClass().getResource("4.png");
        ImageIcon num4 = new ImageIcon(N4);
        this.n4 = num4.getImage();
        
        URL N5 = this.getClass().getResource("5.png");
        ImageIcon num5 = new ImageIcon(N5);
        this.n5 = num5.getImage();
        
        
        URL N6 = this.getClass().getResource("6.png");
        ImageIcon num6 = new ImageIcon(N6);
        this.n6 = num6.getImage();
        
        URL N7 = this.getClass().getResource("7.png");
        ImageIcon num7 = new ImageIcon(N7);
        this.n7 = num7.getImage();
        
        URL N8 = this.getClass().getResource("8.png");
        ImageIcon num8 = new ImageIcon(N8);
        this.n8 = num8.getImage();
        
        URL N9 = this.getClass().getResource("9.png");
        ImageIcon num9 = new ImageIcon(N9);
        this.n9 = num9.getImage();
        
        URL N0 = this.getClass().getResource("0.png");
        ImageIcon num0 = new ImageIcon(N0);
        this.n0 = num0.getImage();
        
        URL Win = this.getClass().getResource("win.png");
        ImageIcon WIN = new ImageIcon(Win);
        this.Ganhou = WIN.getImage();
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        //player.paint(g);
        if (this.gameMode == 0){
            menu.paint(g);
        }
        if (this.gameMode == 1){
            
            if (fundo == 1){
                bg.paint(g);
            }
            if (fundo == -1){
                bg.paint(g);
            }
            
            player.paint(g);
            
            
            for (Inimigos peca : pecasAtu) {
                //System.out.println(peca.vivo);
                if(peca.vivo && player.vidas>0)
                    peca.paint(g);
            }
            Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
            if(win){
                Graphics2D gwd = (Graphics2D) g.create();
                gwd.drawImage(Ganhou, 0, 0 , this);
                gwd.dispose();
            }
            paintScore(g);
        }
        
        //System.out.println("gameMode = " + gameMode);
    }
    
    public void paintScore(Graphics g){

        //System.out.println("SCOOOOOOOORE");
        Graphics2D gSd = (Graphics2D) g.create();
        gSd.drawImage(scoreHud, 370, 0 , this);
        gSd.dispose();
        /*
        Graphics2D g0d = (Graphics2D) g.create();
        Graphics2D g1d = (Graphics2D) g.create();
        Graphics2D g2d = (Graphics2D) g.create();
        Graphics2D g3d = (Graphics2D) g.create();
        Graphics2D g4d = (Graphics2D) g.create();
        Graphics2D g5d = (Graphics2D) g.create();
        Graphics2D g6d = (Graphics2D) g.create();
        Graphics2D g7d = (Graphics2D) g.create();
        Graphics2D g8d = (Graphics2D) g.create();
        Graphics2D g9d = (Graphics2D) g.create();
        */
        if(score%10 != 0){
            Graphics2D g5d = (Graphics2D) g.create();
            g5d.drawImage(n5, 390, 30 , this);
            g5d.dispose();
        }
        else{
            Graphics2D g0d = (Graphics2D) g.create();
            g0d.drawImage(n0, 390, 30 , this);
            g0d.dispose();
        }

        if(score%100 != 0){
            int s10 = score%100;
            s10 = s10/10;
            if(s10 == 1){
                Graphics2D g1d = (Graphics2D) g.create();
                g1d.drawImage(n1, 380, 30 , this);
                g1d.dispose();
            }
            else if(s10 == 2){
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.drawImage(n2, 380, 30 , this);
                g2d.dispose();
            }
            else if(s10 == 3){
                Graphics2D g3d = (Graphics2D) g.create();
                g3d.drawImage(n3, 380, 30 , this);
                g3d.dispose();
            }
            else if(s10 == 4){
                Graphics2D g4d = (Graphics2D) g.create();
                g4d.drawImage(n4, 380, 30 , this);
                g4d.dispose();
            }
            else if(s10 == 5){
                Graphics2D g5d = (Graphics2D) g.create();
                g5d.drawImage(n5, 380, 30 , this);
                g5d.dispose();
            }
            else if(s10 == 6){
                Graphics2D g6d = (Graphics2D) g.create();
                g6d.drawImage(n6, 380, 30 , this);
                g6d.dispose();
            }
            else if(s10 == 7){
                Graphics2D g7d = (Graphics2D) g.create();
                g7d.drawImage(n7, 380, 30 , this);
                g7d.dispose();
            }
            else if(s10 == 8){
                Graphics2D g8d = (Graphics2D) g.create();
                g8d.drawImage(n8, 380, 30 , this);
                g8d.dispose();
            }
            else if(s10 == 9){
                Graphics2D g9d = (Graphics2D) g.create();
                g9d.drawImage(n9, 380, 30 , this);
                g9d.dispose();
            }
            else if(s10 == 0){
                Graphics2D g0d = (Graphics2D) g.create();
                g0d.drawImage(n0, 380, 30 , this);
                g0d.dispose();
            }
        }
        if(score%1000 != 0){
            int s100 = score%1000;
            s100 = s100/100;
            if(s100 == 1){
                Graphics2D g1d = (Graphics2D) g.create();
                g1d.drawImage(n1, 370, 30 , this);
                g1d.dispose();
            }
            else if(s100 == 2){
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.drawImage(n2, 370, 30 , this);
                g2d.dispose();
            }
            else if(s100 == 3){
                Graphics2D g3d = (Graphics2D) g.create();
                g3d.drawImage(n3, 370, 30 , this);
                g3d.dispose();
            }
            else if(s100 == 4){
                Graphics2D g4d = (Graphics2D) g.create();
                g4d.drawImage(n4, 370, 30 , this);
                g4d.dispose();
            }
            else if(s100 == 5){
                Graphics2D g5d = (Graphics2D) g.create();
                g5d.drawImage(n5, 370, 30 , this);
                g5d.dispose();
            }
            else if(s100 == 6){
                Graphics2D g6d = (Graphics2D) g.create();
                g6d.drawImage(n6, 370, 30 , this);
                g6d.dispose();
            }
            else if(s100 == 7){
                Graphics2D g7d = (Graphics2D) g.create();
                g7d.drawImage(n7, 370, 30 , this);
                g7d.dispose();
            }
            else if(s100 == 8){
                Graphics2D g8d = (Graphics2D) g.create();
                g8d.drawImage(n8, 370, 30 , this);
                g8d.dispose();
            }
            else if(s100 == 9){
                Graphics2D g9d = (Graphics2D) g.create();
                g9d.drawImage(n9, 370, 30 , this);
                g9d.dispose();
            }
            else if(s100 == 0){
                Graphics2D g0d = (Graphics2D) g.create();
                g0d.drawImage(n0, 370, 30 , this);
                g0d.dispose();
            }
        }
        
    }
    
    /*public boolean vitoria(Graphics g){
        
        if () {
            
        }
        return true;
    }*/
    
    

    private void move() {
        if(player.vivo){
            for (Inimigos peca : pecasAtu) {
                peca.move(player);
                peca.move(player);
                //System.out.println("andou");
                if(peca.chegouX == false && peca.chegouY == false){
                    //peca.x = peca.x+peca.xa;
                    //peca.y = peca.y+peca.ya;
                    System.out.println("peca X:"+ peca.x);
                    System.out.println("peca Y:"+ peca.y);
                    //System.out.println("andou");
                }
                //System.out.println("andou");
            }	
        }else{
            for (Inimigos peca : pecasAtu) {
                peca.move();
            }
        }
        player.move();
        // Retorna se ocorreu mudança na posição do player.
        if(player.findMatrixPosition(player.x, player.y,estrela,estrelaBispo)){                    
            // Roda a atualização de percurso que cada peça tem que fazer.
            for(Inimigos peca : pecasAtu){
                peca.findMatrixPosition(peca.x,peca.y,estrela,estrelaBispo);
            }                    
        }
        //estrela.mostraPlayer();
    }

    public void run() throws InterruptedException {
        while (true) {
            this.move();
            this.repaint();
            this.colisao();
            
            Thread.sleep(10);
        }
    }
    
    public void colisao(){
        if(player.vivo){
            for (Inimigos peca : pecasAtu) {
                if (peca.vivo) {
                    if(peca.colidiuInimigo(player,peca)){
                        player.vivo = false;
                        player.vidas--;
                    }
                }
                
                if(peca.colidiuHit(player,peca) == true){
                    if(peca.vivo)this.score = this.score+5;
                    peca.vivo = false;
                    if(peca.type == 9)win = true;
                }
            }
        }
            
        if (player.x == 2 && this.mapaX < 3){
            //player.x = 750;
            //frame.getHeight()
            player.x = frame.getWidth() - 80;
            fundo  = fundo*-1;
            this.mapaX++;
            this.limpaInimigo();
            this.spawnInimigo();
        }
        //System.out.println("player y "+player.y);
        if (player.y <= 2 && this.mapaY < 3){
            //player.y = 728;
            //player.y = 500;
            player.y = frame.getHeight() - 80;
            fundo  = fundo*-1;
            this.mapaY++;
            this.limpaInimigo();
            this.spawnInimigo();
        }
        if (player.x == frame.getWidth() - 70 && this.mapaX > 1){
            player.x = 4;
            fundo  = fundo*-1;
            this.mapaX--;
            this.limpaInimigo();
            this.spawnInimigo();
        }
        if (player.y == frame.getHeight() - 70 && this.mapaY > 1){
            player.y = 4;
            fundo  = fundo*-1;
            this.mapaY--;
            this.limpaInimigo();
            this.spawnInimigo();
        }

    }
    
    public void limpaInimigo(){
        pecasAtu.clear();
    }
    
    public void spawnInimigo(){
        for (Inimigos peca : pecas) {
            if(peca.mapaX == this.mapaX && peca.mapaY == this.mapaY)
                pecasAtu.add(new Inimigos(this, peca.type, peca.x, peca.x));
        }
    }

}
