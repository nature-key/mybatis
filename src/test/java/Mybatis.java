import com.sgugu.entity.Employee;
import com.sgugu.entity.EmployeeAnnotion;
import com.sgugu.entity.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

public class Mybatis {


    public SqlSession getSqlSession() throws Exception {
        String resource = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        return session;
    }

    @Test
    public void test() throws Exception {
        String resource = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            Employee blog = (Employee) session.selectOne("selectEmployee", 1);
            System.out.println(blog);
        } finally {
            session.close();
        }
    }

    @Test
    public void test1() throws Exception {
        SqlSession session = this.getSqlSession();
        com.sgugu.entity.dao.Employee employee = session.getMapper(com.sgugu.entity.dao.Employee.class);
        Employee employee1 = employee.getEmployeeById(1);
        try {
            System.out.println(employee1.getClass());
            System.out.println(employee1);
        } finally {
            session.close();
        }
    }
    @Test
    public void test3() throws Exception {
        SqlSession session = this.getSqlSession();
        com.sgugu.entity.EmployeeAnnotion employee = session.getMapper(com.sgugu.entity.EmployeeAnnotion.class);
        Employee employee1 = employee.getEmployeeById(1);
        try {
            System.out.println(employee1.getClass());
            System.out.println(employee1);
        } finally {
            session.close();
        }
    }
    @Test
    public void test4() throws Exception {
        SqlSession session = this.getSqlSession();
        EmployeeMapper employee = session.getMapper(EmployeeMapper.class);
//        Employee empl = new Employee(2,"4","2","1");
//
//        employee.addEmp(empl);
//        employee.updateEmp("err",2);
      employee.delEmp(new Integer(2));
//        System.out.println(a);
        session.commit();
        try {

        } finally {
            session.close();
        }
    }

}
