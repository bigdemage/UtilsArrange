package common.utils;

import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.mp4.Mp4MetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName FileDetailsUtil
 * @Deacription windows文件详细信息
 * @Author wrx
 * @Date 2021/1/15/015 9:38
 * @Version 1.0
 **/
public class FileDetailsUtil {

    public static void main(String[] args) throws IOException, ImageProcessingException {
        File img = new File("D:/001.mp4");
        System.out.println("File Name:" + img.getName());

        Metadata metadata = Mp4MetadataReader.readMetadata(img);
        System.out.println("Directory Count: "+metadata.getDirectoryCount());
        System.out.println();

        //输出所有附加属性数据
        for (Directory directory : metadata.getDirectories()) {
            System.out.println("******\t" + directory.getName() + "\t******");
            for (Tag tag : directory.getTags()) {
                System.out.println(tag.getTagName() + ":" + tag.getDescription());
            }
            System.out.println();
            System.out.println();
        }

    }
}
