# work3
## mybatis
项目结构如下


    ├─src
    │  ├─main
    │  │  ├─java
    │  │  │  ├─com
    │  │  │  │  └─cleverDY
    │  │  │  │      ├─good_and_order  //处理订单与商品的关系
    │  │  │  │      ├─mapper    //Mapper代理
    │  │  │  │      └─pojo      // 商品和订单对象
    │  │  └─resources
    │  │      └─com
    │  │          └─cleverDY
    │  │              └─mapper    //Mapper.xml文件，在Mapper中写方法，Mapper.xml中实现方法
    │  └─test   //测试
    │      
    └


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
编写mybatis-config.xml文件
~~~java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql:///mysqlstudy?useSSl=false/useOldAliasMetadataBehavior=true"/>
                <property name="username" value="root"/>
                <property name="password" value="1234"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
    </mappers>
</configuration>
~~~
导入依赖的jar包
~~~java
<dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.29</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.6</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.12</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.25</version>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
            <version>1.1.11</version>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.1.11</version>
        </dependency>
    </dependencies>
~~~
配置完成后就可以编写mapper映射文件(编写SQL)实现功能操作
这个项目所用到的Mapper接口的方法
GoodsMapper:

    //查看所有
    List<Goods> selectAll();
    //查看详情
    Goods selectByName(String goodName);//通过名字
    Goods selectById(int goodsId);//通过编号
    //查询指定物品 
    List<Goods> selectList(String goodsName);
    //动态条件查询
    List<Goods> selectAllPriceASC();
    //价格降序
    List<Goods> selectAllPriceDESC();
    //价格升序
    List<Goods> selectAllIdASC();
    //添加商品
    void insertGood(Goods goods);
    //删除操作
    void deleteById(int id);
    //批量删除操作
    int batchDelete(List<Integer> list);
    //修改操作
    void updateDynamic(Goods goods);

OrderMapper:

    //查看所有
    List<Order> selectAll();
    //查看详情
    Order selectById(int id);
    Order selectByGoodName(String goodName);
    //动态条件查询
    List<Order> selectList(String goodsName);//查找指定商品名字的订单
    List<Order> selectAllDateDESC();//时间降序
    List<Order> selectAllDateASC();//时间升序
    List<Order> selectAllIdDESC();//id降序
    List<Order> selectAllPriceASC();//价格降序
    List<Order> selectAllPriceDESC();//价格升序
    //改
    //动态修改
    void updateDynamic(Order order);
    //增
    void insertOrder(Order orders);
    //删
    //删除指定
    void deleteById(int id);
    //批量删除
    int batchDelete(List<Integer> list);
    
