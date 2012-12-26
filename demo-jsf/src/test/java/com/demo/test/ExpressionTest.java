package com.demo.test;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.MapContext;

public class ExpressionTest {

	public static void main(String[] args) {
		
		// Create or retrieve a JexlEngine
        JexlEngine jexl = new JexlEngine();
        // Create an expression object
        String jexlExp = "a > 10";
        Expression e = jexl.createExpression( jexlExp );
        // Create a context and add data
        JexlContext jc = new MapContext();
        jc.set("a", 11);
        
        // Now evaluate the expression, getting the result
        Object retObj = e.evaluate(jc);
        System.out.println(retObj);
	}
}
