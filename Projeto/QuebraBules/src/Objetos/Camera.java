package Objetos;

import View.Main;
import View.TelaGame;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

public class Camera extends TelaGame {

    public static float camX = 0;
    public static float camY = 0;
    public static float camZ = 0;
    public static float distacia = 1.5f;

    public static void alterarCamera(GL2 gl, GLU glu) {
        gl.glPushMatrix();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        // Perspective.
        float widthHeightRatio = (float) Main.largura / (float) Main.altura;
        glu.gluPerspective(45, widthHeightRatio, 1, 100);
        glu.gluLookAt(0, 0, distacia, camX, camY, 0, 0, 1, 0);

        // Change back to model view matrix.
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glPopMatrix();
    }

}
