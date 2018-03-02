import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.*;

public class patternReader {
	protected String dataFileName;
	protected LineNumberReader inputFile;
	private int numInputFeatures;
	private int[] inputFeatures;
	private int neuronsPerInputVal;
	private int[] valsPerInputFeature;
	private int[] offsetsByInputFeature;
	private int outputVal;
	private int neuronsPerOutputFeature;

	protected static int MAXPATTERNSIZE = 800;

	public patternReader(String fileToRead) {
		dataFileName = new String(fileToRead);
		openReadFile();
	}

	// Set the input file to the name specified in dataFileName
	private void openReadFile() {
		DataInputStream dIS;
		InputStreamReader inputSR;

		try {
			dIS = new DataInputStream(new FileInputStream(dataFileName));
			inputSR = new InputStreamReader(dIS);
			inputFile = new LineNumberReader(inputSR);
		}

		catch (IOException e) {
			System.err.println("input file not opened properly\n"
					+ e.toString());
			System.exit(1);
		}
	}

	public void switchReadFile(String fileToRead) {
		try {
			inputFile.close();
		} catch (IOException e) {
			System.err.println("file not closed properly\n" + e.toString());
			System.exit(1);
		}
		dataFileName = fileToRead;
		openReadFile();
	}

	public int getPattern(int step) {
		return 0;
	}

	/************** Functions for setting up the features neuron relations ******/

	public void setInputFeatures(int features) {
		numInputFeatures = features;
		inputFeatures = new int[numInputFeatures];
		valsPerInputFeature = new int[numInputFeatures];
		offsetsByInputFeature = new int[numInputFeatures];
	}

	public void setInputNeuronsPerFeature(int neuronsPer) {
		neuronsPerInputVal = neuronsPer;
	}

	public void setValsPerFeature(int featureNum, int vals) {
		valsPerInputFeature[featureNum] = vals;
	}

	public void setOffsets() {
		int offset = 0;
		for (int feature = 0; feature < numInputFeatures; feature++) {
			offsetsByInputFeature[feature] = offset;
			offset += valsPerInputFeature[feature];
		}
	}

	public void setOutputVal(int val) {
		outputVal = val;
	}

	public void setOutputNeuronsPerFeature(int neurons) {
		neuronsPerOutputFeature = neurons;
	}

	// undone this really is a mess. For cars, I got it to work for just the
	// input net, and
	// then
	// did a quick cut and paste for output. It could be done much more
	// sensibly.
	protected int[] points; // points is a global used by these next few functions
							// for
							// adding a new Pattern from a file
	protected int cPoints;
	protected int[] outputPoints;
	protected int cOutputPoints;

	protected int getNextFeatureInteger(StringTokenizer inputLine) {
		String inputSubstring;
		int result;
		inputSubstring = inputLine.nextToken();
		result = Integer.parseInt(inputSubstring);
		return result;
	}
	
	protected float getNextFeatureFloat(StringTokenizer inputLine) {
		String inputSubstring;
		float result;
		inputSubstring = inputLine.nextToken();
		result = Float.parseFloat(inputSubstring);
		return result;
	}

	private void addPoints(int featureVal, int numNewPoints, int offset) {
		for (int newPoint = 0; newPoint < numNewPoints; newPoint++) {
			points[cPoints++] = ((offset + featureVal) * numNewPoints)
					+ newPoint;
		}
	}
	
	protected void addYPoints(int featureVal, int numNewPoints, int offset) {
		for (int newPoint = 0; newPoint < numNewPoints; newPoint++) {
			points[cPoints++] = offset + featureVal	+ newPoint;
		}
	}

	private void addOutputPoints(int featureVal, int numNewPoints, int offset) {
		for (int newPoint = 0; newPoint < numNewPoints; newPoint++) {
			outputPoints[cOutputPoints++] = ((offset + featureVal) * numNewPoints)
					+ newPoint;
		}
	}

	private int[] setPointsFromInputFile() {
		points = new int[MAXPATTERNSIZE];
		int[] features = new int[6];
		cPoints = 0;
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

		features[0] = getNextFeatureInteger(tokenizedLine);
		addPoints(features[0], 30, 0);

		features[1] = getNextFeatureInteger(tokenizedLine);
		addPoints(features[1], 30, 4);

		features[2] = getNextFeatureInteger(tokenizedLine);
		addPoints(features[2], 30, 8);

		features[3] = getNextFeatureInteger(tokenizedLine);
		addPoints(features[3], 30, 12);

		features[4] = getNextFeatureInteger(tokenizedLine);
		addPoints(features[4], 30, 15);

		features[5] = getNextFeatureInteger(tokenizedLine);
		addOutputPoints(features[5], 30, 0);

		return points;
	}

