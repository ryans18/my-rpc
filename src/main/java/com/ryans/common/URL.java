package com.ryans.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Author：Ryans
 * Date：Created in 2023/8/16 22:09
 * Introduction：
 */
@Data
@AllArgsConstructor
public class URL implements Serializable {

    private static final Long serialVersionUID = 10001L;

    private String host;
    private int port;

}