package wzry.specialefficiency;

import java.util.HashMap;
import java.util.Map;

/**
 * 特效类
 * 特效比较复杂,因此初步采用后台配置表达式,实际应用中采用解释器模式解析特效,并最终对动作结果产生影响
 */
public class SpecialEfficiency {
    /**
     * 表达式
     */
    private String expression = "";

    /**
     * 获得表达式
     * @return 表达式
     */
    public String getExpression() {
        return expression;
    }

    /**
     * 获得特效配置
     * @param id 特效id
     * @return 特效配置
     */
    public Map<String, Object> getConfigById(Integer id) {
        Map<String, Object> result = new HashMap<>();
        // todo 从数据源(数据库,redis,文件,接口等)获得该特效配置数据(配置数据由后台配置)
        return result;
    }

    SpecialEfficiency(Integer id) {
        Map<String, Object> config = this.getConfigById(id);
        if (config.containsKey("expression")) {
            this.expression = String.valueOf(config.get("expression"));
        }
    }
}
