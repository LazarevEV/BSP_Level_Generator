public class Room {
    public int x, y, width, height;

    public Room(int x, int y, int width, int height) {
        //x, y - left top corner of the room
        this.x = x; //x + 1 - to the right by 1 point
        this.y = y; //y + 1 - to the bottom by 1 point
        this.width = width;
        this.height = height;
    }

    public String toString() {
        return "Coord: (" + x + ", " + y + ") || width: " + width + " || height: " + height;
    }
}
