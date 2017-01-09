package hzj.controller.upload;

import hzj.controller.base.BaseController;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by MichaelHe on 2016/11/6.
 */
@Controller
@RequestMapping(value = "/upload")
public class UploadFileController extends BaseController{


    private final Logger logger = Logger.getLogger(UploadFileController.class);

    @RequestMapping(value = "/showPage")
    public String showPage(HttpServletRequest request, ModelMap model) {


        return "upload/UploadFile";
    }


    @RequestMapping(value = "/uploadFile")
    public String uploadFile(HttpServletRequest request, HttpServletResponse response, ModelMap model) {


        System.out.println(request.getContextPath());
        System.out.println(request.getServletPath());
        System.out.println(request.getRealPath("WEB-INF/upload"));
        //创建一个解析工厂
        DiskFileItemFactory factory=new DiskFileItemFactory();
        //设置临时缓存文件的保存目录
        factory.setRepository(new File(request.getContextPath()+"/temp"));

        //得到解析器对象
        ServletFileUpload upload=new ServletFileUpload(factory);
        //设置保存文件的编码方式，
        upload.setHeaderEncoding("UTF-8");
        try{
            //设置上传文件的最大值大小，最大值为200MB
            upload.setFileSizeMax(1024*1024*200);

            //定义规定上传文件的类型
            String[]arr={".jpg",".zip",".txt",".ppt",".pptx",".doc",".docx",".xls",".gif"};
            //将类型放到List中
            List fileStandType= Arrays.asList(arr);
            //对请求进行解析,有几个输入项就会有几个FileItem对象
            List<FileItem> items=upload.parseRequest(request);

            for(FileItem item:items){
                //判断输入元素的类型，
                if(item.isFormField()){//是普通项
                    //得到name属性
                    String inputName=item.getFieldName();
                    //得到相对应的值
                    String inputValue=item.getString("UTF-8");//可指定字符编码，以防乱码
                    System.out.println(inputName+" : "+inputValue);
                }else{//是上传文件输入项
                    //获取上传文件名称
                    String fileName=item.getName();
                    //判断fileName是否为空即是否真的选择了上传文件,不为空继续
                    if(!fileName.trim().equals("")){
                        //对文件名进行处理得到文件名
                        fileName=fileName.substring(fileName.lastIndexOf("\\")+1);
                        //得到文件后缀判断文件类型
                        String fileType=fileName.substring(fileName.lastIndexOf("."));
                        //判断是否是制定的文件类型
                        if(!fileStandType.contains(fileType)){
                            //如果不是制定类型的文件跳转页面,
                            request.setAttribute("fileTypeError","只能上传指定类型的文件，jpg/zip/txt/ppt/pptx/docx/doc/xls/gif");
                            request.getRequestDispatcher("/handler.jsp").forward(request, response);
                            return "";
                        }
                        //文件已选择,得到输入流
                        InputStream in=item.getInputStream();
                        //将上传的文件保存在服务器受保护的WEB-INF的目录下，
                        String savePath=request.getRealPath("WEB-INF/upload");
                        savePath=getFilePath(savePath,fileName);
                        //同名文件覆盖问题对fileName进行进一步处理,工具类UUID
                        fileName= UUID.randomUUID().toString()+"_"+fileName;
                        //构建输出流
                        FileOutputStream fos=new FileOutputStream(savePath+"\\"+fileName);
                        byte[] buffer=new byte[1024];
                        //int len=0;
                        while(in.read(buffer)>0){
                            fos.write(buffer);
                            fos.flush();
                        }
                        in.close();
                        fos.close();
                        request.setAttribute("finish","上传成功！");
                        item.delete();//在关闭流之后，删除临时缓存文件
                    }
                }
            }

        }catch(Exception e){
            request.setAttribute("finish","上传失败！");
        }
      //request.getRequestDispatcher("/handler.jsp").forward(request, response);

        return "upload/UploadFile";
    }

    //方法对文件保存目录进行处理，
    public String getFilePath(String path,String fileName){
        //产生目录结构的算法：hash目录
        int dir1=fileName.hashCode()&0x0f;//一级目录
        int dir2=fileName.hashCode()>>4 &0x0f;//二级目录
        String savePath=path+"\\"+dir1+"\\"+"\\"+dir2;
        File file=new File(savePath);
        if(!file.exists()){
            file.mkdirs();
        }
        return savePath;
    }

}
