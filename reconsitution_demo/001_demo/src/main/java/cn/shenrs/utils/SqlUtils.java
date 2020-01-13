package cn.shenrs.utils;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.Reader;
import java.sql.Clob;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * SQL工具类
 * 
 * @author anjh
 * @date 2015年7月20日 下午10:56:42
 * @version 1.0
 */
public class SqlUtils {

	/**
	 * 根据表和字段内容返回查詢总数sql语句
	 * 
	 * @param tableName
	 * @param dataMap
	 * @return
	 */
	public static String getSelectCountSql(String tableName,
                                           List<String> pkid_list, Map<String, Object> dataMap) {
		String sql ="";
		if(isSafety(tableName,pkid_list,dataMap,null)){
			String wherePart = " 1=1 ";
			if (null != pkid_list && pkid_list.size() > 0) {
				for (int i = 0; i < pkid_list.size(); i++) {
					String pkid = pkid_list.get(i);
					wherePart = wherePart + " and " + pkid + "='"+ StringEscapeUtils.escapeSql(String.valueOf(dataMap.get(pkid))) + "'";
				}
			}
			sql = "select count(1) C from " + tableName + " where " + wherePart;
		}
		return sql;
	}
	
	/**
	 * 根据表和字段内容返回查詢sql语句
	 * 
	 * @param tableName
	 * @param dataMap
	 * @return
	 */
	public static String getSelectSql(String tableName,
                                      List<String> pkid_list, Map<String, Object> dataMap, List<String> orderBy_list) {
		String sql = "";
		if(isSafety(tableName,pkid_list,dataMap,orderBy_list)){
			String wherePart = " 1=1 ";
			if (null != pkid_list && pkid_list.size() > 0) {
				for (int i = 0; i < pkid_list.size(); i++) {
					String pkid = pkid_list.get(i);
						wherePart = wherePart + " and " + pkid + "='"+ StringEscapeUtils.escapeSql(String.valueOf(dataMap.get(pkid))) + "'";
				}
			}
			sql = "select * from " + tableName + " where " + wherePart ;
			
			String orderByPart = "";
			if(null != orderBy_list && orderBy_list.size() > 0){
				for(int i=0;i<orderBy_list.size();i++){
					if(!Tool.isNull(orderByPart)){
						orderByPart = orderByPart + ",";
					}
					orderByPart = orderByPart + orderBy_list.get(i);
				}
			}
			if(!Tool.isNull(orderByPart)){
				sql = sql + " order by  "+orderByPart;
			}
		}
		return sql;
	}
	/**
	 * 根据表和字段内容返回查詢sql语句
	 * 
	 * @param tableName
	 * @param dataMap
	 * @return
	 */
	public static String getSelectSql(String tableName,
                                      List<String> pkid_list, Map<String, Object> compare_map, Map<String, Object> dataMap, List<String> orderBy_list) {
		String sql = "";
		if(isSafety(tableName,pkid_list,dataMap,orderBy_list)){
			String wherePart = " 1=1 ";
			if (null != pkid_list && pkid_list.size() > 0) {
				for (int i = 0; i < pkid_list.size(); i++) {
					String pkid = pkid_list.get(i);
					String compare = String.valueOf(compare_map.get(pkid));
					if(Tool.isNull(compare)){
						compare = "=";
					}
					if("like".equalsIgnoreCase(compare)){//针对比较符为like的处理
						wherePart = wherePart + " and " + pkid + " "+compare+" '%"+ StringEscapeUtils.escapeSql(String.valueOf(dataMap.get(pkid))) + "%' ";
					}else if(compare.startsWith("datelike")){//如果是日期，需要将日期进行处理,主要是针对电子表单的日期的处理,datelike:dialet:format
						String[] compareArray=compare.split(":");
						String format=compareArray[2];
						String dialet=compareArray[1];
						wherePart = wherePart + " and " + dateFieldToChar(pkid,format,dialet) + " like '"+ StringEscapeUtils.escapeSql(String.valueOf(dataMap.get(pkid))) + "%' ";
					}else if(compare.startsWith("in")){//针对比较符为in的处理,比较符的格式为in:value
						//获取比较符
						String[] compareArray=compare.split(":");
						String compareStyle=compareArray[1];
						String value= String.valueOf(dataMap.get(pkid));
						//如果比较符为in,那么会将值用||分割，作为in的值
						String[] values=value.split("\\|");
						//如果比较符为=，则采用in的方式拼接
						if("=".equals(compareStyle)){
							wherePart = wherePart + " and " +pkid + " in ("+ StringEscapeUtils.escapeSql(StringUtils.join(values, ",")).replace("\"", "'")+ ") ";
						}else{
							//如果比较符为like,则采用"or"的方式拼接
							List<String> finalValue=new ArrayList<String>();
							for(String temp:values){
								finalValue.add(pkid+" "+compareStyle+" "+ StringEscapeUtils.escapeSql(temp).replace("\"", "'"));
							}
							wherePart = wherePart + " and ("+ StringUtils.join(finalValue, " or ")+ ") ";
						}
					}else if(compare.startsWith("date")){//针对元数据平台主题的日期的处理，日期类型的比较符格式为date;dialect;format;compare
						String[] compareArray=compare.split("\\;");
						String dialect=compareArray[1];
						String format=compareArray[2];
						String compareStr=compareArray[3];
						if("like".equals(compareStr)){
							wherePart= wherePart + " and " + dateFieldToChar(pkid,format,dialect) + " like '"+ StringEscapeUtils.escapeSql(String.valueOf(dataMap.get(pkid))) + "%' ";
						}else if("between".equals(compareStr)){
							String betweenValue= StringEscapeUtils.escapeSql(String.valueOf(dataMap.get(pkid)));
							String[] values=betweenValue.split("~");
							StringBuffer betweenBuffer=new StringBuffer();
							if(!Tool.isNull(values[0])){
								if("null".equals(format)){//如果格式化类型为null,则不需要将字段进行格式化
									betweenBuffer.append(pkid).append(">=").append("'"+values[0]+"'");
								}else{
									betweenBuffer.append(dateFieldToChar(pkid,format,dialect)).append(">=").append("'"+values[0]+"'");
								}
							}
							if(values.length>1 && !Tool.isNull(values[1])){
								if(betweenBuffer.length()>0){
									betweenBuffer.append(" and ");
								}
								if("null".equals(format)){//如果格式化类型为null,则不需要将字段进行格式化
									betweenBuffer.append(pkid).append("<=").append("'"+values[1]+"'");
								}else{
									betweenBuffer.append(dateFieldToChar(pkid,format,dialect)).append("<=").append("'"+values[1]+"'");
								}
								
							}
							wherePart= wherePart + " and ("+betweenBuffer.toString()+")";
						}else{
							wherePart= wherePart + " and " + dateFieldToChar(pkid,format,dialect) +" "+compareStr+"'"+ StringEscapeUtils.escapeSql(String.valueOf(dataMap.get(pkid))) + "' ";
						}
					}else{
						wherePart = wherePart + " and " + pkid + ""+compare+"'"+ StringEscapeUtils.escapeSql(String.valueOf(dataMap.get(pkid))) + "'";
					}
				}
			}
			sql = "select * from " + tableName + " where " + wherePart ;
			
			String orderByPart = "";
			if(null != orderBy_list && orderBy_list.size() > 0){
				for(int i=0;i<orderBy_list.size();i++){
					if(!Tool.isNull(orderByPart)){
						orderByPart = orderByPart + ",";
					}
					orderByPart = orderByPart + orderBy_list.get(i);
				}
			}
			if(!Tool.isNull(orderByPart)){
				sql = sql + " order by  "+orderByPart;
			}
		}
		return sql;
	}
	
