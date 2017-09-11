package co.edu.unal.voltammetry.simulator;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.voltammetry.dto.InputSimulationDto;


/**
 * @source: kado@sys.wakayama-u.ac.jp http://www.wakayama-u.ac.jp/~kado/
 * @date: Last Modified at 2004/04/05
 * @url: http://www.wakayama-u.ac.jp/
 */
public class Simulation {

	private double k0;
	private double c0;
	private double D0;
	private double vu0;
	private double vl0;
	private double v0;
	private int cn0;
	private double k;
	private double c;
	private double D;
	private double vu;
	private double vl;
	private double v;
	private int cn;
	private boolean StartFlag;
	private boolean VFlag;
	private int x0;
	private int y0;
	private int xmax;
	private int ymax;
	private int x;
	private int y;
	private double vmax;
	private double imax;
	private int vscale;
	private double iscale;

	public Simulation() {
		// REQUEST DATA
		k0 = 0.10000000000000001D;
		c0 = 1.0D;
		D0 = 1.0000000000000001E-05D;
		vu0 = 400D;
		vl0 = -400D;
		v0 = 100D;
		cn0 = 1;
		// PRIVATE DATA
		k = k0;
		c = c0 * 0.001D;
		D = D0;
		vu = vu0 * 0.001D;
		vl = vl0 * 0.001D;
		v = v0 * 0.001D;
		cn = cn0;
		StartFlag = false;
		VFlag = true;
		x0 = 120;
		y0 = 150;
		xmax = 100;
		ymax = 100;
		vmax = 500D;
		imax = 0.29999999999999999D;
		vscale = 200;
		iscale = 0.10000000000000001D;
	}

	public List<Double[]> play(InputSimulationDto inputData) {
		k = Double.valueOf(inputData.getRateConstantK()).doubleValue();
		c = Double.valueOf(inputData.getConcentrationRedoxC()).doubleValue() * 0.001D;
		D = Double.valueOf(inputData.getDiffusionConstantD()).doubleValue();
		vu = Math.max(0.20000000000000001D, Double.valueOf(inputData.getUpperPotentialVU()).doubleValue() * 0.001D);
		vl = Double.valueOf(inputData.getLowerPotentialVL()).doubleValue() * 0.001D;
		v = Double.valueOf(inputData.getScanRateV()).doubleValue() * 0.001D;
		cn = Integer.valueOf(inputData.getCycleNumberCN()).intValue();
		
		return cvCalc(k, c, D, vu, vl, v, cn);
	}
	
	private List<Double[]> cvCalc(double d, double d1, double d2, double d3, double d4, double d5, int i) {
		
		List<Double[]> result = new ArrayList<>();
		
		double d6 = 0.0D;
		double d8 = 0.0D;
		double d10 = 8.3140000000000001D;
		double d11 = 96485D;
		double d12 = 298.14999999999998D;
		double d13 = 0.5D;
		double d14 = 1.0D;
		int j = 1;
		double d15 = 0.0D;
		double d16 = d3;
		double d17 = d4;
		int l = (int) (Math.abs(d16 * 1000D - d17 * 1000D) * 2D);
		double d18 = Math.abs(((d16 - d17) / d5) * 2D);
		int i1 = l / 2;
		double d19 = d18 / (double) l;
		double d20 = (2D * (d16 - d17)) / (double) l;
		double d21 = Math.sqrt(d2 * d19 * 2D);
		int j1 = (int) (6D * Math.sqrt(0.5D * (double) (i * l + 1))) + 5;
		double ad[] = new double[j1];
		double ad1[] = new double[j1];
		double ad2[] = new double[j1];
		double ad3[] = new double[j1];
		double d22 = (d2 * d19) / (d21 * d21);
		double d23 = d22;

		for (int k1 = 0; k1 < j1; k1++) {
			ad1[k1] = 1.0D;
			ad[k1] = 1.0D;
			ad3[k1] = 0.0D;
			ad2[k1] = 0.0D;
		}

		int i2 = -1;
		double d24 = d16;
		double d25 = 0.0D;
		double d26 = 0.0D;
		double d27 = 0.0D;
		double d28 = 0.0D;
		
		for (int l1 = 1; l1 <= i * l + 1; l1++) {
			int j2 = (int) (6D * Math.sqrt(d22 * (double) l1));
			double d29 = (-d * (d19 / d21) * Math.exp((-d13 * (double) j * d11 * (d24 - d15)) / d10 / d12) * ad1[0]
					+ d * (d19 / d21) * Math.exp(((1.0D - d13) * (double) j * d11 * (d24 - d15)) / d10 / d12) * ad3[0])
					/ (1.0D + (d * (d19 / d21) * Math.exp((-d13 * (double) j * d11 * (d24 - d15)) / d10 / d12))
							/ (2D * d22)
							+ (d * (d19 / d21) * Math.exp(((1.0D - d13) * (double) j * d11 * (d24 - d15)) / d10 / d12))
									/ (2D * d23));
			ad[0] = ad1[0] + d22 * (ad1[1] - ad1[0]) + d29;
			ad2[0] = (ad3[0] + d23 * (ad3[1] - ad3[0])) - d29;
			double d7 = d24 * 1000D;
			double d9 = (double) j * d11 * d14 * d1 * d29 * (d21 / d19);
			if (l1 <= l + 1 && d9 < d25) {
				d25 = d9;
				d27 = d7;
			}
			if (l1 <= l + 1 && d26 < d9) {
				d26 = d9;
				d28 = d7;
			}
			for (int k2 = 1; k2 <= j2; k2++) {
				ad[k2] = ad1[k2] + d22 * ((ad1[k2 - 1] - 2D * ad1[k2]) + ad1[k2 + 1]);
				ad2[k2] = ad3[k2] + d23 * ((ad3[k2 - 1] - 2D * ad3[k2]) + ad3[k2 + 1]);
			}

			for (int l2 = 0; l2 <= j2; l2++) {
				ad1[l2] = ad[l2];
				ad3[l2] = ad2[l2];
				if ((int) Math.IEEEremainder(l1 - 1, l / 10) == 0) {
					x = (int) ((double) xmax * (d7 / vmax));
					y = -(int) ((double) ymax * (d9 / imax));

					char c1 = '\226';
					byte byte0 = 0;
					if (l1 >= (i * l) / 2 + 1)
						byte0 = 120;
					x = l2 + 1;
				}
			}

			x = (int) ((double) xmax * (d7 / vmax));
			y = -(int) ((double) ymax * (d9 / imax));

			if (x < xmax) {
				Double[] item = new Double[2];
				item[0] = d7;
				item[1] = d9;
				result.add(item);
//				System.out.println(d7 + "," + d9);
			}
			if (l1 - 1 != 0 && (int) Math.IEEEremainder(l1 - 1, i1) == 0)
				i2 = -i2;
			d24 += (double) i2 * d20;
			
			
		}
		
		return result;
	}

