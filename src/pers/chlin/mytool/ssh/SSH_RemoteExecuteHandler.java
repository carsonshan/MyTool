package pers.chlin.mytool.ssh;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * @author CHLin
 * @version 1.0
 * @since JDK 1.6
 */
public class SSH_RemoteExecuteHandler {

	private String command;
	
	private JSch jsch;
	
	private Session session;
	
	private Channel channel;
	
	public SSH_RemoteExecuteHandler(){
		jsch = new JSch();
	}
	
	public void initCommand(String command){
		this.command = command;
	}
	
	public void initCommand(String... commands)
	{
		if (commands != null) {
			for (String tmpCommand : commands) {
				this.command = tmpCommand;
			}
		}
	}
	
	public void initSession(String host, int port, String user, String password) 
	throws JSchException
	{
		session = jsch.getSession(user, host, port);
		SSHUserInfo ui = new SSHUserInfo(password);
		session.setUserInfo(ui);
		session.connect();
	}
	
	
	public void initChannel() 
	throws JSchException
	{
		channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand(command);
	}
	
	public void initChannel(String _command) 
	throws JSchException
	{
		channel = session.openChannel("exec");
		initCommand(_command);
		((ChannelExec) channel).setCommand(command);
	}
	
	public void initChannel(String... _command) 
	throws JSchException
	{
		channel = session.openChannel("exec");
		initCommand(_command);
		((ChannelExec) channel).setCommand(command);
	}
	
	/**
	 * 
	 * @param _inputStream  指定的輸入串流
	 * @param _outputStream 指定的輸出串流
	 * @throws IOException
	 * @throws JSchException
	 */
	public String exec(InputStream _inputStream, OutputStream _outputStream) 
	throws IOException, JSchException
	{
		
		// 設定輸入串流
		// channel.setInputStream(System.in);
		channel.setInputStream(_inputStream);

		// 設定輸出串流
		// channel.setOutputStream(System.out);

		// 錯誤訊息輸出串流
		// FileOutputStream fos=new FileOutputStream("/tmp/stderr");
		// ((ChannelExec)channel).setErrStream(fos);
		((ChannelExec)channel).setErrStream(_outputStream);
		
		InputStream in = channel.getInputStream();
		channel.connect();
		StringBuffer result = null;
		try {
			result = new StringBuffer();
			byte[] tmp = new byte[1024];
			while (true) {

				// 接收回傳的串流
				while (in.available() > 0) {
					int i=in.read(tmp, 0, 1024);
					if (i < 0) {
						break;
					}
					result.append(new String(tmp, 0, i));
				}

				// 偵測command 是否執行完畢
				if (channel.isClosed()) {
					System.out.println("exit-status: "+channel.getExitStatus());
					/*
					 *	ExitStatus 可能為  0 :  success
					 *	       		    1 :  error
					 *                  2 :  fatal error
					 *                  -1 
					 */
					break;
				}

				// command 尚未執行完畢 等候一秒再去接新的串流
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}

			return result.toString();
		} finally {
			if (result != null) {
				result.setLength(0);
			}
		}
	}
	
	public void disconnectChannel()
	{
		if (channel != null) {
			channel.disconnect();
		}
	}
	
	public void disconnectSession()
	{
		if (session != null) {
			session.disconnect();
		}
	}
	
	public static void main(String[] args)
	{
		
		try{
			SSH_RemoteExecuteHandler reH = new SSH_RemoteExecuteHandler();

			reH.initSession("192.168.1.212", 22, "oracle", "oracle");
			
			reH.initChannel("cd shellTest/;","ls shellTest/ -l;");
			System.out.println(reH.exec(null, System.err));
			
			reH.initChannel("df -h;");
			System.out.println(reH.exec(null, System.err));
			
			reH.initChannel("cd shellText/;", "./create.sh;", "ls shellTest/ -l");
			//reH.initChannel("shellTest/delete.sh 1 100;ls shellTest/ -l;");
			System.out.println(reH.exec(null, System.err));
			
			
			//String findCommand = String.format("find /home/oracle/shellTest/logs -name 'hbase-oracle-zookeeper-LinuxFS1.log.%s-%s-[0-9][0-9]' -delete", "2013", "12");
			String findCommand = String.format("find /home/oracle/shellTest/logs -name 'hbase-oracle-zookeeper-LinuxFS1.log.%s-%s-[0-9][0-9]' -delete", "2013", "12");
			reH.initChannel(findCommand);
			System.out.println(reH.exec(null, System.err));
			
			
			reH.initChannel("cd /home/oracle/shellTest", "./create.sh");
			System.out.println(reH.exec(null, System.err));
			
			reH.disconnectChannel();
			reH.disconnectSession();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
