package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.util.Constants;
import next.util.ServletRequestUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.Controller;

public class AnswerController implements Controller {

	private static final Logger logger = LoggerFactory.getLogger(AnswerController.class);
	private AnswerDao answerDao = new AnswerDao();
	private QuestionDao questionDao = new QuestionDao();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		long questionId = ServletRequestUtils.getLongParameter(request, Constants.REQUEST_QUESTION_ID);
		String writer = ServletRequestUtils.getParameter(request, Constants.REQUEST_WRITER);
		String contents = ServletRequestUtils.getParameter(request, Constants.REQUEST_CONTENTS);
		
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
