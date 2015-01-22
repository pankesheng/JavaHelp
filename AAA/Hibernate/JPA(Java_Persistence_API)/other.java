import java.math.BigDecimal;

import javax.persistence.Transient;


public class test implements Serializable {
	
	
	//Serialization:主要任务是写出对象实例变量的数值。不包括变量的任何修饰符、任何成员方法、任何静态的成员变量。
	//@Transient:用于声明序列化的时候不被存储。例如一个Thread对象或一个FileInputStream对象或安全性信息。
	@Transient
    public String getJind() {
		return null;
	}
}