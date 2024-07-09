package com.zsd.springboottest.utils;

import com.spire.ocr.OcrScanner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ReadImage {
    public static void main(String[] args) throws Exception {
        //指定依赖文件的路径
        String dependencies = "src/main/resources/dependencies";
        //指定要需要扫描的图片的路径
        String imageFile = "D:/3.png";
        //指定输出文件的路径
        String outputFile = "读取图片.txt";

        //创建OcrScanner对象，并设置其依赖文件路径
        OcrScanner scanner = new OcrScanner();
        scanner.setDependencies(dependencies);

        //扫描指定的图像文件
        scanner.scan(imageFile);

        //获取扫描的文本内容
        String scannedText = scanner.getText().toString();

        //创建输出文件对象
        File output = new File(outputFile);
        //如果输出文件已经存在，则将其删除
        if (output.exists()) {
            output.delete();
        }
        //创建BufferedWriter对象来将扫描的文本内容写入输出文件
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        writer.write(scannedText);
        writer.close();
    }
}
