package main.java.com.roadjava.util;

import javax.swing.*;
import java.awt.*;

/**
 * 设置主窗体大小
 */
public class DimensionUtil {
    public static Rectangle getBounds(){
        Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
        //保证主界面不会覆盖电脑屏幕任务栏
        Insets screenInsets=Toolkit.getDefaultToolkit().getScreenInsets(new JFrame().getGraphicsConfiguration());
        Rectangle rectangle=new Rectangle(screenInsets.left,screenInsets.top,screenSize.width-screenInsets.left
        -screenInsets.right,screenSize.height-screenInsets.top-screenInsets.bottom);
        return rectangle;
    }
}
