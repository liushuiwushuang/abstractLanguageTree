package com.shlg.generator;

import org.eclipse.jdt.core.dom.CompilationUnit;


public class CompileAST {
	public void compile(CompilationUnit cu) throws Exception {
		Boolean compileResult = CompileHelper.compileOneClassSimple(cu);
		if (compileResult) {

			// 用编译好的类来执行测试数据
			Class clazz = Class.forName("com.shlg.generator.DataTesterImpl");

			TestData td1 = new TestData();
			td1.setCondition1("a");
			td1.setCondition2("a");

			TestData td2 = new TestData();
			td2.setCondition1("a");
			td2.setCondition2("b");

			TestData td3 = new TestData();
			td3.setCondition1("b");
			td3.setCondition2("a");

			TestData td4 = new TestData();
			td4.setCondition1("b");
			td4.setCondition2("b");

			DataTester tester = (DataTester) clazz.newInstance();
			System.out.println("a-a test:" + tester.test(td1));
			System.out.println("a-b test:" + tester.test(td2));
			System.out.println("b-a test:" + tester.test(td3));
			System.out.println("b-b test:" + tester.test(td4));
		}

	}
	
	public static void main(String[] args) {
		CompileAST ca = new CompileAST();
		HelloWorldBuilder bu = new HelloWorldBuilder();
		try {
			ca.compile(bu.build());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

