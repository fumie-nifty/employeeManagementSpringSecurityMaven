package jp.co.flm.mapper;

import static com.github.springtestdbunit.annotation.DatabaseOperation.*;
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
@DisplayName("PT001_01:EmployeeMapper.findOne()メソッドのテスト")
public class EmployeeMapperFindOneTest {

	@Autowired
	EmployeeMapper sut;

	@Autowired
	ExecuteQueryForTestService executeQueryForTestService;

	@Test
	@DisplayName("PT001_01_001：従業員が検索できる場合")
	void testFindOne_test1() {
		// setup
		Integer testArg_employeeId = 922101;
		Employee expected = new Employee(922101, "鈴木　一郎", "研修部", "7700-2257");

		// assert
		assertThat(sut.findOne(testArg_employeeId)).isEqualTo(expected);

	}

	@Test
	@DisplayName("PT001_01_002：従業員が検索できる場合（存在しない従業員番号）")
	void testFindOne_test2() {
		// setup
		Integer testArg_employeeId = 922100;

		// assert
		assertThat(sut.findOne(testArg_employeeId)).isNull();
	}

	@Test
	@DatabaseSetup(type = DELETE_ALL)
	@DisplayName("PT001_01_003：従業員が検索できる場合（レコード０件）")
	void testFindOne_test3() {
		// setup
		Integer testArg_employeeId = 922101;

		// assert
		assertThat(sut.findOne(testArg_employeeId)).isNull();
	}

	@Nested
	@SpringBootTest
	@DisplayName("PT001_01_004：DataAccessExceptionが発生する場合")
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
		@DisplayName("従業員情報の取得に失敗する")
		void testFindOne_test4() throws Exception {
			// assert
			assertThatThrownBy(() -> sut.findOne(922101))
				.isInstanceOf(DataAccessException.class);
		}
	}
}
