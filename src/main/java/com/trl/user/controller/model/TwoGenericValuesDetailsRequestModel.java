package com.trl.user.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TwoGenericValuesDetailsRequestModel<A, B> {

    private A firstValue;
    private B secondValue;

}
