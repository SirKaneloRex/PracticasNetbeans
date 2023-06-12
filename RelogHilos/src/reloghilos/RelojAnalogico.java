import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalTime;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RelojAnalogico extends JPanel implements Runnable {
    private Thread thread;

    public RelojAnalogico() {
        setBackground(Color.WHITE);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Reloj Analógico");
        RelojAnalogico reloj = new RelojAnalogico();
        frame.add(reloj);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                reloj.stop();
            }
        });
        reloj.start();
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
    }

    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(centerX, centerY) - 5;

        g2.setColor(Color.BLACK);
        g2.fillOval(centerX - 5, centerY - 5, 10, 10);

        LocalTime time = LocalTime.now();
        int hour = time.getHour() % 12;
        int minute = time.getMinute();
        int second = time.getSecond();

        // Dibujar la manecilla de las horas
        int hourLength = (int) (radius * 0.4);
        int hourX = (int) (centerX + hourLength * Math.sin(Math.toRadians((hour * 30) - 90)));
        int hourY = (int) (centerY - hourLength * Math.cos(Math.toRadians((hour * 30) - 90)));
        g2.drawLine(centerX, centerY, hourX, hourY);

        // Dibujar la manecilla de los minutos
        int minuteLength = (int) (radius * 0.6);
        int minuteX = (int) (centerX + minuteLength * Math.sin(Math.toRadians((minute * 6) - 90)));
        int minuteY = (int) (centerY - minuteLength * Math.cos(Math.toRadians((minute * 6) - 90)));
        g2.drawLine(centerX, centerY, minuteX, minuteY);

        // Dibujar la manecilla de los segundos
        int secondLength = (int) (radius * 0.8);
        int secondX = (int) (centerX + secondLength * Math.sin(Math.toRadians((second * 6) - 90)));
        int secondY = (int) (centerY - secondLength * Math.cos(Math.toRadians((second * 6) - 90)));
        g2.setColor(Color.RED);
        g2.drawLine(centerX, centerY, secondX, secondY);
    }
}