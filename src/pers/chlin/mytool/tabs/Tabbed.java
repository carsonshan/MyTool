package pers.chlin.mytool.tabs;

import javax.swing.JTabbedPane;

/**
 * @author CHLin
 * @version 1.0
 * @since JDK 1.6
 * 
 */
public class Tabbed extends JTabbedPane {

	private static final long serialVersionUID = 1L;
	
	public static Tabbed makeInstance(){
		return new Tabbed();
	}
	
	private Tabbed(){};
	
}
