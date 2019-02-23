package com.zthc.ewms.system.menu.entity.guard;

public class MenuButtonEnums {

    /**
     * ����
     */
    public enum TypeEnum {
        CATALOG(1, "Ŀ¼"), PAGE(2, "ҳ��");
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
     * ״̬
     */
    public enum StatusEnum {
        DISABLE(0, "����"),ENABLE(1, "����"), DELETE(2, "ɾ��");;
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
     * ��ťģʽ
     */
    public enum ButtonModeEnum {
        STATIC(1, "��̬��ť"), DYNAMIC(2, "��̬��ť"), NO(3, "�ް�ť"),;
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
