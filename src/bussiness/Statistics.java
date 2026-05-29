package bussiness;

import java.util.HashMap;
import java.util.Map;
import model.StatisticalInfo;

/**
 *
 * @author The Miracle Invoker
 */
public class Statistics extends HashMap<String, StatisticalInfo> {

    public final String HEADER_TABLE
            = "|----------------------------------------------------------------------|\n"
            + "|      Peak Name        |  Number Of Participants  |   Total Costs     |\n"
            + "|-----------------------|--------------------------|-------------------|";

    public final String FOOTER_TABLE
            = "|----------------------------------------------------------------------|";

    public Statistics(int i, float f) {
        super(i, f);
    }

    public Statistics(int i) {
        super(i);
    }

    public Statistics() {
    }

    public Statistics(Map<? extends String, ? extends StatisticalInfo> map) {
        super(map);
    }

}
