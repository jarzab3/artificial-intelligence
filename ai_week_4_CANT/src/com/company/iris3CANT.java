package com.company;

import java.util.Enumeration;

public class iris3CANT extends Main {
  public static String ExperimentXMLFile;
  public static iris3Net nullNet;
  public static iris3Experiment experiment; 
  public static int kludge = 4;
  public static iris2PatternReader patReader;  //don't need a 3 because input and output is the same.
  public static globalInhibition outputGlobInhibition;
  public static globalInhibition somGlobInhibition;
  public static globalInhibition hiddenGlobInhibition;
  public static String trainNetName = "src/irisTrain.txt";
  public static String testNetName = "src/irisTest.txt";
  
  private static int testNumber = -1;
  
  public static void main(String args[]){
System.out.println("initialize CANT iris3 ");
   	ExperimentXMLFile = "src/iris3.xml";
    seed = 1;
    initRandom();
    readNewSystem();
    positionWindows();
    delayBetweenSteps=0;
    patReader = new iris2PatternReader(trainNetName);
    outputGlobInhibition = new globalInhibition();
    somGlobInhibition = new globalInhibition("SomNet",100);
    hiddenGlobInhibition = new globalInhibition("HiddenNet",100);
  }
  
  public static void setTestReader() {
	    patReader = new iris2PatternReader(testNetName);	  
  }

  
	protected static void readNewSystem() {
		//System.out.println("readNewSystem");
			nullNet = new iris3Net();

			nets = NetManager.readNets(ExperimentXMLFile, nullNet);
			workerThread = new iris3CANT.WorkerThread();
			initializeExperiment();
			// experiment.printExpName();
			workerThread.start();
			connectAllNets();

			iris3Net somNet = (iris3Net) experiment.getNet("SomNet");
			somNet.zeroIncomingStrengths();
			updateIncomingStrengths();
			somNet.setCyclesPerRun(20);
			patReader = new iris2PatternReader(trainNetName);
		System.out.println(testNumber + " Training On " + trainNetName
				+ " Testing on " + testNetName);
	}
  
  private static void connectAllNets() {
    iris3Net baseNet = (iris3Net)experiment.getNet("BaseNet");
	iris3Net somNet = (iris3Net)experiment.getNet("SomNet");
	iris3Net hiddenNet = (iris3Net)experiment.getNet("HiddenNet");
	iris3Net outputNet = (iris3Net)experiment.getNet("OutputNet");
    
	baseNet.connectBaseToSomNet(somNet);
	somNet.connectSomToHiddenNet(hiddenNet);
	hiddenNet.connectHiddenToSomNet(somNet);
	hiddenNet.connectHiddenToOutputNet(outputNet);
	outputNet.connectOutputToHiddenNet(hiddenNet);
  }


  //set up the experiment specific parameters.
  private static void initializeExperiment() {
    experiment = new iris3Experiment();
    //System.out.println("initialize som1 Experiment");
    experiment.printExpName();
  }

  
	public static void updateIncomingStrengths() {
		// loop through all nets
		// Enumeration <?> eNum = Main.nets.elements();
		// while (eNum.hasMoreElements()) {
		// CANTNet net = (CANTNet)eNum.nextElement();

		// consider if input is also learning with post compensatory need to fix
		// this.
		iris3Net net = (iris3Net) experiment.getNet("SomNet");
		updateIncomingStrengths(net);
		net = (iris3Net) experiment.getNet("HiddenNet");
		updateIncomingStrengths(net);
		net = (iris3Net) experiment.getNet("OutputNet");
		updateIncomingStrengths(net);
	}

	private static void updateIncomingStrengths(iris3Net toNet) {
		String toNetName = toNet.getName();
		// loop through all nets
		Enumeration<?> eNum = nets.elements();
		while (eNum.hasMoreElements()) {
			iris3Net fromNet = (iris3Net) eNum.nextElement();
			// loop through all neurons
			for (int cNeuron = 0; cNeuron < fromNet.getTotalNeurons(); cNeuron++) {
				// loop through all synapses
				for (int cSynapse = 0; cSynapse < fromNet.neurons[cNeuron]
						.getCurrentSynapses(); cSynapse++) {
					Synapse currentSynapse = fromNet.neurons[cNeuron].synapses[cSynapse];
					if (toNetName.compareTo(currentSynapse.toNeuron.parentNet
							.getName()) == 0) {
						if (currentSynapse.getWeight() > 0) {
							double currentIncomingWeight = currentSynapse.toNeuron.getIncomingStrength();
	                        currentSynapse.toNeuron.setIncomingStrength(currentIncomingWeight
											+ currentSynapse.getWeight());
						}
					}
				}
			}
		}
	}

