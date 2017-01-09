package hzj.utils;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpRequest;

import java.io.*;

/**
 * Created by hedwig on 2015/9/15.
 * In wxmngr
 */
public class FileUploadUtil {
    String resultMsg;

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getTmpDir() {
        return getBaseDir() + "temp/";
    }

    public String getBaseDir() {
        return "E:/";
    }

    public boolean moveFile(String FN, String desDir) {

        if (null == FN || 0 == FN.length() || null == desDir)
            return false;
        if (FN.contains(File.pathSeparator)) {
            return false;
        }
        desDir = getBaseDir() + desDir;
        if (!desDir.endsWith(File.separator)) {
            desDir += File.separator;
        }


        File tmpFile = new File(getTmpDir(), FN);
        File desFile = new File(desDir, FN);

        if (!desFile.getParentFile().exists()) {
            if (!desFile.getParentFile().mkdirs()) {
                return false;
            }
        }

        if (tmpFile.exists()) {
            try {
                FileUtils.copyFile(tmpFile, desFile);
            } catch (IOException e) {
                return false;
            }
            return true;
        } else {
            return desFile.exists();
        }
    }

    public boolean saveFile(File inFile, String outFileName) {
        //Files.move() 就行了。。。
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        this.setResultMsg(outFileName);
        if (null == inFile) {
            this.setResultMsg("输入文件为空");
            return false;
        }
        if (outFileName.endsWith(File.separator)) {
            this.setResultMsg("目标文件不能是目录");
            return false;
        }
        File outFile = new File(outFileName);
        if (!outFile.getParentFile().exists()) {
            if (!outFile.getParentFile().mkdirs()) {
                this.setResultMsg("创建目标目录失败");
                return false;
            }
        }
        if (!outFile.exists()) {
            try {
                if (!outFile.createNewFile()) {
                    this.setResultMsg("创建目标文件失败");
                    return false;
                }
            } catch (IOException e) {
                this.setResultMsg("创建目标文件失败");
                return false;
            }
        }
        try {
            fileInputStream = new FileInputStream(inFile);
        } catch (FileNotFoundException fe) {
            this.setResultMsg("读取输入文件失败");
            return false;
        }
        try {
            fileOutputStream = new FileOutputStream(outFileName);
        } catch (FileNotFoundException e) {
            this.setResultMsg("输出文件不存在");
            return false;
        }
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = fileInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            this.setResultMsg("文件输入输出失败");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
