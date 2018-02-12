package com.heartdreamy.jmx;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.util.concurrent.atomic.AtomicLong;

public class Hello extends NotificationBroadcasterSupport implements HelloMBean {
    private final String name = "shizhouqi";
    private int port;
    private String host;
    private AtomicLong sequenceNumber = new AtomicLong(1);

    @Override
    public void sayHello() {
        System.out.println("Hello, World");
    }

    @Override
    public int add(int x, int y) {
        System.out.println("calculate: "+ x +"+" +y);
        return x + y;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public void setPort(int port) {
        int oldPort = this.port;
        this.port = port;
        System.out.println("port now is :" + this.port);

        //Object source,        事件源，一直传递到java.util.EventObject的source
        //long sequenceNumber,  通知序号，标识每次通知的计数器
        //long timeStamp,       通知发出的时间戳
        //String msg,           通知发送的message
        //String attributeName, 被修改属性名
        //String attributeType, 被修改属性类型
        //Object oldValue,      被修改属性修改以前的值
        //Object newValue       被修改属性修改以后的值
        Notification notification = new AttributeChangeNotification(this, sequenceNumber.getAndIncrement(),
                System.currentTimeMillis(),"port value has changed", "port",
                "int",oldPort,this.port);

        sendNotification(notification);
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public void setHost(String host) {
        String oldHost = this.host;
        this.host = host;
        System.out.println("host now is :" + this.host);

        Notification notification = new AttributeChangeNotification(this, sequenceNumber.getAndIncrement(),
                System.currentTimeMillis(),"host value has changed", "host",
                "int",oldHost,this.host);

        sendNotification(notification);
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[]{ AttributeChangeNotification.ATTRIBUTE_CHANGE};
        String name = "hello attribute";
        String description = "an attribute of this MBean has changed";
        MBeanNotificationInfo info = new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[]{info};
    }

}
