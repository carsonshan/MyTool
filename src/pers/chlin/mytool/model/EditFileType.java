package pers.chlin.mytool.model;

/**
 * @author CHLin
 * @version 1.0
 * @since JDK 1.6
 */
public enum EditFileType {
	JSON("json"),
	XML("xml"),
	TEXT("text");
	
	private String code;
	
	private EditFileType(String code)
	{
		this.code = code;
	}
	
	public String getCode()
	{
		return this.code;
	}
	
}
