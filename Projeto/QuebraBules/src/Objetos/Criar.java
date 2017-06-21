package Objetos;

import View.Main;
import View.TelaGame;
import static View.TelaGame.gl;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import java.util.Random;

public class Criar {

    static double x = -.31;//translat dos textos
    static double rotacao = 0;//rotação do teapot
    public static double velocidade = 0.0;//velocidade inicial do teapot
    public static int corBule1, corBule2, corBule3 = 1; //valores para criar belus com cores aleatórias

//    public static double velocidade = 0.005;//
    public static double mover = 0;//posição inicial do teapot

    public static void sala(GL2 gl) {

        gl.glPushMatrix();

        TelaGame.texturaAreaDeTiro.ativarTextura(gl);
        gl.glPushMatrix();
        gl.glBegin(GL2.GL_QUADS);

        gl.glTexCoord2f(1f, 0f);
        gl.glVertex3d(0.5, -0.5, -0.5);
        gl.glTexCoord2f(1f, 1f);
        gl.glVertex3d(0.5, 0.5, -0.5);
        gl.glTexCoord2f(0f, 1f);
        gl.glVertex3d(-0.5, 0.5, -0.5);
        gl.glTexCoord2f(0f, 0f);
        gl.glVertex3d(-0.5, -0.5, -0.5);

        gl.glPopMatrix();
        gl.glEnd();
        TelaGame.texturaAreaDeTiro.desativarTextura(gl);
        //-------------
        TelaGame.texturaParede.ativarTextura(gl);
        gl.glPushMatrix();
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(1f, 0f);
        gl.glVertex3d(0.5, -0.5, -0.5);
        gl.glTexCoord2f(0f, 1f);
        gl.glVertex3d(0.5, 0.5, -0.5);
        gl.glTexCoord2f(1f, 0f);
        gl.glVertex3d(0.5, 0.5, 0.5);
        gl.glTexCoord2f(0f, 0f);
        gl.glVertex3d(0.5, -0.5, 0.5);
        gl.glPopMatrix();
        gl.glEnd();

        //-------------
        gl.glPushMatrix();
        gl.glBegin(GL2.GL_QUADS);

        gl.glTexCoord2f(1f, 0f);
        gl.glVertex3d(-0.5, -0.5, 0.5);

        gl.glTexCoord2f(0f, 1f);
        gl.glVertex3d(-0.5, 0.5, 0.5);

        gl.glTexCoord2f(1f, 0f);
        gl.glVertex3d(-0.5, 0.5, -0.5);
        gl.glTexCoord2f(0f, 0f);
        gl.glVertex3d(-0.5, -0.5, -0.5);

        gl.glPopMatrix();
        gl.glEnd();

        //-------------
        gl.glPushMatrix();
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex3d(0.5, 0.5, 0.5);
        gl.glVertex3d(0.5, 0.5, -0.5);
        gl.glVertex3d(-0.5, 0.5, -0.5);
        gl.glVertex3d(-0.5, 0.5, 0.5);
        gl.glPopMatrix();
        gl.glEnd();

        //-------------
        gl.glPushMatrix();
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex3d(0.5, -0.5, -0.5);
        gl.glVertex3d(0.5, -0.5, 0.5);
        gl.glVertex3d(-0.5, -0.5, 0.5);
        gl.glVertex3d(-0.5, -0.5, -0.5);
        gl.glPopMatrix();
        gl.glEnd();
        TelaGame.texturaParede.desativarTextura(gl);
        //-------------
        TelaGame.texturaFrente.ativarTextura(gl);
        gl.glPushMatrix();
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3d(0.5, -0.5, 0.5);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3d(0.5, 0.5, 0.5);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3d(-0.5, 0.5, 0.5);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3d(-0.5, -0.5, 0.5);
        gl.glPopMatrix();
        gl.glEnd();
        TelaGame.texturaFrente.desativarTextura(gl);
        gl.glPopMatrix();
    }

