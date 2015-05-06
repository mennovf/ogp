package jumpingalien.part3.programs.internal.example;

import java.io.IOException;
import java.util.Optional;

import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.ProgramParser;

public class ExamplePrinter {
	public static void main(String[] args) throws IOException {
		IProgramFactory<PrintingObject, PrintingObject, PrintingObject, PrintingProgram> factory = PrintingObjectFactory.create();
		ProgramParser<PrintingObject, PrintingObject, PrintingObject, PrintingProgram> parser = new ProgramParser<>(
				factory);

		Optional<PrintingProgram> program = parser.parseFile("resources/programs/buzam.txt");
		//Optional<PrintingProgram> program = parser.parseString("double d := 1.0; while d < 3 do d := d + 1; print d; done");		
		
		if (program.isPresent()) {
			System.out.println("Parsing successful");
			System.out.println(program.get());
		} else {
			System.out.println("Parsing failed");
			System.out.println(parser.getErrors());
		}
	}

}
