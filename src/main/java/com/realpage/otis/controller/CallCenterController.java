package  com.realpage.otis.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import  com.realpage.otis.service.CallcenterService;

@RestController
@CrossOrigin(origins = "*")
public class CallCenterController {
	
	@Autowired
	private CallcenterService callcenterService;
	
	@RequestMapping(value="/callcenter/getAllCalls", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getAllCalls() {
		return callcenterService.getCallcenterData(null);
	}
	
	@RequestMapping(value="/callcenter/{roleName}/getCallsByRole", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getCallsByRole(@PathVariable String roleName) {
		return callcenterService.getCallcenterData(roleName);
	}
	
	@RequestMapping(value="/callcenter/login", method = RequestMethod.GET)
	public ModelAndView getLoginPage(HttpSession session){
		System.out.println("sessionInactive" + session.getMaxInactiveInterval());
		return new ModelAndView("login");
	}
	
	@RequestMapping(value={"/","/callcenter/landing"}, method = RequestMethod.GET)
	public ModelAndView getLandingPage(HttpSession session){
		return new ModelAndView("landing");
	}

}
