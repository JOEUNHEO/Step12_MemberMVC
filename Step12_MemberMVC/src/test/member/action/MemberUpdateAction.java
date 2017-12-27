package test.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;
import test.member.dao.MemberDao;
import test.member.dto.MemberDto;

public class MemberUpdateAction extends Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		//1. 파라미터로 전달되는 수정할 회원정보 얻어오기
		int num = Integer.parseInt(request.getParameter("num"));
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		
		MemberDto dto = new MemberDto(num, name, addr);
		//2. DB 에 수정 반영
		boolean isSuccess = MemberDao.getInstance().update(dto);
		
		//3. 응답
		if(isSuccess) {
			request.setAttribute("msg", "회원 정보를 수정했습니다.");
		}else {
			request.setAttribute("msg", "회원 정보 수정에 실패했습니다.");
		}
		
		request.setAttribute("url", "/member/list.do");
		
		return new ActionForward("/views/member/alert.jsp");
		
	}

}
