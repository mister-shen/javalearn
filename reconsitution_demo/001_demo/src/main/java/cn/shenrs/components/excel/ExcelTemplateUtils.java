//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.shenrs.components.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletResponse;
import cn.shenrs.components.excel.model.DataSheet;
import cn.shenrs.components.excel.model.TemplateSheet;
import cn.shenrs.utils.SqlUtils;
import cn.shenrs.utils.Tool;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.util.HSSFColor.RED;
import org.apache.poi.hssf.util.HSSFColor.WHITE;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ExcelTemplateUtils {

    public ExcelTemplateUtils() {
    }

    public static Workbook readWorkBook(String path) {
        Workbook wb = null;

        try {
            wb = WorkbookFactory.create(new FileInputStream(new File(path)));
        } catch (InvalidFormatException var3) {
            var3.printStackTrace();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return wb;
    }

    public static DataValidation setDropSelect(Sheet sheet, String[] pos, int firstRow, int lastRow, int firstCol, int lastCol) {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
        DataValidationConstraint constraint = helper.createExplicitListConstraint(pos);
        DataValidation dataValidation = helper.createValidation(constraint, addressList);
        if (dataValidation instanceof XSSFDataValidation) {
            dataValidation.setSuppressDropDownArrow(true);
            dataValidation.setShowErrorBox(true);
        } else {
            dataValidation.setSuppressDropDownArrow(false);
        }

        return dataValidation;
    }

    public static String rewriteExcelTempAtEmp(Workbook workbook, Sheet sheet, List<DataValidation> dataValidations) {
        String newPath = "";
        if (workbook != null && sheet != null && dataValidations != null && dataValidations.size() > 0) {
            Iterator var5 = dataValidations.iterator();

            while(var5.hasNext()) {
                DataValidation dataValidation = (DataValidation)var5.next();
                sheet.addValidationData(dataValidation);
            }
        }

        return newPath;
    }

    public static void createExcel(Workbook workbook, String path) {
        FileOutputStream fileOut = null;

        try {
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
        } catch (Exception var12) {
            var12.printStackTrace();
        } finally {
            try {
                if (fileOut != null) {
                    fileOut.close();
                }
            } catch (IOException var11) {
                var11.printStackTrace();
            }

        }

    }

    public static CellStyle getErrorStyle(Workbook wb) {
        CellStyle errorstyle = wb.createCellStyle();
        errorstyle.setFillPattern((short)2);
        errorstyle.setFillForegroundColor((new RED()).getIndex());
        errorstyle.setFillBackgroundColor((new RED()).getIndex());
        errorstyle.setBorderBottom((short)1);
        errorstyle.setBorderLeft((short)1);
        errorstyle.setBorderTop((short)1);
        errorstyle.setBorderRight((short)1);
        errorstyle.setLocked(false);
        return errorstyle;
    }

    private static CellStyle getSuccessStyle(Workbook wb) {
        CellStyle errorstyle = wb.createCellStyle();
        errorstyle.setFillPattern((short)2);
        errorstyle.setFillForegroundColor((new WHITE()).getIndex());
        errorstyle.setFillBackgroundColor((new WHITE()).getIndex());
        errorstyle.setBorderBottom((short)1);
        errorstyle.setBorderLeft((short)1);
        errorstyle.setBorderTop((short)1);
        errorstyle.setBorderRight((short)1);
        errorstyle.setLocked(false);
        return errorstyle;
    }

    private static Sheet recoverExcelStyle(Workbook wb, Sheet sheet) {
        Integer rowNum = sheet.getLastRowNum() + 1;

        for(int i = 1; i < rowNum.intValue(); ++i) {
            Row row = sheet.getRow(i);
            if (row == null) {
                break;
            }

            Integer cellNum = Integer.valueOf(row.getLastCellNum());

            for(int j = 0; j < cellNum.intValue(); ++j) {
                Cell cell = row.getCell(j);
                if (cell != null) {
                    cell.removeCellComment();
                    short color = cell.getCellStyle().getFillBackgroundColor();
                    if (color == 10) {
                        short format = cell.getCellStyle().getDataFormat();
                        cell.setCellStyle(getSuccessStyle(wb));
                        cell.getCellStyle().setDataFormat(format);
                    }
                }
            }
        }

        return sheet;
    }

    public static String getCellValue(Cell cell, String fieldtype) {
        String value = "";
        if (cell != null) {
            int tag = cell.getCellType();
            SimpleDateFormat sdf;
            if (tag == 0) {
                if (cell.getCellStyle().getDataFormat() != 58 && cell.getCellStyle().getDataFormat() != 57) {
                    if (!Tool.isNull(fieldtype) && fieldtype.startsWith("DATE")) {
                        sdf = new SimpleDateFormat(fieldtype.replace("DATE:", ""));
                        value = sdf.format(cell.getDateCellValue()).toString();
                    } else if (DateUtil.isCellDateFormatted(cell)) {
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
                cell.setCellType(1);
                value = String.valueOf(cell.getRichStringCellValue());
            } else if (tag == 4) {
                value = " " + cell.getBooleanCellValue();
            } else if (tag == 3) {
                value = "";
            } else if (tag == 5) {
                value = "";
            } else {
                value = cell.getRichStringCellValue().toString();
                if (DateUtil.isCellDateFormatted(cell)) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    value = sdf.format(value).toString();
                }
            }
        }

        if (value != null) {
            value = value.replaceAll(String.valueOf('\u200b'), " ");
            value = value.replaceAll(String.valueOf(' '), " ");
            value = value.trim();
        }

        return value;
    }

    private static String checkCell(Cell cell, String content, Map<String, String> dictionaryMap_CodeToName, String checkString, String checkmethoddescr, Map<String, String> haveData) {
        if (dictionaryMap_CodeToName != null && dictionaryMap_CodeToName.size() > 0) {
            if (checkString.startsWith("!")) {
                if (dictionaryMap_CodeToName.get(content) != null) {
                    return checkmethoddescr;
                }
            } else if (dictionaryMap_CodeToName.get(content) == null) {
                return checkmethoddescr;
            }
        } else if (!Tool.isNull(checkString)) {
            if (content == null) {
                content = "";
            }

            if (!content.matches(checkString)) {
                return checkmethoddescr;
            }
        }

        return "";
    }

    public static boolean checkCell(Cell cell, String content, Drawing drawing, Map<String, String> dictionaryMap_CodeToName, String checkString, String checkmethoddescr, CellStyle errorStyle, Map<String, String> haveData) {
        short format;
        Comment comment;
        if (dictionaryMap_CodeToName != null && dictionaryMap_CodeToName.size() > 0) {
            if (checkString.startsWith("!")) {
                if (dictionaryMap_CodeToName.get(content) != null) {
                    format = cell.getCellStyle().getDataFormat();
                    cell.setCellStyle(errorStyle);
                    cell.getCellStyle().setDataFormat(format);
                    comment = null;
                    if (drawing instanceof XSSFDrawing) {
                        comment = drawing.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, 4, 2, 6, 5));
                        comment.setString(new XSSFRichTextString(checkmethoddescr));
                    } else {
                        comment = drawing.createCellComment(new HSSFClientAnchor(0, 0, 0, 0, (short)4, 2, (short)6, 5));
                        comment.setString(new HSSFRichTextString(checkmethoddescr));
                    }

                    cell.setCellComment(comment);
                    return false;
                }
            } else if (dictionaryMap_CodeToName.get(content) == null) {
                format = cell.getCellStyle().getDataFormat();
                cell.setCellStyle(errorStyle);
                cell.getCellStyle().setDataFormat(format);
                comment = null;
                if (drawing instanceof XSSFDrawing) {
                    comment = drawing.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, 4, 2, 6, 5));
                    comment.setString(new XSSFRichTextString(checkmethoddescr));
                } else {
                    comment = drawing.createCellComment(new HSSFClientAnchor(0, 0, 0, 0, (short)4, 2, (short)6, 5));
                    comment.setString(new HSSFRichTextString(checkmethoddescr));
                }

                cell.setCellComment(comment);
                return false;
            }
        } else if (!Tool.isNull(checkString)) {
            if (content == null) {
                content = "";
            }

            if (!content.matches(checkString)) {
                format = cell.getCellStyle().getDataFormat();
                cell.setCellStyle(errorStyle);
                cell.getCellStyle().setDataFormat(format);
                comment = null;
                if (drawing instanceof XSSFDrawing) {
                    comment = drawing.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, 4, 2, 6, 5));
                    comment.setString(new XSSFRichTextString(checkmethoddescr));
                } else {
                    comment = drawing.createCellComment(new HSSFClientAnchor(0, 0, 0, 0, (short)4, 2, (short)6, 5));
                    comment.setString(new HSSFRichTextString(checkmethoddescr));
                }

                cell.setCellComment(comment);
                return false;
            }
        }

        return true;
    }

    private static String checkCellRepeat(Cell cell, String content, String checkmethoddescr, Map<String, String> haveData) {
        if (!Tool.isNull(content) && !Tool.isNull(getCellValue(cell, ""))) {
            return haveData != null && haveData.size() > 0 && !Tool.isNull((String)haveData.get(content)) ? content + checkmethoddescr : "";
        } else {
            return "数据不能为空！";
        }
    }

    public static boolean checkCellRepeat(Cell cell, String content, Drawing drawing, String checkmethoddescr, CellStyle errorStyle, Map<String, String> haveData) {
        short format;
        Comment comment;
        if (!Tool.isNull(content) && !Tool.isNull(getCellValue(cell, ""))) {
            if (haveData != null && haveData.size() > 0 && !Tool.isNull((String)haveData.get(content))) {
                format = cell.getCellStyle().getDataFormat();
                cell.setCellStyle(errorStyle);
                cell.getCellStyle().setDataFormat(format);
                comment = drawing.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, 4, 2, 6, 5));
                if (comment instanceof XSSFComment) {
                    comment.setString(new XSSFRichTextString(content + checkmethoddescr));
                } else {
                    comment.setString(new HSSFRichTextString(content + checkmethoddescr));
                }

                cell.setCellComment(comment);
                return false;
            } else {
                return true;
            }
        } else {
            format = cell.getCellStyle().getDataFormat();
            cell.setCellStyle(errorStyle);
            cell.getCellStyle().setDataFormat(format);
            comment = drawing.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, 4, 2, 6, 5));
            if (comment instanceof XSSFComment) {
                comment.setString(new XSSFRichTextString("数据不能为空！"));
            } else {
                comment.setString(new HSSFRichTextString("数据不能为空！"));
            }

            cell.setCellComment(comment);
            return false;
        }
    }

    public static String excelColIndexToStr(int columnIndex) {
        if (columnIndex <= 0) {
            return null;
        } else {
            String columnStr = "";
            --columnIndex;

            do {
                if (columnStr.length() > 0) {
                    --columnIndex;
                }

                columnStr = (char)(columnIndex % 26 + 65) + columnStr;
                columnIndex = (columnIndex - columnIndex % 26) / 26;
            } while(columnIndex > 0);

            return columnStr;
        }
    }

    public static void setErrorStyleAndComment(Sheet sheet, Cell cell, String commentStr) {
        Drawing drawing = sheet.createDrawingPatriarch();
        short format = cell.getCellStyle().getDataFormat();
        cell.setCellStyle(getErrorStyle(sheet.getWorkbook()));
        cell.getCellStyle().setDataFormat(format);
        Comment comment = null;
        if (sheet instanceof XSSFSheet) {
            comment = drawing.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, 4, 2, 6, 5));
            comment.setString(new XSSFRichTextString(commentStr));
        } else {
            comment = drawing.createCellComment(new HSSFClientAnchor(0, 0, 0, 0, (short)4, 2, (short)6, 5));
            comment.setString(new HSSFRichTextString(commentStr));
        }

        cell.setCellComment(comment);
    }

    public static String getCommentStr(Cell cell) {
        String msg = "";
        Comment cellComment = cell.getCellComment();
        if (cellComment != null) {
            RichTextString richTextString = cellComment.getString();
            if (richTextString != null) {
                msg = richTextString.getString();
            }
        }

        return msg;
    }

    public static boolean checkRowEmpty(Row row, List<Map<String, Object>> excelfields) {
        if (row == null) {
            return false;
        } else {
            boolean flag = true;
            StringBuffer rowContent = new StringBuffer();

            for(int k = 0; k < excelfields.size(); ++k) {
                Cell cell = row.getCell(k);
                if (cell != null && 2 != cell.getCellType()) {
                    Map<String, Object> excelfield = (Map)excelfields.get(k);
                    String fieldtype = String.valueOf(excelfield.get("FIELDTYPE"));
                    String content = getCellValue(cell, fieldtype);
                    rowContent.append(content);
                }
            }

            if (StringUtils.isBlank(rowContent.toString())) {
                flag = false;
            }

            return flag;
        }
    }

    public static ExcelProp parseExcel(Workbook workbook, Sheet sheet, Map<String, Object> excelsheet, List<Map<String, Object>> excelfields, String dialet, Map<String, Object> variables, Map<String, Map<String, String>> repeatMap) {
        ExcelProp excelProp = new ExcelProp();
        excelProp.setChecked(true);
        List<String> sqlList = new ArrayList();
        ArrayList dataList = new ArrayList();

        try {
            CellStyle errorStyle = getErrorStyle(workbook);
            StringBuffer sb = new StringBuffer();
            int startrow_int = 0;
            String startrow = String.valueOf(excelsheet.get("STARTROW"));
            if (Tool.isInt(startrow)) {
                startrow_int = Integer.parseInt(startrow);
            }

            String tablename = String.valueOf(excelsheet.get("TABLENAME"));
            new ArrayList();
            sheet = recoverExcelStyle(workbook, sheet);
            Drawing drawing = sheet.createDrawingPatriarch();
            Integer rowNum = sheet.getLastRowNum() + 1;
            Integer cellNum = Integer.valueOf(sheet.getRow(0).getLastCellNum());
            sb.append("--------" + Tool.getCurrentDetailTime() + " sheet标签页【" + sheet.getSheetName() + "】中数据开始验证<br>");

            for(int i = startrow_int; i < rowNum.intValue(); ++i) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    StringBuffer rowContent = new StringBuffer();

                    String checkmethods;
                    for(int k = 0; k < excelfields.size(); ++k) {
                        Cell cell = row.getCell(k);
                        if (cell != null && 2 != cell.getCellType()) {
                            Map<String, Object> excelfield = (Map)excelfields.get(k);
                            String fieldtype = String.valueOf(excelfield.get("FIELDTYPE"));
                            checkmethods = getCellValue(cell, fieldtype);
                            rowContent.append(checkmethods);
                        }
                    }

                    Map<String, Object> dataMap = new HashMap();
                    Map<String, Object> dateFormat_map = new HashMap();
                    if (!StringUtils.isBlank(rowContent.toString())) {
                        for(int j = 0; j < excelfields.size(); ++j) {
                            Map<String, Object> excelfield = (Map)excelfields.get(j);
                            checkmethods = String.valueOf(excelfield.get("CHECKMETHOD"));
                            String intransform = String.valueOf(excelfield.get("INTRANSFORM"));
                            new HashMap();
                            Map<String, String> dictionaryMap_NameToCode = new HashMap();
                            if (excelfield.get("dictionaryMap_NameToCode") != null) {
                                dictionaryMap_NameToCode = (Map)excelfield.get("dictionaryMap_NameToCode");
                                Map var28 = (Map)excelfield.get("dictionaryMap_CodeToName");
                            }

                            String checkmethoddescr = String.valueOf(excelfield.get("CHECKMETHODDESCR"));
                            String fieldtype = String.valueOf(excelfield.get("FIELDTYPE"));
                            String fieldcode = String.valueOf(excelfield.get("FIELDCODE"));
                            String fielddefaultvalue = String.valueOf(excelfield.get("FIELDDEFAULTVALUE"));
                            String fieldcheckvalue = String.valueOf(excelfield.get("FIELDCHECKVALUE"));
                            int sheetcolnum = Integer.parseInt(String.valueOf(excelfield.get("SHEETCOLNUM")));
                            String content = null;
                            if (sheetcolnum >= 0) {
                                Cell cell = row.getCell(sheetcolnum);
                                if (cell == null) {
                                    cell = row.createCell(sheetcolnum);
                                }

                                int cellIndex = cell.getColumnIndex();
                                Cell mergeCell = getMergeCell(sheet, cell);
                                if (mergeCell != null) {
                                    content = getCellValue(mergeCell, fieldtype);
                                } else {
                                    content = getCellValue(cell, fieldtype);
                                }

                                if (Tool.isNull(checkmethoddescr)) {
                                    checkmethoddescr = "";
                                }

                                if (!Tool.isNull(intransform) && !intransform.startsWith("!")) {
                                    content = (String)((Map)dictionaryMap_NameToCode).get(content);
                                }

                                if (Tool.isNull(content) && !Tool.isNull(fielddefaultvalue) && "uuid".equalsIgnoreCase(fielddefaultvalue)) {
                                    content = Tool.getUUID();
                                }

                                String checkValue = content;
                                List<String> checkResultList = new ArrayList();
                                int b;
                                if (!Tool.isNull(checkmethods) && checkmethods.split(";").length > 0) {
                                    String[] checkmethoddescrs = checkmethoddescr.split(";");

                                    for(b = 0; b < checkmethods.split(";").length; ++b) {
                                        String checkmethod = checkmethods.split(";")[b];
                                        Map<String, String> dictionaryMap_CodeToName_check = new HashMap();
                                        new HashMap();
                                        if (excelfield.get("dictionaryMap_NameToCode_check") != null && ((Map)excelfield.get("dictionaryMap_NameToCode_check")).get(checkmethod) != null) {
                                            Map var10000 = (Map)((Map)excelfield.get("dictionaryMap_NameToCode_check")).get(checkmethod);
                                            dictionaryMap_CodeToName_check = (Map)((Map)excelfield.get("dictionaryMap_CodeToName_check")).get(checkmethod);
                                        }

                                        if (!Tool.isNull(checkmethod) && checkmethod.startsWith("NOREPEAT-")) {
                                            checkValue = "";
                                            List<String> checkMethodList = Arrays.asList(checkmethod.replace("NOREPEAT-", "").split("-"));
                                            Map<String, String> haveData = (Map)repeatMap.get(checkmethod);
                                            if (haveData == null) {
                                                haveData = new HashMap();
                                                repeatMap.put(checkmethod, haveData);
                                            }

                                            for(int c = 0; c < checkMethodList.size(); ++c) {
                                                if (fieldcode.equalsIgnoreCase((String)checkMethodList.get(c))) {
                                                    checkValue = checkValue + "[" + content + "]";
                                                } else {
                                                    checkValue = checkValue + "[" + dataMap.get(checkMethodList.get(c)) + "]";
                                                }
                                            }

                                            String checkResult = "";
                                            if (!Tool.isNull(checkResult = checkCellRepeat(cell, checkValue, checkmethoddescrs[b], (Map)haveData))) {
                                                checkResultList.add(checkResult);
                                                excelProp.setChecked(false);
                                            }

                                            ((Map)haveData).put(checkValue, checkValue);
                                        } else {
                                            String checkResult = "";
                                            if (!Tool.isNull(checkResult = checkCell(cell, checkValue, (Map)dictionaryMap_CodeToName_check, checkmethod, checkmethoddescrs[b], (Map)null))) {
                                                checkResultList.add(checkResult);
                                                excelProp.setChecked(false);
                                            }
                                        }
                                    }
                                }

                                if (!Tool.isNull(fieldtype) && StringUtils.startsWith(fieldtype, "DATE:") && !Tool.isNull(fieldtype.replace("DATE:", ""))) {
                                    String dateType = fieldtype.replace("DATE:", "");

                                    try {
                                        (new SimpleDateFormat(dateType)).parse(content);
                                    } catch (Exception var50) {
                                        excelProp.setChecked(false);
                                        checkResultList.add("日期类型不符合格式要求，应为" + dateType);
                                    }
                                }

                                if (checkResultList.size() > 0) {
                                    StringBuffer checkResultBuffer = new StringBuffer();

                                    for(b = 1; b <= checkResultList.size(); ++b) {
                                        if (checkResultList.size() > 1) {
                                            checkResultBuffer.append(b).append(" ").append((String)checkResultList.get(b - 1)).append("\r\n");
                                        } else {
                                            checkResultBuffer.append((String)checkResultList.get(b - 1)).append("\r\n");
                                        }
                                    }

                                    setErrorStyleAndComment(sheet, cell, checkResultBuffer.toString());
                                }
                            } else {
                                if (Tool.isNull(fielddefaultvalue)) {
                                    continue;
                                }

                                if (!Tool.isNull(fielddefaultvalue) && "uuid".equalsIgnoreCase(fielddefaultvalue)) {
                                    content = Tool.getUUID();
                                } else {
                                    Pattern placesParam = Pattern.compile("\\{.*\\}");
                                    Matcher matcherParam = placesParam.matcher(fielddefaultvalue);
                                    if (matcherParam.find()) {
                                        String key = matcherParam.group();
                                        key = key.substring(1, key.length() - 1);
                                        if (variables.containsKey(key)) {
                                            fielddefaultvalue = fielddefaultvalue.replace(matcherParam.group(), variables.get(key).toString());
                                        } else {
                                            fielddefaultvalue = fielddefaultvalue.replace(matcherParam.group(), "");
                                        }
                                    }

                                    content = fielddefaultvalue;
                                }
                            }

                            dataMap.put(fieldcode, content);
                            dateFormat_map.put(fieldcode, fieldtype);
                        }

                        sqlList.add(SqlUtils.getInsertSql(tablename, dataMap, dateFormat_map, dialet));
                        dataList.add(dataMap);
                    }
                }
            }

            excelProp.setSqlList(sqlList);
            excelProp.setDataList(dataList);
            sb.append("--------" + Tool.getCurrentDetailTime() + " sheet标签页【" + sheet.getSheetName() + "】中数据结束验证<br>");
            return excelProp;
        } catch (Exception var51) {
            var51.printStackTrace();
            excelProp.setChecked(false);
            return excelProp;
        }
    }

    public static void exportExecl(HttpServletResponse response, String fileName, List<DataSheet> sheets) {
        ExcelUtil.exportExcel(response, fileName, sheets);
    }

    public static Workbook writeExcel(Workbook wb, List<DataSheet> sheets) {
        return ExcelUtil.writeExcel(wb, sheets);
    }

    public static void exportExeclByTemplate(HttpServletResponse response, String filePath, String fileName, List<TemplateSheet> sheets, List<String> deleteSheets) throws Exception {
        ExcelUtil.exportExcelByTemplate(response, filePath, fileName, sheets, deleteSheets);
    }

    public static Workbook writeExcelByTemplate(String filePath, List<TemplateSheet> sheets, List<String> deleteSheets) {
        return ExcelUtil.writeExcelByTemplate(filePath, sheets, deleteSheets);
    }

    public static Cell getMergeCell(Sheet sheet, Cell cell) {
        Cell mergeCell = null;
        int sheetMergeCount = sheet.getNumMergedRegions();

        for(int i = 0; i < sheetMergeCount; ++i) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            if (range.isInRange(cell.getRowIndex(), cell.getColumnIndex())) {
                mergeCell = sheet.getRow(range.getFirstRow()).getCell(range.getFirstColumn());
                break;
            }
        }

        return mergeCell;
    }
}
