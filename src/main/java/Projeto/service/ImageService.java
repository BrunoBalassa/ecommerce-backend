package Projeto.service;

import Projeto.service.execptions.FileExeception;
import org.apache.commons.io.FilenameUtils;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageService {
    public BufferedImage getJpgImageFromFile(MultipartFile uploadFile) {
            String ext = FilenameUtils.getExtension(uploadFile.getOriginalFilename());
            if(!"png".equals(ext) && !"jpg".equals(ext)){
                throw  new FileExeception("Somente imag PNG e JPG são permitidas");
            }
            try {
                BufferedImage img = ImageIO.read(uploadFile.getInputStream());
                if("png".equals(ext)){
                    img = pngToJpg(img);
                }
                return img;
            }catch (IOException e){
                throw  new FileExeception("Erro ao ler arquivo");
            }
    }

    public BufferedImage pngToJpg(BufferedImage img) {
        BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        jpgImage.createGraphics().drawImage(img, 0,0, Color.WHITE, null);
        return jpgImage;
    }
    public InputStream getInputStream(BufferedImage img, String extension) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(img, extension, os);
            return new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            throw new FileExeception("Erro ao ler arquivo");
        }
    }
}