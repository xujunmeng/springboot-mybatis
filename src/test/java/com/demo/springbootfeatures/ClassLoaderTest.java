package com.demo.springbootfeatures;

import org.junit.Test;
import sun.misc.Launcher;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

/**
 * @author james
 * @date 2018/11/13
 */
public class ClassLoaderTest {

    @Test
    public void testBootstrapclass() {
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL.toExternalForm());
        }
        System.out.println(System.getProperty("sun.boot.class.path"));

    }

    @Test
    public void testClassLoader() {
        // 此时三个ClassLoader是同一个对象
        System.out.println(Thread.currentThread().getContextClassLoader()); // 当前线程的类加载器
        System.out.println(ClassLoaderTest.class.getClassLoader()); // 当前类的类加载器
        System.out.println(ClassLoader.getSystemClassLoader()); // 系统初始的类加载器
    }

    @Test
    public void testnewInstance() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        ClassLoaderTest classLoaderTest = ClassLoaderTest.class.newInstance();

        ClassLoaderTest classLoaderTest1 = classLoaderTest.getClass().newInstance();

        Class<ClassLoaderTest> aClass = (Class<ClassLoaderTest>) Class.forName("com.demo.springbootfeatures.ClassLoaderTest");
        ClassLoaderTest classLoaderTest2 = aClass.newInstance();

        System.out.println(classLoaderTest instanceof ClassLoaderTest);
        System.out.println(classLoaderTest1 instanceof ClassLoaderTest);
        System.out.println(classLoaderTest2 instanceof ClassLoaderTest);
    }

    @Test
    public void testConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class[] classes = new Class[]{String.class, Integer.class};
        Person person = Person.class.getConstructor(classes).newInstance("XiaoMing", 10);
        System.out.println(person);

        Constructor<Person> declaredConstructor = Person.class.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);
        Person person1 = declaredConstructor.newInstance("XiaoQiang");
        System.out.println(person1);
    }

}
