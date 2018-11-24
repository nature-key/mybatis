1.mybatis

  1.添加全局配置文件mybstis.xml
  2.添加sql配置文件
  3.添加sql配置到全局配置中
      1.根据配置文件获取sqlSessionFactory
      2.获取sqlsession 对象执行增删改查
  4.和数据库字段不一致，使用别名
   select id,last_name lastName,gender,email from tbl_employee where id = #{id}    