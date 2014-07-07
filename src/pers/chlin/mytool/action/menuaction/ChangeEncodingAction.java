package pers.chlin.mytool.action.menuaction;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pers.chlin.mytool.main.ObjectManager;


/**
 * @author CHLin
 * @version 1.0
 * @since JDK 1.6
 */
public class ChangeEncodingAction implements ActionListener {

	private String dstEncoding;
	
	public static ChangeEncodingAction makeInstance(String dstEncoding)
	{
		return new ChangeEncodingAction(dstEncoding);
	}
	
	private ChangeEncodingAction(String dstEncoding)
	{
		this.dstEncoding = dstEncoding;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		ObjectManager.setFileEncoding(dstEncoding);
		ObjectManager.initEncodingCheckMenuSelected();
	}

}
