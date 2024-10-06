package solver;

import java.util.ArrayList;
import java.util.Stack;

public class RubiksCube {
	private Colors[][] whiteSide;
	private Colors[][] yellowSide;
	private Colors[][] blueSide;
	private Colors[][] greenSide;
	private Colors[][] redSide;
	private Colors[][] orangeSide;
	private String solution;

	public RubiksCube() {
		whiteSide = new Colors[3][3];
		whiteSide[1][1] = Colors.WHITE;

		yellowSide = new Colors[3][3];
		yellowSide[1][1] = Colors.YELLOW;

		blueSide = new Colors[3][3];
		blueSide[1][1] = Colors.BLUE;

		greenSide = new Colors[3][3];
		greenSide[1][1] = Colors.GREEN;

		redSide = new Colors[3][3];
		redSide[1][1] = Colors.RED;

		orangeSide = new Colors[3][3];
		orangeSide[1][1] = Colors.ORANGE;

		solution = "";
	}

	public void setColor(Colors side, Colors color, int row, int col) {
		if(side == Colors.WHITE) {
			whiteSide[row][col] = color;
		}
		if(side == Colors.YELLOW) {
			yellowSide[row][col] = color;
		}
		if(side == Colors.BLUE) {
			blueSide[row][col] = color;
		}
		if(side == Colors.GREEN) {
			greenSide[row][col] = color;
		}
		if(side == Colors.RED) {
			redSide[row][col] = color;
		}
		if(side == Colors.ORANGE) {
			orangeSide[row][col] = color;
		}
	}
	public void r() {
		Colors[] tempGreen = new Colors[3];
		Colors[] tempYellow = new Colors[3];
		Colors[] tempBlue = new Colors[3];
		for(int i = 0; i < 3; i++) {
			tempGreen[i] = greenSide[i][2];
			tempYellow[i] = yellowSide[i][2];
			tempBlue[2-i] = blueSide[i][0];
		}
		for(int i = 0; i < 3; i++) {
			greenSide[i][2] = whiteSide[i][2];
			yellowSide[i][2] = tempGreen[i];
			blueSide[2-i][0] = tempYellow[i];
			whiteSide[i][2] = tempBlue[i];
		}
		//accounting for the orange face, which turns 90 degrees clockwise
		Colors[] bottomOrangeEdges = new Colors[3];
		bottomOrangeEdges[0] = orangeSide[1][2];
		bottomOrangeEdges[1] = orangeSide[2][1];
		bottomOrangeEdges[2] = orangeSide[1][0];

		orangeSide[1][2] = orangeSide[0][1];
		orangeSide[2][1] = bottomOrangeEdges[0];
		orangeSide[1][0] = bottomOrangeEdges[1];
		orangeSide[0][1] = bottomOrangeEdges[2];

		Colors[] orangeCorners = new Colors[3];
		orangeCorners[0] = orangeSide[0][2];
		orangeCorners[1] = orangeSide[2][2];
		orangeCorners[2] = orangeSide[2][0];

		orangeSide[0][2] = orangeSide[0][0];
		orangeSide[2][2] = orangeCorners[0];
		orangeSide[2][0] = orangeCorners[1];
		orangeSide[0][0] = orangeCorners[2];
	}
	public void rPrime() {
		r();
		r();
		r();
	}
	public void r2() {
		r();
		r();
	}
	public void l2() {
		l();
		l();
	}
	public void b2() {
		b();
		b();
	}

	public void l() {
		Colors[] tempWhite = new Colors[3];
		Colors[] tempYellow = new Colors[3];
		Colors[] tempBlue = new Colors[3];
		for(int i = 0; i < 3; i++) {
			tempWhite[i] = whiteSide[2-i][0];
			tempYellow[i] = yellowSide[2-i][0];
			tempBlue[i] = blueSide[i][2];
		}
		for(int i = 0; i < 3; i++) {

			whiteSide[2-i][0] = greenSide[2-i][0];
			greenSide[2-i][0] = tempYellow[i];
			yellowSide[2-i][0] = tempBlue[i];
			blueSide[i][2] = tempWhite[i];
		}

		//accounting for the red face, which turns 90 degrees clockwise
		Colors[] bottomRedEdges = new Colors[3];
		bottomRedEdges[0] = redSide[1][2];
		bottomRedEdges[1] = redSide[2][1];
		bottomRedEdges[2] = redSide[1][0];

		redSide[1][2] = redSide[0][1];
		redSide[2][1] = bottomRedEdges[0];
		redSide[1][0] = bottomRedEdges[1];
		redSide[0][1] = bottomRedEdges[2];

		Colors[] redCorners = new Colors[3];
		redCorners[0] = redSide[0][2];
		redCorners[1] = redSide[2][2];
		redCorners[2] = redSide[2][0];

		redSide[0][2] = redSide[0][0];
		redSide[2][2] = redCorners[0];
		redSide[2][0] = redCorners[1];
		redSide[0][0] = redCorners[2];

	}
	public void lPrime() {
		l();
		l();
		l();
	}

