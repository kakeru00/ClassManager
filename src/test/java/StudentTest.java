import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.criterion.DetachedCriteria;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zh.controller.StudentController;
import com.zh.entity.Student;
import com.zh.service.StudentService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:Spring-Hibernate.xml","classpath:SpringMVC.xml"})
public class StudentTest {
	
	@Resource(name="dataSource")
	private DataSource ds ;
	@Autowired
	StudentController uc;  
	
	@Resource
	StudentService us;
	
	@Ignore
	@Test 
	public void test() throws SQLException {
		
		//System.out.println(ds.getStudent()+ds.getPassword());
		Connection conn = ds.getConnection();
		
		String sql = "select * from a";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs =ps.executeQuery();
		while(rs.next()){
			
			System.out.println(rs.getString(1));
		}
		
		System.out.println();
		rs.close();
		ps.close();
		conn.close();
		
	}
	
	@Test
	public void studentServiceTest(){
		us.deleteAll();
		for (int i = 1; i <= 23; i++) {
			us.save(new Student(i+"",i+"",i+""));
		}
	}

	


}
