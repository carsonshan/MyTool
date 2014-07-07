package pers.chlin.mytool.model;


import java.io.File;

import javax.swing.JFileChooser;

import pers.chlin.mytool.action.tabs.ChangePanelAction;
import pers.chlin.mytool.main.ObjectManager;
import pers.chlin.mytool.tabs.EditFilePanel;
import pers.chlin.mytool.util.WanShiWu;


/**
 * @author CHLin
 * @version 1.0
 * @since JDK 1.6
 */
public class PanelsModel {
	
	public static void makeFilePanel(String dstFilePath, EditFileType editFileType)
	{
		makeFilePanel(new File(dstFilePath), editFileType);
	}
	
	public static void makeFilePanel(File dstFile, EditFileType editFileType)
	{
		try {
			String contextStr;
			switch (editFileType) {
			case JSON:
				contextStr = WanShiWu.getJsonPrettyStrFromFile(dstFile);
				break;
			case XML:
				contextStr = WanShiWu.getJDom2DocUnmodifiedString(dstFile);
				break;
			case TEXT:
			default:
				contextStr = WanShiWu.getTextContentFromFile(dstFile);
				break;
			}
			
			EditFilePanel editFilePanel = EditFilePanel.makeInstance(dstFile.getName(), 
																	 contextStr, 
																	 dstFile.getAbsolutePath(), 
																	 editFileType);
			ObjectManager.getTabbed().addTab(null, editFilePanel);
			ObjectManager.getTabbed().setTabComponentAt( ObjectManager.getTabbed().getTabCount() -1, editFilePanel.getTitlePanel() );
			ObjectManager.getTabbed().setSelectedComponent(editFilePanel);
			ObjectManager.FileOpening();
			
		} catch (Exception e) {
			WanShiWu.showMsgDialog("發生錯誤", e);
			e.printStackTrace();
		}
	}
	
	public static void editorSave(EditFilePanel editor)
	{
		if (editor.getDataSrc() == null) {
			editorSaveAs(editor);
		} else {
			try {
				WanShiWu.writeContentToFile(editor.getTextArea().getText(), editor.getDataSrc());
				editor.whenContextIsNotChange();
			} catch (Exception e) {
				WanShiWu.showMsgDialog("發生錯誤," +e.getMessage(), e);
			}
		}
	}
	
	public static void editorSaveAs(EditFilePanel editor)
	{
		JFileChooser chooser = new JFileChooser(ObjectManager.getLastOpenDir());

		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int retVal = chooser.showOpenDialog(ObjectManager.getMainFrame());

		if (retVal == JFileChooser.APPROVE_OPTION) {
			editor.setDataSrc(chooser.getSelectedFile().getAbsolutePath());
			editor.getTitlePanel().changeTitle(chooser.getSelectedFile().getName());
			ObjectManager.getMainFrame().setTitle(chooser.getSelectedFile().getAbsolutePath() + "  -  " + ChangePanelAction.AppName);
			editorSave(editor);
			editor.validate();
		}
	}
}
