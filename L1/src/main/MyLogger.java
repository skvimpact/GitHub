/**
 * Created by KSafonov on 13/09/2017.
 */
//package org.stepic.java.logging;
import java.util.logging.*;
//import java.util.logging.
public class MyLogger {
    public static void main(String[] args) {
        //LogDemo.LOGGER
        /*int x = 100;
        LogDemo.LOGGER.setLevel(Level.FINEST);
        LogDemo.LOGGER.log(Level.INFO, "I'm logging");
        LogDemo.LOGGER.log(Level.FINEST, "I'm logging {0}"+ x);
        //LogDemo.LOGGER.setLevel(Level.);
        System.out.println(MyLogger.class.getName());*/

       // LogDemo ld = new LogDemo();


        //ClassA.
       // configureLogging();
        //System.out.println(ClassA.LOGGER.getLevel());
        //LogManager.getLogManager().readConfiguration(ClassA.class.getResourceAsStream("logging.properties"));
        Logger orgStepicJavaLogger = Logger.getLogger("");
        ConsoleHandler handler = new ConsoleHandler();
        orgStepicJavaLogger.setLevel(Level.ALL);
        handler.setLevel(Level.WARNING);
        //handler.setFormatter(new XMLFormatter());
        orgStepicJavaLogger.addHandler(handler);

        ClassA classA = new ClassA();
        ClassB classB = new ClassB();
        //System.out.println(classA.LOGGER.getLevel());
        classA.info();
        classB.info();
        //ClassB classB = new ClassB();
      //  classA.incI();
       // classA.incI();
        //LogDemo.LOGGER.
        //System.out.printf("%d is a %f", 0, 0.0);
    }
    private static void configureLogging() {
        // your implementation here

        Logger classALogger = Logger.getLogger("org.stepic.java.logging.ClassA");
        classALogger.setLevel(Level.ALL);
       // classALogger.setUseParentHandlers(true);
       // Logger.getLogger("org.stepic.java.logging.ClassA").setLevel(Level.WARNING);
/*
        Logger orgStepicJavaLogger = Logger.getLogger("org.stepic.java");
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        handler.setFormatter(new XMLFormatter());
        orgStepicJavaLogger.addHandler(handler);
        orgStepicJavaLogger.setUseParentHandlers(false);
        */

   }

}

class LogDemo{
    private static final Logger LOGGER = Logger.getLogger(MyLogger.class.getName());
    private final int i = 9;
    public LogDemo(){
        LOGGER.info(String.format("%d is a %d and %f", 0, 1, 2.0));
    }
//java.util.logging.XMLFormatter
}

class ClassA{
    private static final Logger LOGGER = Logger.getLogger(ClassA.class.getName());
    public void info(){
        System.out.println(LOGGER.getLevel());
        LOGGER.info("Info");
        LOGGER.warning("Warning");
        LOGGER.severe("Severe");
    }
}

class ClassB{
    private static final Logger LOGGER = Logger.getLogger(ClassB.class.getName());
    public void info(){
        LOGGER.info("Info");
        LOGGER.warning("Warning");
        LOGGER.severe("Severe");
    }
}


