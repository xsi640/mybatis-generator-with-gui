package com.suyang.mbg.context;

import java.util.Objects;

public class Location {
    private double width = -1;
    private double height = -1;
    private double top = -1;
    private double left = -1;

    public Location() {
    }

    public Location(double width, double height, double top, double left) {
        this.width = width;
        this.height = height;
        this.top = top;
        this.left = left;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getTop() {
        return top;
    }

    public void setTop(double top) {
        this.top = top;
    }

    public double getLeft() {
        return left;
    }

    public void setLeft(double left) {
        this.left = left;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.width, width) == 0 &&
                Double.compare(location.height, height) == 0 &&
                Double.compare(location.top, top) == 0 &&
                Double.compare(location.left, left) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, top, left);
    }

    public static Location Empty() {
        return new Location();
    }
}
