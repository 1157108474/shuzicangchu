package com.zthc.ewms.sheet.entity.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * ���³ɱ�ö����
 */
public enum RenewalCostEnum {

    UPDATE(0, "δ����"), SUCCESS(1, "���³ɹ�");
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
     * ���ز���
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
     * ��ȡö����map
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
