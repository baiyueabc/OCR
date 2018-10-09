package main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

import javax.imageio.ImageIO;

import javafx.scene.control.cell.*;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
public class shibie {
	public shibie(String path,int length) 
    {    
		
		for(int i=1;i<=length;i++)
		{
		
			writeBufferedImage(alphaProcess(initBufferedImage(path+"\\"+i+".png")),"E://tupian//"+i+".jpg");
			cut("E://tupian//"+i+".jpg","F://tupian1//"+i+".1");
			cut1("E://tupian//"+i+".jpg","F://tupian1//"+i+".2");
		
		}
		
		
		
    }  

private static void writeBufferedImage(BufferedImage img,String filePath){
    String format = filePath.substring(filePath.indexOf('.')+1);
    
    try {
        ImageIO.write(img,format,new File(filePath));
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public static boolean setExcel(String[][] x) {
	boolean t=false;
	  WritableWorkbook wwb = null;  
	  jxl.Cell cell = null;  
	   try {  
            // 创建一个可写入的工作簿（WorkBook）对象,  
            //这里用父类方法createWorkbook创建子类WritableWorkbook让我想起了工厂方法  
            wwb = Workbook.createWorkbook(new File("G:\\2.xlsx"));  //地址
              
            // 创建一个可写入的工作表   
            // Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作簿中的位置  
            WritableSheet ws = wwb.createSheet("sheetTest", 0);  
            for(int i=0;i<x.length;i++){   
                	
                   Label labelC = new Label(0,i,x[i][0]);  
                    ws.addCell( labelC);  
                    labelC = new Label(1,i,x[i][1]);  
                    ws.addCell( labelC);  
    	            t=true;
    	        
              }
              
            wwb.write();// 从内从中写入文件中  
            wwb.close();  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            t=false;
        }  
	return t;
}



/**
简单介绍一下思路，首先遍历所有像素，计算一下整张图的灰度的平均值（grayMean），轮廓颜色较深，灰度值比较小，背景颜色较浅，灰度值较大，加权平均之后总的平均值会比背景的灰度值略低，然后再次遍历所有像素，并计算每个像素点的灰度，如果其灰度值比grayMean大，则将其alpha值设为0，即完全透明，否则不做处理。
*/  /**
     * 处理透明度
     */
    public static BufferedImage alphaProcess(BufferedImage bufferedImage) {
        //获取源图像的宽高
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
    //实例化一个同样大小的图片，并将type设为 BufferedImage.TYPE_4BYTE_ABGR，支持alpha通道的rgb图像
        BufferedImage resImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

        double grayMean = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = bufferedImage.getRGB(i,j);
                int r = (0xff&rgb);
                int g = (0xff&(rgb>>8));
                int b = (0xff&(rgb>>16));
                //这是灰度值的计算公式
                grayMean += (r*0.299+g*0.587+b*0.114);
            }
        }
        //计算平均灰度
        grayMean = grayMean/(width*height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = bufferedImage.getRGB(i,j);
                //一个int是32位,java中按abgr的顺序存储，即前8位是alpha，最后8位是r，所以可以通过下面的方式获取到rgb的值
                int r = (0xff&rgb);
                int g = (0xff&(rgb>>8));
                int b = (0xff&(rgb>>16));
                double gray = (r*0.299+g*0.587+b*0.114);
                //如果灰度值大于之前求的平均灰度值，则将其alpha设为0，下面准确写应该是rgb = r + (g << 8) + (b << 16) ＋ （0 << 24）;
                if (gray>grayMean){
                    rgb = r + (g << 8) + (b << 16);
                }
                resImage.setRGB(i,j,rgb);
            }
        }
        //ok，返回的就是将浅色背景设为透明的BufferedImage了，可以用灰度化里提到的方式写成文件
        return resImage;
    }


    private static BufferedImage initBufferedImage(String imagePath) {
        File file = new File(imagePath);
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }


public static void cut(String file, String descDir)     
{     
    try    
    {    
        String dir = null;  
        // 读取源图像     
        BufferedImage bi = ImageIO.read(new File(file));     
        BufferedImage outImg=bi.getSubimage(145, 10, 300, 30);   

                    // 输出为文件    
                    dir = descDir + ".jpg";  
                    File f = new File(dir);  
                    
                    ImageIO.write(outImg, "jpg", f); 
                    //System.out.println(dir);  
                    
    }     
    catch (Exception e)     
    {     
        e.printStackTrace();     
    }     
}


public static void cut1(String file, String descDir)     
{     
    try    
    {    
        String dir = null;  
        // 读取源图像     
        BufferedImage bi = ImageIO.read(new File(file));     
        BufferedImage outImg=bi.getSubimage(130, 40, 900, 40);   

                    // 输出为文件    
                    dir = descDir + ".jpg";  
                    File f = new File(dir);  
                    
                    ImageIO.write(outImg, "jpg", f); 
                    //System.out.println(dir);  
                    
    }     
    catch (Exception e)     
    {     
        e.printStackTrace();     
    }     
}
}