package controller.validators;

public class PortValidator implements Validator<String> {
	
	private String port;

	@Override
	public boolean validate(String port) {
		this.port = port;
		
		return isANumber() && isNotEmpty();
	}
	
	private boolean isNotEmpty() {
		return !this.port.isEmpty();
	}
	
	private boolean isANumber() {
		try {
			Integer.parseInt(this.port);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
