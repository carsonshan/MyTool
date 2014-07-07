package pers.chlin.mytool.action.menuaction;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pers.chlin.mytool.util.WanShiWu;


/**
 * @author CHLin
 * @version 1.0
 * @since JDK 1.6
 */
public class Aboutme implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		WanShiWu.showMsgDialog("About My tool", "Author : Che-Hsien Lin\nEMail : sabreurqq@gmail.com");
	}

}
