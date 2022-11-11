package com.fairytaler.fairytalecat.common.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Pagenation<T> {
    private List<T> content;
    private Long totalElement;
    private int totalPages;
    private int size;
    private int number;
    public Pagenation(){}
}
