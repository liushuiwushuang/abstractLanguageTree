package bs.nina.time;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TimerTask;

import notification.NotificationServer;
import util.JDBC;
import bs.nina.monitor.TestCloneEventMonitor;

public class SendMessageTask extends TimerTask {
	JDBC jdbc = new JDBC();
	ResultSet rs = null;
	ResultSet rsUser = null;
	ResultSet rsFileCount = null;
	long fileNum;
	long fileNumb;
                   
	public void run() {
		jdbc.deleteResult();  
		rsUser = jdbc.getUser();
		long userID = 0;
			try {
				while(rsUser.next()){
					userID = rsUser.getLong("user_id");
					fileNum = jdbc.getFileNum(userID);
					System.out.println(fileNum);
					rsFileCount = jdbc.getFileNumb(userID);
					if(rsFileCount.next()){
					fileNumb = rsFileCount.getLong("file_count");
					System.out.println(fileNumb);
					} 
					rs = jdbc.getCloneinstanceb();
				if (!rs.next() || fileNum != fileNumb || jdbc.modifyRule(userID) != 0) {           
					TestCloneEventMonitor cloneMonitor = new TestCloneEventMonitor();
					cloneMonitor.timeEvent((int) userID);
					jdbc.returnZero((int) userID);			
				} else {     

				}        
}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			finally {
				NotificationServer.getInstance().notificationToEmail();
				jdbc.deleteCloneinstanceb();
				jdbc.setFileNum(); 
				if(rs != null){
					try {
			   			rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block     
					e.printStackTrace();
				} finally {
					if(rsFileCount != null){	
						try {
							rsFileCount.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							if(rsUser != null){
								try {
									rsUser.close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} 
							}
						}
					}
				}
			}
			}
	}

}
