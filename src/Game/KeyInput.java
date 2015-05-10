package Game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Maciej on 2015-05-08.
 */
public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean[] keyDown = new boolean[4];

    public KeyInput(Handler handler) {
        this.handler = handler;
        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        int vel = 5;

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);


            if(tempObject.getId() == ID.Player) {
                //key event for player 1

                if(key == KeyEvent.VK_W) { tempObject.setVelY(-vel); keyDown[0] = true; }
                if(key == KeyEvent.VK_S) { tempObject.setVelY(vel);  keyDown[1] = true; }
                if(key == KeyEvent.VK_A) { tempObject.setVelX(-vel);  keyDown[2] = true; }
                if(key == KeyEvent.VK_D) { tempObject.setVelX(vel);  keyDown[3] = true; }
            }

        }

        if(key == KeyEvent.VK_ESCAPE) System.exit(1);

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player) {
                //key event for player 1

                if(key == KeyEvent.VK_W)  keyDown[0] = false; //tempObject.setVelY(0);
                if(key == KeyEvent.VK_S)  keyDown[1] = false; //tempObject.setVelY(0);
                if(key == KeyEvent.VK_A)  keyDown[2] = false; //tempObject.setVelX(0);
                if(key == KeyEvent.VK_D)  keyDown[3] = false; //tempObject.setVelX(0);

                //vertical movement
                if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
                //horizontal movement
                if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
            }

        }
    }
}
