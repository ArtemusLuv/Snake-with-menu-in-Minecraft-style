import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/*
 *  I think this use only .wav sound, but I didn't try to check, how be working with .mp3
 *  Try You :D
 */

public class Sound {

    private String track; // link track
    private Clip clip = null; // link class object
    private FloatControl volumeC = null; // volume controller
    private double wt; // variable of volume level
    private  boolean isPlaying;

    // constructor (sound link, volume level)
    public Sound(String track, double wt) {
        this.track = track;
        this.wt = wt;
        this.isPlaying = false; // is music play
    }

    public void playSound() {
        File f = new File(this.track); // give sound file to "f"
        // stream for write and read
        AudioInputStream tr = null; // object of AudioInputStream (empty)
        isPlaying = true;
        try {
            tr = AudioSystem.getAudioInputStream(f); // get your file
        } catch (UnsupportedAudioFileException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        try {
            clip = AudioSystem.getClip(); // get interface implementation Clip
            clip.open(tr); // load your sound stream to Clip
            // get volume controller
            volumeC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            clip.setFramePosition(0); // set the pointer to start
            clip.start();  // go!
        } catch (LineUnavailableException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //volume level
    public void setVolume() {
        if (wt < 0) wt = 0;
        if (wt > 1) wt = 1;
        float min = volumeC.getMinimum();
        float max = volumeC.getMaximum();
        volumeC.setValue((max-min)*(float)wt+min);
    }

    public void play() {
        File f = new File(this.track); // give sound file to "f"
        // stream for write and read
        AudioInputStream tr = null; // object of AudioInputStream (empty)
        try {
            tr = AudioSystem.getAudioInputStream(f); // get your file
        } catch (UnsupportedAudioFileException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        try {
            clip = AudioSystem.getClip(); // get interface implementation Clip
            clip.open(tr); // load your sound stream to Clip
            // get volume controller
            volumeC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            if (this.isPlaying == false){
                clip.setFramePosition(0);
                clip.start();
                this.isPlaying = true;
            }

        } catch (LineUnavailableException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // repeat 5 times
    public void repeat() {
        if (this.isPlaying) clip.loop(5);
    }

    public void stop() {
        clip.stop();
        clip.close();
        this.isPlaying = false;
    }

}
