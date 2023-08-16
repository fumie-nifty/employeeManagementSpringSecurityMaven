package jp.co.flm.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.flm.common.exception.BusinessException;
import jp.co.flm.entity.Employee;
import jp.co.flm.form.EmployeeIdForm;
import jp.co.flm.service.SearchEmpService;

@Controller
public class SearchEmpController {

	/** Service */
	@Autowired
	private SearchEmpService service;

	@RequestMapping("/search")
	public String search(Model model) {

		// フォームオブジェクトをModelに設定
		model.addAttribute("employeeIdForm", new EmployeeIdForm());

		return "/retrieve-input";
	}

	@RequestMapping(value = "/retrieveEmployee", params = "employeeId")
	public String retrieveReqParam(@Validated EmployeeIdForm form,
												BindingResult result,
												Model model) {

		// リクエストパラメーターの入力チェック
		if (result.hasErrors()) {
			return "/retrieve-input";
		}

		Integer employeeId = form.getEmployeeId();

		//Employeeオブジェクトの検索
		Employee employee = service.getEmployee(employeeId);

		// Modelに情報を設定
		model.addAttribute("employee", employee);

		return "/retrieve-employee";
	}

	@RequestMapping("/retrieveAllEmployee")
	public String retrieveAllEmployee(Model model) {

		try {
		// Serviceの呼び出し
		List<Employee> employeeList = service.getAllEmployee();

		// Modelに情報を設定
		model.addAttribute("employeeList", employeeList);

		}catch(BusinessException e) {
			model.addAttribute("message", e.getMessage());
		}
		return "/retrieve-list";
	}

	/**
	 * 業務例外のハンドリング
	 * ハンドリングする例外クラス： BusinessException.class
	 */
	@ExceptionHandler(BusinessException.class)
	public String catchBizException(Model model, Exception e) {

		// エラーメッセージをModelに設定
		model.addAttribute("message", e.getMessage());

		// フォームオブジェクトをModelに設定
		model.addAttribute("employeeIdForm", new EmployeeIdForm());

		return "/retrieve-input";
	}

}

