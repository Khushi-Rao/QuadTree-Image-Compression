import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class TreeCreator extends JPanel {

    // Array of gray values
    int[][] imageArray;


    // -----------------------------------------
    // Main
    // -----------------------------------------

    public static void main(String[] args) {

        if (args.length < 1 || args.length > 2) {

            System.out.println(
                    "Usage: TreeCreator <filename> [depth]"
            );

            return;
        }

        // Default = full resolution
        int depth = Integer.MAX_VALUE;

        // Optional depth
        if (args.length == 2) {

            depth = Integer.parseInt(args[1]);
        }

        // Create tree and display image
        new TreeCreator(args[0], depth);
    }


    // -----------------------------------------
    // Constructor
    // -----------------------------------------

    public TreeCreator(String filename, int depth) {

        // Load quadtree
        QuadTree qt = new QuadTree(filename);

        System.out.println(qt);

        // Image size
        int size = qt.initialSize;

        // Convert tree to image
        imageArray = qt.toArray(depth);

        // Set panel size
        setPreferredSize(
                new Dimension(size, size)
        );


        // Create window
        JFrame frame = new JFrame("Quadtree Image");

        frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        frame.getContentPane().add(this);

        frame.pack();

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }


    // -----------------------------------------
    // Draw image
    // -----------------------------------------

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        for (int y = 0; y < imageArray.length; y++) {

            for (int x = 0; x < imageArray[y].length; x++) {

                int c = imageArray[y][x];

                // Make sure value is between 0 and 255
                c = Math.max(0, Math.min(255, c));

                Color col =
                        new Color(c, c, c);

                g.setColor(col);

                g.fillRect(x, y, 1, 1);
            }
        }
    }
}