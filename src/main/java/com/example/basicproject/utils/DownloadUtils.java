package com.example.basicproject.utils;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.InputStream;

public class DownloadUtils {
    private final static Logger log = LoggerFactory.getLogger(DownloadUtils.class);

    public static void exportExcel(HttpServletResponse response, Workbook workbook,String fileName) throws IOException {
        try {
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            workbook.write(response.getOutputStream());
            response.getOutputStream().flush();

        } catch (Exception e) {
            log.error(fileName + " 文件下载失败" + e.getMessage(), e);
            throw new RuntimeException(fileName + " 文件下载失败" + e.getMessage());
        } finally {
            workbook.close();
            response.getOutputStream().close();
        }

    }

    public static void download(HttpServletResponse response, InputStream inputStream, String fileName) throws IOException {
        try {
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

            byte[] buffer = new byte[8096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            log.error(fileName + " 文件下载失败" + e.getMessage(), e);
            throw new RuntimeException(fileName + " 文件下载失败" + e.getMessage());
        } finally {
            inputStream.close();
            response.getOutputStream().close();
        }
    }

    public static void showPic(HttpServletResponse response, InputStream inputStream) throws IOException {
        try {
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);

            byte[] buffer = new byte[8096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            log.error("showPic" + e.getMessage(), e);
            throw new RuntimeException("showPic" + e.getMessage());
        } finally {
            inputStream.close();
            response.getOutputStream().close();
        }
    }
}
