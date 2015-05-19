package com.huemedia.cms.component;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.ContentHandler;

public class FileUtil {
	
	 public static final String FILE_NAME = "fileName";
	 public static final String MIME = "mime";
	 public static final String EXTENSION = "extension";
	
	
	public static String storeImage(String dir, byte[] img, String filename) {
		String finalfile = null;
		try {
			//InputStream in = new ByteArrayInputStream(img);
			//BufferedImage bImageFromConvert = ImageIO.read(in);

			finalfile = makeDir(dir);
			System.out.println("Filepath : " + finalfile);
			File file = new File(finalfile);
			file.mkdirs();
			finalfile = makeFile(finalfile, filename);
			System.out.println("Final filepath : " + finalfile);
			file = new File(finalfile);
			
			if (file.createNewFile()) {
				FileUtils.writeByteArrayToFile(file, img);
			} else {
				System.out.println("File already exists!");
			}
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finalfile;
	}

	public static String storeImage(String dir, MultipartFile multipartFile) {
		String finalfile = null;
		try {
			finalfile = makeDir(dir);
			System.out.println("Filepath : " + finalfile);
			File file = new File(finalfile);
			file.mkdirs();
			finalfile = makeFile(finalfile, multipartFile.getOriginalFilename());
			System.out.println("Final filepath : " + finalfile);
			file = new File(finalfile);
			if (file.createNewFile()) {
				multipartFile.transferTo(file);
				System.out.println("Done");
			} else {
				System.out.println("File already exists!");
			}
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return finalfile;
	}

	
	public static byte[] readFullPath(String path,String ext){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		byte[] imageData=null;
		try {
			
			FileInputStream fileinputstream = new FileInputStream(path);
             byte[] b = new byte[8192]; 
             int n; 
             while(( n = fileinputstream.read( b )) > 0 ) {  
            	 baos.write( b, 0, n ); 
              }
             fileinputstream.close(); 
             imageData = baos.toByteArray();//getResizedImage(out.toByteArray(), 100,100, 99);  
             baos.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageData;
	}
	
	public static byte[] readFile(String path) {
		String workingDir = System.getProperty("user.home");
		String file = "";
		String your_os = System.getProperty("os.name").toLowerCase();

		if (your_os.indexOf("win") >= 0) {
			file = workingDir+"\\"+ path.replace("/", "\\");
		} else if (your_os.indexOf("nix") >= 0 || your_os.indexOf("nux") >= 0) {
			file = workingDir+"/" + path;
		} else {
			file = workingDir + "{others}" + path;
		}
		System.out.println("Final file path : " + file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] imageData=null;
		try {
			BufferedImage originalImage = ImageIO.read(new File(file));
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			imageData = baos.toByteArray();
			baos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageData;
	}
	
	public static Map<String,String> getInfo(String path){
		Map<String,String> map = new HashMap<String,String>();
		
		try{
		File f=new File(path);
		  InputStream is = new FileInputStream(f);
		 // InputStream bufferedIn = new BufferedInputStream(is);
		  //TikaInputStream stream = TikaInputStream.get(f);
		  ContentHandler contenthandler = new BodyContentHandler();
	      Metadata metadata = new Metadata();
	      metadata.set(Metadata.RESOURCE_NAME_KEY, f.getName());
	      Parser parser = new AutoDetectParser();
	      // OOXMLParser parser = new OOXMLParser();
	      ParseContext context = new ParseContext();
	      context.set(Locale.class, Locale.US);
	      parser.parse(is, contenthandler, metadata,context);
	      
	      String fileName = metadata.get(Metadata.RESOURCE_NAME_KEY);
	      String extension = "";

	      int i = fileName.lastIndexOf('.');
	      int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

	      if (i > p) {
	          extension = fileName.substring(i+1);
	      }
	      map.put("mime", metadata.get(Metadata.CONTENT_TYPE));
	      map.put("fileName", metadata.get(Metadata.RESOURCE_NAME_KEY));
	      map.put("extension", extension);
	      
	      
		}catch(Exception e){
			e.printStackTrace();
		}
	      return map;
	}

	private static String makeDir(String dir) {

		String workingDir = System.getProperty("user.home");
		System.out.println("Working File :" + workingDir);
		String finalfile = "";
		String your_os = System.getProperty("os.name").toLowerCase();
		if (your_os.indexOf("win") >= 0) {
			finalfile = workingDir + "\\" + dir.replace("/", "\\");
		} else if (your_os.indexOf("nix") >= 0 || your_os.indexOf("nux") >= 0) {
			finalfile = workingDir + "/" + dir;
		} else {
			finalfile = workingDir + "{others}" + dir;
		}
		return finalfile;
	}

	private static String makeFile(String path, String fileName) {
		String finalfile = "";
		String your_os = System.getProperty("os.name").toLowerCase();
		if (your_os.indexOf("win") >= 0) {
			finalfile = path + "\\" + fileName;
		} else if (your_os.indexOf("nix") >= 0 || your_os.indexOf("nux") >= 0) {
			finalfile = path + "/" + fileName;
		} else {
			finalfile = path + "{others}" + fileName;
		}
		return finalfile;
	}
}
