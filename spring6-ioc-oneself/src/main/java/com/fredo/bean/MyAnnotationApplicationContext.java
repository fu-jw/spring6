package com.fredo.bean;

import com.fredo.anno.Bean;
import com.fredo.anno.Di;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义Bean容器实现类
 */
public class MyAnnotationApplicationContext implements MyApplicationContext {

    /**
     * 放bean对象
     * key      全类名
     * value    bean对象
     */
    private Map<Class, Object> beanMap = new HashMap<>();
    private static String rootPath;

    /**
     * 获取bean对象
     */
    @Override
    public Object getBean(Class clazz) {
        return beanMap.get(clazz);
    }

    /**
     * 创建有参构造器，根据参数定义扫描规则
     */
//    public MyAnnotationApplicationContext(String basePackage) {
    public static void demo(String basePackage) {
        // 1.根据包名找到文件的绝对路径
        // com.fredo...-> ...com/fredo...
        String pack = basePackage.replaceAll("\\.", "/");

//        String path = MyAnnotationApplicationContext.class.getClassLoader().getResource(pack).getPath();
        String path = Thread.currentThread().getContextClassLoader().getResource("/").getPath();

        System.out.println(path);
    }

    /**
     * 根据包扫描加载bean
     * @param basePackage
     */
    public MyAnnotationApplicationContext(String basePackage) {
        try {
            String packageDirName = basePackage.replaceAll("\\.", "\\\\");
            Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                String filePath = URLDecoder.decode(url.getFile(), "utf-8");
                rootPath = filePath.substring(0, filePath.length() - packageDirName.length());
                loadBean(new File(filePath));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //依赖注入
        loadDi();
    }

    private void loadDi() {
        for(Map.Entry<Class,Object> entry : beanMap.entrySet()){
            //就是咱们放在容器的对象
            Object obj = entry.getValue();
            Class<?> aClass = obj.getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field field : declaredFields){
                Di annotation = field.getAnnotation(Di.class);
                if( annotation != null ){
                    field.setAccessible(true);
                    try {
                        System.out.println("正在给【"+obj.getClass().getName()+"】属性【" + field.getName() + "】注入值【"+ beanMap.get(field.getType()).getClass().getName() +"】");
                        field.set(obj,beanMap.get(field.getType()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void loadBean(File fileParent) {
        if (fileParent.isDirectory()) {
            File[] childrenFiles = fileParent.listFiles();
            if (childrenFiles == null || childrenFiles.length == 0) {
                return;
            }
            for (File child : childrenFiles) {
                if (child.isDirectory()) {
                    //如果是个文件夹就继续调用该方法,使用了递归
                    loadBean(child);
                } else {
                    //通过文件路径转变成全类名,第一步把绝对路径部分去掉
                    String pathWithClass = child.getAbsolutePath().substring(rootPath.length() - 1);
                    //选中class文件
                    if (pathWithClass.contains(".class")) {
                        //    com.xinzhi.dao.UserDao
                        //去掉.class后缀，并且把 \ 替换成 .
                        String fullName = pathWithClass.replaceAll("\\\\", ".").replace(".class", "");
                        try {
                            Class<?> aClass = Class.forName(fullName);
                            //把非接口的类实例化放在map中
                            if (!aClass.isInterface()) {
                                Bean annotation = aClass.getAnnotation(Bean.class);
                                if (annotation != null) {
                                    Object instance = aClass.newInstance();
                                    //判断一下有没有接口
                                    if (aClass.getInterfaces().length > 0) {
                                        //如果有接口把接口的class当成key，实例对象当成value
                                        System.out.println("正在加载【" + aClass.getInterfaces()[0] + "】,实例对象是：" + instance.getClass().getName());
                                        beanMap.put(aClass.getInterfaces()[0], instance);
                                    } else {
                                        //如果有接口把自己的class当成key，实例对象当成value
                                        System.out.println("正在加载【" + aClass.getName() + "】,实例对象是：" + instance.getClass().getName());
                                        beanMap.put(aClass, instance);
                                    }
                                }
                            }
                        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


//    public static void main(String[] args) {
//        demo("com.fredo.bean");
         // 打印结果：
         // /D:/Develop/Projects/ideaProject/spring6/spring6-ioc-oneself/target/classes/com/fredo/bean
//    }

}
