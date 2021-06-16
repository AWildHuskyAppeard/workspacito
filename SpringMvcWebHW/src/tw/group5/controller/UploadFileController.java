package tw.group5.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
		byte[] byteArr = file.getBytes();
		
		try {
			picture.setPicture(byteArr);
			System.out.println("*************************");
			System.out.println("byteArr: " + byteArr);
			picture.setFilename(fileOGName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		pictureService.insert(picture);
	}
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
