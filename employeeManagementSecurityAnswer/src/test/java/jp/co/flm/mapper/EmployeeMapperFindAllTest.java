/**
 * EmployeeMapperTest.java
 * All Rights Reserved, Copyright Fujitsu Learning Media Limited
 */

package jp.co.flm.mapper;

import static com.github.springtestdbunit.annotation.DatabaseOperation.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

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
@DisplayName("PT001_03:EmployeeMapper.flndAllのテスト")
public class EmployeeMapperFindAllTest {

	@Autowired
	EmployeeMapper sut;

	@Autowired
	ExecuteQueryForTestService executeQueryForTestService;

	@Test
	@DisplayName("PT001_03_001:従業員情報のリストが取得できる場合")
	void testFindAll_test1() {
		// setup
		List<Employee> expected = List.of(
			new Employee(922101, "鈴木　一郎", "研修部", "7700-2257"),
			new Employee(922102, "田村　正人", "研修部", "7700-2258"),
			new Employee(922103, "松田　明美", "開発部", "7712-4418"),
			new Employee(922104, "浅井　順二", "開発部", "7712-4416"),
			new Employee(922105, "高橋　道夫", "営業部", "7712-3316"),
			new Employee(922106, "夏木　裕子", "営業部", "7712-3317"));

		// assert
		assertThat(sut.findAll()).isEqualTo(expected);
	}

	@Test
	@DatabaseSetup(type = DELETE_ALL)
	@DisplayName("PT001_03_002:従業員情報のリストが取得できない場合（レコード0件）")
	void testFindAll_test2() {
		// assert
		assertThat(sut.findAll()).isEmpty();
	}

	@Nested
	@SpringBootTest
	@DisplayName("PT001_03_003:DataAccessExceptionが発生する場合")
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
		@DisplayName("従業員情報のリストの取得に失敗する")
		void testFindAll_test3() throws Exception {
			// assert
			assertThatThrownBy(() -> sut.findAll())
				.isInstanceOf(DataAccessException.class);
		}
	}
}
