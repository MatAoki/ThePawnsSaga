package thepawnssaga;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

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
    
    JFrame frame;

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
            
            Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);            
            for (Inimigos peca : pecasAtu) {
                    peca.paint(g2d);
            }
        }
        
        //System.out.println("gameMode = " + gameMode);
    }

    private void move() {
            if(player.vivo){
                for (Inimigos peca : pecasAtu) {
                    peca.move(player);                    
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
            for (Inimigos peca : pecasAtu) {
                if(peca.colidiuInimigo(player,peca))
                    player.vivo = false;
            }            
            
            if (player.x == 2){
                //player.x = 750;
                //frame.getHeight()
                player.x = frame.getWidth() - 80;
                fundo  = fundo*-1;
                this.mapaX++;
                this.limpaInimigo();
                this.spawnInimigo();
            }
            if (player.y == 2){
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
