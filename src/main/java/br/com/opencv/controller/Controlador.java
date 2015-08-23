package br.com.opencv.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JOptionPane;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import br.com.opencv.model.ConversorMatriz;
import br.com.opencv.model.ProcessamentoMatriz;
import br.com.opencv.model.Tecnicas;
import br.com.opencv.view.TelaPrincipal;

public class Controlador {

	private TelaPrincipal telaPrincipal;
	private Mat matrizCam, matrizProcessada;
	private ProcessamentoMatriz processamentoMatriz;
	private boolean executando;

	public Controlador(TelaPrincipal telaPrincipal) {
		this.telaPrincipal = telaPrincipal;
	}
	

	public void iniciaCaptura() {
		if (executando) {
			JOptionPane.showMessageDialog(null,
					"A captura ja esta em execucao!");
			return;
		}
		new Thread(new CapturarVideo()).start();
	}

	public void desligaCaptura() {
		executando = false;
	}

	public void sair() {
		executando = false;
		System.exit(0);
	}

	public void atualizaVideos() {
		telaPrincipal.getPainelCaptura().repaint();
	}

	public void limpaVideos() {
		telaPrincipal.getPainelCaptura().setimagem(null);
	}

	public void incrementaFrames() {
		telaPrincipal.incrementaFrames();
	}

	public void resetaFrames() {
		telaPrincipal.resetaFrames();
	}

	public Mat aplicaTecnicas(Mat matriz) throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		return processamentoMatriz.aplicaTecnicas(matriz, telaPrincipal
				.getTelaTecnicas().getListaMetodosSelecaoPi());
	}

	public void preencheListaTecnicas() {
		Method[] metodosPi = Tecnicas.class.getDeclaredMethods();
		int totalMetodos = metodosPi.length;
		for (int i = 0; i < totalMetodos; i++)
			telaPrincipal.getTelaTecnicas().adicionaMetodoEscolhaPi(
					metodosPi[i].getName());
	}

	private class CapturarVideo implements Runnable {

		@Override
		public void run() {
			matrizCam = new Mat();
			matrizProcessada = new Mat();
			BufferedImage imagemProcessada;
			processamentoMatriz = new ProcessamentoMatriz();
			ConversorMatriz conversorMatriz = new ConversorMatriz();
			VideoCapture capturaVideo = new VideoCapture(0);

			if (capturaVideo.open(0)) {

				executando = true;

				while (executando) {

					if (!capturaVideo.read(matrizCam)) {
						JOptionPane.showMessageDialog(telaPrincipal,
								"Houve um erro na imagem capturada!", "Erro",
								JOptionPane.ERROR_MESSAGE);
						break;
					}

					try {
						matrizProcessada = aplicaTecnicas(matrizCam);
					} catch (NoSuchMethodException | SecurityException
							| IllegalAccessException | IllegalArgumentException
							| InvocationTargetException ex) {
						JOptionPane.showMessageDialog(telaPrincipal,
								"Houve um erro no processamento de imagem!",
								"Erro", JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
						break;
					}

					try {
						imagemProcessada = conversorMatriz
								.matrizParaImagem(matrizProcessada);
					} catch (IOException | IllegalArgumentException ex) {
						JOptionPane
								.showMessageDialog(
										telaPrincipal,
										"Houve um erro ao converter a matriz para imagem!",
										"Erro", JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
						break;
					}

					telaPrincipal.getPainelCaptura()
							.setimagem(imagemProcessada);
					incrementaFrames();
					atualizaVideos();
				}

			}
			resetaFrames();
			limpaVideos();
			capturaVideo.release();
		}
	}

}
