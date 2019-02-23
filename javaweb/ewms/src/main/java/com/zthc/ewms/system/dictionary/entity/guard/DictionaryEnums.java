package com.zthc.ewms.system.dictionary.entity.guard;

public class DictionaryEnums {

	//状态
	public enum  Status{
		DISABLE(0),
		ENABLE(1),
		DELETE(2),
		SHEETING(39),
		CHECKING(40),
		OVER(41);
		private int code;
		Status(int code) {
			this.code = code;
		}
		public int getCode() {
			return code;
		}

	}

	//新增类型
	public enum  AddType{
		TRANSFER(0,"数据迁移"),
		EWMS(1,"系统增加"),
		ERP(2,"ERP同步");
		private int code;
		private String msg;
		AddType(int code,String msg) {
			this.code = code;
			this.msg = msg;
		}
		public int getCode() {
			return code;
		}
		public String getMsg() {
			return msg;
		}

	}


	//是否
	public enum  YesOrNo {
		YES(1),
		NO(0);
		private int code;

		YesOrNo(int code) {
			this.code = code;
		}
		public int getCode() {
			return code;
		}

	}

	//库房库区性质
	public enum  Property {
		HOUSE(1,"库房"),
		AREA(2,"库区"),
		RACK(3,"货架"),
		ALLOCATION(4,"货位");
		private int code;
		private String msg;
		Property(int code,String msg) {
			this.code = code;
			this.msg = msg;
		}
		public int getCode() {
			return code;
		}
		public String getMsg() {
			return msg;
		}

	}


	//接口触发方式
	public enum  TriggerKind {
		CURRENT(1,"实时"),
		HAND(2,"手动"),
		TIMER(3,"定时"),
		OTHER(4,"其他");
		private int code;
		private String msg;
		TriggerKind(int code,String msg) {
			this.code = code;
			this.msg = msg;
		}
		public int getCode() {
			return code;
		}
		public String getMsg() {
			return msg;
		}

	}

	//系统接口性质
	public enum  SystemKind {
		SUPPLYSYS(1,"提供方"),
		CALLSYS(2,"实时");

		private int code;
		private String msg;
		SystemKind(int code,String msg) {
			this.code = code;
			this.msg = msg;
		}
		public int getCode() {
			return code;
		}
		public String getMsg() {
			return msg;
		}
	}

	//同步方式
	public enum  SyncKind {
		WEBSERVICE(1),
		API(2),
		FILEEXPORT(3),
		DBLINK(4),
		OTHER(5);
		int code;
		SyncKind(int code) {
			this.code = code;
		}
		public int getCode() {
			return code;
		}

	}
	//字典分类
	public enum  DicType {
		FundSource(628,"资金来源"),
		ReceiptProcess(30,"单据流程"),
		MaterialUnit(405,"物资计量单位"),
		TJTYPE(265,"统计类型"),
		RelateSystem(345,"相关系统"),
		ReceiptStatus(38,"单据状态"),
		AllocationStatus(508,"调拨类型"),
		JSType(608,"拥有方类型"),
		ButtonPower(44,"按钮权限"),
		RKTYPE(788,"入库类型"),
		CKTYPE(447,"出库类型"),
		TKTYPE(688,"退库类型"),
		PDTYPE(249,"盘点类型"),
		ReceiptType(33,"单据种类"),
		OrderStatus(769,"单据状态"),
		WasteType(306,"废料类型");

		private int code;
		private String msg;
		DicType(int code,String msg) {
			this.code = code;
			this.msg = msg;
		}
		public int getCode() {
			return code;
		}
		public String getMsg() {
			return msg;
		}

	}

	//拥有方类型
	public enum JsType {
		ZSP(708,"赠送品"),
		NoJs(610,"自有"),
		IsJs(609,"寄售");

		private int code;
		private String msg;
		JsType(int code,String msg) {
			this.code = code;
			this.msg = msg;
		}
		public int getCode() {
			return code;
		}
		public String getMsg() {
			return msg;
		}

	}




	public enum TypeToUrlId {

		RK("RK","/sheet/rk/",35),
		YKYW("YKYW", "/sheet/ykyw/",161),
		DB("WZDBD", "/sheet/db/",506),
		TH("WZTH", "/sheet/th/",485),
		TK("WZTK", "/sheet/tk/",315),
		WZJS("WZJS", "/sheet/wzjs/",588),
		KCPD("KCPD","/sheet/pd/",246),
		WZLLD("WZLLD","/sheet/apply/",650),
		wzjcrkd("wzjcrkd","/sheet/jcwzrk/",465),
		wzjcckd("wzjcckd","/sheet/jcwzck/",466),
		sheetCK("CKD","/sheet/ck/",36),
        WZZC("WZZC", "/sheet/zc/", 835),
        ZR("ZR", "/sheet/zr/", 832);
        private String type;
		private String url;
		private int id;
		TypeToUrlId(String type,String url,int id) {
			this.type = type;
			this.url = url;
			this.id = id;
		}
		public String getType() {
			return type;
		}
		public String getUrl() {
			return url;
		}
		public int getId() {
			return id;
		}

		public static String getUrlByType(String type) {
			for (TypeToUrlId url : values()) {
				if (url.getType().equals(type)) {
					return url.getUrl();
				}
			}
			return null;
		}

		public static Integer getIdByType(String type) {
			for (TypeToUrlId id : values()) {
				if (id.getType().equals(type)) {
					return id.getId();
				}
			}
			return null;
		}
	}

}
