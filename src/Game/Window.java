package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Maciej on 2015-05-05.
 */
public class Window extends Canvas{

    public Window (int width, int height, String title, Game game) {
        JFrame frame = new JFrame(title);

        //ikonka
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
        game.run();
    }


}
