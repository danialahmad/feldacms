package com.huemedia.cms.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface ReportService {
   public void generateReport(HttpServletResponse response,String reportName, Map<String, String> map) throws MalformedURLException, IOException;
}
