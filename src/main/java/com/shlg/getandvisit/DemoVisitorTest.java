package com.shlg.getandvisit;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class DemoVisitorTest {
	
	public DemoVisitorTest(String path) {
		CompilationUnit comp = JdtAstUtil.getCompilationUnit(path);
		
		DemoVisitor visitor = new DemoVisitor();
		comp.accept(visitor);
	}
	
	public static void main(String args[]) {
		new DemoVisitorTest("src/main/java/com/shlg/getandvisit/ClassDemo.java");
//		new DemoVisitorTest("D:/F/codeCompile/AST_workspace/thirday/src/main/java/com/shlg/getandvisit/ClassDemo.java");
	}
}