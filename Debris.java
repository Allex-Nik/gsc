import java.awt.*;

public class Debris {
    int init_x, init_y, fin_x, fin_y;
    int x, y;
    int radius = 10;
    int speed = 1;
    int pointsWorth;
    final int SCREEN_WIDTH = 800;
    final int SCREEN_HEIGHT = 600;

    int m;
    int c;
    
    public Debris(int init_x, int init_y, int fin_x, int fin_y) {
        this.init_x = init_x;
        this.init_y = init_y;
        this.fin_x = fin_x;
        this.fin_y = fin_y;
        this.x = init_x;
        this.y = init_y;

        //Trajectory
        //y = mx + c

        m = (fin_y - init_y) / (fin_x - init_x);
        c = init_y - (m * init_x);
    }

    public void move() {
        y += speed;
        try {
            x = (y - c) / m;
        } catch (Exception e) {
            x = (y - c) / (m + 1);
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, radius, radius);
    }

    public int getPoints() {
        return pointsWorth;
    }
}
