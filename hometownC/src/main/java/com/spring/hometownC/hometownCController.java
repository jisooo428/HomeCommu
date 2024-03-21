package com.spring.hometownC;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.hometownC.impl.CategoryDAO;
import com.spring.hometownC.impl.CategoryDO;
import com.spring.hometownC.impl.CommentDAO;
import com.spring.hometownC.impl.CommentDO;
import com.spring.hometownC.impl.MemberDAO;
import com.spring.hometownC.impl.MemberDO;
import com.spring.hometownC.impl.PostDAO;
import com.spring.hometownC.impl.PostDO;

@Controller
public class hometownCController {

	@Autowired
	PostDAO pdao;
	@Autowired
	CategoryDAO cdao;
	@Autowired
	CommentDAO cmdao;
	@Autowired
	MemberDAO mdao;

	// 게시글 관련 기능

	@RequestMapping("/getPostList.do")
	public String postList(MemberDO mdo, Model model) {
		System.out.println("postList() --> ");

		MemberDO member = mdao.getMember(mdo);
		model.addAttribute("member", member);

		ArrayList<PostDO> plist = pdao.getPostList();
		model.addAttribute("plist", plist);

		return "postListView";

	}

	@RequestMapping("/getPostListCate.do")
	public String postListCate(MemberDO mdo, int cateSeq, Model model) {
		System.out.println("postList() --> ");

		MemberDO member = mdao.getMember(mdo);
		model.addAttribute("member", member);

		ArrayList<PostDO> plist = pdao.getPostListCate(cateSeq);
		model.addAttribute("plist", plist);

		return "postListView";

	}

	@RequestMapping("/insertPost.do")
	public String insertPost(MemberDO mdo, Model model) {
		System.out.println("insertPost() --> ");

		MemberDO member = mdao.getMember(mdo);
		model.addAttribute("member", member);
		return "insertPostView";
	}

	@RequestMapping("/insertPostProc.do")
	public String insertPostProc(PostDO pdo, MemberDO mdo, Model model) {
		System.out.println("insertPostProc() --> ");

		pdao.insertPost(pdo, mdo);
		MemberDO member = mdao.getMember(mdo);
		model.addAttribute("member", member);
		return "forward:getPostList.do?mdo=member";
	}

	@RequestMapping(value = "/getPost.do")
	public String getPost(PostDO pdo, CategoryDO cdo, MemberDO mdo, Model model) {
		System.out.println("getPost() --> ");

		PostDO post = pdao.getPost(pdo);
		model.addAttribute("post", post);

		CategoryDO cate = cdao.getCategory(post);
		model.addAttribute("cate", cate);

		ArrayList<CommentDO> commentList = cmdao.getCommentList(post);
		model.addAttribute("commentList", commentList);

		MemberDO member = mdao.getMember(mdo);
		model.addAttribute("member", member);

		return "getPostView";
	}

	@RequestMapping("/modifyPost.do")
	public String modifyPost(PostDO pdo, MemberDO mdo, Model model) {
		System.out.println("modifyPost() --> ");

		PostDO post = pdao.getPost(pdo);
		model.addAttribute("post", post);
		CategoryDO cate = cdao.getCategory(post);
		model.addAttribute("cate", cate);
		ArrayList<CategoryDO> clist = cdao.getCategoryList();
		model.addAttribute("clist", clist);
		MemberDO member = mdao.getMember(mdo);
		model.addAttribute("member", member);

		return "modifyPostView";
	}

	@RequestMapping("/modifyPostProc.do")
	public String modifyPostProc(PostDO pdo, MemberDO mdo) {
		System.out.println("modifyPostProc() --> ");

		pdao.modifyPostProc(pdo);

		MemberDO member = mdao.getMember(mdo);

		return "forward:getPostList.do?mdo=member";
	}

	@RequestMapping("/deletePost.do")
	public String deletePost(PostDO pdo, MemberDO mdo) {
		System.out.println("deletePost() --> ");

		pdao.deletePost(pdo);
		MemberDO member = mdao.getMember(mdo);

		return "forward:getPostList.do?mdo=member";
	}

	// 좋아요 기능
	@RequestMapping("/likedPost.do")
	public String likedPost(PostDO pdo, MemberDO mdo, Model model) {
		System.out.println("likedPost() --> ");

		PostDO post = pdao.getPost(pdo);
		pdao.likedPost(post);

		MemberDO member = mdao.getMember(mdo);

//		PostDO post2 = pdao.getPost(post);
//		model.addAttribute("post", post2);
//		CategoryDO cate = cdao.getCategory(post2);
//		model.addAttribute("cate", cate);
//		ArrayList<CommentDO> commentList = cmdao.getCommentList(post2);
//		model.addAttribute("commentList", commentList);

		return "forward:getPost.do?pdo=post&&mdo=member";
	}

