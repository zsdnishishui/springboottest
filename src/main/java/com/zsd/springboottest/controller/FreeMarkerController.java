package com.zsd.springboottest.controller;

import com.itextpdf.text.DocumentException;
import com.zsd.springboottest.utils.Html2Pdf;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        model.addAttribute("name", "在看");
        return "demo";
    }

    @GetMapping("/demoStr")
    @ResponseBody
    public Map demoStr() throws IOException, TemplateException {
        Writer out = new StringWriter();
        Map res = new HashMap();
        Map params = new HashMap();
        params.put("name", "在看");
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
        params.put("name", "在看");
        //freemarker的模板对象，获取模板
        Template template = configuration.getTemplate("demo.ftl");
        template.process(params, out);
        out.flush();
        Html2Pdf.createPdf(out.toString(), "d://test.pdf");
    }


}
