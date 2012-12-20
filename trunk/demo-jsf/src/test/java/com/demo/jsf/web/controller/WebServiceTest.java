package com.demo.jsf.web.controller;

import junit.framework.TestCase;

import org.springframework.web.client.RestTemplate;

public class WebServiceTest extends TestCase {

	//Template for call service API
	//http://static.springsource.org/spring/docs/3.0.x/javadoc-api/org/springframework/web/client/RestTemplate.html
//    private static RestTemplate restTemplate;
    
    @Override
	public void setUp() throws Exception {
//    	
//    	restTemplate = new RestTemplate();
//    	List<HttpMessageConverter<?>> list = new ArrayList<HttpMessageConverter<?>>();
//    	list.add(new StringHttpMessageConverter());
//    	restTemplate.setMessageConverters(list);

	}

    public void testCallAPI() {
    	
//        String url = "http://localhost:8080/anpprototype/api/company/1.json";
//        String result = restTemplate.getForObject(url, String.class);
//        System.out.println(result);
       
    }
    
    public void testPostAPI() {
    	
//		String url = "http://localhost:8080/order-system/api/user/set-password?password=system" +
//		"&retypePassword=system&activeKey=0aee474329bfb75cf9efeb763db54341";
//        String result = restTemplate.postForObject(url, null, String.class);
//        System.out.println(result);
       
    }
    

}