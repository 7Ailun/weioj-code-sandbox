package com.wei.weiojcodesandbox;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.UUID;
import com.wei.weiojcodesandbox.model.ExecuteCodeRequest;
import com.wei.weiojcodesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JavaNativeCodeSandbox implements CodeSandbox {
    public static final String GLOBAL_CODE_DIR_NAME = "tempCode";
    public static final String GLOBAL_JAVA_FILE_NAME = "Main.java";
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest)  {
        // 从 resource 目录下读取文件中的内容
        String code = ResourceUtil.readStr("test.SimpleCodePath/Main.java", StandardCharsets.UTF_8);
        log.info("code：" + code);
        String userDir = System.getProperty("user.dir");
        log.info("userDir：" + userDir);
        String globalCodePathName = userDir + File.separator + GLOBAL_CODE_DIR_NAME;
        // 判断全局代码目录是否存在
        if(!FileUtil.exist(globalCodePathName)) {
            FileUtil.mkdir(globalCodePathName);
        }

        // 把用户的代码隔离存放
        String userCodeParentName = globalCodePathName + File.separator + UUID.randomUUID();
        String userCodePath = userCodeParentName + File.separator + GLOBAL_JAVA_FILE_NAME;
        File userCodeFile = FileUtil.writeString(code, userCodePath, "UTF-8");

        // 编译代码
        String compileCmd = String.format("javac -encoding utf-8 %s", userCodeFile.getAbsolutePath());
        log.info("编译命令：" + compileCmd);
        try {
            Process process = Runtime.getRuntime().exec(compileCmd);
            int result = process.waitFor();
            if(result == 0) {
                log.info("编译成功");
            } else {
                log.info("编译失败");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
