package com.zsd.springboottest.controller;

import com.itextpdf.text.DocumentException;
import com.zsd.springboottest.utils.Html2Pdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/demo")
    public String demo(Model model) {
        List list = Arrays.asList(new int[]{150, 160, 170, 180, 190, 200, 210});
        model.addAttribute("data", list);
        model.addAttribute("name", "在看");
        model.addAttribute("imgBase64", getImg());
        return "demo2";
    }

    @GetMapping("/demoStr")
    @ResponseBody
    public Map demoStr() {
        Map res = new HashMap();
        Context context = new Context();
        List list = Arrays.asList(new int[]{150, 160, 170, 180, 190, 200, 210});
        context.setVariable("name", "炸死你个");
        context.setVariable("data", list);
        String template = templateEngine.process("demo2.html", context);

        res.put("str", template);
        return res;
    }

    @GetMapping("/createPdf")
    @ResponseBody
    public void createPdf() throws IOException, DocumentException, com.lowagie.text.DocumentException {


        Context context = new Context();
        List list = Arrays.asList(new int[]{150, 160, 170, 180, 190, 200, 210});
        context.setVariable("name", "炸死你个");
        context.setVariable("data", list);
        context.setVariable("imgBase64", getImg());
        String template = templateEngine.process("demo2.html", context);
        Html2Pdf.createPdf(template, "d://test2.pdf");
    }

    /**
     * echarts 服务参考的是
     * https://github.com/mosliu/echarts5-canvas-ssr
     *
     * @return
     */
    public String getImg() {
        String json = "{\"width\":800,\"height\":500,\"option\":{\"backgroundColor\":\"#fff\",\"xAxis\":{\"type\":\"category\",\"data\":[\"Mon\",\"Tue\",\"Wed\",\"Thu\",\"Fri\",\"Sat\",\"Sun\"]},\"yAxis\":{\"type\":\"value\"},\"series\":[{\"data\":[820,932,901,934,1290,1330,1320],\"type\":\"line\"}]}}";
        // 创建请求实体
        HttpEntity<String> requestEntity = new HttpEntity<>(json);

        // 发送POST请求并获取响应结果
        String url = "http://127.0.0.1:8081";
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, byte[].class);
        //获取entity中的数据
        byte[] body = responseEntity.getBody();
        // 转base64
        //获取JDK8里的编码器Base64.Encoder转为base64字符
        String base64 = Base64.getEncoder().encodeToString(body);
        return "data:image/png;base64," + base64;
    }
}
