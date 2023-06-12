import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FractalDeKoch extends JPanel {
//      private int nivelRecursion; // Nivel de recursión para el fractal
    private int NIVEL_RECURSION; // Nivel de recursión para el fractal
    private final int ANCHO_VENTANA = 800;
    private final int ALTO_VENTANA = 600;
    private final double ANCHO_BASE = 600.0;
    private final double ALTURA = Math.sqrt(3.0) * ANCHO_BASE / 2.0;

    public FractalDeKoch(int nivelRecursion) {
       
        this.NIVEL_RECURSION = nivelRecursion;
        JFrame ventana = new JFrame("Fractal de Koch");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(ANCHO_VENTANA, ALTO_VENTANA);
        ventana.setResizable(false);
        ventana.add(this);
        ventana.setVisible(true);
        ventana.setResizable(true);
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        // Puntos iniciales del fractal
        int x1 = ANCHO_VENTANA / 2 - (int) (ANCHO_BASE / 2);
        int y1 = ALTO_VENTANA / 2 + (int) (ALTURA / 2);
        int x2 = ANCHO_VENTANA / 2 + (int) (ANCHO_BASE / 2);
        int y2 = y1;
        int x3 = ANCHO_VENTANA / 2;
        int y3 = ALTO_VENTANA / 2 - (int) (ALTURA / 2);

        // Llamada inicial a la función recursiva
        dibujarFractal(g2d, NIVEL_RECURSION, x1, y1, x2, y2);
        dibujarFractal(g2d, NIVEL_RECURSION, x2, y2, x3, y3);
        dibujarFractal(g2d, NIVEL_RECURSION, x3, y3, x1, y1);
    }

    private void dibujarFractal(Graphics2D g2d, int nivel, int x1, int y1, int x5, int y5) {
        if (nivel == 0) {
            g2d.drawLine(x1, y1, x5, y5);
        } else {
            int deltaX = x5 - x1;
            int deltaY = y5 - y1;
            int x2 = x1 + deltaX / 3;
            int y2 = y1 + deltaY / 3;
            int x3 = (int) ((x1 + x5) / 2 + Math.sqrt(3) * (y1 - y5) / 6);
            int y3 = (int) ((y1 + y5) / 2 + Math.sqrt(3) * (x5 - x1) / 6);
            int x4 = x1 + 2 * deltaX / 3;
            int y4 = y1 + 2 * deltaY / 3;

            dibujarFractal(g2d, nivel - 1, x1, y1, x2, y2);
            dibujarFractal(g2d, nivel - 1, x2, y2, x3, y3);
            dibujarFractal(g2d, nivel - 1, x3, y3, x4, y4);
            dibujarFractal(g2d, nivel - 1, x4, y4, x5, y5);
        }
    }

    public static void main(String[] args) {
        int nivelRecursion = 4; // Nivel de recursión para el fractal (puedes ajustarlo)
        new FractalDeKoch(nivelRecursion);
        
    }
}