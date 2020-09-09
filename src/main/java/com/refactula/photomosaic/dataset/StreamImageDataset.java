package com.refactula.photomosaic.dataset;

import com.refactula.photomosaic.image.ArrayImage;

import java.io.EOFException;
import java.io.IOException;

/**
 * Abstract ImageDataset for such datasets that by nature access images sequentially
 * and require extra time for random access.
 */
public abstract class StreamImageDataset extends AbstractImageDataset {

    private int currentIndex = -1;

    public StreamImageDataset(){
    }

    public StreamImageDataset(int size, int imageWidth, int imageHeight) {
        super(size, imageWidth, imageHeight);
    }

    @Override
    public boolean load(int index, ArrayImage destination) throws IOException {
        if (currentIndex != index) {
            changePosition(index);
            currentIndex = index;
        }

        currentIndex++;
        try {
            readTo(destination);
            return true;
        } catch (EOFException e) {
            return false;
        }
    }

    /**
     * Move the current dataset pointer into given position.
     */
    protected abstract void changePosition(int index) throws IOException;

    /**
     * Reads the next image from dataset.
     * @param destination
     */
    protected abstract void readTo(ArrayImage destination) throws IOException;

}