	/**
	 * 根据表和字段内容返回sql语句
	 * 
	 * @param tableName
	 * @param dataMap
	 * @return
	 */
	public static String getInsertSql(String tableName,
                                      Map<String, Object> dataMap) {
		String sql = "";
		if(isSafety(tableName,null,dataMap,null)){
			String field_sql = "";
			String field_value = "";
			Set<String> keySet = dataMap.keySet();
			for (Object key : keySet) { // 循环map
				if (!Tool.isNull(String.valueOf(dataMap.get(key)))) {
					if (!"".equals(field_sql)) {
						field_sql = field_sql + ",";
						field_value = field_value + ",";
					}
					field_sql = field_sql + key;
					field_value = field_value + "'" + StringEscapeUtils.escapeSql(String.valueOf(dataMap.get(key))) + "'";
				}
			}
			sql = "insert into " + tableName + " (" + field_sql
					+ ") values (" + field_value + ")";
		}
		return sql;
	}
	
	public static String getInsertSql(String tableName,
                                      Map<String, Object> dataMap, Map<String, Object> dateFormat_map, String dialet) {
		String sql = "";
		if(isSafety(tableName,null,dataMap,null)){
			String field_sql = "";
			String field_value = "";
			Set<String> keySet = dataMap.keySet();
			for (Object key : keySet) { // 循环map
				Object object = dataMap.get(key);
				if (!Tool.isNull(String.valueOf(object))) {
					if(object instanceof String[]){
						String[] strArray=(String[])object;
						StringBuffer sb=new StringBuffer();
						for(String str:strArray)
							sb.append(str).append(",");
						sb.deleteCharAt(sb.length()-1);
						dataMap.put((String)key, sb.toString());
					}
					if (!"".equals(field_sql)) {
						field_sql = field_sql + ",";
						field_value = field_value + ",";
					}
					field_sql = field_sql + key;
					
					boolean isMustChangeDate = false; 
					String dateFormat = "";
					if(dateFormat_map!=null&&!Tool.isNull(String.valueOf(dateFormat_map.get(key)))){
						 dateFormat = String.valueOf(dateFormat_map.get(key));
						if(dateFormat.startsWith("date")||dateFormat.startsWith("DATE")){
							isMustChangeDate = true;
							dateFormat = dateFormat.replaceFirst("DATE:", "").replaceFirst("date:", "");
						}
					}
					
					if(isMustChangeDate){//处理日期类型
						field_value = field_value + getCharToDate(StringEscapeUtils.escapeSql(String.valueOf(dataMap.get(key))),dateFormat,dialet);
					}else{
						field_value = field_value + "'" + StringEscapeUtils.escapeSql(String.valueOf(dataMap.get(key))) + "'";
					}
				}
			}
			sql = "insert into " + tableName + " (" + field_sql
					+ ") values (" + field_value + ")";
		}
		return sql;
	}

	
	/**
	 * 根据表和字段内容返回sql语句
	 * 
	 * @param table
	 * @param dataMap
	 * @param pkid_list
	 * @return
	 */
	public static String getUpdateSql(String table,
                                      Map<String, Object> dataMap, List<String> pkid_list) {
		String sql = "";
		if(isSafety(table,pkid_list,dataMap,null)){
			String field_sql = "";
			Set<String> keySet = dataMap.keySet();
			for (Object key : keySet) { // 循环map
				if (dataMap.get(key)!=null) {
					if (!"".equals(field_sql)) {
						field_sql = field_sql + ",";
					}
					if(Tool.isNull((String.valueOf(dataMap.get(key))))){
						field_sql = field_sql + key + " = NULL ";
					}else{
						field_sql = field_sql + key + "='" + StringEscapeUtils.escapeSql(String.valueOf(dataMap.get(key))) + "'";
					}
				}
			}
			String wherePart = " where 1=1 ";
			for (int i = 0; i < pkid_list.size(); i++) {
				String pkid = pkid_list.get(i);
				wherePart = wherePart + " and " + pkid + "='"
						+ String.valueOf(StringEscapeUtils.escapeSql(String.valueOf(dataMap.get(pkid)))) + "'";
			}
		    sql = "update " + table + " set " + field_sql + wherePart;
		}
		return sql;
	}
	/**
	 * 根据表和字段内容返回sql语句
	 * 
	 * @param table
	 * @param dataMap
	 * @param pkid_list
	 * @return
	 */
	public static String getUpdateSql(String table,
                                      Map<String, Object> dataMap, List<String> pkid_list, Map<String, Object> dateFormat_map, String dialet) {
		String sql = "";
		if(isSafety(table,pkid_list,dataMap,null)){
			String field_sql = "";
			Set<String> keySet = dataMap.keySet();
			for (Object key : keySet) { // 循环map
				if (dataMap.get(key)!=null) {
					Object object = dataMap.get(key);
					if(object instanceof String[]){
						String[] strArray=(String[])object;
						StringBuffer sb=new StringBuffer();
						for(String str:strArray)
							sb.append(str).append(",");
						sb.deleteCharAt(sb.length()-1);
						dataMap.put((String)key, sb.toString());
					}
					if (!"".equals(field_sql)) {
						field_sql = field_sql + ",";
					}
					
					boolean isMustChangeDate = false; 
					String dateFormat = "";
					if(dateFormat_map!=null&&!Tool.isNull(String.valueOf(dateFormat_map.get(key)))){
						dateFormat = String.valueOf(dateFormat_map.get(key));
						if(dateFormat.startsWith("date")||dateFormat.startsWith("DATE")){
							isMustChangeDate = true;
							dateFormat = dateFormat.replaceFirst("DATE:", "").replaceFirst("date:", "");
						}
					}
					
					if(isMustChangeDate){//处理日期类型
						field_sql = field_sql + key + "=" + getCharToDate(StringEscapeUtils.escapeSql(String.valueOf(dataMap.get(key))),dateFormat,dialet) + "";
					}else{
						if(Tool.isNull((String.valueOf(dataMap.get(key))))){
							field_sql = field_sql + key + " = NULL ";
						}else{
							field_sql = field_sql + key + "='" + StringEscapeUtils.escapeSql(String.valueOf(dataMap.get(key))) + "'";
						}
					}
				}
			}
			String wherePart = " where 1=1 ";
			for (int i = 0; i < pkid_list.size(); i++) {
				String pkid = pkid_list.get(i);
				if(Tool.isNull((String.valueOf(dataMap.get(pkid))))){
					wherePart = wherePart + " and " + pkid + " is NULL ";
				}else{
					wherePart = wherePart + " and " + pkid + "='" + String.valueOf(StringEscapeUtils.escapeSql(String.valueOf(dataMap.get(pkid)))) + "'";
				}
				
			}
		    sql = "update " + table + " set " + field_sql + wherePart;
		}
		return sql;
	}
	
