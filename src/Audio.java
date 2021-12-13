import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


public class Audio {


    public Audio(){

    }

    // plays Audio from selected path
    public void playAudio(String path){

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./resources/Audio/"+path).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
