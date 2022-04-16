package com.ankit.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class StudentDetailsServiceImpl implements UserDetailsService{

	@Autowired
    private StudentRepo studentRepo;
     
    @Override
    public UserDetails loadUserByUsername(String emailId)
            throws UsernameNotFoundException {
        Student student = studentRepo.getStudentByMailId(emailId);
         
        if (student == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
		
		  ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); 
		  HttpSession session = attributes.getRequest().getSession(); 
		  SessionBean sessionBean = new SessionBean(); 
		  sessionBean.setEmailId(student.getEmailId());
		  sessionBean.setSessionId(Integer.parseInt(student.getStudentId().toString()));
		  sessionBean.setUserName(student.getStudentName());
		  sessionBean.setAdmin(student.getEmailId().equalsIgnoreCase("ankit@localmail.com") ? true : false);
		  sessionBean.setUserTypeId(sessionBean.isAdmin() ? 1 : 2);//1 == admin 2 ==
		  session.setAttribute("sessionBean", sessionBean);
		 
        return new StudentDetails(student);
    }
    
}
