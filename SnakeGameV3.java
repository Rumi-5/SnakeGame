import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGameV3 extends JPanel implements ActionListener, KeyListener {
    private final int size = 20;
    private final int width = 400, height = 400;
    private LinkedList<Point> snake = new LinkedList<>();
    private Point food;
    private int dx = size, dy = 0;
    private Timer timer;
    private Random rand = new Random();

    public SnakeGameV3() {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        snake.add(new Point(100, 100));
        snake.add(new Point(80, 100));
        snake.add(new Point(60, 100));

        spawnFood();

        timer = new Timer(200, this);
        timer.start();
    }

    private void spawnFood() {
        int fx = rand.nextInt(width / size) * size;
        int fy = rand.nextInt(height / size) * size;
        food = new Point(fx, fy);
    }

    public void actionPerformed(ActionEvent e) {
        Point head = snake.getFirst();
        Point newHead = new Point(head.x + dx, head.y + dy);

        if (newHead.x < 0) newHead.x = width - size;
        if (newHead.x >= width) newHead.x = 0;
        if (newHead.y < 0) newHead.y = height - size;
        if (newHead.y >= height) newHead.y = 0;

        snake.addFirst(newHead);

        if (newHead.equals(food)) {
            spawnFood();  
        } else {
            snake.removeLast(); 
        }

        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        g.fillOval(food.x, food.y, size, size);

        g.setColor(Color.GREEN);
        for (Point p : snake) {
            g.fillRect(p.x, p.y, size, size);
        }
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
        JFrame frame = new JFrame("Snake Game V3");
        SnakeGameV3 game = new SnakeGameV3();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }   
}