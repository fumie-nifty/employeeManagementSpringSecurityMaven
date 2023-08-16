package jp.co.flm.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

	private int employeeId;

	private String employeeName;

	private String section;

	private String phone;
}