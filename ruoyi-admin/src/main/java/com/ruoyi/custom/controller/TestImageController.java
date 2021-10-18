package com.ruoyi.custom.controller;


import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruoyi.common.core.controller.BaseController;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

@Controller
@RequestMapping("/test/returnimage")
public class TestImageController extends BaseController {

    @Value("${ruoyi.prefix}")
    private String prefix;

    @GetMapping()
    public String testImage() {
        String python = "D:/app/Anaconda/envs/python364/python.exe";
        String script = prefix + "park_test.py";
        String net = prefix + "data/net";
        String pickle = prefix + "data/spot_dict.pickle";
        String image = prefix + "test_images/scene1380.jpg";
        String show = prefix + "data/pic.jpg";
        ArrayList<String> types = new ArrayList<>();
        types.add("predict");
        types.add("init");
        ArrayList<String> result = new ArrayList<>();

        String cmd = python + " " + script + " " + types.get(0) + " " + net + " " + pickle + " " + image + " " + show;

        //控制台执行流程
        Runtime run = Runtime.getRuntime();//返回与当前 Java 应用程序相关的运行时对象
        try {
            java.lang.Process p = run.exec(cmd);// 启动另一个进程来执行命令
            BufferedInputStream in = new BufferedInputStream(p.getInputStream());
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));

            String lineStr;
            while ((lineStr = inBr.readLine()) != null)
                //获得控制台结果
                result.add(lineStr);
            //检查命令是否执行失败。
            if (p.waitFor() != 0) {
                if (p.exitValue() == 1)//p.exitValue()==0表示正常结束，1：非正常结束
                    System.err.println("命令执行失败!");
            }
            inBr.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //分割结果字符串
        String[] arr = result.get(0).split(" ");

        // 预测结果
        String imageName = arr[0];
        String cntEmpty = arr[1];
        String cntAll = arr[2];

        return "1";
    }
}
