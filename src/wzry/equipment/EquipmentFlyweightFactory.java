package wzry.equipment;

import java.util.Map;
import java.util.HashMap;

/**
 * 装备工厂
 * 使用享元模式作为全局共享
 */
public class EquipmentFlyweightFactory {
    private Map<String, Equipment> map = new HashMap<>();

    public Equipment factory(String code) {
        Equipment result = null;
        if (null != code) {
            if (map.containsKey(code)) {
                result = map.get(code);
            } else {
                result = new Equipment(code);
                map.put(code, result);
            }
        }
        return result;
    }
}
