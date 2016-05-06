package com.shlg.generator;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class HelloWorld {

	public static void main(String[] args) {

		AST ast = AST.newAST(AST.JLS8);

		CompilationUnit compilationUnit = ast.newCompilationUnit();

		// 创建类

		TypeDeclaration programClass = ast.newTypeDeclaration();

		programClass.setName(ast.newSimpleName("HelloWorld"));

		programClass.modifiers().add(

				ast.newModifier(ModifierKeyword.PUBLIC_KEYWORD));

		compilationUnit.types().add(programClass);

		// 创建包

		PackageDeclaration packageDeclaration = ast.newPackageDeclaration();

		packageDeclaration.setName(ast.newName("com.dream"));

		compilationUnit.setPackage(packageDeclaration);

		MethodDeclaration main = ast.newMethodDeclaration();

		main.setName(ast.newSimpleName("main"));

		main.modifiers().add(

				ast.newModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD));

		main.modifiers().add(ast.newModifier(ModifierKeyword.STATIC_KEYWORD));

		main.setReturnType2(ast.newPrimitiveType(PrimitiveType.VOID));

		programClass.bodyDeclarations().add(main);

		Block mainBlock = ast.newBlock();

		main.setBody(mainBlock);

		// 给main方法定义String[]参数

		SingleVariableDeclaration mainParameter = ast

				.newSingleVariableDeclaration();

		mainParameter.setName(ast.newSimpleName("arg"));

		mainParameter.setType(ast.newArrayType(ast.newSimpleType(ast

				.newName("String"))));

		main.parameters().add(mainParameter);

		MethodInvocation println = ast.newMethodInvocation();

		println.setName(ast.newSimpleName("prinln"));

		// 生成String类型的常量

		StringLiteral s = ast.newStringLiteral();

		s.setLiteralValue("Hello World");

		println.arguments().add(s);

		println.setExpression(ast.newName("System.out"));

		mainBlock.statements().add(ast.newExpressionStatement(println));

		System.out.println(compilationUnit.toString());

	}
}
