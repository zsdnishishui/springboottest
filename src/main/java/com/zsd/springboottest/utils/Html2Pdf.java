package com.zsd.springboottest.utils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;

public class Html2Pdf {
    public static void createPdf(String content, String dest) throws IOException, DocumentException, com.lowagie.text.DocumentException {
//        // step 1
//        Document document = new Document();
//        // step 2
//        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
//        // step 3
//        document.open();
//        // step 4
//        XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
//        fontImp.register("Fonts/simfang.ttf");
//        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
//                new ByteArrayInputStream(content.getBytes()), null, Charset.forName("UTF-8"), fontImp);
//        // step 5
//        document.close();

        ITextRenderer render = new ITextRenderer();

        //设置字体至关重要
        ITextFontResolver fontResolver = render.getFontResolver();
//        fontResolver.addFont(FONT_S, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        fontResolver.addFont("Fonts/simfang.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//        fontResolver.addFont(FONT_C, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        // 解析html生成pdf
        render.setDocumentFromString(content);
        render.layout();
        render.createPDF(new FileOutputStream(dest));
        render.finishPDF();

    }
}
