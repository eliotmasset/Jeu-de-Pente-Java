import java.util.*;
import java.lang.Math;
import javax.sound.sampled.*;
import java.util.*;
import java.io.*;

public class Sons implements Runnable
{
        private final int BUFFER_SIZE = 128000;
        private File soundFile;
        private AudioInputStream audioStream;
        private AudioFormat audioFormat;
        private SourceDataLine sourceLine;
        private String path;
        private boolean loop=true;

        @Override
        public void run() {
                if(path==null)
                        path="../son/start.wav";
                playSong();
        }

        public void setPath(String s)
        {
                path=s;
        }

        public void setLoop(boolean _loop)
        {
                loop=_loop;
        }

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
