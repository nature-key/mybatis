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
    
    
    
参数配置

  1.单个参数不做处理
  2.多个参数
  
      Available parameters are [0, 1, param1, param2]
  
    mybatis会把多个参数封装成一个map
    key   param1 params2...paramN
    values 传入的参数值
    
    使用@params进行设置 key
    
    #{key}获取参数值
 3.使用map 传参
          
          
  4.pojo 多个参数是一个pojo直接传入pojo就行      
  
  

 public void updateEmp(@Param("lastName") String lastName, @Param("id") int id);
 取值  lastname =>#{lastName}  
  public void updateEmp( @Param("id") int id,@Param("e"")Employee emp);
  
  取值  id->#{id} lastname->#{param2.lastname/e.lastname}

 list set 数组 也会封装成map
  key(collection )
  key（list）
  key(array)   
  public void updateEmp(List<Integer> ids)   
  
   #{list[0]}
   
mybatis多指参数时候就会把参数封装成一个map
  可以使用 param1 param2 .. . paramN
          0         1         2
  也可以是有那个注解@param("id"")
          #{id}           
使用${} 和 #{} 区别
 SELECT  *  from  tbl_employee where id=${id} AND last_name=#{lastName} AND email=#{2}   
  SELECT * from tbl_employee where id=1 AND last_name=? AND email=? 
  
  $ 直接把值放到id上，  直接拼接一个sql
    而#是一个占位符 预编译  preparaedStament 防止sql 注入 安全
     select *  from  ${table_name} where id =#{id}
 
 
 #{}更丰富的用法
  规定参数的一些规则
   设置javatype jabcType mode  numericScale
   
   jdbcType 通常在某些昌盛景设置
    jdbcType OTHER 无效的类型。默认是这种
   
    oracle不支持 other 
     设置
      1. #{email jabcType=OTHER}
      2.JDBCtyPE fORull =null  
       全职设置<setting
       
查询
   返回为list的时候返回类型是 集合的实体
    <select id="getEmployeeByLastName" resultType="emp">
           SELECT *  from  tbl_employee where last_name like #{lastName}
       </select>       


默认构造函数 写上 




缓存
 一级缓存
  本地缓存  sqlsession级别的缓存 一级缓存是一直开启
  与数据库同一次回话，查询到数据回访本地缓存中
  相同的数据，直接从相同数据的缓存中拿，没有必要去数据库查
  
  一级缓存失效情况
  
   1。sqlsession不同
   2.sqlsession 相同，查询条件不同
   3.sqlsession相同 连词查询之间执行增删改查
   4.sqlsession相同 手动清除一级缓存
 二级缓存 全局缓存
 
  1.一个会话，查询一条数据，放到一级缓存
  2.会话关闭，会把数据放到二级缓存，新的查询，会查询二级缓存
  3.sqlsession ==employeemapper ===> employee
                 DepartmentMapper ===>  departtment
                 
                 不同的namespace查出的数据放到自己的缓存中， 就是一个map
                 
  二级缓存使用
  1.<setting name="cacheEnabled" value="true"></setting>
  2.基于namespace的 
   <cache eviction="" flushInterval=" " readOnly="" size="" type=""> </cache>
                      
    eviction 回收策略
     LRU – 最近最少使用的:移除最长时间不被使用的对象。
     FIFO – 先进先出:按对象进入缓存的顺序来移除它们。
     SOFT – 软引用:移除基于垃圾回收器状态和软引用规则的对象。
     WEAK – 弱引用:更积极地移除基于垃圾收集器状态和弱引用规则的对象。
     
     flushInterval  缓存多长时间清空一次 默认不清楚
     刷新间隔)可以被设置为任意的正整数,而且它们代表一个合理的毫秒 形式的时间段。默认情况是不设置,也就是没有刷新间隔,缓存仅仅调用语句时刷新            
                 
      readOnly 
        只读 true mybatis认为所有从缓存中获取数据的操作都是读操作，不会修改数据
         加快获取数据的性能，直接把引用返回，不安全
        非只读  false mybatis认为数据是可以修改的，
            mybatis会克隆一份，使用序列化反序列化克隆一份新的数据返回，数据安全，单不安全
     size 缓存的大小
     
     实体要进行序列化       
            
       <setting name="cacheEnabled" value="true"></setting>
      cacheEnabled false  关闭二级缓存
      
       select 标签中  useCache="false"  二级缓存不能使用，一级缓存可用 
       
       flushCache="true"   增删改查 一二级缓存都会清空
       
       session1.clearCache(); 清除当前一级缓存
       <setting name="localCacheScope" value="SESSION"></setting>  默认一级缓存session
       MyBatis 利用本地缓存机制（Local Cache）防止循环引用（circular references）和加速重复嵌套查询。 默认值为 SESSION，这种情况下会缓存一个会话中执行的所有查询。 若设置值为 STATEMENT，本地会话仅用在语句执行上，对相同 SqlSession 的不同调用将不会共享数据。	SESSION | STATEMENT	