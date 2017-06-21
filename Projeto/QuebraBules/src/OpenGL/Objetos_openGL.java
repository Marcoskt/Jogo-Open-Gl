package OpenGL;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

public class Objetos_openGL {

    FPSAnimator animacao;
    GLCanvas canvas;
    GLProfile glp;
    GLCapabilities caps;
    GL2 gl2;
    int quadrosPorSegundo =80;
    public GLUT glut;
    GLU glu;
    

    public Objetos_openGL() {
        glp = GLProfile.getDefault();
        caps = new GLCapabilities(glp);
        canvas = new GLCanvas(caps);
        animacao = new FPSAnimator(canvas, quadrosPorSegundo);
        glut = new GLUT();
        glu = new GLU();

    }

    public GLU getGlu() {
        return glu;
    }

    public void setGlu(GLU glu) {
        this.glu = glu;
    }

    public GLUT getGlut() {
        return glut;
    }

    public void setGlut(GLUT glut) {
        this.glut = glut;
    }

    public GL2 getGl2() {
        return gl2;
    }

    public GL2 getGl2(GLAutoDrawable Display) {
        return gl2 = Display.getGL().getGL2();
    }

    public void setGl2(GL2 gl2) {
        this.gl2 = gl2;
    }

    public int getQuadrosPorSegundo() {
        return quadrosPorSegundo;
    }

    public void setQuadrosPorSegundo(int quadrosPorSegundo) {
        this.quadrosPorSegundo = quadrosPorSegundo;
    }

    public FPSAnimator getAnimacao() {
        return animacao;
    }

    public void setAnimacao(FPSAnimator animacao) {
        this.animacao = animacao;
    }

    public GLCanvas getCanvas() {
        return canvas;
    }

    public void setCanvas(GLCanvas canvas) {
        this.canvas = canvas;
    }

    public GLProfile getGlp() {
        return glp;
    }

    public void setGlp(GLProfile glp) {
        this.glp = glp;
    }

    public GLCapabilities getCaps() {
        return caps;
    }

    public void setCaps(GLCapabilities caps) {
        this.caps = caps;
    }

}
