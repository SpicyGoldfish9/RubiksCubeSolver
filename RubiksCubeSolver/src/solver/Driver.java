package solver;

public class Driver {
	
	public static void main(String args[]){  
		RubiksCube cube = new RubiksCube();
		for(int i = 0; i < 6; i++) {
			cube.setColor(Colors.values()[i], Colors.values()[i], 0, 0);
			cube.setColor(Colors.values()[i], Colors.values()[i], 0, 1); 
			cube.setColor(Colors.values()[i], Colors.values()[i], 0, 2); 
			cube.setColor(Colors.values()[i], Colors.values()[i], 1, 0); 
			cube.setColor(Colors.values()[i], Colors.values()[i], 1, 2); 
			cube.setColor(Colors.values()[i], Colors.values()[i], 2, 0); 
			cube.setColor(Colors.values()[i], Colors.values()[i], 2, 1); 
			cube.setColor(Colors.values()[i], Colors.values()[i], 2, 2); 
		}
		
		cube.u2();
		cube.b2();
		cube.f();
		
		cube.r();
		cube.u2();
		
		cube.r();
		cube.d2();
		cube.f2();
		cube.b2();
		cube.b2();
		cube.f();
		cube.r2();
		cube.u2();
		cube.r();
		cube.u2();
		cube.r();
		cube.r2();
		
		
		
		
		long startTime = System.nanoTime();

       
		cube.solveDaisy();
		cube.solveCross();
		cube.solveWhiteCorners();
		cube.solveSecondLayer();
		cube.solveYellowCross();
		cube.orientYellowCorners();
		cube.flipYellowCorners();
		cube.permuteYellowEdges();
		 // End time
        long endTime = System.nanoTime();

        // Calculate elapsed time in nanoseconds
        long elapsedTime = endTime - startTime;

        // Convert to milliseconds
        double elapsedTimeInMillis = elapsedTime / 1_000_000.0;
        System.out.println("Elapsed time in milliseconds: " + elapsedTimeInMillis);
		System.out.println(cube.getSolution());
		System.out.print(cube.cubeState());
	}  
	
}
