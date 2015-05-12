package Game;

import java.awt.*;
import java.util.Random;

/**
 * Created by Maciej on 2015-05-09.
 */
public class PlayerBullets extends GameObject {

    private Handler handler;

    public PlayerBullets(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        velX = 0;
        velY = -5;
        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 4, 4);
    }

    public void tick() {
        x += velX;
        y += velY;


        if(y <= 0) handler.removeObject(this);

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.red, 4, 4, 0.02f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 4, 4);
    }
}
