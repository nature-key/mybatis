import com.sgugu.entity.Employee;
import com.sgugu.entity.EmployeeAnnotion;
import com.sgugu.entity.EmployeeMapper;
import com.sgugu.entity.EmployeeMapperMap;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Employee empl = new Employee(null, "4", "2", "1");
//
        employee.addEmp(empl);
        System.out.println(empl.getId());

//        employee.updateEmp("err",2);
//      employee.delEmp(new Integer(2));
//        System.out.println(a);
        session.commit();
        try {

        } finally {
            session.close();
        }
    }

    @Test
    public void test6() throws Exception {
        SqlSession sqlSession = this.getSqlSession();
        EmployeeMapper employee = sqlSession.getMapper(EmployeeMapper.class);
        Employee emp = employee.getEmployeeById(1);
        System.out.println(emp);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test00() throws Exception {
        SqlSession sqlSession = this.getSqlSession();
        EmployeeMapper employee = sqlSession.getMapper(EmployeeMapper.class);
        Employee emp = employee.getEmployeeByIdandName(2, "4");
        System.out.println(emp);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test01() throws Exception {
        SqlSession sqlSession = this.getSqlSession();
        EmployeeMapper employee = sqlSession.getMapper(EmployeeMapper.class);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 3);
        map.put("lastName", "4");

        Employee emp = employee.getEmployee(map);
        System.out.println(emp);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test02() throws Exception {
//        getEmployeeThird
        SqlSession sqlSession = this.getSqlSession();
        try {

            EmployeeMapperMap mapper = sqlSession.getMapper(EmployeeMapperMap.class);

           Employee employee = mapper.getEmployeeByDept(1);
            System.out.println(employee);
            System.out.println(employee.getDepartment());

//            Employee employee = mapper.getEmpById(3);
//            System.out.println(employee);


//        Employee employee1 = employee.getEmployeeThird(1, "tom", "tom@com");
//        System.out.println(employee1);

//            List<Employee> employees = mapper.getEmployeeByLastName("%e%");
//            System.out.println(employees);

//          Map<String ,Object> map =   mapper.getEmployeeByreturnMap("tome");
//            System.out.println(map);
//            Map<Integer, Employee> employeeMap = mapper.getEmployeeByreturnEmp("tome");
//            System.out.println(employeeMap);

        } catch (Exception e) {

        }finally {
            sqlSession.commit();
            sqlSession.close();
        }


    }


    @Test
    public void test03() throws Exception {
        SqlSession sqlSession = this.getSqlSession();
        EmployeeMapperMap employee = sqlSession.getMapper(EmployeeMapperMap.class);

        Employee employee1= employee.getEmployeeByDept(1);
        System.out.println(employee1);
        System.out.println(employee1.getDepartment());
        sqlSession.commit();
        sqlSession.close();
    }
}
