package br.com.andrefilipe.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.andrefilipeos.exception.UnsuportedMathOperationException;

@RestController
public class MathController {

	// This annotation tells spring that this method is an endpoint
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(@PathVariable(name = "numberOne") String numberOne,
			@PathVariable(name = "numberTwo") String numberTwo) throws Exception {

		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}

		Double sum = convertToDouble(numberOne) + convertToDouble(numberTwo);

		return sum;

	}

	// This annotation tells spring that this method is an endpoint
	@RequestMapping(value = "subtract/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double subtraction(@PathVariable(name = "numberOne") String numberOne,
			@PathVariable(name = "numberTwo") String numberTwo) {

		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}

		Double subtract = convertToDouble(numberOne) - convertToDouble(numberTwo);

		return subtract;
	}

	// This annotation tells spring that this method is an endpoint
	@RequestMapping(value = "multiplication/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double multiplication(@PathVariable(name = "numberOne") String numberOne,
			@PathVariable(name = "numberTwo") String numberTwo) {

		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}

		Double multiplication = convertToDouble(numberOne) * convertToDouble(numberTwo);

		return multiplication;
	}

	// This annotation tells spring that this method is an endpoint
	@RequestMapping(value = "division/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double division(@PathVariable(name = "numberOne") String numberOne,
			@PathVariable(name = "numberTwo") String numberTwo) {

		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}

		Double division = convertToDouble(numberOne) / convertToDouble(numberTwo);

		return division;
	}

	// This annotation tells spring that this method is an endpoint
	@RequestMapping(value = "mean/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double mean(@PathVariable(name = "numberOne") String numberOne,
			@PathVariable(name = "numberTwo") String numberTwo) {

		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}

		Double division = (convertToDouble(numberOne) / convertToDouble(numberTwo)) / 2;

		return division;
	}

	// This annotation tells spring that this method is an endpoint
	@RequestMapping(value = "squareRoot/{number}", method = RequestMethod.GET)
	public Double squareRoot(@PathVariable(name = "number") String number) {

		if (!isNumeric(number)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}

		Double division = Math.sqrt(convertToDouble(number));

		return division;
	}

	private Double convertToDouble(String number) {
		return Double.valueOf(number);
	}

	// when numberOne or two doesn't numeric, throw an exception
	private boolean isNumeric(String strNumber) {
		if (strNumber == null)
			return false;

		String number = strNumber.replaceAll(",", ".");

		return number.matches("[+-]?[0-9]*\\.?[0-9]+");
	}
}
