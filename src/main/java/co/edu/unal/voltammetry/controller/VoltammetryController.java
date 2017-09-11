package co.edu.unal.voltammetry.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.edu.unal.voltammetry.dto.InputSimulationDto;
import co.edu.unal.voltammetry.dto.ResponseDto;
import co.edu.unal.voltammetry.fitting.PolinomioRegresion;
import co.edu.unal.voltammetry.simulator.Simulation;

@Controller
public class VoltammetryController {

	@RequestMapping("/test-request")
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "test";
	}

	@PostMapping("/play-simulation")
	@ResponseBody
	public ResponseDto<List<Double[]>> playSimulation(@RequestBody InputSimulationDto dto, HttpServletRequest request) {
		Simulation simulation = new Simulation();
		List<Double[]> result = simulation.play(dto);
		return ResponseDto.success(result);
	}
	
	@PostMapping("/play-fitting")
	@ResponseBody
	public ResponseDto<List<Double[]>> playFitting(@RequestBody List<String[]> inputString, HttpServletRequest request) {
		
		List<Double[]> input = new ArrayList<>();
		
		for (String[] coordenadasString : inputString) {
			Double[] coordenadasDouble = new Double[2];
			coordenadasDouble[0] = Double.parseDouble(coordenadasString[0]);
			coordenadasDouble[1] = Double.parseDouble(coordenadasString[1]);
			input.add(coordenadasDouble);
		}
		
		PolinomioRegresion polinomio = new PolinomioRegresion(input, 8);
		List<Double[]> result = polinomio.calculaPolinomio();
		return ResponseDto.success(result);
	}

}
