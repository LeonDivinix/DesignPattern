package wzry.specialefficiency;

import java.util.HashMap;
import java.util.Map;

/**
 * 特效工厂
 * 使用享元模式作为全局共享
 */
public class SpecialEfficiencyFactory {
    private Map<Integer, SpecialEfficiency> map = new HashMap<>();

    public SpecialEfficiency factory(Integer id) {
        SpecialEfficiency result = null;
        if (null != id) {
            if (map.containsKey(id)) {
                result = map.get(id);
            } else {
                result = new SpecialEfficiency(id);
            }
        }
        return result;
    }
}
