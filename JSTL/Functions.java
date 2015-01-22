import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class Functions {
	
	private static SessionFactory sessionFactory;

	public static List getLylxList() {
		List lylxList = null; 
		try {
			HibernateTemplate ht = new HibernateTemplate(sessionFactory);
			return ht.find("from Lylx a order by a.id");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lylxList;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		Functions.sessionFactory = sessionFactory;
	}

}
