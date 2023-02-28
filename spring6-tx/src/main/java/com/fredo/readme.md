# 事务：
## 一、JdbcTemplate
Spring 框架对 JDBC 进行封装，使用 JdbcTemplate 方便实现对数据库操作
### 0.Jdbc和数据源
#### JDBC
JDBC的全称是Java数据库连接(Java Database connect)，它是一套用于执行SQL语句的Java API。
应用程序可通过这套API连接到关系数据库，并使用SQL语句来完成对数据库中数据的查询、更新和删除等操作。

JDBC的缺点：运行一次程序就连接一次数据库，最重要的，每次连接数据库，就要耗费较多资源
```java
//1.注册数据库的驱动
DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//2.通过 DriverManager获取数据库连接
String url="jdbc:mysql://localhost:3306/chapter01";
String usernames="root";
String password="root";
Connection conn=DriverManager.getConnection(url, username, password);
//3.通过 Connection对象获取 Statement对象
Statement stmt= conn.createStatement();
//4.使用 Statement执行SQL语句
String sql="select * from users";
ResultSet rs=stmt.executeQuery(sql);
//5、操作 ResultSet结果集
System.out.println("id|name|password|email|birthday");
while (rs.next()) {
    int id=rs.getInt("id");     //通过列名获取指定字段的值
    String name=rs.getString("name");
    String psw=rs.getString("password");
    String email=rs.getString("email");
    Date birthday=rs.getDate("birthday");
    System.out.println(id+"|"+name+"|"+psw+"|"+email+"|"+birthday); 
}
//6.回收数据库
rs.close();
stmt.close();
conn.close();
```
#### 数据源
数据源顾名思义，数据的来源，是提供某种所需要数据的器件或原始媒体。在数据源中存储了所有建立数据库连接的信息。
就像通过指定文件名称可以在文件系统中找到文件一样，通过提供正确的数据源名称，你可以找到相应的数据库连接。

数据源的好处：
1. 连接资源重用
2. 系统的响应速度更快
3. 属于一种新的资源分配的手段（以前我们是从数据库服务器直接获取连接实现交互。现在我们只需要从连接池中拿连接，用完了，再放回连接池即可，而不必大费周章的去直接连接数据库）
4. 避免服务器（一言不合就）宕机

数据源使用步骤：
0. 配置文件druid.properties：
```text
jdbcUrl = jdbc:mysql://localhost:3306/test
username = root
password = root
initialSize = 5
maxActive = 20
minActive = 5
maxWait = 3000
```
1. 创建连接池
```java
//1.加载配置文件
Properties pro = new Properties();
pro.load(DruidTest.class.getClassLoader().getResourceAsStream("druid.properties"));
//2.创建数据源对象
/*DataSource:是SUN公司声明的接口 。DruidDataSource：是阿里巴巴对应实现的类*/
DataSource dataSource  = new DruidDataSource();
//3.设置属性
DruidDataSource ds = (DruidDataSource)dataSource;
ds.setUrl(pro.getProperty("jdbcUrl"));
ds.setUsername(pro.getProperty("username"));
ds.setPassword(pro.getProperty("password"));
ds.setInitialSize(new Integer(pro.getProperty("initialSize")));
ds.setMaxActive(new Integer(pro.getProperty("maxActive")));
ds.setMinIdle(new Integer(pro.getProperty("minIdle")));
ds.setMaxWait(new Long(pro.getProperty("maxWait")));
//4.拿连接。直接通过数据源获取可用的连接
Connection conn = ds.getConnection();
//System.out.println(conn);//检验是否能够成功拿到一个数据库连接池中的连接
```

```java
//2.编写sql语句
String sql = "INSERT INTO test1 VALUES (NULL,\"张四丰\")";
//3.PreparedStatement
PreparedStatement pst = conn.prepareStatement(sql);
//4.执行sql
pst.executeUpdate();

//5.直接关闭资源即可，
conn.close();
pst.close();
```
## 二、声明式事务
一系列操作在逻辑上是一个整体，要么都成功要么都失败
### 1.特性
#### 1.原子性
Atomicity
一系列操作组成一个在逻辑上不可分割的整体，要么都成功要么都失败
#### 2.一致性
Consistency
一个事务执行之前和执行之后数据都必须处于一致性状态
要么成功执行，得到正确结果
要么执行失败，返回原始状态
没有中间状态
#### 3.隔离性
Isolation
事务与事务之间互不影响，互相隔离，不会查看到其他事务中间结果
#### 4.持久性
Durability
事务执行后的结果可以永久保存下来

### 2.编程式事务
就是编程时自己组件事务
```java
Connection conn = ...;
try {
    // 开启事务：关闭事务的自动提交
    conn.setAutoCommit(false);
    // 核心操作...
    // 提交事务
    conn.commit();
}catch(Exception e){
    // 回滚事务
    conn.rollBack();
}finally{
    // 释放数据库连接
    conn.close();
}
```
缺点：
- 细节没有被屏蔽：具体操作过程中，所有细节都需要程序员自己来完成，比较繁琐。
- 代码复用性不高：如果没有有效抽取出来，每次实现功能都需要自己编写代码，代码就没有得到复用。

### 3.声明式事务
既然事务控制的代码有规律可循，代码的结构基本是确定的，所以框架就可以将固定模式的代码抽取出来，进行相关的封装。
封装起来后，我们只需要在配置文件中进行简单的配置即可完成操作。

优点：
- 提高开发效率
- 消除了冗余的代码
- 框架会综合考虑相关领域中在实际开发环境下有可能遇到的各种问题，进行了健壮性、性能等各个方面的优化

使用步骤：
1. 导入外部属性文件
   <context:property-placeholder location="classpath:jdbc.properties"/>
2. 配置数据源
   <bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
   <property name="url" value="${jdbc.url}"/>
   <property name="driverClassName" value="${jdbc.driver}"/>
   <property name="username" value="${jdbc.user}"/>
   <property name="password" value="${jdbc.password}"/>
   </bean>
3. 配置事务管理器
   <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
   <property name="dataSource" ref="druidDataSource"/>
   </bean>
4. 开启事务的注解驱动
   <tx:annotation-driven transaction-manager="transactionManager" />



