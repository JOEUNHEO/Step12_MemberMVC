package test.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;
import test.member.dao.MemberDao;
import test.member.dto.MemberDto;

public class MemberUpdateFormAction extends Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//1. 수정할 회원의 번호를 읽어와서
		int num = Integer.parseInt(request.getParameter("num"));
		//2. DB 에서 수정할 회원의 정보를 얻어와서
		MemberDto dto = MemberDao.getInstance().getData(num);
		//3. request 에 담고
		request.setAttribute("dto", dto);
		//4. forward 이동해서 form 을 응답한다.						
		return new ActionForward("/views/member/updateform.jsp");
		
		
	}

}
