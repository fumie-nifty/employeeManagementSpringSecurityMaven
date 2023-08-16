package jp.co.flm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import jp.co.flm.entity.Employee;

@Mapper
public interface EmployeeMapper {

	/**
	 * 従業員情報を検索する
	 * ハンズオン
	 */
	public Employee findOne(Integer id);

	/**
	 * 従業員情報を登録する
	 * 基本問題
	 */
	public void save(Employee employee);

	/**
	 * 従業員情報のリストを取得する
	 * 実践問題
	 */
	public List<Employee> findAll();

	/**
	 * 従業員情報更新する
	 * 実践問題
	 */
	public int update(Employee employee);

}

