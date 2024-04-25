package com.wei.weiojcodesandbox;


import com.wei.weiojcodesandbox.model.ExecuteCodeRequest;
import com.wei.weiojcodesandbox.model.ExecuteCodeResponse;

/**
 * 代码沙箱公共接口
 */
public interface CodeSandbox {
    /**
     * 执行目标代码
     * @param executeCodeRequest
     * @return
     */
     ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
