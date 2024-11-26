package com.itheima.mp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Song Laixiong
 * @Create: 2024-11-26
 * @Description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PageDTO<T> {

    private Long total;

    private Long pages;

    private List<T> list;
}
