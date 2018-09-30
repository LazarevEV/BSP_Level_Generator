import java.util.ArrayList;

public class LeafGenerator {
    public final int MAX_LEAF_SIZE = 200;

    ArrayList<Leaf> leafList = new ArrayList<>();
    Leaf helpLeaf;

    public void generate(int width, int height) {
        Leaf root = new Leaf(0, 0, width, height);
        leafList.add(root);

        boolean didSplit = true;

        while (didSplit) {
            didSplit = false;

            for (int i = 0; i < leafList.size(); i++) {
                Leaf leaf = leafList.get(i);
                if (leaf.leftChild == null && leaf.rightChild == null) {
                    if (leaf.width > MAX_LEAF_SIZE || leaf.height > MAX_LEAF_SIZE) {
                        if (leaf.split()) {
                            leafList.add(leaf.leftChild);
                            leafList.add(leaf.rightChild);
                            didSplit = true;
                        }
                    }
                }
            }
        }

        root.createRooms();
    }
}
