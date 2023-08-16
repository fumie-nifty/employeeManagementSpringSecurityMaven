package jp.co.flm.service;

import jp.co.flm.entity.Employee;

/**
 * 従業員登録Service
 */
public interface InsertEmpService {

	/**
	 * 従業員情報の確認を行う
	 * @param employee 従業員情報
	 * @return employee 従業員情報
	 */
	public Employee confirmEmployee(Employee employee) ;

	/**
	 * 従業員情報の登録を行う
	 * @param employee 従業員情報
	 */
	public void registEmployee(Employee employee) ;

}
