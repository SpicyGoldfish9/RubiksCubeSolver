package solver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SolverGUI {
    private RubiksCube cube;
    private JFrame frame;

    private JPanel mainPanel;
    private JPanel colorPanel;

    private JLabel currentColorLabel;
    private Color currentColor;
    private JButton whiteButton;
    private JButton yellowButton;
    private JButton blueButton;
    private JButton greenButton;
    private JButton redButton;
    private JButton orangeButton;
    private JButton getSolutionButton;

    public SolverGUI() {
    	cube = new RubiksCube();
        frame = new JFrame();
        mainPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        colorPanel = new JPanel();

        currentColorLabel = new JLabel("The current color is white.");
        currentColor = Color.WHITE;

        whiteButton = new JButton();
        whiteButton.setBackground(Color.WHITE);
        yellowButton = new JButton();
        yellowButton.setBackground(Color.YELLOW);
        blueButton = new JButton();
        blueButton.setBackground(Color.BLUE);
        greenButton = new JButton();
        greenButton.setBackground(Color.GREEN);
        redButton = new JButton();
        redButton.setBackground(Color.RED);
        orangeButton = new JButton();
        orangeButton.setBackground(Color.ORANGE);
        getSolutionButton = new JButton();
        getSolutionButton.setText("Get Solution");
        

        colorPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        colorPanel.setLayout(new GridLayout(0, 1));
        colorPanel.add(whiteButton);
        colorPanel.add(yellowButton);
        colorPanel.add(blueButton);
        colorPanel.add(greenButton);
        colorPanel.add(redButton);
        colorPanel.add(orangeButton);
        colorPanel.add(currentColorLabel);
        colorPanel.add(getSolutionButton);
        
        whiteButton.addActionListener((ActionEvent e) -> {
            currentColorLabel.setText("The current color is white.");
            currentColor = Color.WHITE;
        });

        yellowButton.addActionListener((ActionEvent e) -> {
            currentColorLabel.setText("The current color is yellow.");
            currentColor = Color.YELLOW;
        });

        blueButton.addActionListener((ActionEvent e) -> {
            currentColorLabel.setText("The current color is blue.");
            currentColor = Color.BLUE;
        });

        greenButton.addActionListener((ActionEvent e) -> {
            currentColorLabel.setText("The current color is green.");
            currentColor = Color.GREEN;
        });

        redButton.addActionListener((ActionEvent e) -> {
            currentColorLabel.setText("The current color is red.");
            currentColor = Color.RED;
        });

        orangeButton.addActionListener((ActionEvent e) -> {
            currentColorLabel.setText("The current color is orange.");
            currentColor = Color.ORANGE;
        });
        


        // Create six panels, each with a 3x3 grid of buttons
        for (int i = 0; i < 6; i++) {
            JPanel gridPanel = new JPanel(new GridLayout(3, 3));
            for (int j = 1; j <= 9; j++) {
                JButton gridButton = new JButton();
                gridButton.addActionListener((ActionEvent e) -> {
                    gridButton.setBackground(currentColor);
                });
                gridPanel.add(gridButton);
            }
            mainPanel.add(gridPanel);
        }
        
        getSolutionButton.addActionListener((ActionEvent e) -> {
        	Colors centerColor = Colors.WHITE;
        	Colors surroundingColor = Colors.WHITE;
        	
        	//the main panel contains 6 gridPanels which represent the 6 faces of the cube
        	java.awt.Component[] mainComponents = mainPanel.getComponents();
        	
            for (int i = 0; i < mainComponents.length; i++) {
                if (mainComponents[i] instanceof JPanel) {
                	//checking if the component is an instance of a JPanel and then casting if it is
                    JPanel panel = (JPanel) mainComponents[i];
                    java.awt.Component[] buttonComponents = panel.getComponents();
                    
                    //figuring out the color of the center
                    if (buttonComponents[4] instanceof JButton) {
                        JButton button = (JButton) buttonComponents[4];
                        if(button.getBackground() == Color.WHITE) {
                        	centerColor = Colors.WHITE;
                        } else if(button.getBackground() == Color.YELLOW) {
                        	centerColor = Colors.YELLOW;
                        } else if(button.getBackground() == Color.BLUE) {
                        	centerColor = Colors.BLUE;
                        } else if(button.getBackground() == Color.GREEN) {
                        	centerColor = Colors.GREEN;
                        } else if(button.getBackground() == Color.RED) {
                        	centerColor = Colors.RED;
                        } else {
                        	centerColor = Colors.ORANGE;
                        }
                    }
                    
                    
                    
                    for (int j = 0; j < buttonComponents.length; j++) {
                    	if (buttonComponents[j] instanceof JButton) {
                            JButton button = (JButton) buttonComponents[j];
                            if(button.getBackground() == Color.WHITE) {
                            	surroundingColor = Colors.WHITE;
                            } else if(button.getBackground() == Color.YELLOW) {
                            	surroundingColor = Colors.YELLOW;
                            } else if(button.getBackground() == Color.BLUE) {
                            	surroundingColor = Colors.BLUE;
                            } else if(button.getBackground() == Color.GREEN) {
                            	surroundingColor = Colors.GREEN;
                            } else if(button.getBackground() == Color.RED) {
                            	surroundingColor = Colors.RED;
                            } else {
                            	surroundingColor = Colors.ORANGE;
                            }
                        }
                    	int row = 0, col = 0;
                    	if(j == 0) {
                    		
                    	} else if(j == 1) {
                    		row = 0;
                    		col = 1;
                    	} else if(j == 2) {
                    		row = 0;
                    		col = 2;
                    	} else if(j == 3) {
                    		row = 1;
                    		col = 0;
                    	} else if(j == 4) {
                    		row = 1;
                    		col = 1;
                    	} else if(j == 5) {
                    		row = 1;
                    		col = 2;
                    	} else if(j == 6) {
                    		row = 2;
                    		col = 0;
                    	} else if(j == 7) {
                    		row = 2;
                    		col = 1;
                    	} else {
                    		row = 2;
                    		col = 2;
                    	}
                    	cube.setColor(centerColor, surroundingColor, row, col);
                    }
                }
            }
            cube.solveDaisy();
    		cube.solveCross();
    		cube.solveWhiteCorners();
    		cube.solveSecondLayer();
    		cube.solveYellowCross();
    		cube.orientYellowCorners();
    		cube.flipYellowCorners();
    		cube.permuteYellowEdges();
    		System.out.println(cube.getSolution());
        });
        
        // Use BorderLayout for the frame and add panels to different regions
        frame.setLayout(new BorderLayout());
        frame.add(colorPanel, BorderLayout.WEST);
        frame.add(mainPanel, BorderLayout.CENTER);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Rubik's Cube Solver");
        frame.setSize(800, 600); // Set the frame size
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SolverGUI();
    }
}