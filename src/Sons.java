import java.util.*;
import java.lang.Math;
import javax.sound.sampled.*;
import java.util.*;
import java.io.*;

/**
 * Classe pour lancer des sons sur des threads différents
 * @author Eliot Masset et Amimri Anouar
 * @version 1.0
 */
public class Sons implements Runnable
{
        /**
        * Volume
        */
        private float vol;
        /**
        * Controlleur du volume
        */
        private FloatControl volume;
        /**
        * Taille du buffer
        */
        private final int BUFFER_SIZE = 128000;
        /**
        * Fichier audio
        */
        private File soundFile;
        /**
        * Flux de lecture audio
        */
        private AudioInputStream audioStream;
        /**
        * Format audio
        */
        private AudioFormat audioFormat;
        /**
        * Stoque une ligne de donnée audio
        */
        private SourceDataLine sourceLine;
        /**
        * Chemin vers l'audio
        */
        private String path;
        /**
        * Boolean pour savoir s'il faut lire en boucle
        */
        private boolean loop=true;

        Sons()
        {
                loop=true;
        }
        /**
        * Fonction qui se lance quand un thread démarre
        */
        @Override
        public void run() {
                vol=50;
                if(path==null)
                        path="../son/start.wav";
                playSong();
        }

        /**
        * Setter sur le chemin de l'audio
	* @param s String qui stoque le chemin vers l'audio
        */
        public void setPath(String s)
        {
                path=s;
        }

        /**
        * Setter sur le boolean pour lire en boucle
	* @param _loop Boolean qui stoque l'état de loop
        */
        public void setLoop(boolean _loop)
        {
                loop=_loop;
        }

        /**
        * Setter sur le volume du son
	* @param _vol qui stoque le volume
        */
        public void setVolume(float _vol)
        {
                vol=_vol;
                volume.setValue(20.0f * (float) Math.log10( vol / 100.0 ));
        }

        /**
        * getter sur le volume
	* @return le volume
        */
        public float getVolume()
        {
                return vol;
        }

        /**
        * fonction qui lit et lance un fichier audio
        */
        public void playSong()
        {
                try {
                        String savePath=path;
                        soundFile = new File(path);
                        audioStream = AudioSystem.getAudioInputStream(soundFile);
                        audioFormat = audioStream.getFormat();
                        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
                        sourceLine = (SourceDataLine) AudioSystem.getLine(info);
                        sourceLine.open(audioFormat);
                        sourceLine.start();
                        if(loop)
                        {
                                volume = (FloatControl) sourceLine.getControl( FloatControl.Type.MASTER_GAIN );
                                volume.setValue(20.0f * (float) Math.log10( vol / 100.0 ));
                        }
                       int nBytesRead = 0;
                        byte[] abData = new byte[BUFFER_SIZE];
                        while (nBytesRead != -1 && savePath==path) {
                                try {
                                        nBytesRead = audioStream.read(abData, 0, abData.length);
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                                if (nBytesRead >= 0) {
                                        @SuppressWarnings("unused")
                                        int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
                                }
                        }   
                        sourceLine.drain();
                        sourceLine.close();
                }
                catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                    throw new RuntimeException(e);
                         }
                if(loop)
                        playSong();
            }
        
}
