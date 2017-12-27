package test.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;
import test.member.dao.MemberDao;

public class MemberDeleteAction extends Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward af = null;
		
		//1. 삭제할 회원의 번호를 읽어와서
		int num = Integer.parseInt(request.getParameter("num"));
		//2. DB 에서 삭제하고
		boolean isSuccess = MemberDao.getInstance().delete(num);
		
		if(isSuccess) {
		//3. 응답(리다일렉트)
			af = new ActionForward("/member/list.do");
			af.setRedirect(true); //리다일렉트 요청하도록 응답
		}
		return af;
	}
	
}
