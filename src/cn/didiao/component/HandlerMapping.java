package cn.didiao.component;

import cn.didiao.annotation.Controller;
import cn.didiao.annotation.RequestMapping;
import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.lang.ClassScanner;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;

public class HandlerMapping {





    public  HashMap<String ,InvocationHandler> urlMapping(){
        //可以更换到Init里 待决定
        String url="cn.didiao.controller";
        Set<Class<?>> set = ClassScanner.scanPackageByAnnotation(url, Controller.class);

        HashMap<String ,InvocationHandler> map=new HashMap();
        for (Class<?> aClass : set) {
            String classPath = AnnotationUtil.getAnnotationValue(aClass, RequestMapping.class);
            System.out.println("classPath:"+classPath);
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                boolean b = AnnotationUtil.hasAnnotation(method, RequestMapping.class);
                if (b){
                    String methodPath = AnnotationUtil.getAnnotationValue(method, RequestMapping.class);
                    System.out.println("methodPath:"+methodPath);
                    try {
                        InvocationHandler inv=new InvocationHandler(aClass.newInstance(),method);
                        map.put(classPath+methodPath,inv);
                    } catch (InstantiationException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }
        return map;
    }


}
