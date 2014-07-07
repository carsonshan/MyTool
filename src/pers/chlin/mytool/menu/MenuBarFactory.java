package pers.chlin.mytool.menu;


import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import pers.chlin.mytool.main.ObjectManager;
import pers.chlin.mytool.model.EditFileType;
import pers.chlin.mytool.util.WanShiWu;



/**
 * @author hsien
 */
public class MenuBarFactory{
	
	private static JMenuBar menuBar;
	
	public static JMenuBar makeMenuBar() throws Exception{
		
		menuBar = new JMenuBar();
		{//TODO
			JMenu mainMenu = makeMenu("檔案");
			
			JMenu openMenu = makeMenu("開啟");
			{
				LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
				params.put("java.lang.Integer", JFileChooser.FILES_ONLY);
				params.put("pers.chlin.mytool.model.EditFileType", EditFileType.JSON);
				openMenu.add(makeMenuItem("開啟Json檔案", "pers.chlin.mytool.action.menuaction.OpenFileAction", "makeInstance", params));
				params.put("pers.chlin.mytool.model.EditFileType", EditFileType.XML);
				openMenu.add(makeMenuItem("開啟Xml檔案", "pers.chlin.mytool.action.menuaction.OpenFileAction", "makeInstance", params));
				params.put("pers.chlin.mytool.model.EditFileType", EditFileType.TEXT);
				openMenu.add(makeMenuItem("開啟純文字檔案", "pers.chlin.mytool.action.menuaction.OpenFileAction", "makeInstance", params));
			}
			mainMenu.add(openMenu);
			
			mainMenu.add(new JSeparator());
			mainMenu.add(makeMenuItem("SSH 小幫手", "pers.chlin.mytool.action.menuaction.Aboutme"));
			mainMenu.add(new JSeparator());
			JMenuItem saveMi = makeMenuItem("儲存", "pers.chlin.mytool.action.menuaction.SaveFileAction");
			
			JMenuItem saveAsMi = makeMenuItem("另存新檔", "pers.chlin.mytool.action.menuaction.SaveAsFileAction");
			
			ObjectManager.addFileOpeningMenuItems(saveMi);
			ObjectManager.addFileOpeningMenuItems(saveAsMi);
			mainMenu.add(saveMi);
			mainMenu.add(saveAsMi);
			menuBar.add(mainMenu);
		}
		{// TODO
			JMenu optionMenu = makeMenu("選項");
			{
				JMenu encoding = makeMenu("I/O編碼");
				HashMap<String, Object> params = new HashMap<String, Object>();
				params.put("java.lang.String", "UTF-8");
				JCheckBoxMenuItem first = makeCheckMenuItem("UTF-8","pers.chlin.mytool.action.menuaction.ChangeEncodingAction" , "makeInstance", params);

				params.put("java.lang.String", "BIG5");
				JCheckBoxMenuItem sencod = makeCheckMenuItem("BIG5","pers.chlin.mytool.action.menuaction.ChangeEncodingAction" , "makeInstance", params);

				ObjectManager.addEncodingCheckMenu(first);
				ObjectManager.addEncodingCheckMenu(sencod);
				ObjectManager.initEncodingCheckMenuSelected();

				encoding.add(first);
				encoding.add(sencod);
				optionMenu.add(encoding);
			}
			{
				optionMenu.add(new JSeparator());
				
				JMenuItem prettyFormatMi = makeMenuItem("Pretty Format","pers.chlin.mytool.action.menuaction.ChangeToPrettyFormat");
				JMenuItem onelineFormatMi = makeMenuItem("Oneline Format","pers.chlin.mytool.action.menuaction.ChangeToOneLineFormat");
				
				ObjectManager.addFileOpeningMenuItems(prettyFormatMi);
				ObjectManager.addFileOpeningMenuItems(onelineFormatMi);
				
				optionMenu.add(prettyFormatMi);
				optionMenu.add(onelineFormatMi);
				
			}
			
			{
				optionMenu.add(new JSeparator());
				JMenuItem mi = new JMenuItem("這啥小我忘記了");
				optionMenu.add(mi);
			}
			
			menuBar.add(optionMenu);
		}
		{// TODO
			JMenu aboutMenu = makeMenu("Help");
			aboutMenu.add(makeMenuItem("關於", "pers.chlin.mytool.action.menuaction.Aboutme"));
			menuBar.add(aboutMenu);
		}
		ObjectManager.NoFileOpening();
		return menuBar;
	}
	
	public static JMenu makeMenu(String menuName){
		JMenu menu = new JMenu(menuName);
		return menu;
	}
	
	public static JMenuItem makeMenuItem(String menuItemName, String actionClassPath) throws Exception{
		
		JMenuItem menuItem = new JMenuItem(menuItemName);
		menuItem.addActionListener((ActionListener) Class.forName(actionClassPath).newInstance() );
		return menuItem;
	}
	
	public static JMenuItem makeMenuItem(String menuItemName, 
										 String actionClassPath, 
										 String actionClassConstructorName, 
										 HashMap<String, Object> params) throws Exception {
		
		JMenuItem menuItem = new JMenuItem(menuItemName);
		Class<?> dstActionClass = Class.forName(actionClassPath);
		Class<?>[] paramsClass = WanShiWu.getClassListByNamesSet(params.keySet());
		Method dstConstructor = dstActionClass.getMethod(actionClassConstructorName, paramsClass);
		menuItem.addActionListener((ActionListener) dstConstructor.invoke( dstActionClass, WanShiWu.getValuesFromHashMap(params)));
		return menuItem;
	}
	
	
	public static JCheckBoxMenuItem makeCheckMenuItem(String menuItemName, 
											  		  String actionClassPath, 
											  		  String actionClassConstructorName, 
											  		  HashMap<String, Object> params) throws Exception {

		JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem(menuItemName);
		Class<?> dstActionClass = Class.forName(actionClassPath);
		Class<?>[] paramsClass = WanShiWu.getClassListByNamesSet(params.keySet());
		Method dstConstructor = dstActionClass.getMethod(actionClassConstructorName, paramsClass);
		menuItem.addActionListener((ActionListener) dstConstructor.invoke( dstActionClass, WanShiWu.getValuesFromHashMap(params)));
		return menuItem;
	}
	
}
