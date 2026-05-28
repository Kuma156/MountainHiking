package bussiness;

import controller.Acceptable;
import controller.FileIOHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Mountain;

/**
 *
 * @author The Miracle Invoker
 */
public class Mountains extends ArrayList<Mountain> {

    private FileIOHandler ioHandler = new FileIOHandler();

    public void loadData() throws IOException {
        List<Mountain> list = ioHandler.readMountainFromCsv("MountainList.csv");
        this.addAll(list);
    }

    public boolean isExistMountainCode(String code) {
        boolean valid = Acceptable.isValid(code, Acceptable.MOUNTAIN_CODE_VALID);
        if (!valid) {
            return false;
        }

        for (Mountain m : this) {
            if (m.getMountainPeakCode().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }
}
