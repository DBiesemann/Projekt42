/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Objects;

/**
 *
 * @author namibj
 * @param <T> The type of the encapsulated object.
 */
public class GenericDoubleDouble<T> {

    final T obj;
    final double i;
    final double j;

    /**
     *
     * @param obj The object to be encapsulated.
     * @param i The first double to be encapsulated.
     * @param j The second double to be encapsulated.
     */
    public GenericDoubleDouble(T obj, double i, double j) {
        this.obj = obj;
        this.i = i;
        this.j = j;
    }

    public T getObj() {
        return obj;
    }

    public double getI() {
        return i;
    }

    public double getJ() {
        return j;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.obj);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.i) ^ (Double.doubleToLongBits(this.i) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.j) ^ (Double.doubleToLongBits(this.j) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GenericDoubleDouble<?> other = (GenericDoubleDouble<?>) obj;
        if (!Objects.equals(this.obj, other.obj)) {
            return false;
        }
        if (Double.doubleToLongBits(this.i) != Double.doubleToLongBits(other.i)) {
            return false;
        }
        if (Double.doubleToLongBits(this.j) != Double.doubleToLongBits(other.j)) {
            return false;
        }
        return true;
    }

}
