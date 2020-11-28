import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class DrawGraphic extends JComponent {
    private final Originator originator;
    /*
   Learned about Graphics2D from:
   https://www.tutorialspoint.com/awt/awt_graphics2d_class.htm
   https://www.programcreek.com/java-api-examples/?api=java.awt.Graphics2D
   https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics2D.html
    */
    public Graphics2D graphics2D;
    protected BufferedImage backgroundImage;
    private BufferedImage bufferedImage;
    private int currentX, currentY, oldX, oldY;
    private MouseMotionListener motion;
    private MouseListener listener;

    public DrawGraphic() {
        originator = new Originator();
        defaultListener();
    }

    public void defaultListener() {
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                saveToOriginator(bufferedImage);
                oldX = e.getX();
                oldY = e.getY();
            }
        });

        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                saveToOriginator(bufferedImage);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();

                if (graphics2D != null) {
                    graphics2D.drawLine(oldX, oldY, currentX, currentY);
                }
                repaint();
                oldX = currentX;
                oldY = currentY;

            }
        });
        addMouseListener(listener);
        addMouseMotionListener(motion);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        if (bufferedImage == null) {
            bufferedImage = (BufferedImage) createImage(getSize().width, getSize().height);
            graphics = bufferedImage.getGraphics();
            graphics2D = (Graphics2D) bufferedImage.getGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        graphics.drawImage(bufferedImage, 0, 0, null);
    }

    private void setImage(Image image) {
        graphics2D = (Graphics2D) image.getGraphics();
        bufferedImage = (BufferedImage) image;
        repaint();
    }

    public void save(String file) {
        try {
            ImageIO.write(bufferedImage, "PNG", new File(file));
        } catch (IOException ex) {
            showMessageDialog(null, "An error occurred saving the file!");
        }
    }

    public void load(String file) {
        try {
            bufferedImage = ImageIO.read(new File(file));
            graphics2D = bufferedImage.createGraphics();
        } catch (IOException ex) {
            showMessageDialog(null, "An error occurred loading the file!");
        }
    }

    public void colorPicker(Color color) {
        graphics2D.setPaint(color);
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

    public void clear() {
        if (backgroundImage != null) {
            setImage(copyOfImage(backgroundImage));
        } else {
            graphics2D.setPaint(Color.white);
            graphics2D.fillRect(0, 0, getSize().width, getSize().height);
            graphics2D.setPaint(Color.black);
            repaint();
        }
    }

    public Originator getOriginator() {
        return originator;
    }

    private BufferedImage copyOfImage(Image image) {
        BufferedImage copyOfImage = (BufferedImage) createImage(getSize().width, getSize().height);
        Graphics graphics = copyOfImage.createGraphics();
        graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        return copyOfImage;
    }

    private void saveToOriginator(BufferedImage image) {
        getOriginator().setAndStoreState(copyOfImage(image));
    }

    public void getUndoImg() {
        setImage(getOriginator().getImage());
    }

    public void getRedoImg() {
        setImage(getOriginator().getImage());
    }
}