	public void u() {
		Colors[] tempGreen = new Colors[3];
		Colors[] tempRed = new Colors[3];
		Colors[] tempBlue = new Colors[3];
		for(int i = 0; i < 3; i++) {
			tempGreen[i] = greenSide[0][i];
			tempRed[i] = redSide[0][i];
			tempBlue[i] = blueSide[0][i];
		}
		for(int i = 0; i < 3; i++) {
			greenSide[0][i] = orangeSide[0][i];
			redSide[0][i] = tempGreen[i];
			blueSide[0][i] = tempRed[i];
			orangeSide[0][i] = tempBlue[i];
		}

		//accounting for the yellow face, which turns 90 degrees clockwise
		Colors[] bottomYellowEdges = new Colors[3];
		bottomYellowEdges[0] = yellowSide[1][2];
		bottomYellowEdges[1] = yellowSide[2][1];
		bottomYellowEdges[2] = yellowSide[1][0];

		yellowSide[1][2] = yellowSide[0][1];
		yellowSide[2][1] = bottomYellowEdges[0];
		yellowSide[1][0] = bottomYellowEdges[1];
		yellowSide[0][1] = bottomYellowEdges[2];

		Colors[] yellowCorners = new Colors[3];
		yellowCorners[0] = yellowSide[0][2];
		yellowCorners[1] = yellowSide[2][2];
		yellowCorners[2] = yellowSide[2][0];

		yellowSide[0][2] = yellowSide[0][0];
		yellowSide[2][2] = yellowCorners[0];
		yellowSide[2][0] = yellowCorners[1];
		yellowSide[0][0] = yellowCorners[2];
	}
	public void uPrime() {
		u();
		u();
		u();
	}
	public void u2() {
		u();
		u();
	}
	public void d2() {
		d();
		d();
	}
	public void d() {
		Colors[] tempGreen = new Colors[3];
		Colors[] tempOrange = new Colors[3];
		Colors[] tempBlue = new Colors[3];
		for(int i = 0; i < 3; i++) {
			tempGreen[i] = greenSide[2][i];
			tempOrange[i] = orangeSide[2][i];
			tempBlue[i] = blueSide[2][i];
		}
		for(int i = 0; i < 3; i++) {
			greenSide[2][i] = redSide[2][i];
			redSide[2][i] = tempBlue[i];
			blueSide[2][i] = tempOrange[i];
			orangeSide[2][i] = tempGreen[i];
		}
		//accounting for the white face, which turns 90 degrees clockwise
		Colors[] bottomWhiteEdges = new Colors[3];
		bottomWhiteEdges[0] = whiteSide[1][2];
		bottomWhiteEdges[1] = whiteSide[2][1];
		bottomWhiteEdges[2] = whiteSide[1][0];

		whiteSide[1][2] = whiteSide[0][1];
		whiteSide[2][1] = bottomWhiteEdges[0];
		whiteSide[1][0] = bottomWhiteEdges[1];
		whiteSide[0][1] = bottomWhiteEdges[2];

		Colors[] whiteCorners = new Colors[3];
		whiteCorners[0] = whiteSide[0][2];
		whiteCorners[1] = whiteSide[2][2];
		whiteCorners[2] = whiteSide[2][0];

		whiteSide[0][2] = whiteSide[0][0];
		whiteSide[2][2] = whiteCorners[0];
		whiteSide[2][0] = whiteCorners[1];
		whiteSide[0][0] = whiteCorners[2];
	}
	public void dPrime() {
		d();
		d();
		d();
	}

	public void f() {
		Colors[] tempOrange = new Colors[3];
		Colors[] tempWhite = new Colors[3];
		Colors[] tempRed = new Colors[3];
		for(int i = 0; i < 3; i++) {
			tempOrange[i] = orangeSide[2-i][0];
			tempWhite[i] = whiteSide[0][i];
			tempRed[i] = redSide[i][2];
		}
		for(int i = 0; i < 3; i++) {
			orangeSide[2-i][0] = yellowSide[2][2-i];
			whiteSide[0][i] = tempOrange[i];
			redSide[i][2] = tempWhite[i];
			yellowSide[2][2-i] = tempRed[i];
		}
		//accounting for the green face, which turns 90 degrees clockwise
		Colors[] bottomGreenEdges = new Colors[3];
		bottomGreenEdges[0] = greenSide[1][2];
		bottomGreenEdges[1] = greenSide[2][1];
		bottomGreenEdges[2] = greenSide[1][0];

		greenSide[1][2] = greenSide[0][1];
		greenSide[2][1] = bottomGreenEdges[0];
		greenSide[1][0] = bottomGreenEdges[1];
		greenSide[0][1] = bottomGreenEdges[2];

		Colors[] greenCorners = new Colors[3];
		greenCorners[0] = greenSide[0][2];
		greenCorners[1] = greenSide[2][2];
		greenCorners[2] = greenSide[2][0];

		greenSide[0][2] = greenSide[0][0];
		greenSide[2][2] = greenCorners[0];
		greenSide[2][0] = greenCorners[1];
		greenSide[0][0] = greenCorners[2];
	}
	public void fPrime() {
		f();
		f();
		f();
	}
	public void f2() {
		f();
		f();
	}

	public void b() {
		Colors[] tempRed = new Colors[3];
		Colors[] tempWhite = new Colors[3];
		Colors[] tempOrange = new Colors[3];
		for(int i = 0; i < 3; i++) {
			tempRed[i] = redSide[2-i][0];
			tempWhite[i] = whiteSide[2][2-i];
			tempOrange[i] = orangeSide[i][2];
		}
		for(int i = 0; i < 3; i++) {
			redSide[2-i][0] = yellowSide[0][i];
			whiteSide[2][2-i] = tempRed[i];
			orangeSide[i][2] = tempWhite[i];
			yellowSide[0][i] = tempOrange[i];
		}
		//accounting for the green face, which turns 90 degrees clockwise
		Colors[] bottomBlueEdges = new Colors[3];
		bottomBlueEdges[0] = blueSide[1][2];
		bottomBlueEdges[1] = blueSide[2][1];
		bottomBlueEdges[2] = blueSide[1][0];

		blueSide[1][2] = blueSide[0][1];
		blueSide[2][1] = bottomBlueEdges[0];
		blueSide[1][0] = bottomBlueEdges[1];
		blueSide[0][1] = bottomBlueEdges[2];

		Colors[] blueCorners = new Colors[3];
		blueCorners[0] = blueSide[0][2];
		blueCorners[1] = blueSide[2][2];
		blueCorners[2] = blueSide[2][0];

		blueSide[0][2] = blueSide[0][0];
		blueSide[2][2] = blueCorners[0];
		blueSide[2][0] = blueCorners[1];
		blueSide[0][0] = blueCorners[2];
	}
	public void bPrime() {
		b();
		b();
		b();
	}

	public void sexyMove() {
		r();
		u();
		rPrime();
		uPrime();
	}
	public void reverseSexyMove() {
		u();
		r();
		uPrime();
		rPrime();
	}
	public void reverseLeftSexyMove() {
		uPrime();
		lPrime();
		u();
		l();
	}
	
	public void finishCubeGreenSolvedRight() {
		sexyMove();
		leftSexyMove();
		reverseSexyMove();
		reverseLeftSexyMove();
	}

	public void finishCubeGreenSolvedLeft() {
		leftSexyMove();
		sexyMove();
		reverseLeftSexyMove();
		reverseSexyMove();
	}
	
	public void fPrimeSexy() {
		fPrime();
		uPrime();
		f();
		u();
	}
	public void reverseFPrimeSexy() {
		uPrime();
		fPrime();
		u();
		f();
	}
	public void rPrimeSexy() {
		rPrime();
		uPrime();
		r();
		u();
	}
	
