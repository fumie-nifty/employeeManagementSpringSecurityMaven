/**
 * EmployeeMapperUpdateTest.java
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

package jp.co.flm.mapper;

import static com.github.springtestdbunit.annotation.DatabaseOperation.*;
import static com.github.springtestdbunit.assertion.DatabaseAssertionMode.*;
import static org.assertj.core.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

import jp.co.flm.entity.Employee;
import jp.co.flm.test.util.ExecuteQueryForTestService;

/**
 * @author FLM
 * @version 1.0 yyyy/mm/dd
 */
@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:AllTest/setupDB.xml")
@DatabaseTearDown("classpath:AllTest/setupDB.xml")
@DisplayName("PT001_04:EmployeeMapper.update()メソッドのテスト")
public class EmployeeMapperUpdateTest {

	@Autowired
	EmployeeMapper sut;

	@Autowired
	ExecuteQueryForTestService executeQueryForTestService;

	@Test
	@ExpectedDatabase(value = "classpath:EmployeeMapper/expectedUpdateDB.xml",
			assertionMode = NON_STRICT_UNORDERED)
	@DisplayName("PT001_04_001:従業員情報が変更できる")
	void testUpdate_test1() {
		// setup
		Employee testArg_employee
			= new Employee(922106, "吉田　裕二", "研修部", "9912-3333");

		// assert
		sut.update(testArg_employee);
	}

	@Test
	@ExpectedDatabase(
		value = "classpath:EmployeeMapper/expectedDB_Unchanged.xml",
		assertionMode = NON_STRICT_UNORDERED)
	@DisplayName("PT001_04_002:従業員情報が更新できない場合（存在しない従業員番号）")
	void testUpdate_test2() {
		// setup
		Employee testArg_employee
			= new Employee(922107, "吉田　裕二", "研修部", "9912-3333");

		// assert
		int count = sut.update(testArg_employee);

		assertThat(count).isEqualTo(0);
	}

	@Test
	@DatabaseSetup(type = DELETE_ALL)
	@DisplayName("PT001_04_003:従業員情報が更新できない場合（レコード０件）")
	void testUpdate_test3() {
		// setup
		Employee testArg_employee
			= new Employee(922106, "吉田　裕二", "研修部", "9912-3333");

		// assert
		int count = sut.update(testArg_employee);

		assertThat(count).isEqualTo(0);
	}

	@Nested
	@SpringBootTest
	@DisplayName("PT001_04_004:DataAccessExceptionが発生する場合")
	class MemberTableRenamed {

		@BeforeEach
		void setUp() throws Exception {
			executeQueryForTestService.renameTable("employee", "employee2");
		}

		@AfterEach
		void tearDown() throws Exception {
			executeQueryForTestService.renameTable("employee2", "employee");
		}

		@Test
		@DisplayName("従業員情報の変更に失敗する")
		void testFindOne_test4() throws Exception {
			// assert
			Employee testArg_employee
			= new Employee(922106, "吉田　裕二", "研修部", "9912-3333");

		// assert
					assertThatThrownBy(() -> sut.update(testArg_employee))
				.isInstanceOf(DataAccessException.class);
		}
	}
}