	/**
	 * 根据表和字段内容返回删除数据sql语句
	 * 
	 * @param tableName
	 * @param dataMap
	 * @return
	 */
	public static String getDeleteSql(String tableName,
                                      List<String> pkid_list, Map<String, Object> dataMap) {
		String sql = "";
		if(isSafety(tableName,pkid_list,dataMap,null)){
			String wherePart = " 1=1 ";
			if (null != pkid_list && pkid_list.size() > 0) {
				for (int i = 0; i < pkid_list.size(); i++) {
					String pkid = pkid_list.get(i);
						wherePart = wherePart + " and " + pkid + "='"+ StringEscapeUtils.escapeSql(String.valueOf(dataMap.get(pkid))) + "'";
				}
			}
			sql = "delete from " + tableName + " where " + wherePart;
		}
		return sql;
	}
	
	/**
	 * 日期
	 * @param str
	 * @param format
	 * @param dialet
	 * @return
	 */
	public static String getCharToDate(String str, String format, String dialet){
		String function = "";
		if(dialet.equals("oracle")){
			format = format == null ? "YYYY-MM-DD" : format.replace("HH:mm", "hh24:mi");
			function = "to_date('" + str + "','"+format+"')";
		}else if(dialet.equals("db2")){
			format = format == null ? "YYYY-MM-DD" : format.replace("HH:mm", "hh24:mi");
			function = "to_date('" + str + "','"+format+"')";
		}else if(dialet.equals("mysql")){
			function = "'" + str + "'";
		}else if(dialet.equals("sqlserver")){
			function = "CAST('" + str + "' AS datetime)";
		}else{
			function = "'"+str+"'";
		}		
		return function;
	}
	
