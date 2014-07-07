package pers.chlin.mytool.action.doc;


import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import pers.chlin.mytool.tabs.EditFilePanel;


/**
 * @author CHLin
 * @version 1.0
 * @since JDK 1.6
 */
public class DocListener implements DocumentListener {

	private EditFilePanel editPanel;
	
	public DocListener(EditFilePanel editPanel)
	{
		this.editPanel = editPanel;
	}
	
	@Override
	public void changedUpdate(DocumentEvent arg0)
	{
		whenContextChange();
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) 
	{
		whenContextChange();
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) 
	{
		whenContextChange();
	}
	
	private void whenContextChange()
	{
		if (!editPanel.isContextChange()) {
			editPanel.whenContextChange();
		}
	}

}
