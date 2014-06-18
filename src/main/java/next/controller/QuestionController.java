package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.Controller;
import next.util.Constants;
import next.util.ServletRequestUtils;

public class QuestionController implements Controller {

	private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String writer = ServletRequestUtils.getParameter(request, Constants.REQUEST_WRITER);
		String title = ServletRequestUtils.getParameter(request, Constants.REQUEST_TITLE);
		String contents = ServletRequestUtils.getParameter(request, Constants.REQUEST_CONTENTS);

		//PrintTest
		logger.info("writer : "+writer);
		logger.info("title : "+title);
		logger.info("contents : "+contents);
		
		QuestionDao questionDao = new QuestionDao();
		questionDao.insert(new Question(writer, title, contents));
		
		return "redirect:/";
	}

}