    public static void textosJogo(GL2 lg, GLUT glut) {
        gl.glPushMatrix();
        gl.glTranslated(x, -0.7, 0);
        //gl.glScaled ( 5.0/Main.largura , 5.0/Main.largura , 5.0/Main.largura ); 
        gl.glRasterPos3f(0, 0.5f, 0);
        glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, "Vidas: " + TelaGame.vidas);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(x, -0.75, 0);
        //gl.glScaled ( 5.0/Main.largura , 5.0/Main.largura , 5.0/Main.largura ); 
        gl.glRasterPos3f(0, 0.5f, 0);
        glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, "Pontos: " + TelaGame.pontos);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(x, -0.80, 0);
        //gl.glScaled ( 5.0/Main.largura , 5.0/Main.largura , 5.0/Main.largura ); 
        gl.glRasterPos3f(0, 0.5f, 0);
        glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, "Recorde: " + TelaGame.maiorPontuacao);
        gl.glPopMatrix();
    }

    public static void textosInfo(GL2 lg, GLUT glut) {
        gl.glPushMatrix();
        gl.glTranslated(-0.22, -0.4, 0);
        //gl.glScaled ( 5.0/Main.largura , 5.0/Main.largura , 5.0/Main.largura ); 
        gl.glRasterPos3f(0, 0.5f, 0);
        glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, "Clique na chaleira para iniciar");
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glTranslated(-0.27, -0.45, 0);
        //gl.glScaled ( 5.0/Main.largura , 5.0/Main.largura , 5.0/Main.largura ); 
        gl.glRasterPos3f(0, 0.5f, 0);
        glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, "P - Pausar, R - Reinicar, M - Menu");
        gl.glPopMatrix();

    }

    public static void textoJogoPausado(GL2 lg, GLUT glut) {
        gl.glPushMatrix();
        gl.glColor3d(1, 0, 0);
        gl.glTranslated(-0.22, -0.46, 0);
        //gl.glScaled ( 5.0/Main.largura , 5.0/Main.largura , 5.0/Main.largura ); 
        gl.glRasterPos3f(0, 0.5f, 0);
        glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, "Jogo pausado, P para continuar");
        gl.glColor4d(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glPopMatrix();

    }

    public static void teapot(GL2 gl, GLUT glut) {
        rotacao += 3;
        if (mover < -0.32) {
            TelaGame.removerBule();
            TelaGame.vidas--;
        }

        gl.glPushMatrix();
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);
        gl.glColorMaterial(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE);

        float luzAmbiente[] = {0.2f, 0.2f, 0.2f, 1.0f};
        float luzDifusa[] = {0.7f, 0.7f, 0.7f, 1.0f};
        float luzEspecular[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float posicaoLuz[] = {0.0f, 50.0f, 50.0f, 1.0f};

        gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, luzAmbiente, 0);

        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, luzAmbiente, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, luzDifusa, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, luzEspecular, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, posicaoLuz, 0);

// Brilho do material
        float especularidade[] = {1.0f, 1.0f, 1.0f, 1.0f};
        int especMaterial = 60;

// Define a reflectância do material 
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, especularidade, 0);
// Define a concentração do brilho
        gl.glMateriali(GL2.GL_FRONT, GL2.GL_SHININESS, especMaterial);

        gl.glTranslated(mover, -0.07, 0);
        gl.glRotated(rotacao, 0f, 2f, 0f);
        //gl.glColor3d(1, x, 1);

        gl.glColor3d(corBule1, corBule2, corBule3);
        glut.glutSolidTeapot(.035);
        gl.glColor4d(1.0f, 1.0f, 1.0f, 1.0f);

        
        gl.glDisable(GL2.GL_LIGHT0);
        gl.glDisable(GL2.GL_LIGHTING);
        gl.glDisable(GL2.GL_COLOR_MATERIAL);
        
        gl.glPopMatrix();

    }

}
