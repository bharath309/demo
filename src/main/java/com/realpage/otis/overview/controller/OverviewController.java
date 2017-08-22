package  com.realpage.otis.overview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import  com.realpage.otis.overview.service.IOverviewService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/overview")
public class OverviewController {

	@Autowired
	private IOverviewService overviewService;

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public String getCallCenterOverview() {
		return overviewService.getCallCenterOverview();
	}
}
