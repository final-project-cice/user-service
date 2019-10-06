package com.trl.userservice.controller.model;

import java.util.Objects;

/**
 *
 */
public class OneGenericValueDetailsRequestModel<A> {

    private A value;

    public OneGenericValueDetailsRequestModel() {
    }

    public OneGenericValueDetailsRequestModel(A value) {
        this.value = value;
    }

    public A getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OneGenericValueDetailsRequestModel<?> that = (OneGenericValueDetailsRequestModel<?>) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "OneGenericValueDetailsRequestModel{" +
                "value=" + value +
                '}';
    }

}
