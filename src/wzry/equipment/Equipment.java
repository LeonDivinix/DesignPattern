package wzry.equipment;

import wzry.helper.ReflectionHelper;

import java.util.HashMap;
import java.util.Map;

public class Equipment {
    /**
     * 装备编号
     */
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /**
     * 根据code获得装备配置数据
     * @param code 装备编号
     * @return 装备配置数据
     */
    private Map<String, Object> getConfigByCode(String code) {
        Map<String, Object> result = new HashMap<>();
        // todo 从数据源(数据库,redis,文件,接口等)获得该装备配置数据(配置数据由后台配置)
        return result;
    }

    /**
     *
     * @param code 装备编号
     */
    public Equipment(String code) {
        Map<String, Object> config = this.getConfigByCode(code);
        this.code = code;
        if (config.containsKey("name")) {
            this.name = String.valueOf(config.get("name"));
        }
        ReflectionHelper.setFieldsValue(this, config);
    }
}
