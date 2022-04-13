/**
 * @author Viktor_Ruff
 * @date 03|30|2022. 8:49 PM
 */

public class Road extends Stage {

    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c) {

        try {
            Thread.sleep(1000);
            System.out.println(c.getName() + " начал этап: " + description);

            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
