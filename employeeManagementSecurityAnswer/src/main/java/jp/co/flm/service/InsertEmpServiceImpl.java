package jp.co.flm.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.flm.common.exception.BusinessException;
import jp.co.flm.entity.Employee;
import jp.co.flm.mapper.EmployeeMapper;

/**
 * 従業員登録Serviceの実装クラス
 */
@Service
public class InsertEmpServiceImpl implements InsertEmpService {

	/** Mapper */
	@Autowired
	private EmployeeMapper mapper;

	/**
	 * @see InsertEmpService#registEmployee(Employee)
	 */
	@Override
	public void registEmployee(Employee employee) {

		// Mapperの呼び出し
		mapper.save(employee);

	}

	/**
	 * @see jp.co.flm.service.InsertEmpService#confirmEmployee(jp.co.flm.entity.Employee)
	 */
	@Override
	public Employee confirmEmployee(Employee employee) {
		ArrayList<String> sectionList = new ArrayList<>();
		sectionList.add("研修部");
		sectionList.add("開発部");
		sectionList.add("営業部");

			if(!sectionList.contains(employee.getSection())) {
				throw new BusinessException("部署名は、「研修部・開発部・営業部」のいずれかを入力してください。");
			}

		return employee;
	}
}
