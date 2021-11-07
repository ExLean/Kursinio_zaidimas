import java.awt.*;

public class Brick extends GameObject{
    Handler handler;

    public Brick(int x, int y, ID id, Handler handler, boolean isHit) {
        super(x, y, id);
        this.handler = handler;
        this.isHit = isHit;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    public void tick() {
        collision();
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Bullet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(tempObject);
                    isHit = true;
                }
            }
        }
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Brick && tempObject.isHit()) {
                handler.object.remove(i);
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(x, y, 32, 32);
        g.setColor(Color.gray);
        g.drawRect(x, y, 32, 32);
    }
}
