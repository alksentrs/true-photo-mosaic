package com.refactula.photomosaic.dataset;

import com.refactula.photomosaic.image.ArrayImage;
import com.refactula.photomosaic.image.AwtImage;
import com.refactula.photomosaic.image.ColorChannel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class LocalImageDataset extends StreamImageDataset {

    private File [] files;
    private int index;

    public LocalImageDataset(String path) {
        File dir = new File(path);
        files = dir.listFiles();

        if (null!=files) {
            setSize(files.length);
            AwtImage awtImage = null;
            try {
                awtImage = new AwtImage(ImageIO.read(files[0]));
                setImageWidth(awtImage.getWidth());
                setImageHeight(awtImage.getHeight());
                System.out.println("Found "+files.length+" images of "+awtImage.getWidth()+"x"+awtImage.getHeight());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void changePosition(int index) throws IOException {
        this.index = index;
    }

    @Override
    protected void readTo(ArrayImage destination) throws IOException {

        BufferedImage bufferedImage = ImageIO.read(files[index]);

        for (int i=0; i<bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                int rgb = bufferedImage.getRGB(i, j);
                destination.set(ColorChannel.RED,i,j,ColorChannel.RED.extract(rgb));
                destination.set(ColorChannel.GREEN,i,j,ColorChannel.GREEN.extract(rgb));
                destination.set(ColorChannel.BLUE,i,j,ColorChannel.BLUE.extract(rgb));
            }
        }
    }

    @Override
    public void close() throws IOException {

    }
}
