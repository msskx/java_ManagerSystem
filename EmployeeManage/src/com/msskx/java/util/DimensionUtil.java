package com.msskx.java.util;


import javax.swing.*;
import java.awt.*;

/**
 * 获取默认全屏大小
 * 保证主界面不会覆盖电脑状态栏
 */
public class DimensionUtil {
    public static Rectangle getBounds(){
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        Insets screenInsets=Toolkit.getDefaultToolkit().getScreenInsets(new JFrame().getGraphicsConfiguration());
        Rectangle rectangle=new Rectangle(screenInsets.left, screenInsets.top,
                screenSize.width-screenInsets.left-screenInsets.right,
                screenSize.height-screenInsets.top-screenInsets.bottom);
        return rectangle;
    }
}
