package com.refactula.photomosaic.app;

import com.refactula.photomosaic.dataset.ImageDataset;
import com.refactula.photomosaic.dataset.LocalImageDataset;
import com.refactula.photomosaic.image.ArrayImage;
import com.refactula.photomosaic.image.AverageColor;
import com.refactula.photomosaic.image.ColorChannel;
import com.refactula.photomosaic.utils.progress.PeriodicEvent;
import com.refactula.photomosaic.utils.progress.ProgressEstimator;
import com.refactula.photomosaic.utils.progress.TimeMeter;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;


public class BuildIndexApp {

    public static void main(String[] args) throws Exception {

        PeriodicEvent periodicLog = new PeriodicEvent(3, TimeUnit.SECONDS);
        periodicLog.update();
        ProgressEstimator progressEstimator = new ProgressEstimator(TimeMeter.start());

        String pathImageDataset = "imageSet";
        File file = new File(pathImageDataset);
        if (!file.isDirectory()) file.mkdirs();

        try (
                DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("index.bin")));
                ImageDataset dataset = new LocalImageDataset(pathImageDataset);
        ) {
            ArrayImage buffer = dataset.createImageBuffer();
            AverageColor averageColor = new AverageColor();

            for (int i = 0; i < dataset.size(); i++) {
                if (!dataset.load(i, buffer)) {
                    throw new RuntimeException("Dataset size is invalid");
                }
                averageColor.compute(buffer);
                for (ColorChannel channel : ColorChannel.values()) {
                    output.writeByte(averageColor.get(channel));
                }

                if (periodicLog.update()) {
                    double progress = i / (double) dataset.size();
                    long estimationSeconds = TimeUnit.MILLISECONDS.toSeconds(progressEstimator.estimateRemainingTime(progress));
                    System.out.format("Progress = %.3f, remaining time = %ds\n", progress, estimationSeconds);
                }
            }
        }
    }

}
