//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.shenrs.components.excel;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.shenrs.components.excel.model.DataSheet;
import cn.shenrs.components.excel.model.ExcelImage;
import cn.shenrs.components.excel.model.TemplateSheet;
import cn.shenrs.components.excel.web.RequestHelper;
import cn.shenrs.utils.Tool;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;

class ExcelUtil {
    private static Logger logger = Logger.getLogger(ExcelUtil.class);
    ExcelUtil() {
    }

    public static void exportExcel(HttpServletResponse response, String fileName, List<DataSheet> sheets) {
        Workbook wb = new HSSFWorkbook();
        writeExcel(wb, sheets);
        export(fileName, wb, response);
    }

    public static Workbook writeExcel(Workbook wb, List<DataSheet> sheets) {
        CellStyle cs = createTitleStyle(wb);
        CellStyle cs2 = createCommonStyle(wb);
        if (sheets != null && sheets.size() > 0) {
            Iterator var5 = sheets.iterator();

            while(var5.hasNext()) {
                DataSheet sheetData = (DataSheet)var5.next();
                String sheetName = sheetData.getSheetName();
                String[] keys = sheetData.getKeys();
                String[] columnNames = sheetData.getColumnNames();
                CellStyle[] dataColumnCellStyles = sheetData.getDataColumnCellStyles();
                List<Map<String, Object>> data = sheetData.getData();
                List<Map<String, String>> patternMaps = sheetData.getPatternMaps();
                Sheet sheet = null;
                if (StringUtils.isNotEmpty(sheetName)) {
                    sheet = wb.createSheet(sheetName);
                } else {
                    sheet = wb.createSheet();
                }

                dataColumnCellStyles = createCustomStyle(wb, dataColumnCellStyles);
                Row head = sheet.createRow(0);
                int i;
                Cell cell;
                if (columnNames != null && columnNames.length > 0) {
                    for(i = 0; i < columnNames.length; ++i) {
                        cell = head.createCell(i);
                        cell.setCellStyle(cs);
                        cell.setCellValue(columnNames[i]);
                    }
                } else {
                    for(i = 0; i < keys.length; ++i) {
                        cell = head.createCell(i);
                        cell.setCellStyle(cs);
                        cell.setCellValue(keys[i]);
                    }
                }

                int j;
                for(i = 0; i < data.size(); ++i) {
                    Row row = sheet.createRow(i + 1);

                    for(j = 0; j < keys.length; ++j) {
                        cell = row.createCell(j);
                        cell.setCellStyle(cs2);
                        if (dataColumnCellStyles != null && dataColumnCellStyles.length > 0) {
                            CellStyle customCellStyle = dataColumnCellStyles[j];
                            if (customCellStyle != null) {
                                cell.setCellStyle(customCellStyle);
                            }
                        }

                        String value = null;
                        if (((Map)data.get(i)).get(keys[j]) == null) {
                            value = " ";
                        } else {
                            if (patternMaps != null && patternMaps.size() > 0) {
                                Iterator var20 = patternMaps.iterator();

                                while(var20.hasNext()) {
                                    Map<String, String> patternMap = (Map)var20.next();
                                    if (StringUtils.equals(keys[j], (String)patternMap.get("key"))) {
                                        if (StringUtils.equals("date", (String)patternMap.get("type"))) {
                                            Date date = (Date)((Map)data.get(i)).get(keys[j]);
                                            SimpleDateFormat sdf = new SimpleDateFormat((String)patternMap.get("pattern"));
                                            value = sdf.format(date);
                                            cell.setCellValue(value);
                                        }

                                        if (StringUtils.equals("number", (String)patternMap.get("type"))) {
                                            value = String.valueOf(((Map)data.get(i)).get(keys[j]));
                                            if (Tool.isDouble(value)) {
                                                cell.setCellValue(Double.parseDouble(value));
                                            }
                                        }
                                    }
                                }
                            }

                            if (value == null) {
                                value = ((Map)data.get(i)).get(keys[j]).toString();
                                cell.setCellValue(value);
                            }
                        }
                    }
                }

                for(i = 0; i < keys.length; ++i) {
                    sheet.autoSizeColumn(i, true);
                }

                Row startRow = sheet.getRow(0);

                for(i = 0; i < keys.length; ++i) {
                    j = sheet.getColumnWidth(i);
                    cell = startRow.getCell(i);
                    int curCellWidth = getValue(cell).length() * 512;
                    if (curCellWidth > j) {
                        sheet.setColumnWidth(i, curCellWidth + 512);
                    }
                }

                setDataFilter(sheet, sheetData.getDataFilterCell());
            }

            return wb;
        } else {
            return wb;
        }
    }

