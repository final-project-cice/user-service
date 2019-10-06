package com.trl.userservice.controller.model;

import java.util.Objects;

/**
 *
 */
public class ThreeGenericValuesDetailsRequestModel<A, B, C> {

    private A firstValue;
    private B secondValue;
    private C thirdValue;

    public ThreeGenericValuesDetailsRequestModel() {
    }

    public ThreeGenericValuesDetailsRequestModel(A firstValue, B secondValue, C thirdValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
    }

    public A getFirstValue() {
        return firstValue;
    }

    public B getSecondValue() {
        return secondValue;
    }

    public C getThirdValue() {
        return thirdValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThreeGenericValuesDetailsRequestModel<?, ?, ?> that = (ThreeGenericValuesDetailsRequestModel<?, ?, ?>) o;
        return firstValue.equals(that.firstValue) &&
                secondValue.equals(that.secondValue) &&
                thirdValue.equals(that.thirdValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstValue, secondValue, thirdValue);
    }

    @Override
    public String toString() {
        return "ThreeGenericValuesDetailsRequestModel{" +
                "firstValue=" + firstValue +
                ", secondValue=" + secondValue +
                ", thirdValue=" + thirdValue +
                '}';
    }

}
