package Game;

import java.awt.*;


/**
 * Created by Maciej on 2015-05-09.
 */
public class BasicEnemy extends GameObject {

    private Handler handler;

    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        velX = 5;
        velY = 5;
        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT - 48) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 24) velX *= -1;

        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.red, 16, 16, 0.02f, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}
