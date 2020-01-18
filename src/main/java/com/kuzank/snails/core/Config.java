package com.kuzank.snails.core;


import java.util.HashMap;
import java.util.Map;

public class Config {
    static Map<String, String> code = new HashMap<>();

    public static String getMsg(int num) {
        if (code.isEmpty()) {
            code.put("301", "身份令牌认证失败,请重新登录!");
            code.put("302", "操作令牌认证失败或超时!");
            code.put("303", "请申请操作令牌后再操作!");
            code.put("304", "令牌配置错误,请联系管理员!");
            code.put("305", "令牌已使用,请勿重复操作!");
            code.put("401", "没有相关操作权限,请联系管理员!");
            code.put("402", "相关令牌权限不存在!");
            code.put("403", "资源已被其他人员占用,请稍后重试!");

            code.put("601", "流程不存在!");
            code.put("602", "流程实例不存在!");
            code.put("603", "流程参数异常:当前轨迹为空!");
            code.put("604", "对不起,你不是当前流程节点操作者!");
            code.put("605", "流程参数异常:当前节点为空!");
            code.put("606", "流程参数异常:节点流转配置为空!");
            code.put("607", "流程参数异常:找不到合适的流转出口!");
            code.put("608", "流程参数异常:下一个节点信息为空!");
            code.put("609", "流程参数异常:流程的表单为空!");
            code.put("610", "流程参数异常:下一个节点操作者为空!");
            code.put("611", "流程参数异常:流程实例与流程对象不匹配!");
            code.put("612", "对不起,当前节点不允许手动提交!");

        }
        String msg = code.get(String.valueOf(num));
        return msg;
    }

}
