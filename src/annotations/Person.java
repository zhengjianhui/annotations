package annotations;

/**
 * 用于测试注解 的继承
 * @author zjh
 *
 */
@Description("I am superClass annotations") 
public class Person {

	public String name() {;
		return null;
	}
	
	@Description("I am superClass method annotations")
	public int age() {
		return 0;
	};
	
	public void sing() {;
		System.out.println("啊啊啊~啊啊啊啊啊~西湖美景。。。。。编不下去了");
	}
}
