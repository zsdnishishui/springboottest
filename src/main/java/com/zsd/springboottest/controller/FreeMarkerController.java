package com.zsd.springboottest.controller;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/freeMarker")
public class FreeMarkerController {
    @Autowired
    private Configuration configuration;

    @GetMapping("/demo")
    public String demo(Model model) {
        model.addAttribute("name", "student");
        return "demo";
    }

    @GetMapping("/demoStr")
    @ResponseBody
    public Map demoStr() throws IOException, TemplateException {
        Writer out = new StringWriter();
        Map res = new HashMap();
        Map params = new HashMap();
        params.put("name", "student");
        //freemarker的模板对象，获取模板
        Template template = configuration.getTemplate("demo.ftl");
        template.process(params, out);
        out.flush();
        res.put("str", out.toString());
        return res;
    }

    @GetMapping("/createPdf")
    @ResponseBody
    public void createPdf() throws IOException, TemplateException, DocumentException, com.lowagie.text.DocumentException {
        Writer out = new StringWriter();
        Map params = new HashMap();
        params.put("name", "student");
        //freemarker的模板对象，获取模板
        Template template = configuration.getTemplate("demo.ftl");
        template.process(params, out);
        out.flush();
        createPdf(out.toString(), "d://test.pdf");
    }

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

        //设置字体
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
