package com.zthc.ewms.system.user.entity.guard;

public class UserEnums {

    /**
     * 状态
     */
    public enum StatusEnum {
        ENABLE(1, "在用"), DISABLE(0, "禁用");
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
     * 性别
     */
    public enum SexEnum {
        BOY(0, "男"), GIRL(1, "女");
        private int sex;
        private String meaning;

        SexEnum(int sex, String meaning) {
            this.sex = sex;
            this.meaning = meaning;
        }

        public int getSex() {
            return sex;
        }

        public String getMeaning() {
            return meaning;
        }
    }

    //类型
    public enum TypeEnum {
        TRANSFER(0, "数据迁移"), EWMS(1, "系统增加"), ERP(2, "ERP同步");
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

    //人员范围类型
    public enum ScopeTypeEnum {
        ORGANIZATION(1, "部门组织"), WAREHOUSE(2, "库房库区"), SPAREPARTSCATE(3, "物料范围"), USEDEP(4, "下辖科室");
        private int type;
        private String meaning;

        ScopeTypeEnum(int type, String meaning) {
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
}
