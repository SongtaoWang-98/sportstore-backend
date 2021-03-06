package com.stewart.sports_store.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GroupVO {
    private String name;
    private List<CategoryVO> categories;
}
