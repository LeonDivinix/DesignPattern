package wzry.equipment;

import wzry.specialefficiency.SpecialEfficiency;

import java.util.ArrayList;
import java.util.List;

public class PhysicWeapon extends Equipment {
    /**
     * 加物理攻击
     */
    private int addPhysicAttack = 0;
    /**
     * 加最大生命
     */
    private int addHP = 0;
    /**
     * 加最大法力
     */
    private int addMP = 0;
    /**
     * 加魔法防御
     */
    private int addMagicDeffence = 0;
    /**
     * 加攻击速度
     */
    private int addAttackSpeedRate = 0;
    /**
     * 加吸血百分比
     */
    private int addAbsorbHPRate = 0;
    /**
     * 加暴击几率
     */
    private int addCriticalHitRate = 0;
    /**
     * 加移速百分比
     */
    private int addMoveSpeedRate = 0;
    /**
     * 冷却缩减百分比
     */
    private int addColdDownRate = 0;
    /**
     * 特效
     */
    private List<SpecialEfficiency> specialEfficiencyList = new ArrayList<>();

    public PhysicWeapon(String code) {
        super(code);
    }
}