    public static void exportExcelByTemplate(HttpServletResponse response, String filePath, String fileName, List<TemplateSheet> sheets, List<String> deleteSheets) {
        Workbook workbook = writeExcelByTemplate(filePath, sheets, deleteSheets);
        String suffix = StringUtils.substringAfterLast(filePath, ".");
        export(fileName, suffix, workbook, response);
    }

    public static Workbook writeExcelByTemplate(String filePath, List<TemplateSheet> sheets, List<String> deleteSheets) {
        int writeRowNum = 0;
        Map<String, Object> map = null;
        FileInputStream fin = null;
        Workbook workbook = null;
        Sheet sheet = null;

        try {
            fin = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(fin);
            fin.close();
            Iterator var9 = sheets.iterator();

            while(true) {
                TemplateSheet sheetData;
                while(true) {
                    if (!var9.hasNext()) {
                        if (deleteSheets != null && deleteSheets.size() > 0) {
                            var9 = deleteSheets.iterator();

                            while(var9.hasNext()) {
                                String temp = (String)var9.next();
                                sheet = workbook.getSheet(temp);
                                if (sheet != null) {
                                    workbook.removeSheetAt(workbook.getSheetIndex(temp));
                                }
                            }
                        }

                        sheet = null;
                        var9 = sheets.iterator();

                        while(true) {
                            while(var9.hasNext()) {
                                sheetData = (TemplateSheet)var9.next();
                                List<Map<String, Object>> data = sheetData.getData();
                                Map<String, Object> paramMap = sheetData.getParamMap();
                                List<Map<String, String>> patternMaps = sheetData.getPatternMaps();
                                String sheetName = sheetData.getSheetName();
                                String modelSheet = sheetData.getModelSheet();
                                List<ExcelImage> images = sheetData.getImages();
                                sheet = workbook.getSheet(sheetName);
                                if (sheet == null) {
                                    logger.error("名称为【" + sheetName + "】的sheet页在模板中不存在！");
                                } else {
                                    Row row = null;
                                    Cell cell = null;

                                    for(int i = 0; i <= sheet.getLastRowNum(); ++i) {
                                        row = sheet.getRow(i);
                                        boolean flag = false;
                                        List<String> listExpression = new ArrayList();
                                        List<CellStyle> listCellStyle = new ArrayList();
                                        List<Short> listRowHeightStyle = new ArrayList();
                                        if (row != null) {
                                            int rowshift;
                                            for(rowshift = 0; rowshift < row.getLastCellNum(); ++rowshift) {
                                                cell = row.getCell(rowshift);
                                                if (!getValue(cell).equals("")) {
                                                    String cellValue = getValue(cell);
                                                    Pattern pattern_ = Pattern.compile("\\$((LIST)|(MAP)|(IMG))\\{(\\w+)\\}");
                                                    Matcher matcher_ = pattern_.matcher(cellValue);
                                                    if (matcher_.find()) {
                                                        String fieldType = matcher_.group(1);
                                                        String cellExpression = matcher_.group(5);
                                                        if ("LIST".equals(fieldType)) {
                                                            writeRowNum = row.getRowNum();
                                                            listExpression.add(cellExpression);
                                                            listCellStyle.add(cell.getCellStyle());
                                                            listRowHeightStyle.add(row.getHeight());
                                                            flag = true;
                                                        } else if ("MAP".equals(fieldType)) {
                                                            try {
                                                                String value = paramMap.get(cellExpression).toString();
                                                                cellValue = cellValue.replaceAll("\\$(MAP)\\{(\\w+)\\}", value);
                                                                setCellValue(cell, cellValue);
                                                            } catch (Exception var37) {
                                                                System.out.println("-------------------------------------------------------");
                                                                System.out.println("scriptMap不能为null,并且scriptMap存在以" + cellExpression + "为key的value");
                                                            }
                                                        }
                                                    } else if (flag) {
                                                        listExpression.add("");
                                                        listCellStyle.add(cell.getCellStyle());
                                                        listRowHeightStyle.add(row.getHeight());
                                                    }
                                                } else if (flag) {
                                                    if (isMergedRegion(sheet, i, rowshift)) {
                                                        listExpression.add("null");
                                                        listCellStyle.add(cell.getCellStyle());
                                                        listRowHeightStyle.add(row.getHeight());
                                                    } else {
                                                        listExpression.add("");
                                                        listCellStyle.add(cell.getCellStyle());
                                                        listRowHeightStyle.add(row.getHeight());
                                                    }
                                                }
                                            }

                                            if (listExpression != null && listExpression.size() > 0 && flag) {
                                                if (writeRowNum + 1 <= sheet.getLastRowNum()) {
                                                    rowshift = data.size() - 1;
                                                    if (data.size() != 1) {
                                                        if (data.size() == 0) {
                                                            sheet.shiftRows(writeRowNum + 1, sheet.getLastRowNum(), rowshift, true, false);
                                                            --i;
                                                        } else {
                                                            sheet.shiftRows(writeRowNum + 1, sheet.getLastRowNum(), rowshift, true, false);
                                                        }
                                                    }
                                                }

                                                for(rowshift = 0; rowshift < data.size(); ++rowshift) {
                                                    map = (Map)data.get(rowshift);
                                                    Row wirteRow = sheet.createRow(writeRowNum);

                                                    int RegionColumnWidth = 0;
                                                    boolean mergeflag = false;

                                                    int j;
                                                    for(j = 0; j < sheet.getNumMergedRegions(); ++j) {
                                                        if (sheet.getMergedRegion(j).getFirstRow() == writeRowNum) {
                                                            sheet.removeMergedRegion(j);
                                                            --j;
                                                        }
                                                    }

                                                    for(j = 0; j < listExpression.size(); ++j) {
                                                        Cell writeCell = wirteRow.createCell(j);
                                                        String value = null;
                                                        if (map.get(listExpression.get(j)) != null) {
                                                            Iterator var34 = patternMaps.iterator();

                                                            while(var34.hasNext()) {
                                                                Map<String, String> patternMap = (Map)var34.next();
                                                                if (StringUtils.equals((String)listExpression.get(j), (String)patternMap.get("key"))) {
                                                                    if (StringUtils.equals("date", (String)patternMap.get("type"))) {
                                                                        Date date = (Date)map.get(listExpression.get(j));
                                                                        SimpleDateFormat sdf = new SimpleDateFormat((String)patternMap.get("pattern"));
                                                                        value = sdf.format(date);
                                                                        writeCell.setCellValue(value);
                                                                    }

                                                                    if (StringUtils.equals("number", (String)patternMap.get("type"))) {
                                                                        value = String.valueOf(map.get(listExpression.get(j)));
                                                                        if (Tool.isDouble(value)) {
                                                                            writeCell.setCellValue(Double.parseDouble(value));
                                                                        }
                                                                    }
                                                                }
                                                            }

                                                            if (value == null) {
                                                                value = map.get(listExpression.get(j)).toString();
                                                                writeCell.setCellValue(value);
                                                            }
                                                        }

                                                        CellRangeAddress region;
                                                        if (value == null && ((String)listExpression.get(j)).equals("null")) {
                                                            ++RegionColumnWidth;
                                                            mergeflag = true;
                                                            if (j == listExpression.size() - 1 && j != 1) {
                                                                region = new CellRangeAddress(writeRowNum, writeRowNum, j - RegionColumnWidth, j);
                                                                sheet.addMergedRegion(region);
                                                            }
                                                        } else if (mergeflag) {
                                                            mergeflag = false;
                                                            int firstRegionColumn = j - RegionColumnWidth - 1;
                                                            int lastRegionColumn = j - 1;
                                                            region = new CellRangeAddress(writeRowNum, writeRowNum, firstRegionColumn, lastRegionColumn);
                                                            sheet.addMergedRegion(region);
                                                            RegionColumnWidth = 0;
                                                        }

                                                        writeCell.setCellStyle((CellStyle)listCellStyle.get(j));
                                                    }

                                                    wirteRow.setHeight(((Short)listRowHeightStyle.get(0)).shortValue());
                                                    ++writeRowNum;
                                                }
                                            }
                                        }
                                    }

                                    if (images != null && images.size() > 0) {
                                        Drawing patriarch = sheet.createDrawingPatriarch();
                                        Iterator var45 = images.iterator();

                                        while(var45.hasNext()) {
                                            ExcelImage image = (ExcelImage)var45.next();
                                            ClientAnchor anchor = image.getAnchor();
                                            String imgPath = image.getImgPath();
                                            if (StringUtils.endsWithIgnoreCase(imgPath, ".png")) {
                                                patriarch.createPicture(anchor, workbook.addPicture(getImageData(imgPath, "png"), 6));
                                            } else if (StringUtils.endsWithIgnoreCase(imgPath, ".jpg")) {
                                                patriarch.createPicture(anchor, workbook.addPicture(getImageData(imgPath, "jpg"), 5));
                                            }
                                        }
                                    }
                                }
                            }

                            return workbook;
                        }
                    }

                    sheetData = (TemplateSheet)var9.next();
                    String sheetName = sheetData.getSheetName();
                    String modelSheet = sheetData.getModelSheet();
                    sheet = workbook.getSheet(sheetName);
                    if (sheet != null) {
                        break;
                    }

                    if (!Tool.isNull(modelSheet)) {
                        Sheet fromSheet = workbook.getSheet(modelSheet);
                        sheet = workbook.createSheet(sheetName);
                        copySheet(fromSheet, sheet);
                        break;
                    }
                }

                setDataFilter(sheet, sheetData.getDataFilterCell());
            }
        } catch (Exception var38) {
            var38.printStackTrace();
            return workbook;
        }
    }