	/**
	 * 将日期字段进行字符串格式化
	 * @param field
	 * @param format
	 * @param dialet
	 * @return
	 */
	public static String dateFieldToChar(String field, String format, String dialet){
		String function="";
		if(dialet.equals("oracle") || dialet.equals("db2")){
			format = format == null ? "YYYY-MM-DD" : format.replace("HH:mm", "hh24:mi");
			function = "to_char(" + field + ",'"+format+"')";
		}else if(dialet.equals("mysql")){
			function = field;
		}else if(dialet.equals("sqlserver")){
			format = format == null ? "YYYY-MM-DD" : format;
			if("yyyy-MM-dd".equalsIgnoreCase(format))
				function="CONVERT(varchar(100),"+field+", 23)";
			else if("yyyy-MM".equalsIgnoreCase(format))
				function="CONVERT(varchar(100),"+field+", 126)";
			else if("yyyy".equalsIgnoreCase(format))
				function="DATEPART(yyyy,"+field+")";
			else if("yyyy-MM-dd HH:mm:ss".equalsIgnoreCase(format))
				function="CONVERT(varchar(100),"+field+", 120)";
			else
				function=field;
		}else{
			function = field;
		}
		return function;
	}

	/**
	 * 替换sql语句中的参数
	 * @param sql
	 * @param paramMap
	 * @return
	 */
	public static String replaceSqlParam(String sql, Map<String, Object> paramMap) {
		    if(Tool.isNull(sql)){ 
		    	return sql;
		    }
		    
		    //System.out.println("解析sql中的函数前："+sql);
		    Pattern placesFn = Pattern.compile("\\/\\*[\\s\\S]*\\*\\/");
		    
		    while(sql.indexOf( "*/" )>=0){
		        String sql1=sql.substring( 0,sql.indexOf( "*/" ) +2);
		        String sql2=sql.substring( sql.indexOf( "*/" )+2,sql.length( ) );
		        Matcher matcherFn = placesFn.matcher(sql1);
				 if (matcherFn.find()) {
				       System.out.println("sql中的函数部分："+matcherFn.group(0));
				       String fn=matcherFn.group(0);
				       String resultFn= fn.substring( 2,fn.length( )-2 );
				       Pattern placesParam = Pattern.compile("\\{.*\\}");
				       Matcher matcherParam = placesParam.matcher(fn);
				       if(matcherParam.find()){
				           String key=matcherParam.group(0);
				          // System.out.println("sql中函数的参数部分:"+matcherParam.group(0));
				           key=key.substring( 1,key.length( )-1);
				           //System.out.println("sql中函数的参数key:"+key);
				           if(paramMap!=null && paramMap.get( key )!=null && !"".equals(paramMap.get( key ))){
				                resultFn=resultFn.replace( matcherParam.group(0) , StringEscapeUtils.escapeSql(String.valueOf(paramMap.get( key ))) );
				                sql1=sql1.replace( fn , resultFn );
				           }else{
				        	   sql1=sql1.replace( fn , "" );
				           }
				       }else{
				    	   sql1=sql1.replace( fn , " " );
				       }
				    }
		 
				 sql=sql1+sql2;
		    }
		   // System.out.println("解析sql中的函数后："+sql);
	        return sql;
	}
	
