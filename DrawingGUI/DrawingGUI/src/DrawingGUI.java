import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawingGUI extends JFrame {

    public DrawingGUI(){

        super("Paint GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1500,1000));
        pack();
        setLocationRelativeTo(null);

        addGuiComponents();


    }

    private void addGuiComponents() {

        //JPanel Configurations
        JPanel canvasPanel = new JPanel();
        SpringLayout springLayout = new SpringLayout();
        canvasPanel.setLayout(springLayout);

        //1. Canvas
        Canvas canvas = new Canvas(1500,950);
        canvasPanel.add(canvas);
        springLayout.putConstraint(SpringLayout.NORTH, canvas, 50, SpringLayout.NORTH, canvasPanel);

        //2. Color Chooser
        JButton colorChooserButton = new JButton("Select Color");
        colorChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Color c = JColorChooser.showDialog(null, "Select a color", Color.BLACK);
                colorChooserButton.setBackground(c);
                canvas.setColor(c);

            }
        });

        canvasPanel.add(colorChooserButton);
        springLayout.putConstraint(SpringLayout.NORTH, colorChooserButton, 10, SpringLayout.NORTH, canvasPanel);
        springLayout.putConstraint(SpringLayout.WEST, colorChooserButton, 25, SpringLayout.WEST, canvasPanel);

        //3. Reset Button
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                canvas.resetCanvas();

            }
        });
        canvasPanel.add(resetButton);
        springLayout.putConstraint(SpringLayout.NORTH, resetButton, 10, SpringLayout.NORTH, canvasPanel);
        springLayout.putConstraint(SpringLayout.WEST, resetButton, 150, SpringLayout.WEST, canvasPanel);

        this.getContentPane().add(canvasPanel);

    }

}
