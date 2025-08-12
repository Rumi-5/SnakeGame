import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SnakeGameV2 extends JPanel implements ActionListener, KeyListener {
    private int x = 100, y = 100;
    private final int size = 20;
    private int dx = size, dy = 0;  
    private Timer timer;

    public SnakeGameV2() {
        timer = new Timer(200, this);
        timer.start();
        setFocusable(true);
        addKeyListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        x += dx;
        y += dy;

        if (x < 0) x = getWidth() - size;
        if (x >= getWidth()) x = 0;
        if (y < 0) y = getHeight() - size;
        if (y >= getHeight()) y = 0;

        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(x, y, size, size);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT && dx == 0) {
            dx = -size; dy = 0;
        } else if (key == KeyEvent.VK_RIGHT && dx == 0) {
            dx = size; dy = 0;
        } else if (key == KeyEvent.VK_UP && dy == 0) {
            dx = 0; dy = -size;
        } else if (key == KeyEvent.VK_DOWN && dy == 0) {
            dx = 0; dy = size;
        }
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game V2");
        SnakeGameV2 game = new SnakeGameV2();
        frame.add(game);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}