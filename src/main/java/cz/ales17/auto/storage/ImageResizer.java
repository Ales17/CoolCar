package cz.ales17.auto.storage;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.image.*;
import java.awt.*;
public class ImageResizer {

    public static InputStream resizeImage(InputStream inputStream) throws IOException {

        int targetWidth = 800, targetHeight = 600;
        // Read the original image from the input stream
        BufferedImage originalImage = ImageIO.read(inputStream);

        // Calculate the new dimensions while maintaining the aspect ratio
        double originalWidth = originalImage.getWidth();
        double originalHeight = originalImage.getHeight();
        double aspectRatio = originalWidth / originalHeight;

        if (originalWidth > originalHeight) {
            // Landscape
            targetHeight = (int) (targetWidth / aspectRatio);
        } else {
            // Portrait
            targetWidth = (int) (targetHeight * aspectRatio);
        }

        // Create a new buffered image with the new dimensions
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g.dispose();

        // Write the resized image to a byte array output stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "jpg", outputStream); // You can change the format as needed

        // Convert the byte array output stream to an input stream
        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
