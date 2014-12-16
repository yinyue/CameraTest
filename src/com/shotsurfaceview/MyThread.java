package com.shotsurfaceview;

import java.io.ByteArrayInputStream;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.wanghai.Camera.StreamIt;

public class MyThread extends Thread {
	private byte byteBuffer[] = new byte[1024];
	private OutputStream outsocket;
	private OutputStream wenzi;
	private ByteArrayOutputStream myoutputstream;
	private String ipname;
	private String aa;

	//public MyThread(ByteArrayOutputStream myoutputstream, String ipname, String aa) {
	public MyThread(StreamIt streamit, String ipname) throws IOException {
	//public MyThread(ByteArrayOutputStream myoutputstream, String ipname) {
//		ByteArrayOutputStream bo = new ByteArrayOutputStream();  
//        ObjectOutputStream oo = new ObjectOutputStream(bo);  
//        oo.writeObject(streamit);   
//        oo.close(); 
		//this.myoutputstream = myoutputstream;
		this.myoutputstream = streamit.outstream;
        //this.myoutputstream = bo;
		this.ipname = ipname;
		//this.aa = streamit.aa;
		
		try {
			myoutputstream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			// 将图像数据通过Socket发送出去
			Socket tempSocket = new Socket(ipname, 6011);
			outsocket = tempSocket.getOutputStream();
			
//			Socket wenziSocket = new Socket(ipname, 6012);
//			wenzi = wenziSocket.getOutputStream();
//			PrintWriter pw=new PrintWriter(wenzi);  
//			String str = "yinyue";
//			pw.write(str);  
//          pw.flush();  
//          wenziSocket.shutdownOutput(); 
			
			ByteArrayInputStream inputstream = new ByteArrayInputStream(
					myoutputstream.toByteArray());
			
			int amount;
			while ((amount = inputstream.read(byteBuffer)) != -1) {
				outsocket.write(byteBuffer, 0, amount);
			}
			
			myoutputstream.flush();
//            pw.close();  
//            wenzi.close();  
//            wenziSocket.close(); 
			myoutputstream.close();
			tempSocket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


