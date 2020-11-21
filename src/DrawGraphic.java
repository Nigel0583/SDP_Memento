import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import static javax.swing.JOptionPane.showMessageDialog;

public class DrawGraphic extends JComponent {

    private int X1, Y1, X2, Y2;
    private Graphics2D graphics2D;

    private BufferedImage bufferedImage;

    protected void paintComponent(Graphics graphics) {
        if (bufferedImage == null) {
            bufferedImage = (BufferedImage) createImage(getSize().width, getSize().height);
            graphics2D = (Graphics2D) bufferedImage.getGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }

        graphics.drawImage(bufferedImage, 0, 0, null);
    }

    public DrawGraphic() {
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                X2 = e.getX();
                Y2 = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                X1 = e.getX();
                Y1 = e.getY();

                if (graphics2D != null) {
                    graphics2D.drawLine(X2, Y2, X1, Y1);
                    repaint();
                    X2 = X1;
                    Y2 = Y1;
                }
            }
        });
    }

    public  void save(String fileName) {
        try {
            ImageIO.write(bufferedImage, "PNG", new File(fileName));
        } catch (IOException ex) {
            showMessageDialog(null, "An error occurred saving the file!");
        }
    }

    public void load(String fileName) {
        try {
            bufferedImage = ImageIO.read(new File(fileName));
            graphics2D = bufferedImage.createGraphics();
        } catch (IOException ex) {
            showMessageDialog(null, "An error occurred loading the file!");
        }
    }


    public void clear() {
        graphics2D.setPaint(Color.white);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        graphics2D.setPaint(Color.black);
        repaint();
    }
    public void black() {
        graphics2D.setPaint(Color.black);
    }

    public void red() {
        graphics2D.setPaint(Color.red);
    }

    public void green() {
        graphics2D.setPaint(Color.green);
    }

    public void blue() {
        graphics2D.setPaint(Color.blue);
    }
}
