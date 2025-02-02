package com.spring.hometownC.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository("memberDAO")
public class MemberDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public ArrayList<MemberDO> getMemberList() {
		System.out.println("getMemberList() --> ");
		
		String sql ="select * from member";
		return (ArrayList<MemberDO>) jdbcTemplate.query(sql, new MemberRowMapper());
	}
	
	public MemberDO getMember(MemberDO mdo) {
		System.out.println("DAO getMember --> ");
		
		String sql = "select * from member where id = ?";
		Object[] args = {mdo.getId()};
		return (MemberDO) jdbcTemplate.queryForObject(sql, args, new MemberRowMapper());
	}
	
	public void memberJoin(MemberDO mdo) {
		System.out.println(" memberJoin() --> ");
		
		String sql = "insert into member (id, username, password, phone, address) values" + "(?, ?, ?, ?, ?)";
		Object[] args = {mdo.getId(), mdo.getUsername(), mdo.getPassword(), mdo.getPhone(), mdo.getAddress()};
		jdbcTemplate.update(sql, args);
	}
	
	public void editMember(MemberDO mdo) {
		System.out.println("DAO editMember() --> ");
		
		String sql = "update member set username=?, password=?,  phone=? , address=? where id=?";
		Object[] args = { mdo.getUsername(), mdo.getPassword(), mdo.getPhone(), mdo.getAddress(), mdo.getId() };
		jdbcTemplate.update(sql, args);
	}
}

class MemberRowMapper implements RowMapper<MemberDO> {

	@Override
	public MemberDO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("[spring jdbc] mapRow() --> ");
		
		MemberDO member = new MemberDO();
		member.setId(rs.getString(1));
		member.setUsername(rs.getString(2));
		member.setPassword(rs.getString(3));
		member.setPhone(rs.getString(4));
		member.setAddress(rs.getString(5));
		
		return member;
	}
	
}