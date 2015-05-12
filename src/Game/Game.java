package Game;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 * Created by Maciej on 2015-05-05.
 */
public class Game extends Canvas {
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    //public static final int WIDTH = 1366, HEIGHT = 768;

    private Thread thread;
    private boolean running = false;

    private Random r;
    private Handler handler;
    private HUD hud = new HUD();
    private Spawn spawner;

    private Menu menu;

    public enum STATE {
        Menu,
        Help,
        Game,
        End
    }

    public static STATE gameState = STATE.Menu;

    public Game() {
        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, handler, hud);
        r = new Random();
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);

        spawner = new Spawn(handler, hud);

        if(gameState == STATE.Game) {
            //handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler)); //tworzenie/rysowanie gracza
        } else {
            for(int  i =0; i < 20; i ++) {
                handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
            }
        }

        new Window(WIDTH, HEIGHT, "Let's build a game!", this);
    }

    public synchronized void start() {
        thread = new Thread(String.valueOf(this));
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }



    private void tick() {
        handler.tick();
        if(gameState == STATE.Game){
            hud.tick();
            spawner.tick();

            if(hud.HEALTH <= 0) {
                hud.HEALTH = 100;
                gameState = STATE.End;
                handler.clearEnemies();
                for(int  i =0; i < 20; i ++)
                    handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
            }
        } else if(gameState == STATE.Menu || gameState == STATE.End) {
            menu.tick();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
           return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);


        handler.render(g); // generuje gracza

        if(gameState == STATE.Game) {
           hud.render(g);
        } else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
            menu.render(g);
        }


        g.dispose();
        bs.show();
    }

    public static float clamp(float var, float min, float max) {
        if(var >= max)
            return var = max;
        else if(var <= min)
            return var = min;
        else
            return var;
    }

    public static void main (String [] args) {
        new Game();
    }

}
