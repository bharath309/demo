package  com.realpage.otis.overview.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OverviewDaoImpl implements OverviewDao {

	@Autowired
	private JdbcTemplate mpfuiJdbcTemplate;

	@Override
	public String getCallCenterOverview() {
		return mpfuiJdbcTemplate.queryForObject("SELECT * from CallCenterOverviewJSON()", String.class);
	}
}
