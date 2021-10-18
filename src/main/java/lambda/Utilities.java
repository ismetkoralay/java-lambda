package lambda;

import java.util.ArrayList;
import java.util.List;

public class Utilities {
    public static void extractIntegersFromNestedObject(ArrayList arrayList, List<Integer> list) {
        if(arrayList == null || arrayList.size() == 0)
            return;

        for(var y : arrayList) {
            if(y instanceof ArrayList) {
                extractIntegersFromNestedObject((ArrayList)y, list);
            } else {
                Double d = (Double) y;
                list.add(d.intValue());
            }
        }
    }
}
