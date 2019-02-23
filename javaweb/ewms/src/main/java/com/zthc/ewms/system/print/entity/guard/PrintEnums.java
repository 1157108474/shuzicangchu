package com.zthc.ewms.system.print.entity.guard;

import java.util.HashMap;
import java.util.Map;

/**
 * ��ӡö����
 *
 * @author yyb
 * @create 2018-12-06-15:05
 */
public class PrintEnums {
    /**
     * ���ʽ���
     */
    public enum WZJSEnum {
        EQUIPMENT_SPARE(1,"�豸����Ʒ����������"),ORIGINAL_AUXILIARY(2,"ԭ������"),
        SAFETY_MATERIALS(3,"��ȫ����"),OTHER_MATERIALS(4,"��������");
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
         * �������ͷ��ز���
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
         * ����WZJSEnum��map����
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
     * �����˻�
     */
    public enum WZTHEnum {
        EQUIPMENT(182,"�豸�˻���"),MATERIAL(183,"�����˻���"),
        CONSIGNMENT_EQUIPMENT(184,"�����豸�˻���"),CONSIGNMENT_MATERIAL(185,"���۲����˻���");
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
         * �������ͷ��ز���
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
         * ����WZTHEnum��map����
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
