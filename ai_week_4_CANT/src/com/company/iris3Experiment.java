package com.company;


//run multiple tests

public class iris3Experiment extends CANTExperiment {
	
  public int numInputTypes = 75;
  public int numTests = 75;
  
  private int trainTestNet[][];//offset,trainInstances
  private int trainTestInput;
  private int testNet[];
  private int firstTestAnswerCycle=0;

  public iris3Experiment () {
    trainingLength = 23000; 
    inTest = false;
    presentTrainUntil = trainingLength + (75 * numInputTypes) ;
    answers = new int[numTests];
    realAnswers = new int[numInputTypes];
	iris3Net somNet = (iris3Net) getNet("SomNet");
    int somNetSize = somNet.getSize();
    trainTestNet = new int[somNetSize][numInputTypes];
    trainTestInput = 0;
    testNet = new int[somNetSize];
  }
  
  public boolean experimentDone(int CANTStep) {
	if (CANTStep > trainingLength + (75 * numInputTypes) + 
			(75 * numTests)+ 75 )
		return true;
    return false;  
  }
  
	private void printSynapticStrength() {
		double inputToSom = 0;
		double somToHidden = 0;
		double hiddenToSom = 0;
		double hiddenToOutput = 0;
		double outputToHidden = 0;
		double somToSom = 0;
		double hiddenToHidden = 0;
		double outputToOutput = 0;

		iris3Net inputNet = (iris3Net) getNet("BaseNet");
		iris3Net somNet = (iris3Net) getNet("SomNet");
		iris3Net hiddenNet = (iris3Net) getNet("HiddenNet");
		iris3Net outputNet = (iris3Net) getNet("OutputNet");

		for (int neuronNum = 0; neuronNum < 1000; neuronNum++) {
			inputToSom = 0;
			somToHidden = 0;
			hiddenToSom = 0;
			hiddenToOutput = 0;
			outputToHidden = 0;
			somToSom = 0;
			hiddenToHidden = 0;
			outputToOutput = 0;
			
			// input
			int relevantSynapses = 0;
			if (neuronNum < 550) {
						for (int synapse = 0; synapse < inputNet.neurons[neuronNum]
						.getCurrentSynapses(); synapse++) {
					CANTNeuron toNeuron = inputNet.neurons[neuronNum].synapses[synapse].toNeuron;
					if (toNeuron.parentNet.getName().compareTo("SomNet") == 0) {
						inputToSom += inputNet.neurons[neuronNum].synapses[synapse]
								.getWeight();
						relevantSynapses++;
					}
				}
				inputToSom /= relevantSynapses;
			}

			// som
			relevantSynapses = 0;
			int forwardSynapses = 0;
			for (int synapse = 0; synapse < somNet.neurons[neuronNum]
					.getCurrentSynapses(); synapse++) {
				CANTNeuron toNeuron = somNet.neurons[neuronNum].synapses[synapse].toNeuron;
				if (toNeuron.parentNet.getName().compareTo("SomNet") == 0) {
					somToSom += somNet.neurons[neuronNum].synapses[synapse]
							.getWeight();
					relevantSynapses++;
				} else if (toNeuron.parentNet.getName().compareTo("HiddenNet") == 0) {
					somToHidden += somNet.neurons[neuronNum].synapses[synapse]
							.getWeight();
					forwardSynapses++;
				} else
					System.out.println("errir ");

			}
			somToSom /= relevantSynapses;
			somToHidden /= forwardSynapses;

			// hidden
			relevantSynapses = 0;
			forwardSynapses = 0;
			int backwardSynapses = 0;
			for (int synapse = 0; synapse < hiddenNet.neurons[neuronNum]
					.getCurrentSynapses(); synapse++) {
				CANTNeuron toNeuron = hiddenNet.neurons[neuronNum].synapses[synapse].toNeuron;
				if (toNeuron.parentNet.getName().compareTo("HiddenNet") == 0) {
					hiddenToHidden += hiddenNet.neurons[neuronNum].synapses[synapse]
							.getWeight();
					relevantSynapses++;
				} else if (toNeuron.parentNet.getName().compareTo("OutputNet") == 0) {
					hiddenToOutput += hiddenNet.neurons[neuronNum].synapses[synapse]
							.getWeight();
					forwardSynapses++;
				} else if (toNeuron.parentNet.getName().compareTo("SomNet") == 0) {
					hiddenToSom += hiddenNet.neurons[neuronNum].synapses[synapse]
							.getWeight();
					backwardSynapses++;
				} else
					System.out.println("error ");
			}
			hiddenToHidden /= relevantSynapses;
			hiddenToOutput /= forwardSynapses;
			hiddenToSom /= backwardSynapses;

			// output
			if (neuronNum < 150) {
				relevantSynapses = 0;
				backwardSynapses = 0;
				for (int synapse = 0; synapse < outputNet.neurons[neuronNum]
						.getCurrentSynapses(); synapse++) {
					CANTNeuron toNeuron = outputNet.neurons[neuronNum].synapses[synapse].toNeuron;
					if (toNeuron.parentNet.getName().compareTo("OutputNet") == 0) {
						outputToOutput += outputNet.neurons[neuronNum].synapses[synapse]
								.getWeight();
						relevantSynapses++;
					} else if (toNeuron.parentNet.getName().compareTo(
							"HiddenNet") == 0) {
						outputToHidden += outputNet.neurons[neuronNum].synapses[synapse]
								.getWeight();
						backwardSynapses++;
					}
				}
				outputToOutput /= relevantSynapses;
				outputToHidden /= backwardSynapses;
			}

			System.out.print(neuronNum + " " + inputToSom + " " + somToSom
					+ " ");
			System.out.print(somToHidden + " " + hiddenToHidden + " "
					+ hiddenToSom);
			System.out.println(" " + hiddenToOutput + " " + outputToOutput
					+ " " + outputToHidden);
		}
	}

