import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;

import antlr.InputBuffer;

//测试
public class StudentTest {
	private SessionFactory sessionfactory;
	private Session session;
	private Transaction transaction;

	@Before
	public void init() {
		// 创建配置对象
		Configuration config = new Configuration().configure();
		// 创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(config.getProperties()).buildServiceRegistry();
		// 创建会话工厂对象
		sessionfactory = config.buildSessionFactory(serviceRegistry);
		// 绘画对象
		session = sessionfactory.openSession();
		// 开启事务
		transaction = session.beginTransaction();
	}

	@Test
	public void testStudent() {/*
								 * Student s=new Student(4,"雷园","男",new
								 * Date(),"武当山");
								 */
		Student s = new Student();
		s.setName("雷园");
		s.setGender("男");
		s.setBirthday(new Date());
		s.setAddress("武当山");
		session.save(s);
	}

	/**
	 * 测试增加图片的功能
	 * 
	 * @throws IOException
	 */
	@Test
	public void testWriteBlob() throws IOException {
		Student s = new Student();
		s.setName("雷园");
		s.setGender("男");
		s.setBirthday(new Date());
		s.setAddress("武当山");
		// 获取照片
		File f = new File("d:" + File.separator + "1.jpg");
		// 获得照片输入流
		InputStream input = new FileInputStream(f);
		// 创建一个Blob对象
		Blob image = Hibernate.getLobCreator(session).createBlob(input,
				input.available());
		s.setPicture(image);
		session.save(s);
	}

	/**
	 * 测试获取
	 */
	@Test
	public void testGetStudent() {
		Student s = (Student) session.get(Student.class, 2);
		System.out.println(s.getName() + s.getGender());
	}

	@Test
	public void testLoadStudent() {
		Student s = (Student) session.load(Student.class, 1);
		System.out.println(s);
	}

	@Test
	public void testUpdateStudent() {
		Student s = (Student) session.get(Student.class, 2);
		s.setGender("女");
		session.update(s);
	}

	@Test
	public void testDelStudent() {
		Student s = (Student) session.get(Student.class, 1);
		session.delete(s);
	}

	@After
	public void destory() {
		transaction.commit();// 提交事务
		session.close();// 关闭会话
		sessionfactory.close();// 关闭会话工厂
	}
}
