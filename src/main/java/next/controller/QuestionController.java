package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.Controller;

public class QuestionController implements Controller {

	private QuestionDao questionDao = new QuestionDao();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		//test code
//		System.out.println("writer : "+writer);
//		System.out.println("title : "+title);
//		System.out.println("contents : "+contents);
		
		Question question = new Question(writer, title, contents);
		questionDao.insert(question);
		
		return "redirect:/";
	}

}
