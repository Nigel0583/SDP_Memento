import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DrawerGUI extends JFrame {
    DrawGraphic drawGraphic;
    JPanel pnDrawer;
    JButton btSave, btLoad, btBlack, btClear, btRed, btGreen, btBlue;
    private File file;
    private int saveCounter = 0;
    private final JLabel lbFileName;
    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser;
            if (e.getSource() == btClear) {
                drawGraphic.clear();
            } else if (e.getSource() == btBlack) {
                drawGraphic.black();
            } else if (e.getSource() == btRed) {
                drawGraphic.red();
            } else if (e.getSource() == btGreen) {
                drawGraphic.green();
            } else if (e.getSource() == btBlue) {
                drawGraphic.blue();
            } else if (e.getSource() == btSave) {
                if (saveCounter == 0) {
                    chooser = new JFileChooser();
                    if (chooser.showSaveDialog(btSave) == JFileChooser.APPROVE_OPTION) {
                        file = chooser.getSelectedFile();
                        saveCounter = 1;
                        lbFileName.setText(file.toString());
                        drawGraphic.save(file.toString());
                        drawGraphic.black();
                    }
                } else {
                    lbFileName.setText(file.toString());
                    drawGraphic.save(file.toString());
                }
            } else if (e.getSource() == btLoad) {
                chooser = new JFileChooser();
                if (chooser.showOpenDialog(btLoad) == JFileChooser.APPROVE_OPTION) {
                    file = chooser.getSelectedFile();
                    lbFileName.setText(file.toString());
                    drawGraphic.load(file.toString());
                    drawGraphic.black();
                }
            }
        }
    };

    public DrawerGUI() {
        pnDrawer = new JPanel();

        //construct components
        btLoad = new JButton("Load");
        btSave = new JButton("Save");
        lbFileName = new JLabel("File Name");
        btClear = new JButton("Clear");
        drawGraphic = new DrawGraphic();
        btBlack = new JButton("Black");
        btRed = new JButton("Red");
        btGreen = new JButton("Green");
        btBlue = new JButton("Blue");

        //adjust size and set layout
        pnDrawer.setPreferredSize(new Dimension(1264, 814));
        pnDrawer.setLayout(null);

        //add components
        pnDrawer.add(btLoad);
        pnDrawer.add(btSave);
        pnDrawer.add(lbFileName);
        pnDrawer.add(btClear);
        pnDrawer.add(drawGraphic);
        pnDrawer.add(btBlack);
        pnDrawer.add(btRed);
        pnDrawer.add(btGreen);
        pnDrawer.add(btBlue);

        //set component bounds
        btLoad.setBounds(10, 70, 135, 25);
        btSave.setBounds(10, 40, 135, 25);
        lbFileName.setBounds(10, 0, 275, 35);
        btClear.setBounds(10, 100, 135, 35);
        drawGraphic.setBounds(180, 40, 1075, 765);
        btBlack.setBounds(10, 140, 135, 35);
        btRed.setBounds(10, 180, 135, 35);
        btGreen.setBounds(10, 220, 135, 35);
        btBlue.setBounds(10, 260, 135, 35);

        btBlack.setBackground(Color.BLACK);
        btBlack.addActionListener(listener);
        btRed.setBackground(Color.RED);

        btRed.addActionListener(listener);
        btGreen.setBackground(Color.GREEN);
        btGreen.addActionListener(listener);
        btBlue.setBackground(Color.BLUE);
        btBlue.setBackground(Color.BLUE);
        btSave.addActionListener(listener);
        btLoad.addActionListener(listener);
        btClear.addActionListener(listener);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setContentPane(pnDrawer);
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
