package tw.group5.controller.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tw.group5.model.product.Product;
import tw.group5.model.product.dao.TestFileDao;

@Controller
public class TestController {
	
	@GetMapping("/uploadpage")
	public String upload() {
		return "product/testfile";
	}

	@PostMapping("/upload")
	@ResponseBody
	public String uploadfile(@RequestParam("p_Img") MultipartFile p_Img, @RequestParam("p_Video") MultipartFile p_Video,
			HttpServletRequest request) throws IllegalStateException, IOException {
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
			saveFile(saveImgPath, saveVideoPath);
			return "failed";
		}
		return "redirect:/product/list";
	}
		@Autowired
		private TestFileDao dao;
		
		private void saveFile(String saveImgPath, String saveVideoPath) throws IOException {
		Product product = new Product();
		InputStream isImg = new FileInputStream(saveImgPath);
		InputStream isVideo = new FileInputStream(saveVideoPath);
		byte[] b = new byte[isImg.available()];
		byte[] bs = new byte[isVideo.available()];
		isVideo.read(bs);
		isVideo.close();
		isImg.read(b);
		isImg.close();
		
		product.setP_Img(b);; //存到javabean裡面
		product.setP_Video(bs);
		dao.insert(product); //存到資料庫
		
	}
	}
	

