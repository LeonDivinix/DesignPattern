package wzry.equipment;

import wzry.helper.ReflectionHelper;

import java.util.HashMap;
import java.util.Map;

public class Equipment {
    /**
     * 装备编号
     */
    private String code;

    /**
     * 加攻击速度
     */
    private int addAttackRate = 0;
    /**
     * 加暴击几率
     */
    private int addCriticalHitRate = 0;
    /**
     * 加移动速度
     */
    private int addMoveSpeedRate = 0;

    public String getCode() {
        return code;
    }


    /**
     * 根据code获得装备配置数据
     * @param code 装备编号
     * @return 装备配置数据
     */
    private Map<String, Integer> getConfigByCode(String code) {
        Map<String, Integer> result = new HashMap<>();
        // todo 从数据源(数据库,redis,文件,接口等)获得该装备配置数据(配置数据由后台配置)
        return result;
    }

    /**
     *
     * @param code 装备编号
     */
    public Equipment(String code) {
        Map<String, Integer> config = this.getConfigByCode(code);
        this.code = code;
        ReflectionHelper.setFieldsValue(this, config);
    }
}
