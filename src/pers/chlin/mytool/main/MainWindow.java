package pers.chlin.mytool.main;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import pers.chlin.mytool.action.MainWindowAdapter;
import pers.chlin.mytool.action.tabs.ChangePanelAction;
import pers.chlin.mytool.frame.MainFrame;
import pers.chlin.mytool.tabs.Tabbed;

/**
 * @author CHLin
 * @version 1.0
 * @since JDK 1.6
 */
public class MainWindow {
	
	public static void main(String[] args)
	{
		try {
			ObjectManager.setMainFrame(MainFrame.makeInstance());
			ObjectManager.getMainFrame().setVisible(true);
			ObjectManager.getMainFrame().addWindowListener(new MainWindowAdapter());
			Tabbed tabbed = Tabbed.makeInstance();
			tabbed.addChangeListener(new ChangePanelAction());
			ObjectManager.setTabbed(tabbed);
			ObjectManager.getMainFrame().setContentPane(ObjectManager.getTabbed());
			
	        ObjectManager.setLocationPath(System.getProperty("user.dir"));
	        
	        InputStream fis = null;
			try {
				Properties prop = new Properties();

				fis = MainWindow.class.getResourceAsStream("/log4j.properties");
				Reader reader = new InputStreamReader(fis, "UTF-8");
				prop.load(reader);
				prop.put("log4j.appender.myLogger.File", System.getProperty("user.dir") + File.separator + "mytool.log");
				PropertyConfigurator.configure(prop);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						// ignore
					}
				}
			}
	        
		} catch (Exception e) {
			e.printStackTrace();
			// TODO 
		}
	}
	
}
