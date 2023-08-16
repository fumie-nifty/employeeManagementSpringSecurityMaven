package jp.co.flm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * JUnitライフサイクルメソッドの確認
 * @author FLM
 * @version 1.0.0
 */
public class SampleTest {

	//全体の事前処理
	@BeforeAll
	public static void beforAll() {
		System.out.println("@BeforeAll");
	}

	//各テストの事前処理
	@BeforeEach
	public void beforEach() {
		System.out.println("@BeforeEach");
	}

	//全体の事後処理
	@AfterAll
	public static void afterAll() {
		System.out.println("@AfterAll");
	}

	//各テストの事後処理
	@AfterEach
	public void afterEach() {
		System.out.println("@AfterEach");
	}

	//テスト処理
	@Test
	@DisplayName("文字列比較テスト：正常系")
	public void testString01() {

		String expected = "flm";		//期待値
		String actual = "flm";			//テスト実施結果

		System.out.println("testString01");

		//期待値とテスト実施結果が等しいかを検証
		assertEquals(expected,actual);

	}

	//テスト処理
	@Test
	@DisplayName("文字列比較テスト：異常系")
	public void testString02() {

		String expected = "flm";		//期待値
		String actual = "FLM";			//テスト実施結果

		System.out.println("testString02");

		assertEquals(expected,actual);

	}

}
