/**
 * Eg4_AllTest.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jp.co.flm.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

/**
 * @author FLM
 * @version 1.0 yyyy/mm/dd
 */
@RunWith(JUnitPlatform.class)
@DisplayName("PT001:EmployeeMapperのテスト")
@SelectClasses({
	EmployeeMapperFindOneTest.class,
	EmployeeMapperSaveTest.class,
	EmployeeMapperFindAllTest.class
	})
public class EmployeeMapper_AllTest {}
