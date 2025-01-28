package com.develop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HoubiResp {
    private List<HoubiDataResp> data;
    private String status;
    private String ts;
}
