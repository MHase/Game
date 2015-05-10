package Game;

import java.util.Random;

/**
 * Created by Maciej on 2015-05-09.
 */
public class Spawn {

    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {
        scoreKeep++;

        if (scoreKeep >= 200) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            if (hud.getLevel() == 2) {
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.FastEnemy, handler));
            }
            if (hud.getLevel() == 3) {
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
            }
            if (hud.getLevel() == 4) {
                handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.SmartEnemy, handler));
            }
            if (hud.getLevel() == 5) {
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
            }
            if (hud.getLevel() == 6) {
                handler.clearEnemies();
                handler.addObject(new BossEnemy((Game.WIDTH) / 2 - 48, -120, ID.BossEnemy, handler));
            }

        }
    }
}

