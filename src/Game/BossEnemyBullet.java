package Game;

import java.awt.*;
import java.util.Random;

/**
 * Created by Maciej on 2015-05-09.
 */
public class BossEnemyBullet extends GameObject {

    private Handler handler;
    private Random r = new Random();

    public BossEnemyBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        velX = (r.nextInt(5 - -5) + (-5));
        velY = 5;
        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        //if(y <= 0 || y >= Game.HEIGHT - 48) velY *= -1;
        //if(x <= 0 || x >= Game.WIDTH - 24) velX *= -1;

        if(y >= Game.HEIGHT) handler.removeObject(this);

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.white, 16, 16, 0.02f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}
