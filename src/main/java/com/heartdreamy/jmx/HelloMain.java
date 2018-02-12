package com.heartdreamy.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class HelloMain {
    public static void main(String[] args){
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = new ObjectName("com.heartdreamy.jmx:name=Hello");
            Hello mbean = new Hello();
            mBeanServer.registerMBean(mbean, name);

            HelloListener helloListener = new HelloListener();
            mBeanServer.addNotificationListener(name,helloListener,null,null);
            System.out.println("Waiting forever...");
            Thread.sleep(Long.MAX_VALUE);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
        } catch (InstanceNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
