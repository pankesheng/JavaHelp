import java.math.BigDecimal;

import javax.persistence.Transient;


public class test implements Serializable {
	
	
	//Serialization:��Ҫ������д������ʵ����������ֵ���������������κ����η����κγ�Ա�������κξ�̬�ĳ�Ա������
	//@Transient:�����������л���ʱ�򲻱��洢������һ��Thread�����һ��FileInputStream�����ȫ����Ϣ��
	@Transient
    public String getJind() {
		return null;
	}
}