package solver;

public enum Colors {
	WHITE("W"), YELLOW("Y"), BLUE("B"), GREEN("G"), RED("R"), ORANGE("O");
	
	private String color;
	
	private Colors(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
}