	public void reverseRPrimeSexy() {
		uPrime();
		rPrime();
		u();
		r();
	}
	public void finishCubeBlueSolvedRight() {
		lSexy();
		rPrimeSexy();
		reverseLSexy();
		reverseRPrimeSexy();
	}
	
	public void finishCubeBlueSolvedLeft() {
		rPrimeSexy();
		lSexy();
		reverseRPrimeSexy();
		reverseLSexy();
	}
	
	
	public void bPrimeSexy() {
		bPrime();
		uPrime();
		b();
		u();
	}
	public void reverseBPrimeSexy() {
		uPrime();
		bPrime();
		u();
		b();
	}
	public void finishCubeRedSolvedLeft() {
		bPrimeSexy();
		fSexy();
		reverseBPrimeSexy();
		reverseFSexy();
	}
	
	public void finishCubeRedSolvedRight() {
		fSexy();
		bPrimeSexy();
		reverseFSexy();
		reverseBPrimeSexy();
	}
	
	public void finishCubeOrangeSolvedLeft() {
		fPrimeSexy();
		bSexy();
		reverseFPrimeSexy();
		reverseBSexy();
	}
	
	public void finishCubeOrangeSolvedRight() {
		bSexy();
		fPrimeSexy();
		reverseBSexy();
		reverseFPrimeSexy();
	}
	public void fSexy() {
		f();
		u();
		fPrime();
		uPrime();
	}
	public void reverseFSexy() {
		u();
		f();
		uPrime();
		fPrime();
	}

	public void bSexy() {
		b();
		u();
		bPrime();
		uPrime();
	}

	public void reverseBSexy() {
		u();
		b();
		uPrime();
		bPrime();
	}
	public void lSexy() {
		l();
		u();
		lPrime();
		uPrime();
	}

	public void reverseLSexy() {
		u();
		l();
		uPrime();
		lPrime();
	}
	public void insertBlueRedFacingBlue(){
		u();
		l();
		u();
		lPrime();
		uPrime();
		bPrime();
		uPrime();
		b();
		u();
	}
	public void insertBlueRedFacingRed() {
		uPrime();
		bPrime();
		uPrime();
		b();
		u();
		l();
		u();
		lPrime();
		uPrime();
	}

	public void insertBlueOrangeFacingBlue() {
		uPrime();
		rPrime();
		uPrime();
		r();
		u();
		b();
		u();
		bPrime();
		uPrime();
	} 
	public void insertBlueOrangeFacingOrange() {
		u();
		b();
		u();
		bPrime();
		uPrime();
		rPrime();
		uPrime();
		r();
		u();
	}

	public void insertGreenRedFacingGreen() {
		uPrime();
		lPrime();
		uPrime();
		l();
		u();
		f();
		u();
		fPrime();
		uPrime();
	}
	public void insertGreenRedFacingRed() {
		u();
		f();
		u();
		fPrime();
		uPrime();
		lPrime();
		uPrime();
		l();
		u();
	}
	public void insertGreenOrangeFacingGreen() {
		u();
		r();
		u();
		rPrime();
		uPrime();
		fPrime();
		uPrime();
		f();
		u();
	}
	public void insertGreenOrangeFacingOrange() {
		uPrime();
		fPrime();
		uPrime();
		f();
		u();
		r();
		u();
		rPrime();
		uPrime();
	}
	public void splitBSexy() {
		bPrime();
		uPrime();
		b();
		u();
	}
	public void leftSexyMove() {
		lPrime();
		uPrime();
		l();
		u();
	}
	public void fLeftSexy() {
		f();
		u();
		fPrime();
		uPrime();
	}
	public void fPrimeLeftSexy() {
		fPrime();
		uPrime();
		f();
		u();
	}
	public void solveLineCase() {
		f();
		sexyMove();
		fPrime();
	}
	public void solveLCase() {
		b();
		u();
		l();
		uPrime();
		lPrime();
		bPrime();
	}
	public void solveDotCase() {
		solveLineCase();
		solveLCase();
	}
	public void yPerm() {
		f();
		r();
		uPrime();
		rPrime();
		uPrime();
		r();
		u();
		rPrime();
		fPrime();
		sexyMove();
		rPrime();
		f();
		r();
		fPrime();
	}

	//R U R' U R U R' F' R U R' U' R' F R2 U' R' U2 R U' R'
	public void NaPerm() {
		r();
		u();
		rPrime();
		u();
		r();
		u();
		rPrime();
		fPrime();
		sexyMove();
		rPrime();
		f();
		r2();
		uPrime();
		rPrime();
		u2();
		r();
		uPrime();
		rPrime();
	}

	//name of the swap method: the two opposite colors and then the face in between them

	public void redOrangeGreenSwap() {
		f();
		u();
		fPrime();
		uPrime();
		f();
		u();
		fPrime();
		uPrime();
		f();
		u();
		fPrime();
		uPrime();
		leftSexyMove(); //L' U' L U
		leftSexyMove();
		leftSexyMove();
		uPrime();
	}


	public void redOrangeBlueSwap() {
		bSexy(); // B U B' U'
		bSexy();
		bSexy();
		rPrime();
		uPrime();
		r();
		u();
		rPrime();
		uPrime();
		r();
		u();
		rPrime();
		uPrime();
		r();
	}


	public void blueGreenRedSwap() {
		l();
		u();
		lPrime();
		uPrime();
		l();
		u();
		lPrime();
		uPrime();
		l();
		u();
		lPrime();		
		uPrime();
		bPrime();
		uPrime();
		b();
		u();
		bPrime();
		uPrime();
		b();
		u();
		bPrime();
		uPrime();
		b();
	}

	public void blueGreenOrangeSwap() {
		sexyMove();
		sexyMove();
		sexyMove();
		fPrime();
		uPrime();
		f();
		u();
		fPrime();
		uPrime();
		f();
		u();
		fPrime();
		uPrime();
		f();
	}

	public void cornerFlipSexy() {
		l();
		d();
		lPrime();
		dPrime();
	}
	public void cornerFlipReverseSexy() {
		d();
		l();
		dPrime();
		lPrime();
	}
	
	public void yellowCornerCycle() {
		u();
		r();
		uPrime();
		lPrime();
		u();
		rPrime();
		uPrime();
		l();
	}

