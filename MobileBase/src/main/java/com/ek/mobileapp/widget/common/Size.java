package com.ek.mobileapp.widget.common;

public class Size {

    public Size(int i, int j) {
        width = i;
        height = j;
    }

    public int getArea() {
        return width * height;
    }

    public Size scaleByHeight(Size size) {
        Size size1;
        if (width > 0 && height > 0 && size.width > 0 && size.height > 0)
            size1 = new Size((width * size.height) / height, size.height);
        else
            size1 = null;
        return size1;
    }

    public Size scaleByWidth(Size size) {
        Size size1;
        if (width > 0 && height > 0 && size.width > 0 && size.height > 0) {
            int i = (height * size.width) / width;
            size1 = new Size(size.width, i);
        } else {
            size1 = null;
        }
        return size1;
    }

    public Size scaleTo(Size size, boolean flag) {
        Size size1;
        Size size2;
        size1 = scaleByWidth(size);
        size2 = scaleByHeight(size);
        if (size2 != null) {
            if (size1 == null) {
                size1 = size2;
            } else {
                int i = size1.getArea();
                int j = size2.getArea();
                if (flag) {
                    if (i < j)
                        size1 = size2;
                } else if (i > j)
                    size1 = size2;
            }

        }
        return size1;
    }

    public String toString() {
        return (new StringBuilder(String.valueOf(width))).append(" ").append(height).toString();
    }

    public int height;
    public int width;
}
