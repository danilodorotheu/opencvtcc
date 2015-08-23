package br.com.opencv.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import org.opencv.core.Mat;

public class ProcessamentoMatriz {

	private Tecnicas tecnicas;

	public ProcessamentoMatriz() {
		tecnicas = new Tecnicas();
	}

	public Mat aplicaTecnicas(Mat matriz, Enumeration<String> listaTecnicas)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Class<?> tipoParametros[] = new Class[1];
		tipoParametros[0] = Mat.class;
		Object argumentos[] = new Object[1];
		while (listaTecnicas.hasMoreElements()) {
			argumentos[0] = matriz;
			Method metodo = Tecnicas.class.getMethod(
					listaTecnicas.nextElement(), tipoParametros);
			matriz = (Mat) metodo.invoke(tecnicas, argumentos);
		}
		return matriz;
	}

}
