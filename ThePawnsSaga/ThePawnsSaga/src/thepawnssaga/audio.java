/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thepawnssaga;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author joao.lima
 */
public class audio {
    Clip crit;
    
    public synchronized void musica(String audioString){
        try
        {
            crit = AudioSystem.getClip();
            AudioInputStream inputStream1 = AudioSystem.getAudioInputStream(this.getClass().getResource(audioString));
            crit.open(inputStream1);
            crit.loop(Clip.LOOP_CONTINUOUSLY);
            crit.start();
        } catch (Exception e){System.err.println(e.getMessage());}
    }
    
    public void stopMusica(){
        crit.stop();
    }
    
}
