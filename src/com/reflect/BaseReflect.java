package com.reflect;

import com.been.Reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * Created by Administrator on 2018/3/2 0002.
 */
public class BaseReflect {

    public static void main(String args[]) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String path = "com.been.Reflect";
        Class clazz1 = Class.forName(path);
        Class clazz2 = Class.forName(path);

        //Class 虚拟机把类加载到内存，Class是一种类型
        System.err.println(clazz1.hashCode() == clazz2.hashCode());

        //获取类信息
        System.err.println("完全限定类名:"+clazz1.getName());
        System.err.println("类名："+clazz1.getSimpleName());

        //获取属性字段
        //getField | getFields 只能获取访问方式为public的属性
        System.err.println("获取public声明的属性"+clazz1.getFields().length);
        System.err.println("获取public声明的属性"+clazz1.getField("phone"));

        //getDeclaredFields | getDeclaredField 可以获取所有属性 不区分权限
        System.err.println("获取所有属性:"+clazz1.getDeclaredFields().length);
        System.err.println("获取所有属性:"+clazz1.getDeclaredField("name"));
        for (Field field :clazz1.getDeclaredFields()){
            System.err.println("属性："+field);
        }

        //获取方法的信息
        Method[] methods = clazz1.getDeclaredMethods();
        for (Method m : methods){
            System.err.println("方法:"+m+"--参数类型:"+ Arrays.toString(m.getParameterTypes()));
        }
        //第二个参数 是方法的参数类型，是一个可变参数
        System.err.println(clazz1.getDeclaredMethod("setAge",Integer.class));

        //获取构造器
        System.err.println("构造器："+Arrays.toString(clazz1.getDeclaredConstructors()));
        System.err.println("构造器："+clazz1.getDeclaredConstructor(String.class,Integer.class));

        //动态创建对象
        //无参创建对象
        Reflect reflect = (Reflect) clazz1.newInstance();
        //使用有参构造器创建对象
        Constructor<Reflect> c = clazz1.getDeclaredConstructor(String.class,Integer.class);
        Reflect reflect1 = c.newInstance("小米",12);
        System.err.println("reflect"+reflect+"  reflect1"+reflect1);

        //通过反射调用方法
        Method method = clazz1.getMethod("setName", String.class);
        method.invoke(reflect1,"qwertyu");
        System.err.println("reflect1 getName"+reflect1.getName());

        //通过反射操作属性
        Field f = clazz1.getDeclaredField("name");
        //java.lang.IllegalAccessException: Class com.reflect.BaseReflect can not access a member of class com.been.Reflect with modifiers "private"
       // f.setAccessible(true);  // 不需要安全检查  可以直接访问  同时设置为true 能提高反射的效率
        //f.set(reflect1,"ppqqcc");
       // System.err.println("reflect1 setPropt name"+reflect1.getName());
       // System.err.println("reflect1 setPropt name"+f.get(reflect1));  //通过反射获取属性值


        //获取泛型类型    方法中带有 Generic  则与获取泛型有关
        Field field = clazz1.getField("map");
        System.err.println("获取属性类型："+field.getType().getName());
        //获取反射类型  <String,Array>   ParameterizedType 反射类型实体类
        Type type = field.getGenericType();
        //判断
        if(type instanceof ParameterizedType){
            Type[] types = ((ParameterizedType)type).getActualTypeArguments();
            for (Type t:types) {
                System.err.println("泛型类型:"+t);
            }
        }

    }
}
