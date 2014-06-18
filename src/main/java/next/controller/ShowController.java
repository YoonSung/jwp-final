package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.util.Constants;
import next.util.ServletRequestUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.Controller;

public class ShowController implements Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(ShowController.class);
	private QuestionDao questionDao = new QuestionDao();
	private AnswerDao answerDao = new AnswerDao();
	private Question question;
	private List<Answer> answers;
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		long questionId = ServletRequestUtils.getLongParameter(request, Constants.REQUEST_QUESTION_ID); 
		question = questionDao.findById(questionId);
		
		//test
		/*
		 * Question [
				questionId=, 
				writer= 
				title=, 
				contents=
				createdDate=2014-06-18 17:05:47.947, 
				countOfComment=3
			]
		 */
		
		//PrintTest
		logger.info("question Request : "+question.toString());
		
		answers = answerDao.findAllByQuestionId(questionId);
		request.setAttribute("question", question);
		request.setAttribute("answers", answers);
		return "show.jsp";
	}
}
