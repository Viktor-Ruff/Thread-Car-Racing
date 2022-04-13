import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Viktor_Ruff
 * @date 03|30|2022. 8:51 PM
 */

public class MainClass {

    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));

        Car arrayCar = new Car(CARS_COUNT, race);
        arrayCar.startRace();

    }

}
