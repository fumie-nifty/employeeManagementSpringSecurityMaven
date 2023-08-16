package jp.co.flm.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jp.co.flm.common.exception.BusinessException;
import jp.co.flm.entity.Employee;
import jp.co.flm.form.EmployeeForm;
import jp.co.flm.service.InsertEmpService;

@SessionAttributes(types = EmployeeForm.class)
@Controller
public class InsertEmpController {

	/** Service */
	@Autowired
	InsertEmpService service;

	/**
	 * 例題一覧画面（トップ）から従業員登録画面への遷移
	 * @return /insert-input
	 */
	@RequestMapping("/inputInsert")
	public String inputInsert(Model model) {

		// フォームオブジェクトをModelに設定
		model.addAttribute("employeeForm", new EmployeeForm());

		return "/insert-input";
	}
	/**
	 * 従業員登録確認画面を表示する
	 */
	@RequestMapping(value = "/confirmInsert", method = RequestMethod.POST)
	public String confirmInsert(
			@ModelAttribute("employeeForm") @Validated EmployeeForm form,
			BindingResult result) {

		// 入力チェック
		if (result.hasErrors()) {
			// 従業員登録画面（/eg6/insert-input）を返却する
			return "/insert-input";
		}

		Employee employee = new Employee(form.getEmployeeId(),
																form.getEmployeeName(),
																form.getSection(),
																form.getPhone());

			service.confirmEmployee(employee);

		return "/insert-confirm";
	}

	/**
	 * 従業員登録結果画面を表示する
	 */
	@RequestMapping(value = "/commitInsert", method = RequestMethod.POST)
	public String commitInsert(@ModelAttribute("employeeForm") @Validated EmployeeForm form,
													Model model,
													SessionStatus status) {

		// フォームオブジェクトに格納された情報を従業員情報オブジェクトに設定する
		Employee employee = new Employee(form.getEmployeeId(),
								form.getEmployeeName(),
								form.getSection(),
								form.getPhone());

		// Serviceの呼び出し
		service.registEmployee (employee);

		// Modelに情報を設定
		model.addAttribute("employee", employee);

		//セッションからフォームオブジェクトの削除
		status.setComplete();

		return "/insert-complete";
	}

	/**
	 * 従業員登録画面へ戻る
	 */
	@RequestMapping("/reviseInput")
	public String reviseInput(@ModelAttribute("employeeForm") EmployeeForm form) {
		return "/insert-input";
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
		model.addAttribute("employeeForm", new EmployeeForm());

		return "/insert-input";
	}

}
