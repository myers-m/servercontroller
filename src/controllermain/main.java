package controllermain;

import java.io.IOException;
import java.net.UnknownHostException;
import controllerserver.controllerserver;

public class main {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("connect to port of 8000");
		new controllerserver().Create();
	}
}
