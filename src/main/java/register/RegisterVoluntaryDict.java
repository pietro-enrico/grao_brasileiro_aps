package register;

import java.util.Map;
import java.util.HashMap;

public class RegisterVoluntaryDict {
    protected Integer options(String is_voluntary) {
        Map<String, Integer> optionsVoluntary = new HashMap<>();

        optionsVoluntary.put("Dessa vez não =/", 0);
        optionsVoluntary.put("Quero mudar o mundo!", 1);

        return optionsVoluntary.get(is_voluntary);
    }
}
