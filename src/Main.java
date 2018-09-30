public class Main {

    public static void main(String[] args) throws Exception {
        LeafGenerator lg = new LeafGenerator();

        int width = 600;
        int height = 600;

        Window w = new Window(width, height, lg.leafList);

        lg.generate(width,height);

        w.init();

//        for (Leaf leaf : lg.leafList) {
//            System.out.println(">>>>LEAF: ");
//            System.out.println("(x,y) = (" + leaf.x + ", " + leaf.y + ") || height: " + leaf.height + " || width: " + leaf.width);
//        }
        System.out.println("Leaf Amount: " + lg.leafList.size());
        int roomAmount = 0;
        for (Leaf leaf: lg.leafList) {
            if (leaf.room != null) roomAmount++;
        }
        System.out.println("Room Amount: " + roomAmount);
    }
}
