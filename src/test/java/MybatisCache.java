import com.sgugu.entity.Department;
import com.sgugu.entity.DepartmentMapper;
import com.sgugu.entity.Employee;
import com.sgugu.entity.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

public class MybatisCache {


    public SqlSession getSqlSession() throws Exception {

        String resource = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        return session;
    }


    /**
     * 二级缓存测试
     *
     * @throws Exception
     */

    @Test
    public void test02() throws Exception {
        String resource = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();



        EmployeeMapper employeeMapper1 = sqlSession1.getMapper(EmployeeMapper.class);
        Employee employee1 = employeeMapper1.getEmployeeById(1);
        System.out.println(employee1);
        sqlSession1.close();


        EmployeeMapper employeeMapper2 = sqlSession2.getMapper(EmployeeMapper.class);
        Employee employee2 = employeeMapper2.getEmployeeById(1);
        System.out.println(employee2);
        sqlSession2.close();


    }


    @Test
    public void test1() throws Exception {

        SqlSession session1 = this.getSqlSession();
//        SqlSession session2 = this.getSqlSession();
        try {
            EmployeeMapper employeeMapper1 = session1.getMapper(EmployeeMapper.class);
//            EmployeeMapper employeeMapper2 = session2.getMapper(EmployeeMapper.class);
            Employee emp1 = employeeMapper1.getEmployeeById(1);
//            employeeMapper1.addEmp(new Employee(null,"d","d","d"));
//            session1.clearCache();
            Employee emp2 = employeeMapper1.getEmployeeById(1);
            System.out.println(emp1.toString());
            System.out.println(emp2.toString());
            System.out.println(emp1 == emp2);

        } finally {
            session1.close();
//            session2.close();
        }

    }

    @Test
    public void  test03() throws Exception{
        String resource = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        DepartmentMapper departmentMapper1 = sqlSession1.getMapper(DepartmentMapper.class);
        Department department1 =  departmentMapper1.getDepartmentById(1);
        System.out.println(department1);
        sqlSession1.close();

        DepartmentMapper departmentMapper2 = sqlSession2.getMapper(DepartmentMapper.class);

        Department department2 =departmentMapper2.getDepartmentById(1);
        System.out.println(department2);
        sqlSession2.close();
    }

}
