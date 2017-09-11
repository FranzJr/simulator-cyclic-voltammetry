package co.edu.unal.voltammetry.fitting;

import java.awt.*;

public class MiCanvas extends Canvas {
	int n; // número de datos
	double x[];
	double y[];
	// valor máximo y mínimo de x
	double xMin, xMax, yMax, yMin;
	PolinomioRegresion polinomio;
	// escalas
	double escalaX, escalaY;
	// origen
	int orgX, orgY;
	int cAncho, cAlto;

	public MiCanvas() {
		n = 0;
		setBackground(Color.black);
	}

	void setDatos(double[] x, double[] y, int grado) {
		this.x = x;
		this.y = y;
		n = x.length;
		polinomio = new PolinomioRegresion(x, y, grado);
		polinomio.calculaPolinomio();
		repaint();
	}

	public void paint(Graphics g) {
		if (polinomio != null) {
			dibujaEjes(g);
			dibujaPuntos(g);
			dibujaPolinomio(g);
		} else {
			g.setColor(Color.white);
			g.drawString("Introducir al menos tres pares de datos", 10, 50);
		}

	}

	void escalasAutomaticas() {
		xMax = -10000;
		xMin = 10000;
		yMax = -10000;
		yMin = 10000;

		for (int i = 0; i < n; i++) {
			if (x[i] > xMax)
				xMax = x[i];
			if (x[i] < xMin)
				xMin = x[i];
			if (y[i] > yMax)
				yMax = y[i];
			if (y[i] < yMin)
				yMin = y[i];
		}
		if (xMin > 0)
			xMin = 0.0;
		if (xMax < 0)
			xMax = 0.0;
		if (yMin > 0)
			yMin = 0.0;
		if (yMin > polinomio.a[0])
			yMin = polinomio.a[0];
		if (yMax < 0)
			yMax = 0.0;
	}

	void dibujaEjes(Graphics g) {
		escalasAutomaticas();
		int w = getSize().width;
		int h = getSize().height;
		cAlto = g.getFontMetrics().getHeight();
		cAncho = g.getFontMetrics().stringWidth("0");
		escalaX = (double) (w - 4 * cAncho) / Math.abs(xMax - xMin);
		escalaY = (double) (h - 4 * cAlto) / Math.abs(yMax - yMin);
		orgX = 2 * cAncho + (int) (Math.abs(xMin) * escalaX);
		orgY = 2 * cAlto + (int) (Math.abs(yMax) * escalaY);
		g.setColor(Color.magenta);
		g.drawLine(orgX, 0, orgX, h);
		g.drawLine(0, orgY, w, orgY);

	}

	void dibujaPuntos(Graphics g) {
		int x1, y1, y2, y3;
		for (int i = 0; i < n; i++) {
			x1 = orgX + (int) (x[i] * escalaX);
			y1 = orgY - (int) (y[i] * escalaY);
			g.setColor(Color.white);
			g.fillOval(x1 - 2, y1 - 2, 4, 4);
		}
	}

	void dibujaPolinomio(Graphics g) {
		int x1, y1, x2, y2;
		x1 = orgX + (int) (xMin * escalaX);
		y1 = orgY - (int) (f(xMin) * escalaY);
		g.setColor(Color.cyan);
		double dx = (xMax - xMin) / 100;
		for (double x = xMin; x < xMax; x += dx) {
			x2 = orgX + (int) (x * escalaX);
			y2 = orgY - (int) (f(x) * escalaY);
			g.drawLine(x1, y1, x2, y2);
			x1 = x2;
			y1 = y2;
		}
		// coeficientes del polinomio
		g.setColor(Color.white);
		String texto;
		for (int i = 0; i <= polinomio.grado; i++) {
			texto = "a[" + i + "]=" + String.valueOf((double) Math.round(polinomio.a[i] * 10000) / 10000);
			g.drawString(texto, orgX + cAncho, (i + 1) * cAlto);
		}

	}

	double f(double x) {
		double y = polinomio.a[0];
		for (int i = 1; i <= polinomio.grado; i++) {
			y += polinomio.a[i] * potencia(x, i);
		}
		return y;
	}

	double potencia(double x, int n) {
		double y = 1.0;
		for (int i = 1; i <= n; i++) {
			y *= x;
		}
		return y;
	}
}