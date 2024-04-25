package com.wei.weiojcodesandbox;

import com.wei.weiojcodesandbox.model.ExecuteCodeRequest;
import com.wei.weiojcodesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class JavaNativeCodeSandboxTest {

    @Test
    void executeCode() {
        CodeSandbox codeSandbox = new JavaNativeCodeSandbox();
        ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        System.out.println("executeCodeResponse = " + executeCodeResponse);
    }
}