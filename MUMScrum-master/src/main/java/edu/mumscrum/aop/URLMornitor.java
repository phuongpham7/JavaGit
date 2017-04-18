package edu.mumscrum.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.RequestMapping;

import edu.mumscrum.domain.Employee;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class URLMornitor {

	 @Autowired
	   private HttpSession session;
	/**
	 * Check User Permission
	 * @throws Exception 
	 */
	@Before("execution(* edu.mumscrum.web.*.*(..)) && !execution(* edu.mumscrum.web.LoginController.*(..))")
	public void userPermission() throws Exception{
		System.out.println("----Check User Permission----");
		checkSession();
		System.out.println("----Permission Pass----");
	}

	
	
	private void checkSession() throws Exception{
		
		Employee employee= (Employee)session.getAttribute("loggedInUser");
		if(employee==null){
			throw new Exception("Please Login our system!!!");
		}
	}

}
