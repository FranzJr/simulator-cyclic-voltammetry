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

	private static double GAS_CONSTANT = 8.31445981D;
	private static double FARADAY_CONSTANT = 96485.33289D;

	private double k0;
	private double c0;
	private double D0;
	private double vu0;
	private double vl0;
	private double v0;
	private int cn0;
	private double rateConstantK;
	private double concentrationRedoxC;
	private double diffusionConstantOx;
	private double diffusionConstantRe;
	private double upperPotentialVU;
	private double lowerPotentialVL;
	private double scanRateV;
	private int cycleNumberCN;
	private boolean StartFlag;
	private boolean VFlag;
	private int x0;
	private int y0;
	private int xmax;
	private int ymax;
	private int x;
	private int y;
	private double maximunVoltagePositiveAxisX;
	private double maximumCurrentAxisY;
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
		rateConstantK = k0;
		concentrationRedoxC = c0 * 0.001D;
		diffusionConstantOx = D0;
		diffusionConstantRe = D0;
		upperPotentialVU = vu0 * 0.001D;
		lowerPotentialVL = vl0 * 0.001D;
		scanRateV = v0 * 0.001D;
		cycleNumberCN = cn0;
		StartFlag = false;
		VFlag = true;
		x0 = 120;
		y0 = 150;
		xmax = 100;
		ymax = 100;
		maximunVoltagePositiveAxisX = 1000D;
		maximumCurrentAxisY = 20D;
		vscale = 200;
		iscale = 0.10000000000000001D;
	}

	public List<Double[]> play(InputSimulationDto inputData) {
		rateConstantK = Double.valueOf(inputData.getRateConstantK()).doubleValue();
		concentrationRedoxC = Double.valueOf(inputData.getConcentrationRedoxC()).doubleValue() * 0.001D;
		diffusionConstantOx = Double.valueOf(inputData.getDiffusionConstantOx()).doubleValue();
		diffusionConstantRe = Double.valueOf(inputData.getDiffusionConstantRe()).doubleValue();
		upperPotentialVU = Double.valueOf(inputData.getUpperPotentialVU()).doubleValue() * 0.001D;
		// Math.max(0.20000000000000001D,
		// Double.valueOf(inputData.getUpperPotentialVU()).doubleValue() * 0.001D);
		lowerPotentialVL = Double.valueOf(inputData.getLowerPotentialVL()).doubleValue() * 0.001D;
		scanRateV = Double.valueOf(inputData.getScanRateV()).doubleValue() * 0.001D;
		cycleNumberCN = Integer.valueOf(inputData.getCycleNumberCN()).intValue();

		Double temperature = Double.valueOf(inputData.getTemperature()).doubleValue();
		Double alfa = Double.valueOf(inputData.getAlfa()).doubleValue();
		int electronsNumber = Integer.valueOf(inputData.getElectronsNumber()).intValue();
		Double standartPotential = Double.valueOf(inputData.getStandartPotential()).doubleValue();

		return cvCalc(temperature, alfa, electronsNumber, standartPotential, rateConstantK, concentrationRedoxC,
				diffusionConstantOx, diffusionConstantRe, upperPotentialVU, lowerPotentialVL, scanRateV, cycleNumberCN);
	}

	private List<Double[]> cvCalc(double temperature, double alfa, int electronsNumber, double standartPotential,
			double rateConstant, double concentrationRedox, double diffusionConstantOx, double diffusionConstantRe,
			double upperPotential, double lowerPotential, double scanRate, int cycleNumber) {

		List<Double[]> result = new ArrayList<>();

		double d6 = 0.0D;
		double d8 = 0.0D;

//		temperature = 298.14999999999998D;// COMMENT
//		alfa = 0.5D;// COMMENT
		double d14 = 1.0D;
		double d16 = upperPotential;
		double d17 = lowerPotential;

//		electronsNumber = 1;// COMMENT
//		standartPotential = 0.0D;// COMMENT
		int l = (int) (Math.abs(d16 * 1000D - d17 * 1000D) * 2D);
		double d18 = Math.abs(((d16 - d17) / scanRate) * 2D);
		int i1 = l / 2;
		double d19 = d18 / (double) l;
		double d20 = (2D * (d16 - d17)) / (double) l;
		double d21 = Math.sqrt(diffusionConstantOx * d19 * 2D);
		int j1 = (int) (6D * Math.sqrt(0.5D * (double) (cycleNumber * l + 1))) + 5;

		double ad[] = new double[j1];
		double ad1[] = new double[j1];
		double ad2[] = new double[j1];
		double ad3[] = new double[j1];
		double d22 = (diffusionConstantOx * d19) / (d21 * d21);
		// double d23 = d22;
		double d23 = (diffusionConstantRe * d19) / (d21 * d21);
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

		for (int l1 = 1; l1 <= cycleNumber * l + 1; l1++) {
			int j2 = (int) (6D * Math.sqrt(d22 * (double) l1));
			double d29 = (-rateConstant * (d19 / d21)
					* Math.exp((-alfa * (double) electronsNumber * FARADAY_CONSTANT * (d24 - standartPotential))
							/ GAS_CONSTANT / temperature)
					* ad1[0]
					+ rateConstant * (d19 / d21)
							* Math.exp(((1.0D - alfa) * (double) electronsNumber * FARADAY_CONSTANT
									* (d24 - standartPotential)) / GAS_CONSTANT / temperature)
							* ad3[0])
					/ (1.0D + (rateConstant * (d19 / d21)
							* Math.exp((-alfa * (double) electronsNumber * FARADAY_CONSTANT * (d24 - standartPotential))
									/ GAS_CONSTANT / temperature))
							/ (2D * d22)
							+ (rateConstant * (d19 / d21) * Math.exp(((1.0D - alfa) * (double) electronsNumber
									* FARADAY_CONSTANT * (d24 - standartPotential)) / GAS_CONSTANT / temperature))
									/ (2D * d23));
			ad[0] = ad1[0] + d22 * (ad1[1] - ad1[0]) + d29;
			ad2[0] = (ad3[0] + d23 * (ad3[1] - ad3[0])) - d29;
			double d7 = d24 * 1000D;
			double d9 = (double) electronsNumber * FARADAY_CONSTANT * d14 * concentrationRedox * d29 * (d21 / d19);
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
					x = (int) ((double) xmax * (d7 / maximunVoltagePositiveAxisX));
					y = -(int) ((double) ymax * (d9 / maximumCurrentAxisY));

					char c1 = '\226';
					byte byte0 = 0;
					if (l1 >= (cycleNumber * l) / 2 + 1)
						byte0 = 120;
					x = l2 + 1;
				}
			}

			x = (int) ((double) xmax * (d7 / maximunVoltagePositiveAxisX));
			y = -(int) ((double) ymax * (d9 / maximumCurrentAxisY));

			if (x < xmax) {
				Double[] item = new Double[2];
				item[0] = d7;// 1000;
				item[1] = d9;// 100000;
				result.add(item);
				// System.out.println(d7 + "," + d9);
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
		return rateConstantK;
	}

	public void setK(double k) {
		this.rateConstantK = k;
	}

	public double getC() {
		return concentrationRedoxC;
	}

	public void setC(double c) {
		this.concentrationRedoxC = c;
	}

	public double getD() {
		return diffusionConstantOx;
	}

	public void setD(double d) {
		diffusionConstantRe = d;
	}

	public double getVu() {
		return upperPotentialVU;
	}

	public void setVu(double vu) {
		this.upperPotentialVU = vu;
	}

	public double getVl() {
		return lowerPotentialVL;
	}

	public void setVl(double vl) {
		this.lowerPotentialVL = vl;
	}

	public double getV() {
		return scanRateV;
	}

	public void setV(double v) {
		this.scanRateV = v;
	}

	public int getCn() {
		return cycleNumberCN;
	}

	public void setCn(int cn) {
		this.cycleNumberCN = cn;
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
		return maximunVoltagePositiveAxisX;
	}

	public void setVmax(double vmax) {
		this.maximunVoltagePositiveAxisX = vmax;
	}

	public double getImax() {
		return maximumCurrentAxisY;
	}

	public void setImax(double imax) {
		this.maximumCurrentAxisY = imax;
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
