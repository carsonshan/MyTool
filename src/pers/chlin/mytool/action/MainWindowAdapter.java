package pers.chlin.mytool.action;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author CHLin
 * @version 1.0
 * @since JDK 1.6
 */
public class MainWindowAdapter extends WindowAdapter {
	
	public void windowClosing(WindowEvent evt) {
		// TODO
		System.exit(0);
	}
	
	
}
