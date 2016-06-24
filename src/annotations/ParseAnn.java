package annotations;

import java.lang.reflect.Method;

/**
 * �������ڲ��Խ���ע��
 * ���������������
 * ������Ϊ SOURCE ʱ  ֻ����Դ��ʱ���� ��������
 * ������Ϊ CLASS ʱ   ֻ���ڱ���ʱ���� ��������
 * 
 * �ܽ�������ֻ�� RUNTIME  ӦΪ��ͨ��������ܻ�ȡ��������������ʱ��̬����
 * 
 * @author zjh
 *
 */
public class ParseAnn {

	public static void main(String[] args) {
		// ����ע��
		
		try {
			// ��һ�� ʹ���������
			Class c = Class.forName("annotations.Child");
			
			// �ҵ��������ע��  
			// isAnnotationPresent()  �ж������Ƿ���� ��Ӧע�� ����һ�� booleanֵ
			// ����Ϊ ע�������ӿڵ�����Ϣ��
			boolean isExits = c.isAnnotationPresent(Description.class);
			
			// �������ע�����õ� ע���ʵ��
			if(isExits) {
				
				// �õ�ע�� ʵ��
				Description descr = (Description) c.getAnnotation(Description.class);
				// ��ȡ��Աֵ 
				System.out.println(descr.value()); 
				//  ���˵���õ�ע��ĳ�Ա��Ϣ  I am superClass annotations  ͬʱ˵����ӳ��븸���ע��
				
			}
			
			
			
			// �鿴�������Ƿ���ע��
			// ��ȡ���� ����
			Method[] me = c.getMethods();
			
			// ��������
			for (Method m : me) {
				// �жϷ������Ƿ���ע��
				boolean isExitsMethod = m.isAnnotationPresent(Description.class);
				if(isExitsMethod) {
					// ��ȡ��Ա��Ϣ
					String value = m.getAnnotation(Description.class).value();
					
					// �����Ա��Ϣ
					System.out.println(value);
				}
			}
 			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}