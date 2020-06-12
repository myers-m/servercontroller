package controllerserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class controllerserver{
	Socket socket;
	Scanner scanner;
	InputStream in;
	OutputStream out;
	
	public boolean run=true;

	public controllerserver() throws UnknownHostException, IOException {
		this.socket=new Socket("127.0.0.1",8000);
		this.in=this.socket.getInputStream();
		this.out=this.socket.getOutputStream();
		this.scanner=new Scanner(System.in);
	}
	
	
	public void Create() throws IOException {
		System.out.print("Instruction:");
		while(this.run) {
			if(this.scanner.hasNext()) {
				String need=this.scanner.nextLine();
				this.scanner.reset();
				
				this.set(need);
				this.output(this.get());
				
				if(need.equals("quit")) {
					this.run=false;
					System.exit(1);
				}
				else {
				System.out.print("Instruction:");
				}
			}
		}
	}
	
	void set(String need) throws IOException {
		this.out.write((need+"-0").getBytes());
		this.out.flush();
	}
	
	String get() throws IOException {
		byte[] need = new byte[1024];
		String res = "";
		
		int i=0;
		boolean run=true;
		while(run) {
			i = in.read(need);
			res+=new String(need,0,i);
			if(res.contains("-0")) {
				res=res.replace("-0", "");
				run=false;
			}
		}
		return res;
	}
	
	void output(String need) {
		if(need.contains("/n")) {
			String[] res=need.split("/n");
			for(int i=0;i<res.length;i++) {
				System.out.println(res[i]);
			}
		}
		else {
			System.out.println(need);
		}
	}
}
