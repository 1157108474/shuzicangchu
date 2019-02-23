package com.zthc.ewms.sheet.entity.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 更新成本枚举类
 */
public enum RenewalCostEnum {

    UPDATE(0, "未更新"), SUCCESS(1, "更新成功");
    private Integer renewalCost;
    private String meaning;

    RenewalCostEnum(Integer renewalCost, String meaning) {
        this.renewalCost = renewalCost;
        this.meaning = meaning;
    }

    public Integer getRenewalCost() {
        return renewalCost;
    }

    public String getMeaning() {
        return meaning;
    }

    /**
     * 返回参数
     *
     * @param renewalCost
     * @return
     */
    public static String getValue(Integer renewalCost) {
        if (renewalCost != null) {
            for (RenewalCostEnum e : RenewalCostEnum.values()) {
                if (renewalCost.equals(e.getRenewalCost())) {
                    return e.getMeaning();
                }
            }
        } else {
            return RenewalCostEnum.UPDATE.meaning;
        }
        return "";
    }

    /**
     * 获取枚举类map
     *
     * @return
     */
    public static Map<Integer, Object> getMap() {
        Map<Integer, Object> maps = new HashMap<Integer, Object>();
        for (RenewalCostEnum e : RenewalCostEnum.values()) {
            maps.put(e.getRenewalCost(), e.getMeaning());
        }
        return maps;
    }


}
