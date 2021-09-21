package top.cuizilin.blog.utils;

import java.util.Random;

public class PhotoUtils {
    public static String createNewPhoto(){
        String genericAddr = "http://unsplash.it/800/515?image=";
        Random random = new Random(System.nanoTime());
        Integer photoId = random.nextInt(1000);
        return genericAddr + photoId;
    }
}
