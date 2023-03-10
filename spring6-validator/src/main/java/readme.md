# Spring Validation
在开发中，我们经常遇到参数校验的需求，
比如用户注册的时候，要校验用户名不能为空、用户名长度不超过20个字符、手机号是合法的手机号格式等等。
如果使用普通方式，我们会把校验的代码和真正的业务处理逻辑耦合在一起，而且如果未来要新增一种校验逻辑也需要在修改多个地方。
而Spring Validation允许通过注解的方式来定义对象校验规则，把校验和业务逻辑分离开，让代码编写更加方便。

Spring Validation其实就是对Hibernate Validator进一步的封装，方便在Spring中使用。

## 校验方式
1. 通过实现org.springframework.validation.Validator接口，然后在代码中调用这个类
2. 按照Bean Validation方式来进行校验，即通过注解的方式。
3. 基于方法实现校验
4. 自定义校验

### 通过Validator接口实现
0. 引依赖：hibernate-validator、jakarta.el
1. 实体类：Person
2. 实现Validator接口：PersonValidator
3. 测试

### 通过注解实现
1. 配置类：ValidationConfig
2. 实体类：User，在属性上添加注解校验

   - 常用注解说明：
   @NotNull	限制必须不为null
   @NotEmpty	只作用于字符串类型，字符串不为空，并且长度不为0
   @NotBlank	只作用于字符串类型，字符串不为空，并且trim()后不为空串
   @DecimalMax(value)	限制必须为一个不大于指定值的数字
   @DecimalMin(value)	限制必须为一个不小于指定值的数字
   @Max(value)	限制必须为一个不大于指定值的数字
   @Min(value)	限制必须为一个不小于指定值的数字
   @Pattern(value)	限制必须符合指定的正则表达式
   @Size(max,min)	限制字符长度必须在min到max之间
   @Email	验证注解的元素值是Email，也可以通过正则表达式和flag指定自定义的email格式

