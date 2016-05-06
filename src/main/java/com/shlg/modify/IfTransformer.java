package com.shlg.modify;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.InfixExpression.Operator;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.StringLiteral;

public class IfTransformer extends ASTVisitor {
	
	@Override
	public boolean visit(IfStatement node) {
		/**
		 * fragment != null
		 */
		
		InfixExpression ie = (InfixExpression)node.getExpression();
		ie.setOperator(Operator.NOT_EQUALS);
		
		/**
		 * if (fragment == null) {
		 *	  System.out.println("Wrong!");
		 * }
		 * else {
		 *    System.out.println("Wrong!");
		 * }
		 */
		node.setElseStatement(
				(Block) ASTNode.copySubtree(node.getAST(),  node.getThenStatement()));

		/**
		 * if (fragment == null) {
		 *	  System.out.println("Done!");
		 * }
		 * else {
		 *    System.out.println("Wrong!");
		 * }
		 */
		AST ast = node.getAST();
		
		MethodInvocation methodInv = ast.newMethodInvocation();

		SimpleName nameSystem = ast.newSimpleName("System");
		SimpleName nameOut = ast.newSimpleName("out");
		SimpleName namePrintln = ast.newSimpleName("println");

		//连接‘System’和‘out’
		//System.out
		QualifiedName nameSystemOut = ast.newQualifiedName(nameSystem, nameOut);

		//连接‘System.out’和‘println’到MethodInvocation节点
		//System.out.println()
		methodInv.setExpression(nameSystemOut);
		methodInv.setName(namePrintln);

		//"Done!"
		StringLiteral sDone = ast.newStringLiteral();
		sDone.setEscapedValue("\"Done!\"");

		//System.out.println("Done!")
		methodInv.arguments().add(sDone);

		//将方法调用节点MethodInvocation连接为表达式语句ExpressionStatement的子节点
		//System.out.println("Done!");
		ExpressionStatement es = ast.newExpressionStatement(methodInv);
		
		//将表达式语句连接为新建语句块节点Block的子节点
		//{
		//System.out.println("Done!");
		//}
		Block block = ast.newBlock();
		block.statements().add(es);
		
		//将语句块节点Block连接为IfStatement节点的子节点
		node.setThenStatement(block);
		
		System.out.println(node.toString());
		return false;
		
	}
}