	public String cubeState() {
		String cubeState = "";

		for(int i = 0; i < 6; i++) {
			Colors currColor = Colors.values()[i];
			cubeState += currColor.getColor() + " Side: " + "\n";
			if(currColor == Colors.WHITE) {
				for(int row = 0; row < 3; row++) {
					for(int col = 0; col < 3; col++) {
						cubeState += whiteSide[row][col].getColor();
					}
					cubeState += "\n";
				}
			}
			if(currColor == Colors.YELLOW){
				for(int row = 0; row < 3; row++) {
					for(int col = 0; col < 3; col++) {
						cubeState += yellowSide[row][col].getColor();
					}
					cubeState += "\n";
				}			
			}
			if(currColor == Colors.BLUE) {
				for(int row = 0; row < 3; row++) {
					for(int col = 0; col < 3; col++) {
						cubeState += blueSide[row][col].getColor();
					}
					cubeState += "\n";
				}
			}
			if(currColor == Colors.GREEN) {
				for(int row = 0; row < 3; row++) {
					for(int col = 0; col < 3; col++) {
						cubeState += greenSide[row][col].getColor();
					}
					cubeState += "\n";
				}
			}
			if(currColor == Colors.RED) {
				for(int row = 0; row < 3; row++) {
					for(int col = 0; col < 3; col++) {
						cubeState += redSide[row][col].getColor();
					}
					cubeState += "\n";
				}
			}
			if(currColor == Colors.ORANGE) {
				for(int row = 0; row < 3; row++) {
					for(int col = 0; col < 3; col++) {
						cubeState += orangeSide[row][col].getColor();
					}
					cubeState += "\n";
				}
			}
			cubeState += "\n\n";
		}

		return cubeState;
	}

	public String getSolution() {
		return solution;
	}

	//hard
	public void solveDaisy() {
		while(yellowSide[0][1] != Colors.WHITE || yellowSide[1][0] != Colors.WHITE || yellowSide[1][2] != Colors.WHITE || yellowSide[2][1] != Colors.WHITE) {
			//yellow edges on the white face (4 cases)
			if(whiteSide[0][1] == Colors.WHITE) {
				int uCounter = 0;
				while(yellowSide[2][1] == Colors.WHITE) {
					u();
					uCounter++;
				}
				for(int i = 0; i < uCounter; i++) {
					solution += "U ";
				}
				f2();
				solution += "F2 ";
			}
			if(whiteSide[1][2] == Colors.WHITE) {
				int uCounter = 0;
				while(yellowSide[1][2] == Colors.WHITE) {
					u();
					uCounter++;
				}
				for(int i = 0; i < uCounter; i++) {
					solution += "U ";
				}
				r2();
				solution += "R2 ";
			}
			if(whiteSide[2][1] == Colors.WHITE) {
				int uCounter = 0;
				while(yellowSide[0][1] == Colors.WHITE) {
					u();
					uCounter++;
				}
				for(int i = 0; i < uCounter; i++) {
					solution += "U ";
				}
				b2();
				solution += "B2 ";
			}
			if(whiteSide[1][0] == Colors.WHITE) {
				int uCounter = 0;
				while(yellowSide[1][0] == Colors.WHITE) {
					u();
					uCounter++;
				}
				for(int i = 0; i < uCounter; i++) {
					solution += "U ";
				}
				l2();
				solution += "L2 ";
			}

			//force the white edge to the greenSide[1][2], then make the open spot available and turn the edge up
			if(greenSide[0][1] == Colors.WHITE) {
				//no need to clearGreen here (not possible to have white edge in yellowSide[2][1]
				forceGreen(0, 1);
			}
			if(greenSide[1][2] == Colors.WHITE) {
				clear(2, 1);
				forceGreen(1, 2);
			}
			if(greenSide[2][1] == Colors.WHITE) {
				clear(2, 1);
				forceGreen(2, 1);
			}
			if(greenSide[1][0] == Colors.WHITE) {
				clear(2, 1);
				forceGreen(1, 0);
			}

			//force the white edge to the orangeSide[1][2], then make the open spot available and turn the edge up
			if(orangeSide[0][1] == Colors.WHITE) {
				//no need to clearGreen here (not possible to have white edge in yellowSide[2][1]
				forceOrange(0, 1);
			}
			if(orangeSide[1][2] == Colors.WHITE) {
				clear(1, 2);
				forceOrange(1, 2);
			}
			if(orangeSide[2][1] == Colors.WHITE) {
				clear(1, 2);
				forceOrange(2, 1);
			}
			if(orangeSide[1][0] == Colors.WHITE) {
				clear(1, 2);
				forceOrange(1, 0);
			}

			//force the white edge to the redSide[1][2], then make the open spot available and turn the edge up
			if(redSide[0][1] == Colors.WHITE) {
				//no need to clearGreen here (not possible to have white edge in yellowSide[2][1]
				forceRed(0, 1);
			}
			if(redSide[1][2] == Colors.WHITE) {
				clear(1, 0);
				forceRed(1, 2);
			}
			if(redSide[2][1] == Colors.WHITE) {
				clear(1, 0);
				forceRed(2, 1);
			}
			if(redSide[1][0] == Colors.WHITE) {
				clear(1, 0);
				forceRed(1, 0);
			}

			//force the white edge to the blueSide[1][2], then make the open spot available and turn the edge up
			if(blueSide[0][1] == Colors.WHITE) {
				//no need to clearGreen here (not possible to have white edge in yellowSide[2][1]
				forceBlue(0, 1);
			}
			if(blueSide[1][2] == Colors.WHITE) {
				clear(0, 1);
				forceBlue(1, 2);
			}
			if(blueSide[2][1] == Colors.WHITE) {
				clear(0, 1);
				forceBlue(2, 1);
			}
			if(blueSide[1][0] == Colors.WHITE) {
				clear(0, 1);
				forceBlue(1, 0);
			}

		}
	}
	//clears the yellowSide[row][col], meaning there is no white edge there
	public void clear(int row, int col) {
		while(yellowSide[row][col] == Colors.WHITE) {
			u();
			solution += "U ";
		}
	}
	public void forceGreen(int row, int col) {
		if(row == 0 && col == 1) {
			f();
			solution += "F ";
		}
		if(row == 2 && col == 1) {
			fPrime();
			solution += "F' ";
		} 
		if(row == 1 && col == 0){
			f2();
			solution += "F2 ";
		}
		uPrime();
		r();
		solution += "U' R ";
	}
	public void forceOrange(int row, int col) {
		if(row == 0 && col == 1) {
			r();
			solution += "R ";
		}
		if(row == 2 && col == 1) {
			rPrime();
			solution += "R' ";
		} 
		if(row == 1 && col == 0){
			r2();
			solution += "R2 ";
		}
		uPrime();
		b();
		solution += "U' B ";
	}
	public void forceRed(int row, int col) {
		if(row == 0 && col == 1) {
			l();
			solution += "L ";
		}
		if(row == 2 && col == 1) {
			lPrime();
			solution += "L' ";
		} 
		if(row == 1 && col == 0){
			l2();
			solution += "L2 ";
		}
		uPrime();
		f();
		solution += "U' F ";
	}
	public void forceBlue(int row, int col) {
		if(row == 0 && col == 1) {
			b();
			solution += "B ";
		}
		if(row == 2 && col == 1) {
			bPrime();
			solution += "B' ";
		} 
		if(row == 1 && col == 0){
			b2();
			solution += "B2 ";
		}
		uPrime();
		l();
		solution += "U' L ";
	}

