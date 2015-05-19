package com.huemedia.cms.component;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.tika.exception.TikaException;
import org.apache.tika.mime.MimeTypeException;
import org.imgscalr.Scalr;
import org.xml.sax.SAXException;

public class ImageUtil {

	public static byte[] resize(InputStream is, int height, int width) {

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			BufferedImage originalImage = ImageIO.read(is);
			BufferedImage thumbnail = Scalr.resize(originalImage,
					Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT, height, width,
					Scalr.OP_ANTIALIAS);
			ImageIO.write(thumbnail, "gif", os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// InputStream is = new ByteArrayInputStream(os.toByteArray());

		return os.toByteArray();
	}

	

	public static void main(String[] args) throws IOException, SAXException, TikaException, MimeTypeException  {
		/**
		 * try {
		 * 
		 * byte[] imageInByte; BufferedImage originalImage = ImageIO.read(new
		 * File( "d:/hugo/no-photo.jpg"));
		 * 
		 * BufferedImage thumbnail = Scalr.resize(originalImage,
		 * Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT, 26, 26,
		 * Scalr.OP_ANTIALIAS);
		 * 
		 * ImageIO.write(thumbnail, "jpg", new File( "d:/hugo/no-photo_s.jpg"));
		 * 
		 * } catch (IOException e) { System.out.println(e.getMessage()); }
		 **/
		
		Map<String,String> map =FileUtil.getInfo("d:/hugo/urs.doc");
		System.out.println(map.get(FileUtil.FILE_NAME));
		System.out.println(map.get(FileUtil.EXTENSION));
		System.out.println(map.get(FileUtil.MIME));
	     
	      
	     

	}

}
