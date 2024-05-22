package com.zsd.springboottest.controller;

import com.itextpdf.text.DocumentException;
import com.zsd.springboottest.utils.Html2Pdf;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {
    @Autowired
    private TemplateEngine templateEngine;

    @GetMapping("/demo")
    public String demo(Model model) {
        model.addAttribute("name", "在看");
        return "demo2";
    }

    @GetMapping("/demoStr")
    @ResponseBody
    public Map demoStr() {
        Map res = new HashMap();
        Context context = new Context();
        context.setVariable("name", "炸死你个");
        String template = templateEngine.process("demo2.html", context);

        res.put("str", template);
        return res;
    }

    @GetMapping("/createPdf")
    @ResponseBody
    public void createPdf() throws IOException, TemplateException, DocumentException, com.lowagie.text.DocumentException {
        Context context = new Context();
        context.setVariable("name", "炸死你个");
        String template = templateEngine.process("demo2.html", context);
        Html2Pdf.createPdf(template, "d://test2.pdf");
    }
}
