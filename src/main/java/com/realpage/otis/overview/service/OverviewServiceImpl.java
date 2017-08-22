package  com.realpage.otis.overview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  com.realpage.otis.overview.dao.OverviewDao;

@Service
public class OverviewServiceImpl implements IOverviewService{
	
	@Autowired
	private OverviewDao overviewDao;
	@Override
	public String getCallCenterOverview() {
		return overviewDao.getCallCenterOverview();
	}

}
