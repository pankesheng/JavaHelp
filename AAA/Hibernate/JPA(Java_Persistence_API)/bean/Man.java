package bean;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")//_lx��ֵΪ2ʱΪMan�����¼
public class Man extends Person{
	
}
