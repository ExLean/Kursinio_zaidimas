import java.awt.*;

public class Brick extends GameObject{
    Handler handler;

    public Brick(int x, int y, ID id, Handler handler, int isHit) {
        super(x, y, id);
        this.handler = handler;
        this.isHit = isHit;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    } // Gets object's bounds

    public void tick() {
        collision();
    } // Every tick checks if brick wall has been hit

    private void collision() { // Checks if bullet hit a brick wall, if yes then removes that wall and bullet
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Bullet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(tempObject);
                    isHit++;
                }
            }
        }
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Brick && tempObject.isHit >= 1) {
                handler.object.remove(i);
            }
        }
    }

    public void render(Graphics g) { // Draws the brick walls
        g.setColor(Color.orange);
        g.fillRect(x, y, 32, 32);
        g.setColor(Color.gray);
        g.drawRect(x, y, 32, 32);
    }
}
