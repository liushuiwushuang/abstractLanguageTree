package bs.nina.controllers;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bs.nina.dao.DBtoDrools;
import bs.nina.entities.User;
import bs.nina.service.LoginService;
import util.JDBC;

@Controller
@RequestMapping("/asUser")
public class UserController {
		@Test
		@RequestMapping("/registration")
		public String registration(HttpServletRequest request) {
			KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
			KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
					.newKnowledgeBuilder();
				File file = new File("/home/liushui/Documents/workspace-sts/bs/src/main/resources/bs/nina/rules/UserDrools.drl");
				kbuilder.add(ResourceFactory.newFileResource(file),ResourceType.DRL);

			if ( kbuilder.hasErrors() ) {  
				  System.err.println( kbuilder.getErrors().toString() );  
				}   

			Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();
			kbase.addKnowledgePackages(pkgs);
			StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
			User user = new User();
			LoginService loginService = new LoginService();
			loginService.checkLogin(user,ksession, request);
			request.setAttribute("registPerson", user.getMessage());

			return "/login";

		}
		
		@RequestMapping("/login")
		public String login(HttpServletRequest request){
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			JDBC dbd = new JDBC();
			ResultSet rs =dbd.login(userName, password);
			try {
				if(rs.next()){
					HttpSession session1 = request.getSession();
					HttpSession session2 = request.getSession();
					int userID = rs.getInt("user_id");
					session1.setAttribute("userID", userID);
					session2.setAttribute("userName", userName);
					request.setAttribute("message", "欢迎" + userName + "登录本系统！");
					return "redirect:/jsp/writeRule.jsp";
//					return "redirect:/jsp/NewResults.jsp";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "/login";
		}
		
		
		@RequestMapping("/manager")
		public String manager(HttpServletRequest request){	
			DBtoDrools dbd = new DBtoDrools();
			int developerID = (Integer) request.getSession().getAttribute(
					"userID");
			String userName = request.getParameter("userName_modify");
			String password = request.getParameter("password_modify");
			String passwordAgain = request.getParameter("passwordagain_modify");
			String email = request.getParameter("email_modify");
//			System.out.println(password + "lala");
			if(!password.equals(passwordAgain) || password == null || passwordAgain == null){
				JOptionPane.showMessageDialog(null, "密码填写错误！");
				return "redirect:/jsp/developerManager.jsp";
			}else{
				dbd.getHttpDeveloperOne(request, userName, password, email);
				JOptionPane.showMessageDialog(null, "修改成功！");
			}
			return "redirect:/jsp/modifyUserInfo.jsp";
		}
		
		//delete rules
		@RequestMapping("/deleteRule")
		public String deleteRule(HttpServletRequest request){
			String fileName = request.getParameter("fileName");
			JDBC jdbc = new JDBC();
			jdbc.deleteFile(fileName);
			return "redirect:/jsp/showRuleContect.jsp";
		}

}
