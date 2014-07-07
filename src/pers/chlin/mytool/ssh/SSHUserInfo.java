package pers.chlin.mytool.ssh;

import com.jcraft.jsch.UserInfo;

/**
 * @author CHLin
 * @version 1.0
 * @since JDK 1.6
 */
public class SSHUserInfo implements UserInfo {

	private String passwd;
	
	public SSHUserInfo(String passwd)
	{
		this.passwd = passwd;
	}
	
	@Override
	public String getPassphrase() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.passwd;
	}

	@Override
	public boolean promptPassphrase(String arg0) {
		return false;
	}

	@Override
	public boolean promptPassword(String arg0) {
		return true;
	}

	@Override
	public boolean promptYesNo(String arg0) {
		return true;
	}

	@Override
	public void showMessage(String msg ) {
		System.out.println( msg );
	}

}