	/**
	 * 验证是否是字段或者表名称
	 * @param str
	 * @return
	 */
	private  static  boolean isSafety(String checkStr, List<String> checkList, Map<String, Object> checkMap, List<String> orderBy_list){
		//支持匹配中文
		String regex = "^(\\w|[\u4e00-\u9fa5])+\\.(\\w|[\u4e00-\u9fa5])+|(\\w|[\u4e00-\u9fa5])+$";
		//数据库表名，需要兼容schema.tableName模式
		if(!checkStr.matches ("^\\w+\\.\\w+|\\w+$")){
			System.out.println("数据库表和 字段名称只能包含数字和下划线，【"+checkStr+"】未通过验证");
			return false;
		}
		if(checkList!=null){
			for(int i=0;i<checkList.size();i++){
				if(!checkList.get(i).matches (regex)){
					System.out.println("数据库表和 字段名称只能包含数字和下划线，【"+checkList.get(i)+"】未通过验证");
					return false;
				}
			}
		}
		if(orderBy_list!=null){
			for(int i=0;i<orderBy_list.size();i++){
				if(!orderBy_list.get(i).replace(" ", "").matches (regex)){
					System.out.println("数据库表和 字段名称只能包含数字和下划线，【"+checkList.get(i)+"】未通过验证");
					return false;
				}
			}
		}
		if(checkMap!=null){
			 Iterator it = checkMap.entrySet().iterator();
			 while (it.hasNext()) {
			    Map.Entry entry = (Map.Entry) it.next();
			    String key = String.valueOf(entry.getKey());
			    String value = String.valueOf(entry.getValue());
			    if(!key.matches (regex)){
			    	System.out.println("数据库表和 字段名称只能包含数字和下划线，【"+key+"】未通过验证");
					return false;
				}
			 }
		}
		return true;
	}
	
	/**
	 * clob转string
	 * @param clob
	 * @return
	 */
	public static String clobToString(Clob clob) {
	      String reString = "";
	      try{
	    	  Reader is = clob.getCharacterStream();// 得到流
	    	  BufferedReader br = new BufferedReader(is);
	    	  String s = br.readLine();
	    	  StringBuffer sb = new StringBuffer();
	    	  while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING  
	    		  sb.append(s);  
	    		  s = br.readLine();  
	    	  }  
	    	  reString = sb.toString();  
	    	  if(br!=null){  
	    		  br.close();  
	    	  }  
	    	  if(is!=null){  
	    		  is.close();  
	    	  }  
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }
	      return reString;  
	     }  

}