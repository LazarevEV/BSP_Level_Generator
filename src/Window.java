import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Window {
    ArrayList<Leaf> leafList;
    JFrame frame = new JFrame("Generated Level");
    int width, height;

    public Window (int width, int height, ArrayList<Leaf> leafList) {
        this.width = width;
        this.height = height;
        this.leafList = leafList;
    }

    public void init() throws Exception {
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.add(new MyPanel(leafList));

        frame.setVisible(true);

        getSaveSnapShot(frame, "level.png");
    }

    public static BufferedImage getScreenShot(Component component) {

        BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
        // paints into image's Graphics
        component.paint(image.getGraphics());
        return image;
    }

    public static void getSaveSnapShot(Component component, String fileName) throws Exception {
        BufferedImage img = getScreenShot(component);
        // write the captured image as a PNG
        ImageIO.write(img, "png", new File(fileName));
    }


}
