import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Viktor_Ruff
 * @date 03|30|2022. 8:51 PM
 */
public class Race  {

    private ArrayList<Stage> stages;

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }


}
