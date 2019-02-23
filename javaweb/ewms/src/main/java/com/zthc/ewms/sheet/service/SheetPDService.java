package com.zthc.ewms.sheet.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.Condition;
import com.zthc.ewms.base.util.ExcelImport;
import com.zthc.ewms.base.util.FileUtils;
import com.zthc.ewms.sheet.dao.SheetPDDao;
import com.zthc.ewms.sheet.entity.pd.KcpdjsDetail;
import com.zthc.ewms.sheet.entity.pd.KcpdxhDetail;
import com.zthc.ewms.sheet.entity.pd.WzpcManage;
import com.zthc.ewms.sheet.entity.pd.WzpdDetail;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SheetPDService {

    @Resource
    public SheetPDDao sheetPDDao;

    public LayuiPage<KcpdjsDetail> getjspdStockDetailList(Condition condition, Map params) {
        return sheetPDDao.getjspdStockDetailList(condition, params);
    }

    public LayuiPage<KcpdxhDetail> getxhpdStockDetailList(Condition condition, Map<String, Object> params) {
        return sheetPDDao.getxhpdStockDetailList(condition, params);
    }

    @Transactional
    public void saveSheetDetails(List<WzpdDetail> detailList) {
        for (WzpdDetail detail : detailList) {
            sheetPDDao.saveSheetDetail(detail);
        }
    }

    @Transactional
    public void deletePDDetail(Integer[] idArray) {
        for (int i = 0; i < idArray.length; i++) {
            sheetPDDao.deletePDDetail(idArray[i]);
        }
    }

    public LayuiPage<WzpcManage> manageKcpd(Condition condition, Map<String, Object> params) {
        return sheetPDDao.manageKcpd(condition, params);
    }

    public List<WzpcManage> manageKcpdExport(Map<String, Object> params) {
        return sheetPDDao.manageKcpdExport(params);
    }

    @Transactional
    public int editPdDetail(int id, double detailCount, int userId, String userName, int stockResult) {
        return sheetPDDao.editPdDetail(id, detailCount, userId, userName, stockResult);
    }

    public WzpdDetail getDetail(int i) {
        return sheetPDDao.getDetail(i);
    }

    public long getDetailCount(Map<String, Object> params) {
        return sheetPDDao.getDetailCount(params);
    }

    public Map<String, Object> getPyCount(int id) {
        return sheetPDDao.getPyCount(id);
    }

    public JSONObject isPDTrue(Map<String, Object> sheet) {
        JSONObject ret = new JSONObject();
        Map<String, Object> params = new HashMap<>();
        params.put("sheetId", sheet.get("id"));
        long count = getDetailCount(params);
        if (count <= 0) {
            ret.put("code", false);
            ret.put("message", "请添加盘点明细！");
        } else {
            params.put("stockStatus", 0);
            long countWp = getDetailCount(params);//没有盘点
            if (countWp > 0) {
                ret.put("code", false);
                ret.put("message", "有明细未盘点无法提交！");
            } else {
                /*Map<String, Object> pyCount = getPyCount(Integer.parseInt(sheet.get("id").toString()));
                if (Integer.parseInt(pyCount.get("sysCountAll").toString()) < Integer.parseInt(pyCount.get("detailCountAll").toString())) {
                    ret.put("code", false);
                    ret.put("message", "有盘盈数据没有添加，无法提交！");
                } else {
                    ret.put("code", true);
                    ret.put("message", "");
                }*/
                ret.put("code", true);
                ret.put("message", "");
            }
        }
        return ret;
    }

    public List<WzpdDetail> sheetDetailsNoPage(String id) {
        return sheetPDDao.sheetDetailsNoPage(id);
    }

    public WzpcManage getWzpcManageOne(String id) {
        return sheetPDDao.getWzpcManageOne(id);
    }


    @Transactional
    public void importPdResult(MultipartFile file, Integer userId, String userName) throws IOException {
        //MultipartFile转换为文件流
        InputStream inputStream = FileUtils.convertStream(file);

        // 根据后缀名称判断excel的版本
        Workbook workbook = FileUtils.readExcel(file.getOriginalFilename(), inputStream);
        if (workbook == null) {
            throw new IOException();
        }
        //解析Excel
        List<WzpdDetail> wzpdDetails = analysisExcels(workbook);
        //匹配、修改盘点明细
        matchWzpdDetails(wzpdDetails, userId, userName);
    }

    /**
     * 解析excel
     *
     * @param workbook
     * @return
     * @throws IOException
     */
    @Transactional
    public List<WzpdDetail> analysisExcels(Workbook workbook) {
        // 开始读取数据
        List<WzpdDetail> wzpdDetails = new ArrayList<WzpdDetail>();
        //获取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        //获取最大行数
        int rownum = sheet.getPhysicalNumberOfRows();
        //获取第一行
        Row row ;
        WzpdDetail wzpdDetail;
        Integer detailCount;
        for (int i = 2; i < rownum; i++) {
            row = sheet.getRow(i);
            if (row != null) {
                wzpdDetail = new WzpdDetail();
                //物料编码
                wzpdDetail.setMaterialCode(ExcelImport.getValue(row.getCell(0)));
                //物料描述
                wzpdDetail.setDescription(ExcelImport.getValue(row.getCell(1)));
                //单位
                wzpdDetail.setDetailUnitName(ExcelImport.getValue(row.getCell(2)));
                //库房
                wzpdDetail.setStoreLocationName(ExcelImport.getValue(row.getCell(3)));
                //库存数量
                wzpdDetail.setSysCount(Integer.parseInt(ExcelImport.getValue(row.getCell(4))));
                //盘点数量
                detailCount = Integer.parseInt(ExcelImport.getValue(row.getCell(5)));
                //判断盘点数量是否为空
                if (detailCount != null) {
                    wzpdDetail.setDetailCount(detailCount);
                    if (wzpdDetail.getSysCount() == wzpdDetail.getDetailCount()) {
                        //正常
                        wzpdDetail.setStockResult(0);
                    } else if (wzpdDetail.getSysCount() > wzpdDetail.getDetailCount()) {
                        //盘亏
                        wzpdDetail.setStockResult(-1);
                    } else {
                        //盘盈
                        wzpdDetail.setStockResult(1);
                    }
                } else {
                    wzpdDetail.setDetailCount(wzpdDetail.getSysCount());
                    //正常
                    wzpdDetail.setStockResult(0);
                }
                //备注
                wzpdDetail.setMemo(ExcelImport.getValue(row.getCell(9)));
                //Excel列0
                wzpdDetail.setRownum(i);
                wzpdDetails.add(wzpdDetail);
            }
        }
        return wzpdDetails;
    }

    /**
     * 匹配修改盘点明细
     *
     * @param wzpdDetails
     */
    @Transactional
    public void matchWzpdDetails(List<WzpdDetail> wzpdDetails, Integer userId, String userName) {
        WzpdDetail resultWzpdDetail;
        for (WzpdDetail wzpdDetail : wzpdDetails) {
            //匹配盘点明细
            resultWzpdDetail = this.sheetPDDao.getWzpdDetail(wzpdDetail);
            if (resultWzpdDetail == null) {
                throw new RuntimeException("第" + wzpdDetail.getRownum() + "行,物料编码:" + wzpdDetail.getMaterialCode() + "库房:" +
                        wzpdDetail.getStoreLocationName() + ",库存数量:" + wzpdDetail.getSysCount() + "的数据未匹配到");
            }
            //修改盘点明细
            this.sheetPDDao.editPdDetail(resultWzpdDetail.getId(), wzpdDetail.getDetailCount(), userId, userName, wzpdDetail.getStockResult());
        }
    }


}
