# work3
## mybatis

通过如下几个步骤，用mybatis快速进行持久层的开发

    编写全局配置文件
    编写mapper映射文件
    加载全局配置文件，生成SqlSessionFactory
    创建SqlSession，调用mapper映射文件中的SQL语句来执行CRUD操作
    
使用 MyBatis 框架来创建数据库连接并打开一个会话
~~~java
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();
~~~
