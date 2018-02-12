package com.heartdreamy.jmx;

public interface HelloMBean {
	public void sayHello();
	public int add(int x, int y);
	public String getName();

	public int getPort();
	public void setPort(int port);
	public String getHost();
	public void setHost(String host);
}
