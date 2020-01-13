package cn.shenrs.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;


public class Tool {
    private static Logger log = Logger.getLogger(Tool.class);
    public final static String REG_INT = "[0-9]*";

    public final static String REG_DOUBLE1 = "^[-+]?\\d[dD]$";
    public final static String REG_DOUBLE2 = "^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)[dD]?$";
    public final static String REG_DOUBLE3 = "^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE][-+]?\\d+)?[dD]?$";
    public final static String REG_DOUBLE4 = "^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?[dD]?$       ";
    public final static String REG_DOUBLE = "-?([0]|([1-9]+\\d{0,}?))(.[\\d]+)?$";

    public final static String REG_ENGLISH_CHAR = "[a-zA-Z]*";

    public final static String REG_China_Char = "^[\u4e00-\u9fa5]+$";
    public final static String REG_CALCULATOR = "([0-9]|[+,\\-,\\*,/,(,)])*";

    public final static String FILTERSTRING_TYPE_TABLE = "table";

    public final static String FILTERSTRING_TYPE_TITLE = "title";

    /**
     * 判断字符串是否为int
     *
     * @param str
     * @return
     */
    public static boolean isInt(String str) {
        boolean result = false;
        try {
            if (str == null) {
                return result;
            }
            str = str.replace(",", "");
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return result;
        }
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return true:字符串为空
     * false:不为空
     */
    public static boolean isNull(String str) {
        boolean result = false;
        if (str != null && !"".equals(str) && !"null".equalsIgnoreCase(str) && !"NaN".equalsIgnoreCase(str)) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    /**
     * 字符串去空
     *
     * @param str
     * @return true:字符串为空
     */
    public static String replaceNull(String str) {
        String result = "";
        if (!isNull(str)) {
            result = str;
        }
        return result;
    }

    /**
     * 判断字符串是否为double
     *
     * @param str
     * @return
     */
    public static boolean isDouble(String str) {
        boolean result = false;
        try {
            if (Tool.isNull(str)) {
                result = false;
            } else {
                double d = Double.parseDouble(str);
                if (!Double.isInfinite(d) && !Double.isNaN(d)) {
                    result = true;
                } else {
                    log.warn(str + ":" + d);
                }
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * 判断字符串是全是英文字符串
     *
     * @param str
     * @return
     */
    public static boolean isEnglisChar(String str) {
        if (str == null) {
            return false;
        } else {
            return str.matches(REG_ENGLISH_CHAR);
        }
    }

    /**
     * 判断字符串是全是中文字符串
     *
     * @param str
     * @return
     */
    public static boolean isChinaChar(String str) {
        if (str == null) {
            return false;
        } else {
            return str.matches(REG_China_Char);
        }

    }

    /**
     * 判断字符串是含有中文字符串
     *
     * @param str
     * @return
     */
    public static boolean isHaveChinaChar(String str) {
        if (str == null) {
            return false;
        } else {
            Pattern pa = Pattern.compile("[\u4E00-\u9FA0]", Pattern.CANON_EQ);
            Matcher ma = pa.matcher(str);
            return ma.find();
        }
    }

    /**
     * 判断字符串是否是IP地址
     *
     * @param str
     * @return
     */
    public static boolean isIP(String str) {
        if (str == null) {
            return false;
        }
        String regex0 = "(2[0-4]\\d)" + "|(25[0-5])";
        String regex1 = "1\\d{2}";
        String regex2 = "[1-9]\\d";
        String regex3 = "\\d";
        String regex = "(" + regex0 + ")|(" + regex1 + ")|(" + regex2 + ")|(" + regex3 + ")";
        regex = "(" + regex + ").(" + regex + ").(" + regex + ").(" + regex + ")";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 判断字符串是否符合文件名规则，符合返回，不符合处理后返回
     *
     * @param file
     * @return
     */
    public static String getPrintName(String file) {
        try {
            String regex = "[?\\*<>|\"/:]";
            if (!Tool.isNull(file)) {
                if (file.indexOf("../") != -1) {
                    for (; ; ) {
                        file = file.replaceAll("../", "");
                        if (file.indexOf("../") == -1) {
                            break;
                        }
                    }
                }
                if (file.indexOf("'") != -1 || file.indexOf("\\") != -1 || file.indexOf("/") != -1 || file.indexOf("<") != -1 || file.indexOf(">") != -1 || file.indexOf(":") != -1 || file.indexOf("*") != -1 || file.indexOf("?") != -1 || file.indexOf("\"") != -1 || file.indexOf("|") != -1) {
                    if (file.indexOf("\\") != -1) {
                        file = file.replace("\\", "");
                    }
                    if (file.indexOf("'") != -1) {
                        file = file.replace("'", "");
                    }
                    if (file.indexOf("/") != -1) {
                        file = file.replace("/", "");
                    }
                    file = file.replaceAll(regex, "");
                }
                if (file.subSequence(0, 1).equals(".")) {
                    for (; ; ) {
                        file = file.substring(1);
                        if (!file.subSequence(0, 1).equals(".")) {
                            break;
                        }
                    }
                }
                String tempFile = file;
                if (file.getBytes().length > 20) {
                    String delFile = "";
                    String sub_file = file;
                    int end = file.length();
                    if (file.length() > 20) {
                        sub_file = file.substring(0, 20);
                        end = 20;
                    }
                    for (int i = 0; i <= 10; i++) {
                        int bl = sub_file.substring(0, end - i).getBytes().length;
                        if (bl <= 20) {
                            delFile = file.substring(end - i);
                            file = sub_file.substring(0, end - i);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
        }

        return file;
    }

    public static double round(double v, int scale) {
        if (scale < 0) {
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static String calculator(String formula) {
        try {
            formula = formula.replaceAll(" ", "");
            boolean can = formula.matches(REG_CALCULATOR);
            if (can) {
            } else {
            }
            return formula;
        } catch (Exception e) {
        }
        return formula;
    }

    public static String formatNum(double value, int maxDigits) {
        String retValue = null;
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(0);
        df.setMaximumFractionDigits(maxDigits);
        retValue = df.format(value);
        retValue = retValue.replaceAll(",", "");
        if (Tool.isNull(retValue) || "0".equals(retValue)) {
            retValue = "";
        }
        return retValue;
    }

    /**
     * 验证是否为制定时间格式的字符串
     *
     * @param strDateTime
     * @param type
     * @return
     */
    public static boolean checkDateFormatAndValite(String strDateTime, String type) {
        try {
            if (strDateTime == null || "".equals(strDateTime)) {
                return false;
            }
            SimpleDateFormat formatter = new SimpleDateFormat(type);
            String str = formatter.format(formatter.parse(strDateTime));
            if (str.equals(strDateTime)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static String substring(String str, int length) {
        if (isNull(str)) {
            return str;
        }
        if (str.length() > length) {
            return str.substring(0, length) + "...";
        } else {
            return str;
        }
    }

    /**
     * 拼接js脚本的参数，替换特殊字符<br>
     * 替换三个特殊字符：反斜杠\单引号'双引号"<br>
     * 替换/r/n 标签为html的BR标签
     *
     * @param strValue
     * @return
     */
    public static String changeJsStr(String strValue) {
        String str = strValue;
        if (strValue != null) {
            // 替换/r/n 标签为html的BR标签
            str = str.replaceAll("\n|\r\n|\n\r", "");
            // 替换三个特殊字符：反斜杠\单引号'双引号"
            str = str.replaceAll("\\\\", "\\\\\\\\");
            str = str.replaceAll("'", "\\\\'");
            str = str.replaceAll("\"", "\\&quot;");
        }
        return str;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        return time.substring(0, 10);
    }

    /**
     * 获取制定格式的当前时间
     *
     * @return
     */
    public static String getCurDateByType(String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        String time = sdf.format(new Date());
        return time;
    }

    /**
     * 获取当前时间,具体到秒
     *
     * @return
     */
    public static String getCurrentDetailTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        return time;
    }


    /**
     * @param date 时间
     * @param type 格式  24小时制(yyyy-MM-dd HH:mm:ss)
     * @return 13位毫秒值
     */
    public static String getMillisecond(String date, String type) {
        long millisecond = 0;
        String typedefult = "yyyy-MM-dd";
        try {
            if (Tool.isNull(type)) {
                type = typedefult;
            } else if ("YYYYMMDD".equals(type)) {
                date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
                type = "yyyy-MM-dd";
            }
            SimpleDateFormat formatter = new SimpleDateFormat(type);
            millisecond = formatter.parse(date).getTime();
        } catch (Exception e) {
        }
        return String.valueOf(millisecond);
    }

    /**
     * @param Millisecond 13为毫秒值
     * @param type        要返回的时间模式   24小时制(yyyy-MM-dd HH:mm:ss)
     * @return 时间字符串
     */
    public static String getDateFromMillisecond(String Millisecond, String type) {
        long millisecond = 0;
        String result = "";
        String typedefult = "yyyy-MM-dd";
        try {
            if (Tool.isNull(type)) {
                type = typedefult;
            }
            SimpleDateFormat formatter = new SimpleDateFormat(type);
            millisecond = Long.parseLong(Millisecond);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(millisecond);
            result = formatter.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getMD5(String str) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] s = md.digest(str.getBytes());
            String ss = "";
            for (int i = 0; i < s.length; i++) {
                ss = Integer.toHexString(s[i] & 0xff);
                if (ss.length() == 1) {
                    result += "0" + ss;
                } else {
                    result += ss;
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 二维数组，交叉列表
     *
     * @param rowsql  行sql
     * @param colsql  列sql
     * @param datasql 数据sql
     * @return String二维数组
     * @throws Exception
     */
    public static String[][] getCross(String rowsql, String colsql, String datasql) throws Exception {
        String[][] frameArr = null;
        /*AppBo ab = new AppBo();
        ArrayList<HashMap> rowlist = new ArrayList<HashMap>();
		rowlist = ab.query(rowsql);
		ArrayList<HashMap> collist = new ArrayList<HashMap>();
		collist = ab.query(colsql);
		ArrayList<HashMap> datalist = new ArrayList<HashMap>();
		if(!Tool.isNull(datasql)){
			datalist = ab.query(datasql);
		}
		frameArr = new String[rowlist.size() + 2][collist.size() + 2];
		String value = "";
		// 放入确定行的数据
		for (int i = 0; i < rowlist.size(); i++) {
			HashMap<String,String> row = new HashMap<String,String>();
			row = rowlist.get(i);
			frameArr[i + 2][0] = row.get("RKEY").trim();
			frameArr[i + 2][1] = row.get("RVALUE").trim();
		}
		// 放入确定列的数据
		for (int i = 0; i < collist.size(); i++) {
			HashMap<String,String> col = new HashMap<String,String>();
			col = collist.get(i);
			frameArr[0][i+2] = col.get("CKEY").trim();
			frameArr[1][i+2] = col.get("CVALUE").trim();
		}
		
		// 数据
		HashMap<String, String> dataMap = new HashMap<String, String>();
		for (int i = 0; i < datalist.size(); i++) {
			HashMap<String,String> data = new HashMap<String,String>();
			data = datalist.get(i);
			dataMap.put(data.get("RKEY")+"_"+data.get("CKEY"), data.get("ADATA"));
		}
		for (int row = 0; row < frameArr.length; row++) {
			for (int col = 0; col < frameArr[row].length; col++) {
				String Str_value = frameArr[row][0] + '_' + frameArr[0][col];
				if (!Tool.isNull(dataMap.get(Str_value))) {
					frameArr[row][col] = dataMap.get(Str_value);
				}
			}
		}*/
        return frameArr;
    }

    /**
     * @param cfili
     * @param datetype
     * @param newtype
     * @return
     */
    public static String getDateByType(String date, String oldtype, String newtype) {

        SimpleDateFormat format1 = new SimpleDateFormat(oldtype);
        Date da;
        try {

            da = format1.parse(date);
            SimpleDateFormat format2 = new SimpleDateFormat(newtype);

            date = format2.format(da);

        } catch (ParseException e) {
            //e.printStackTrace();
        }
        return date;
    }

    /**
     * @return
     */
    public static String getCurrentYear() {
        String rtn = "";
        String date = getCurrentTime();
        String year = date.substring(0, 4);
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date da = format.parse(year);
            rtn = format1.format(da);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rtn;
    }

    /**
     * 设置插入语句  返回sql语句
     */
    public static HashMap<String, Object> getInsertSql(String table, Map caseMap) {
        String field_sql = "";
        String field_value = "";
        List<Object> param_list = new ArrayList<Object>();
        Set<String> keySet = caseMap.keySet();
        for (Object key : keySet) { //循环map
            if (caseMap.get(key) != null) {
                if (!"".equals(field_sql)) {
                    field_sql = field_sql + ",";
                    field_value = field_value + ",";
                }
                field_sql = field_sql + key;
                field_value = field_value + "?";
                param_list.add(caseMap.get(key));
            }
        }
        String sql = "insert into " + table + " (" + field_sql + ") values (" + field_value + ")";
        HashMap<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("sql", sql);
        returnMap.put("param", param_list);
        return returnMap;
    }

    /**
     * 设置插入语句  返回sql语句
     */
    public static HashMap<String, Object> getUpdateSql(String table, Map caseMap, String pkid) {
        String field_sql = "";
        List<Object> param_list = new ArrayList<Object>();
        Set<String> keySet = caseMap.keySet();
        for (Object key : keySet) { //循环map
            if (caseMap.get(key) != null) {
                if (!"".equals(field_sql)) {
                    field_sql = field_sql + ",";
                }
                field_sql = field_sql + key + "=?";
                param_list.add(caseMap.get(key));
            }
        }
        String sql = "update " + table + " set " + field_sql + " where " + pkid + "='" + String.valueOf(caseMap.get(pkid)) + "'";
        HashMap<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("sql", sql);
        returnMap.put("param", param_list);
        return returnMap;
    }

    /**
     * 利用Introspector和PropertyDescriptor 将Bean --> Map
     *
     * @param obj
     * @return
     */
    public static HashMap<String, Object> transBean2Map(Object obj) {
        if (obj == null) {
            return null;
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }
        return map;
    }

    /**
     * 利用Introspector和PropertyDescriptor 将Bean --> Map
     * map中的key大小写都存了一份
     *
     * @param obj
     * @return
     */
    public static HashMap<String, Object> transBean2MapIgnoreCase(Object obj) {
        if (obj == null) {
            return null;
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        HashMap<String, Object> return_map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }

            //重构
            Set<String> keySet = map.keySet();
            for (Object key : keySet) { //循环map
                if (map.get(key) != null) {
                    return_map.put(String.valueOf(key).toLowerCase(), map.get(key));
                    return_map.put(String.valueOf(key).toUpperCase(), map.get(key));
                }
            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }
        return return_map;
    }

    /**
     * 利用org.apache.commons.beanutils 工具类实现 Map --> Bean
     *
     * @param map
     * @param obj
     */
    public static Object transMap2Bean(Map<String, Object> map, Object obj) {
        try {
            BeanUtils.populate(obj, map);
        } catch (Exception e) {
            System.out.println("transMap2Bean2 Error " + e);
        }
        return obj;
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 拆分字符串为SET数组，用逗号分隔
     *
     * @param str
     * @return
     */
    public static HashSet<Integer> getStrToList(String str, String splitStr) {
        HashSet<Integer> set = new HashSet<Integer>();
        if (str.contains(splitStr)) {
            String[] rowArr = str.split(splitStr);
            for (int i = 0; i < rowArr.length; i++) {
                if (Tool.isInt(rowArr[i]) && Integer.parseInt(rowArr[i]) - 1 >= 0) {
                    set.add(Integer.parseInt(rowArr[i]) - 1);
                }
            }
        } else {
            if (Tool.isInt(str) && Integer.parseInt(str) - 1 >= 0) {
                set.add(Integer.parseInt(str) - 1);
            }
        }
        return set;
    }

    /**
     * 拆分字符串为SET数组，用逗号分隔
     *
     * @param str
     * @return
     */
    public static List<String> getStrToStrList(String str, String splitStr) {
        List<String> list = new ArrayList<String>();
        if (str.contains(splitStr)) {
            String[] rowArr = str.split(splitStr);
            for (int i = 0; i < rowArr.length; i++) {
                if (!Tool.isNull(rowArr[i])) {
                    list.add(rowArr[i]);
                } else {
                    list.add("");
                }
            }
        } else {
            if (!Tool.isNull(str)) {
                list.add(str);
            }
        }
        return list;
    }
}
