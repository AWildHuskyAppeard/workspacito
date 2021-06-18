package tw.group5.model;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity @Table(name = "picture")
@Component("picture")
public class Picture {
	
	@Id @Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "FILENAME")
	private String filename;
	
	@JsonIgnore
	@Column(name = "PICTURE")
	private Blob picture;
	
	@Column(name = "MIMETYPE")
	private String mimetype;
	

	//回傳blob，編碼成base64
	@Transient
	String picturesString;
	

	public String getPicturesString() {
		//data:[mimeType];base64,xxxxxxx
		
		StringBuffer result = new StringBuffer("data:" + mimetype + ";base64,");
		try {
			InputStream is = picture.getBinaryStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			int len = 0;
			byte[] b = new byte[is.available()];
			while((len = is.read(b))!= -1) {
				baos.write(b, 0, len);
			}
			byte[] bytes = baos.toByteArray();
			Base64.Encoder be = Base64.getEncoder();
			result.append(new String(be.encode(bytes)));			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	

	public void setPicturesString(String picturesString) {
		this.picturesString = picturesString;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
	}
	
	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}
	

}
