import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HUDTest {

    @Test
    void getHEALTH() {
        HUD h = new HUD();
        assertEquals(3, h.getHEALTH());
    }

    @Test
    void setHEALTH() {
        HUD h = new HUD();
        h.setHEALTH(3);
        assertEquals(3, h.getHEALTH());
    }
}