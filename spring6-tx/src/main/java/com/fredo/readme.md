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


