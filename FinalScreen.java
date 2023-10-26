import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.*;

public class FinalScreen {
    private String gameName = "Galactic Salvage Crew";
    private String feedback;
    Color backgroundColor = new Color(102, 98, 98);
    Color titleColor = Color.BLUE;
    Color messageColor = Color.BLUE;
    Color buttonColor = Color.BLUE;


    FinalScreen(boolean isVictory) {

        if (isVictory) {
            feedback = "Congratulations! You won.";
        } else {
            feedback = "Sorry, you lost. Try again.";
        }

        JFrame frame = new JFrame("Final Screen");
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
        panel.add(title, BorderLayout.NORTH);

        JLabel message = new JLabel(feedback, JLabel.CENTER);
        message.setFont(new Font("Serif", Font.BOLD, 40));
        message.setForeground(messageColor);
        panel.add(message, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(100, 100, 100, 100);
        buttonPanel.setBackground(backgroundColor);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        JLabel restart = new JLabel("Restart", JLabel.CENTER);
        restart.setFont(new Font("Serif", Font.BOLD, 60));
        restart.setForeground(buttonColor);
        buttonPanel.add(restart);

        restart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    frame.dispose();
                    new MainGameScreen();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                buttonColor = Color.RED;
                restart.setForeground(buttonColor);
                buttonPanel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonColor = Color.BLUE;
                restart.setForeground(buttonColor);
                buttonPanel.repaint();
            }
        });
    }
}
