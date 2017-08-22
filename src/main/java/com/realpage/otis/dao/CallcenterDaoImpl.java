package  com.realpage.otis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CallcenterDaoImpl implements CallcenterDao{

	@Autowired
	private JdbcTemplate mpfuiJdbcTemplate;
	
	@Override
	public String getCallcenterData(String roleName) {
		return mpfuiJdbcTemplate.queryForObject("select * from CallsByUserRole( ? )", new Object[]{roleName}, String.class);
	}
	
}
