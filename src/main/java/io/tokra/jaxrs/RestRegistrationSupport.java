package io.tokra.jaxrs;

import java.util.ServiceLoader;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class RestRegistrationSupport {
	
	private static final Logger logger = LoggerFactory.getLogger(RestRegistrationSupport.class);
	
	private RestRegistrationSupport(){}

	/**
	 * Method for simple registration of singletons to {@link Application},
	 * using {@link ServiceLoader}
	 * 
	 * @param singletons
	 * @param clazz
	 */
	public static <T> void registerCommonSingletonsOf(@NotNull Set<Object> singletons, @NotNull Class<T> clazz) {
		ServiceLoader<T> providers = ServiceLoader.load(clazz);
		if (providers != null) {
			for (T provider : providers) {
				logger.info("Registering common: {} -> {}", clazz.getSimpleName(), provider.getClass().getName());
				singletons.add(provider);
			}
		}
	}
}