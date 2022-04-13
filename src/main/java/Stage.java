/**
 * @author Viktor_Ruff
 * @date 03|30|2022. 8:48 PM
 */

public abstract class Stage {
    protected int length;
    protected String description;

    public String getDescription() {
        return description;
    }

    public abstract void go(Car c);
}

