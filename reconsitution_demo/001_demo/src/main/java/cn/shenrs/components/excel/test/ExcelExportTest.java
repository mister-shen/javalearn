package cn.shenrs.components.excel.test;

import cn.shenrs.components.excel.ExcelTemplateUtils;
import cn.shenrs.components.excel.model.DataSheet;
import cn.shenrs.components.excel.model.ExcelImage;
import cn.shenrs.components.excel.model.TemplateSheet;
import cn.shenrs.utils.Tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
 * @author shenrs
 * @Description
 * @create 2019-12-16 16:47
 **/
public class ExcelExportTest {

    private final static String CONF_PATH = "";

    /**
     * 导出excel，不基于模板，支持多sheet页
     * @param response
     */
    public void exportExcel(HttpServletResponse response){
        //定义导出文件名称
        String excelName="";
        //创建sheet页集合，里面封装需要导出的sheet页数据
        List<DataSheet> sheets=new ArrayList<DataSheet>();

		/*封装第一个sheet页数据开始*/
        //创建一个sheet页
        DataSheet sheet1=new DataSheet();

        //针对日期列的格式化设置，为每一个需要格式化的列创建一个map，然后调用sheet1.addPatternMap(map)
        //map中需要设置三个值，1、key:列的key值   2、type：值为date 3、pattern：日期的格式化字符串
		Map<String,String> patternMap1=new HashMap<String,String>();
		patternMap1.put("key", "date");
		patternMap1.put("type", "date");
		patternMap1.put("pattern", "yyyy-MM-dd");
		sheet1.addPatternMap(patternMap1);

        //封装sheet页中的动态行数据
		Map<String,Object> map1=new HashMap<String,Object>();
		map1.put("category", "类别1");
		map1.put("name", "蟹");
		map1.put("price", "31");
		map1.put("num", "10");
		map1.put("date", new Date());
		sheet1.addRow(map1);
        //将第一个sheet页放到sheets集合中
        sheets.add(sheet1);
		/*封装第一个sheet页数据开始*/

		/*封装第二个sheet页数据开始*/
		DataSheet sheet2=new DataSheet();
		sheet2.setSheetName("产品销售额2");
		sheet2.setKeys(new String[]{"category","name","price","num"});
		sheet2.setColumnNames(new String[]{"产品类别","产品名称","单价","数量"});
		Map<String,Object> map5=new HashMap<String,Object>();
		map5.put("category", "类别1");
		map5.put("name", "蟹");
		map5.put("price", "31");
		map5.put("num", "10");
		sheet2.addRow(map5);

		Map<String,Object> map6=new HashMap<String,Object>();
		map6.put("category", "类别1");
		map6.put("name", "龙虾");
		map6.put("price", "100");
		map6.put("num", "30");
		sheet2.addRow(map6);

		Map<String,Object> map7=new HashMap<String,Object>();
		map7.put("category", "类别1");
		map7.put("name", "墨鱼");
		map7.put("price", "80");
		map7.put("num", "20");
		sheet2.addRow(map7);

		Map<String,Object> map8=new HashMap<String,Object>();
		map8.put("category", "类别2");
		map8.put("name", "黄鱼");
		map8.put("price", "200");
		map8.put("num", "10");
		sheet2.addRow(map8);
		sheets.add(sheet2);
		/*封装第二个sheet页数据结束*/

        //调用该工具方法进行导出
        ExcelTemplateUtils.exportExecl(response, excelName, sheets);
    }

    /**
     * 导出exlel(基于模板)，支持多sheet页,支持删除指定模板页签
     * @param response
     * @throws Exception
     */
    public void exportExcelByTemplate1(HttpServletResponse response) throws Exception {
        //设置导出excel的名称
        String excelName="产品销售额";
        //模板绝对路径
        String templatePath= CONF_PATH+ File.separator+"eform"+ File.separator+"excel"+ File.separator+"export"+ File.separator+"demo1.xls";

        //sheet页集合，里面封装需要导出的sheet页数据
        List<TemplateSheet> sheets=new ArrayList<TemplateSheet>();

		/*第一个sheet页数据封装开始*/
        TemplateSheet sheet1=new TemplateSheet();
        //设置sheet页名称，跟模板名称一致
        sheet1.setSheetName("产品销售额");

        //设置需要格式化为数字的列（单元格左上角不再显示绿色三角标识）,为每一个需要格式化的列创建一个map，然后调用sheet1.addPatternMap(map)
        //对于身份证号、手机号列不要进行该格式化设置
        //map中需要设置两个值，1、key:列的key值   2、type：值为date
        Map<String,String> patternMap2=new HashMap<String,String>();
        patternMap2.put("key", "formula");
        patternMap2.put("type", "number");
        sheet1.addPatternMap(patternMap2);

        //添加动态行数据，调用sheet1.addRow()，map中的key值要与模板中$LIST{key}参数中的key名称一致
        Map<String,Object> map1=new HashMap<String,Object>();
        map1.put("category", "类别1");
        map1.put("name", "蟹");
        map1.put("price", "31");
        map1.put("num", "10");
        map1.put("formula", "310");
        sheet1.addRow(map1);

        Map<String,Object> map2=new HashMap<String,Object>();
        map2.put("category", "类别1");
        map2.put("name", "龙虾");
        map2.put("price", "100");
        map2.put("num", "30");
        map2.put("formula", "3000");
        sheet1.addRow(map2);

        Map<String,Object> map3=new HashMap<String,Object>();
        map3.put("category", "类别1");
        map3.put("name", "墨鱼");
        map3.put("price", "80");
        map3.put("num", "20");
        map3.put("formula", "1600");
        sheet1.addRow(map3);

        Map<String,Object> map4=new HashMap<String,Object>();
        map4.put("category", "类别2");
        map4.put("name", "黄鱼");
        map4.put("price", "200");
        map4.put("num", "10");
        map4.put("formula", "2000");
        sheet1.addRow(map4);
        sheets.add(sheet1);
		/*第一个sheet页数据封装结束*/

		/*第二个sheet页封装开始*/
        TemplateSheet sheet2=new TemplateSheet();
        //设置第二个sheet页的名称
        sheet2.setSheetName("产品销售额2");
        //如果第二个sheet页需要根据第一个sheet页的模板来生成，则设置sheet2.setModelSheet("产品销售额"),参数值为第一个sheet页的名称
        sheet2.setModelSheet("产品销售额");

        //动态行数据
        Map<String,Object> map5=new HashMap<String,Object>();
        map5.put("category", "类别1");
        map5.put("name", "蟹");
        map5.put("price", "31");
        map5.put("num", "10");
        sheet2.addRow(map5);

        Map<String,Object> map6=new HashMap<String,Object>();
        map6.put("category", "类别1");
        map6.put("name", "龙虾");
        map6.put("price", "100");
        map6.put("num", "30");
        sheet2.addRow(map6);

        Map<String,Object> map7=new HashMap<String,Object>();
        map7.put("category", "类别1");
        map7.put("name", "墨鱼");
        map7.put("price", "80");
        map7.put("num", "20");
        sheet2.addRow(map7);

        Map<String,Object> map8=new HashMap<String,Object>();
        map8.put("category", "类别2");
        map8.put("name", "黄鱼");
        map8.put("price", "200");
        map8.put("num", "10");
        sheet2.addRow(map8);
		/*第二个sheet页封装结束*/
        sheets.add(sheet2);

        //配置需要删除的模板页签
        List<String> deletes=new ArrayList<String>();
        deletes.add("Sheet3");

        //调用该工具方法进行导出
        ExcelTemplateUtils.exportExeclByTemplate(response, templatePath, excelName, sheets,deletes);

    }

