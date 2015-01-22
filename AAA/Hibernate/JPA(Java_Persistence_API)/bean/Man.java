package bean;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")//_lx的值为2时为Man对象记录
public class Man extends Person{
	
}
