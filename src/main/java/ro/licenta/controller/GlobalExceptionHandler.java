package ro.licenta.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	public static final String DEFAULT_ERROR_VIEW = "error";
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception{
		logger.error("Process error : " + e.getMessage());
		
		// If the exception is annotated with @ResponseStatus re throw it and let
	    // the framework handle it - like the OrderNotFoundException example
	    // at the start of this post.
	    // AnnotationUtils is a Spring Framework utility class.
		if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		}
		
		//Otherwise setup and sens the user to a default error view
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", request.getRequestURL());
		mav.setViewName(DEFAULT_ERROR_VIEW);
		
		return mav;
	}
}
