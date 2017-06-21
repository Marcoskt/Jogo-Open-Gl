package Objetos;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Som {

    private final String localArquivo;
    Clip clip;
    AudioInputStream audioInputStream;
    File arquivoDeSom;

    public Som(String file) {
        localArquivo = file;
        arquivoDeSom = new File(localArquivo);
    }

    public void tocar() {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(arquivoDeSom);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (Exception ex) {
            System.out.println("Erro ao tocar som");
            ex.printStackTrace();
        }
    }

}