  public void switchToTest () {
    System.out.println("swithctotest ");
    inTest = true;
	iris3Net inputNet = (iris3Net) getNet("BaseNet");
	iris3Net somNet = (iris3Net) getNet("SomNet");
	iris3Net hiddenNet = (iris3Net) getNet("HiddenNet");
	iris3Net outputNet = (iris3Net) getNet("OutputNet");
	inputNet.setLearningOn(false);
	somNet.setLearningOn(false);
	hiddenNet.setLearningOn(false);
	outputNet.setLearningOn(false);
	//printSynapticStrength();
  }
  
  private void  printNumFiring(int currentStep) {
		iris3Net somNet = (iris3Net) getNet("SomNet");
		int numFiring = 0;
		
		for (int i = 0; i < somNet.getSize(); i ++)
			if (somNet.neurons[i].getFired()) numFiring++;
		
		//System.out.println(currentStep + " " + numFiring);  
  }
  
  public boolean isEndEpoch(int currentStep) {
	iris3Net inputNet = (iris3Net) getNet("BaseNet");
	
	if ((currentStep %5000) == 0)
	  {
		//inputNet.printSomLocales();
		//inputNet.setLearningRate(inputNet.getLearningRate()*0.7f);
	   }
	printNumFiring(currentStep);
	
    if ((currentStep% inputNet.getCyclesPerRun()) == 0 ) 
    	{
    	iris3CANT.resetForNewTest();
    	return (true);
    	}
    return (false);
    
}
  //put the train som net into a fired array.  We'll use this to 
  //compare later.
	private void saveTrainNet() {
		iris3Net somNet = (iris3Net) getNet("SomNet");
		//int measureStep = iris3CANT.CANTStep - 55;
		for (int measureStep = iris3CANT.CANTStep -75; 
				measureStep < iris3CANT.CANTStep; measureStep++)
		for (int offset = 0; offset < somNet.getSize(); offset++) {
				trainTestNet[offset][trainTestInput] += somNet.measure
						.getActiveState(measureStep, offset);
		}
		trainTestInput++;
	}
	
