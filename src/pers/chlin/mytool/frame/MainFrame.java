package pers.chlin.mytool.frame;


import javax.swing.JFrame;

import pers.chlin.mytool.menu.MenuBarFactory;


/**
 * @author CHLin
 * @version 1.0
 * @since JDK 1.6
 */
public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public static MainFrame makeInstance()
	{
		return new MainFrame();
	}
	
	private MainFrame()
	{
		try {
			this.setTitle("My Tool  Ver 1.0");
			this.setJMenuBar( MenuBarFactory.makeMenuBar() );
			this.setSize(1000,700);
			this.setLocation(150,10); 
			this.setResizable(false);//是否可手動縮放大小
			this.setVisible(true);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
