package cn.shenrs.utils;

import cn.shenrs.components.excel.ExcelTemplateUtils;
import cn.shenrs.components.excel.model.DataSheet;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author shenrs
 * @Description
 * @create 2019-12-16 16:37
 **/
public class ExportUtils {


    /**
     * 导出功能
     * @param response
     * @param keys
     * @param columnNames
     * @param excelName
     * @param resultList
     */
    public void exportExcel(HttpServletResponse response, String[] keys, String[] columnNames, String excelName, List<Map<String, Object>> resultList ){
        //创建sheet页集合，里面封装需要导出的sheet页数据
        List<DataSheet> sheets=new ArrayList<DataSheet>();

        DataSheet sheet1=new DataSheet();
        //设置sheet页的名称
        sheet1.setSheetName(excelName);
        //设置标题名称
        sheet1.setKeys(keys);
        //设置标题各列对应的key
        sheet1.setColumnNames(columnNames);
        List<Map<String, Object>> list = resultList;
        if(list.size()>0){
            for (Map<String, Object> map : list) {
                sheet1.addRow(map);
            }
        }
        sheets.add(sheet1);
        ExcelTemplateUtils.exportExecl(response, excelName, sheets);
    }


}
