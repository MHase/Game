package Game;

import java.awt.*;
import java.util.Random;

/**
 * Created by Maciej on 2015-05-06.
 */
public class Player extends GameObject {

    //Random r = new Random();
    Handler handler = new Handler();

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;


    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    public void tick() {
        x += velX;
        y += velY;

        x = (int) Game.clamp(x, 0, Game.WIDTH - 38);
        y = (int) Game.clamp(y, 0, Game.HEIGHT - 61);

        collision();
    }

    private void collision() { //kolizja gracza z czymœ innym
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i); //zbiera wszystkie objecty z gry

            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy) { //kolizja z BasicEnemy, czyli tempObject jest naszym BasicEnemy
                if (getBounds().intersects(tempObject.getBounds())) {
                    //collision code
                    HUD.HEALTH -= 2;
                }
            }

        }
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect((int)x, (int)y, 32, 32);
        g.setColor(Color.red);
        g.drawRect((int)x , (int)y, 32, 32);
    }



}