	public void solveCross() {
		while(true) {
			if(yellowSide[2][1] == Colors.WHITE && greenSide[0][1] == Colors.GREEN) {
				break;
			}
			u();
			solution += "U ";
		}
		f2();
		solution += "F2 ";

		while(true) {
			if(yellowSide[1][2] == Colors.WHITE && orangeSide[0][1] == Colors.ORANGE) {
				break;
			}
			u();
			solution += "U ";
		}
		r2();
		solution += "R2 ";

		while(true) {
			if(yellowSide[1][0] == Colors.WHITE && redSide[0][1] == Colors.RED) {
				break;
			}
			u();
			solution += "U ";
		}
		l2();
		solution += "L2 ";

		while(true) {
			if(yellowSide[0][1] == Colors.WHITE && blueSide[0][1] == Colors.BLUE) {
				break;
			}
			u();
			solution += "U ";
		}
		b2();
		solution += "B2 ";
	}

	public void solveWhiteCorners() {
		ArrayList<Colors> greenOrangeCornerDown = new ArrayList<>();
		greenOrangeCornerDown.add(whiteSide[0][2]);
		greenOrangeCornerDown.add(greenSide[2][2]);
		greenOrangeCornerDown.add(orangeSide[2][0]);
		if(greenOrangeCornerDown.contains(Colors.WHITE) && (greenSide[2][2] != Colors.GREEN || orangeSide[2][0] != Colors.ORANGE)) {
			ArrayList<Colors> greenOrangeCornerUp = new ArrayList<>();
			greenOrangeCornerUp.add(yellowSide[2][2]);
			greenOrangeCornerUp.add(greenSide[0][2]);
			greenOrangeCornerUp.add(orangeSide[0][0]);
			while(greenOrangeCornerUp.contains(Colors.WHITE)) {
				u();
				solution += "U ";
				greenOrangeCornerUp.clear();
				greenOrangeCornerUp.add(yellowSide[2][2]);
				greenOrangeCornerUp.add(greenSide[0][2]);
				greenOrangeCornerUp.add(orangeSide[0][0]);
			}
			r();
			u();
			rPrime();
			solution += "R U R' ";
		}

		ArrayList<Colors> greenRedCornerDown = new ArrayList<>();
		greenRedCornerDown.add(whiteSide[0][0]);
		greenRedCornerDown.add(greenSide[2][0]);
		greenRedCornerDown.add(redSide[2][2]);
		if(greenRedCornerDown.contains(Colors.WHITE) && (greenSide[2][0] != Colors.GREEN || redSide[2][2] != Colors.RED)) {
			ArrayList<Colors> greenRedCornerUp = new ArrayList<>();
			greenRedCornerUp.add(yellowSide[2][0]);
			greenRedCornerUp.add(greenSide[0][0]);
			greenRedCornerUp.add(redSide[0][2]);
			while(greenRedCornerUp.contains(Colors.WHITE)) {
				u();
				solution += "U ";
				greenRedCornerUp.clear();
				greenRedCornerUp.add(yellowSide[2][0]);
				greenRedCornerUp.add(greenSide[0][0]);
				greenRedCornerUp.add(redSide[0][2]);
			}
			f();
			u();
			fPrime();
			solution += "F U F' ";
		}

		ArrayList<Colors> orangeBlueCornerDown = new ArrayList<>();
		orangeBlueCornerDown.add(whiteSide[2][2]);
		orangeBlueCornerDown.add(orangeSide[2][2]);
		orangeBlueCornerDown.add(blueSide[2][0]);

		if(orangeBlueCornerDown.contains(Colors.WHITE) && (blueSide[2][0] != Colors.BLUE || orangeSide[2][2] != Colors.ORANGE)) {
			ArrayList<Colors> orangeBlueCornerUp = new ArrayList<>();
			orangeBlueCornerUp.add(yellowSide[0][2]);
			orangeBlueCornerUp.add(blueSide[0][0]);
			orangeBlueCornerUp.add(orangeSide[0][2]);
			while(orangeBlueCornerUp.contains(Colors.WHITE)) {
				u();
				solution += "U ";
				orangeBlueCornerUp.clear();
				orangeBlueCornerUp.add(yellowSide[0][2]);
				orangeBlueCornerUp.add(blueSide[0][0]);
				orangeBlueCornerUp.add(orangeSide[0][2]);
			}
			b();
			u();
			bPrime();
			solution += "B U B' ";
		}

		ArrayList<Colors> blueRedCornerDown = new ArrayList<>();
		blueRedCornerDown.add(whiteSide[2][0]);
		blueRedCornerDown.add(blueSide[2][2]);
		blueRedCornerDown.add(redSide[2][0]);
		if(blueRedCornerDown.contains(Colors.WHITE) && (blueSide[2][2] != Colors.BLUE || redSide[2][0] != Colors.RED)) {
			ArrayList<Colors> blueRedCornerUp = new ArrayList<>();
			blueRedCornerUp.add(yellowSide[0][0]);
			blueRedCornerUp.add(blueSide[0][2]);
			blueRedCornerUp.add(redSide[0][0]);
			while(blueRedCornerUp.contains(Colors.WHITE)) {
				u();
				solution += "U ";
				blueRedCornerUp.clear();
				blueRedCornerUp.add(yellowSide[0][0]);
				blueRedCornerUp.add(blueSide[0][2]);
				blueRedCornerUp.add(redSide[0][0]);
			}
			l();
			u();
			lPrime();
			solution += "L U L' ";
		}
		if(whiteSide[0][2] != Colors.WHITE) {
			ArrayList<Colors> greenOrangeTop = new ArrayList<>();
			greenOrangeTop.add(yellowSide[2][2]);
			greenOrangeTop.add(greenSide[0][2]);
			greenOrangeTop.add(orangeSide[0][0]);
			while(!greenOrangeTop.contains(Colors.WHITE) || !greenOrangeTop.contains(Colors.GREEN) || !greenOrangeTop.contains(Colors.ORANGE)) {
				u();
				solution += "U ";
				greenOrangeTop.clear();
				greenOrangeTop.add(yellowSide[2][2]);
				greenOrangeTop.add(greenSide[0][2]);
				greenOrangeTop.add(orangeSide[0][0]);
			}
			if(yellowSide[2][2] == Colors.WHITE) {
				sexyMove();
				sexyMove();
				sexyMove();
				solution += "R U R' U' R U R' U' R U R' U' ";
			} else if(orangeSide[0][0] == Colors.WHITE) {
				sexyMove();
				solution += "R U R' U' ";
			} else {
				reverseSexyMove();
				solution += "U R U' R' ";
			}
		}

		if(whiteSide[0][0] != Colors.WHITE) {
			ArrayList<Colors> greenRedTop = new ArrayList<>();
			greenRedTop.add(yellowSide[2][0]);
			greenRedTop.add(greenSide[0][0]);
			greenRedTop.add(redSide[0][2]);
			while(!greenRedTop.contains(Colors.WHITE) || !greenRedTop.contains(Colors.GREEN) || !greenRedTop.contains(Colors.RED)) {
				u();
				solution += "U ";
				greenRedTop.clear();
				greenRedTop.add(yellowSide[2][0]);
				greenRedTop.add(greenSide[0][0]);
				greenRedTop.add(redSide[0][2]);
			}
			if(yellowSide[2][0] == Colors.WHITE) {
				fSexy();
				fSexy();
				fSexy();
				solution += "F U F' U' F U F' U' F U F' U' ";
			} else if(greenSide[0][0] == Colors.WHITE) {
				fSexy();
				solution += "F U F' U' ";
			} else {
				reverseFSexy();
				solution += "U F U' F' ";
			}
		}

		if(whiteSide[2][2] != Colors.WHITE) {
			ArrayList<Colors> orangeBlueTop = new ArrayList<>();
			orangeBlueTop.add(blueSide[0][0]);
			orangeBlueTop.add(orangeSide[0][2]);
			orangeBlueTop.add(yellowSide[0][2]);
			while(!orangeBlueTop.contains(Colors.WHITE) || !orangeBlueTop.contains(Colors.ORANGE) || !orangeBlueTop.contains(Colors.BLUE)) {
				u();
				solution += "U ";
				orangeBlueTop.clear();
				orangeBlueTop.add(orangeSide[0][2]);
				orangeBlueTop.add(blueSide[0][0]);
				orangeBlueTop.add(yellowSide[0][2]);
			}
			if(yellowSide[0][2] == Colors.WHITE) {
				bSexy();
				bSexy();
				bSexy();
				solution += "B U B' U' B U B' U' B U B' U' ";
			} else if(blueSide[0][0] == Colors.WHITE) {
				bSexy();
				solution += "B U B' U' ";
			} else {
				reverseBSexy();
				solution += "U B U' B' ";
			}
		}


		if(whiteSide[2][0] != Colors.WHITE) {
			ArrayList<Colors> redBlueTop = new ArrayList<>();
			redBlueTop.add(blueSide[0][2]);
			redBlueTop.add(redSide[0][0]);
			redBlueTop.add(yellowSide[0][0]);
			while(!redBlueTop.contains(Colors.WHITE) || !redBlueTop.contains(Colors.RED) || !redBlueTop.contains(Colors.BLUE)) {
				u();
				solution += "U ";
				redBlueTop.clear();
				redBlueTop.add(blueSide[0][2]);
				redBlueTop.add(redSide[0][0]);
				redBlueTop.add(yellowSide[0][0]);
			}
			if(yellowSide[0][0] == Colors.WHITE) {
				lSexy();
				lSexy();
				lSexy();
				solution += "L U L' U' L U L' U' L U L' U' ";
			} else if(redSide[0][0] == Colors.WHITE) {
				lSexy();
				solution += "L U L' U' ";
			} else {
				reverseLSexy();
				solution += "U L U' L' ";
			}
		}
	}




	
	public void solveSecondLayer() {
		ArrayList<Colors> greenOrangeEdge = new ArrayList<>();
		greenOrangeEdge.add(greenSide[1][2]);
		greenOrangeEdge.add(orangeSide[1][0]);
		if(!greenOrangeEdge.contains(Colors.YELLOW) && (greenSide[1][2] != Colors.GREEN || orangeSide[1][0] != Colors.ORANGE)) {
			ArrayList<Colors> insertGO = new ArrayList<>();
			insertGO.add(greenSide[0][1]);
			insertGO.add(yellowSide[2][1]);
			while(!insertGO.contains(Colors.YELLOW)) {
				u();
				solution += "U ";
				insertGO.clear();
				insertGO.add(greenSide[0][1]);
				insertGO.add(yellowSide[2][1]);
			}
			u();
			sexyMove();
			fPrimeLeftSexy();
			solution += "U R U R' U' F' U' F U ";
		}

		ArrayList<Colors> greenRedEdge = new ArrayList<>();
		greenRedEdge.add(greenSide[1][0]);
		greenRedEdge.add(redSide[1][2]);
		if(!greenRedEdge.contains(Colors.YELLOW) && (greenSide[1][0] != Colors.GREEN || redSide[1][2] != Colors.RED)) {
			ArrayList<Colors> insertGR = new ArrayList<>();
			insertGR.add(greenSide[0][1]);
			insertGR.add(yellowSide[2][1]);
			while(!insertGR.contains(Colors.YELLOW)) {
				u();
				solution += "U ";
				insertGR.clear();
				insertGR.add(greenSide[0][1]);
				insertGR.add(yellowSide[2][1]);
			}
			uPrime();
			leftSexyMove();
			fLeftSexy();
			solution += "U' L' U' L U F U F' U' ";
		}

		ArrayList<Colors> blueRedEdge = new ArrayList<>();
		blueRedEdge.add(redSide[1][0]);
		blueRedEdge.add(blueSide[1][2]);
		if(!blueRedEdge.contains(Colors.YELLOW) && (redSide[1][0] != Colors.RED || blueSide[1][2] != Colors.BLUE)) {
			ArrayList<Colors> insertBR = new ArrayList<>();
			insertBR.add(redSide[0][1]);
			insertBR.add(yellowSide[1][0]);
			while(!insertBR.contains(Colors.YELLOW)) {
				u();
				solution += "U ";
				insertBR.clear();
				insertBR.add(redSide[0][1]);
				insertBR.add(yellowSide[1][0]);
			}
			insertBlueRedFacingRed();
			solution += "U' B' U' B U L U L' U' ";
		}

		ArrayList<Colors> blueOrangeEdge = new ArrayList<>();
		blueOrangeEdge.add(orangeSide[1][2]);
		blueOrangeEdge.add(blueSide[1][0]);
		if(!blueOrangeEdge.contains(Colors.YELLOW) && (blueSide[1][0] != Colors.BLUE || orangeSide[1][2] != Colors.ORANGE)) {
			ArrayList<Colors> insertBO = new ArrayList<>();
			insertBO.add(blueSide[0][1]);
			insertBO.add(yellowSide[0][1]);
			while(!insertBO.contains(Colors.YELLOW)) {
				u();
				solution += "U ";
				insertBO.clear();
				insertBO.add(blueSide[0][1]);
				insertBO.add(yellowSide[0][1]);
			}
			insertBlueOrangeFacingBlue();
			solution += "U' R' U' R U B U B' U' ";
		}
		ArrayList<Colors> frontEdge = new ArrayList<>();
		frontEdge.add(greenSide[0][1]);
		frontEdge.add(yellowSide[2][1]);
		while(true) {
			if(frontEdge.contains(Colors.BLUE) && frontEdge.contains(Colors.RED)) {
				if(greenSide[0][1] == Colors.BLUE) {
					u2();
					insertBlueRedFacingBlue();
					solution += "U' L U L' U' B' U' B U ";
				} else {
					u();
					insertBlueRedFacingRed();
					solution += "B' U' B U L U L' U' ";
				}
			} else if(frontEdge.contains(Colors.BLUE) && frontEdge.contains(Colors.ORANGE)) {
				if(greenSide[0][1] == Colors.BLUE) {
					u2();
					insertBlueOrangeFacingBlue();
					solution += "U R' U' R U B U B' U' ";
				} else {
					uPrime();
					insertBlueOrangeFacingOrange();
					solution += "B U B' U' R' U' R U ";
				}
			} else if(frontEdge.contains(Colors.GREEN) && frontEdge.contains(Colors.ORANGE)) {
				if(greenSide[0][1] == Colors.GREEN) {
					insertGreenOrangeFacingGreen();
					solution += "U R U R' U' F' U' F U ";
				} else {
					uPrime();
					insertGreenOrangeFacingOrange();
					solution += "U2 F' U' F U R U R' U' ";
				}
			} else {
				if(greenSide[0][1] == Colors.GREEN) {
					insertGreenRedFacingGreen();
					solution += "U' L' U' L U F U F' U' ";
				} else {
					u();
					insertGreenRedFacingRed();
					solution += "U2 F U F' U' L' U' L U ";
				}
			}
			if(greenSide[1][2] == Colors.GREEN && orangeSide[1][0] == Colors.ORANGE && orangeSide[1][2] == Colors.ORANGE && blueSide[1][0] == Colors.BLUE && blueSide[1][2] == Colors.BLUE && redSide[1][0] == Colors.RED && redSide[1][2] == Colors.RED) {
				break;
			}
			frontEdge.clear();
			frontEdge.add(greenSide[0][1]);
			frontEdge.add(yellowSide[2][1]);
			while(frontEdge.contains(Colors.YELLOW)) {
				u();
				solution += "U ";
				frontEdge.clear();
				frontEdge.add(greenSide[0][1]);
				frontEdge.add(yellowSide[2][1]);
			}
		}

	}

	

