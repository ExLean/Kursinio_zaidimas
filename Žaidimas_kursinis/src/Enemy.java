import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class Enemy extends GameObject {
    Handler handler;

    public Enemy(int x, int y, ID id, String direction, long lastTurn, Handler handler, int isHit) {
        super(x, y, id);
        this.handler = handler;
        this.direction = direction;
        this.lastTurn = lastTurn;
        this.isHit = isHit;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    public void tick() {
        x += velX;
        y += velY;
        x = Game.clamp(x, 0, Game.WIDTH - 47);
        y = Game.clamp(y, 0, Game.HEIGHT - 70);
        if (System.currentTimeMillis() - lastTurn >= 1200) {
            if (new Random().nextInt(4) == 0) {
                this.velY = -3;
                this.direction = "north";
            }
            if (new Random().nextInt(4) == 1) {
                this.velY = 3;
                this.direction = "south";
            }
            if (new Random().nextInt(4) == 2) {
                this.velX = 3;
                this.direction = "east";
            }
            if (new Random().nextInt(4) == 3) {
                this.velX = -3;
                this.direction = "west";
            }
            if (new Random().nextInt(10) >= 1) {
                switch (this.direction) {
                    case "north" -> tempObject(this.direction).setVelY(-5);
                    case "south" -> tempObject(this.direction).setVelY(5);
                    case "east" -> tempObject(this.direction).setVelX(5);
                    case "west" -> tempObject(this.direction).setVelX(-5);
                }
            }
            lastTurn = System.currentTimeMillis();
        }
        collision();
    }

    public GameObject tempObject(String text) {
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
        GameObject tempObject2 = new Bullet(this.x + xi, this.y + yi, ID.Bullet, text);
        handler.addObject(tempObject2);
        return tempObject2;
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Bullet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(tempObject);
                    isHit++;
                }
            }
            if (tempObject.getId() == ID.Brick) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    this.velX = 0;
                    this.velY = 0;
                }
            }
        }
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Enemy && tempObject.isHit >= 3) {
                handler.object.remove(i);
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 32, 32);
    }
}
