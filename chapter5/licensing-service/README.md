## Bugs

# On Setting Default Language

The configuration used to set the default language in Spring Boot is:

spring.mvc.locale=it

In this project the value is set in configserver in licensing-service.properties.

# Code removed

To avoid problem the @EnableWebMvc annotation was removed by ExceptionController class since it disabled the Auto-configuration implemented by WebMvcAutoConfiguration and performed by the localeResolver method.

Below the relevant code of WebMvcAutoConfiguration is shown:

	/**
	 * {@link EnableAutoConfiguration Auto-configuration} for {@link EnableWebMvc Web MVC}.
	 *
	 * @author Phillip Webb
	 * @author Dave Syer
	 * @author Andy Wilkinson
	 * @author Sébastien Deleuze
	 * @author Eddú Meléndez
	 * @author Stephane Nicoll
	 * @author Kristine Jetzke
	 * @author Bruce Brouwer
	 * @author Artsiom Yudovin
	 * @since 2.0.0
	 */
	@Configuration(proxyBeanMethods = false)
	@ConditionalOnWebApplication(type = Type.SERVLET)
	@ConditionalOnClass({ Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class })
	@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
	@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)
	@AutoConfigureAfter({ DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class,
			ValidationAutoConfiguration.class })
	public class WebMvcAutoConfiguration {
	...
	
		@Bean
		@ConditionalOnMissingBean
		@ConditionalOnProperty(prefix = "spring.mvc", name = "locale")
		public LocaleResolver localeResolver() {
			if (this.mvcProperties.getLocaleResolver() == WebMvcProperties.LocaleResolver.FIXED) {
				return new FixedLocaleResolver(this.mvcProperties.getLocale());
			}
			AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
			localeResolver.setDefaultLocale(this.mvcProperties.getLocale());
			return localeResolver;
		}
	...
	
