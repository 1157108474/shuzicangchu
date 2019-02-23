package com.zthc.ewms.system.print.entity.guard;

import java.util.HashMap;
import java.util.Map;

/**
 * 打印枚举类
 *
 * @author yyb
 * @create 2018-12-06-15:05
 */
public class PrintEnums {
    /**
     * 物资接收
     */
    public enum WZJSEnum {
        EQUIPMENT_SPARE(1,"设备、备品件及材料类"),ORIGINAL_AUXILIARY(2,"原辅材料"),
        SAFETY_MATERIALS(3,"安全物资"),OTHER_MATERIALS(4,"其他物资");
        private Integer type;
        private String meaning;

        WZJSEnum(Integer type, String meaning) {
            this.type = type;
            this.meaning = meaning;
        }

        public Integer getType() {
            return type;
        }

        public String getMeaning() {
            return meaning;
        }

        /**
         * 根据类型返回参数
         * @param type
         * @return
         */
        public static String getValue(Integer type) {
            for (WZJSEnum e : WZJSEnum.values()) {
                if (e.getType().equals(type)) {
                    return e.getMeaning();
                }
            }
            return "";
        }
        /**
         * 返回WZJSEnum的map类型
         * @return
         */
        public static Map<Integer, String> getMap() {
            Map<Integer, String> maps = new HashMap<Integer, String>();
            for (WZJSEnum e : WZJSEnum.values()) {
                maps.put(e.getType(), e.getMeaning());
            }
            return maps;
        }
    }
    /**
     * 物资退货
     */
    public enum WZTHEnum {
        EQUIPMENT(182,"设备退货单"),MATERIAL(183,"材料退货单"),
        CONSIGNMENT_EQUIPMENT(184,"寄售设备退货单"),CONSIGNMENT_MATERIAL(185,"寄售材料退货单");
        private Integer type;
        private String meaning;

        WZTHEnum(Integer type, String meaning) {
            this.type = type;
            this.meaning = meaning;
        }

        public Integer getType() {
            return type;
        }

        public String getMeaning() {
            return meaning;
        }

        /**
         * 根据类型返回参数
         * @param type
         * @return
         */
        public static String getValue(Integer type) {
            for (WZTHEnum e : WZTHEnum.values()) {
                if (e.getType().equals(type)) {
                    return e.getMeaning();
                }
            }
            return "";
        }
        /**
         * 返回WZTHEnum的map类型
         * @return
         */
        public static Map<Integer, String> getMap() {
            Map<Integer, String> maps = new HashMap<Integer, String>();
            for (WZTHEnum e : WZTHEnum.values()) {
                maps.put(e.getType(), e.getMeaning());
            }
            return maps;
        }
    }
}
