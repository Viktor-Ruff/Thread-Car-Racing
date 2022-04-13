import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

/**
 * @author Viktor_Ruff
 * @date 03|30|2022. 8:46 PM
 */
public class Car {

    private boolean lastStage = true;
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;
    private Car[] cars;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    public Car(int countCars, Race race) {
        this.race = race;
        this.cars = new Car[countCars];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(this.race, 20 + (int) (Math.random() * 10));
        }
    }

    public void startRace() {

        CountDownLatch cdl = new CountDownLatch(cars.length);
        Semaphore smp = new Semaphore(cars.length / 2);

        System.out.println(Colors.ANSI_BLUE + "IMPORTANT ANNOUNCEMENT  >>> Preparation!!!" + Colors.ANSI_RESET);
        for (int i = 0; i < cars.length; i++) {

            int finalI = i;
            new Thread(() -> {
                System.out.println(cars[finalI].getName() + " готовится");

                try {
                    Thread.sleep(500 + (int) (Math.random() * 800));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(cars[finalI].getName() + " готов");
                    cdl.countDown();

                    for (int j = 0; j < race.getStages().size(); j++) {
                        if (race.getStages().get(j) instanceof Tunnel t) {
                            try {
                                System.out.println(cars[finalI].getName() + " готовится к этапу(ждет): " + race.getStages().get(j).getDescription());
                                smp.acquire();
                                race.getStages().get(j).go(cars[finalI]);

                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                smp.release();
                            }

                        } else if (race.getStages().get(j) == race.getStages().get(race.getStages().size() - 1) && lastStage) {

                            try {
                                lastStage = false;
                                race.getStages().get(j).go(cars[finalI]);

                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                System.out.printf(Colors.ANSI_RED + "%s IS WIN!!! \n" + Colors.ANSI_RESET, cars[finalI].getName());
                                try {
                                    Thread.sleep(1500 * cars.length);
                                    System.out.println(Colors.ANSI_YELLOW + "ATTENTION PLEASE >>> Race is end " + Colors.ANSI_RESET);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            race.getStages().get(j).go(cars[finalI]);

                        }
                    }
                }
            }).start();
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Colors.ANSI_PURPLE + "ATTENTION PLEASE >>> Race is start!!" + Colors.ANSI_RESET);
    }

}

