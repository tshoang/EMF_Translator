package ac.soton.eventb.textout.visitor.elements;

import java.util.ArrayList;
import java.util.List;

import org.eventb.emf.core.machine.Parameter;

import ac.soton.eventb.textout.core.ExportTextManager;

public class PrintableParameter implements IPrintable {

	private Parameter parameter;

	public PrintableParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	@Override
	public List<String> print() {
		List<String> output = new ArrayList<>();
		String comment = ExportTextManager.adjustComment(parameter.getComment());
		output.add(parameter.getName() + " " + comment);
		return output;
	}

}
