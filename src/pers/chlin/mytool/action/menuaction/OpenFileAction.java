package pers.chlin.mytool.action.menuaction;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import pers.chlin.mytool.main.ObjectManager;
import pers.chlin.mytool.model.EditFileType;
import pers.chlin.mytool.model.PanelsModel;


/**
 * @author CHLin
 * @version 1.0
 * @since JDK 1.6
 */
public class OpenFileAction implements ActionListener {

	private int FileSelectMode = JFileChooser.FILES_ONLY;
	
	private EditFileType editFileType;
	
	public static OpenFileAction makeInstance(Integer fileSelectMode, EditFileType editFileType)
	{
		return new OpenFileAction(fileSelectMode, editFileType);
	}
	
	public OpenFileAction(){}
	
	public OpenFileAction(int fileSelectMode, EditFileType editFileType)
	{
		this.FileSelectMode = fileSelectMode;
		this.editFileType = editFileType;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JFileChooser chooser = new JFileChooser(ObjectManager.getLastOpenDir());
		
		chooser.setFileSelectionMode(this.FileSelectMode);
		
		int retVal = chooser.showOpenDialog(ObjectManager.getMainFrame());
		
		if (retVal == JFileChooser.APPROVE_OPTION) {
			ObjectManager.setLastOpenDir( chooser.getSelectedFile().getParentFile().getAbsolutePath());
			PanelsModel.makeFilePanel(chooser.getSelectedFile(), editFileType);
		}
	}
	
	

}