    public static void export(String filename, String suffix, Workbook wb, HttpServletResponse response) {
        try {
            HttpServletRequest request = RequestHelper.getRequest();
            if (request != null) {
                if (request.getHeader("User-Agent").toUpperCase().indexOf("FIREFOX") > 0) {
                    filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
                } else if (request.getHeader("User-Agent").toUpperCase().indexOf("CHROME") > 0) {
                    filename = URLEncoder.encode(filename, "UTF-8").replace("%28", "\\(").replace("%29", "\\)").replace("\\+", "%20").replace("%2B", "+").replace("%3B", ";").replace("%40", "@").replace("%23", "\\#").replace("%26", "\\&").replace("%2C", "\\,");
                } else {
                    filename = URLEncoder.encode(filename, "UTF-8");
                }
            } else {
                filename = new String(filename.getBytes("GBK"), "ISO-8859-1");
            }

            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "." + suffix + "\"");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            wb.write(response.getOutputStream());
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

    public static void export(String filename, Workbook wb, HttpServletResponse response) {
        export(filename, "xls", wb, response);
    }

    private static CellStyle createTitleStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font f = wb.createFont();
        f.setFontHeightInPoints((short)10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight((short)700);
        style.setFont(f);
        style.setBorderLeft((short)1);
        style.setBorderRight((short)1);
        style.setBorderTop((short)1);
        style.setBorderBottom((short)1);
        style.setAlignment((short)2);
        return style;
    }

    private static CellStyle createCommonStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font f = wb.createFont();
        f.setFontHeightInPoints((short)10);
        f.setColor(IndexedColors.BLACK.getIndex());
        style.setFont(f);
        style.setBorderLeft((short)1);
        style.setBorderRight((short)1);
        style.setBorderTop((short)1);
        style.setBorderBottom((short)1);
        style.setAlignment((short)1);
        return style;
    }

