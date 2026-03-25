package com.main.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.main.qrcode.QRCodeGeneration;

@RestController
public class QRCodeController {

	
	private static final String QR_CODE_PATH="./src/main/resources/QRCode.png";
	
	
	
	
	@GetMapping("/generateqr/{codeText}/{width}/{height}")
	public void downlode(@PathVariable("codeText")String codeText,@PathVariable("width")int width,@PathVariable("height")int height) throws WriterException, IOException
	{
		
		QRCodeGeneration.generateQRCode(codeText, width, height, QR_CODE_PATH);
		
	}
	
	@GetMapping("/readqr")
	public String readqr() throws WriterException, IOException, NotFoundException
	{
		
		return QRCodeGeneration.readQRCode(QR_CODE_PATH);
		
	}
	
	@GetMapping("/gencode/{codeText}/{width}/{height}")	
	public ResponseEntity<byte[]> genertaeQRCode(@PathVariable("codeText")String codeText,@PathVariable("width")int width,@PathVariable("height")int height) throws WriterException, IOException
	{
		
		return ResponseEntity.status(HttpStatus.OK).body(QRCodeGeneration.getQrCodeImage(codeText, width, height));
		
		
	}
	
	
	
	
}
