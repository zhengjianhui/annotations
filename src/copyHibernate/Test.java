package copyHibernate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {

	public static void main(String[] args) {
		Filter f1 = new Filter();
		// ��ѯid Ϊ 1 ���û�
		f1.setId(1);
		
		
		
		Filter f2 = new Filter();
		// �����û���ģ����ѯ 
		f2.setUserName("lucy");
		
		Filter f3 = new Filter();
		// ��ѯ����Ϊ��������һ����ֵ
		f3.setEmail("zheng@123.com,dai@123.com");
		
		// ���ö�Ӧ�� query()  ���ض�Ӧ��sql ���
		String sql1 = query(f1);
		String sql2 = query(f2);
		String sql3 = query(f3);
		
		System.out.println(sql1);
		System.out.println(sql2);
		System.out.println(sql3);
		
	}
	
	/**
	 * sql ����
	 * @param f
	 * @return
	 */
	private static String query(Object f) {
		// ��ȡ��Ӧ������Ϣ ���ڼ����Ƿ���ע�� �� ����
		Class c = f.getClass();
		
		// �ж��Ƿ���һ�� table ע��
		boolean isExists = c.isAnnotationPresent(Table.class);
		
		if(!isExists) {
			// �����Ǵ��ڵ�����
			return null;
		}
		
		// �ǵĻ� ��ȡ Table ����
		Table table = (Table) c.getAnnotation(Table.class);
		// ��ȡ����
		String tableName = table.value();
		
		// ����һ�� StringBuilder ����
		StringBuilder sb = new StringBuilder();
		// ƾ��һ����ѯ���
		sb.append("select * from" + " ").append(tableName).append(" " + "where 1 = 1");
		
		// ��ȡ�����ֶ� 
		Field[] field = c.getDeclaredFields();
		// ���������ֶ�
		for (Field field2 : field) {
			// �ж��Ƿ����  Column ע��
			boolean fieldExists = field2.isAnnotationPresent(Column.class);
			// �ǵĻ����Ӳ�ѯ����
			if(fieldExists) {
				// ��ȡ�����ֶ���
				String sqlName = field2.getAnnotation(Column.class).value();
				
				// ��ȡʵ�����ֶε�����
				String fieldName = field2.getName();
				
				// ͨ�������ȡ�ֶζ�Ӧ�� get ����  ��һ����ĸ��д
				String fieldGetMethod = "get" 
						+ fieldName.substring(0,1).toUpperCase() 
						+ fieldName.substring(1,fieldName.length()); 
				
				// ���ڽ��� �����ֶε�get �������ص�ֵ
				Object fieldValue = null;
				try {
					// ��ȡget ���� û�в���
					Method method = c.getMethod(fieldGetMethod);
					
					// ͨ��������� get ����
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
				
				// ��� String���ֶε��� null  ���� int�ֶ� == 0 ��������
				if(fieldValue == null || fieldValue instanceof Integer && (Integer)fieldValue == 0) {
					continue;
				}
				
				// ƴ�Ӳ�ѯ���� and + ���ݿ��ֶ��� + "=" + �ֶ�ֵ 
				// �����String ���ͼ��� '' ������
				if(fieldValue instanceof String) {
					
					// �鿴fieldValue  �Ƿ���� , �� �ǵĻ�����  in��xxx,xxxx,xxx�� ��sql ��ѯ
					if(((String) fieldValue).contains(",")) {
						// �Զ���Ϊ�ָ��� �и�����
						String fieldValues[] = ((String)fieldValue).split(",");
						
						// ƴ������
						sb.append(" " + "in(").append("" + sqlName + "=");
						for (String str : fieldValues) {
							sb.append("'" + str + "',");
						}
						// ɾ�����һ������
						sb.deleteCharAt(sb.length() - 1);
						// ������������
						sb.append(")");
					
					// ��������ƴ�� 
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