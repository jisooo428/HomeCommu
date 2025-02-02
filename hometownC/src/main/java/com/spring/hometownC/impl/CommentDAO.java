package com.spring.hometownC.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("CommentDAO")
public class CommentDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insertComment(CommentDO cmdo, PostDO pdo) {
		System.out.println("DAO insertComment() --> ");
		
		System.out.println(pdo.getSeq());
		
		String sql = "insert into comment (writer, comment, postseq) values" + "(?, ?, ?)";
		Object[] args = {cmdo.getWriter(), cmdo.getComment(), pdo.getSeq()};
		jdbcTemplate.update(sql, args);
	}
	
	public void deleteComment(CommentDO cmdo) {
		System.out.println("DAO deleteComment() --> ");
		
		String sql = "delete from comment where seq=?";
		Object[] args = {cmdo.getSeq()};
		jdbcTemplate.update(sql, args);
	}
	
	public ArrayList<CommentDO> getCommentList(PostDO pdo) {
		System.out.println("DAO getCommentList() --> ");
		
		String sql = "select * from comment where postseq=?";
		Object[] args = {pdo.getSeq()};
		return (ArrayList<CommentDO>) jdbcTemplate.query(sql, args, new CommentRowMapper());
		
	}

}

class CommentRowMapper implements RowMapper<CommentDO> {

	@Override
	public CommentDO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("Comment mapRow() --> ");
		
		CommentDO comment = new CommentDO();
		comment.setSeq(rs.getInt(1));
		comment.setWriter(rs.getString(2));
		comment.setComment(rs.getString(3));
		comment.setRegdate(rs.getString(4));
		comment.setPostseq(rs.getInt(5));
		
		return comment;
	}
	
}
