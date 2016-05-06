package com.shlg.generator;

import org.eclipse.jdt.internal.compiler.ast.Statement;

public class GeneAST {
//	private static Statement createSimpleCompareStatement(String paramName, String paramMemGetterName, String value,
//            Statement trueStatement, Statement falseStatement) {
//        // 生成 {paramName}.{paramMemGetterName}() 方法调用表达式
//        NameExpr paramExpr = new NameExpr(paramName);
//        Expression callCondition1 = new MethodCallExpr(paramExpr, paramMemGetterName);
//        // 生成 {value}.equals( {paramName}.{paramMemGetterName}() ) 表达式
//        Expression strExp = new StringLiteralExpr(value);
//        MethodCallExpr equalCondition1 = new MethodCallExpr(strExp, "equals", Lists.newArrayList(callCondition1));
//        // 将truestatement falsestatement封装为代码块
//        BlockStmt trueblock = new BlockStmt();
//        trueblock.setStmts(Lists.newArrayList(trueStatement));
//        BlockStmt falseblock = new BlockStmt();
//        falseblock.setStmts(Lists.newArrayList(falseStatement));
//        // 返回 if ({value}.equals( {paramName}.{paramMemGetterName}() ) ) {
//        // trueblock; } else { falseblock; } 语句
//        return new IfStmt(equalCondition1, trueblock, falseblock);
//    }
 
    /**
     * 编译一个DataTester的子类并且执行
     * 
     * @throws Exception
     */
//    private static void testCompile() throws Exception {
//        CompilationUnit cu = new CompilationUnit();
//        // 设置包名
//        cu.setPackage(new PackageDeclaration(ASTHelper.createNameExpr("yp.published.grammer")));
// 
//        // 设置类名，DataTesterImpl，并且实现DataTester接口
//        ClassOrInterfaceDeclaration type = new ClassOrInterfaceDeclaration(ModifierSet.PUBLIC, false, "DataTesterImpl");
//        ClassOrInterfaceType interType = new ClassOrInterfaceType("yp.published.grammer.DataTester");
//        type.setImplements(Lists.newArrayList(interType));
//        ASTHelper.addTypeDeclaration(cu, type);
// 
//        // 在实现类里面定义Test方法,实现了接口的Test方法
//        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.BOOLEAN_TYPE, "test");
//        AnnotationExpr anno = new MarkerAnnotationExpr(ASTHelper.createNameExpr("Override"));
//        method.setAnnotations(Lists.newArrayList(anno));
//        // test方法的参数TestData
//        ReferenceType refType = ASTHelper.createReferenceType("yp.published.grammer.TestData", 0);
//        Parameter param = ASTHelper.createParameter(refType, "data");
//        method.setParameters(Lists.newArrayList(param));
//        ASTHelper.addMember(type, method);
// 
//        // 定义了方法题，一个block
//        BlockStmt block = new BlockStmt();
//        method.setBody(block);
// 
//        // 生成类似于 if ("b".equals(data.getCondition2()) { return true; } else {
//        // return false; }的代码
//        Statement stmtCon2 = createSimpleCompareStatement("data", "getCondition2", "b",
//                new ReturnStmt(new BooleanLiteralExpr(true)), new ReturnStmt(new BooleanLiteralExpr(false)));
// 
//        // 生成类似的 if ("a".equals(data.getCondition1)) { stmtCon2 } else { return
//        // false; } 的代码
//        // 其中stmtCon2为上面生成的if else代码
//        Statement stmt = createSimpleCompareStatement("data", "getCondition1", "a", stmtCon2,
//                new ReturnStmt(new BooleanLiteralExpr(false)));
// 
//        // 设置方法体代码块
//        block.setStmts(Lists.newArrayList(stmt));
// 
//        System.out.println(cu.toString());
// 
        //打印结果
//      package yp.published.grammer;
//
//      public class DataTesterImpl implements yp.published.grammer.DataTester {
//
//          @Override
//          public boolean test(yp.published.grammer.TestData data) {
//              if ("a".equals(data.getCondition1())) {
//                  if ("b".equals(data.getCondition2())) {
//                      return true;
//                  } else {
//                      return false;
//                  }
//              } else {
//                  return false;
//              }
//          }
//      }
         
        // 编译上述语法树
//        Boolean compileResult = CompileHelper.compileOneClassSimple(cu);
//        if (compileResult) {
// 
//            // 用编译好的类来执行测试数据
//            Class clazz = Class.forName("yp.published.grammer.DataTesterImpl");
// 
//            TestData td1 = new TestData();
//            td1.setCondition1("a");
//            td1.setCondition2("a");
// 
//            TestData td2 = new TestData();
//            td2.setCondition1("a");
//            td2.setCondition2("b");
// 
//            TestData td3 = new TestData();
//            td3.setCondition1("b");
//            td3.setCondition2("a");
// 
//            TestData td4 = new TestData();
//            td4.setCondition1("b");
//            td4.setCondition2("b");
// 
//            DataTester tester = (DataTester) clazz.newInstance();
//            System.out.println("a-a test:" + tester.test(td1));
//            System.out.println("a-b test:" + tester.test(td2));
//            System.out.println("b-a test:" + tester.test(td3));
//            System.out.println("b-b test:" + tester.test(td4));
//        }
// 
//    }
}