	// 댓글 관련 기능
	@RequestMapping("/insertComment.do")
	public String insertComment(CommentDO cmdo, PostDO pdo, MemberDO mdo, Model model) {
		System.out.println("insertComment() --> ");

		cmdao.insertComment(cmdo, pdo);

		PostDO post = pdao.getPost(pdo);
		MemberDO member = mdao.getMember(mdo);

//		model.addAttribute("post", post);
//		CategoryDO cate = cdao.getCategory(post);
//		model.addAttribute("cate", cate);
//		ArrayList<CommentDO> commentList = cmdao.getCommentList(post);
//		model.addAttribute("commentList", commentList);

		return "forward:getPost.do?pdo=post&&mdo=member";
	}

	// 로그인 관련 기능.
	@RequestMapping("/login.do")
	public String login() {
		System.out.println("login() --> ");

		return "login";
	}

	@RequestMapping(value = "loginProc.do", method = RequestMethod.POST)
	public String loginProc(MemberDO mdo, Model model) {
		System.out.println("loginProc() --> ");

		ArrayList<MemberDO> mList = mdao.getMemberList();

		for (int i = 0; i < mList.size(); i++) {
			MemberDO mListmdo = mList.get(i);
			System.out.println(mListmdo.getId());
			System.out.println(mListmdo.getPassword());

			if ((mdo.getId().equals(mListmdo.getId())) && (mdo.getPassword().equals(mListmdo.getPassword()))) {
				model.addAttribute("member", mListmdo);
				return "forward:getPostList.do?mdo=mListdo";
			}
		}

		return "memberJoin";
	}

	@RequestMapping(value = "/memberJoin.do")
	public String memberJoin() {
		System.out.println("memberJoin() --> ");

		return "memberJoin";
	}

	@RequestMapping(value = "/memberJoinProc.do")
	public String memberJoinPorc(MemberDO mdo) {
		System.out.println("memeberJointProc() --> ");

		mdao.memberJoin(mdo);

		return "login";
	}

	@RequestMapping(value = "/editMember.do")
	public String editMember(MemberDO mdo, Model model) {
		System.out.println("editMember() --> ");

		MemberDO member = mdao.getMember(mdo);
		model.addAttribute("member", member);
		return "editMember";
	}

	@RequestMapping(value = "/editMemberProc.do")
	public String editMemberProc(MemberDO mdo) {
		System.out.println("editMemberProc() --> ");

		MemberDO member = mdao.getMember(mdo);

		mdao.editMember(mdo);

		return "forward:getPostList.do?mdo=member";
	}

	// 회원 가입 아이디 중복 체크
	@RequestMapping("checkId.do")
	public String checkId(@RequestParam("id") String id) {
		System.out.println("checkId() --> ");

		ArrayList<MemberDO> mList = mdao.getMemberList();

		for (int i = 0; i < mList.size(); i++) {
			MemberDO mListmdo = mList.get(i);

			if (id.equals(mListmdo.getId())) {
				return "N";
			}
		}

		return "Y";
	}

	// 글 검색 기능
	@RequestMapping(value = "searchPostList.do")
	public String searchPostList(@RequestParam(value = "searchCon") String searchCon,
			@RequestParam(value = "searchKey") String searchKey, MemberDO mdo, Model model) {
		System.out.println("searchPostList --> ");

		MemberDO member = mdao.getMember(mdo);

		ArrayList<PostDO> plist = pdao.searchPostList(searchCon, searchKey);
		model.addAttribute("plist", plist);
		model.addAttribute("member", member);

		return "postListView";

	}

	// 글 정렬 기능
	@RequestMapping(value = "sortByDate.do")
	public String sortByDate(MemberDO mdo, Model model) {

		ArrayList<PostDO> plist = pdao.sortByDate();

		model.addAttribute("plist", plist);
		MemberDO member = mdao.getMember(mdo);
		model.addAttribute("member", member);

		return "postListView";
	}

	// 글 정렬 기능
	@RequestMapping(value = "sortByLiked.do")
	public String sortByLiked(MemberDO mdo, Model model) {

		ArrayList<PostDO> plist = pdao.sortByLiked();

		model.addAttribute("plist", plist);
		MemberDO member = mdao.getMember(mdo);
		model.addAttribute("member", member);

		return "postListView";
	}

}
