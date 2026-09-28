import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class QuadTree {

    QtNode startNode;

    int[][] image;

    int initialSize;

    int depth = 0;

    int maxDepth;

    int numberOfNodes = 0;

    int numberOfLeaves = 0;

    int dummy;


    // -----------------------------------------
    // Construct a quadtree from a text file
    // -----------------------------------------

    public QuadTree(String filename) {

        startNode = new QtNode();

        try {

            BufferedReader file =
                    new BufferedReader(new FileReader(filename));

            // First number = image size
            initialSize = Integer.parseInt(file.readLine());

            numberOfNodes = 0;
            numberOfLeaves = 0;

            // Read the tree recursively
            readRecursive(startNode, file);

            file.close();

        } catch (IOException e) {

            System.out.println(
                    "Error: invalid fileformat or file not found"
            );

            System.out.println(e.getMessage());
        }

        // Calculate gray values for internal nodes
        fillGrayValues(startNode);
    }


    // -----------------------------------------
    // Recursively read the quadtree
    // -----------------------------------------

    public void readRecursive(
            QtNode qtn,
            BufferedReader file) throws IOException {

        String line = file.readLine();

        if (line == null) {
            throw new IOException("Unexpected end of file.");
        }

        int g = Integer.parseInt(line.trim());

        numberOfNodes++;

        // g < 256 means leaf
        if (g < 256) {

            numberOfLeaves++;

            if (g < 0) {
                throw new IOException("Invalid gray value.");
            }

            qtn.grayValue = g;
            qtn.leaf = true;

        }

        // g >= 256 means internal node
        else {

            qtn.grayValue = 0;
            qtn.leaf = false;

            for (int i = 0; i < 4; i++) {

                qtn.children[i] = new QtNode();

                readRecursive(qtn.children[i], file);
            }
        }
    }


    // -----------------------------------------
    // Convert tree to image array
    // -----------------------------------------

    public int[][] toArray() {

        return toArray(Integer.MAX_VALUE);
    }


    public int[][] toArray(int max) {

        dummy = 0;

        depth = 0;

        maxDepth = max;

        int[][] theArray =
                new int[initialSize][initialSize];

        traverseTree(
                startNode,
                0,
                0,
                initialSize,
                theArray
        );

        System.out.println(
                "Nodes visited for drawing: " + dummy
        );

        return theArray;
    }


    // -----------------------------------------
    // Recursively traverse tree
    // -----------------------------------------

    public void traverseTree(
            QtNode node,
            int bsx,
            int bsy,
            int size,
            int[][] im) {

        depth++;

        dummy++;

        // Stop if leaf OR maximum depth reached
        if (node.leaf || depth > maxDepth) {

            fillArray(
                    im,
                    bsx,
                    bsy,
                    size,
                    node.grayValue
            );

        } else {

            size = size / 2;

            // Child 0 = upper-left
            traverseTree(
                    node.children[0],
                    bsx,
                    bsy,
                    size,
                    im
            );

            // Child 1 = upper-right
            traverseTree(
                    node.children[1],
                    bsx + size,
                    bsy,
                    size,
                    im
            );

            // Child 2 = lower-left
            traverseTree(
                    node.children[2],
                    bsx,
                    bsy + size,
                    size,
                    im
            );

            // Child 3 = lower-right
            traverseTree(
                    node.children[3],
                    bsx + size,
                    bsy + size,
                    size,
                    im
            );
        }

        depth--;
    }


    // -----------------------------------------
    // Fill a square area with gray value
    // -----------------------------------------

    public void fillArray(
            int[][] im,
            int bsx,
            int bsy,
            int size,
            int value) {

        for (int y = bsy; y < bsy + size; y++) {

            for (int x = bsx; x < bsx + size; x++) {

                im[y][x] = value;
            }
        }
    }


    // -----------------------------------------
    // Calculate gray value of internal nodes
    // -----------------------------------------

    public void fillGrayValues(QtNode node) {

        if (node.leaf) {

            return;
        }

        int g = 0;

        for (int i = 0; i < 4; i++) {

            fillGrayValues(node.children[i]);

            g += node.children[i].grayValue;
        }

        node.grayValue = g / 4;
    }


    // -----------------------------------------
    // toString
    // -----------------------------------------

    public String toString() {

        return "Quadtree: nodes:"
                + numberOfNodes
                + ", leaves:"
                + numberOfLeaves;
    }
}