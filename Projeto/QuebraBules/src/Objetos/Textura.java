package Objetos;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Textura {

    
    public  Texture textura;
    private TextureData textureData;
    private BufferedImage ImagemDaTextura;
    public InputStream stream;
    public static int width, height;
    
    

    public Textura(String img) {
        lerTextura(img);
        carregarTextura(img);

    }

    void lerTextura(String arquivo) {

        ImagemDaTextura = null;
        try {
            ImagemDaTextura = ImageIO.read(new File(arquivo));

            width = ImagemDaTextura.getWidth();
            height = ImagemDaTextura.getHeight();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar arquivo: " + arquivo);
            System.out.println(e);
        }
    }

    public void ativarTextura(GL2 gl) {
        textura.enable(gl);
        textura.bind(gl);
    }

    public void desativarTextura(GL2 gl) {
        textura.disable(gl);
    }

    void carregarTextura(String img) {
        try {
            
            stream = getClass().getResourceAsStream(img);
            textureData = TextureIO.newTextureData(GLProfile.getDefault(), stream, false, "jpg");
            textura = TextureIO.newTexture(textureData);
        
        } catch (IOException exc) {
            System.out.println("erro aqui" + exc);
            System.exit(1);
        }
    }

    
    

}