	public void solveYellowCross() {
		int flippedYellowEdges = 0;

		for(int turn = 0; turn < 4; turn++) {
			if(yellowSide[2][1] == Colors.YELLOW) {
				flippedYellowEdges++;
			}
			u();
		}

		if(flippedYellowEdges == 0) {
			solveDotCase();
			solution += "F R U R' U' F' B U L U' L' B' ";
		}
		if(flippedYellowEdges == 2) {
			if(yellowSide[0][1] == Colors.YELLOW && yellowSide[1][2] == Colors.YELLOW) {
				u();
				solveLCase();
				solution += "U B U L U' L' B' ";
			} else if(yellowSide[0][1] == Colors.YELLOW && yellowSide[1][0]== Colors.YELLOW) {
				u2();
				solveLCase();
				solution += "U2 B U L U' L' B' ";
			} else if(yellowSide[2][1] == Colors.YELLOW && yellowSide[1][0]== Colors.YELLOW) {
				uPrime();
				solveLCase();
				solution += "U' B U L U' L' B' ";
			} else if(yellowSide[2][1] == Colors.YELLOW && yellowSide[1][2]== Colors.YELLOW) {
				solveLCase();
				solution += "B U L U' L' B' ";
			} else if(yellowSide[0][1] == Colors.YELLOW && yellowSide[2][1] == Colors.YELLOW) {
				u();
				solveLineCase();
				solution += "U F R U R' U' F' ";
			} else {
				solveLineCase();
				solution += "F R U R' U' F' ";
			}
		}

	}

