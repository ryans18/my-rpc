package com.ryans.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Author：Ryans
 * Date：Created in 2023/8/16 23:17
 * Introduction：
 */
@Data
@AllArgsConstructor
public class Invocation implements Serializable {

    private String interfaceName;
    private String methodName;
    // 参数类型
    private Class<?>[] parameterTypes;
    private Object[] parameters;

}