package jp.co.flm.common.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SystemExceptionHandler {

	/**
	 * システム例外（SystemException・DataAccessException）をハンドリングする
	 */
	@ExceptionHandler({ SystemException.class, DataAccessException.class })
	public String handleError(Model model) {
		model.addAttribute("message", "システムエラーです。システム管理者に連絡してください。");
		return "/error";
	}

}

