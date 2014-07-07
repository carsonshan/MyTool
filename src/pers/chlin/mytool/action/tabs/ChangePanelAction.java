package pers.chlin.mytool.action.tabs;


import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pers.chlin.mytool.main.ObjectManager;
import pers.chlin.mytool.tabs.EditFilePanel;
import pers.chlin.mytool.tabs.Tabbed;


/**
 * @author CHLin
 * @version 1.0
 * @since JDK 1.6
 */
public class ChangePanelAction implements ChangeListener {

	final public static String AppName = "Tool  Ver 0.1";//TODO 
	
	@Override
	public void stateChanged(ChangeEvent arg0) 
	{
		Object selectTarget = ((Tabbed) arg0.getSource()).getSelectedComponent();
		if (selectTarget == null) {
			ObjectManager.NoFileOpening();
			ObjectManager.getMainFrame().setTitle(AppName);
		} else if (selectTarget instanceof EditFilePanel) {
			String dataSrc = ((EditFilePanel) selectTarget).getDataSrc();
			if (dataSrc == null || dataSrc.isEmpty()) {
				dataSrc = ((EditFilePanel) selectTarget).getTitlePanel().getTitle();
			}
			ObjectManager.getMainFrame().setTitle(dataSrc + "  -  " + AppName);
		}
	}

}
