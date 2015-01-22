package bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)//һ�ü̳���һ�ű�(SINGLE_TABLE),һ����һ�ű�(TABLE_PER_CLASS)
@DiscriminatorColumn(name = "_lx", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("0")//_lx��ֵΪ0ʱΪPerson�����¼

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "t_person")
public class Person {
	
	@Id
	@GeneratedValue//���ɲ���
	private Long id;
	
	@Column(length=11,nullable=false,name="_name")
	private String name;
	
	@Lob//�洢���ı�����
	private String info;
	
	@Lob//�洢���ֽ��ļ�
	@Basic(fetch=FetchType.LAZY)//�ӳټ��ء�FetchType.EAGE(���ϼ���)
	private Byte[] file;
	
	@Temporal(TemporalType.TIMESTAMP)//TIME(12:29:30);DATE(2009-11-11);TIMESTAMP(2009-11-11 12:29:30);
	private Date birthday;
	
	@Transient//�����Բ���Ϊ���ݿ���ֶ�,���������ΪgetX�Ҳ���Ϊ���ݿ���ֶ���Ҫ���ϴ˹ؼ���
	private String other;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="_schoolId")
	private School school;

	@OneToOne(optional=true, cascade=CascadeType.ALL)
	@JoinColumn(name="_idcardId")
	private IdCard idcard;

	@ManyToMany(cascade={CascadeType.REFRESH})
	@JoinTable(name="t_person_role",inverseJoinColumns=@JoinColumn(name="_role_id"),joinColumns=@JoinColumn(name="_person_id"))
	private Set<Role> roles = new HashSet<Role>();
	
	
	//optional��=trueʱ����ΪNull
	//cascade��	ȫ������(ALL)��ˢ�¼���(REFRESH)����Ӽ���(PERSIST)���޸ļ���(MERGE)��ɾ������(REMOVE)
	//fetch��	�ӳټ���(LAZY)(XxxToManyĬ��)����������(EAGE)(XxxToOneĬ��)
	//mappedBy��˵���������Ǳ�ά����(����޸Ķ���ʱ�������Բ�����),�൱��inverse="true"
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Byte[] getFile() {
		return file;
	}

	public void setFile(Byte[] file) {
		this.file = file;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public IdCard getIdcard() {
		return idcard;
	}

	public void setIdcard(IdCard idcard) {
		this.idcard = idcard;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
