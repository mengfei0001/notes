package com.jssit;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.io.IOException;

/**
 * Created by Administrator on 2018/3/5 0005.
 */
public class JssitTest {
    public static void main(String args[]) throws CannotCompileException, NotFoundException, IOException {
        //createClass();

        //javassist 基本方法
        

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
