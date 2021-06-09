package tw.group5.controller.cart;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.group5.model.cart.FooService;

@Controller
public class FooController {

    @Autowired
    private FooService fooService;
    
    @RequestMapping(value="/displayallbeans") 
    public String getHeaderAndBody(Map model){
        model.put("header", fooService.getHeader());
        model.put("message", fooService.getBody());
        return "displayallbeans";
    }
    
	// DIY // 用來列出所有bean的方法
	@Autowired
    private ApplicationContext applicationContext;
    
	@RequestMapping(path = "/listAllBeans", method = RequestMethod.GET)
	@ResponseBody
    public String listAllBeans() {
        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
        String aa = "";
        for(String beanName : allBeanNames) {
            System.out.println(beanName);
            aa = aa + beanName + "<br>";
        }
        return aa;
    }
}