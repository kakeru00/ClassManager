package com.zh.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class MyFileUtils {

	// 创建文件
	private static void createFile(String path, String fileName) {
		// path表示你所创建文件的路径
		File f = new File(path);

		// fileName表示你创建的文件名；
		File file = new File(f, fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 将文件写入到zip文件中
	 * 
	 * @param inputFile
	 * @param outputstream
	 * @throws Exception
	 */
	private static void zipFile(File inputFile, ZipOutputStream outputstream) throws IOException {
		try {
			if (inputFile.exists()) {
				if (inputFile.isFile()) {
					FileInputStream inStream = new FileInputStream(inputFile);
					BufferedInputStream bInStream = new BufferedInputStream(inStream);
					ZipEntry entry = new ZipEntry(inputFile.getName());
					outputstream.putNextEntry(entry);

					final int MAX_BYTE = 10 * 1024 * 1024; // 最大的流为10M
					long streamTotal = 0; // 接受流的容量
					int streamNum = 0; // 流需要分开的数量
					int leaveByte = 0; // 文件剩下的字符数
					byte[] inOutbyte; // byte数组接受文件的数据

					streamTotal = bInStream.available(); // 通过available方法取得流的最大字符数
					streamNum = (int) Math.floor(streamTotal / MAX_BYTE); // 取得流文件需要分开的数量
					leaveByte = (int) streamTotal % MAX_BYTE; // 分开文件之后,剩余的数量

					
					if (streamNum > 0) {
						for (int j = 0; j < streamNum; ++j) {
							inOutbyte = new byte[MAX_BYTE];
							// 读入流,保存在byte数组
							bInStream.read(inOutbyte, 0, MAX_BYTE);
							outputstream.write(inOutbyte, 0, MAX_BYTE); // 写出流
						}
					}
					// 写出剩下的流数据
					inOutbyte = new byte[leaveByte];
					bInStream.read(inOutbyte, 0, leaveByte);
					outputstream.write(inOutbyte);
					outputstream.closeEntry(); // Closes the current ZIP entry
					// and positions the stream for
					// writing the next entry
					bInStream.close(); // 关闭
					inStream.close();
				
				}
			} else {
				
			}
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * 压缩文件列表中的文件
	 * 
	 * @param files
	 * @param outputStream
	 * @throws IOException
	 */
	private static void zipFile(File[] files, ZipOutputStream outputStream) throws IOException {
		try {
			int size = files.length;
			System.out.println("files size:"+size);
			// 压缩列表中的文件
			for (int i = 0; i < size; i++) {
				File file = files[i];
				zipFile(file, outputStream);
			}
		} catch (IOException e) {
			throw e;
		}
	}
	
	public static void packageZip(String outFilePath,String zipName,File[] files) throws IOException{
		File zip = new File(outFilePath +"/"+ zipName);
		if(zip.exists()){
			zip.delete();
		}
		createFile(outFilePath, zipName);
		// 文件输出流
		FileOutputStream outStream = new FileOutputStream(zip);
		// 压缩流
		ZipOutputStream zipOutStream = new ZipOutputStream(outStream);
		
		MyFileUtils.zipFile(files, zipOutStream);
		zipOutStream.close();
		outStream.close();
		
	}
	
	public static File[] getFileList(String uploadFilePath){//获取上传文件的目录
		 
       //存储要下载的文件名
    
       File file = new File(uploadFilePath);
       if(!file.isFile()){
       	File[] files = file.listFiles();
       	return files;
       }
       return null;
      
	}
	
	// 递归删除文件夹
		public static void deleteFile(File file) {
			if (file.exists()) {// 判断文件是否存在
				if (file.isFile()) {// 判断是否是文件
					System.out.println("删除文件"+file.getPath());
					file.delete();// 删除文件
				} else if (file.isDirectory()) {// 否则如果它是一个目录
					System.out.println("删除文件夹"+file.getPath());
					File[] files = file.listFiles();// 声明目录下所有的文件 files[];
					for (int i = 0; i < files.length; i++) {// 遍历目录下所有的文件
						deleteFile(files[i]);// 把每个文件用这个方法进行迭代
					}
					file.delete();// 删除文件夹
				}
			} else {
				System.out.println("所删除的文件不存在");
			}
		}
	
}
