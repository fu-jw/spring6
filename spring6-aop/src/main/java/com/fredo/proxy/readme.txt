由计算器案例分析知：

    需要解耦核心业务功能代码和附加功能代码 -->

    使用代理类 -->

    调用目标方法的时候，不再是直接对目标方法进行调用，而是通过代理类间接调用 -->

    静态代理实现：CalcStaticProxy

        静态代理确实实现了解耦，但是由于代码都写死了，完全不具备任何的灵活性。
        就拿日志功能来说，将来其他地方也需要附加日志，那还得再声明更多个静态代理类，
        那就产生了大量重复的代码，日志功能还是分散的，没有统一管理。

    提出进一步的需求：将日志功能集中到一个代理类中，
    将来有任何日志需求，都通过这一个代理类来实现。
    这就需要使用动态代理技术了。

    动态代理实现：ProxyFactory
        静态方法 newProxyInstance 实现，
        Proxy.newProxyInstance(classLoader, interfaces, invocationHandler)
        1、classLoader：加载动态生成的代理类的类加载器
        2、interfaces：目标对象实现的所有接口的class对象所组成的数组
        3、invocationHandler：设置代理对象实现目标对象方法的过程，即代理类中如何重写接口中的抽象方法
