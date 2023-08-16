/**
 * EmployeeMapperSaveTest.java
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

package jp.co.flm.mapper;

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
import org.springframework.transaction.annotation.Transactional;

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
@DisplayName("PT001_02:EmployeeMapper.save()メソッドのテスト")
public class EmployeeMapperSaveTest {

	@Autowired
	EmployeeMapper sut;

	@Autowired
	ExecuteQueryForTestService executeQueryForTestService;

	@Test
	@ExpectedDatabase(value = "classpath:EmployeeMapper/expectedDB.xml",
			assertionMode = NON_STRICT_UNORDERED)
	@DisplayName("PT001_02_001:従業員情報を登録できる")
	void testSave_test1() {
		// setup
		Employee testArg_employee
			= new Employee(922100, "富士通　太郎", "研修部", "7700-8888");

		// assert
		sut.save(testArg_employee);
	}

	@Test
	@Transactional
	@ExpectedDatabase(
			value = "classpath:EmployeeMapper/expectedDB_Unchanged.xml",
			assertionMode = NON_STRICT_UNORDERED)
	@DisplayName("PT001_02_002:従業員情報が登録できない（employeeIdの重複）")
	void testSave_test2() {
		// setup
		Employee testArg_employee
			= new Employee(922101, "富士通　太郎", "研修部", "7700-8888");

		// assert
		assertThatThrownBy(() -> sut.save(testArg_employee))
			.isInstanceOf(DataAccessException.class);
	}

	@Nested
	@SpringBootTest
	@DisplayName("PT001_02_003:DataAccessExceptionが発生する場合")
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
		@DisplayName("従業員情報の登録に失敗する")
		void testFindOne_test4() throws Exception {

			Employee testArg_employee
			= new Employee(922100, "富士通　太郎", "研修部", "7700-8888");

		// assert
			assertThatThrownBy(() ->sut.save(testArg_employee))
				.isInstanceOf(DataAccessException.class);
		}
	}
}
