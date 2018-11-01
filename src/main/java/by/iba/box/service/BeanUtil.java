package by.iba.box.service;


import by.iba.box.config.EntityConfig;
import lombok.experimental.UtilityClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@UtilityClass
public class BeanUtil {

    private ApplicationContext context = new AnnotationConfigApplicationContext(
            EntityConfig .class);

    public static  <T> T getBean(java.lang.Class<T> requiredType){
        return context.getBean(requiredType);
    }

    public static  <T> T getBean(java.lang.String name,
                              java.lang.Class<T> requiredType){
        return context.getBean(name,requiredType);
    }

}
