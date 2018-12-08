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

    @Test
    public void test1() throws Exception {

        SqlSession session1 = this.getSqlSession();
//        SqlSession session2 = this.getSqlSession();
        try{
            EmployeeMapper employeeMapper1 = session1.getMapper(EmployeeMapper.class);
//            EmployeeMapper employeeMapper2 = session2.getMapper(EmployeeMapper.class);
            Employee emp1 = employeeMapper1.getEmployeeById(1);
//            employeeMapper1.addEmp(new Employee(null,"d","d","d"));
            session1.clearCache();
            Employee emp2 = employeeMapper1.getEmployeeById(1);
            System.out.println(emp1.toString());
            System.out.println(emp2.toString());
            System.out.println(emp1==emp2);

        }finally {
            session1.close();
//            session2.close();
        }


    }

}
