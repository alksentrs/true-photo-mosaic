package com.refactula.photomosaic.dataset;

public abstract class AbstractImageDataset implements ImageDataset {

    private int size;
    private int imageWidth;
    private int imageHeight;

    public AbstractImageDataset() {
    }

    public AbstractImageDataset(int size, int imageWidth, int imageHeight) {
        this.size = size;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int getImageWidth() {
        return imageWidth;
    }

    @Override
    public int getImageHeight() {
        return imageHeight;
    }

}
