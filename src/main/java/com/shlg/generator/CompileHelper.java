package com.shlg.generator;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import org.eclipse.jdt.core.dom.CompilationUnit;

import com.shlg.generator.CompileHelper.StringObject;

public class CompileHelper {
	
	public static Boolean compileOneClassSimple(CompilationUnit cu) throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        StringObject so = new StringObject(
                String.format("%s.%s", cu.getPackage().getName(), ((List<StringObject>) cu.getTypeRoot()).get(0).getName()), cu.toString());
        Iterable<String> options = Arrays.asList("-d", "target/classes");
        Iterable<? extends JavaFileObject> files = Arrays.asList(so);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, options, null, files);
        return task.call();
    }
 
    static class StringObject extends SimpleJavaFileObject {
 
        private String contents = null;
 
        protected StringObject(String className, String contents) throws Exception {
            super(URI.create("string:///" + className.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.contents = contents;
        }
 
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return contents;
        }
    }
}
