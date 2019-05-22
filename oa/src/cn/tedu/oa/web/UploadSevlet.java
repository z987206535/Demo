package cn.tedu.oa.web;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.oa.utils.Upload;

public class UploadSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UploadSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Upload up= new Upload();
		up.init(request);
		ServletContext ctx = 
				  getServletContext();
		String path = ctx.getRealPath("upload");
				System.out.println(path);		
		up.setSaveDir(path);
		up.setTagFileName(new Random().nextInt(99999)+"");
		up.uploadFile();
		
		

		
		/*
		 //1：创建目录 webcontent/upload
		 //2: FileUpload 开源组完成上传
		 //3: 创建二个对象解析上传文件
		 DiskFileItemFactory 
		 factory = new DiskFileItemFactory();
		 ServletFileUpload
		    sfu = 
		    new ServletFileUpload(factory);
		 //4:每一个FileItem对象包含了一个表单域
		 //的信息,可以通过访问该对象的方法获取
		 //相应的数据
		 try{
		 List<FileItem> items = 
		      sfu.parseRequest(request);
		 //5:针对每一个表单域进行处理
		 for(int i=0;i<items.size();i++){
			FileItem curr = items.get(i);
			//6:区分是普通表单域还是文件上传域
			if(curr.isFormField()){
			  //7:是一个普通的表单域
			  System.out.println(curr.toString());
			}else{
			  //8:是一个文件上传域
			  //9:上传文件后物理路径 upload
			  //d:/wtp/j2ee/upload
			  //10:获取upload目录路径
			ServletContext ctx = 
			  getServletContext();
			String path = ctx.getRealPath("upload");
			System.out.println(path);
			//11:获文件名
			String fileName = curr.getName();
			File file =
					 new File(path+"\\"+fileName);
			curr.write(file);
			}
		 }
		 }catch(Exception e){
			  e.printStackTrace();
		 }
		 */
	}

}
