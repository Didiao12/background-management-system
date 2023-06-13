import cn.didiao.annotation.Controller;
import cn.didiao.controller.UserController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {

        Annotation[] annotations = UserController.class.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
        for (Method method : UserController.class.getMethods()) {
            for (Annotation annotation : method.getAnnotations()) {
                System.out.println(annotation);
            }
        }

        System.out.println("Hello world!");
    }
}
