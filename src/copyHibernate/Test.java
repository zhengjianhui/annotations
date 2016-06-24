package copyHibernate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {

	public static void main(String[] args) {
		Filter f1 = new Filter();
		// 查询id 为 1 的用户
		f1.setId(1);
		
		
		
		Filter f2 = new Filter();
		// 根据用户名模糊查询 
		f2.setUserName("lucy");
		
		Filter f3 = new Filter();
		// 查询邮箱为其中任意一个的值
		f3.setEmail("zheng@123.com,dai@123.com");
		
		// 调用对应的 query()  返回对应的sql 语句
		String sql1 = query(f1);
		String sql2 = query(f2);
		String sql3 = query(f3);
		
		System.out.println(sql1);
		System.out.println(sql2);
		System.out.println(sql3);
		
	}
	
	/**
	 * sql 工厂
	 * @param f
	 * @return
	 */
	private static String query(Object f) {
		// 获取对应的类信息 用于检验是否有注解 和 解析
		Class c = f.getClass();
		
		// 判断是否是一个 table 注解
		boolean isExists = c.isAnnotationPresent(Table.class);
		
		if(!isExists) {
			// 并不是存在的类型
			return null;
		}
		
		// 是的话 获取 Table 对象
		Table table = (Table) c.getAnnotation(Table.class);
		// 获取表名
		String tableName = table.value();
		
		// 创建一个 StringBuilder 对象
		StringBuilder sb = new StringBuilder();
		// 凭借一个查询语句
		sb.append("select * from" + " ").append(tableName).append(" " + "where 1 = 1");
		
		// 获取所有字段 
		Field[] field = c.getDeclaredFields();
		// 遍历所有字段
		for (Field field2 : field) {
			// 判断是否存在  Column 注解
			boolean fieldExists = field2.isAnnotationPresent(Column.class);
			// 是的话添加查询条件
			if(fieldExists) {
				// 获取数据字段名
				String sqlName = field2.getAnnotation(Column.class).value();
				
				// 获取实体类字段的名字
				String fieldName = field2.getName();
				
				// 通过反射获取字段对应的 get 方法  第一个字母大写
				String fieldGetMethod = "get" 
						+ fieldName.substring(0,1).toUpperCase() 
						+ fieldName.substring(1,fieldName.length()); 
				
				// 用于接收 反射字段的get 方法返回的值
				Object fieldValue = null;
				try {
					// 获取get 方法 没有参数
					Method method = c.getMethod(fieldGetMethod);
					
					// 通过反射调用 get 方法
					fieldValue = method.invoke(f);
					
//					System.out.println(fieldValue);
					
					
				} catch (NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
				// 如果 String的字段等于 null  或则 int字段 == 0 则不作处理
				if(fieldValue == null || fieldValue instanceof Integer && (Integer)fieldValue == 0) {
					continue;
				}
				
				// 拼接查询条件 and + 数据库字段名 + "=" + 字段值 
				// 如果是String 类型加上 '' 单引号
				if(fieldValue instanceof String) {
					
					// 查看fieldValue  是否包含 , 号 是的话就是  in（xxx,xxxx,xxx） 的sql 查询
					if(((String) fieldValue).contains(",")) {
						// 以逗号为分隔符 切割条件
						String fieldValues[] = ((String)fieldValue).split(",");
						
						// 拼接条件
						sb.append(" " + "in(").append("" + sqlName + "=");
						for (String str : fieldValues) {
							sb.append("'" + str + "',");
						}
						// 删除最后一个逗号
						sb.deleteCharAt(sb.length() - 1);
						// 增加最后的括号
						sb.append(")");
					
					// 否则正常拼接 
					} else {
						sb.append(" and ").append(sqlName).append("=").append("'" + fieldValue + "'");
					}
				} 
				
				if(fieldValue instanceof Integer) {
					sb.append(" and ").append(sqlName).append("=").append(fieldValue);
				}
			}
			
			
		}
		
		return sb.toString();
	}
}
