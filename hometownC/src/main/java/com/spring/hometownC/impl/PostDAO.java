package com.spring.hometownC.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("PostDAO")
public class PostDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public ArrayList<PostDO> getPostList() {
		System.out.println("DAO getPostList() --> ");

		String sql = "SELECT @ROWNUM:=@ROWNUM+1 AS rowNum, post.* FROM post , (SELECT @ROWNUM:=0) AS R order by @ROWNUM DESC ";
		return (ArrayList<PostDO>) jdbcTemplate.query(sql, new PostRowMapper());
	}
	
	public ArrayList<PostDO> getPostListCate(int cateSeq) {
		System.out.println("DAO getPostListSeq");
		
		String sql = "SELECT @ROWNUM:=@ROWNUM+1 AS rowNum, post.* FROM post , (SELECT @ROWNUM:=0) AS R where category = ? order by @ROWNUM DESC ";
		Object[] args = { cateSeq};
		return (ArrayList<PostDO>) jdbcTemplate.query(sql, args, new PostRowMapper());
	}

	public void insertPost(PostDO pdo, MemberDO mdo) {
		System.out.println("DAO insertPost() --> ");

		String sql = "insert into post (category, title, writer, content, liked) values" + "(?, ?, ?, ?, ?)";
		Object[] args = { pdo.getCategory(), pdo.getTitle(), mdo.getId(), pdo.getContent(), 0 };
		jdbcTemplate.update(sql, args);
	}

	public PostDO getPost(PostDO pdo) {
		System.out.println("DAO getPost() --> ");

		String sql = "SELECT @ROWNUM:=@ROWNUM+1 AS rowNum, post.* FROM post, (SELECT @ROWNUM:=0) AS R  where seq=? order by @ROWNUM DESC ";
		Object[] args = { pdo.getSeq() };
		return (PostDO) jdbcTemplate.queryForObject(sql, args, new PostRowMapper());
	}

	public void modifyPostProc(PostDO pdo) {
		System.out.println("DAO modifyPostProc() --> ");

		String sql = "update post set category=?, title=?, content=? where seq=?";
		Object[] args = { pdo.getCategory(), pdo.getTitle(), pdo.getContent(), pdo.getSeq() };
		jdbcTemplate.update(sql, args);
	}

	public void deletePost(PostDO pdo) {
		System.out.println("DAO deletePost() --> ");

		String sql = "delete from post where seq = ? ";
		Object[] args = { pdo.getSeq() };
		jdbcTemplate.update(sql, args);
	}

	public void likedPost(PostDO pdo) {
		System.out.println("DAO likedPost() --> ");

		String sql = "update post set liked=? where seq=?";
		int plus = pdo.getLiked() + 1;
		jdbcTemplate.update(sql, plus, pdo.getSeq());
	}

	// �� �˻�
	public ArrayList<PostDO> searchPostList(String searchCon, String searchKey) {
		System.out.println("DAO searchPostList() -->");

		String sql = "";
		if (searchCon.equals("title")) {
			sql = "SELECT @ROWNUM:=@ROWNUM+1 AS rowNum, post.* FROM post, (SELECT @ROWNUM:=0) AS R where title=? order by @ROWNUM DESC ";
		} else if (searchCon.equals("writer")) {
			sql = "SELECT @ROWNUM:=@ROWNUM+1 AS rowNum, post.* FROM post, (SELECT @ROWNUM:=0) AS R where writer=? order by @ROWNUM DESC ";
		} 

		Object[] args = { searchKey };
		return (ArrayList<PostDO>) jdbcTemplate.query(sql, args, new PostRowMapper());

	}
	
	public ArrayList<PostDO> sortByDate() {
		System.out.println("DAO sortByDate --> ");
		
		String sql = "SELECT @ROWNUM:=@ROWNUM+1 AS rowNum, post.* FROM post, (SELECT @ROWNUM:=0) AS R order by regdate ASC ";
		
		return (ArrayList<PostDO>) jdbcTemplate.query(sql, new PostRowMapper());
	}
	
	public ArrayList<PostDO> sortByLiked() {
		System.out.println("DAO sortByLiked --> ");
		
		String sql = "SELECT @ROWNUM:=@ROWNUM+1 AS rowNum, post.* FROM post, (SELECT @ROWNUM:=0) AS R  order by liked desc";		
		
		return (ArrayList<PostDO>) jdbcTemplate.query(sql, new PostRowMapper());
	}
}

class PostRowMapper implements RowMapper<PostDO> {

	@Override
	public PostDO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("Post mapRow() --> ");

		PostDO post = new PostDO();
		post.setRn(rs.getInt(1));
		post.setSeq(rs.getInt(2));
		post.setCategory(rs.getInt(3));
		post.setTitle(rs.getString(4));
		post.setWriter(rs.getString(5));
		post.setContent(rs.getString(6));
		post.setLiked(rs.getInt(7));
		post.setRegdate(rs.getString(8));

		return post;
	}

}
