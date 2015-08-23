package br.com.opencv.model;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.BackgroundSubtractorKNN;
import org.opencv.video.BackgroundSubtractorMOG2;
import org.opencv.video.Video;

public class Tecnicas {

	private BackgroundSubtractorKNN bsKNN;
	private BackgroundSubtractorMOG2 bsMOG2;

	public Tecnicas() {
		bsKNN = Video.createBackgroundSubtractorKNN(500, 800.0, true);
		bsMOG2 = Video.createBackgroundSubtractorMOG2(500, 32, true);
	}

	// ELIMINACAO DE RUIDO COM SUAVIZACAO
	public Mat medianBlur(Mat matriz) {
		Mat matrizMedian = new Mat();
		Imgproc.medianBlur(matriz, matrizMedian, 3);
		return matrizMedian;
	}

	// SUVIZACAO
	public Mat gaussianBlur(Mat matriz) {
		Mat matrizGaussian = new Mat();
		Imgproc.GaussianBlur(matriz, matrizGaussian, new Size(0, 0), 10, 1);
		return matrizGaussian;
	}

	// LIMIARIZACAO
	public Mat threshold(Mat matriz) {
		Mat matrizThreshold = new Mat();
		Imgproc.threshold(matriz, matrizThreshold, 127, 255,
				Imgproc.THRESH_BINARY);
		return matrizThreshold;
	}

	// DETECCAO DE BORDAS
	public Mat laplacian(Mat matriz) {
		Mat matrizLaplacian = new Mat();
		Imgproc.Laplacian(matriz, matrizLaplacian, 5, 3, 1, 1);
		return matrizLaplacian;
	}

	// DETECCAO DE BORDAS
	public Mat canny(Mat matriz) {
		Mat matrizCanny = new Mat();
		Imgproc.Canny(matriz, matrizCanny, 150, 250, 3, false);
		return matrizCanny;
	}

	// DETECCAO DE BORDAS
	/*
	 * public Mat sobel(Mat matriz) { Mat matrizSobel = new Mat();
	 * Imgproc.Sobel(matriz, matrizSobel, 1, 1, 1); return matrizSobel; }
	 */

	// FILTRO MORFOLOGICO
	public Mat dilate(Mat matriz) {
		Mat matrizDilate = new Mat();
		Imgproc.dilate(matriz, matrizDilate, matrizDilate);
		return matrizDilate;
	}

	// FILTRO MORFOLOGICO
	public Mat erode(Mat matriz) {
		Mat matrizErode = new Mat();
		Imgproc.erode(matriz, matrizErode, matrizErode);
		return matrizErode;
	}

	/**
	 * Metodo de subtracao de Objeto na Matriz: Utiliza o
	 * backgroundSubtractionKNN para subtrair um objeto em uma matriz, alem de
	 * realizar o processo de aprendizado
	 * 
	 * @param matriz
	 * @return {@link Mat}
	 * @author thiago
	 */
	public Mat backgroundSubtractionKNN(Mat matriz) {
		Mat matrizBS = new Mat(matriz.size(), CvType.CV_8UC1);
		bsKNN.apply(matriz, matrizBS, Configuracao.taxaAprendizadoKNN);
		return matrizBS;
	}

	/**
	 * Metodo de subtracao de Objeto na Matriz: Utiliza o
	 * backgroundSubtractionMOG2 para subtrair um objeto em uma matriz, alem de
	 * realizar o processo de aprendizado
	 * 
	 * @param matriz
	 * @return {@link Mat}
	 * @author thiago
	 */
	public Mat backgroundSubtractionMOG2(Mat matriz) {
		Mat matrizBS = new Mat(matriz.size(), CvType.CV_8UC1);
		bsMOG2.apply(matriz, matrizBS, Configuracao.taxaAprendizadoMOG2);
		return matrizBS;
	}

	// CONVERSAO ESPACO CINZA PARA O ESPACO RGB
	public Mat colorGrayToRGB(Mat matriz) {
		Mat matrizCvtColor = new Mat();
		Imgproc.cvtColor(matriz, matrizCvtColor, Imgproc.COLOR_GRAY2BGRA, 0);
		return matrizCvtColor;
	}

	// CONVERSAO ESPACO RGB PARA O ESPACO CINZA
	public Mat colorRGBToGray(Mat matriz) {
		Mat matrizCvtColor = new Mat();
		Imgproc.cvtColor(matriz, matrizCvtColor, Imgproc.COLOR_BGRA2GRAY, 0);
		return matrizCvtColor;
	}

	// FILTROS MORFOLOGICOS
	public Mat morphologyEx(Mat matriz) {

		Mat erosao = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
				new Size(8, 8));
		Mat dilatacao = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
				new Size(8, 8));

		Mat abreElemento = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
				new Size(3, 3), new Point(1, 1));
		Mat fechaElemento = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
				new Size(7, 7), new Point(3, 3));

		Imgproc.morphologyEx(matriz, matriz, Imgproc.MORPH_OPEN, erosao);
		Imgproc.morphologyEx(matriz, matriz, Imgproc.MORPH_OPEN, dilatacao);
		Imgproc.morphologyEx(matriz, matriz, Imgproc.MORPH_OPEN, abreElemento);
		Imgproc.morphologyEx(matriz, matriz, Imgproc.MORPH_CLOSE, fechaElemento);

		return matriz;
	}

}
