package com.refactula.photomosaic.app;

import com.refactula.photomosaic.dataset.EightyMillionTinyImages;
import com.refactula.photomosaic.dataset.ImageDataset;
import com.refactula.photomosaic.image.ArrayImage;
import com.refactula.photomosaic.utils.PeriodicEvent;
import com.refactula.photomosaic.utils.ProgressEstimator;
import com.refactula.photomosaic.utils.TimeMeter;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DevelopmentApp {

    public static final int DATASET_SIZE = 100000;

    public static void main(String[] args) throws Exception {
        downloadImagesIntoFile();
    }

    private static void downloadImagesIntoFile() throws IOException {
        TimeMeter timeMeter = TimeMeter.start();
        ProgressEstimator progressEstimator = new ProgressEstimator(timeMeter);
        PeriodicEvent periodicLog = new PeriodicEvent(3, TimeUnit.SECONDS);
        periodicLog.update();

        try (
                ImageDataset dataset = new EightyMillionTinyImages();
                DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("dataset.bin")))
        ) {
            ArrayImage buffer = new ArrayImage(dataset.getImageWidth(), dataset.getImageHeight());
            for (int i = 0; i < DATASET_SIZE; i++) {
                dataset.load(i, buffer);
                buffer.writeTo(output);

                if (periodicLog.update()) {
                    long remainingEstimation = progressEstimator.estimateRemainingTime(i / (double) DATASET_SIZE);
                    System.out.println("Time spend: " + TimeUnit.MILLISECONDS.toSeconds(timeMeter.get()) + "s"
                            + ", remaining time: " + TimeUnit.MILLISECONDS.toSeconds(remainingEstimation) + "s"
                    );
                }
            }
        }

        timeMeter.stop();

        System.out.println("Time spend: " + timeMeter.get());
    }

}