	private void saveTheTestNet() {
		iris3Net somNet = (iris3Net) getNet("SomNet");

		for (int offset = 0; offset < somNet.getSize(); offset++) {
			testNet[offset] = 0;
		}

		//int measureStep = iris3CANT.CANTStep - 30;
		for (int measureStep = iris3CANT.CANTStep -75; 
				measureStep < iris3CANT.CANTStep; measureStep++)
		for (int offset = 0; offset < somNet.getSize(); offset++) {
			testNet[offset] += somNet.measure.getActiveState(measureStep, offset);
		}
		trainTestInput++;
	}

	public void debugPrintTrainTestNets(){
	    for ( int counter = 0; counter < 1000; counter++ )
	       if ((trainTestNet[counter][0] == 0) &&
	           (trainTestNet[counter][1] == 0))
	    	   
		    System.out.println("dTTT " +  counter);
	}
	
	public void printTrainTestNet() {
		iris3Net somNet = (iris3Net) getNet("SomNet");
		int size = somNet.getSize();
		iris3CANT.updateIncomingStrengths();
		for (int neuron = 0; neuron < size; neuron++) {
			int testsFired = 0;
			int neuronsFired=0;
			for (int test = 0; test < numInputTypes; test++) {
				if (trainTestNet[neuron][test]>0) {
					testsFired++;
					neuronsFired += trainTestNet[neuron][test];
				}
			}

			System.out.println("pTTT " +neuron+ " " + testsFired + " " + 
			  neuronsFired + " " + somNet.neurons[neuron].incomingStrength);

		}
	}
	
	  public double getTestTrainCorrelation(int testItem){
		    double Correlation;
		    double Sxx =0;
		    double Syy =0;
		    double Sxy =0;
		    double MeanS;
		    double MeanY;
		    double Sx=0;
		    double Sy=0;
			 
			iris3Net somNet = (iris3Net) getNet("SomNet");
		    int size = somNet.getSize();
	   
		    for ( int counter = 0; counter <size; counter++ )
		      Sx += testNet[counter];
		    MeanS = Sx/size;

		    for ( int counter = 0; counter < size; counter++ )
		      { Sy += trainTestNet[counter][testItem]; }
		    MeanY = Sy/size;

		    for ( int counter = 0; counter <size; counter++ ) {
		      Sxx += (testNet[counter]- MeanS)*(testNet[counter]- MeanS);
		      Syy += (trainTestNet[counter][testItem]- MeanY)*(trainTestNet[counter][testItem]- MeanY);
		      Sxy += (testNet[counter]- MeanS)*(trainTestNet[counter][testItem]- MeanY); }

		    Correlation = Sxy/(Math.sqrt (Sxx*Syy));
			return Correlation;
		  }
  
    int presentTrainUntil; //set in constructor
    int answers[];  //answers from the test data; the system doesn't know this.
    int answerCount=0;
    int realAnswers[];  //Answers from the training data.
    int realAnswerCount=0;
    boolean testFileOpened = false;

