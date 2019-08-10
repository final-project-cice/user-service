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
public class ThreeGenericValuesDetailsRequestModel<A, B, C> {

    private A firstValue;
    private B secondValue;
    private C thirdValue;

}
