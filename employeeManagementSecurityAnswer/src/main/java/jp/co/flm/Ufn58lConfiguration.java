/**
 * Ufn58lConfiguration.java
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jp.co.flm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//import jp.co.flm.common.aspect.LoggingAspect;

/**
 * コンフィグレーションクラス
 * @author FLM
 * @version 1.0.0
 */
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class Ufn58lConfiguration {

	/**
	 * ResourceBundleMessageSourceのBean定義
	 * @return {@code ResourceBundleMessageSource}
	 */
	@Bean
	ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource
			= new ResourceBundleMessageSource();
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setBasenames("ufn58l-messages");
		return messageSource;
	}

	/**
	 * LoggingAspectクラスのBean定義
	 * @return {@code LoggingAspect}
	 */
	/*
	@Bean
	LoggingAspect loggingAspect() {
		return new LoggingAspect();
	}
	*/
}
