package co.edu.unal.voltammetry.fitting;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @source: http://www.sc.ehu.es/sbweb/fisica/cursoJava/numerico/regresion1/regresion1.htm
 */

public class PolinomioRegresion {

	private double[] x; // datos
	private double[] y;
	private int nDatos;
	double[][] m; // matriz de los coeficientes
	double[] t; // términos independientes
	public double[] a; // polinomio a[0]+a[1]·x+a[2]·x2+...
	public int grado; // grado del polinomio

	public PolinomioRegresion(List<Double[]> input, int grado) {

		this.x = new double[input.size()];
		this.y = new double[input.size()];

		for (int i = 0; i < input.size(); i++) {

			Double[] doubles = input.get(i);
			this.x[i] = doubles[0];
			this.y[i] = doubles[1];
		}

		this.x = x;
		this.y = y;
		nDatos = x.length;
		this.grado = grado;
		t = new double[grado + 1];
		m = new double[grado + 1][grado + 1];
		a = new double[grado + 1];
	}

	public PolinomioRegresion(double[] x, double[] y, int grado) {
		this.x = x;
		this.y = y;
		nDatos = x.length;
		this.grado = grado;
		t = new double[grado + 1];
		m = new double[grado + 1][grado + 1];
		a = new double[grado + 1];
	}

	private void coeficientes() {
		double[] s = new double[2 * grado + 1];
		double suma;
		for (int k = 0; k <= 2 * grado; k++) {
			suma = 0.0;
			for (int i = 0; i < nDatos; i++) {
				suma += potencia(x[i], k);
			}
			s[k] = suma;
		}
		for (int k = 0; k <= grado; k++) {
			suma = 0.0;
			for (int i = 0; i < nDatos; i++) {
				suma += potencia(x[i], k) * y[i];
			}
			t[k] = suma;
		}
		for (int i = 0; i <= grado; i++) {
			for (int j = 0; j <= grado; j++) {
				m[i][j] = s[i + j];
			}
		}
	}

	private double potencia(double base, int exp) {
		double producto = 1.0;
		for (int i = 0; i < exp; i++) {
			producto *= base;
		}
		return producto;
	}

	// procedimiento de Seidel
	public List<Double[]> calculaPolinomio() {
		coeficientes();
		// matriz
		double aux;
		for (int i = 0; i <= grado; i++) {
			aux = m[i][i];
			for (int j = 0; j <= grado; j++) {
				m[i][j] = -m[i][j] / aux;
			}
			t[i] = t[i] / aux;
			m[i][i] = 0.0;
		}
		// aproximación inicial
		double[] p = new double[grado + 1];
		p[0] = t[0];
		for (int i = 1; i <= grado; i++) {
			p[i] = 0.0;
		}
		// aproximaciones sucesivas
		double error = 0.0, maximo = 0.0;
		do {
			maximo = 0.0;
			for (int i = 0; i <= grado; i++) {
				a[i] = t[i];
				for (int j = 0; j < i; j++) {
					a[i] += m[i][j] * a[j];
				}
				for (int j = i + 1; j <= grado; j++) {
					a[i] += m[i][j] * p[j];
				}
				error = Math.abs((a[i] - p[i]) / a[i]);
				if (error > maximo)
					maximo = error;
			}
			for (int i = 0; i <= grado; i++) {
				p[i] = a[i];
			}
		} while (maximo > 0.001);

		List<Double[]> result = new ArrayList<>();

		for (int i = 0; i < x.length; i++) {

			Double[] doubles = new Double[2];
			doubles[0] = this.x[i];
			doubles[1] = this.y[i];

			result.add(doubles);
		}
		
		return result;

	}
}