//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.shenrs.components.excel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelProp implements Serializable {
    private static final long serialVersionUID = 4127374681394471582L;
    private boolean checked;
    private List<String> sqlList = new ArrayList();
    private List<Map<String, Object>> dataList = new ArrayList();
    private Map<String, List<String>> allSqlMap = new HashMap();
    private Map<String, List<Map<String, Object>>> allDataMap = new HashMap();
    private List<Map<String, Object>> sheetList = new ArrayList();
    private Map<String, List<Map<String, Object>>> allFiledMap = new HashMap();
    private String errorTempFileName;
    private String errorMessage;
    private Map<String, Boolean> executeSqlResult = new HashMap();

    public ExcelProp() {
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<String> getSqlList() {
        return this.sqlList;
    }

    public void setSqlList(List<String> sqlList) {
        this.sqlList = sqlList;
    }

    public String getErrorTempFileName() {
        return this.errorTempFileName;
    }

    public void setErrorTempFileName(String errorTempFileName) {
        this.errorTempFileName = errorTempFileName;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<Map<String, Object>> getDataList() {
        return this.dataList;
    }

    public void setDataList(List<Map<String, Object>> dataList) {
        this.dataList = dataList;
    }

    public Map<String, Boolean> getExecuteSqlResult() {
        return this.executeSqlResult;
    }

    public void setExecuteSqlResult(Map<String, Boolean> executeSqlResult) {
        this.executeSqlResult = executeSqlResult;
    }

    public Map<String, List<String>> getAllSqlMap() {
        return this.allSqlMap;
    }

    public void setAllSqlMap(Map<String, List<String>> allSqlMap) {
        this.allSqlMap = allSqlMap;
    }

    public Map<String, List<Map<String, Object>>> getAllDataMap() {
        return this.allDataMap;
    }

    public void setAllDataMap(Map<String, List<Map<String, Object>>> allDataMap) {
        this.allDataMap = allDataMap;
    }

    public List<Map<String, Object>> getSheetList() {
        return this.sheetList;
    }

    public void setSheetList(List<Map<String, Object>> sheetList) {
        this.sheetList = sheetList;
    }

    public Map<String, List<Map<String, Object>>> getAllFiledMap() {
        return this.allFiledMap;
    }

    public void setAllFiledMap(Map<String, List<Map<String, Object>>> allFiledMap) {
        this.allFiledMap = allFiledMap;
    }
}