    private static CellStyle[] createCustomStyle(Workbook wb, CellStyle[] customCellStyles) {
        if (customCellStyles != null) {
            for(int i = 0; i < customCellStyles.length; ++i) {
                CellStyle customCellStyle = customCellStyles[i];
                if (customCellStyle != null) {
                    CellStyle commonStyle = createCommonStyle(wb);
                    if (customCellStyle.getBorderLeft() != 0) {
                        commonStyle.setBorderLeft(customCellStyle.getBorderLeft());
                    }

                    if (customCellStyle.getBorderRight() != 0) {
                        commonStyle.setBorderRight(customCellStyle.getBorderRight());
                    }

                    if (customCellStyle.getBorderTop() != 0) {
                        commonStyle.setBorderTop(customCellStyle.getBorderTop());
                    }

                    if (customCellStyle.getBorderBottom() != 0) {
                        commonStyle.setBorderBottom(customCellStyle.getBorderBottom());
                    }

                    if (customCellStyle.getAlignment() != 0) {
                        commonStyle.setAlignment(customCellStyle.getAlignment());
                    }

                    customCellStyles[i] = commonStyle;
                }
            }
        }

        return customCellStyles;
    }

    private static String getValue(Cell cell) {
        String value = "";
        if (cell != null) {
            int tag = cell.getCellType();
            SimpleDateFormat sdf;
            if (tag == 0) {
                if (cell.getCellStyle().getDataFormat() != 58 && cell.getCellStyle().getDataFormat() != 57) {
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                        value = sdf.format(cell.getDateCellValue()).toString();
                    } else {
                        DecimalFormat df = new DecimalFormat("#.#########");
                        value = df.format(cell.getNumericCellValue());
                    }
                } else {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    double values = cell.getNumericCellValue();
                    Date date = DateUtil.getJavaDate(values);
                    value = sdf.format(date);
                }
            } else if (tag == 1) {
                value = cell.getRichStringCellValue().toString();
            } else if (tag == 2) {
                value = cell.getCellFormula();
            } else if (tag == 4) {
                value = " " + cell.getBooleanCellValue();
            } else if (tag == 3) {
                value = "";
            } else if (tag == 5) {
                value = "";
            } else {
                value = cell.getRichStringCellValue().toString();
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    value = sdf.format(value).toString();
                }
            }
        }

        return Tool.replaceBlank(value);
    }

    private static boolean isMergedRegion(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();

        for(int i = 0; i < sheetMergeCount; ++i) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow && column >= firstColumn && column <= lastColumn) {
                return true;
            }
        }

        return false;
    }

    private static Row createRow(Sheet sheet, int rowIndex) {
        Row row = null;
        if (sheet.getRow(rowIndex) != null) {
            int lastRowNo = sheet.getLastRowNum();
            sheet.shiftRows(rowIndex, lastRowNo, 1);
        }

        row = sheet.createRow(rowIndex);
        return row;
    }

    private static void copyRow(Row srcRow, Row destRow, boolean copyValueFlag) {
        destRow.setHeight(srcRow.getHeight());

        for(int i = 0; i < srcRow.getLastCellNum(); ++i) {
            Cell newCell = destRow.createCell(i);
            copyCell(srcRow.getCell(i), newCell, true);
        }

    }

    private static void copyCell(Cell srcCell, Cell destCell, boolean copyValueFlag) {
        if (destCell != null && srcCell != null) {
            destCell.setCellStyle(srcCell.getCellStyle());
            if (srcCell.getCellComment() != null) {
                destCell.setCellComment(srcCell.getCellComment());
            }

            int srcCellType = srcCell.getCellType();
            destCell.setCellType(srcCellType);
            if (copyValueFlag) {
                if (srcCellType == 0) {
                    if (HSSFDateUtil.isCellDateFormatted(srcCell)) {
                        destCell.setCellValue(srcCell.getDateCellValue());
                    } else {
                        destCell.setCellValue(srcCell.getNumericCellValue());
                    }
                } else if (srcCellType == 1) {
                    destCell.setCellValue(srcCell.getRichStringCellValue());
                } else if (srcCellType != 3) {
                    if (srcCellType == 4) {
                        destCell.setCellValue(srcCell.getBooleanCellValue());
                    } else if (srcCellType == 5) {
                        destCell.setCellErrorValue(srcCell.getErrorCellValue());
                    } else if (srcCellType == 2) {
                        destCell.setCellFormula(srcCell.getCellFormula());
                    }
                }
            }

        }
    }

    private static void mergerRegion(Sheet fromSheet, Sheet toSheet) {
        int sheetMergerCount = fromSheet.getNumMergedRegions();

        for(int i = 0; i < sheetMergerCount; ++i) {
            toSheet.addMergedRegion(fromSheet.getMergedRegion(i));
        }

    }

    private static void copySheet(Sheet fromSheet, Sheet toSheet) {
        mergerRegion(fromSheet, toSheet);

        for(int i = 0; i <= fromSheet.getLastRowNum(); ++i) {
            Row tmpRow = fromSheet.getRow(i);
            if (i == 0) {
                for(int j = 0; j < tmpRow.getLastCellNum(); ++j) {
                    toSheet.setColumnWidth(j, fromSheet.getColumnWidth(j));
                }
            }

            Row newRow = toSheet.createRow(tmpRow.getRowNum());
            copyRow(tmpRow, newRow, true);
        }

    }

    private static void setCellValue(Cell cell, Object value) {
        int srcCellType = cell.getCellType();
        if (value != null) {
            if (srcCellType == 0) {
                if (HSSFDateUtil.isCellDateFormatted(cell) && value instanceof Date) {
                    cell.setCellValue((Date)value);
                } else {
                    cell.setCellValue(Double.parseDouble(String.valueOf(value)));
                }
            } else if (srcCellType == 1) {
                cell.setCellValue(String.valueOf(value));
            } else if (srcCellType == 3) {
                cell.setCellValue(String.valueOf(value));
            } else if (srcCellType == 4) {
                cell.setCellValue(Boolean.valueOf(String.valueOf(value)).booleanValue());
            } else if (srcCellType == 2) {
                cell.setCellFormula(String.valueOf(value));
            } else {
                cell.setCellValue("");
            }
        }

    }

    private static void removeRow(Sheet sheet, int rowIndex) {
        int lastRowNum = sheet.getLastRowNum();
        if (rowIndex >= 0 && rowIndex < lastRowNum) {
            sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
        }

        if (rowIndex == lastRowNum) {
            Row removingRow = sheet.getRow(rowIndex);
            if (removingRow != null) {
                sheet.removeRow(removingRow);
            }
        }

    }

    private static byte[] getImageData(String imgPath, String format) {
        if (Tool.isNull(imgPath)) {
            return null;
        } else {
            byte[] imgData = null;
            BufferedImage in = null;
            ByteArrayOutputStream bout = null;

            try {
                in = ImageIO.read(new File(imgPath));
                bout = new ByteArrayOutputStream();
                ImageIO.write(in, format, bout);
                imgData = bout.toByteArray();
            } catch (IOException var6) {
                var6.printStackTrace();
            }

            return imgData;
        }
    }

    private static void setDataFilter(Sheet sheet, String dataFilterCell) {
        if (!Tool.isNull(dataFilterCell)) {
            CellRangeAddress range = CellRangeAddress.valueOf(dataFilterCell);
            sheet.setAutoFilter(range);
        }
    }
}
