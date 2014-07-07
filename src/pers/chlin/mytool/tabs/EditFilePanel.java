package pers.chlin.mytool.tabs;


import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import pers.chlin.mytool.action.button.CloseEditJPanelAction;
import pers.chlin.mytool.action.doc.DocListener;
import pers.chlin.mytool.model.EditFileType;



/**
 * @author CHLin
 * @version 1.0
 * @since JDK 1.6
 */
public class EditFilePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	final public static String ContextChangedPrefix = "* "; 
	
	private boolean isContextChange = false;
	
	private TitleJPanel titlePanel;
	
	private JTextArea textArea;
	
	private String dataSrc;
	
	private EditFileType editFileType = EditFileType.TEXT;
	
	public static EditFilePanel makeInstance(String title)
	{
		return makeInstance(null);
	}
	
	public static EditFilePanel makeInstance(String title, String content)
	{
		return new EditFilePanel(title, content);
	}
	
	public static EditFilePanel makeInstance(String title, String content, String dataSrc)
	{
		return new EditFilePanel(title, content, dataSrc);
	}
	
	public static EditFilePanel makeInstance(String title, String content, String dataSrc, EditFileType fileType)
	{
		return new EditFilePanel(title, content, dataSrc, fileType);
	}
	
	private EditFilePanel(String title, String content){
		this(title, content, null);
	}
	
	private EditFilePanel(String title, String content, String dataSrc){
		this(title, content, dataSrc, null);
	}
	
	private EditFilePanel(String title, String content, String dataSrc, EditFileType fileType)
	{
		if (fileType != null) {
			this.editFileType = fileType;
		}
		
		titlePanel = TitleJPanel.makeInstance(title, CloseEditJPanelAction.makeInstance(this));
		this.dataSrc = dataSrc;
		this.setLayout(new BorderLayout());
		textArea = new JTextArea();
		textArea.setText(content == null ? "" : content);
		textArea.setFont(new Font("標楷體", Font.BOLD, 16));
		textArea.getDocument().addDocumentListener(new DocListener(this));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(textArea);
		this.add(scrollPane);
	}
	
	public String getDataSrc() 
	{
		return dataSrc;
	}

	public void setDataSrc(String dataSrc) 
	{
		this.dataSrc = dataSrc;
	}
	
	public JTextArea getTextArea(){
		return this.textArea;
	}

	public TitleJPanel getTitlePanel() {
		return titlePanel;
	}

	public boolean isContextChange() {
		return isContextChange;
	}

	public void setContextChange(boolean isContextChange) {
		this.isContextChange = isContextChange;
	}
	
	public void whenContextChange(){
		this.titlePanel.changeTitle(ContextChangedPrefix + this.titlePanel.getTitle());
		this.titlePanel.validate();
		this.isContextChange = true;
	}
	
	public void whenContextIsNotChange(){
		if (this.titlePanel.getTitle().startsWith(ContextChangedPrefix)) {
			this.titlePanel.changeTitle( this.titlePanel.getTitle().replaceFirst("\\" + ContextChangedPrefix, "") );
			this.titlePanel.validate();
		}
		this.isContextChange = false;
		
	}

	public EditFileType getEditFileType() {
		return editFileType;
	}

	public void setEditFileType(EditFileType editFileType) {
		this.editFileType = editFileType;
	}

}
