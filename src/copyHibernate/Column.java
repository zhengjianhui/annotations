package copyHibernate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义注解，用于字段上
 * @author zjh
 *
 */
@Target({ElementType.FIELD})  // 作用与方法上
@Retention(RetentionPolicy.RUNTIME) // 生命周期为运行时有效
public @interface Column {

	// 定义成员
	String value();
}
