package copyHibernate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ʵ��ע�ⶨ��
 * @author zjh
 *
 */
@Target({ElementType.TYPE}) // ����������
@Retention(RetentionPolicy.RUNTIME) // ������������
public @interface Table {

	// �����Ա
	String value();
}