package com.demo.jsf.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "result")
public class Result implements Serializable {
	
    private static final long serialVersionUID = 6235813775394286561L;
    
    protected int _code;
    
    protected String _message;
    
    protected Map<String, Object> _content = new HashMap<String, Object>();
    
    public Result() {
    }
    
    public Result(int code, String message) {
        this(code, message, null);
    }
    
    public Result(int code, String message, Map<String, Object> content) {
        this._code = code;
        this._message = message;
        this._content = content;
    }
    
    public static Result getInstance(int code, String message) {
        return instance(code, message, null);
    }
    
    public static Result instance(int code, String message, Map<String, Object> content) {
        return new Result(code, message, content);
    }
    
    public int getCode() {
        return _code;
    }
    public void setCode(int code) {
        _code = code;
    }
    public String getMessage() {
        return _message;
    }
    public void setMessage(String message) {
        _message = message;
    }
    
    public Map<String, Object> getContent() {
		return _content;
	}

	public void setContent(Map<String, Object> _content) {
		this._content = _content;
	}
	
}