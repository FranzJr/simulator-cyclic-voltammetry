package co.edu.unal.voltammetry.dto;

public class InputSimulationDto {

	private String rateConstantK; // k0
	private String concentrationRedoxC; // c0
	private String diffusionConstantD; // D0
	private String scanRateV; // v0
	private String upperPotentialVU; // vu0
	private String lowerPotentialVL; // vl0
	private String cycleNumberCN; // cn0
	
	public InputSimulationDto() {
	}

	public InputSimulationDto(String rateConstantK, String concentrationRedoxC, String diffusionConstantD,
			String scanRateV, String upperPotentialVU, String lowerPotentialVL, String cycleNumberCN) {
		this.rateConstantK = rateConstantK;
		this.concentrationRedoxC = concentrationRedoxC;
		this.diffusionConstantD = diffusionConstantD;
		this.scanRateV = scanRateV;
		this.upperPotentialVU = upperPotentialVU;
		this.lowerPotentialVL = lowerPotentialVL;
		this.cycleNumberCN = cycleNumberCN;
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

	public String getDiffusionConstantD() {
		return diffusionConstantD;
	}

	public void setDiffusionConstantD(String diffusionConstantD) {
		this.diffusionConstantD = diffusionConstantD;
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