    /**
     * 导出exlel(基于模板)，支持参数替换，插入图片
     * @param response
     * @throws Exception
     */
    public void exportExcelByTemplate2(HttpServletResponse response) throws Exception {
        //设置导出excel的名称
        String excelName="产品销售额";
        //模板绝对路径
        String templatePath=CONF_PATH+ File.separator+"eform"+ File.separator+"excel"+ File.separator+"export"+ File.separator+"demo2.xls";

        //sheet页集合，里面封装需要导出的sheet页数据
        List<TemplateSheet> sheets=new ArrayList<TemplateSheet>();

		/*第一个sheet页数据封装开始*/
        TemplateSheet sheet1=new TemplateSheet();
        //设置sheet页名称
        sheet1.setSheetName("产品销售额");

        //设置需要格式化为数字的列（单元格左上角不再显示绿色三角标识）,为每一个需要格式化的列创建一个map，然后调用sheet1.addPatternMap(map)
        //对于身份证号、手机号列不要进行该格式化设置
        //map中需要设置两个值，1、key:列的key值   2、type：值为date
        Map<String,String> patternMap2=new HashMap<String,String>();
        patternMap2.put("key", "formula");
        patternMap2.put("type", "number");
        sheet1.addPatternMap(patternMap2);

        //设置需要替换的固定位置参数，参数的key要与模板中$MAP{key}中的key保持一致
        sheet1.putParam("sales", "6910");

        //添加动态行数据，调用sheet1.addRow()，map中的key值要与模板中$LIST{key}参数中的key名称一致
        Map<String,Object> map1=new HashMap<String,Object>();
        map1.put("category", "类别1");
        map1.put("name", "蟹");
        map1.put("price", "31");
        map1.put("num", "10");
        map1.put("formula", "310");
        sheet1.addRow(map1);

        Map<String,Object> map2=new HashMap<String,Object>();
        map2.put("category", "类别1");
        map2.put("name", "龙虾");
        map2.put("price", "100");
        map2.put("num", "30");
        map2.put("formula", "3000");
        sheet1.addRow(map2);

        Map<String,Object> map3=new HashMap<String,Object>();
        map3.put("category", "类别1");
        map3.put("name", "墨鱼");
        map3.put("price", "80");
        map3.put("num", "20");
        map3.put("formula", "1600");
        sheet1.addRow(map3);

        Map<String,Object> map4=new HashMap<String,Object>();
        map4.put("category", "类别2");
        map4.put("name", "黄鱼");
        map4.put("price", "200");
        map4.put("num", "10");
        map4.put("formula", "2000");
        sheet1.addRow(map4);

        //设置需要插入的图片
        String imgPath1=CONF_PATH+ File.separator+"eform"+ File.separator+"excel"+ File.separator+"export"+ File.separator+"evidence.png";
		/*
		 * 创建建image对象，构造函数参数为：
		 * 1、图片绝对路径；
		 * 2、图片左上角所在单元格行数（从0开始）;
		 * 3、图片左上角所在单元格列数(从0开始);
		 * 4、图片右下角所在单元格行数；
		 * 5、图片右下角所在单元格列数；
		 * 6、图片左上角所在单元格的X轴位置（0-1023）
		 * 7、图片左上角所在单元格的Y轴位置（0-255）
		 * 8、图片右下所在单元格的X轴位置（0-1023）
		 * 9、图片右下角所在单元格的Y轴位置（0-255）
		 */
        ExcelImage image1=new ExcelImage(imgPath1,1,11,4,13,0,0,100,255);
        sheet1.addImage(image1);

        sheets.add(sheet1);
		/*第一个sheet页数据封装结束*/

        ExcelTemplateUtils.exportExeclByTemplate(response, templatePath, excelName, sheets,null);

    }
}
