package co.edu.unal.voltammetry.dto;

public class InputSimulationDto {

	private String temperature;
	private String alfa;
	private String electronsNumber;
	private String standartPotential;
	private String diffusionConstantOx;
	private String diffusionConstantRe;

	private String rateConstantK; // k0
	private String concentrationRedoxC; // c0
	private String scanRateV; // v0
	private String upperPotentialVU; // vu0
	private String lowerPotentialVL; // vl0
	private String cycleNumberCN; // cn0

	public InputSimulationDto() {
	}

	public InputSimulationDto(String temperature, String alfa, String electronsNumber, String standartPotential,
			String diffusionConstantOx, String diffusionConstantRe, String rateConstantK, String concentrationRedoxC,
			String scanRateV, String upperPotentialVU, String lowerPotentialVL, String cycleNumberCN) {
		super();
		this.temperature = temperature;
		this.alfa = alfa;
		this.electronsNumber = electronsNumber;
		this.standartPotential = standartPotential;
		this.diffusionConstantOx = diffusionConstantOx;
		this.diffusionConstantRe = diffusionConstantRe;
		this.rateConstantK = rateConstantK;
		this.concentrationRedoxC = concentrationRedoxC;
		this.scanRateV = scanRateV;
		this.upperPotentialVU = upperPotentialVU;
		this.lowerPotentialVL = lowerPotentialVL;
		this.cycleNumberCN = cycleNumberCN;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getAlfa() {
		return alfa;
	}

	public void setAlfa(String alfa) {
		this.alfa = alfa;
	}

	public String getElectronsNumber() {
		return electronsNumber;
	}

	public void setElectronsNumber(String electronsNumber) {
		this.electronsNumber = electronsNumber;
	}

	public String getStandartPotential() {
		return standartPotential;
	}

	public void setStandartPotential(String standartPotential) {
		this.standartPotential = standartPotential;
	}

	public String getDiffusionConstantOx() {
		return diffusionConstantOx;
	}

	public void setDiffusionConstantOx(String diffusionConstantOx) {
		this.diffusionConstantOx = diffusionConstantOx;
	}

	public String getDiffusionConstantRe() {
		return diffusionConstantRe;
	}

	public void setDiffusionConstantRe(String diffusionConstantRe) {
		this.diffusionConstantRe = diffusionConstantRe;
	}

	public String getRateConstantK() {
		return rateConstantK;
	}

	public void setRateConstantK(String rateConstantK) {
		this.rateConstantK = rateConstantK;
	}

	public String getConcentrationRedoxC() {
		return concentrationRedoxC;
	}

	public void setConcentrationRedoxC(String concentrationRedoxC) {
		this.concentrationRedoxC = concentrationRedoxC;
	}

	public String getScanRateV() {
		return scanRateV;
	}

	public void setScanRateV(String scanRateV) {
		this.scanRateV = scanRateV;
	}

	public String getUpperPotentialVU() {
		return upperPotentialVU;
	}

	public void setUpperPotentialVU(String upperPotentialVU) {
		this.upperPotentialVU = upperPotentialVU;
	}

	public String getLowerPotentialVL() {
		return lowerPotentialVL;
	}

	public void setLowerPotentialVL(String lowerPotentialVL) {
		this.lowerPotentialVL = lowerPotentialVL;
	}

	public String getCycleNumberCN() {
		return cycleNumberCN;
	}

	public void setCycleNumberCN(String cycleNumberCN) {
		this.cycleNumberCN = cycleNumberCN;
	}

}
