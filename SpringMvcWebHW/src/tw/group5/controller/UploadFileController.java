//package tw.group5.controller;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import tw.group5.model.Picture;
//import tw.group5.model.PictureService;
//
//@Controller
//public class UploadFileController {
//	
//	@GetMapping(path = "/uploadMainPage.controller")
//	public String processMainPage() {
//		return "uploadFile";
//	}
//	
//	@PostMapping(path = "/uploadFile.controller")
//	@ResponseBody
//	public String processFileUpload(@RequestParam("myFiles") MultipartFile multipartFile, HttpServletRequest request) throws IllegalStateException, IOException {
//		String fileName = multipartFile.getOriginalFilename();
//		System.out.println("fileName: " + fileName);
//		
//		//create an uploadTempDir's directory in case of not inexistency
//		String saveFileDir = request.getSession().getServletContext().getRealPath("/") + "uploadTempDir\\";  //getServletContext():取得環境根目錄
//		File saveDirFile = new File(saveFileDir);
//		saveDirFile.mkdirs();
//		
//		String saveFilePath = saveFileDir + fileName;
//		File savefile = new File(saveFilePath);
//		multipartFile.transferTo(savefile);
//		System.out.println("saveFilePath: " + saveFilePath);
//		
//		if(fileName!=null && fileName.length()!=0) {
//			savePicture(fileName, saveFilePath);
//			return "save success";
//		}else {
//			return "save failed";
//		}
//	}
//	
//	@Autowired
//	private PictureService pictureService;
//
//	private void savePicture(String fileName, String saveFilePath) throws IOException {
//		Picture p1 = new Picture();
//		p1.setFilename(fileName);
//		
//		InputStream is1 = new FileInputStream(saveFilePath);
//		byte[] b = new byte[is1.available()];
//		is1.read(b);
//		is1.close();
//		
//		p1.setPicture(b); //存到javabean裡面
//		pictureService.insert(p1); //存到資料庫
//		
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//}
