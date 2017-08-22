package  com.realpage.otis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import  com.realpage.otis.dao.CallcenterDao;

@Service
public class CallcenterServiceImpl implements CallcenterService{

	@Autowired
	private CallcenterDao callcenterDao;
	
	@Override
	@PreAuthorize("hasAuthority('QA')")
	public String getCallcenterData(String roleName) {
		return callcenterDao.getCallcenterData(roleName);
	}
	
}
