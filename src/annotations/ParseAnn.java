package annotations;

import java.lang.reflect.Method;

/**
 * 该类用于测试解析注解
 * 解析与作用于相关
 * 作用域为 SOURCE 时  只有在源码时存在 解析不到
 * 作用域为 CLASS 时   只有在编译时存在 解析不到
 * 
 * 能解析到的只有 RUNTIME  应为用通过反射才能获取，而反射是运行时动态加载
 * 
 * @author zjh
 *
 */
public class ParseAnn {

	public static void main(String[] args) {
		// 解析注解
		
		try {
			// 第一步 使用类加载器
			Class c = Class.forName("annotations.Child");
			
			// 找到类上面的注解  
			// isAnnotationPresent()  判断类上是否存在 对应注解 返回一个 boolean值
			// 参数为 注解名（接口的类信息）
			boolean isExits = c.isAnnotationPresent(Description.class);
			
			// 如果存在注解则拿到 注解的实例
			if(isExits) {
				
				// 拿到注解 实例
				Description descr = (Description) c.getAnnotation(Description.class);
				// 获取成员值 
				System.out.println(descr.value()); 
				//  结果说明拿到注解的成员信息  I am superClass annotations  同时说明会接成与父类的注解
				
			}
			
			
			
			// 查看方法上是否有注解
			// 获取所有 方法
			Method[] me = c.getMethods();
			
			// 遍历方法
			for (Method m : me) {
				// 判断方法上是否有注解
				boolean isExitsMethod = m.isAnnotationPresent(Description.class);
				if(isExitsMethod) {
					// 获取成员信息
					String value = m.getAnnotation(Description.class).value();
					
					// 输出成员信息
					System.out.println(value);
				}
			}
 			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
