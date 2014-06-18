package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.Controller;

public class AnswerController implements Controller {

	private static final Logger logger = LoggerFactory.getLogger(AnswerController.class);
	private AnswerDao answerDao = new AnswerDao();
	private QuestionDao questionDao = new QuestionDao();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		long questionId = Long.parseLong(request.getParameter("questionId"));
		String writer = request.getParameter("writer");
		String contents = request.getParameter("contents");
		
		//PrintTest
		logger.info("questionId : " +questionId);
		logger.info("writer : "+writer);
		logger.info("contents : "+contents);
		
		Answer answer = new Answer(writer, contents, questionId);
		
		int successInsertQueryNumber = answerDao.insert(answer);
		
		if ( successInsertQueryNumber == 1 ) {
			questionDao.updateCount(questionId);
		}
		
		return "api";
	}

}