	public void setPattern(CANTNet inputNet, CANTNet outputNet,
			CANTExperiment currentExperiment) {
		points = new int[MAXPATTERNSIZE];
		int[] features = new int[7];
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

		features[0] = getNextFeatureInteger(tokenizedLine);
		addPoints(features[0], 30, 0);

		features[1] = getNextFeatureInteger(tokenizedLine);
		addPoints(features[1], 30, 4);

		features[2] = getNextFeatureInteger(tokenizedLine);
		addPoints(features[2], 30, 8);

		features[3] = getNextFeatureInteger(tokenizedLine);
		addPoints(features[3], 30, 12);

		features[4] = getNextFeatureInteger(tokenizedLine);
		addPoints(features[4], 30, 15);

		features[5] = getNextFeatureInteger(tokenizedLine);
		addPoints(features[5], 30, 18);

		features[6] = getNextFeatureInteger(tokenizedLine);
		addOutputPoints(features[6], 100, 0);
		currentExperiment.correctAnswer = features[6];

		CANTPattern newPat = new CANTPattern(inputNet, "bob", 0, cPoints,
				points);
		inputNet.addNewPattern(newPat);

		CANTPattern newOutputPat = new CANTPattern(outputNet, "fred", 0,
				cOutputPoints, outputPoints);
		outputNet.addNewPattern(newOutputPat);

	}

	public void setYeastPattern(CANTNet inputNet,
			CANTExperiment currentExperiment, boolean test) {
		points = new int[MAXPATTERNSIZE];
		float[] features = new float[8];
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

		for (int i = 0; i < 8; i ++ ) {
		  features[i] = getNextFeatureFloat(tokenizedLine);
		  int start = ((int)(features[i]*100)) -5;
		  addYPoints(start, 10, i*110);
		}


		category = getNextFeatureInteger(tokenizedLine);
		if (!test) {
			addYPoints(category*25, 25, 880);
		}
		//System.out.print ( features[0] + " " + features[1] + " " +
		  //         features[2] + " " + features[3] + " " +
		    //       features[4] + " " + features[5] + " " +
		      //     features[6] + " " + features[7] + " " + category+ 
		        //   " \n");

		
		/*		features[1] = getNextFeatureInteger(tokenizedLine);
		addPoints(features[1], 10, 4);

		features[2] = getNextFeatureInteger(tokenizedLine);
		addPoints(features[2], 10, 8);

		features[3] = getNextFeatureInteger(tokenizedLine);
		addPoints(features[3], 10, 12);

		features[4] = getNextFeatureInteger(tokenizedLine);
		addPoints(features[4], 10, 15);

		features[5] = getNextFeatureInteger(tokenizedLine);
		addPoints(features[5], 10, 18);

		features[6] = getNextFeatureInteger(tokenizedLine);
		if (!test) {
			addPoints(features[6], 10, 21);
		}

*/
		currentExperiment.correctAnswer = category;

		CANTPattern newPat = new CANTPattern(inputNet, "bob", 0, cPoints,
				points);
		inputNet.addNewPattern(newPat);
	}

	public void setCar4Pattern(CANTNet inputNet,
			CANTExperiment currentExperiment, boolean test) {
		points = new int[MAXPATTERNSIZE];
		int[] features = new int[7];
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

		features[0] = getNextFeatureInteger(tokenizedLine);
		addPoints(features[0], 10, 0);

		features[1] = getNextFeatureInteger(tokenizedLine);
		addPoints(features[1], 10, 4);

		features[2] = getNextFeatureInteger(tokenizedLine);
		addPoints(features[2], 10, 8);

		features[3] = getNextFeatureInteger(tokenizedLine);
		addPoints(features[3], 10, 12);

		features[4] = getNextFeatureInteger(tokenizedLine);
		addPoints(features[4], 10, 15);

		features[5] = getNextFeatureInteger(tokenizedLine);
		addPoints(features[5], 10, 18);

		features[6] = getNextFeatureInteger(tokenizedLine);
		if (!test) {
			addPoints(features[6], 10, 21);
		}

		currentExperiment.correctAnswer = features[6];

		CANTPattern newPat = new CANTPattern(inputNet, "bob", 0, cPoints,
				points);
		inputNet.addNewPattern(newPat);
	}

	/*
	public void setCongressPattern(CANTNet inputNet, CANTNet outputNet) {
		String inputLine;
		StringTokenizer tokenizedLine = null;
		int outputFeature;
		outputPoints = new int[MAXPATTERNSIZE];
		cOutputPoints = 0;
		int[] featureVal = new int[numInputFeatures];
		points = new int[MAXPATTERNSIZE];
		cPoints = 0;

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

		// read the output feature (which is first).
		outputFeature = getNextFeatureInteger(tokenizedLine);
		addOutputPoints(outputFeature, neuronsPerOutputFeature, 0);
		CANT23congress.experiment.correctAnswer = outputFeature;

		// add an items to the points for each input
		for (int inputFeature = 0; inputFeature < numInputFeatures; inputFeature++) {
			featureVal[inputFeature] = getNextFeatureInteger(tokenizedLine);
			// If there's an illegitimate feature value, just don't add it to
			// the pattern.
			if ((featureVal[inputFeature] >= 0)
					&& (featureVal[inputFeature] < valsPerInputFeature[inputFeature]))
				addPoints(featureVal[inputFeature], neuronsPerInputVal,
						offsetsByInputFeature[inputFeature]);
		}
		CANTPattern newPat = new CANTPattern(inputNet, "bob", 0, cPoints,
				points);
		inputNet.addNewPattern(newPat);

		CANTPattern newOutputPat = new CANTPattern(outputNet, "fred", 0,
				cOutputPoints, outputPoints);
		outputNet.addNewPattern(newOutputPat);
	}
	*/

	// just set the input Net.
	public void setPattern(CANTNet net) {

		int[] pts = setPointsFromInputFile();

		CANTPattern newPat = new CANTPattern(net, "bob", 0, cPoints, pts);
		net.addNewPattern(newPat);

	}
}
