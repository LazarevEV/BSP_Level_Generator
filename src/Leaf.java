import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Leaf {
    private final int MIN_LEAF_SIZE = 100;
    private final int MIN_ROOM_SIZE = 70;
    private final int hallSize = 8;

    public int x, y, width, height;

    public Leaf leftChild;
    public Leaf rightChild;
    public Room room;
    ArrayList<Room> halls;

    public Leaf(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean split() {
        Random rnd = new Random();

        if (leftChild != null || rightChild != null) return false;

        boolean splitH = rnd.nextBoolean();
        if (width > height && width / height >= 1.25) splitH = false;
        else if (height > width && height / width >= 1.25) splitH = true;

        int max = (splitH ? height : width) - MIN_LEAF_SIZE;
        if (max <= MIN_LEAF_SIZE) return false;

        int split = (int) (rnd.nextInt(max - MIN_LEAF_SIZE) + MIN_LEAF_SIZE);

        if (splitH) {
            leftChild = new Leaf(x, y, width, split);
            rightChild = new Leaf(x, y + split, width, height - split);
        } else {
            leftChild = new Leaf(x, y, split, height);
            rightChild = new Leaf(x + split, y, width - split, height);
        }
        return true;
    }

    public void createRooms() {
        Random rnd = new Random();

        if (leftChild != null || rightChild != null) {
            // этот лист был разрезан, поэтому переходим к его дочерним листьям
            if (leftChild != null) {
                leftChild.createRooms();
            }
            if (rightChild != null) {
                rightChild.createRooms();
            }
            if (leftChild != null && rightChild != null) {
                    createHall(leftChild.getRoom(), rightChild.getRoom());
            }
        } else {
            int roomSizeX, roomSizeY;
            int roomPosX, roomPosY;

            roomSizeX = (int) (rnd.nextInt((width - 2) - MIN_ROOM_SIZE) + MIN_ROOM_SIZE);
            roomSizeY = (int) (rnd.nextInt((height - 2) - MIN_ROOM_SIZE) + MIN_ROOM_SIZE);
            //System.out.println("roomSizeX: " + roomSizeX + " || roomSizeY: " + roomSizeY);
            //System.out.println("width: " + width + " || height: " + height);
            roomPosX = (int) (rnd.nextInt((width - roomSizeX - 1) - 1) + 1);
            roomPosY = (int) (rnd.nextInt((height - roomSizeY - 1) - 1) + 1);
            room = new Room(x + roomPosX, y + roomPosY, roomSizeX, roomSizeY);
        }
    }

    public Room getRoom() {
        Random rand = new Random();
        if (room != null)
            return room;
        else {
            Room lRoom = null;
            Room rRoom = null;

            if (leftChild != null) {
                lRoom = leftChild.getRoom();
            }
            if (rightChild != null) {
                rRoom = rightChild.getRoom();
            }

            if (lRoom == null && rRoom == null)
                return null;
            else if (rRoom == null)
                return lRoom;
            else if (lRoom == null)
                return rRoom;
            else if (rand.nextBoolean())
                return lRoom;
            else
                return rRoom;
        }
    }

    public void createHall(Room lRoom, Room rRoom) {
        Random rnd = new Random();

        halls = new ArrayList<>();

        int pOneX = (int) (rnd.nextInt((lRoom.x + lRoom.width - hallSize) - (lRoom.x + hallSize)) + (lRoom.x + hallSize));
        System.out.println("leftRoomRightX: " + (lRoom.x + lRoom.width - hallSize) + " || leftRoomLeftX: " + (lRoom.x + hallSize));
        int pOneY = (int) (rnd.nextInt((lRoom.y + lRoom.height - hallSize) - (lRoom.y + hallSize)) + (lRoom.y + hallSize));
        System.out.println("leftRoomRightY: " + (lRoom.y + lRoom.height - hallSize) + " || leftRoomLeftY: " + (lRoom.y + hallSize));
        int pTwoX = (int) (rnd.nextInt((rRoom.x + rRoom.width - hallSize) - (rRoom.x + hallSize)) + (rRoom.x + hallSize));
        int pTwoY = (int) (rnd.nextInt((rRoom.y + rRoom.height - hallSize) - (rRoom.y + hallSize)) + (rRoom.y + hallSize));

        System.out.println("pointOne: (" + pOneX + ", " + pOneY + ")");
        System.out.println("pointTwo: (" + pTwoX + ", " + pTwoY + ")");

        int w = pTwoX - pOneX;
        int h = pTwoY - pOneY;

        System.out.println("w: " + w + " || h: " + h);

        if (w < 0) {
            if (h < 0) {
                if (rnd.nextBoolean()) {
                    halls.add(new Room(pTwoX, pOneY, Math.abs(w), hallSize));
                    halls.add(new Room(pTwoX, pTwoY, hallSize, Math.abs(h)));
                } else {
                    halls.add(new Room(pTwoX, pTwoY, Math.abs(w), hallSize));
                    halls.add(new Room(pOneX, pTwoY, hallSize, Math.abs(h)));
                }
            } else if (h > 0) {
                if (rnd.nextBoolean()) {
                    halls.add(new Room(pTwoX, pOneY, Math.abs(w), hallSize));
                    halls.add(new Room(pTwoX, pOneY, hallSize, Math.abs(h)));
                } else {
                    halls.add(new Room(pTwoX, pTwoY, Math.abs(w), hallSize));
                    halls.add(new Room(pOneX, pOneY, hallSize, Math.abs(h)));
                }
            } else // если (h == 0)
            {
                halls.add(new Room(pTwoX, pTwoY, Math.abs(w), hallSize));
            }
        } else if (w > 0) {
            if (h < 0) {
                if (rnd.nextBoolean()) {
                    halls.add(new Room(pOneX, pTwoY, Math.abs(w), hallSize));
                    halls.add(new Room(pOneX, pOneY, hallSize, Math.abs(h)));
                } else {
                    halls.add(new Room(pOneX, pOneY, Math.abs(w), hallSize));
                    halls.add(new Room(pTwoX, pTwoY, hallSize, Math.abs(h)));
                }
            } else if (h > 0) {
                if (rnd.nextBoolean()) {
                    halls.add(new Room(pOneX, pOneY, Math.abs(w), hallSize));
                    halls.add(new Room(pTwoX, pOneY, hallSize, Math.abs(h)));
                } else {
                    halls.add(new Room(pOneX, pTwoY, Math.abs(w), hallSize));
                    halls.add(new Room(pOneX, pOneY, hallSize, Math.abs(h)));
                }
            } else // если (h == 0)
            {
                halls.add(new Room(pOneX, pOneY, Math.abs(w), hallSize));
            }
        } else // если (w == 0)
        {
            if (h < 0) {
                halls.add(new Room(pTwoX, pTwoY, hallSize, Math.abs(h)));
            } else if (h > 0) {
                halls.add(new Room(pOneX, pOneY, hallSize, Math.abs(h)));
            }
        }
    }

}
