package controller;

import controller.validators.PortValidator;

public class ValidatorsController {
	
	private static ValidatorsController instance;
	
	private ValidatorsController() {
	}
	
	public boolean validatePort(String port) {
		return new PortValidator().validate(port);
	}
	
	public static ValidatorsController getInstance() {
		if(instance == null) {
			instance = new ValidatorsController();
		}
		
		return instance;
	}

}
