package test.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.controller.Action;
import test.controller.ActionForward;

public class MemberInsertFormAction extends Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		//jsp가 응답 할 수 있게 하기 위해서는 forward 로 보내야 한다! (주소창에 jsp가 나오면 안된다). 
		//controller 를 거쳐서 보낼 때는 redirect 로 보내야 한다.
		ActionForward af = new ActionForward("/views/member/insertform.jsp");
		
		return af;
	}

}
