import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {
    private Game game;
    private Handler handler;
    private HUD hud;

    public Menu(Game game, Handler handler, HUD hud) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }

    public void render(Graphics g) {
        if (game.gameState == Game.STATE.Menu) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.red);
            g.drawString("Menu", 240, 70);

            g.setFont(fnt2);
            g.drawString("Play", 270, 180);
            g.drawRect(250, 150, 100, 40);

            g.drawString("Help", 270, 280);
            g.drawRect(250, 250, 100, 40);

            g.drawString("Quit", 270, 380);
            g.drawRect(250, 350, 100, 40);
        } else if (game.gameState == Game.STATE.Help) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 18);

            g.setFont(fnt);
            g.setColor(Color.red);
            g.drawString("Help", 240, 70);

            g.setFont(fnt3);
            g.drawString("Controls: w,a,s,d - movement, space - shooting, esc - quit", 65, 250);

            g.setFont(fnt2);
            g.drawString("Back", 520, 420);
            g.drawRect(500, 390, 100, 40);
        } else if (game.gameState == Game.STATE.Over) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("GAME OVER", 190, Game.HEIGHT / 2 - 32);

            g.setFont(fnt2);
            g.drawString("Play again?", 440, 420);
            g.drawRect(430, 390, 180, 40);
        }

    }

    public void tick() {
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        // Play button
        if (mouseOver(mx, my, 250, 150, 100, 40) && game.gameState == Game.STATE.Menu) {
            game.gameState = Game.STATE.Game;
            loadGame();
        }
        // Quit button
        if (mouseOver(mx, my, 250, 350, 100, 40) && game.gameState == Game.STATE.Menu) {
            System.exit(1);
        }
        // Help button
        if (mouseOver(mx, my, 250, 250, 100, 40) && game.gameState == Game.STATE.Menu) {
            game.gameState = Game.STATE.Help;
        }
        // Back button from Help
        if (mouseOver(mx, my, 500, 390, 100, 40) && game.gameState == Game.STATE.Help) {
            game.gameState = Game.STATE.Menu;
        }
        // Try again button
        if (mouseOver(mx, my, 430, 390, 180, 40) && game.gameState == Game.STATE.Over) {
            game.gameState = Game.STATE.Game;
            loadGame();
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else return false;
        } else return false;
    }

    public void loadGame() {
        handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler, "north"));
        handler.addObject(new Enemy(0, 0, ID.Enemy, "north", System.currentTimeMillis(), handler, 0));
        handler.addObject(new Enemy(480, 0, ID.Enemy, "north", System.currentTimeMillis(), handler, 0));
        handler.addObject(new Enemy(0, 640, ID.Enemy, "north", System.currentTimeMillis(), handler, 0));
        handler.addObject(new Enemy(480, 640, ID.Enemy, "north", System.currentTimeMillis(), handler, 0));
        for (int i = 0; i < Game.map.length; i++) {
            for (int j = 0; j < Game.map[0].length; j++) {
                if (Game.map[i][j] == 1) {
                    handler.addObject(new Brick(j * 32, i * 32, ID.Brick, handler, 0));
                }
            }
        }
    }
}
