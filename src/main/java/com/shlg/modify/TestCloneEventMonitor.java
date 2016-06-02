package bs.nina.monitor;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import util.FileUtil;
import util.JDBC;
import bs.nina.entities.CloneClass;
import bs.nina.entities.CloneInstance;
import bs.nina.entities.Commit;
import bs.nina.entities.Developer;
import bs.nina.entities.Repository;
import bs.nina.init.LoadFileContext;

@Controller
@RequestMapping("/cloneEventMonitor")
public class TestCloneEventMonitor {
	CloneEventsMonitor monitor = new CloneEventsMonitor();

	@Test
	@RequestMapping("/test")
	public String test(HttpServletRequest request) {
		int userID = (Integer) request.getSession().getAttribute("userID");
		String rule = request.getParameter("drl");
		System.out.println(rule);
		monitor.addRules(request, userID, rule);

		return "redirect:/jsp/writeRule.jsp";
	}
	
	@RequestMapping("/save")
	public String save(HttpServletRequest request){
		JDBC jdbc = new JDBC();
		int userID = (Integer) request.getSession().getAttribute("userID");
		String fileName = (String) request.getParameter("fileName");
		System.out.println(fileName);
		String filePath = (String) request.getParameter("filePath");
		System.out.println(filePath);
		String rule = request.getParameter("drlsave");
		FileUtil.writeToFile(filePath, fileName+".txt",
				rule);
		RuleManager ruleManager = new RuleManager();
		int randomRuleName=(int)(Math.random()*100000);
		String composedDrlFileContent = ruleManager.header() + " rule " + " \"" + randomRuleName
				+ "\"  " + " when " + rule + " then " + ruleManager.actions(userID) + " end";
		FileUtil.writeToFile(filePath, fileName +".drl",
				composedDrlFileContent);
		jdbc.modifyOne(userID);
		return "redirect:/jsp/showRuleContect.jsp";
	}
	    
	@RequestMapping("/tryNow")
	public String tryNow(HttpServletRequest request){
		LoadFileContext loadFileContext = new LoadFileContext();
		try {
			loadFileContext.readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int userID = (Integer) request.getSession().getAttribute("userID");
		JDBC jdbc = new JDBC();
		jdbc.deleteResult();  
		timeEvent(userID);
		return "redirect:/jsp/showResults.jsp";
	}
	

	public void timeEvent(int userID) {
		JDBC jdbc = new JDBC();
		ResultSet rs = jdbc.getAllCloneInstance();
		try {
			while (rs.next()) {
				long developerID = rs.getLong("developer_id");
				String developerName = rs.getString("developer_name");
				long repositoryID = rs.getLong("repository_id");
				String url = rs.getString("url");
				long commitID = rs.getLong("commit_id");
				long commitDateL = rs.getLong("commit_date");
				String version = rs.getString("version");
				long cloneClassID = rs.getInt("clone_class_id");
				String cloneClassName = rs.getString("clone_class_name");
				long cloneinstanceID = rs.getInt("clone_instance_id");
				long instanceCount = rs.getLong("instance_count");
				

				Developer developer = new Developer();
				Repository repo = new Repository();
				Commit commit = new Commit();

				CloneClass cloneClass = new CloneClass();
				CloneInstance cloneInstance = new CloneInstance();

				developer.setDeveloperId(developerID);
				developer.setDeveloperName(developerName);
				repo.setRepositoryId(repositoryID);
				repo.setURL(url);
				commit.setCommitId(commitID);
				commit.setCommitDate(commitDateL);
				commit.setVersion(version);
				cloneClass.setCloneClassId(cloneClassID);
				cloneClass.setCloneClassName(cloneClassName);
				cloneClass.setInstanceCount(instanceCount);
				cloneInstance.setInstanceId(cloneinstanceID);

				CloneEvent event = new CloneEvent();
				event.setDeveloper(developer);
				event.setRepository(repo);
				event.setCommit(commit);
//				event.setMethod(method);
//				event.setClazz(clazz);
				event.setCloneClass(cloneClass);
				event.setCloneInstance(cloneInstance);
				
//				System.out.println(event.getDeveloper().getDeveloperName());

				monitor.addEvent(event);
			}
//			String[] fileNames = jdbc.getAllFileName();
			String[] fileNames = jdbc.getAllFileName(userID);
			monitor.check(fileNames);
		} catch (SQLException e) {  
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
