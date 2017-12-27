package test.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;
import test.member.dao.MemberDao;
import test.member.dto.MemberDto;

public class MemberListAction extends Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//1. 회원 목록을 얻어온다.
		List<MemberDto> list = MemberDao.getInstance().getList();
		//2. request 에 담는다.
		request.setAttribute("list", list);
		//3. view 페이지로 forward 이동할 수 있도록 ActionForward 객체를 생성해서
		ActionForward af = new ActionForward("/views/member/list.jsp");
		//리턴해 준다.
		return af;
	}

}
