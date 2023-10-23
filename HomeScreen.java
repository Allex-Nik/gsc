import java.awt.*;
import java.awt.event.MouseListener;
import javax.swing.*;

public class HomeScreen extends JFrame {
    String gameName = "Galactic Salvage Crew";
    Color backgroundColor = new Color(102, 98, 98);
    Color titleColor = Color.BLUE;
    Color buttonColor = Color.BLUE;

    HomeScreen() {
        JFrame frame = new JFrame("Home Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(backgroundColor);
        frame.add(panel);

        JLabel title = new JLabel(gameName, JLabel.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 40));
        title.setForeground(titleColor);
        panel.add(title, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(100, 100, 100, 100);;
        buttonPanel.setBackground(backgroundColor);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        JLabel start = new JLabel("Start", JLabel.CENTER);
        start.setFont(new Font("Serif", Font.BOLD, 60));
        start.setForeground(buttonColor);
        buttonPanel.add(start);

        start.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                //TODO: Add code to start game
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {}

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {}

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                System.out.println("Mouse entered");
                buttonColor = Color.RED;
                start.setForeground(buttonColor);
                buttonPanel.repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                System.out.println("Mouse exited");
                buttonColor = Color.BLUE;
                start.setForeground(buttonColor);
                buttonPanel.repaint();

            }
        });

    }

    public static void main(String[] args) {
        HomeScreen homeScreen = new HomeScreen();
    }
    
}
