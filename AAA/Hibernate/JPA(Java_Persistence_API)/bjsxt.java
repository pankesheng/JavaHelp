

//һ��һ�����������(�ص�)
	public class Husband {
		@OneToOne
		@JoinColumn(name = "wife_id")
		private Wife wife;
	}
	public class Wife {
		
	}
	
//һ��һ˫���������
	public class Husband {
		@OneToOne(cascade = CascadeType.ALL)	//�����ɾ�����޸�Husbandʱ���������ɾ�����޸�Wife
		@JoinColumn(name = "wife_id")
		private Wife wife;
	}
	public class Wife {
		@OneToOne(mappedBy = "wife")
		private Husband husband;
	}
	
//һ�Զ൥��
	public class Group {
		@OneToMany
		@JoinColumn(name = "group_id")
		private Set<User> users = new HashSet<User>();
	}
	public class User {
		
	}
	
//һ�Զ�˫��
	public class Group {
		@OneToMany(mappedBy = "group")
		private Set<User> users = new HashSet<User>();
	}
	public class User {
		@ManyToOne
		@JoinColumn(name = "group_id")
		private Group group;
	}

	
//ע��
	//˫��ʱmappedBy����	
	