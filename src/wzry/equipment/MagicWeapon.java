package wzry.equipment;

public class MagicWeapon extends Equipment {
    /**
     * 加魔法攻击
     */
    private int addMagicAttatck = 0;
    /**
     * 加最大生命
     */
    private int addHP = 0;
    /**
     * 加最大法力
     */
    private int addMP = 0;
    /**
     * 加每5秒回蓝
     */
    private int addRecoverMP = 0;
    /**
     * 冷却缩减百分比
     */
    private int addColdDownRate = 0;
    /**
     * 加移速百分比
     */
    private int addMoveSpeedRate = 0;
    public MagicWeapon(String code) {
        super(code);
    }
}