	private static void oldUpdateIncomingStrengths(iris3Net net) {
		// loop through all neurons
		for (int cNeuron = 0; cNeuron < net.getTotalNeurons(); cNeuron++) {
			// loop through all synapses
			for (int cSynapse = 0; cSynapse < net.neurons[cNeuron]
					.getCurrentSynapses(); cSynapse++) {
				if (net.neurons[cNeuron].synapses[cSynapse].getWeight() > 0) {
					double currentIncomingWeight = ((CANTNeuronSpontaneousFatigue) net.neurons[cNeuron].synapses[cSynapse].toNeuron)
							.getIncomingStrength();
					((CANTNeuronSpontaneousFatigue) net.neurons[cNeuron].synapses[cSynapse].toNeuron)
							.setIncomingStrength(currentIncomingWeight
									+ net.neurons[cNeuron].synapses[cSynapse]
											.getWeight());
				}
			}
		}
	}

  public static void runOneStepStart() {
	//iris3Net net = (iris3Net) experiment.getNet("SomNet");
	//int somNeuronsFired = net.getNeuronsFired();
	//net = (iris3Net) experiment.getNet("HiddenNet");
	//int hiddenNeuronsFired = net.getNeuronsFired();
	//System.out.println(CANTStep + " " + somNeuronsFired + " " + hiddenNeuronsFired);

    if (experiment.trainingLength == CANTStep ) 
    	experiment.switchToTest();
    
    //if (experiment.getInTest()) 
    	experiment.measure(CANTStep);
 
    if (experiment.isEndEpoch(CANTStep))
      experiment.endEpoch();
  }
  
  public static synchronized void resetForNewTest() {
	if(!experiment.getInTest()) return;

	Enumeration <?> eNum = nets.elements();
    while (eNum.hasMoreElements()) {
      iris3Net net = (iris3Net)eNum.nextElement();
      //      net.setInitialFatigue(-1.0);
      for (int i = 0; i < net.getSize(); i++) {
 	     net.neurons[i].setFatigue((float)0.0);
	     net.neurons[i].setActivation(0.0);
      }
    }
  }

  private static int numSystems = 1;
  public static synchronized int getNumSystems() {return numSystems;}
  
  public static synchronized void runOneStep() {
    //runOneStepStart();

      //System.out.println("Step " + CANTStep);
	  
	//this assumes only somNet has fatiguing neurons.
    iris3Net somNet = (iris3Net)experiment.getNet("SomNet");
    somNet.zeroIncomingStrengths();
    iris3Net hiddenNet = (iris3Net)experiment.getNet("HiddenNet");
    hiddenNet.zeroIncomingStrengths();
    iris3Net outputNet = (iris3Net)experiment.getNet("OutputNet");
    outputNet.zeroIncomingStrengths();
    updateIncomingStrengths();

    Enumeration <?> eNum = nets.elements();
    eNum = nets.elements();
    while (eNum.hasMoreElements()) {
      iris3Net net = (iris3Net)eNum.nextElement();
    if (net.getName().compareTo("BaseNet") == 0)
      {
      net.runAllOneStep(CANTStep); 
      CANTStep++;
      }
    }
   
    //System.out.println("Incremenet cantvis1step"+CANTStep);

  if (experiment.experimentDone(CANTStep)) 
    {
    //System.out.println("experiment done "+CANTStep);
    closeSystem();
  	numSystems++;
  	readNewSystem();
  	//makeNewSystem(numSystems);
    }
  }
  
  
 private  static void positionWindows() {
    iris3Net baseNet = (iris3Net)experiment.getNet("BaseNet");
    iris3Net somNet = (iris3Net)experiment.getNet("SomNet");
    iris3Net hiddenNet = (iris3Net)experiment.getNet("HiddenNet");
    iris3Net outputNet = (iris3Net)experiment.getNet("OutputNet");
  
    baseNet.cantFrame.setLocation(0,0);
    baseNet.cantFrame.setSize (500,400);
    baseNet.cantFrame.setVisible(true);  
   
  
    somNet.cantFrame.setLocation(500,0);
    somNet.cantFrame.setSize (200,400);
    somNet.cantFrame.setVisible(true);  

    hiddenNet.cantFrame.setLocation(700,0);
    hiddenNet.cantFrame.setSize (200,400);
    hiddenNet.cantFrame.setVisible(true);  

    outputNet.cantFrame.setLocation(900,0);
    outputNet.cantFrame.setSize (200,400);
    //somNet.cantFrame.matrix.addStringsToPrint ("0",30,180);
    //somNet.cantFrame.matrix.addStringsToPrint ("1",50);
    outputNet.cantFrame.setVisible(true);  
  }
  
  //embedded Thread class
  public static class WorkerThread extends Main.WorkerThread{
    public void run(){
      //System.out.println("xor Thread ");
      while(true){
         if(isRunning){
           runOneStep();
         }
         else{
           try{sleep(delayBetweenSteps);}
		   catch(InterruptedException ie){ie.printStackTrace();}
             }//else
       }//while
    }//run
  }//WorkerThread class
}

