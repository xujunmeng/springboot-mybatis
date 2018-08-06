package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author james
 * @date 2018/8/6
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionParamModel {

    private String string;

}
