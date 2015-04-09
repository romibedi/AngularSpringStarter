package com.spring.angular;

public class Car {
	
	private Engine engine;
	private String warningMessage;
	
	
	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public String getWarningMessage() {
		return warningMessage;
	}

	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}

	public Car(Engine engine){
		this.engine = engine;
	}
	
	public void accelerate(){
		engine.increaseRPM();
		
		if (engine.getRPM() > 5000){
			setWarningMessage("Slow Down!");
		}
	}

}
