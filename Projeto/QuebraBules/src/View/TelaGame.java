package View;

import Objetos.Textura;
import Objetos.Camera;
import Objetos.Criar;
import Objetos.Som;
import OpenGL.Objetos_openGL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TelaGame extends JFrame implements GLEventListener, KeyListener, MouseMotionListener, MouseListener {

    Objetos_openGL openGL = new OpenGL.Objetos_openGL();
    public static GL2 gl;
    JPanel painel = new JPanel(new BorderLayout());
    public static Textura texturaFrente, texturaAreaDeTiro, texturaParede;

    Cursor cursorPadrao, cursorMartelo, cursorBaterMartelo;
    public static int vidas = 5;
    public static int pontos = 0;
    public static int maiorPontuacao = 0;
    int bulesQuebrados = 3; //quando for quabrados 3 bules, a velocidade é aumentada;
    //public static int valorPonto = 0;
    static Random corDoBule = new Random();//cores aleatórias no bule
    Som somMartelada;
    Som somErrouMartelada;
    static int nivelVelocidade = 0; //mostra no título da janela o nível de velocidade do bule
    boolean mostrarVelocidade = true;
    boolean som = true;
    int valorPontuacao = 100; //valor da pontuação por bule quebrado
    boolean pausado = false;

    public TelaGame() {

        criarCursores();

        setCursor(cursorPadrao);

        somMartelada = new Som("som/quebrar.wav");
        somErrouMartelada = new Som("som/errou.wav");
        alterarTitulo();
    }

    @Override
    public void init(GLAutoDrawable glad) {
        openGL.setGl2(glad.getGL().getGL2());

        openGL.getGl2().glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        texturaFrente = new Textura("porta.jpg");
        texturaAreaDeTiro = new Textura("fundo.jpg");
        texturaParede = new Textura("parede.jpg");

    }

    @Override
    public void dispose(GLAutoDrawable glad) {
    }

    @Override

    public void display(GLAutoDrawable glad) {
        Main.largura = getWidth();
        Main.altura = getHeight();

        Criar.mover -= Criar.velocidade;//velocidade do teapot

        gl = openGL.getGl2(glad);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        Telajogo();
    }

    void Telajogo() {
        if (Camera.distacia < 1.5) {//evitar que verifique vidas quando estiver no menu
            if (vidas <= 0) {

                // Main.objetosOpenGL.getAnimacao().pause();
                JOptionPane.showMessageDialog(null, "Fim de jogo, acabou as vidas.\n\n"
                        + ((pontos > 0) ? "Você conseguiu " + pontos + " pontos" : "Sem pontuação"), "Sem vidas", JOptionPane.INFORMATION_MESSAGE);
                //Main.objetosOpenGL.getAnimacao().resume();
                vidas = 5;
                Camera.distacia = 1.5f;
            }
        }

        Camera.alterarCamera(gl, openGL.getGlu());

        Criar.sala(gl);

        if (Camera.distacia < 1.5) {
            Criar.textosJogo(gl, openGL.getGlut());
            Criar.teapot(gl, openGL.getGlut());

        } else {
            setCursor(cursorPadrao);

        }

        if (Criar.mover == 0 && Camera.distacia != 1.5) {//tira as informações quando o jogo começar

            if (pausado) {
                Criar.textoJogoPausado(gl, openGL.getGlut());
                Main.objetosOpenGL.getAnimacao().pause();
            } else {

                Criar.textosInfo(gl, openGL.getGlut());
            }

        }

        if (Main.objetosOpenGL.getAnimacao().isPaused()) {
            Criar.textoJogoPausado(gl, openGL.getGlut());
        }

        if (pontos > maiorPontuacao) {
            maiorPontuacao = pontos;
        }

    }

    void criarCursores() {
        try {

            Toolkit kit = Toolkit.getDefaultToolkit();
            Image image = new ImageIcon(getClass().getResource("cursorPadrao.png")).getImage();
            Point point = new Point(16, 16);

            String nameCursor = "Image1";
            cursorPadrao = kit.createCustomCursor(image, point, nameCursor);

            image = new ImageIcon(getClass().getResource("cursorMartelo.png")).getImage();
            point = new Point(16, 16);
            kit.getBestCursorSize(32, 32);
            nameCursor = "Image2";
            cursorMartelo = kit.createCustomCursor(image, point, nameCursor);

            image = new ImageIcon(getClass().getResource("cursorBaterMartelo.png")).getImage();
            point = new Point(16, 16);
            kit.getBestCursorSize(32, 32);
            nameCursor = "Image3";
            cursorBaterMartelo = kit.createCustomCursor(image, point, nameCursor);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    void reinicarJogo() {
        alterarCorDoBule();
        Criar.mover = 0;
        vidas = 5;
        Criar.velocidade = 0;
        pontos = 0;
        bulesQuebrados = 3;
        nivelVelocidade = 0;
        Camera.distacia = 1.4f;
        alterarTitulo();

    }

    public static void removerBule() {
        Criar.mover = 0.32;
        alterarCorDoBule();

    }

    static void alterarCorDoBule() {
        Criar.corBule1 = corDoBule.nextInt(2);
        Criar.corBule2 = corDoBule.nextInt(2);
        Criar.corBule3 = corDoBule.nextInt(2);
    }

    void opcaoTeclado() {

        JOptionPane.showMessageDialog(null, ""
                + "Todos os atalhos de teclado\n"
                + "P - Pause\n"
                + "R - Reiniciar jogo\n"
                + "M - Voltar ao menu\n"
                + "S - Ativar/Desativar som\n"
                + "V - Definir valor da pontuação\n"
                + "N - Nível de velovidade no título da janela\n"
                + "E - Sair do jogo");
    }

    void opcaoComoJogar() {

        JOptionPane.showMessageDialog(null, "Quebre a maior quantidade de bules que puder.\n\n"
                + "O jogo inicia com 5 vidas, perde-se uma se errar o bule ou deixa-lo passar.\n"
                + "A cada 3 bules quebrados a velocidade é aumentada.");

    }

    void aumentarVelocidadeBule() {
        Criar.velocidade += 0.001;
        nivelVelocidade++;
        if (mostrarVelocidade) {
            alterarTitulo();
        }
    }

    void alterarTitulo() {
        String somStatus = (som) ? "Som ativado, " : "Som desativado, ";
        String velocidadeStatus = (mostrarVelocidade) ? "Nível velocidade: " + nivelVelocidade + ", " : " ";
        setTitle("Quebra Bules   ( " + velocidadeStatus + somStatus + "Valor pontuação: " + valorPontuacao + " )");
    }

    void alterarValorPontuacao(String p) {

        try {
            valorPontuacao = Integer.parseInt(p);
            alterarTitulo();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inteiro inválido, pontuação definida para 100");
            valorPontuacao = 100;
            alterarTitulo();
        }

    }

    void sair() {
        if (JOptionPane.showConfirmDialog(null, "Sair do jogo?", "Sair!",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            System.exit(0);

        }
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        char tecla = e.getKeyChar();

        switch (tecla) {

            case 'p':
                if (Main.objetosOpenGL.getAnimacao().isPaused()) {
                    Main.objetosOpenGL.getAnimacao().resume();
                    pausado = false;
                } else {
                    
                    pausado = true;

                }
                if (Camera.distacia == 1.5) {//evitar pausar no menu
                    Main.objetosOpenGL.getAnimacao().resume();
                }
                break;

            case 's':

                som = !(som);
                alterarTitulo();
                break;

            case 'r':

                reinicarJogo();

                break;

            case 'm':

                Camera.distacia = 1.5f;
                break;

            case 'n':
                mostrarVelocidade = !(mostrarVelocidade);
                alterarTitulo();
                break;

            case 'v':
                alterarValorPontuacao(JOptionPane.showInputDialog(null, "Digite o valor da pontuação por bule", "Valor pontuacao", JOptionPane.PLAIN_MESSAGE));

                break;

            case 'e':

                sair();

                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!Main.objetosOpenGL.getAnimacao().isPaused()) {//entra se o jogo não estiver pausado
            float x = e.getX() / (float) Main.largura
                    * (0.6f - -0.6f) + -0.6f;
            
            
            if (e.getX() > 160 && e.getX() < 640 && e.getY() > 390 && e.getY() < 460) {//entra se clicar fora da area que o teapot passa
                if (((x - Criar.mover) + "").contains("0.0")) {//se acertar no teapot
                    bulesQuebrados++;
                    if (bulesQuebrados >= 3) {
                        aumentarVelocidadeBule();
                        bulesQuebrados = 0;
                    }
                    pontos += valorPontuacao;
                    removerBule();

                    if (som && Camera.distacia != 1.5) {
                        somMartelada.tocar();

                    }
                } else {
                    if (som && Camera.distacia != 1.5) {
                        somErrouMartelada.tocar();
                    }
                    vidas--;

                }
            } else {
                if (som && Camera.distacia != 1.5) {
                    somErrouMartelada.tocar();
                }
                vidas--;
            }

        }

        if (Camera.distacia == 1.5) {//funciona quando estiver no menu

            if (e.getX() > 214 && e.getX() < 593 && e.getY() > 318 && e.getY() < 380) {
                reinicarJogo();
                Camera.distacia = 1.4f;
            }

            if (e.getX() > 214 && e.getX() < 602 && e.getY() > 416 && e.getY() < 481) {
                opcaoTeclado();
            }

            if (e.getX() > 214 && e.getX() < 602 && e.getY() > 512 && e.getY() < 574) {
                opcaoComoJogar();
            }

            if (e.getX() > 214 && e.getX() < 602 && e.getY() > 606 && e.getY() < 670) {
                sair();
            }

        }

    }

    void convert(GL2 gl) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        setCursor(cursorBaterMartelo);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setCursor(cursorMartelo);

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
