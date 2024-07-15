package dev.laughingcat27.util.fx.clip;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class AudioClipFactory {

    public static Clip createClip(URL url) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);

            Clip clip = AudioSystem.getClip();

            clip.open(audioInputStream);

            return clip;
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
