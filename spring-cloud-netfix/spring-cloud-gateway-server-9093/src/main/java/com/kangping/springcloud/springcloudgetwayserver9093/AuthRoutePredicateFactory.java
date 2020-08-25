

package com.kangping.springcloud.springcloudgetwayserver9093;


import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * <p >
 * 功能： 自定义断言，
 * 			名称必须以 RoutePredicateFactory 结尾
 * </p>
 * 
 * @param
 * @return
 * @author kangping
 * @date  2020/8/25 15:00
 */

@Component
public class AuthRoutePredicateFactory extends AbstractRoutePredicateFactory<AuthRoutePredicateFactory.Config> {

	public AuthRoutePredicateFactory() {
		super(Config.class);
	}

	/**
	 * Name key.
	 */
	public static final String NAME_KEY = "name";

	/**
	 * Regexp key.
	 */
	public static final String REGEXP_KEY = "regexp";

	@Override
	public List<String> shortcutFieldOrder() {
		return Arrays.asList(NAME_KEY,REGEXP_KEY);
	}

	@Override
	public Predicate<ServerWebExchange> apply(Config config) {
		return new GatewayPredicate() {
			@Override
			public boolean test(ServerWebExchange exchange) {
				HttpHeaders headers = exchange.getRequest().getHeaders();
				List<String> strings = headers.get(config.getName());
				if (CollectionUtils.isEmpty(strings)) {
					return false;
				}
				return strings.contains("authValue");
			}


		};
	}

	public static class Config {

		private String name;

		private String regexp;

		public String getName() {
			return name;
		}

		public Config setName(String name) {
			this.name = name;
			return this;
		}

		public String getRegexp() {
			return regexp;
		}

		public Config setRegexp(String regexp) {
			this.regexp = regexp;
			return this;
		}

	}

}
