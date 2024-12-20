// Kelas utama untuk frame game
import javax.swing.JFrame;

public class GameFrame extends JFrame {
    GameFrame() {
        // Menambahkan panel game ke frame
        this.add(new GamePanel());
        // Mengatur judul frame
        this.setTitle("Snake Game");
        // Mengatur operasi default saat frame ditutup
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Mengatur agar frame tidak dapat diubah ukurannya
        this.setResizable(false);
        // Mengatur ukuran frame agar sesuai dengan komponen di dalamnya
        this.pack();
        // Menampilkan frame
        this.setVisible(true);
        // Menempatkan frame di tengah layar
        this.setLocationRelativeTo(null);
    }

    // Fungsi main untuk memulai game
    public static void main(String[] args) {
        new GameFrame();
    }
}