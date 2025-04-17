package com.example.basicproject.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideShow;
import org.apache.xmlbeans.XmlException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PPTUtils {
    public static void main(String[] args) throws IOException, OpenXML4JException, XmlException {
        XMLSlideShow ppt = new XMLSlideShow(OPCPackage.open(new FileInputStream("C:\\Users\\刁宇航\\Desktop\\aaa.pptx")));
        PDDocument pdfDoc = new PDDocument();

        Dimension pageSize = ppt.getPageSize();
        for (XSLFSlide slide : ppt.getSlides()) {
            BufferedImage image = new BufferedImage(pageSize.width, pageSize.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = image.createGraphics();
            slide.draw(graphics);
            graphics.dispose();

            // 添加到 PDF
            PDPage page = new PDPage(new PDRectangle(pageSize.width, pageSize.height));
            pdfDoc.addPage(page);

            PDImageXObject pdImage = PDImageXObject.createFromByteArray(
                    pdfDoc, toByteArray(image, "png"), "slide"
            );
            try (PDPageContentStream contents = new PDPageContentStream(pdfDoc, page)) {
                contents.drawImage(pdImage, 0, 0, pageSize.width, pageSize.height);
            }
        }
        pdfDoc.save("output.pdf");
        pdfDoc.close();
        ppt.close();
    }

    private static byte[] toByteArray(BufferedImage image, String format) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, format, baos);
        return baos.toByteArray();
    }
}
