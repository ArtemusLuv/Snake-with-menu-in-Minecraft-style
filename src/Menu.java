import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu {

    JFrame frame; // the main frame on which everything is superimposed
    StrFont fontForMenu; // custom font (check realization in class StrFont)
    JButton play, close;
    ImageIcon icon, background, back_pressed, logo, back;
    JLabel background_label, title, down_title;
    private final int SCRN_WIDTH = 1920;
    private final int SCRN_HEIGHT = 1080;
    public static Sound buttonSound, menuSound; // sound effect (check realization in class Sound)

    public Menu() {
        fontForMenu = new StrFont();

        buttonSound = new Sound("src/SoundFile/Button.wav",1);
        menuSound = new Sound("src/SoundFile/MenuSound.wav",0.8);
        menuSound.play();
        menuSound.setVolume();
        menuSound.repeat(); // cyclic sound playback

        icon = new ImageIcon("src/Textures/IconApp.png");

        frame = new JFrame("Mine snake");
        frame.setIconImage(icon.getImage()); // set icon app on Windows bar
        frame.setSize(SCRN_WIDTH,SCRN_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // close the app on the cross
        frame.setLocationRelativeTo(null); // layout in center
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // fullscreen with Windows bar
        frame.setResizable(false); // turn off stretching
        background = new ImageIcon("src/Textures/Background.png"); // create background
        // recreate background with new parameters width and height
        background.setImage(background.getImage().getScaledInstance(SCRN_WIDTH,SCRN_HEIGHT,Image.SCALE_AREA_AVERAGING));
        background_label = new JLabel(background); // set main background in label
        background_label.setSize(SCRN_WIDTH,SCRN_HEIGHT);
        logo = new ImageIcon("src/Textures/GameLogo.png");
        title = new JLabel(logo); // add logo format png in label
        title.setBounds(SCRN_WIDTH/2 - logo.getIconWidth()/2,120,logo.getIconWidth(),logo.getIconHeight());

        back = new ImageIcon("src/Textures/ButtonBackground.png");
        back.setImage(back.getImage().getScaledInstance(800,80,Image.SCALE_DEFAULT));
        back_pressed = new ImageIcon("src/Textures/PressedButton.png");
        back_pressed.setImage(back_pressed.getImage().getScaledInstance(800,80,Image.SCALE_DEFAULT));

        // button Play
        play = new JButton("Play");
        play.setBounds(560,460,800,80);
        play.setRolloverIcon(back_pressed); // background is changing when you aim your mose cursor on button
        play.setIcon(back); // set default background
        play.setHorizontalTextPosition(JButton.CENTER); // set text layout
        play.setFont(fontForMenu.mine_font); // set custom font
        play.setForeground(Color.white); // set color font
        play.setBorderPainted(false); // turn off the stroke of the button

        // button Close
        close = new JButton ("Quit Game");
        close.setBounds(560,556,800,80);
        close.setRolloverIcon(back_pressed); // background is changing when you aim your mose cursor on button
        close.setIcon(back);
        close.setHorizontalTextPosition(JButton.CENTER); // set text layout
        close.setFont(fontForMenu.mine_font); // set custom font
        close.setForeground(Color.white); // set color font
        close.setBorderPainted(false); // turn off the stroke of the button

        // label with the name and developer
        down_title = new JLabel("Mine Snake 1.0 by Artemus");
        down_title.setFont(fontForMenu.mine_font.deriveFont(24f)); // add custom font and resize him on 24
        down_title.setForeground(Color.white); // set color font
        down_title.setBounds(8,1012,320,24); // set position



        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSound.playSound(); // button sound
                buttonSound.setVolume();
                menuSound.stop(); // turn off menu sound
                background_label.setVisible(false); // turn off menu
                Game games = new Game(); // create game
                frame.add(games); // add game in frame
                games.requestFocus(); // request focus on game
            }
        });

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSound.playSound(); // button sound
                buttonSound.setVolume();
                System.out.println("\nGAME WAS CLOSING");
                System.exit(0); // method for close app
            }
        });


        frame.add(background_label); // add main background in frame
        background_label.add(title); // add logo in background
        background_label.add(play); // add button Play in background
        background_label.add(close); // add button Close in background
        background_label.add(down_title); // add label with the name and developer in background
        frame.setVisible(true); // display frame

    }

}
