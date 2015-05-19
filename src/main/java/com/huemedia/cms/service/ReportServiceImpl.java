package com.huemedia.cms.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
	
	private static final String HOST="192.168.0.10:8080";
	//private static final String HOST="115.85.128.59:8181";
	private static final String FOLDER="reports";
	@Override
	public void generateReport(HttpServletResponse response,String reportName, Map<String, String> map) throws IOException {
		 Set set = map.entrySet();
		 Iterator it = set.iterator();
	     String param="";   
		 while (it.hasNext()) {
			 Map.Entry<String, String> entry =
		                (Entry<String, String>) it.next();
			 param = param+"&"+entry.getKey()+"="+entry.getValue();
	     }
		 
		    
		 
		    String downloadurl1 = "http://"+HOST+"/jasperserver/flow.html?_flowId=viewReportFlow&reportUnit=/"+FOLDER+"/"+reportName+param+"&j_username=jasperadmin&j_password=jasperadmin&output=pdf";
		  //  downloadurl1=  URLEncoder.encode(downloadurl1);
		    URL url = new URL(downloadurl1);
	        response.setContentType("application/pdf");
	        response.setHeader("Cache-Control", "maxage=1");
	        response.setHeader("Content-disposition", "inline;filename="+reportName+".pdf");
	        
	        URLConnection connection = url.openConnection();
	        InputStream stream = connection.getInputStream(); 
	        ServletOutputStream outs = response.getOutputStream();
	        int len;
	        byte[] buf = new byte[1024];
	        while ((len = stream.read(buf)) > 0) {
	            outs.write(buf, 0, len);
	        }
	        outs.flush();
	        outs.close();
	}

}
