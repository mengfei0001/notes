package com.compair;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * Created by Administrator on 2018/3/2 0002.
 */
public class Compair {

    public static void main(String args[]) throws Exception {
//        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//        int res = compiler.run(null,null,null,"G:/github/src/com/reflect/Abc.java");
//        System.err.println(res == 0?"成功":"失败");
//        if(res == 0 ){
//            run();
//        }

        run2();
    }
    public static void run() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int res = compiler.run(null,null,null,"G:/github/src/com/reflect/Abc.java");
        System.err.println(res == 0?"成功":"失败");
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("java -cp G:/github/src/com/reflect/");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String temp;
            while ((temp = reader.readLine())!= null){
                System.err.println(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void run2()  throws Exception {
        URL[] urls = new URL[]{new URL("file:/"+"G:/github/src/com/reflect/"),new URL("file:/"+"G:/github/src/com/net/v1/")};
        System.err.println(Arrays.toString(urls));
        URLClassLoader loader = new URLClassLoader(urls);
        Class<?> abc = loader.loadClass("com.reflect.Abc");
        Method main = abc.getMethod("main", String[].class);
        main.invoke(null,(Object) new String[]{});
    }
}
