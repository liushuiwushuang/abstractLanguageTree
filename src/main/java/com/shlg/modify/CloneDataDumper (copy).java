package com.github.chuang;

import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.github.chuang.biz.RepositoriesMonitor;

@SpringBootApplication
@ComponentScan(basePackages={"com.github.chuang"})
public class CloneDataDumper {
	@Autowired
	RepositoriesMonitor repositoriesMonitor;
	public void start(){
//		repositoriesMonitor.addRepo("/tmp/se-platform/.git","gitlocal");
		
//		repositoriesMonitor.addRepo("/home/liushui/T/se-platform/.git","gitlocal");
		repositoriesMonitor.addRepo("/home/liushui/Public/study_resource/cloneVersion2/testgit/.git","gitlocal");
//		repositoriesMonitor.addRepo("/home/gitProject/testgit/.git", "gitlocal");
		Timer time = new Timer();
		time.schedule(repositoriesMonitor, 0,5000);
	}

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(CloneDataDumper.class, args);
		CloneDataDumper app = (CloneDataDumper) context.getBean("cloneDataDumper");
		app.start();
	}

}
