import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
     * Comment.
     */
public class MainGameScreen extends JPanel {
    
    int score;
    private int START_AMUNITION;
    private SpaceShip spaceShip;
    private Debris debris;
    private List<Projectile> projectiles = new ArrayList<>();
    private  final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGHT = 600;
    JFrame frame;

   

    /**
     * Comment.
     */
    public MainGameScreen() {
        Random random = new Random();
        frame = new JFrame();
        this.setBackground(Color.BLACK);

        spaceShip = new SpaceShip(300, 530, 50, 50);
        debris = new Debris(random.nextInt(SCREEN_WIDTH), 0, random.nextInt(SCREEN_WIDTH), SCREEN_HEIGHT);

        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setUndecorated(true);
        frame.setVisible(true);

        

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int mouseX = e.getX();
                spaceShip.setX(mouseX - spaceShip.getWidth() / 2);
                repaint();
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Projectile projectile = spaceShip.fire();
                    projectiles.add(projectile);
                }
            }
        });

        Timer gameLoop = new Timer((1000 / 60), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProjectiles();
                updateDebris();
            }
        });

        gameLoop.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        spaceShip.draw(g);
        for (Projectile p : projectiles) {
            p.draw(g);
        }
        debris.draw(g);
    }

    public void updateProjectiles() {
        for (Projectile p : projectiles) {
            p.move();
        }
        repaint();
    }

    public void updateDebris() {
        debris.move();
        repaint();
    }


    public static void main(String[] args) {
        new MainGameScreen();
    }
}