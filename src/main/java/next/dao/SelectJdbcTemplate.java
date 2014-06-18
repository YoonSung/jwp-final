package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import next.support.db.ConnectionManager;

public abstract class SelectJdbcTemplate {
	
	Connection connection;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public SelectJdbcTemplate() {
		this.connection = ConnectionManager.getConnection();
	}

	public <Any> Any findById(String query) throws SQLException {
	    pstmt = connection.prepareStatement(query);
	    setValues(pstmt);
	 
	    rs = pstmt.executeQuery();
	    Any any = null;
	    if(rs.next()) {
	      any = mapRow(rs);
	    }
	    
	    release();
	    return any;
	}
	
	@SuppressWarnings("unchecked")
	public <Any> List<Any> findAll(String query) throws SQLException {
		pstmt = connection.prepareStatement(query);
	    setValues(pstmt);
	 
	    rs = pstmt.executeQuery();
	    List<Any> anys = new ArrayList<Any>();
	    
		while (rs.next()) {
			anys.add((Any) mapRow(rs));
		}
	    
	    release();
	    return anys;
	}
	
	void release() throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (connection != null) {
			connection.close();
		}
	}
	
	abstract <Any> Any mapRow(ResultSet rs) throws SQLException; 
	abstract void setValues(PreparedStatement pstmt) throws SQLException;
}
