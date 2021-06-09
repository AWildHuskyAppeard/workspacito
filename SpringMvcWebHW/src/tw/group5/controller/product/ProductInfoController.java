package tw.group5.controller.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tw.group5.model.product.ProductInfo;
import tw.group5.model.product.service.ProductServiceImpl;

@Controller
public class ProductInfoController{

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
	
	
	@GetMapping("/product")
	public String addProduct() {
		return "product/edit";
	}

	@PostMapping("/product")
	@ResponseBody
	public String addProduct(ProductInfo productInfo, @RequestParam("p_Img") MultipartFile p_Img, @RequestParam("p_Video") MultipartFile p_Video,
			HttpServletRequest request) throws IOException {
		String imgName = p_Img.getOriginalFilename();
		String videoName = p_Video.getOriginalFilename();
		//String videoName = p_Video.getOriginalFilename();
		
		
		String saveFileDir = request.getSession().getServletContext().getRealPath("/")+"uploadTemDir\\";
		File saveDirImg = new File(saveFileDir);
		saveDirImg.mkdirs();
		
		String saveImgPath = saveFileDir + imgName;
		String saveVideoPath = saveFileDir + videoName;
		File saveImg = new File(saveImgPath);
		File saveVideo = new File(saveVideoPath);
		p_Img.transferTo(saveImg);
		p_Video.transferTo(saveVideo);
		
		if (imgName != null && imgName.length() !=0 && videoName != null && videoName.length() !=0 ) {
			saveFile(productInfo, saveImgPath, saveVideoPath);
			return "failed";
		}
		return "redirect:/product/list";
	}
	
//	@GetMapping("/showproducts")
//	public String showAllproduct() {
//		return "product/list";
//	}
//	@GetMapping("/product")
//	public String addProduct(Model m) {
//		ProductInfo product = new ProductInfo();
//		service.saveProduct(product);
//		m.addAttribute("product", product);
//		return "/product/edit";
//	}
	
	
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
	
	private void saveFile(ProductInfo productInfo, String saveImgPath, String saveVideoPath) throws IOException {
		
		InputStream isImg = new FileInputStream(saveImgPath);
		InputStream isVideo = new FileInputStream(saveVideoPath);
		byte[] b = new byte[isImg.available()];
		byte[] bs = new byte[isVideo.available()];
		isVideo.read(bs);
		isVideo.close();
		isImg.read(b);
		isImg.close();
		
		productInfo.setP_Img(b); //存到javabean裡面
		productInfo.setP_Video(bs);
		service.saveProduct(productInfo); //存到資料庫
		
	}
	
	
	
	
	
	
}