	public void endEpoch() {
		// read a pattern from the file.
		iris3Net inputNet = (iris3Net) getNet("BaseNet");
		iris3Net outputNet = (iris3Net) getNet("OutputNet");
		if (inTest && (iris3CANT.CANTStep <= presentTrainUntil)) {
			if (firstTestAnswerCycle == 0) {
				firstTestAnswerCycle = iris3CANT.CANTStep;
				// System.out.println("FTA " + firstTestAnswerCycle);
			} else {
				saveTrainNet();
				realAnswers[realAnswerCount++] = correctAnswer;
			}
		}
		if (inTest && (iris3CANT.CANTStep > presentTrainUntil)
				&& (!testFileOpened)) {
			testFileOpened = true;
			realAnswers[realAnswerCount++] = correctAnswer;
			iris3CANT.setTestReader();
			iris3CANT.patReader.setIrisPattern(inputNet, this, true);
			inputNet.setNeuronsToStimulate(40);
			outputNet.setNeuronsToStimulate(0);
			saveTrainNet();
		} else if (inTest && (iris3CANT.CANTStep > presentTrainUntil)) {
			iris3CANT.patReader.setIrisPattern(inputNet, this, true);
			inputNet.setNeuronsToStimulate(40);
			outputNet.setNeuronsToStimulate(0);
			answers[answerCount++] = correctAnswer;
		} else {
			iris3CANT.patReader.setIrisPattern(inputNet, this, false);
            inputNet.setNeuronsToStimulate(40);  //50
            int curPattern = correctAnswer-1;
            outputNet.setCurrentPattern(curPattern);
            outputNet.selectPattern(curPattern);
		}
	}
  
 
  public double printConnectionWeights(int step, String netName) {
	double totWeight = 0;
	int posNeurons = 0;
	iris3Net net = (iris3Net) getNet(netName);
    
	for (int i = 0; i < net.getSize(); i++) {
	  if (!net.neurons[i].isInhibitory()) {
		posNeurons++;
	    for (int synapse=0;synapse < net.neurons[i].getCurrentSynapses(); synapse++) {
		  totWeight += net.neurons[i].synapses[synapse].getWeight(); 
	    }    	   
	  }
	}
	return totWeight/posNeurons;
	//System.out.println("Weights " + step + " " + totWeight/totSynapses);
  } 
    	
  public void printExpName () {
    System.out.println("Iris3 Exp");
  }
  
   
  private int correctCategorisations = 0;
  private int correctOutputCategorisations = 0;
  private int bestTestNum = -1;
  private void printADecision (int currentStep) {  
	   saveTheTestNet();
	   double bestResult = 0;
	   int answer = -1;
	   for (int testNum = 0; testNum < numInputTypes; testNum ++)
	   {
  	     double result = getTestTrainCorrelation(testNum);
//System.out.println("Compare Answer " + correctAnswer + " " + result + " " + realAnswers[testNum]);	  
  	     if (result > bestResult) {
  	    	 answer = realAnswers[testNum];
  	    	 bestTestNum = testNum;
  	    	 bestResult = result;
  	     } 
	   }
	   if (correctAnswer == answer) correctCategorisations++;
	   //System.out.println("Real Answer " + bestTestNum + " " + correctAnswer + " " + answer);	  
  }

    private int categorySize = 50;
	private void printAnotherDecision() {
		iris3Net outputNet = (iris3Net) getNet("OutputNet");
		int categoryA = 0;
		int categoryB = 0;
		int categoryC = 0;
		int answer = 0;
		for (int measureStep = iris3CANT.CANTStep - 75; measureStep < iris3CANT.CANTStep; measureStep++) {
			for (int offset = 0; offset < categorySize; offset++) {
				if (outputNet.measure.getActiveState(measureStep, offset) == 1)
					categoryA++;
				if (outputNet.measure.getActiveState(measureStep, offset+categorySize) == 1)
					categoryB++;
				if (outputNet.measure.getActiveState(measureStep, offset+(2*categorySize)) == 1)
					categoryC++;
			}
		}
		if ((categoryA > categoryB) && (categoryA > categoryC)) answer = 1;
		else if ((categoryB > categoryA) && (categoryB > categoryC)) { 
			answer = 2;
			
		}
		else answer = 3;
		if (answer == correctAnswer) correctOutputCategorisations++;
		//System.out.println("OutDec " + categoryA + " " + categoryB + " "
			//	+ categoryC + " " + correctAnswer + " " + correctOutputCategorisations);
	}

	public void measure(int currentStep) {
		if (currentStep <= trainingLength + (75 * numInputTypes))
			return;

		if (((currentStep - trainingLength) % 75) == 0) {
			printADecision(currentStep);
			printAnotherDecision();
		}
		if (currentStep == trainingLength + (75 * numInputTypes)
				+ +(75 * numTests)) {
			System.out.println("Decisions " + correctCategorisations + " "
					+ correctOutputCategorisations);
			//printTrainTestNet();
		}
	}
  
} //end class

