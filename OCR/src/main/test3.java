//识别整个文件中的图片
package main;

import java.io.BufferedReader;  

import java.io.File;  
import java.io.FileInputStream;  
import java.io.InputStreamReader;  
import java.util.ArrayList;  
import java.util.List;
import java.util.Map;

import org.jdesktop.swingx.util.OS;  
  
public class test3  
{  
    private final String LANG_OPTION = "-l";  
    private final String EOL = System.getProperty("line.separator");  
    /** 
     * 文件位置我放置在，项目同一路径 
     */  
    private String tessPath = new File("E:/Tesseract-OCR").getAbsolutePath();  
  
    /** 
     * @param imageFile 
     *            传入的图像文件 
     * @param imageFormat 
     *            传入的图像格式 
     * @return 识别后的字符串 
     */  
    public String recognizeText(File imageFile) throws Exception  
    {  
        /** 
         * 设置输出文件的保存的文件目录 
         */  
        File outputFile = new File(imageFile.getParentFile(), "output");  
  
        StringBuffer strB = new StringBuffer();  
        List<String> cmd = new ArrayList<String>();  
        if (OS.isWindowsXP())  
        {  
            cmd.add(tessPath + "\\tesseract");  
        } else if (OS.isLinux())  
        {  
            cmd.add("tesseract");  
        } else  
        {  
            cmd.add(tessPath + "\\tesseract");  
        }  
        cmd.add("");  
        cmd.add(outputFile.getName());  
        cmd.add(LANG_OPTION);  
        cmd.add("chi_sim");  
        cmd.add("eng");  
  
       
        
        ProcessBuilder pb = new ProcessBuilder();  
        /** 
         *Sets this process builder's working directory. 
         */  
        
        Map<String, String> env = pb.environment(); //获得进程的环境
        env.put("TESSDATA_PREFIX", "E:\\Tesseract-OCR\\tessdata"); 
        
        
        pb.directory(imageFile.getParentFile());  
        cmd.set(1, imageFile.getName());  
        pb.command(cmd);  
        pb.redirectErrorStream(true);  
        Process process = pb.start();  
        // tesseract.exe 1.jpg 1 -l chi_sim  
        // Runtime.getRuntime().exec("tesseract.exe 1.jpg 1 -l chi_sim");  
        /** 
         * the exit value of the process. By convention, 0 indicates normal 
         * termination. 
         */  
//      System.out.println(cmd.toString());  
        int w = process.waitFor();  
        if (w == 0)// 0代表正常退出  
        {  
            BufferedReader in = new BufferedReader(new InputStreamReader(  
                    new FileInputStream(outputFile.getAbsolutePath() + ".txt"),  
                    "UTF-8"));  
            String str;  
  
            while ((str = in.readLine()) != null)  
            {  
                strB.append(str).append(EOL);  
            }  
            in.close();  
        } else  
        {  
            String msg;  
            switch (w)  
            {  
            case 1:  
                msg = "Errors accessing files. There may be spaces in your image's filename.";  
                break;  
            case 29:  
                msg = "Cannot recognize the image or its selected region.";  
                break;  
            case 31:  
                msg = "Unsupported image format.";  
                break;  
            default:  
                msg = "Errors occurred.";  
            }  
            throw new RuntimeException(msg);  
        }  
        new File(outputFile.getAbsolutePath() + ".txt").delete();  
        return strB.toString().replaceAll("\\s*", "");  
    }  
}  