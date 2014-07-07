package pers.chlin.mytool.action.menuaction;


import java.awt.Component;
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
public class SaveFileAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		identifyDstComponent(ObjectManager.getTabbed().getSelectedComponent());
	}
	
	private void identifyDstComponent(Component component)
	{
		if (component == null) {
			WanShiWu.showMsgDialog("儲存失敗", "component 為null");
		} else if (component instanceof EditFilePanel) {
			if (((EditFilePanel) component).isContextChange()) {
				PanelsModel.editorSave((EditFilePanel) component);
			}
		}
	}

}
