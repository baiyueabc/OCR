package main;


import java.io.File;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.awt.*;  
import java.awt.event.*;


import javax.swing.*;

  
@SuppressWarnings("serial")  
public class MainWindow extends JFrame {  
    // *********************************************  
    // 第一步声明程序所需控件  
    // *********************************************  
    JFrame frame;  
    // 上边面板的控件  
    JButton btn_add, btn_query_stu, btn_query_cnt;  
    JPanel panel_head, panel_add, panel_query_stu, panel_query_cnt;  
    // 下面第二个面板的控件  
    JLabel label_query_time,label_query_number;  
    JTextField text_time,text_num,text_in;   
    JTextArea text_result;
    JButton btn_Ok,btn_shibie;  
    JScrollPane jsp;
    // 下面第一个面板的控件  
    JButton btn_submit;  
    JLabel label_name, label_number, label_mark;  
    JTextField text_name, text_number, text_mark, text_sta;  
    // 下面第三个面板的控件  
    JLabel label;  
    JTextField text_cnt;  
  
    public MainWindow() {  
        // ********************************  
        // 第二步设置 底层窗口属性  
        // ********************************  
        // 得到当前屏幕的尺寸  
        Toolkit kit = Toolkit.getDefaultToolkit();  
        Dimension screenSize = kit.getScreenSize();  
        int screenWidth = screenSize.width;  
        int screenHeight = screenSize.height;  
        // frame指底层窗口  
        frame = new JFrame("图片文字提取");  
        frame.setBounds(screenWidth / 3, screenHeight / 4, 450, 300); // 设置位置及大小  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.getContentPane().setBackground(Color.lightGray);  
        LayoutManager layoutMgr = null;
		frame.getContentPane().setLayout(new BorderLayout()); // 使用绝对布局  
        frame.setResizable(false); // 设置窗口大小不可变  
  
        
        // 上层面板  
        panel_head = new JPanel();  
        panel_head.setBounds(2, 0, 440, 40);  
        panel_head.setBackground(Color.lightGray);  
        panel_head.setVisible(true);  
        panel_head.setLayout(new GridLayout(1, 3, 2, 0));  
  
        btn_add = new JButton("个人信息");  
        btn_add.setFont(new Font("宋体", 1, 12)); // 设置字体 格式 大小  
        btn_add.setVisible(true);  
        panel_head.add(btn_add);  
        btn_add.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                panel_add.setVisible(true);  
                panel_query_stu.setVisible(false);  
                panel_query_cnt.setVisible(false);  
            }  
        });  
  
        btn_query_stu = new JButton("图片识别");  
        btn_query_stu.setVisible(true);  
        panel_head.add(btn_query_stu);  
        btn_query_stu.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                panel_add.setVisible(false);  
                panel_query_stu.setVisible(true);  
                panel_query_cnt.setVisible(false);  
            }  
        });  
  
        btn_query_cnt = new JButton("文字打印");  
        btn_query_cnt.setVisible(true);  
        panel_head.add(btn_query_cnt);  
        btn_query_cnt.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                panel_add.setVisible(false);  
                panel_query_stu.setVisible(false);  
                panel_query_cnt.setVisible(true);  
 
            }  
        });  
  
        frame.getContentPane().add(panel_head);  
  
        // 下层面板  
  
        Font font = new Font("宋体", 1, 16);  
  
        panel_add = new JPanel();  
        panel_add.setBounds(2, 45, 440, 225);  
        panel_add.setBackground(Color.lightGray);  
        panel_add.setLayout(new GridLayout(4, 2, 20, 20));  
  
        label_number = new JLabel("ID：");  
        label_number.setFont(font);  
        label_number.setHorizontalAlignment(SwingConstants.CENTER);  
        panel_add.add(label_number);  
  
        text_number = new JTextField();  
        text_number.setFont(font);  
        panel_add.add(text_number);  
  
        label_name = new JLabel("姓名：");  
        label_name.setFont(font);  
        label_name.setHorizontalAlignment(SwingConstants.CENTER);  
        panel_add.add(label_name);  
  
        text_name = new JTextField();  
        text_name.setFont(font);  
        panel_add.add(text_name);  
  
        label_mark = new JLabel("密码：");  
        label_mark.setFont(font);  
        label_mark.setHorizontalAlignment(SwingConstants.CENTER);  
        panel_add.add(label_mark);  
  
        text_mark = new JTextField();  
        text_mark.setFont(font);  
        panel_add.add(text_mark);  
  
        btn_submit = new JButton("提交");  
        btn_submit.setFont(font);  
        btn_submit.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                String s = text_mark.getText();  
            }  
        });  
        panel_add.add(btn_submit);  
  
        text_sta = new JTextField();  
        panel_add.add(text_sta);  
  
        panel_add.setVisible(true);  
        frame.getContentPane().add(panel_add);  
  
        // 下层面板  
        panel_query_stu = new JPanel();  
        panel_query_stu.setBounds(2, 45, 440, 225);  
        panel_query_stu.setBackground(Color.lightGray);  
        panel_query_stu.setLayout(layoutMgr);  
  
        label_query_time = new JLabel("识别时间：");  
        label_query_time.setBounds(370, 60, 150, 40);  
        panel_query_stu.add(label_query_time);  
  
        label_query_number = new JLabel("图片个数：");  
        label_query_number.setBounds(370, 130, 150, 40);  
        panel_query_stu.add(label_query_number);  
        
        text_in = new JTextField();  
        text_in.setBounds(160, 10, 180, 40);  
        panel_query_stu.add(text_in);  
  
        
       
        
        text_result = new JTextArea(50,50);  
        text_result.setBounds(10, 60, 350, 160);
        text_result.setLineWrap(true);
        text_result.setWrapStyleWord(true);
        text_result.setText("企业注册号                                                   企业名称");
        JScrollPane jsp=new JScrollPane(text_result);
        jsp.setBounds(350,60,10,100);
        panel_query_stu.add(jsp);
        
        
        panel_query_stu.add(text_result);  
  
        text_time = new JTextField();   
        text_time.setBounds(370, 90, 150, 40);  
         
        panel_query_stu.add(text_time);  
  
        text_num = new JTextField(); 
        text_num.setBounds(370, 160, 150, 40);  
        
        panel_query_stu.add(text_num);  
  
       
        
        btn_shibie = new JButton("选择文件夹：");  
        btn_shibie.setFont(font);  
        btn_shibie.setBounds(10, 10, 140, 40);  
        btn_shibie.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            JFileChooser jfc=new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
            jfc.showDialog(new JLabel(), "选择");
            File file=jfc.getSelectedFile();
            long i=file.listFiles().length;
            String s=String.valueOf(i);
            text_in.setText(file.getAbsolutePath());
            text_num.setText(s);
            /*if(file.isDirectory()){
                System.out.println("文件夹:"+file.getAbsolutePath());
            }else if(file.isFile()){
                System.out.println("文件:"+file.getAbsolutePath());
            }*/
        }
        }
        )
        
        ; 
        panel_query_stu.add(btn_shibie);
        
        btn_Ok = new JButton("识别");  
        btn_Ok.setFont(font);  
        btn_Ok.setBounds(360, 10, 80, 40);  
        btn_Ok.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
            	
            	long star=System.currentTimeMillis();
            	new shibie(text_in.getText(),Integer.parseInt(text_num.getText()));
            	
            	//new shibie(text_in.getText(),40);
            	
            	 String s[][];
                 s=new String[51][51];
                 s[0][0]="企业注册号";
                 s[0][1]="企业名称";
            	
            	try  
                {  
                      
                    File testDataDir = new File("F:\\tupian1");  
                    //System.out.println(testDataDir.listFiles().length); 
                    int j=testDataDir.listFiles().length;
                    String m=String.valueOf(j/2);
                    text_num.setText(m);
                    int i = 0 ; 
                    int p=1;
                    text_result.append("\r\n");
                    for(File file :testDataDir.listFiles())  
                    {  
                        i++ ;  
                        String recognizeText = new test3().recognizeText(file);  
                       
                        System.out.print(recognizeText+"\t"); 
                        text_result.append(recognizeText+"\t");
                        /*text_result.invalidate();
                        text_result.paintImmediately(text_result.getBounds());
                       */ 
                       if(i%2!=0)
                       {
                       	s[p][0]=recognizeText;
                       	
                       }
                       else
                       {
                       	s[p][1]=recognizeText;
                       	p++;
                       }
                       
                       if( i % 2  == 0 )  
                        {  
                            System.out.println();  
                            text_result.append("\r\n");
                            
                        } 
                    }  
                   
                } 
            	catch (Exception n)  
                {  
                    n.printStackTrace();  
                }  
            	 long end=System.currentTimeMillis();
                 
                 
                 text_time.setText((end-star)/1000+"秒");

                boolean t;
        	     t= setExcel(s); 
        	     if(t==true) {
        	    	 System.out.println("保存成功");
        	     }
        	     else 
        	    	 {System.out.println("保存失败");}
            	
                System.out.print("识别结束");
               
                
                
            }

			  
        });  
        panel_query_stu.add(btn_Ok);  
  
        panel_query_stu.setVisible(false);  
        frame.getContentPane().add(panel_query_stu);  
  
        
        
        
        
        
        
        // // 下层面板  
         panel_query_cnt = new JPanel();  
         panel_query_cnt.setBounds(2, 45, 440, 225);  
         panel_query_cnt.setBackground(Color.lightGray);  
         panel_query_cnt.setLayout(layoutMgr);  
           
         label = new JLabel("表格输出打印：");  
         label.setBounds(10, 10, 150, 40);  
         label.setFont(font);  
         panel_query_cnt.add(label);  
           
         text_cnt = new JTextField();   
         text_cnt.setBounds(10, 60, 420, 140);  
         panel_query_cnt.add(text_cnt);  
         text_cnt.setText("表格文件存于E盘目录下，名称为：输出"); 
         
         frame.getContentPane().add(panel_query_cnt);  
         panel_query_cnt.setVisible(false);  
  
        // 需添加完组件后设置可见  
        frame.setVisible(true); // 设置窗口可见  
  
    }  
    
    
    public static boolean setExcel(String[][] x) {
    	boolean t=false;
    	  WritableWorkbook wwb = null;  
    	  jxl.Cell cell = null;  
    	   try {  
                // 创建一个可写入的工作簿（WorkBook）对象,  
                //这里用父类方法createWorkbook创建子类WritableWorkbook让我想起了工厂方法  
                wwb = Workbook.createWorkbook(new File("E:\\输出.xlsx"));  //地址
                  
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


}  
