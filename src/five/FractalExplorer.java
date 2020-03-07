package five;

import four.FractalGenerator;
import four.JImageDisplay;
import four.Mandelbrot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

/**
 @author shaidullin
 */

public class FractalExplorer {
    private int size;
    private JImageDisplay image;
    private FractalGenerator fcGen;
    private Rectangle2D.Double range;
    private JComboBox<FractalGenerator> box;
    private JButton button;

    public FractalExplorer(int size) {
        this.size = size;
        this.fcGen = new Mandelbrot();
        this.range = new Rectangle2D.Double();
        fcGen.getInitialRange(this.range);
        createAndShowGUI();
        drawFractal();

    }

    void createAndShowGUI() {
        JFrame frame = new JFrame("Fractals");
        image = new JImageDisplay(size, size);
        button = new JButton("Reset");
        //button.setActionCommand("reset");
        JLabel label = new JLabel("Fractal: ");
        box = new JComboBox<>();
        //box.setActionCommand("box");
        JPanel panelBox = new JPanel();

        panelBox.add(label);
        panelBox.add(box);

        box.addItem(new Mandelbrot());
        box.addItem(new Tricorn());
        box.addItem(new BurningShip());

        frame.add(image, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);
        frame.add(panelBox,BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        ActionHandler handler = new ActionHandler();
        button.addActionListener(handler);
        box.addActionListener(handler);
        image.addMouseListener(new MouseHandler());

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void drawFractal() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, size, x);
                double yCoord = FractalGenerator.getCoord(range.y, range.y + range.width, size, y);
                double numIters = fcGen.numIterations(xCoord, yCoord);
                if (numIters == -1) image.drawPixel(x, y, 0);
                else {
                    float hue = 0.7f + (float) numIters / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    image.drawPixel(x, y, rgbColor);
                }
            }
        }
        image.repaint();
    }

    public class ActionHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == button) {
                fcGen.getInitialRange(range);
                drawFractal();
            }else if (e.getSource() ==  box) {
                fcGen = (FractalGenerator) box.getSelectedItem();
                fcGen.getInitialRange(range);
                drawFractal();
            }
        }
    }

    public class MouseHandler implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            double mouseX = FractalGenerator.getCoord(range.x, range.x + range.width, size, e.getX());
            double mouseY = FractalGenerator.getCoord(range.y, range.y + range.width, size, e.getY());
            System.out.println(mouseX + " "+ mouseY);
            fcGen.recenterAndZoomRange(range, mouseX, mouseY, 0.5);
            drawFractal();
        }

        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    public static void main(String[] args) {new FractalExplorer(500);}
}