package pers.chlin.mytool.util;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JOptionPane;


import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import pers.chlin.mytool.main.ObjectManager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author CHLin
 * @version 1.0
 * @since JDK 1.6
 */
public class WanShiWu {
	
	public static Class<?>[] getClassListByNamesSet(Set<String> names) 
	throws ClassNotFoundException
	{
		if (names != null && names.size() > 0) {
			Class<?>[] retCollection = new Class<?>[names.size()];
			Iterator<String> namesIt = names.iterator();
			for (int i = 0; namesIt.hasNext(); i ++) {
				retCollection[i] = Class.forName(namesIt.next());
			}
			return retCollection;
		} else {
			return null;
		}
	}
	
	public static Object[] getValuesFromHashMap(HashMap<String, Object> srcMap)
	{
		if (srcMap != null && srcMap.size() > 0) {
			Object[] values = new Object[srcMap.size()];
			Iterator<String> names = srcMap.keySet().iterator();
			for (int i = 0; names.hasNext(); i++) {
				values[i] = srcMap.get(names.next());
			}
			return values;
		} else {
			return null;
		}
	}
	
	public static boolean checkAllStrMustHasValue(String... strs)
	{
		if (strs == null || strs.length == 0) {
			return false;
		}
		for (String str : strs) {
			if (str == null || str.trim().isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	
	public static boolean isNullOrEmpty(String dstVal)
	{
		return dstVal == null ? true : dstVal.trim().isEmpty();
	}
	
	public static JsonNode convertFileToJsonNode(String dstFilePath) 
	throws IOException
	{
		return convertFileToJsonNode(new File(dstFilePath), ObjectManager.getFileEncoding());
	}
	
	public static JsonNode converFileToJsonNode(String dstFilePath, String encoding) 
	throws IOException
	{
		return convertFileToJsonNode(new File(dstFilePath), encoding);
	}
	
	public static JsonNode convertFileToJsonNode(File dstFile) 
	throws IOException
	{
		return convertFileToJsonNode(dstFile, ObjectManager.getFileEncoding());
	}
	
	public static JsonNode convertFileToJsonNode(File dstFile, String encoding) 
	throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readTree(getTextContentFromFile(dstFile, encoding));
	}
	
	public static String getJsonStrFromFile(String dstFilePath) 
	throws IOException
	{
		return getJsonStrFromFile(dstFilePath, ObjectManager.getFileEncoding());
	}
	
	public static String getJsonStrFromFile(String dstFilePath, String encoding) 
	throws IOException
	{
		return getJsonStrFromFile(new File(dstFilePath), encoding);
	}
	
	public static String getJsonStrFromFile(File dstFile) 
	throws IOException
	{
		return getJsonStrFromFile(dstFile, ObjectManager.getFileEncoding());
	}
	
	public static String getJsonStrFromFile(File dstFile, String encoding) 
	throws IOException
	{
		ObjectMapper om = new ObjectMapper();
		JsonNode jn = convertFileToJsonNode(dstFile, encoding);
		return om.writeValueAsString(jn);
	}
	
	public static String getJsonPrettyStrFromFile(String dstFilePath) 
	throws IOException
	{
		return getJsonPrettyStrFromFile(dstFilePath, ObjectManager.getFileEncoding());
	}
	
	public static String getJsonPrettyStrFromFile(String dstFilePath, String encoding) 
	throws IOException
	{
		return getJsonPrettyStrFromFile(new File(dstFilePath), encoding);
	}
	
	public static String getJsonPrettyStrFromFile(File dstFile) 
	throws IOException
	{
		return getJsonPrettyStrFromFile(dstFile, ObjectManager.getFileEncoding());
	}
	
	public static String getJsonPrettyStrFromFile(File dstFile, String encoding) 
	throws IOException
	{
		ObjectMapper om = new ObjectMapper();
		JsonNode jn = convertFileToJsonNode(dstFile, encoding);
		return om.writer().withDefaultPrettyPrinter().writeValueAsString(jn);
	}
	
	public static void writeContentToFile(String content, String dstFilePath) 
	throws JsonProcessingException, IOException
	{
		writeContentToFile(content, new File(dstFilePath), ObjectManager.getFileEncoding());
	}
	
	public static void writeContentToFile(String content, String dstFilePath, String encoding) 
	throws JsonProcessingException, IOException
	{
		writeContentToFile(content, new File(dstFilePath), encoding);
	}
	
	public static void writeContentToFile(String content, File dstFile) 
	throws JsonProcessingException, IOException
	{
		writeContentToFile(content, dstFile, ObjectManager.getFileEncoding());
	}
	
	public static void writeContentToFile(String content, File dstFile, String encoding) 
	throws JsonProcessingException, IOException
	{
		BufferedWriter writer = null;
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		try {
			fos = new FileOutputStream(dstFile);
			osw = new OutputStreamWriter(fos , encoding);
			writer = new BufferedWriter(osw);
			writer.write(content);
			writer.flush();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
					//ignore TODO
				}
			}
			if (osw != null) {
				try {
					osw.close();
				} catch (Exception e) {
					//ignore; TODO
				}
			}
			
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e) {
					//ignore; TODO
				}
			}
		}
	}
	
	public static String jsonStrPrettyFormat(String jsonStr) 
	throws JsonProcessingException, IOException
	{
		ObjectMapper om = new ObjectMapper();
		JsonNode jn = om.readTree(jsonStr);
		return om.writer().withDefaultPrettyPrinter().writeValueAsString(jn);
	}
	
	public static String jsonStrOnelineFormat(String jsonStr) 
	throws JsonProcessingException, IOException
	{
		ObjectMapper om = new ObjectMapper();
		JsonNode jn = om.readTree(jsonStr);
		return om.writeValueAsString(jn);
	}
	
	public static void showMsgDialog(String title, String msgContent)
	{
		JOptionPane.showMessageDialog(ObjectManager.getMainFrame(), 
									  msgContent,title,
									  JOptionPane.PLAIN_MESSAGE,
									  null//UIManager.getIcon("Tree.collapsedIcon") Icon
									  );
	}
	
	public static void showMsgDialog(String title, Throwable throwable)
	{
		String contextMsg = throwable.getMessage().concat("\n");
		
		for (int i=0; i < 5; i++) {
			contextMsg = contextMsg.concat(throwable.getStackTrace()[i].toString()).concat("\n");
		}
		
		JOptionPane.showMessageDialog(ObjectManager.getMainFrame(), 
									  contextMsg,title,
									  JOptionPane.PLAIN_MESSAGE,
									  null//UIManager.getIcon("Tree.collapsedIcon") Icon 
									  );
	}
	
	public static int showYesOrNoDialog(String title, String content)
	{
		return JOptionPane.showOptionDialog(ObjectManager.getMainFrame(),
											content,
                					  	    title, 
                					  	    JOptionPane.YES_NO_OPTION,
                					  	    JOptionPane.QUESTION_MESSAGE, null, null, null);
	}
	
	public static int showYesNoCancelDialog(String title, String content)
	{
		return JOptionPane.showOptionDialog(ObjectManager.getMainFrame(),
											content,
                					  	    title, 
                					  	    JOptionPane.YES_NO_CANCEL_OPTION,
                					  	    JOptionPane.QUESTION_MESSAGE, null, null, null);
	}
	
	public static URL getIconURI(String iconName)
	{
		return WanShiWu.class.getClassLoader().getResource("Resources/icon/" + iconName);
	}
	
	
	public static Document getDocFromFile(String dstPath) 
	throws JDOMException, IOException
	{
		return getDocFromFile(new File(dstPath));
	}
	
	public static Document getDocFromFile(File file) 
	throws JDOMException, IOException
	{
		SAXBuilder saxBuilder = new SAXBuilder();
		return saxBuilder.build(file);
	}
	
	public static Document getDocFromString(String xmlContent) 
	throws JDOMException, IOException
	{
		SAXBuilder saxBuilder = new SAXBuilder();
		StringReader reader = null;
		try {
			reader = new StringReader(xmlContent);
			return saxBuilder.build(reader);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					// ignore TODO
				}
			}
		}
	}
	
	public static String getJDom2DocUnmodifiedString(String dstPath) 
	throws JDOMException, IOException
	{
		return getJDom2DocUnmodifiedString(getDocFromFile(new File(dstPath)));
	}
	
	public static String getJDom2DocUnmodifiedString(File dstFile) 
	throws JDOMException, IOException
	{
		return getJDom2DocUnmodifiedString(getDocFromFile(dstFile));
	}
	
	public static String getJDom2DocUnmodifiedString(Document doc)
	{
		XMLOutputter outputer = new XMLOutputter(Format.getRawFormat());
		return outputer.outputString(doc);
	}
	
	
	public static String getJDom2DocWhitespaceNormalisedString(String dstPath) 
	throws JDOMException, IOException
	{
		return getJDom2DocWhitespaceNormalisedString(getDocFromFile(new File(dstPath)));
	}
	
	public static String getJDom2DocWhitespaceNormalisedString(File dstFile) 
	throws JDOMException, IOException
	{
		return getJDom2DocWhitespaceNormalisedString(getDocFromFile(dstFile));
	}
	
	public static String getJDom2DocWhitespaceNormalisedString(Document doc)
	{
		XMLOutputter outputer = new XMLOutputter(Format.getCompactFormat());
		return outputer.outputString(doc);
	}
	
	public static String getJDom2PrettyFormatString(String dstPath) 
	throws JDOMException, IOException
	{
		return getJDom2PrettyFormatString(getDocFromFile(new File(dstPath)));
	}
	
	public static String getJDom2PrettyFormatString(File dstFile) 
	throws JDOMException, IOException
	{
		return getJDom2PrettyFormatString(getDocFromFile(dstFile));
	}
	
	public static String getJDom2PrettyFormatString(Document doc)
	{
		XMLOutputter outputer = new XMLOutputter(Format.getPrettyFormat());
		return outputer.outputString(doc);
	}
	
	
	public static String getTextContentFromFile(String dstFilePath) 
	throws IOException
	{
		return getTextContentFromFile(dstFilePath, ObjectManager.getFileEncoding());
	}
	
	public static String getTextContentFromFile(String dstFilePath, String encoding) 
	throws IOException
	{
		return getTextContentFromFile(new File(dstFilePath), encoding);
	}
	
	public static String getTextContentFromFile(File dstFile) 
	throws IOException
	{
		return getTextContentFromFile(dstFile, ObjectManager.getFileEncoding());
	}
	
	public static String getTextContentFromFile(File dstFile, String encoding) 
	throws IOException
	{
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader bufferedReader = null;
		StringBuffer stringBuffer = null;
		try {
    		fis = new FileInputStream(dstFile);
    		isr = new InputStreamReader(fis, encoding);
    		bufferedReader = new BufferedReader(isr);
    		
    		stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
			}
			return stringBuffer.toString();
		} finally {
			
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
					// TODO
					//e.printStackTrace();
				}
			}
			
			if (isr != null) {
				try {
					isr.close();
				} catch (Exception e) {
					// TODO
					e.printStackTrace();
				}
			}
			
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (Exception e) {
					// TODO
					//e.printStackTrace();
				}
			}
			
			if (stringBuffer != null) {
				stringBuffer.setLength(0);
			}
		}
	}
	
}
