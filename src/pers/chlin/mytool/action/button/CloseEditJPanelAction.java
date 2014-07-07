package pers.chlin.mytool.action.button;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pers.chlin.mytool.main.ObjectManager;
import pers.chlin.mytool.model.PanelsModel;
import pers.chlin.mytool.tabs.EditFilePanel;
import pers.chlin.mytool.util.WanShiWu;


/**
 * @author CHLin
 * @version 1.0
 * @since JDK 1.6
 */
public class CloseEditJPanelAction implements ActionListener {

	private EditFilePanel dstPanel;
	
	public static CloseEditJPanelAction makeInstance(EditFilePanel dstPanel)
	{
		return new CloseEditJPanelAction(dstPanel);
	}
	
	private CloseEditJPanelAction(EditFilePanel dstPanel)
	{
		this.dstPanel = dstPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if (dstPanel.isContextChange()) {
			ObjectManager.getTabbed().setSelectedComponent(dstPanel);
			int retVal = WanShiWu.showYesNoCancelDialog("儲存", "是否儲存檔案?");
			if (retVal == 2) {// 不儲存
				return;
			} else if (retVal == 0) {
				saveFileAndExist();
			}
		}
		closePanel();
	}
	
	private void closePanel()
	{
		ObjectManager.getTabbed().remove(dstPanel);
		dstPanel = null;
	}
	
	private void saveFileAndExist()
	{
		PanelsModel.editorSave(dstPanel);
	}

}
