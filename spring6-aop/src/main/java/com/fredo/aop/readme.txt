AOP
    Aspect Oriented Programming
    一种设计思想，是软件设计领域中的面向切面编程，它是面向对象编程的一种补充和完善

    实现原理：通过预编译方式和运行期动态代理方式实现

    达到效果：在不修改源代码的情况下，给程序动态统一添加额外功能

    使用目的：对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率

    相关术语：
    1、横切关注点：就是要增强的功能点，
            分散在每个各个模块中解决同一样的问题，如用户验证、日志管理、事务处理、数据缓存都属于横切关注点
    2、通知（增强）：就是要增强功能的具体实现，分类如下：
            1 前置通知：在被代理的目标方法前执行-------------------@Before------------始终执行
            2 后置通知：在被代理的目标方法最终结束后执行（盖棺定论）----@After-------------始终执行
            3 返回通知：在被代理的目标方法成功结束后执行（寿终正寝）----@AfterReturning----目标方法正常执行完后才执行
            4 异常通知：在被代理的目标方法异常结束后执行（死于非命）----@AfterThrowing-----目标方法异常后才执行
            5 环绕通知：使用try...catch...finally结构围绕整个被代理的目标方法，包括上面四种通知对应的所有位置
       执行顺序：5----1----目标方法----3/4----2----5
    3、切面：封装通知方法的类

    4、目标：被代理的目标对象

    5、代理：向目标对象应用通知之后创建的代理对象

    6、连接点：就是spring允许你使用通知的方法
            把方法排成一排，每一个横切位置看成x轴方向，把方法从上到下执行的顺序看成y轴，x轴和y轴的交叉点就是连接点。

    7、切入点：定位连接点的方式
            每个类的方法中都包含多个连接点，所以连接点是类中客观存在的事物（从逻辑上来说）。
            如果把连接点看作数据库中的记录，那么切入点就是查询记录的 SQL 语句。
            Spring 的 AOP 技术可以通过切入点定位到特定的连接点。通俗说，要实际去增强的方法
            切点通过 org.springframework.aop.Pointcut 接口进行描述，它使用类和方法作为连接点的查询条件

    代理失效：
    有a，b两个目标方法，但是a方法内部调用了b方法，就会导致b方法的代理失效

    使用步骤：
    0.引用依赖
        spring-aop
        spring-aspects
    1.配置文件中开启aop规范
        <!--xmlns:aop="http://www.springframework.org/schema/aop"
            http://www.springframework.org/schema/aop
            http://www.springframework.org/schema/aop/spring-aop.xsd"-->
    2.配置文件中开启aspectj的自动代理，为目标对象生成代理，即识别注解 @Aspect
        <aop:aspectj-autoproxy/>
    3.编写切面类，LogAspect
    4.切面类中添加方法（即切点），方法中可以添加参数（即连接点信息、执行结果、异常信息）
    5.切点方法上添加注解（即通知类型）
    6.通知类型中设置切点表达式
        value = "execution(public int com.fredo.aop.target.CalculatorLogImpl.*(..))"
    7.重用切点表达式，避免相同配置重复写

    切点重用
        @Pointcut("execution(* com.fredo.aop.target.CalculatorLogImpl.*(..))")
        public void pointCut(){}


基于XML的AOP
    配置文件：
    <context:component-scan base-package="com.fredo.aop.xml"></context:component-scan>
    <aop:config>
        <!--配置切面类-->
        <aop:aspect ref="loggerAspect">
            <!--配置切点-->
            <aop:pointcut id="pointCut"
                       expression="execution(* com.fredo.aop.xml.CalculatorImpl.*(..))"/>
            <!--配置通知五种类型-->
            <aop:before method="beforeMethod" pointcut-ref="pointCut"></aop:before>
            <aop:after method="afterMethod" pointcut-ref="pointCut"></aop:after>
            <aop:after-returning method="afterReturningMethod" returning="result" pointcut-ref="pointCut"></aop:after-returning>
            <aop:after-throwing method="afterThrowingMethod" throwing="ex" pointcut-ref="pointCut"></aop:after-throwing>
            <aop:around method="aroundMethod" pointcut-ref="pointCut"></aop:around>
        </aop:aspect>
    </aop:config>










