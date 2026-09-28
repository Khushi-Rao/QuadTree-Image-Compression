import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MyTreeCreator {

    static final int SIZE = 256;

    static int[][] image =
            new int[SIZE][SIZE];


    // -----------------------------------------
    // Read FinalImage.txt
    // -----------------------------------------

    public static void readImage(String filename)
            throws IOException {

        BufferedReader br =
                new BufferedReader(
                        new FileReader(filename)
                );


        for (int y = 0; y < SIZE; y++) {

            for (int x = 0; x < SIZE; x++) {

                String line = br.readLine();

                if (line == null) {

                    br.close();

                    throw new IOException(
                            "FinalImage.txt does not contain enough pixels."
                    );
                }

                int value =
                        Integer.parseInt(line.trim());


                // Gray values must be 0-255
                if (value < 0 || value > 255) {

                    br.close();

                    throw new IOException(
                            "Invalid gray value: " + value
                    );
                }

                image[y][x] = value;
            }
        }

        br.close();

        System.out.println(
                "FinalImage.txt loaded successfully."
        );
    }


    // -----------------------------------------
    // Create quadtree
    // -----------------------------------------

    public static QtNode createTree(
            int x,
            int y,
            int size) {


        QtNode node = new QtNode();


        // Take the first pixel as reference
        int firstValue = image[y][x];

        boolean same = true;


        // Check whether every pixel in this
        // square has the same gray value
        for (int row = y;
             row < y + size && same;
             row++) {

            for (int col = x;
                 col < x + size;
                 col++) {

                if (image[row][col] != firstValue) {

                    same = false;

                    break;
                }
            }
        }


        // ---------------------------------
        // If all pixels are same:
        // create a leaf
        // ---------------------------------

        if (same) {

            node.leaf = true;

            node.grayValue = firstValue;

            return node;
        }


        // ---------------------------------
        // Otherwise:
        // create an internal node
        // ---------------------------------

        node.leaf = false;

        node.grayValue = 256;


        int half = size / 2;


        // Child 0
        // Upper-left
        node.children[0] =
                createTree(
                        x,
                        y,
                        half
                );


        // Child 1
        // Upper-right
        node.children[1] =
                createTree(
                        x + half,
                        y,
                        half
                );


        // Child 2
        // Lower-left
        node.children[2] =
                createTree(
                        x,
                        y + half,
                        half
                );


        // Child 3
        // Lower-right
        node.children[3] =
                createTree(
                        x + half,
                        y + half,
                        half
                );


        return node;
    }


    // -----------------------------------------
    // Write tree to file
    // -----------------------------------------

    public static void writeTree(
            QtNode node,
            BufferedWriter writer)
            throws IOException {


        // Write current node first
        writer.write(
                Integer.toString(node.grayValue)
        );

        writer.newLine();


        // If internal node,
        // recursively write 4 children
        if (!node.leaf) {

            for (int i = 0; i < 4; i++) {

                writeTree(
                        node.children[i],
                        writer
                );
            }
        }
    }


    // -----------------------------------------
    // Main
    // -----------------------------------------

    public static void main(String[] args) {

        try {

            // STEP 1:
            // Read the image
            readImage("FinalImage.txt");


            // STEP 2:
            // Build the quadtree
            System.out.println(
                    "Creating quadtree..."
            );

            QtNode root =
                    createTree(
                            0,
                            0,
                            SIZE
                    );


            // STEP 3:
            // Create output file
            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(
                                    "MyFinalResult.txt"
                            )
                    );


            // First line = image size
            writer.write(
                    Integer.toString(SIZE)
            );

            writer.newLine();


            // STEP 4:
            // Write tree
            writeTree(root, writer);


            writer.close();


            System.out.println(
                    "MyFinalResult.txt created successfully."
            );

        } catch (IOException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Error: FinalImage.txt contains an invalid number."
            );
        }
    }
}