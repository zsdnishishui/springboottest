package com.zsd.springboottest.utils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 如果未将tessdata放在根目录下需要指定绝对路径
 * instance.setDatapath("the absolute path of tessdata");
 * 对应的动态连接库下载地址：
 * Downloads are available here https://github.com/ollydev/libtesseract/releases
 * <p>
 * orc 离线识别
 **/
public class OcrUtil {

    private static final String TEST_RESOURCES_LANGUAGE_PATH = "src/main/resources/tessdata";

    private static void tess4j() throws TesseractException, IOException {


        ITesseract instance = new Tesseract();

        instance.setDatapath(TEST_RESOURCES_LANGUAGE_PATH);

        //如果需要识别英文之外的语种，需要指定识别语种，并且需要将对应的语言包放进项目中
        instance.setLanguage("chi_sim");

        // 指定识别图片
        File imgDir = new File("D:/2.png");
        if (imgDir.exists()) {
            long startTime = System.currentTimeMillis();
            BufferedImage bufferedImage = ImageIO.read(imgDir);
            String ocrResult = instance.doOCR(bufferedImage);
            // 输出识别结果
            System.out.println("OCR Result: \n" + ocrResult + "\n 耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        } else {
            System.out.println("====");
        }

    }

    public static void main(String[] args) throws TesseractException, IOException {
        tess4j();
    }
}
