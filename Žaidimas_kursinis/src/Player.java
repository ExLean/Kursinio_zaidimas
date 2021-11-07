import java.awt.*;

public class Player extends GameObject{
    Handler handler;

    public Player(int x, int y, ID id, Handler handler, String direction) {
        super(x, y, id);
        this.handler = handler;
        this.direction = direction;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    public void tick() {
        x += velX;
        y += velY;
        x = Game.clamp(x, 0, Game.WIDTH - 47);
        y = Game.clamp(y, 0, Game.HEIGHT - 70);
        collision();
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Bullet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 1;
                    handler.object.remove(i);
                }
            }
            if (tempObject.getId() == ID.Brick) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    this.velX = 0;
                    this.velY = 0;
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 32, 32);
    }
}
