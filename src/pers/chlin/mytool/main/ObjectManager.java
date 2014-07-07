package pers.chlin.mytool.main;


import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import pers.chlin.mytool.tabs.Tabbed;


/**
 * @author CHLin
 * @version 1.0
 * @since JDK 1.6
 */
public class ObjectManager {
	
	private static String FileEncoding = "UTF-8";
	
	private static String LastOpenDir = "D:/";
	
	private static JFrame MainFrame;
	
	private static Tabbed tabbed;
	
	private static Dimension textAreaSize = new Dimension(850, 600);//TODO
	
	private static ArrayList<JCheckBoxMenuItem> encodingCheckMenus;
	
	private static Boolean hasFileOpening;
	
	private static ArrayList<JMenuItem> fileOpeningMenuItem;
	
	private static String locationPath;
	
	public static void setLastOpenDir(String lastOpenDir){
		LastOpenDir = lastOpenDir;
	}
	
	public static String getLastOpenDir(){
		return LastOpenDir;
	}
	
	public static void setMainFrame(JFrame _main){
		MainFrame = _main;
	}
	
	public static JFrame getMainFrame(){
		return MainFrame;
	}

	public static Tabbed getTabbed() {
		return tabbed;
	}

	public static void setTabbed(Tabbed tabbed) {
		ObjectManager.tabbed = tabbed;
	}

	public static Dimension getTextAreaSize() {
		return textAreaSize;
	}

	public static void setTextAreaSize(Dimension testAreaSize) {
		ObjectManager.textAreaSize = testAreaSize;
	}

	public static String getFileEncoding() {
		return FileEncoding;
	}

	public static void setFileEncoding(String fileEncoding) {
		FileEncoding = fileEncoding;
	}

	public static ArrayList<JCheckBoxMenuItem> getEncodingCheckMenus() {
		return encodingCheckMenus;
	}

	public static void setEncodingCheckMenus(ArrayList<JCheckBoxMenuItem> encodingCheckMenus) 
	{
		ObjectManager.encodingCheckMenus = encodingCheckMenus;
	}
	
	public static void addEncodingCheckMenu(JCheckBoxMenuItem checkMenu)
	{
		if (encodingCheckMenus == null) {
			encodingCheckMenus = new ArrayList<JCheckBoxMenuItem>();
		}
		encodingCheckMenus.add(checkMenu);
	}
	
	public static void initEncodingCheckMenuSelected()
	{
		if (encodingCheckMenus != null) {
			for (JCheckBoxMenuItem checkMenu : encodingCheckMenus) {
				checkMenu.setSelected(checkMenu.getText().equals(FileEncoding));
			}
		}
	}
	
	
	public static void NoFileOpening()
	{
		if (fileOpeningMenuItem != null && (hasFileOpening == null || hasFileOpening)) {
			for (JMenuItem mi : fileOpeningMenuItem) {
				mi.setEnabled(false);
			}
			hasFileOpening = false;
		}
	}
	
	public static void FileOpening()
	{
		if (fileOpeningMenuItem != null && hasFileOpening != null && !hasFileOpening) {
			for (JMenuItem mi : fileOpeningMenuItem) {
				mi.setEnabled(true);
			}
			hasFileOpening = true;
		}
	}
	
	public static void addFileOpeningMenuItems(JMenuItem mi)
	{
		if (fileOpeningMenuItem == null) {
			fileOpeningMenuItem = new ArrayList<JMenuItem>();
		}
		fileOpeningMenuItem.add(mi);
	}

	public static boolean isHasFileOpening() 
	{
		return hasFileOpening;
	}
	
	public static String getLocationPath() 
	{
		return locationPath;
	}

	public static void setLocationPath(String locationPath) 
	{
		ObjectManager.locationPath = locationPath;
	}
}
