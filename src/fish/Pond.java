package fish;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;

public class Pond {
    private boolean isDone;
    private ArrayList<Fish> fishes = new ArrayList<Fish>();

    public Pond(){
        this.isDone = false;
        for (int j = 0; j < 19; j++) {
            if (j<10){
                fishes.add(new Fish(true,true,this));
            } else {
                fishes.add(new Fish(false,true,this));
            }

        }
    }

    public ArrayList<Fish> getFishes(){
        return this.fishes;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setFishes(ArrayList<Fish> fishes) {
        this.fishes = fishes;
    }

    public void runPond(){
        System.out.println("Pond Initialized");
        Random rnd = new Random();
        ExecutorService fixedPool = Executors.newFixedThreadPool(5);
        while(!isDone){
            fixedPool.submit(this.getFishes().get(rnd.nextInt(this.getFishes().size())));
        }
        System.out.println("Pond is dead.");
    }
}
