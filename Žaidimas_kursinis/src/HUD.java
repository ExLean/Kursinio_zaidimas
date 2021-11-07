import java.awt.*;

public class HUD {

    public static int HEALTH = 3;

    public void tick() {
        HEALTH = Game.clamp(HEALTH, 0 , 3);
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 198, 32);
        g.setColor(Color.green);
        g.fillRect(15, 15, HEALTH * 66, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 198, 32);
    }

    public static int getHEALTH() {
        return HEALTH;
    }

    public static void setHEALTH(int HEALTH) {
        HUD.HEALTH = HEALTH;
    }
}