	public void orientYellowCorners() {
		ArrayList<Colors> redGreenYellow = new ArrayList<>();
		boolean redGreenSolved = false;
		ArrayList<Colors> orangeGreenYellow = new ArrayList<>();
		boolean orangeGreenSolved = false;
		ArrayList<Colors> orangeBlueYellow = new ArrayList<>();
		boolean orangeBlueSolved = false;
		ArrayList<Colors> redBlueYellow = new ArrayList<>();
		boolean redBlueSolved = false;
		int numCorrect = 0;

		for(int turn = 0; turn < 4; turn++) {
			redGreenYellow.add(yellowSide[2][0]);
			redGreenYellow.add(greenSide[0][0]);
			redGreenYellow.add(redSide[0][2]);

			orangeGreenYellow.add(greenSide[0][2]);
			orangeGreenYellow.add(orangeSide[0][0]);
			orangeGreenYellow.add(yellowSide[2][2]);

			orangeBlueYellow.add(yellowSide[0][2]);
			orangeBlueYellow.add(orangeSide[0][2]);
			orangeBlueYellow.add(blueSide[0][0]);

			redBlueYellow.add(redSide[0][0]);
			redBlueYellow.add(yellowSide[0][0]);
			redBlueYellow.add(blueSide[0][2]);

			if(redGreenYellow.contains(Colors.RED) && redGreenYellow.contains(Colors.GREEN)) {
				numCorrect++;
				redGreenSolved = true;
			}
			if(orangeGreenYellow.contains(Colors.ORANGE) && orangeGreenYellow.contains(Colors.GREEN)) {
				numCorrect++;
				orangeGreenSolved = true;
			}
			if(orangeBlueYellow.contains(Colors.ORANGE) && orangeBlueYellow.contains(Colors.BLUE)) {
				numCorrect++;
				orangeBlueSolved = true;
			}
			if(redBlueYellow.contains(Colors.RED) && redBlueYellow.contains(Colors.BLUE)) {
				numCorrect++;
				redBlueSolved = true;
			}

			if(numCorrect == 4) {
				return;
			}else if(numCorrect == 2) {
				break;
			} else {
				redGreenSolved = false;
				orangeGreenSolved = false;
				orangeBlueSolved = false;
				redBlueSolved = false;
				orangeBlueYellow.clear();
				orangeGreenYellow.clear();
				redBlueYellow.clear();
				redGreenYellow.clear();
				u();
				solution += "U ";
				numCorrect = 0;
			}
		}
		// 5 cases: 4 cases for the direct swaps and 1 case for the diagonal swap
		//can be made more efficient/less moves by using OLLs instead of sexy move swaps
		if(redGreenSolved && orangeGreenSolved) {
			redOrangeBlueSwap();
			solution += "B U B' U' B U B' U' B U B' U' R' U' R U R' U' R U R' U' R ";
		} else if(redGreenSolved && redBlueSolved) {
			blueGreenOrangeSwap();
			solution += "R U R' U' R U R' U' R U R' U' F' U' F U F' U' F U F' U' F";
		} else if(redBlueSolved && orangeBlueSolved) {
			redOrangeGreenSwap();
			solution += "F U F' U' F U F' U' F U F' U' L' U' L U L' U' L U L' U' L ";
		} else if(orangeBlueSolved && orangeGreenSolved) {
			blueGreenRedSwap();
			solution += "L U L' U' L U L' U' L U L' U' B' U' B U B' U' B U B' U' B ";
		} else if(orangeBlueSolved && redGreenSolved){
			yPerm();
			solution += "F R U' R' U' R U R' F' R U R' U' R' F R F' ";
		} else {
			NaPerm();
			solution += "R U R' U R U R' F' R U R' U' R' F R2 U' R' U2 R U' R' ";
		}
	}

