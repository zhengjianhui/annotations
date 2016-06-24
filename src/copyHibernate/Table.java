package copyHibernate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实现注解定义
 * @author zjh
 *
 */
@Target({ElementType.TYPE}) // 定义作用域
@Retention(RetentionPolicy.RUNTIME) // 定义声明周期
public @interface Table {

	// 定义成员
	String value();
}
