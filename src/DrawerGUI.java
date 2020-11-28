import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;


public class DrawerGUI {
    DrawGraphic drawGraphic;
    Color color = Color.WHITE;
    JButton btSave, btLoad, btColor, btBlack, btClear, btRed, btGreen, btBlue, btUndo, btRedo;
    JLabel lbFileName;
    JPanel pnDrawer = new JPanel();
    JFileChooser chooser;
    private File file;
    private int saveCounter = 0;

    public DrawerGUI() {
        makeGUI();
        createAndShowGUI();
    }

    public void makeGUI() {
        //construct components
        btLoad = new JButton("Load");
        btSave = new JButton("Save");
        lbFileName = new JLabel("File Name");
        btClear = new JButton("Clear");
        this.drawGraphic = new DrawGraphic();
        btBlack = new JButton("Black");
        btRed = new JButton("Red");
        btGreen = new JButton("Green");
        btBlue = new JButton("Blue");
        btColor = new JButton("Choose Color");
        btUndo = new JButton("Undo");
        btRedo = new JButton("Redo");

        //adjust size and set layout
        pnDrawer.setPreferredSize(new Dimension(1264, 814));
        pnDrawer.setLayout(null);

        //add components
        pnDrawer.add(btLoad);
        pnDrawer.add(btSave);
        pnDrawer.add(lbFileName);
        pnDrawer.add(btClear);
        pnDrawer.add(this.drawGraphic);
        pnDrawer.add(btBlack);
        pnDrawer.add(btRed);
        pnDrawer.add(btGreen);
        pnDrawer.add(btBlue);
        pnDrawer.add(btColor);
        pnDrawer.add(btUndo);
        pnDrawer.add(btRedo);

        //set component bounds
        btLoad.setBounds(10, 70, 135, 25);
        btSave.setBounds(10, 40, 135, 25);
        lbFileName.setBounds(10, 0, 275, 35);
        btClear.setBounds(10, 100, 135, 35);
        this.drawGraphic.setBounds(180, 40, 1075, 765);
        btBlack.setBounds(10, 140, 135, 35);
        btRed.setBounds(10, 180, 135, 35);
        btGreen.setBounds(10, 220, 135, 35);
        btBlue.setBounds(10, 260, 135, 35);
        btColor.setBounds(10, 300, 135, 35);
        btUndo.setBounds(10, 340, 135, 35);
        btRedo.setBounds(10, 380, 135, 35);

        btBlack.setBackground(Color.BLACK);
        btRed.setBackground(Color.RED);
        btGreen.setBackground(Color.GREEN);
        btBlue.setBackground(Color.BLUE);

        btBlack.addActionListener(this::colorBlack);
        btRed.addActionListener(this::colorRed);
        btGreen.addActionListener(this::colorGreen);
        btBlue.addActionListener(this::colorBlue);
        btSave.addActionListener(this::saveAction);
        btLoad.addActionListener(this::loadAction);
        btClear.addActionListener(this::clearAction);
        btColor.addActionListener(this::chooseColor);
        btUndo.addActionListener(this::undoAction);
        btRedo.addActionListener(this::redoAction);

    }

    private void saveAction(ActionEvent event) {
        if (saveCounter == 0) {
            chooser = new JFileChooser();
            if (chooser.showSaveDialog(btSave) == JFileChooser.APPROVE_OPTION) {
                file = chooser.getSelectedFile();
                saveCounter = 1;
                lbFileName.setText(file.toString());
                drawGraphic.save(file.toString());
            }
        } else {
            lbFileName.setText(file.toString());
            drawGraphic.save(file.toString());
        }
    }

    private void loadAction(ActionEvent event) {
        chooser = new JFileChooser();
        if (chooser.showOpenDialog(btLoad) == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            lbFileName.setText(file.toString());
            drawGraphic.load(file.toString());

        }
    }

    private void clearAction(ActionEvent event) {
        drawGraphic.clear();
    }

    private void chooseColor(ActionEvent event) {
        color = JColorChooser.showDialog(null, "Choose a color!",
                color);
        if (color == null)
            color = (Color.WHITE);
        drawGraphic.colorPicker(color);
    }

    private void colorBlack(ActionEvent event) {
        drawGraphic.black();
    }

    private void colorRed(ActionEvent event) {
        drawGraphic.red();
    }

    private void colorGreen(ActionEvent event) {
        drawGraphic.green();
    }

    private void colorBlue(ActionEvent event) {
        drawGraphic.blue();
    }

    private void undoAction(ActionEvent event) {
        drawGraphic.getOriginator().undo();
        drawGraphic.getUndoImg();
    }

    private void redoAction(ActionEvent event) {
        drawGraphic.getOriginator().redo();
        drawGraphic.getRedoImg();
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(pnDrawer);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}
