import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Game extends JPanel implements ActionListener {

    private final int SIZE_X = 1900;
    private final int SIZE_Y = 1040;
    private final int DOT_SIZE = 48;
    private final int ALL_DOTS = 30;
    private Image dot, body, apple;
    private int appleX, appleY;
    private int x[] = new int[ALL_DOTS];
    private int y[] = new int[ALL_DOTS];
    private int dots;
    private Timer timer;
    public boolean left = false, right = true, up = false, down = false;
    private boolean inGame = true;
    public static Sound gameSound, eatingSound, deathSound;
    Image background = new ImageIcon("src/Textures/Background.png").getImage();

    public Game() {
        setSize(1920,1080);
        setBackground(null);
        loadImage();
        initGame();
        addKeyListener(new KeyListener());
        requestFocus();
    }

    class KeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_A | key == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_D | key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_W | key == KeyEvent.VK_UP  && !down) {
                up = true;
                left = false;
                right = false;
            }
            if (key == KeyEvent.VK_S | key == KeyEvent.VK_DOWN  && !up) {
                down = true;
                left = false;
                right = false;
            }
        }
    }

    public void initGame() {
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 48 - i*DOT_SIZE;
            y[i] = 48;
        }
        timer = new Timer(200, this);
        timer.start();
        createApple();
        gameSound = new Sound("src/SoundFile/GameSound.wav",0.75);
        gameSound.play();
        gameSound.setVolume();
        gameSound.repeat();
    }

    public void createApple() {
        appleX = new Random().nextInt(40)*DOT_SIZE;
        appleY = new Random().nextInt(22)*DOT_SIZE;
    }

    public void loadImage() {
        ImageIcon iia = new ImageIcon("src/Textures/Apple.png");
        apple = iia.getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
        ImageIcon iid = new ImageIcon("src/Textures/SlimeHead.png");
        dot = iid.getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
        ImageIcon iid2 = new ImageIcon("src/Textures/SlimeBody.png");
        body = iid2.getImage().getScaledInstance(48,48,Image.SCALE_DEFAULT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background,0,0,null);

        if (inGame) {
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 1; i < dots; i++) {
                g.drawImage(dot,x[0],y[0],this);
                g.drawImage(body,x[i],y[i],this);
            }
        } else {
            StrFont mine = new StrFont();
            String str = "Game over";
            g.setColor(Color.white);
            g.setFont(mine.mine_font);
            g.drawString(str, 880, 500);
        }

    }

    public void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if (left) {x[0] -= DOT_SIZE;}
        if (right) {x[0] += DOT_SIZE;}
        if (up) {y[0] -= DOT_SIZE;}
        if (down) {y[0] += DOT_SIZE;}
    }

    public void checkApple(){
        if (x[0] == appleX && y[0] == appleY) {
            dots++;
            createApple();
            eatingSound = new Sound("src/SoundFile/Nom-nom.wav",0.8);
            eatingSound.play();
            eatingSound.setVolume();
        }
    }

    public void checkCollisions() {
        deathSound = new Sound("src/SoundFile/SlimeDeath.wav",1);
        for (int i = dots; i > 0; i--) {
            if (i > 3 && x[0] == x[i] && y[0] == y[i]) {
                deathSound.play();
                inGame = false;
            }
        }
            if (x[0] > SIZE_X) {
                deathSound.play();
                inGame = false;
            }
            if (x[0] < 0) {
                deathSound.play();
                inGame = false;
            }
            if (y[0] > SIZE_Y) {
                deathSound.play();
                inGame = false;
            }
            if (y[0] < 0) {
                deathSound.play();
                inGame = false;
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            move();
            checkApple();
            checkCollisions();
        } repaint();
    }

}
