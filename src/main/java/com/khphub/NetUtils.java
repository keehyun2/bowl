package com.khphub;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author khpark
 *	Network과 관련된 쓸만한 함수들을 모아 놓았습니다. <br/>
 * A collection of useful functions related to Network
 */
public class NetUtils {

	/**
	 * 함수가 실행되는 Computer 의 ip 를 가져오고, 운영체제가 window이던 linux이던 ip가 같다고 나타납니다. <br/>
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
