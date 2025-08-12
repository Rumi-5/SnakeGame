import javax.swing.*;
import java.awt.*;

public class SnakeGameV1 extends JPanel {
    private int x = 0, y = 50; // Snake head position
    private final int size = 20;

    public SnakeGameV1() {
        Timer timer = new Timer(200, e -> {
            x += size;  // Move right
            if (x > getWidth()) x = 0;  // Wrap around
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillRect(x, y, size, size);  // Draw snake head as green square
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game V1");
        SnakeGameV1 game = new SnakeGameV1();
        frame.add(game);
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}