package tw.group5.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
@EnableWebMvc //允許mvc架構，透過DispatcherServlet， A.K.A -> <context:component-scan base-package="tw.group5"/>
@ComponentScan(basePackages = "tw.group5")
@EnableTransactionManagement //啟用交易管理
public class SpringMVCJavaConfig implements WebMvcConfigurer {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setOrder(5); //最不優先(因為很吃資源)
		return viewResolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/view/resources/images/");
		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/view/resources/js/");
	}
	
	/*json組態設定-----------------------------------------------------------------------------------------------------------------------------------------------*/
	@Bean
	public MappingJackson2JsonView jsonView() {
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setPrettyPrint(true);
		return jsonView;
	}
	@Bean
	public Jaxb2Marshaller jaxbMarchaller() {
		Jaxb2Marshaller jaxbMarchaller = new Jaxb2Marshaller();
//		jaxbMarchaller.setClassesToBeBound(new Class[] {House.class});
		jaxbMarchaller.setPackagesToScan("tw.group5");
		return jaxbMarchaller;
	}
	@Bean
	public ContentNegotiatingViewResolver contentNegotiatingViewResolver(MappingJackson2JsonView jsonView) {
		ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
		List<View> defaultViews = new ArrayList<View>();
		defaultViews.add(jsonView);
		return contentNegotiatingViewResolver;
	}
	/*above the dashed line: json組態設定------------------------------------------------------------------------------------------------------------*/
	
	/*允許上傳檔案的檔名可用中文---------------------------------------------------------------------------*/
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		return multipartResolver;
	}
	/*above the dashed line: 上傳檔案的檔名可用中文---------------------------------------------------------------------------------------------*/

}
