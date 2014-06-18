package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.Controller;

public class QuestionController implements Controller {

	private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
	private QuestionDao questionDao = new QuestionDao();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");

		//PrintTest
		logger.info("writer : "+writer);
		logger.info("title : "+title);
		logger.info("contents : "+contents);
		
		Question question = new Question(writer, title, contents);
		questionDao.insert(question);
		
		return "redirect:/";
	}

}
