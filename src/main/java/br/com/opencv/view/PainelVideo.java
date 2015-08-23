package br.com.opencv.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class PainelVideo extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage imagemTemp;

	public PainelVideo() {
		super();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (imagemTemp == null) return;
		g.drawImage(imagemTemp, 0, 0, imagemTemp.getWidth(), imagemTemp.getHeight(), null);
	}

	public void setimagem(BufferedImage imagemNova) {
		this.imagemTemp = imagemNova;
	}

	public BufferedImage getImagem() {
		return imagemTemp;
	}

}
