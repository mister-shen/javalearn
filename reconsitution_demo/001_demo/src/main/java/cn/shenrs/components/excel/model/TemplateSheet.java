//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.shenrs.components.excel.model;

import cn.shenrs.utils.Tool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateSheet implements Serializable {
    private static final long serialVersionUID = -4919457894528913246L;
    private String sheetName;
    private String modelSheet;
    private List<Map<String, Object>> data = new ArrayList();
    private List<Map<String, String>> patternMaps = new ArrayList();
    private Map<String, Object> paramMap = new HashMap();
    private List<ExcelImage> images = new ArrayList();
    private String dataFilterCell;

    public TemplateSheet() {
    }

    public void addRow(Map<String, Object> map) {
        this.data.add(map);
    }

    public void addImage(ExcelImage image) {
        this.images.add(image);
    }

    public void putParam(String key, Object value) {
        this.paramMap.put(key, value);
    }

    public Object getParam(String key) {
        return this.paramMap.get(key);
    }

    public void addPatternMap(Map<String, String> patternMap) {
        this.patternMaps.add(patternMap);
    }

    public void addPatternStr(String patternStr) {
        if (!Tool.isNull(patternStr)) {
            String[] patternArray = patternStr.split(",");
            String[] var6 = patternArray;
            int var5 = patternArray.length;

            for(int var4 = 0; var4 < var5; ++var4) {
                String pattern = var6[var4];
                if (!Tool.isNull(pattern)) {
                    String[] formatArray = pattern.split(":");
                    if (formatArray.length >= 2) {
                        Map<String, String> patternMap = new HashMap();
                        patternMap.put("key", formatArray[0]);
                        patternMap.put("type", formatArray[1]);
                        if (formatArray.length == 3) {
                            patternMap.put("pattern", formatArray[2]);
                        }

                        this.patternMaps.add(patternMap);
                    }
                }
            }

        }
    }

    public String getSheetName() {
        return this.sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getModelSheet() {
        return this.modelSheet;
    }

    public void setModelSheet(String modelSheet) {
        this.modelSheet = modelSheet;
    }

    public List<Map<String, Object>> getData() {
        return this.data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public Map<String, Object> getParamMap() {
        return this.paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public List<Map<String, String>> getPatternMaps() {
        return this.patternMaps;
    }

    public void setPatternMaps(List<Map<String, String>> patternMaps) {
        this.patternMaps = patternMaps;
    }

    public List<ExcelImage> getImages() {
        return this.images;
    }

    public void setImages(List<ExcelImage> images) {
        this.images = images;
    }

    public String getDataFilterCell() {
        return this.dataFilterCell;
    }

    public void setDataFilterCell(String dataFilterCell) {
        this.dataFilterCell = dataFilterCell;
    }
}
