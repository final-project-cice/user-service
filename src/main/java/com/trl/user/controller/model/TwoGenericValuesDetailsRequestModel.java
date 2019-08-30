package com.trl.user.controller.model;

import java.util.Objects;

/**
 *
 */
public class TwoGenericValuesDetailsRequestModel<A, B> {

    private A firstValue;
    private B secondValue;

    public TwoGenericValuesDetailsRequestModel() {
    }

    public TwoGenericValuesDetailsRequestModel(A firstValue, B secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public A getFirstValue() {
        return firstValue;
    }

    public B getSecondValue() {
        return secondValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwoGenericValuesDetailsRequestModel<?, ?> that = (TwoGenericValuesDetailsRequestModel<?, ?>) o;
        return firstValue.equals(that.firstValue) &&
                secondValue.equals(that.secondValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstValue, secondValue);
    }

    @Override
    public String toString() {
        return "TwoGenericValuesDetailsRequestModel{" +
                "firstValue=" + firstValue +
                ", secondValue=" + secondValue +
                '}';
    }

}