	public void flipYellowCorners() {
		while(yellowSide[0][0] != Colors.YELLOW || yellowSide[0][2] != Colors.YELLOW || yellowSide[2][0] != Colors.YELLOW || yellowSide[2][2] != Colors.YELLOW) {
			if(yellowSide[2][0] != Colors.YELLOW) {
				if(redSide[0][2] == Colors.YELLOW) {
					cornerFlipSexy();
					cornerFlipSexy();
					solution += "L D L' D' L D L' D' ";
				} else {
					cornerFlipReverseSexy();
					cornerFlipReverseSexy();
					solution += "D L D' L' D L D' L' ";
				}
			}
			u();
			solution += "U ";
		}
		while(greenSide[0][0] != Colors.GREEN) {
			u();
			solution += "U ";
		}
	}

	public void permuteYellowEdges() {
		boolean blueSideSolved = false;
		boolean greenSideSolved = false;
		boolean redSideSolved = false;
		boolean orangeSideSolved = false;
		int totalSolved = 0;

		if(blueSide[0][1] == Colors.BLUE) {
			blueSideSolved = true;
			totalSolved++;
		}

		if(greenSide[0][1] == Colors.GREEN) {
			greenSideSolved = true;
			totalSolved++;
		}

		if(redSide[0][1] == Colors.RED) {
			redSideSolved = true;
			totalSolved++;
		}

		if(orangeSide[0][1] == Colors.ORANGE) {
			orangeSideSolved = true;
			totalSolved++;
		}


		if(totalSolved == 4) {
			return;
		}
		if(totalSolved == 0) {
			finishCubeGreenSolvedRight();
			solution += "R U R' U' L' U' L U U R U' R' U' L' U L ";
		}
		
		if(blueSide[0][1] == Colors.BLUE) {
			blueSideSolved = true;
		} else if(greenSide[0][1] == Colors.GREEN) {
			greenSideSolved = true;
		} else if(redSide[0][1] == Colors.RED) {
			redSideSolved = true;
		} else {
			orangeSideSolved = true;
		}
		
		if(greenSideSolved) {
			if(redSide[0][1] == Colors.ORANGE) {
				finishCubeGreenSolvedRight();
				solution += "R U R' U' L' U' L U U R U' R' U' L' U L ";
			} else {
				finishCubeGreenSolvedLeft();
				solution += "L' U' L U R U R' U' U' L' U L U R U' R' ";
			}
		} else if(orangeSideSolved) {
			if(greenSide[0][1] == Colors.BLUE) {
				finishCubeOrangeSolvedRight();
				solution += "B U B' U' F' U' F U U B U' B' U' F' U F ";
			} else {
				finishCubeOrangeSolvedLeft();
				solution += "F' U' F U B U B' U' U' F' U F U B U' B' ";

			}
		} else if(blueSideSolved) {
			if(orangeSide[0][1] == Colors.RED) {
				finishCubeBlueSolvedRight();
				solution += "L U L' U' R' U' R U U L U' L' U' R' U R ";
			} else {
				finishCubeBlueSolvedLeft();
				solution += "R' U' R U L U L' U' U' R' U R U L U' L' ";
			}
		} else {
			if(blueSide[0][1] == Colors.GREEN) {
				finishCubeRedSolvedRight();
				solution += "F U F' U' B' U' B U U F U' F' U' B' U B ";
			} else {
				finishCubeRedSolvedLeft();
				solution += "B' U' B U F U F' U' U' B' U B U F U' F' ";
			}
		}
	}
	
}
