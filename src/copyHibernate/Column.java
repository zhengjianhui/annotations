package copyHibernate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ����ע�⣬�����ֶ���
 * @author zjh
 *
 */
@Target({ElementType.FIELD})  // �����뷽����
@Retention(RetentionPolicy.RUNTIME) // ��������Ϊ����ʱ��Ч
public @interface Column {

	// �����Ա
	String value();
}