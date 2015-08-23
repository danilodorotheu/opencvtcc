package br.com.opencv.main;

import javax.swing.SwingUtilities;

import org.opencv.core.Core;

import br.com.opencv.view.TelaPrincipal;

public class Main {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new TelaPrincipal();
			}
		});
	}
}
