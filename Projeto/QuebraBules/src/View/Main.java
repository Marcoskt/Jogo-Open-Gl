package View;

import OpenGL.Objetos_openGL;
import java.awt.Color;

public class Main {
    
    public static int largura = 800, altura = 800;
    public static OpenGL.Objetos_openGL objetosOpenGL;
    
    public static void main(String[] args) {

        //Objetos----------------------------------------------------
        TelaGame telaPrincipal = new TelaGame();
        objetosOpenGL = new Objetos_openGL();
        //----------------------------------------------------------     

        //Configurações da tela-------------------------------------
        //telaPrincipal.painel.add(objetosOpenGL.getCanvas());
        telaPrincipal.add(objetosOpenGL.getCanvas());
        
        telaPrincipal.setSize(largura, altura);
        telaPrincipal.setLocationRelativeTo(null);
        telaPrincipal.setResizable(false);
        //telaPrincipal.painel.setBounds(20, 20, 500, 500);
        //telaPrincipal.add(telaPrincipal.painel);
        //telaPrincipal.setResizable(false);
        telaPrincipal.addKeyListener(telaPrincipal);
        telaPrincipal.getContentPane().setBackground(Color.WHITE);
        telaPrincipal.setVisible(true);
        //----------------------------------------------------------        

        //OpenGL----------------------------------------------------
        objetosOpenGL.getAnimacao().start();
        objetosOpenGL.getCanvas().addGLEventListener(telaPrincipal);
        objetosOpenGL.getCanvas().addKeyListener(telaPrincipal);
        objetosOpenGL.getCanvas().addMouseMotionListener(telaPrincipal);
        objetosOpenGL.getCanvas().addMouseListener(telaPrincipal);
        
        objetosOpenGL.getCanvas().setVisible(true);
        //----------------------------------------------------------

    }
    
}
