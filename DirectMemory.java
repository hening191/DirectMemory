package com.zkteco.biometric;

import java.nio.ByteBuffer;

import org.omg.CORBA.Current;

public class DirectMemory {

	public static void main(String[] args) {
		//测试参数
		int applicationMemory = 500;
		int applicationCount = 1000000;
		
		//申请内存测试
		long applicationStartTime = System.currentTimeMillis();
		for(int i = 0;i<applicationCount;i++){
			ByteBuffer buffer = ByteBuffer.allocate(applicationMemory);
		}
		long applicationHeapEndTime = System.currentTimeMillis();
		for(int i=0;i<applicationCount;i++){
			ByteBuffer buffer = ByteBuffer.allocateDirect(applicationMemory);
		}
		long applicationDirectEndTime = System.currentTimeMillis();
		
		System.out.println("Heap申请耗时:"+(applicationHeapEndTime - applicationStartTime));
		System.out.println("Direct申请耗时:"+(applicationDirectEndTime - applicationHeapEndTime));

		//读写测试
		ByteBuffer buffer = null;
		buffer = ByteBuffer.allocate(applicationMemory);
		long ReadAndWriteStartTime = System.currentTimeMillis();
		for(int i=0;i<applicationCount;i++){
			for (int j = 0; j < 100; j++) { 
			    buffer.putInt(j);  
			}
			buffer.flip();  
			for (byte j = 0; j < 100; j++) {  
			    buffer.getInt();  
			}  
			buffer.clear();  
		}
		long ReadAndWriteEndTime = System.currentTimeMillis();
		System.out.println("Heap读写耗时:"+(ReadAndWriteEndTime - ReadAndWriteStartTime));
		
		buffer = ByteBuffer.allocateDirect(applicationMemory);
		ReadAndWriteStartTime = System.currentTimeMillis();
		for(int i=0;i<applicationCount;i++){
			for (int j = 0; j < 100; j++) { 
			    buffer.putInt(j);  
			}
			buffer.flip();  
			for (byte j = 0; j < 100; j++) {  
			    buffer.getInt();  
			}  
			buffer.clear();  
		}
		ReadAndWriteEndTime = System.currentTimeMillis();
		System.out.println("Direct读写耗时:"+(ReadAndWriteEndTime - ReadAndWriteStartTime));
	}

}
