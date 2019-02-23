package com.zthc.ewms.system.menu.entity.guard;

public class MenuButtonEnums {

    /**
     * 类型
     */
    public enum TypeEnum {
        CATALOG(1, "目录"), PAGE(2, "页面");
        private int type;
        private String meaning;

        TypeEnum(int type, String meaning) {
            this.type = type;
            this.meaning = meaning;
        }

        public int getType() {
            return type;
        }

        public String getMeaning() {
            return meaning;
        }
    }

    /**
     * 状态
     */
    public enum StatusEnum {
        DISABLE(0, "禁用"),ENABLE(1, "启用"), DELETE(2, "删除");;
        private int status;
        private String meaning;

        StatusEnum(int status, String meaning) {
            this.status = status;
            this.meaning = meaning;
        }

        public int getStatus() {
            return status;
        }

        public String getMeaning() {
            return meaning;
        }
    }

    /**
     * 按钮模式
     */
    public enum ButtonModeEnum {
        STATIC(1, "静态按钮"), DYNAMIC(2, "动态按钮"), NO(3, "无按钮"),;
        private int buttonMode;
        private String meaning;

        ButtonModeEnum(int buttonMode, String meaning) {
            this.buttonMode = buttonMode;
            this.meaning = meaning;
        }

        public int getButtonMode() {
            return buttonMode;
        }

        public String getMeaning() {
            return meaning;
        }
    }
}
