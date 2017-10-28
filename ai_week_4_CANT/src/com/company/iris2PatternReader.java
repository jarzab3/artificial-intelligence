package com.company;

import java.io.*;
import java.util.StringTokenizer;


public class iris2PatternReader extends patternReader{

	public iris2PatternReader(String fileToRead){
		super (fileToRead);
	}
	
	public void setIrisPattern(CANTNet inputNet,
			CANTExperiment currentExperiment, boolean test) {
		points = new int[MAXPATTERNSIZE];
		float[] features = new float[4];
		int category=0;
		outputPoints = new int[MAXPATTERNSIZE];
		cPoints = 0;
		cOutputPoints = 0;
		String inputLine;
		StringTokenizer tokenizedLine = null;
		

		// read the line
		try {
			inputLine = inputFile.readLine();

			// check for end of file. If so, close and reopen.
			if (inputLine == null) {
				inputFile.close();
				DataInputStream dIS = new DataInputStream(new FileInputStream(
						dataFileName));
				InputStreamReader inputSR = new InputStreamReader(dIS);
				inputFile = new LineNumberReader(inputSR);
				inputLine = inputFile.readLine();
			}
			// System.out.print ( inputLine + "\n");
			tokenizedLine = new StringTokenizer(inputLine);
		}
		

		catch (IOException e) {
			System.err
					.println("data input file not read properly or not reopened\n"
							+ e.toString());
			System.exit(1);
		}
		

		for (int i = 0; i < 4; i ++ ) {
		  features[i] = getNextFeatureFloat(tokenizedLine);
		  int start = ((int)(features[i]*100));
		  addYPoints(start, 10, i*110);
		}


		category = getNextFeatureInteger(tokenizedLine);
		if (!test) {
			addYPoints(category*20, 20, 440);
		}
		currentExperiment.correctAnswer = category;

		CANTPattern newPat = new CANTPattern(inputNet, "bob", 0, cPoints,
				points);
		inputNet.addNewPattern(newPat);
	}
}
