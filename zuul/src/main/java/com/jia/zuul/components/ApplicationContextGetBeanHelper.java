package com.jia.zuul.components;

import com.jia.common.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextGetBeanHelper implements ApplicationContextAware {
//    @Autowired
    private static ApplicationContext applicationContext;

    private User user;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static Object getBean(String className) throws BeansException,IllegalArgumentException {
        if(className==null || className.length()<=0) {
            throw new IllegalArgumentException("className为空");
        }

        String beanName = null;
        if(className.length() > 1) {
            beanName = className.substring(0, 1).toLowerCase() + className.substring(1);
        } else {
            beanName = className.toLowerCase();
        }
        return applicationContext != null ? applicationContext.getBean(beanName) : null;
    }

}