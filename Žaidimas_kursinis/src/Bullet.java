import java.awt.*;

public class Bullet extends GameObject{
    public Bullet(int x, int y, ID id, String direction) {
        super(x, y, id);
        this.direction = direction;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 8, 8);
    }

    public void tick() {
        x += velX;
        y += velY;
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 8, 8);
    }
}
