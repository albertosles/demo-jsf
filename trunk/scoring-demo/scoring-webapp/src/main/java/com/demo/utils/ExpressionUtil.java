package com.demo.utils;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.MapContext;
import org.apache.log4j.Logger;
	
public class ExpressionUtil {
	
	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(ExpressionUtil.class.getName());
	  
	public static void main(String[] args) {
		
		String jexlExp = "((a > 10) and (b < 12)) and (c==3)";
		Map <String,Object> fieldValuePairs = new HashMap<String, Object>();
		fieldValuePairs.put("a", 11);
		fieldValuePairs.put("b", 11);
//		fieldValuePairs.put("c", 5);
		boolean retval =  ExpressionUtil.expressionCheck(jexlExp, fieldValuePairs);
        System.out.println(retval);
	}
	
	public static boolean expressionCheck(String jexlExp, Map<String, Object> fieldValuePairs) {
		
		// Create or retrieve a JexlEngine
        JexlEngine jexl = new JexlEngine();
        jexl.setLenient(false);
        // Create an expression object
        Expression e = jexl.createExpression( jexlExp );
        // Create a context and add data
        JexlContext jc = new MapContext();
        for(Map.Entry<String, Object> entry : fieldValuePairs.entrySet()) {
        	String field = entry.getKey();
        	Object value = entry.getValue();
        	jc.set(field, value);
        }
        return (Boolean) e.evaluate(jc);
	}
}
