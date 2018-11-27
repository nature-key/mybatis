1.mybatis

  1.添加全局配置文件mybstis.xml
  2.添加sql配置文件
  3.添加sql配置到全局配置中
      1.根据配置文件获取sqlSessionFactory
      2.获取sqlsession 对象执行增删改查
  4.和数据库字段不一致，使用别名
   select id,last_name lastName,gender,email from tbl_employee where id = #{id}    
   
2.接口式编程
  mybatis映射文件和接口结合使用，mybatis会自动给接口产生一个代理对象
  
  优点 有明确的参数检验，返回值类型校验
  
  
  1.sqlsession代表和数据库的一次回话用完必须关闭
  2.sqlsession和connection一样非线程安全
  3.mapper接口没有实现类，mybatis会生成一个代理
  4.重要的配置文件
    1.mybatis.xml 全局配置数据库库 事物管理 （也可以使用）
    2.mapper sql配置文件   