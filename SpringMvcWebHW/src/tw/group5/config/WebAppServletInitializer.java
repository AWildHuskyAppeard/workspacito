package tw.group5.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//意義上等同於web.xml
public class WebAppServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override //beans.config.xml
	protected Class<?>[] getRootConfigClasses() {
		//大的組態檔，設定連線(類似beans.config.xml)
		return new Class[] {RootAppConfig.class};
//		return null;
	}

	@Override //mvc-servlet.xml
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {SpringMVCJavaConfig.class}; //小的組態檔，設定映射關係的
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	//處理編碼
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter charEncodingFilter = new CharacterEncodingFilter();
		charEncodingFilter.setEncoding("UTF-8");
		charEncodingFilter.setForceEncoding(true);
		return new Filter[] {charEncodingFilter};
	}
	
	

}
