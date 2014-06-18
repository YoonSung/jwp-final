package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import core.mvc.Controller;

public class AnswerController implements Controller {

	private AnswerDao answerDao = new AnswerDao();
	private QuestionDao questionDao = new QuestionDao();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		long questionId = Long.parseLong(request.getParameter("questionId"));
		String writer = request.getParameter("writer");
		String contents = request.getParameter("contents");
		
		Answer answer = new Answer(writer, contents, questionId);
		
		int successInsertQueryNumber = answerDao.insert(answer);
		
		if ( successInsertQueryNumber == 1 ) {
			questionDao.updateCount(questionId);
		}
		
		//test code
//		System.out.println("into AnswerController");
//		System.out.println("questionId : " +questionId);
//		System.out.println("writer : "+writer);
//		System.out.println("contents : "+contents);
		
		return "api";
	}

}
