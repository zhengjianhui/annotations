package annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在接口前增加 @ 符号使接口变为注解
 * 
 * 元注解 用于描述注解的  注解
 * 
 * 用于声明作用域
 * @Target(ElementType.METHOD) 
 * 	ElementType.METHOD  		声明注解用于方法上
 * 	ElementType.CONSTRUCTOR  	声明用于构造函数上
 * 	ElementType.FIELD			声明用于字段上
 * 	ElementType.LOCAL_VARIABLE	声明用于局部变量上
 * 	ElementType.PACKAGE			声明用于包上
 *  ElementType.PARAMETER		声明与参数上
 * 	ElementType.TYPE			声明与类接口上
 * 
 * 用于声明生明周期  源码，编译，运行 注解
 * @Retention()
 *  RetentionPolicy.SOURCE		声明在源码中有效	在源码中有效，编译时会丢弃
 * 	RetentionPolicy.CLASS		声明在编译期有效	编译时会 记录到class中运行时忽略
 *  RetentionPolicy.RUNTIME		声明在运行期有效	运行时可以通过放射读取
 * 
 * 
 * 标示注解，表示允许子注解继承 
 * @Inherited
 * 	用在接口上不会有作用，只能作用于类上时才会生效
 *  子类使用注解会覆盖 父类注解
 * 
 *  
 * 生成javadoc 时会包含注解 
 * @Documented  
 *  
 * @author zjh
 *
 */
@Target({ElementType.METHOD,ElementType.TYPE})    // 元注解  ElementType 注解的作用域 声明注解使用与那些地方

@Retention(RetentionPolicy.RUNTIME)  // 声明周期

@Inherited	// 标示注解，表示允许子注解继承 用在接口上没有作用

@Documented  // 生成javadoc 时会包含注解
public @interface Description {
	
	
	/**
	 * 成员以无参无异常的方式声明
	 * 成员的声明类似接口的方法， 在注解中的意识为 注解的成员
	 * 可以使用 default 给成员指定默认值
	 * 
	 * 成员类型是受限制的，合法类型有
	 * 
	 * 	基本类型
	 *  String
	 *  Class
	 *  Annotations		注解
	 *  Enumeration		枚举
	 *  
	 *  当注解只有一个成员变量的时候 成员名必须为 value()
	 *  String desc();  desc() 就是成员名
	 *  @Retention(RetentionPolicy.RUNTIME)  只有一个成员时，约定熟诚的规则是这样使用的 所以用value 标示成员名
	 *  
	 *  没有成员的注解类 成为标示注解  
	 *  @Documented 与 @Documented  都是标示注解
	 */

//	String desc(int 1); 错误,成员必须无参
//	String desc() throw Exception;  错误,成员不能抛出异常
//	Map desc();   错误,成员是受限制的
	
//	String desc();
//	String author();
//	
//	// 通过default 指定成员的默认值
//	int age() default 18;
	String value();
}
