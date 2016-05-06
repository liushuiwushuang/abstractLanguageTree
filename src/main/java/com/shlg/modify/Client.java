package com.shlg.modify;

import org.eclipse.jdt.core.dom.CompilationUnit;

import com.shlg.getandvisit.JdtAstUtil;

public class Client {

	public static void main(String[] args) {
		
		String path="src/main/java/com/shlg/modify/IfExp.java";
		CompilationUnit comp = JdtAstUtil.getCompilationUnit(path);
		IfTransformer it = new IfTransformer();
		comp.accept(it);
	}

}
