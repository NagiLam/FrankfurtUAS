package ipump;

import java.io.IOException;
import javax.annotation.Resources;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Wav {

    private static SourceDataLine auline = null;
    

    public static void play(String filename) {
        stop();
 
        if (Clock.getSpeed() > 8)
        {   
            return;
        }
        
 
        final String finalFilename = filename;
        
      
        new Thread(
            new Runnable() {
                public void run() {
                    try {
                        playFile(finalFilename);   
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        ).start();
    }
    

    public static void stop()
    {
        if (auline != null)
        {
            auline.stop();
            auline.close();
        }
    }
    
 
    private static void playFile(String filename)
    {
     
        AudioInputStream audioInputStream = null;
        try {
                audioInputStream = AudioSystem.getAudioInputStream(Resources.class.getClass().getResourceAsStream("/resources/wav/" + filename));
        } catch (UnsupportedAudioFileException e1) {
                e1.printStackTrace();
                return;
        } catch (IOException e1) {
                e1.printStackTrace();
                return;
        }

      
        AudioFormat format = audioInputStream.getFormat();
        auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);
        } catch (LineUnavailableException e) {
                e.printStackTrace();
                return;
        } catch (Exception e) {
                e.printStackTrace();
                return;
        }

        if (auline.isControlSupported(FloatControl.Type.PAN)) {
                FloatControl pan = (FloatControl) auline
                                .getControl(FloatControl.Type.PAN);
        }


        auline.start();
        int nBytesRead = 0;
        byte[] abData = new byte[524288];

        try {
                while (nBytesRead != -1) {
                        nBytesRead = audioInputStream.read(abData, 0, abData.length);
                        if (nBytesRead >= 0)
                                auline.write(abData, 0, nBytesRead);
                }
        } catch (IOException e) {
                e.printStackTrace();
                return;
        } finally {
                auline.drain();
                auline.close();
        }
    }
}
