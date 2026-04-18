package register;

import java.util.Map;
import java.util.HashMap;

public class RegisterVoluntaryDict {
    protected static Boolean options(String is_voluntary) {
        Map<String, Boolean> optionsVoluntary = new HashMap<>();

        optionsVoluntary.put("Quero mudar o mundo!", true);
        optionsVoluntary.put("Dessa vez não =/", false);

        return optionsVoluntary.get(is_voluntary);
    }
}
