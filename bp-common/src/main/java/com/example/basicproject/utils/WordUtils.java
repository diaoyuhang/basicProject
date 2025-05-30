package com.example.basicproject.utils;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class WordUtils {
    public static void main(String[] args) throws Exception {
        // 读取 DOCX 文件
//        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\刁宇航\\Desktop\\24电3+3（2）电子学籍卡 (2)(1).docx");
//        FileOutputStream fileOutputStream = new FileOutputStream("output.pdf");
//        XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream);
//        PdfOptions options = PdfOptions.create();
//        PdfConverter.getInstance().convert(xwpfDocument, fileOutputStream, options);
//        fileInputStream.close();

        try (PDDocument document = PDDocument.load(new File("output.pdf"))) {
            PDFRenderer renderer = new PDFRenderer(document);

            // 逐页渲染为图片
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 300); // 300 DPI
                File output = new File("output_page_" + (i + 1) + ".png");
                ImageIO.write(image, "PNG", output);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
