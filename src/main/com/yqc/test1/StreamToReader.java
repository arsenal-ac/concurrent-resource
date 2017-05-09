package com.yqc.test1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;

/**
 * InputStreamת��ΪInputReader
 * @author yangqc
 * 2016��7��13��
 */
public class StreamToReader {
	public static void main(String[] args) throws IOException {
		InputStream inputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\hello.txt");
		Reader reader = new InputStreamReader(inputStream);
		//-1��һ��int���ͣ�����byte����char���ͣ����ǲ�һ���ġ�
		int data = reader.read();
		while (data != -1) {
			// ���ﲻ��������ݶ�ʧ����Ϊ���ص�int���ͱ���dataֻ�е�16λ�����ݣ���16λû������
			char theChar = (char) data;
			data = reader.read();
			System.out.print(theChar);
		}
		reader.close();
		OutputStream os=new OutputStream() {
			@Override
			public void write(int b) throws IOException {
			}
		};
	}
}
