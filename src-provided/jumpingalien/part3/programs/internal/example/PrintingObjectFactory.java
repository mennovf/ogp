package jumpingalien.part3.programs.internal.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;

import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.SourceLocation;

public class PrintingObjectFactory {

	/**
	 * Creates an implementation of IProgramFactory where the implementation of
	 * each interface method (except createProgram) just creates a
	 * PrintingObject with all arguments.
	 */
	@SuppressWarnings("unchecked")
	public static IProgramFactory<PrintingObject, PrintingObject, PrintingObject, PrintingProgram> create() {

		InvocationHandler handler = new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				if (method.getName().equals("createProgram")) {
					return new PrintingProgram((PrintingObject) args[0],
							(Map<String, PrintingObject>) args[1]);
				}
				if (args != null) {
					SourceLocation sourceLocation = (SourceLocation) args[args.length - 1];
					if (args.length >= 1) {
						return new PrintingObject(sourceLocation,
								method.getName(), Arrays.copyOfRange(args, 0,
										args.length - 1));
					} else {
						return new PrintingObject(sourceLocation,
								method.getName());
					}
				} else {
					return new PrintingObject(null, method.getName());
				}
			}
		};

		return (IProgramFactory<PrintingObject, PrintingObject, PrintingObject, PrintingProgram>) Proxy
				.newProxyInstance(IProgramFactory.class.getClassLoader(),
						new Class[] { IProgramFactory.class }, handler);
	}
}
