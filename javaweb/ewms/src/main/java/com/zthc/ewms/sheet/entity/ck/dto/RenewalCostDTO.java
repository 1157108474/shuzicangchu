package com.zthc.ewms.sheet.entity.ck.dto;

/**
 * 更新成本
 *
 * @author yyb
 * @create 2018-12-24-9:34
 */
public class RenewalCostDTO {


    //出库明细ID
    private Integer sheetCKDETAILId;
    //成本
    private Double noTaxPrice;
    //金额
    private Double notAxSum;

    public Integer getSheetCKDETAILId() {
        return sheetCKDETAILId;
    }

    public void setSheetCKDETAILId(Integer sheetCKDETAILId) {
        this.sheetCKDETAILId = sheetCKDETAILId;
    }

    public Double getNoTaxPrice() {
        return noTaxPrice;
    }

    public void setNoTaxPrice(Double noTaxPrice) {
        this.noTaxPrice = noTaxPrice;
    }

    public Double getNotAxSum() {
        return notAxSum;
    }

    public void setNotAxSum(Double notAxSum) {
        this.notAxSum = notAxSum;
    }

    public RenewalCostDTO(Integer sheetCKDETAILId, Double noTaxPrice, Double notAxSum) {
        this.sheetCKDETAILId = sheetCKDETAILId;
        this.noTaxPrice = noTaxPrice;
        this.notAxSum = notAxSum;
    }
}
