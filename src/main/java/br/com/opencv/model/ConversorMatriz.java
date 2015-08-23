/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.opencv.model;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

public class ConversorMatriz {

	public BufferedImage matrizParaImagem(Mat matriz) throws IOException,
			IllegalArgumentException {
		MatOfByte matrizBytes = new MatOfByte();
		Imgcodecs.imencode(".jpg", matriz, matrizBytes);
		byte[] bytesMatriz = matrizBytes.toArray();
		InputStream fluxoEntrada = new ByteArrayInputStream(bytesMatriz);
		return ImageIO.read(fluxoEntrada);
	}

	public Mat imagemParaMatriz(BufferedImage imagemTemp) {
		byte[] bytesImagem = ((DataBufferByte) imagemTemp.getRaster()
				.getDataBuffer()).getData();
		Mat matriz = new Mat(imagemTemp.getHeight(), imagemTemp.getWidth(),
				CvType.CV_8UC3);
		matriz.put(0, 0, bytesImagem);
		return matriz;
	}

}
