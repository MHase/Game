package Game;

import com.sun.prism.paint.Gradient;

import java.awt.*;

/**
 * Created by Maciej on 2015-05-09.
 */
public class Trail extends GameObject {

    private float alpha = 1;
    private float life;

    private Handler handler;
    private Color color;

    private int width, height;

    //life = 0.001 - 0.1

    public Trail(int x, int y, ID id, Color color, int width, int height, float life, Handler handler) {
        super(x, y, id);
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
        this.handler = handler;
    }

    public void tick() {
        if (alpha > life) {
            alpha -= (life - 0.0001);
        }
        else handler.removeObject(this);
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));

        g.setColor(color);
        g.fillRect((int)x, (int)y, width, height);

        g2d.setComposite(makeTransparent(1));
    }

    private AlphaComposite makeTransparent(float Alpha) {
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type, Alpha);
    }

    public Rectangle getBounds() {
        return null;
    }
}
