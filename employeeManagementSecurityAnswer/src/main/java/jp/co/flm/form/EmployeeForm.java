/**
 * EmployeeForm.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jp.co.flm.form;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 従業員登録情報入力フォーム
 * @author FLM
 * @version 1.0 yyyy/mm/dd
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeForm implements Serializable {

	/** 従業員ID */
	@Min(100000)
	@Max(999999)
	@NotNull
	private Integer employeeId;

	/** 従業員名 */
	@Size(max = 20)
	@NotBlank
	private String employeeName;

	/** 部署名 */
	@Size(max = 5)
	@NotBlank
	private String section;

	/** 内線番号 */
	@Pattern(regexp = "\\d{4}-\\d{4}")
	@NotBlank
	private String phone;

}
