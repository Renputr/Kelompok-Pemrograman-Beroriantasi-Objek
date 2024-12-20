// Kelas untuk panel game yang berisi logika permainan
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    // Lebar layar permainan
    private final int SCREEN_WIDTH = 600;
    // Tinggi layar permainan
    private final int SCREEN_HEIGHT = 600;
    // Ukuran unit permainan
    private final int UNIT_SIZE = 25;
    // Jumlah unit permainan
    private final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    // Delay untuk timer
    private final int DELAY = 75;
    // Array untuk menyimpan posisi x dari ular
    private final int[] x = new int[GAME_UNITS];
    // Array untuk menyimpan posisi y dari ular
    private final int[] y = new int[GAME_UNITS];
    // Jumlah bagian tubuh ular
    private int bodyParts = 6;
    // Jumlah apel yang dimakan
    private int applesEaten;
    // Posisi x dari apel
    private int appleX;
    // Posisi y dari apel
    private int appleY;
    // Arah pergerakan ular
    private char direction = 'R';
    // Status permainan
    private boolean running = false;
    // Timer untuk mengatur kecepatan permainan
    private Timer timer;
    // Objek ular
    private Snake snake;

    GamePanel() {
        // Mengatur ukuran panel
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        // Mengatur warna latar belakang panel
        this.setBackground(Color.black);
        // Mengatur agar panel dapat menerima input dari keyboard
        this.setFocusable(true);
        // Menambahkan key listener ke panel
        this.addKeyListener(this);
        // Memulai permainan
        startGame();
    }

    public void startGame() {
        // Membuat objek ular
        snake = new Snake(x, y, bodyParts);
        // Membuat apel baru
        newApple();
        // Mengatur status permainan menjadi berjalan
        running = true;
        // Membuat timer dengan delay yang telah ditentukan
        timer = new Timer(DELAY, this);
        // Memulai timer
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Menggambar komponen permainan
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            // Menggambar apel
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // Menggambar ular
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
        } else {
            // Menampilkan pesan game over
            gameOver(g);
        }
    }

    public void newApple() {
        // Mengatur posisi apel secara acak
        appleX = (int) (Math.random() * (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = (int) (Math.random() * (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move() {
        // Menggerakkan bagian tubuh ular
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        // Menggerakkan kepala ular sesuai arah
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkApple() {
        // Memeriksa apakah kepala ular bertabrakan dengan apel
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions() {
        // Memeriksa apakah kepala ular bertabrakan dengan tubuhnya sendiri
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }

        // Memeriksa apakah kepala ular bertabrakan dengan dinding
        if (x[0] < 0 || x[0] >= SCREEN_WIDTH || y[0] < 0 || y[0] >= SCREEN_HEIGHT) {
            running = false;
        }

        // Menghentikan timer jika permainan berakhir
        if (!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        // Menampilkan pesan game over
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Mengatur arah pergerakan ular berdasarkan input dari keyboard
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (direction != 'R') {
                    direction = 'L';
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 'L') {
                    direction = 'R';
                }
                break;
            case KeyEvent.VK_UP:
                if (direction != 'D') {
                    direction = 'U';
                }
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 'U') {
                    direction = 'D';
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Tidak digunakan
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Tidak digunakan
    }
}