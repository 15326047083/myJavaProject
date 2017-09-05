import java.sql.Blob;
import java.util.Date;


//学生类
public class Student {

	private String num;// 学号
	private String name;// 姓名
	private String gender;// 性别
	private Date birthday;// 生日
	private String address;// 地址
	private Blob picture;//照片
	/**
	 * 不带参默认构造方法
	 */
	public Student() {
	}
	/**
	 * 带参数构造方法
	 * @param num
	 * @param name
	 * @param gender
	 * @param birthday
	 * @param address
	 */
	public Student(String num, String name, String gender, Date birthday,
			String address) {
		this.num = num;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.address = address;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public Blob getPicture() {
		return picture;
	}
	public void setPicture(Blob picture) {
		this.picture = picture;
	}
	
}
