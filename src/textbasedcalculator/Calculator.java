package textbasedcalculator;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.StringUtils;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.ScriptEngine;

@WebServlet("/Calculator")
public class Calculator extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public Calculator() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// Reading the param from textbox and splitting the value.

		String[] equationSplit = request.getParameter("textEquation").split("\\s");
		String equationValue = "";

		// for each loop to print elements of string array

		for (String value : equationSplit) {
			if (value.equalsIgnoreCase("plus")) {
				equationValue += "+";
			} else if (value.equalsIgnoreCase("minus")) {
				equationValue += "-";
			} else if (value.equalsIgnoreCase("division")) {
				equationValue += "/";
			} else if (value.equalsIgnoreCase("times")) {
				equationValue += "*";
			} else if (value.equalsIgnoreCase("square")) {
				equationValue += "*";
			} else if (value.equalsIgnoreCase("squareroot")) {
				equationValue += "squareroot";
			}

			else {
				equationValue += ConvertStringToValue(value);

			}
		}
		if (equationValue.contains("*")) {
			equationValue = equationValue + equationValue.replace("*", "");
		}
		String result = "";
		if (equationValue.contains("squareroot")) {
			equationValue = String.valueOf(Math.sqrt(Double.parseDouble(equationValue.replace("squareroot", ""))));

			result = ReplaceNumberswithStrings(equationValue).toString();

		} else {
			System.out.println(equationValue);
			result = ReplaceNumberswithStrings(Calculate(equationValue).toString());

		}

		request.setAttribute("answer", result);
		request.getRequestDispatcher("CalculatorPage.jsp").forward(request, response);

	}

	public Object Calculate(String expression) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		try {
			Object result = engine.eval(expression);
			return result;
		} catch (ScriptException e) {
			e.printStackTrace();
			return null;
		}

	}

	// Converting string to value
	public static int ConvertStringToValue(String equationString) {
		switch (equationString.toLowerCase()) {
		case "zero":
			return 0;
		case "one":
			return 1;
		case "two":
			return 2;
		case "three":
			return 3;
		case "four":
			return 4;
		case "five":
			return 5;
		case "six":
			return 6;
		case "seven":
			return 7;
		case "eight":
			return 8;
		case "nine":
			return 9;
		default:
			return 0;
		}
	}

	public static String ReplaceNumberswithStrings(String equationValue) {

		String replacedString = equationValue.replace(".", "point ").replace("-", "negative ").replace("0", "zero ")
				.replace("1", "one ").replace("2", "two ").replace("3", "three ").replace("4", "four ")
				.replace("5", "five ").replace("6", "six ").replace("7", "seven ").replace("8", "eight ")
				.replace("9", "nine ");

		return replacedString;
	}

}
