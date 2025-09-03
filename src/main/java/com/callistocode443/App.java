package com.callistocode443;

import com.callistocode443.config.AppConfig;
import com.callistocode443.console.OperationsConsoleListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        OperationsConsoleListener listener = context.getBean(OperationsConsoleListener.class);

        listener.operationListener();

        context.close();
    }
}
