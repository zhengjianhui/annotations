package annotations;
/**
 * 使用自定义注解
 * @author zjh
 *
 */
//@Description("I am class annotations")  // 子类使用注解会覆盖父类注解
public class Child extends Person {

	@Description("I am class method anntations")
	@Override
	public String name() {
		return null;
	}

	@Override
	public int age() {
		return 0;
	}


}