	public double getK0() {
		return k0;
	}

	public void setK0(double k0) {
		this.k0 = k0;
	}

	public double getC0() {
		return c0;
	}

	public void setC0(double c0) {
		this.c0 = c0;
	}

	public double getD0() {
		return D0;
	}

	public void setD0(double d0) {
		D0 = d0;
	}

	public double getVu0() {
		return vu0;
	}

	public void setVu0(double vu0) {
		this.vu0 = vu0;
	}

	public double getVl0() {
		return vl0;
	}

	public void setVl0(double vl0) {
		this.vl0 = vl0;
	}

	public double getV0() {
		return v0;
	}

	public void setV0(double v0) {
		this.v0 = v0;
	}

	public int getCn0() {
		return cn0;
	}

	public void setCn0(int cn0) {
		this.cn0 = cn0;
	}

	public double getK() {
		return k;
	}

	public void setK(double k) {
		this.k = k;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}

	public double getD() {
		return D;
	}

	public void setD(double d) {
		D = d;
	}

	public double getVu() {
		return vu;
	}

	public void setVu(double vu) {
		this.vu = vu;
	}

	public double getVl() {
		return vl;
	}

	public void setVl(double vl) {
		this.vl = vl;
	}

	public double getV() {
		return v;
	}

	public void setV(double v) {
		this.v = v;
	}

	public int getCn() {
		return cn;
	}

	public void setCn(int cn) {
		this.cn = cn;
	}

	public boolean isStartFlag() {
		return StartFlag;
	}

	public void setStartFlag(boolean startFlag) {
		StartFlag = startFlag;
	}

	public boolean isVFlag() {
		return VFlag;
	}

	public void setVFlag(boolean vFlag) {
		VFlag = vFlag;
	}

	public int getX0() {
		return x0;
	}

	public void setX0(int x0) {
		this.x0 = x0;
	}

	public int getY0() {
		return y0;
	}

	public void setY0(int y0) {
		this.y0 = y0;
	}

	public int getXmax() {
		return xmax;
	}

	public void setXmax(int xmax) {
		this.xmax = xmax;
	}

	public int getYmax() {
		return ymax;
	}

	public void setYmax(int ymax) {
		this.ymax = ymax;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getVmax() {
		return vmax;
	}

	public void setVmax(double vmax) {
		this.vmax = vmax;
	}

	public double getImax() {
		return imax;
	}

	public void setImax(double imax) {
		this.imax = imax;
	}

	public int getVscale() {
		return vscale;
	}

	public void setVscale(int vscale) {
		this.vscale = vscale;
	}

	public double getIscale() {
		return iscale;
	}

	public void setIscale(double iscale) {
		this.iscale = iscale;
	}

}
