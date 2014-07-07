package pers.chlin.mytool.action.menuaction;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import org.jdom2.Document;

import pers.chlin.mytool.main.ObjectManager;
import pers.chlin.mytool.tabs.EditFilePanel;
import pers.chlin.mytool.util.WanShiWu;

/**
 * @author CHLin
 * @version 1.0
 * @since JDK 1.6
 */
public class ChangeToPrettyFormat implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		identifyDstComponent(ObjectManager.getTabbed().getSelectedComponent());
	}
	
	private void identifyDstComponent(Component component)
	{
		if (component == null) {
			// do nothing
		} else if (component instanceof EditFilePanel) {
			try {
				EditFilePanel dstPanel = (EditFilePanel) component;
				switch (dstPanel.getEditFileType()) {
				case JSON:
					dstPanel.getTextArea().setText(WanShiWu.jsonStrPrettyFormat(dstPanel.getTextArea().getText()) );
					break;
				case XML:
					Document doc = WanShiWu.getDocFromString(dstPanel.getTextArea().getText());
					dstPanel.getTextArea().setText(WanShiWu.getJDom2PrettyFormatString(doc));
					break;
				}
			} catch (Exception e) {
				WanShiWu.showMsgDialog("轉換失敗," + e.getMessage(), e);
			}
		}
	}

}
