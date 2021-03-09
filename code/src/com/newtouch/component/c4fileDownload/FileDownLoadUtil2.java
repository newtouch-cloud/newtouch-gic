package com.newtouch.component.c4fileDownload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class FileDownLoadUtil2 {
	
	 public static HttpServletResponse downLoadFiles(File files,String zipname,
	            HttpServletRequest request, HttpServletResponse response)
	            throws Exception {
	        try {
	            /**这个集合就是你想要打包的所有文件，
	             * 这里假设已经准备好了所要打包的文件*/
//	            List<File> files = new ArrayList<File>();
	        	response.setCharacterEncoding("UTF-8"); //解决ie 8 下载中文附件乱码问题
	            /**创建一个临时压缩文件，
	             * 我们会把文件流全部注入到这个文件中
	             * 这里的文件你可以自定义是.rar还是.zip*/
	            File file = new File(zipname+".rar");
	            if (!file.exists()){   
	                file.createNewFile();   
	            }
	            response.reset();
	            //创建文件输出流
	            FileOutputStream fous = new FileOutputStream(file);   
	            /**打包的方法我们会用到ZipOutputStream这样一个输出流,
	             * 所以这里我们把输出流转换一下*/
	           ZipOutputStream zipOut    = new ZipOutputStream(fous);
	            /**这个方法接受的就是一个所要打包文件的集合，
	             * 还有一个ZipOutputStream*/
	           zipFile(files, zipOut);
	            zipOut.close();
	            fous.close();
	           return downloadZip(file,response);
	        }catch (Exception e) {
	                e.printStackTrace();
	            }
	            /**直到文件的打包已经成功了，
	             * 文件的打包过程被我封装在FileUtil.zipFile这个静态方法中，
	             * 稍后会呈现出来，接下来的就是往客户端写数据了*/
	        return response ;
	    }

	    /**
	     * 把接受的全部文件打成压缩包 
	     * @param List<File>;  
	     * @param org.apache.tools.zip.ZipOutputStream  
	     */
//	    public static void zipFile(List files,ZipOutputStream outputStream) {
//	        int size = files.size();
//	        for(int i = 0; i < size; i++) {
//	            File file = (File) files.get(i);
//	            zipFile(file, outputStream);
//	        }
//	    }

	    private static HttpServletResponse downloadZip(File file,HttpServletResponse response) {
	        try {
	        // 以流的形式下载文件。
	        InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
	        byte[] buffer = new byte[fis.available()];
	        fis.read(buffer);
	        fis.close();
	        // 清空response
	        response.reset();

	        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	        response.setContentType("application/octet-stream");
	        //如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
	        response.setHeader("Content-Disposition", "attachment;filename=" +URLEncoder.encode(file.getName(), "UTF-8"));
	        toClient.write(buffer);
	        toClient.flush();
	        toClient.close();
	        } catch (IOException ex) {
	        ex.printStackTrace();
	        }finally{
	             try {
	                    File f = new File(file.getPath());
	                    f.delete();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	        }
	        return response;
	    }

	    /**  
	     * 根据输入的文件与输出流对文件进行打包
	     * @param File
	     * @param org.apache.tools.zip.ZipOutputStream
	     */
	    private static void zipFile(File inputFile,
	            ZipOutputStream ouputStream) {
	        try {
	            if(inputFile.exists()) {
	                /**如果是目录的话这里是不采取操作的，
	                 * 至于目录的打包正在研究中*/
	                if (inputFile.isFile()) {
	                    FileInputStream IN = new FileInputStream(inputFile);
	                    BufferedInputStream bins = new BufferedInputStream(IN, 512);
	                    //org.apache.tools.zip.ZipEntry
	                    /*
	                     * 本系统中大部分上传的文件的被加了十位随机"(字母或数字)+_" 用于判断唯一,下载时删除此前缀
	                     */
	                    String fileName = inputFile.getName();
	                    ZipEntry entry = new ZipEntry(fileName.substring(fileName.indexOf("_")+1, fileName.length()));
	                    ouputStream.putNextEntry(entry);
	                    // 向压缩文件中输出数据   
	                    int nNumber;
	                    byte[] buffer = new byte[512];
	                    while ((nNumber = bins.read(buffer)) != -1) {
	                        ouputStream.write(buffer, 0, nNumber);
	                    }
	                    // 关闭创建的流对象   
	                    bins.close();
	                    IN.close();
	                } else {
	                    try {
	                        File[] files = inputFile.listFiles();
	                        for (int i = 0; i < files.length; i++) {
	                            zipFile(files[i], ouputStream);
	                        }
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static HttpServletResponse downloadZipNew(File file,String fname,HttpServletResponse response) throws IOException {
	        try {
	        // 以流的形式下载文件。
	        InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
	        byte[] buffer = new byte[fis.available()];
	        fis.read(buffer);
	        fis.close();
	        // 清空response
	        response.reset();

	        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	        response.setContentType("application/octet-stream");
	        //如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
	        response.setHeader("Content-Disposition", "attachment;filename=" +URLEncoder.encode(fname, "UTF-8"));
	        toClient.write(buffer);
	        toClient.flush();
	        toClient.close();
	        } catch (IOException ex) {
	        	response.setContentType("text/html; charset=UTF-8"); //转码
	        	response.getWriter().println("<script>");
			    
	        	response.getWriter().println("alert('文件不存在！');");
	        	response.getWriter().println("history.back();");
	        	response.getWriter().println("</script>");
	        }finally{
	             try {
	                    //File f = new File(file.getPath());
	                    //f.delete();//删除方法，下载一次就删掉路径下的文件
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	        }
	        return response;
	    }
	    /**
	     * @author liuhaiyue
	     * @param file  添加了一个删除的方法，将新建立的一个下载之后
	     * 直接删除，避免重复生成文件。
	     * @param fname
	     * @param response
	     * @return
	     */
	    public static HttpServletResponse downloadZipNewExcel(File file,String fname,HttpServletResponse response) {
	        try {
	        // 以流的形式下载文件。
	        InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
	        byte[] buffer = new byte[fis.available()];
	        fis.read(buffer);
	        fis.close();
	        // 清空response
	        response.reset();

	        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	        response.setContentType("application/octet-stream");
	        //如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
	        response.setHeader("Content-Disposition", "attachment;filename=" +URLEncoder.encode(fname, "UTF-8"));
	        toClient.write(buffer);
	        toClient.flush();
	        toClient.close();
	        } catch (IOException ex) {
	        ex.printStackTrace();
	        }finally{
	             try {
	                    File f = new File(file.getPath());
	                    f.delete();//删除方法，下载一次就删掉路径下的文件
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	        }
	        return response;
	    }
}
