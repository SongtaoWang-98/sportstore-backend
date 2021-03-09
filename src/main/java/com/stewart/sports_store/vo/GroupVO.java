package com.stewart.sports_store.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupVO {
    private String name;
    private List<CategoryVO> categories;
}
