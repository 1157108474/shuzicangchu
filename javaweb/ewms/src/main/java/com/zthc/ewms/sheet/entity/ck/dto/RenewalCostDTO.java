package com.zthc.ewms.sheet.entity.ck.dto;

/**
 * ���³ɱ�
 *
 * @author yyb
 * @create 2018-12-24-9:34
 */
public class RenewalCostDTO {


    //������ϸID
    private Integer sheetCKDETAILId;
    //�ɱ�
    private Double noTaxPrice;
    //���
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
