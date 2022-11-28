package twopoints;

public class TwoPoints {
    public static void main(String[] args) {
        System.out.println(computePointOfInterception(2, 4));
    }

    public static int computePointOfInterception(int x, int
            y) {
        int speed = 1;
        while (x != y) {
            if (x + speed <= 12) {
                x = x + speed;
            } else {
                x = x - 12 + speed;
            }

            if (y + speed * 2 <= 12){
                y = y + speed*2;
            }else{
                y = (y - 12 + speed*2);
            }

        }
        return x;
    }
}