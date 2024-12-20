// Kelas untuk mengelola ular
public class Snake {
    private int[] x;
    private int[] y;
    private int bodyParts;

    public Snake(int[] x, int[] y, int bodyParts) {
        this.x = x;
        this.y = y;
        this.bodyParts = bodyParts;
    }

    public int[] getX() {
        return x;
    }

    public int[] getY() {
        return y;
    }

    public int getBodyParts() {
        return bodyParts;
    }

    public void setBodyParts(int bodyParts) {
        this.bodyParts = bodyParts;
    }
}