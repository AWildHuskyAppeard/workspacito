package tw.group5.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tw.group5.model.Picture;
import tw.group5.model.PictureDao;
import tw.group5.model.PictureService;

@Controller
public class UploadFileController {
	@Autowired
	private PictureService pictureService;

	@Autowired
	Picture picture;
	
	@Autowired
	private EntityManagerFactory emf;

	@GetMapping(path = "/uploadMainPage.controller")
	public String processMainPage() {
		return "uploadFile";
	}

	/*
	 * @PostMapping(path = "/testUploadFile.controller")
	 * 
	 * @ResponseBody public Map<String, String> upload (@RequestParam(name =
	 * "myFiles") MultipartFile file, @RequestParam String theText,
	 * HttpServletRequest request) throws IllegalStateException, IOException{
	 * System.out.println("傳進來的" + file); System.out.println("傳進來的" + theText);
	 * Map<String, String> map = new HashMap<>(); String path =
	 * request.getSession().getServletContext().getRealPath("/") +
	 * "testUploadTempDir\\"; String fileName = file.getOriginalFilename(); String
	 * saveFilePath = path + fileName;
	 * 
	 * System.out.println("fileName:" + fileName); System.out.println("save path" +
	 * saveFilePath);
	 * 
	 * File saveFile = new File(saveFilePath); file.transferTo(saveFile);
	 * 
	 * try { picture.setFilename(fileName); InputStream is = new
	 * FileInputStream(saveFilePath); byte bt[] = new byte[is.available()];
	 * is.read(bt); is.close(); picture.setPicture(bt);
	 * pictureService.insert(picture);
	 * 
	 * map.put("success", "上傳成功");
	 * 
	 * 
	 * } catch (Exception e) { map.put("fail", "上傳失敗"); }
	 * 
	 * return map; }
	 */

	@PostMapping(path = "/testUploadFile.controller")
	@ResponseBody
//	public Map<String, String> upload (@RequestParam(name = "myFiles") MultipartFile file, @RequestParam String theText) throws IllegalStateException, IOException{
	public void upload(@RequestParam(name = "myFiles") MultipartFile file, @RequestParam String theText)
			throws IllegalStateException, IOException {
		System.out.println("透過form表單進來的file: " + file);
		String fileOGName = file.getOriginalFilename();
		InputStream is = file.getInputStream();
		long size = file.getSize();
		EntityManager em = emf.createEntityManager();
		Session session = (Session)em.getDelegate();
		Blob blobPic = Hibernate.getLobCreator(session).createBlob(is, size);
		
		try {
			picture.setPicture(blobPic);
			System.out.println("*************************");
//			System.out.println("byteArr: " + byteArr);
			picture.setFilename(fileOGName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		pictureService.insert(picture);
	}
	
	
	//測試拿圖片
	@GetMapping(value = "/prac/showPic.controller", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	@ResponseBody
	public byte[] pracShowPic() throws SQLException {
//		picture.setId(8);
		Picture pic = pictureService.getPic(10);
		Blob picResult = pic.getPicture();
		byte[] sendback = picResult.getBytes(1, (int)picResult.length());
		return sendback;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
