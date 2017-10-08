package com.khphub;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author khpark
 * A collection of useful functions related to Network
 */
public class NetUtils {

	/**
	 * It fetches the ip of the computer on which the function is executed, and the operating system shows that ip is the same as linux on the window.
	 * @return
	 * @throws SocketException
	 */
	public String getHostIPforAllOS() throws SocketException{
		String WasServerIP = "";
		Enumeration<NetworkInterface> nienum = NetworkInterface.getNetworkInterfaces();
		while (nienum.hasMoreElements()) {
			NetworkInterface ni = nienum.nextElement();
			Enumeration<InetAddress> kk= ni.getInetAddresses();
			while (kk.hasMoreElements()) {
				InetAddress inetAddress = kk.nextElement();
				if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && 
						inetAddress.isSiteLocalAddress()) {
					WasServerIP = inetAddress.getHostAddress().toString();
				}
			}
		}
		
		return WasServerIP;
	}
}
