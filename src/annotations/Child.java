package annotations;
/**
 * ʹ���Զ���ע��
 * @author zjh
 *
 */
//@Description("I am class annotations")  // ����ʹ��ע��Ḳ�Ǹ���ע��
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