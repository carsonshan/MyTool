package pers.chlin.mytool.tabs;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pers.chlin.mytool.util.WanShiWu;


/**
 * @author CHLin
 * @version 1.0
 * @since JDK 1.6
 */
public class TitleJPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel nameLabel;
	
	private JButton closeBtn;
	
	public static TitleJPanel makeInstance(String panelName,ActionListener closeAction)
	{
		return new TitleJPanel(panelName, closeAction);
	}
	
	private TitleJPanel(String tabName, ActionListener closeAction)
	{
		ImageIcon closeIcon = new ImageIcon( WanShiWu.getIconURI("close_icon.gif") );
		nameLabel = new JLabel(tabName);
		closeBtn = new JButton(closeIcon);
		this.add(nameLabel, BorderLayout.WEST);
		this.add(closeBtn, BorderLayout.EAST);
		closeBtn.setPreferredSize(new Dimension(closeIcon.getIconWidth(), closeIcon.getIconHeight()));
		closeBtn.addActionListener(closeAction);
		this.setOpaque(false);
	}
	
	public String getTitle(){
		return this.nameLabel.getText();
	}
	
	public void changeTitle(String dstName){
		this.nameLabel.setText(dstName);
	}
	
}
