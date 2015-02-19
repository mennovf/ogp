package jumpingalien.common.gui;

class InputAction {
	private final Runnable start;
	private final Runnable end;

	private boolean isExecuting = false;

	public InputAction(Runnable start, Runnable end) {
		this.start = start;
		this.end = end;
	}

	public void start() {
		if (!isExecuting) {
			start.run();
			isExecuting = true;
		}
	}

	public void end() {
		if (isExecuting) {
			end.run();
			isExecuting = false;
		}
	}
}
