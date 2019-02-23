package com.zthc.ewms.system.dictionary.entity.guard;

public class DictionaryEnums {

	//״̬
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

	//��������
	public enum  AddType{
		TRANSFER(0,"����Ǩ��"),
		EWMS(1,"ϵͳ����"),
		ERP(2,"ERPͬ��");
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


	//�Ƿ�
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

	//�ⷿ��������
	public enum  Property {
		HOUSE(1,"�ⷿ"),
		AREA(2,"����"),
		RACK(3,"����"),
		ALLOCATION(4,"��λ");
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


	//�ӿڴ�����ʽ
	public enum  TriggerKind {
		CURRENT(1,"ʵʱ"),
		HAND(2,"�ֶ�"),
		TIMER(3,"��ʱ"),
		OTHER(4,"����");
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

	//ϵͳ�ӿ�����
	public enum  SystemKind {
		SUPPLYSYS(1,"�ṩ��"),
		CALLSYS(2,"ʵʱ");

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

	//ͬ����ʽ
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
	//�ֵ����
	public enum  DicType {
		FundSource(628,"�ʽ���Դ"),
		ReceiptProcess(30,"��������"),
		MaterialUnit(405,"���ʼ�����λ"),
		TJTYPE(265,"ͳ������"),
		RelateSystem(345,"���ϵͳ"),
		ReceiptStatus(38,"����״̬"),
		AllocationStatus(508,"��������"),
		JSType(608,"ӵ�з�����"),
		ButtonPower(44,"��ťȨ��"),
		RKTYPE(788,"�������"),
		CKTYPE(447,"��������"),
		TKTYPE(688,"�˿�����"),
		PDTYPE(249,"�̵�����"),
		ReceiptType(33,"��������"),
		OrderStatus(769,"����״̬"),
		WasteType(306,"��������");

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

	//ӵ�з�����
	public enum JsType {
		ZSP(708,"����Ʒ"),
		NoJs(610,"����"),
		IsJs(609,"����");

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
