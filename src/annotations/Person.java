package annotations;

/**
 * ���ڲ���ע�� �ļ̳�
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
		System.out.println("������~����������~�������������������಻��ȥ��");
	}
}
