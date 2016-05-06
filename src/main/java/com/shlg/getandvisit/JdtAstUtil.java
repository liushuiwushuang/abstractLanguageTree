package com.shlg.getandvisit;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class JdtAstUtil {
    /**
    * 简单的工具类，用于将源代码以字符数组形式解析为AST
    * 其中getCompilationUnit()方法的输入参数为需解析的Java源代码文件路径，
    * 返回值为该文件对应的CompilationUnit节点
    * get compilation unit of source code
    * @param javaFilePath 
    * @return CompilationUnit
    */
    public static CompilationUnit getCompilationUnit(String javaFilePath){
        byte[] input = null;
		try {
		    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(javaFilePath));
		    input = new byte[bufferedInputStream.available()];
	            bufferedInputStream.read(input);
	            bufferedInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

        
		ASTParser astParser = ASTParser.newParser(AST.JLS8);
		
		//方法setSource()针对不同形式的源代码作为参数而进行了重载，主要分类为字符数组形式（char[]）和JavaModel形式（ICompilationUnit、IClassFile等）
		//这里采用字符数组形式
        astParser.setSource(new String(input).toCharArray());
        
        //设置 所需解析的代码的类型： 这里K_COMPILATION_UNIT 表示编译单元，即一个Java文件
        astParser.setKind(ASTParser.K_COMPILATION_UNIT);

        //createAST()方法的参数类型为IProgressMonitor，用于对AST的转换进行监控，不需要的话就填个null即可。
        CompilationUnit result = (CompilationUnit) (astParser.createAST(null)); 
        
        return result;
    }
}
