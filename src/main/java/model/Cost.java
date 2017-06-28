package model;


public class Cost {
	
	private double black;
	private double color;
	
	public Cost(double blackCost, double colorCost){
		this.black = blackCost;
		this.color = colorCost;
	}
	
	public double getBlack() {
		return black;
	}
	public void setBlack(double black) {
		this.black = black;
	}
	public double getColor() {
		return color;
	}
	public void setColor(double color) {
		this.color = color;
	}

}
