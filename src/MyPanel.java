import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MyPanel extends JPanel {
    ArrayList<Leaf> leafList;

    public MyPanel(ArrayList<Leaf> leafList) {
        this.leafList = leafList;
        //setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void save(String imageFile) {
        Rectangle r = getBounds();

        try {
            BufferedImage i = new BufferedImage(r.width, r.height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = i.getGraphics();
            paint(g);
            ImageIO.write(i, "png", new File(imageFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawLeaf(Graphics graphics, Leaf leaf) {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color randomColor = new Color(r, g, b);
        graphics.setColor(Color.black);
        //graphics.setColor(randomColor);
        graphics.fillRect(leaf.x, leaf.y, leaf.width, leaf.height);

        if (leaf.room != null) {
            graphics.setColor(Color.white);
            graphics.fillRect(leaf.room.x, leaf.room.y, leaf.room.width, leaf.room.height);
//            graphics.setColor(Color.gray);
//            graphics.drawRect(leaf.room.x, leaf.room.y, leaf.room.width, leaf.room.height);
        }
    }

    public void drawHall(Graphics g, Room hall) {
        g.setColor(Color.white);
        g.fillRect(hall.x, hall.y, hall.width, hall.height);
//        g.setColor(Color.gray);
//        g.drawRect(hall.x, hall.y, hall.width, hall.height);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Leaf leaf : leafList) {
            drawLeaf(g, leaf);
            //if (leaf.halls != null) drawHalls(g, leaf);
        }

        for (Leaf leaf : leafList) {
            if (leaf.halls != null) {
                for (Room hall : leaf.halls) {
                    //System.out.println(hall.toString());
                    drawHall(g, hall);
                }
            }
        }
        // Draw Text
        // g.drawRect(0,0, 20,20);

    }
}
