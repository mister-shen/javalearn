//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.shenrs.components.excel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.CellStyle;

public class DataSheet implements Serializable {
    private static final long serialVersionUID = -8314970179463469838L;
    private String sheetName;
    private List<Map<String, Object>> data = new ArrayList();
    private List<Map<String, String>> patternMaps = new ArrayList();
    private String[] columnNames;
    private String[] keys;
    private CellStyle[] dataColumnCellStyles;
    private String dataFilterCell;

    public DataSheet() {
    }

    public void addRow(Map<String, Object> map) {
        this.data.add(map);
    }

    public void addPatternMap(Map<String, String> patternMap) {
        this.patternMaps.add(patternMap);
    }

    public String getSheetName() {
        return this.sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<Map<String, Object>> getData() {
        return this.data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public String[] getColumnNames() {
        return this.columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public String[] getKeys() {
        return this.keys;
    }

    public void setKeys(String[] keys) {
        this.keys = keys;
    }

    public List<Map<String, String>> getPatternMaps() {
        return this.patternMaps;
    }

    public void setPatternMaps(List<Map<String, String>> patternMaps) {
        this.patternMaps = patternMaps;
    }

    public String getDataFilterCell() {
        return this.dataFilterCell;
    }

    public void setDataFilterCell(String dataFilterCell) {
        this.dataFilterCell = dataFilterCell;
    }

    public CellStyle[] getDataColumnCellStyles() {
        return this.dataColumnCellStyles;
    }

    public void setDataColumnCellStyles(CellStyle[] dataColumnCellStyles) {
        this.dataColumnCellStyles = dataColumnCellStyles;
    }
}
