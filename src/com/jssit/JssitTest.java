package com.jssit;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/3/5 0005.
 */
public class JssitTest {
    public static void main(String args[]) throws Exception{
        //createClass();

        //javassist 基本方法
        //baseClassInfo();
       // new Reflect();
        //创建方法
        //createMethod();
        //操作方法
        //oprMethod();
        //操作属性
        //oprProp();
        //构造器
        getCons();
        pvq
    }
    private static void getCons() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("com.been.Reflect");
        CtConstructor[] cons = ctClass.getDeclaredConstructors();
        for (CtConstructor con : cons) {
            System.err.println(con.getLongName());
        }
    }

    private static void oprProp() throws NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("com.been.Reflect");
        CtField school = new CtField(pool.get("java.lang.String"), "school", ctClass);
        school.setModifiers(Modifier.PUBLIC);
        ctClass.addField(school);
        ctClass.addMethod(CtNewMethod.setter("setSchool",school));
        ctClass.addMethod(CtNewMethod.getter("getSchool",school));

        //反射调用
        Class clazz = ctClass.toClass();
        Object t = clazz.newInstance();
        Method setSchool = clazz.getDeclaredMethod("setSchool", String.class);
        setSchool.invoke(t,"清华大学");
        Method getSchool = clazz.getDeclaredMethod("getSchool");
        System.err.println(getSchool.invoke(t));
    }

    private static void oprMethod() throws NotFoundException, CannotCompileException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("com.been.Reflect");
        CtMethod say = ctClass.getDeclaredMethod("say",new CtClass[]{CtClass.intType});
        say.insertBefore("System.err.println(\"start\");System.err.println($1);");
        say.insertAfter("System.err.println(\"end\");");

        Class clazz = ctClass.toClass();
        Method sayMethod = clazz.getDeclaredMethod("say",int.class);
        sayMethod.invoke(clazz.newInstance(),100);
    }

    private static void createMethod() throws NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("com.been.Reflect");
        CtMethod add = new CtMethod(CtClass.intType, "add", new CtClass[]{CtClass.intType, CtClass.intType},ctClass);
        add.setModifiers(Modifier.PUBLIC);
        //add.setBody("{return 1;}");
        //add.setBody("{return a+b}");
        //Exception in thread "main" java.lang.NoClassDefFoundError: java/lang/StackWalker$Option  jdk升级到 jdk9  或使用低版本的javassist.jar 如javassist-3.4.GA.jar
        add.setBody("{return $1+$2;}");
        ctClass.addMethod(add);

        Class clazz = ctClass.toClass();
        System.err.println(clazz.getName());
        Object obj = clazz.newInstance();
        Method addMethod = clazz.getDeclaredMethod("add", int.class, int.class);
        System.err.println("反射调用method:"+addMethod.invoke(obj,100,200));
    }

    private static void baseClassInfo() throws NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("com.been.Reflect");
        System.err.println("className="+ctClass.getName());
        System.err.println("获取简要类名："+ctClass.getSimpleName());
        System.err.println("获取父类："+ctClass.getSuperclass().getSimpleName());
        System.err.println("获取接口:"+ctClass.getInterfaces());
    }

    private static void createClass() throws CannotCompileException, NotFoundException, IOException {
        //1.获取类池
        ClassPool pool = ClassPool.getDefault();
        //2.创建类
        CtClass ctClass = pool.makeClass("com.entity.Student");
        //3.创建属性
        CtField cName = CtField.make("private String name;", ctClass);
        CtField cAge = CtField.make("private int age;", ctClass);
        ctClass.addField(cName);
        ctClass.addField(cAge);
        //4.创建方法
        CtMethod getName = CtMethod.make("public String getName(){return this.name;}", ctClass);
        CtMethod setName = CtMethod.make("public void setName(String name){this.name = name;}", ctClass);
        ctClass.addMethod(setName);
        ctClass.addMethod(getName);

        CtConstructor constructor = new CtConstructor(new CtClass[]{CtClass.intType,pool.get("java.lang.String")},ctClass);
        constructor.setBody("{this.name = name;this.age=age;}");
        ctClass.addConstructor(constructor);

        ctClass.writeFile("G:/github/");
    }
}
