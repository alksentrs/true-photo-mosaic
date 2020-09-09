package com.refactula.photomosaic.app;

import com.refactula.photomosaic.image.AwtImage;
import com.refactula.photomosaic.utils.progress.PeriodicEvent;
import com.refactula.photomosaic.utils.progress.ProgressEstimator;
import com.refactula.photomosaic.utils.progress.TimeMeter;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class BuildTinyDataset {
    public static void main(String[] args) throws Exception {

        PeriodicEvent periodicLog = new PeriodicEvent(3, TimeUnit.SECONDS);
        periodicLog.update();
        ProgressEstimator progressEstimator = new ProgressEstimator(TimeMeter.start());

        String format = "png";
        String path = "imageSet";
        String pathTiny = "imageSetTiny";

        File filePath = new File(path);
        if (!filePath.isDirectory()) filePath.mkdirs();

        File filePathTiny = new File(pathTiny);
        if (!filePathTiny.isDirectory()) filePathTiny.mkdirs();

        File [] filesTiny = filePathTiny.listFiles();
        for (int i = 0; i < filesTiny.length; i++) filesTiny[i].delete();

        File dir = new File(path);
        File [] files = dir.listFiles();

        for (int i = 0; i < files.length; i++) {
            System.out.println(i);

            AwtImage awtImage = new AwtImage(ImageIO.read(files[i]));
            AwtImage awtImageTiny = awtImage.scale(0.5);

            String fullName = files[i].getName();
            String name = fullName.substring(0,fullName.length()-4);

            File fileTiny = new File(pathTiny+File.separator+name+"."+format);


            awtImageTiny.save(format,fileTiny);

            if (periodicLog.update()) {
                double progress = i / (double) files.length;
                long estimationSeconds = TimeUnit.MILLISECONDS.toSeconds(progressEstimator.estimateRemainingTime(progress));
                System.out.format("Progress = %.3f, remaining time = %ds\n", progress, estimationSeconds);
            }
        }
    }

}