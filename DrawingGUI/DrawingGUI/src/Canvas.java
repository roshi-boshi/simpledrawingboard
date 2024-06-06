import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;
public class Canvas extends JPanel {

    //dot color
    private Color color;

    //canvas width and height
    private int canvasWidth;
    private int canvasHeight;

    //dot location
    private int x, y;

    private final static int STROKE_SIZE = 8;

    //holds all the paths created in the canvas
    private List<List<ColorDot>> allPaths;

    //used to draw a line between points
    private List<ColorDot> currentPath;

    public Canvas(int targetWidth, int targetHeight) {

        super();
        setPreferredSize(new Dimension(targetWidth,targetHeight));
        setOpaque(true);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // init variables
        allPaths = new ArrayList<>(25);
        canvasWidth = targetWidth;
        canvasHeight = targetHeight;

        MouseAdapter mouse = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //get current mouse location
                x = e.getX();
                y = e.getX();

                //draw in current mouse location
                Graphics g = getGraphics();
                g.setColor(color);
                g.fillRect(x,y,STROKE_SIZE, STROKE_SIZE);
                g.dispose();

                //start current path
                currentPath = new ArrayList<>(25);
                currentPath.add(new ColorDot(x,y,color));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //add current path to all paths
                allPaths.add(currentPath);


                //reset the current path
                currentPath = null;

            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // get current location
                x = e.getX();
                y = e.getY();

                Graphics2D g2d = (Graphics2D) getGraphics();
                g2d.setColor(color);
                if(!currentPath.isEmpty()){

                    ColorDot prevPoint = currentPath.get(currentPath.size() - 1);
                    g2d.setStroke(new BasicStroke((STROKE_SIZE)));

                    //connect the current point to the previous point to draw a line
                    g2d.drawLine(prevPoint.getX(),prevPoint.getY(),x,y);

                }
                g2d.dispose();

                //add the new point to the path
                ColorDot nextPoint = new ColorDot(e.getX(),e.getY(), color);
                currentPath.add(nextPoint);
            }
        };

        addMouseListener(mouse);
        addMouseMotionListener(mouse);

    }

    public void setColor(Color color){

        this.color = color;

    }

    public void resetCanvas(){

        //clears all rectangles
        Graphics g = getGraphics();
        g.clearRect(0,0,canvasWidth,canvasHeight);
        g.dispose();

        //reset the path
        currentPath = null;
        allPaths = new ArrayList<>(25);

        repaint();
        revalidate();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //redraws all of the paths created so far
        for(List<ColorDot> path : allPaths){

        ColorDot from = null;
        for(ColorDot point : path) {
            g2d.setColor(point.getColor());

            //edge case when a single dot is created
            if (path.size() == 1) {

                g2d.fillRect(point.getX(), point.getY(), STROKE_SIZE, STROKE_SIZE);

            }

            if (from != null) {
                g2d.setStroke(new BasicStroke(STROKE_SIZE));
                g2d.drawLine(from.getX(), from.getY(), point.getX(), point.getY());

            }
            from = point;

        }

        }
    }
}
