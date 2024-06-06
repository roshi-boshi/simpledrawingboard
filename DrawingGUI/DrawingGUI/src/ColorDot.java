import java.awt.*;

public class ColorDot {
    private Color color;
    private int x;

    private int y;

    public ColorDot(int x, int y, Color color){

        this.x = x;
        this.y = y;
        this.color = color;

    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
