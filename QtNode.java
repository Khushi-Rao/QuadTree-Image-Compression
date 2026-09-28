// -------------------------
// The tree's nodes
// Doesn't have any methods.
// -------------------------

public class QtNode {

    boolean leaf;
    int grayValue;
    QtNode[] children;

    public QtNode() {
        children = new QtNode[4];
        grayValue = 0;
        leaf = false;
    }
}