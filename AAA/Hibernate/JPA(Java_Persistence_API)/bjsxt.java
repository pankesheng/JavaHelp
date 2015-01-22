

//一对一单向外键关联(重点)
	public class Husband {
		@OneToOne
		@JoinColumn(name = "wife_id")
		private Wife wife;
	}
	public class Wife {
		
	}
	
//一对一双向外键关联
	public class Husband {
		@OneToOne(cascade = CascadeType.ALL)	//保存或删除或修改Husband时级联保存或删除或修改Wife
		@JoinColumn(name = "wife_id")
		private Wife wife;
	}
	public class Wife {
		@OneToOne(mappedBy = "wife")
		private Husband husband;
	}
	
//一对多单向
	public class Group {
		@OneToMany
		@JoinColumn(name = "group_id")
		private Set<User> users = new HashSet<User>();
	}
	public class User {
		
	}
	
//一对多双向
	public class Group {
		@OneToMany(mappedBy = "group")
		private Set<User> users = new HashSet<User>();
	}
	public class User {
		@ManyToOne
		@JoinColumn(name = "group_id")
		private Group group;
	}

	
//注：
	//双向时mappedBy必设	
	