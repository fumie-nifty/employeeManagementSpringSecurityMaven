package jp.co.flm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TopController {

	@RequestMapping("/")
	public String handler() {
		return "/top";
	}

}
