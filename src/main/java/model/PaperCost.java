package model;

public class PaperCost {
	
	private PaperType paperType;
	private Cost singleSide;
	private Cost doubleSide;
	
	public PaperType getPaperType() {
		return paperType;
	}
	public void setPaperType(PaperType paperType) {
		this.paperType = paperType;
	}
	public Cost getSingleSide() {
		return singleSide;
	}
	public void setSingleSide(Cost singleSide) {
		this.singleSide = singleSide;
	}
	public Cost getDoubleSide() {
		return doubleSide;
	}
	public void setDoubleSide(Cost doubleSide) {
		this.doubleSide = doubleSide;
	}

}
