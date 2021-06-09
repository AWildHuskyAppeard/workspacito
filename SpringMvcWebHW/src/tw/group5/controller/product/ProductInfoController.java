package tw.group5.controller.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;

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
	public String addProduct(@RequestParam("p_Img") MultipartFile p_Img, @RequestParam("p_Video") MultipartFile p_Video,
			HttpServletRequest request,@RequestParam("u_ID")String u_ID,@RequestParam("p_Name")String p_Name,@RequestParam("p_Class")String p_Class,@RequestParam("p_Price") int p_Price,@RequestParam("p_DESC")String p_DESC) throws IOException {
		//新增空的bean放東西
		ProductInfo productInfo = new ProductInfo();
		//建立現在時間
		SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date date = new Date();
		String stDate = dt.format(date);
		//放form的值
		productInfo.setP_Class(p_Class);
		productInfo.setP_DESC(p_DESC);
		productInfo.setP_Name(p_Name);
		productInfo.setP_Price(p_Price);;
		productInfo.setU_ID(u_ID);
		productInfo.setP_createDate(stDate);
		
		String imgName = p_Img.getOriginalFilename();
		String videoName = p_Video.getOriginalFilename();
		//String videoName = p_Video.getOriginalFilename();
		System.out.println("hello");
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
			//傳過去方法然後存到db
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
		System.out.println(productInfo);
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
