package tw.group5.controller.product;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tw.group5.model.product.ProductInfo;
import tw.group5.model.product.service.ProductServiceImpl;

@Controller
public class ProductInfoController {

	@Autowired
	ProductServiceImpl service;
	
	@Autowired
	ServletContext context;
	
	
	
	public ProductInfoController() {
	}



	@GetMapping("/products")
	public String allProduct(Model m) {
		
		List<ProductInfo> list = service.findAll();
		m.addAttribute("products", list);
		
		return "/product/list";
	}
	
	@GetMapping("/showproducts")
	public String showAllproduct() {
		return "product/list";
	}
	
	@GetMapping("/insertproductform")
	public String addProduct() {
		return "product/edit";
	}
	
//	@GetMapping("/product")
//	public String addProduct(Model m) {
//		ProductInfo product = new ProductInfo();
//		service.saveProduct(product);
//		m.addAttribute("product", product);
//		return "/product/edit";
//	}
	
	@PostMapping("/product")
	public String addProduct(@RequestParam("p_Img")MultipartFile p_Img,@RequestParam("p_Video")MultipartFile p_Video,ProductInfo productInfo,Model m) throws IOException {
		productInfo.setP_Video(p_Video.getBytes());
		productInfo.setP_Img(p_Img.getBytes());
		service.saveProduct(productInfo);
		return "redirect:/product/list";
	}
	
	@GetMapping("/product/{p_ID}")
	public String toUpdate(@PathVariable("p_ID") Integer p_ID, Model m) {
		ProductInfo product = new ProductInfo();
		product = service.findByProductID(p_ID);
		m.addAttribute("product", product);
		return "/product/edit";
	}
	
	@PutMapping("/product")
	public String updateProduct(ProductInfo productInfo) {
		service.saveProduct(productInfo);
		return "redirect:/product/list";
	}
	
	@DeleteMapping("/product/{p_ID}")
	public String deleteProduct(@PathVariable("p_ID")Integer p_ID) {
		service.deleteProduct(p_ID);
		return "redirect:/product/list";
	}
	
	
	
	
	
	
	
	
}
