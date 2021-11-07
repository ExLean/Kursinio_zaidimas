import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class KeyInput extends KeyAdapter {
    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Player) {
                //key events for player 1
                if (key == KeyEvent.VK_W) {
                    tempObject.setVelY(-3);
                    tempObject.setDirection("north");
                }
                if (key == KeyEvent.VK_S) {
                    tempObject.setVelY(3);
                    tempObject.setDirection("south");
                }
                if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(3);
                    tempObject.setDirection("east");
                }
                if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(-3);
                    tempObject.setDirection("west");
                }
                if (key == KeyEvent.VK_SPACE) {
                    switch (tempObject.getDirection()) {
                        case "north" -> tempObject(tempObject, "north").setVelY(-5);
                        case "south" -> tempObject(tempObject, "south").setVelY(5);
                        case "east" -> tempObject(tempObject, "east").setVelX(5);
                        case "west" -> tempObject(tempObject, "west").setVelX(-5);
                    }
                }
            }
        }
        if (key == KeyEvent.VK_ESCAPE) System.exit(1);
    }

    public GameObject tempObject(GameObject tempObject, String text) {
        int xi = 0, yi = 0;
        if (Objects.equals(text, "north")) {
            xi = 13;
            yi = -15;
        } else if (Objects.equals(text, "south")) {
            xi = 13;
            yi = 37;
        } else if (Objects.equals(text, "east")) {
            xi = 37;
            yi = 13;
        } else {
            xi = -15;
            yi = 13;
        }
        GameObject tempObject2 = new Bullet(tempObject.getX() + xi, tempObject.getY() + yi, ID.Bullet, text);
        handler.addObject(tempObject2);
        return tempObject2;
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Player) {
                //key events for player 1
                if (key == KeyEvent.VK_W) tempObject.setVelY(0);
                if (key == KeyEvent.VK_S) tempObject.setVelY(0);
                if (key == KeyEvent.VK_D) tempObject.setVelX(0);
                if (key == KeyEvent.VK_A) tempObject.setVelX(0);
            }
        }
    }
}
