package com.zthc.ewms.sheet.entity.pd;


/**
 * 循环盘点明细
 * */

public class KcpdxhDetail {

	private int sheetId;
	private String materialcode;
	private String description;
	private String detailunitname;
	private double storecount;
	private int storeid;
	private int storelocationid;
	private String storelocationname;//库位
	private String storelocationcode;
	private int ztid;
	private int enablesn;
	private String housename;
	private String code;
	private int providerdepid;
	private String providername;
	private int ypcount;

	public int getSheetId() {
		return sheetId;
	}

	public void setSheetId(int sheetId) {
		this.sheetId = sheetId;
	}

	public String getMaterialcode() {
		return materialcode;
	}

	public void setMaterialcode(String materialcode) {
		this.materialcode = materialcode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetailunitname() {
		return detailunitname;
	}

	public void setDetailunitname(String detailunitname) {
		this.detailunitname = detailunitname;
	}

	public double getStorecount() {
		return storecount;
	}

	public void setStorecount(double storecount) {
		this.storecount = storecount;
	}

	public int getStoreid() {
		return storeid;
	}

	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}

	public int getStorelocationid() {
		return storelocationid;
	}

	public void setStorelocationid(int storelocationid) {
		this.storelocationid = storelocationid;
	}

	public String getStorelocationname() {
		return storelocationname;
	}

	public void setStorelocationname(String storelocationname) {
		this.storelocationname = storelocationname;
	}

	public String getStorelocationcode() {
		return storelocationcode;
	}

	public void setStorelocationcode(String storelocationcode) {
		this.storelocationcode = storelocationcode;
	}

	public int getZtid() {
		return ztid;
	}

	public void setZtid(int ztid) {
		this.ztid = ztid;
	}

	public int getEnablesn() {
		return enablesn;
	}

	public void setEnablesn(int enablesn) {
		this.enablesn = enablesn;
	}

	public String getHousename() {
		return housename;
	}

	public void setHousename(String housename) {
		this.housename = housename;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getProviderdepid() {
		return providerdepid;
	}

	public void setProviderdepid(int providerdepid) {
		this.providerdepid = providerdepid;
	}

    public String getProvidername() {
        return providername;
    }

    public void setProvidername(String providername) {
        this.providername = providername;
    }

    public int getYpcount() {
		return ypcount;
	}

	public void setYpcount(int ypcount) {
		this.ypcount = ypcount;
	}

	public KcpdxhDetail() {
	}

	public KcpdxhDetail(int sheetId, String materialcode, String description, String detailunitname, double storecount, int storeid, int storelocationid, String storelocationname, String storelocationcode, int ztid, int enablesn, String housename, String code, int providerdepid, String providername, int ypcount) {
		this.sheetId = sheetId;
		this.materialcode = materialcode;
		this.description = description;
		this.detailunitname = detailunitname;
		this.storecount = storecount;
		this.storeid = storeid;
		this.storelocationid = storelocationid;
		this.storelocationname = storelocationname;
		this.storelocationcode = storelocationcode;
		this.ztid = ztid;
		this.enablesn = enablesn;
		this.housename = housename;
		this.code = code;
		this.providerdepid = providerdepid;
		this.providername = providername;
		this.ypcount = ypcount;
	}
}