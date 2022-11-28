package fish;

import java.util.ArrayList;
import java.util.Random;

public class Fish implements Runnable {
    private boolean isMale;
    private boolean isAlive;

    private boolean isInMeeting;
    private ArrayList<Fish> fishes;

    private Pond pond;

    public Fish(boolean isMale,boolean isAlive,Pond pond){
        this.isMale = isMale;
        this.isAlive = isAlive;
        this.pond = pond;
        this.fishes = pond.getFishes();
    }

    public boolean isMale(){
        return this.isMale;
    }

    public boolean isAlive(){
        return this.isAlive;
    }

    public void meet(){
//        System.out.println("Meeting started");
        Random rnd = new Random();
        boolean properFish = false;

        synchronized (fishes){
            isInMeeting = true;
            Fish fishToMeet = fishes.get(rnd.nextInt(this.fishes.size()));

            while (!properFish){
                if (fishToMeet.isAlive && !fishToMeet.isInMeeting && (fishes.indexOf(fishToMeet) != fishes.indexOf(this))){
                    properFish = true;
                } else {
                    fishToMeet = fishes.get(rnd.nextInt(this.fishes.size()));
                }
            }

            fishToMeet.setInMeeting(true);
//            System.out.println("Found proper fish.");

            if (isMale){
//                System.out.println("Fish is male.");
                if (fishToMeet.isMale){
//                    System.out.println("Both fishes are male.");
                    this.isAlive = false;
                    fishToMeet.isAlive = false;
//                    System.out.println("Both fish are dead.");
                } else {
//                    System.out.println("Our fish is male and the fish to meet is female.");
                    fishes.add(spawnNewFish());
//                    System.out.println("Spawned a new fish.");
                }
            } else  {
                if (fishToMeet.isMale){
//                    System.out.println("Our fish is female and the fish to meet is female.");
                    fishes.add(spawnNewFish());
//                    System.out.println("Spawned a new fish.");
                } else{
//                    System.out.println("Both fish are female.");
                    if (rnd.nextInt() == 0){
                        isAlive = false;
//                        System.out.println("Our fish died");
                    }else{
                        fishToMeet.isAlive = false;
//                        System.out.println("Fish that we met died");
                    }
                }
            }
            System.out.println("Meeting ended. Fish still alive: "+numOfFishAlive());

            setInMeeting(false);
            fishToMeet.setInMeeting(false);

            if (numOfFishAlive()<2){
                pond.setDone(true);
            }
        }
    }

    public Fish spawnNewFish(){
        Random rnd = new Random();
        boolean male = false;

        if (rnd.nextInt(2) == 0){
            male = true;
        }

        return new Fish(male,true,pond);
    }

    public boolean isPondDead(){
        for (Fish fish: fishes) {
            if (fish.isAlive()){
                return false;
            }
        }
        return true;
    }

    public int numOfFishAlive(){
        int sum = 0;
        for (Fish fish: fishes) {
            if (fish.isAlive()){
                sum++;
            }
        }
        return sum;
    }

    public void setInMeeting(boolean isInMeeting){
     this.isInMeeting = isInMeeting;
    }

    @Override
    public void run() {
            meet();


    }
}
