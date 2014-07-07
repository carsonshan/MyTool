package pers.chlin.mytool.action.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

/**
 * @author CHLin
 * @version 1.0
 * @since JDK 1.6
 */
public class CloseDialogAction implements ActionListener {

	private JDialog dstDialog;
	
	public CloseDialogAction(JDialog dstDialog)
	{
		this.dstDialog = dstDialog;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (dstDialog != null) {
			dstDialog.dispose();
		}
	}
	
}
