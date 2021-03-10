package fr.zelytra.daedalus.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {

    public static List<String> dynamicTab(List<String> list, String arg){
        List<String> finalList = new ArrayList<String>(list);
        for (String s : list) {
            if (!s.toLowerCase().startsWith(arg.toLowerCase())) {
                finalList.remove(s);
            }
        }
        Collections.sort(finalList);
        return finalList;
    }
}
