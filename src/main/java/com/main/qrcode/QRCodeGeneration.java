package com.main.qrcode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGeneration {

	
	public static void generateQRCode(String text,int width, int height, String filepath) throws WriterException, IOException
	{
		QRCodeWriter codeWriter=new QRCodeWriter();
		
		BitMatrix bitMatrix=codeWriter.encode(text,BarcodeFormat.QR_CODE, width, height);
		
		Path path=FileSystems.getDefault().getPath(filepath);
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		
	}
	
	
	
	
	public static byte[] getQrCodeImage(String text, int width,int height) throws WriterException, IOException
	{
		
		QRCodeWriter codeWriter=new QRCodeWriter();
		BitMatrix bitMatrix= codeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
		ByteArrayOutputStream pngoutput=new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngoutput);
	    byte[] pngdata=pngoutput.toByteArray();
				
		return pngdata;
		
	}
	
	public static String readQRCode(String filePath) throws IOException, NotFoundException {
        // 1. Read the image file
        BufferedImage bufferedImage = ImageIO.read(new File(filePath));

        // 2. Convert to ZXing-compatible format
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        // 3. Decode the QR Code
        Result result = new MultiFormatReader().decode(bitmap);

        return result.getText();
    }
	
	
	
	
	
}
