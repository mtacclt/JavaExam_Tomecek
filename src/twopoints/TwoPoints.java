package twopoints;

public class TwoPoints {
    public static void main(String[] args) {
        System.out.println(computePointOfInterception(2, 4));
    }

    public static int computePointOfInterception(int x, int y) {
        return pointOfInterceptionWithFactors(x,y,12,1);
    }

    public static int pointOfInterceptionWithFactors(int x,int y,int circleSize,int speed){
        while (x != y) {
            if (x + speed <= circleSize) {
                x = x + speed;
            } else {
                x = x - circleSize + speed;
            }

            if (y + speed * 2 <= circleSize){
                y = y + speed*2;
            }else{
                y = y - circleSize + speed*2;
            }
        }
        return x;
    }
}