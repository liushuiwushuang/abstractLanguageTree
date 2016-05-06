package com.shlg.getandvisit;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

/*
 * 实现输出给定Java文件中所声明的类名、方法名和属性名
 */
public class DemoVisitor extends ASTVisitor {

	@Override
	public boolean visit(FieldDeclaration node) {
		for (Object obj: node.fragments()) {
			VariableDeclarationFragment v = (VariableDeclarationFragment)obj;
			System.out.println("Field:\t" + v.getName());
		}
		
		return true;
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		System.out.println("Method:\t" + node.getName());
		return true;
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		System.out.println("Class:\t" + node.getName());
		return true;
	}
}