package com.company;

import java.util.Enumeration;

public class iris3Net extends CANTNet {
	public iris3Net() {
	}

	public iris3Net(String name, int cols, int rows, int topology) {
		super(name, cols, rows, topology);
		cyclesToStimulatePerRun = 40;
		recordingActivation = true;
	}
	
	public int getNeuronsFired() {
		int result = 0;
		for (int i = 0 ; i < getSize(); i++)
			if (neurons[i].getFired()) result++;
		return result;
	}

	// This really slows down processing.
	public void updateIncomingStrengthsSlow() {
		for (int cNeuron = 0; cNeuron < getTotalNeurons(); cNeuron++) {
			CANTNeuronSpontaneousFatigue modNeuron = (CANTNeuronSpontaneousFatigue) neurons[cNeuron];
			modNeuron.setIncomingStrength(modNeuron.getIncomingStrength(3));
		}
	}

	public void zeroIncomingStrengths() {
		for (int cNeuron = 0; cNeuron < getTotalNeurons(); cNeuron++) {
			neurons[cNeuron].setIncomingStrength(0);
		}
	}

	// need this to subclass experiment
	public void changePattern(int cantStep) {
		// note this only runs on the first step. The rest of the time it
		// is in experiment.endEpoch.
		if (cantStep == 0) {
			if (getName().compareTo("OutputNet") == 0) {
				int curPattern = getCurrentPattern();
				setCurrentPattern(curPattern);
				((CANTPattern) patterns.get(curPattern))
						.arrange(getNeuronsToStimulate());
			} 
			else  {
				int curPattern = 0;
				setCurrentPattern(curPattern);
				//((CANTPattern) patterns.get(curPattern)).arrange(getNeuronsToStimulate());
			}
		}
	}
	  
	  public void readBetweenAllNets() {
	    int netsChecked = 0;
	    Enumeration <?> eNum = Main.nets.elements();
	    iris3Net net = (iris3Net) eNum.nextElement();
	    iris3Net baseNet = net;
	    iris3Net somNet = net;
	    iris3Net hiddenNet = net;
	    iris3Net outputNet = net;

	    System.out.println("iris3 read Between");

	      iris3Net gasNet = net;
	      do {
	        System.out.println(net.getName());
	        if (net.getName().compareTo("BaseNet") == 0)
	          baseNet = net;
	        else if (net.getName().compareTo("SomNet") == 0)
	          somNet = net;
	        else if (net.getName().compareTo("HiddenNet") == 0)
		          hiddenNet = net;
	        else if (net.getName().compareTo("OutputNet") == 0)
		          outputNet = net;
	        else
	          System.out.println(net.getName() + " missed net in connect all");
	        netsChecked++;
	        if (netsChecked < 4)
	          net = (iris3Net) eNum.nextElement();
	      } while (netsChecked < 4);

	      baseNet.readConnectTo(somNet);
	      somNet.readConnectTo(hiddenNet);
	      hiddenNet.readConnectTo(somNet);
	      hiddenNet.readConnectTo(outputNet);
	      outputNet.readConnectTo(hiddenNet);
	  }
	  
	  
	  
	  public void runAllOneStep(int CANTStep) {  
	    //This series of loops is really chaotic, but I needed to
	    //get all of the propagation done in each net in step.
	    iris3CANT.runOneStepStart();
		
	    Enumeration <?> eNum = Main.nets.elements();
	    while (eNum.hasMoreElements()) {
	      iris3Net net = (iris3Net)eNum.nextElement();
	      //net.runOneStep(CANTStep);
	      net.changePattern(CANTStep);
	    }
	    eNum = Main.nets.elements();
	    while (eNum.hasMoreElements()) {
	      iris3Net net = (iris3Net)eNum.nextElement();
	      net.setExternalActivation(CANTStep);
	    }
	    
	    iris3CANT.outputGlobInhibition.applyGlobalInhibition();
	    //iris3CANT.somGlobInhibition.applyGlobalInhibition();
	    //iris3CANT.hiddenGlobInhibition.applyGlobalInhibition();
	    //net.propogateChange();  
	    eNum = Main.nets.elements();
	    while (eNum.hasMoreElements()) {
	      iris3Net net = (iris3Net)eNum.nextElement();
	      net.setNeuronsFired();
	    }
	    eNum = Main.nets.elements();
	    while (eNum.hasMoreElements()) {
	      iris3Net net = (iris3Net)eNum.nextElement();
	      net.setDecay ();
	    }
	    eNum = Main.nets.elements();
	    while (eNum.hasMoreElements()) {
	      iris3Net net = (iris3Net)eNum.nextElement();
	      net.spreadActivation();
	    }
	    eNum = Main.nets.elements();
	    while (eNum.hasMoreElements()) {
	      iris3Net net = (iris3Net)eNum.nextElement();
	      net.setFatigue();
	    }
	    eNum = Main.nets.elements();
	    while (eNum.hasMoreElements()) {
	      iris3Net net = (iris3Net)eNum.nextElement();
	      net.learn();
	    }
	    eNum = Main.nets.elements();
	    while (eNum.hasMoreElements()) {
	      iris3Net net = (iris3Net)eNum.nextElement();
	      net.cantFrame.runOneStep(CANTStep+1);
	    }
	    eNum = Main.nets.elements();
	    while (eNum.hasMoreElements()) {
	      iris3Net net = (iris3Net)eNum.nextElement();
	      if (net.recordingActivation) net.setMeasure(CANTStep); 	  
	    }
	  }


	public CANTNet getNewNet(String name, int cols, int rows, int topology) {
		iris3Net net = new iris3Net(name, cols, rows, topology);
		return (net);
	}

  private int InternalSomSynapses = 10;//5;
  private int InternalHiddenSynapses = 10;//5;
  private int InternalOutputSynapses = 10;
  private int InputToSomSynapses = 20; 
  private int SomToHiddenSynapses = 15;//10
  private int HiddenToSomSynapses = 10;//5
  private int HiddenToOutputSynapses = 10;
  private int OutputToHiddenSynapses = 10;

	protected void createNeurons() {
		if (topology == 1) {
			totalNeurons = 0;
			neurons = new CANTNeuron[cols * rows];
			for (int i = 0; i < cols * rows; i++) {
				neurons[i] = new CANTNeuron(totalNeurons++, this);
				// neurons[i] = new
				// CANTNeuronSpontaneousFatigue(totalNeurons++,this);
				neurons[i].setCompensatoryBase(10.0);
			}
		} else if (topology == 2) {
			System.out.println(" creating fatigue neurons ");
			totalNeurons = 0;
			neurons = new CANTNeuronSpontaneousFatigue[cols * rows];
			for (int i = 0; i < cols * rows; i++) {
				neurons[i] = new CANTNeuronSpontaneousFatigue(totalNeurons++,
						this);
				neurons[i].setCompensatoryBase(10.0);
			}
			setInitialFatigue();
		} else if (topology == 3) {
			System.out.println(" creating fatigue neurons ");
			totalNeurons = 0;
			neurons = new CANTNeuronSpontaneousFatigue[cols * rows];
			for (int i = 0; i < cols * rows; i++) {
				neurons[i] = new CANTNeuronSpontaneousFatigue(totalNeurons++,
						this);
				neurons[i].setCompensatoryBase(10.0);
			}
			setInitialFatigue();
		} else if (topology == 4) {
			System.out.println(" creating fatigue neurons ");
			totalNeurons = 0;
			neurons = new CANTNeuronSpontaneousFatigue[cols * rows];
			for (int i = 0; i < cols * rows; i++) {
				neurons[i] = new CANTNeuronSpontaneousFatigue(totalNeurons++,
						this);
				neurons[i].setCompensatoryBase(10.0);
			}
			setInitialFatigue();
		} else
			System.out.println("error in iris3Net.createNuerons ");
	}
	  
	public void connectBaseToSomNet(iris3Net somNet) {
		for (int fromNeuron = 0; fromNeuron < getSize(); fromNeuron++) {
			int externalSynapses = InputToSomSynapses;
			for (int synapse = 0; synapse < externalSynapses; synapse++) {
				int toNeuron = Main.random.nextInt(somNet.getSize());
				neurons[fromNeuron].addConnection(somNet.neurons[toNeuron],
						0.001);
			}
		}
	}

	public void connectSomToHiddenNet(iris3Net hiddenNet) {
		for (int fromNeuron = 0; fromNeuron < getSize(); fromNeuron++) {
			int externalSynapses;
			externalSynapses = SomToHiddenSynapses;
			for (int synapse = 0; synapse < externalSynapses; synapse++) {
				int toNeuron = Main.random.nextInt(hiddenNet.getSize());
				neurons[fromNeuron].addConnection(hiddenNet.neurons[toNeuron],
						0.001);
			}
		}
	}
	
	public void connectHiddenToSomNet(iris3Net somNet) {
		for (int fromNeuron = 0; fromNeuron < getSize(); fromNeuron++) {
			int externalSynapses = HiddenToSomSynapses;
			for (int synapse = 0; synapse < externalSynapses; synapse++) {
				int toNeuron = Main.random.nextInt(somNet.getSize());
				neurons[fromNeuron].addConnection(somNet.neurons[toNeuron],
						0.001);
			}
		}
	}
	
	public void connectHiddenToOutputNet(iris3Net outputNet) {
		for (int fromNeuron = 0; fromNeuron < getSize(); fromNeuron++) {
			int externalSynapses = HiddenToOutputSynapses;
			for (int synapse = 0; synapse < externalSynapses; synapse++) {
				int toNeuron = Main.random.nextInt(outputNet.getSize());
				neurons[fromNeuron].addConnection(outputNet.neurons[toNeuron],
						0.001);
			}
		}
	}

	public void connectOutputToHiddenNet(iris3Net somNet) {
		for (int fromNeuron = 0; fromNeuron < getSize(); fromNeuron++) {
			int externalSynapses;
			externalSynapses = OutputToHiddenSynapses;
			for (int synapse = 0; synapse < externalSynapses; synapse++) {
				int toNeuron = Main.random.nextInt(somNet.getSize());
				neurons[fromNeuron].addConnection(somNet.neurons[toNeuron],
						0.001);
			}
		}
	}
	
	  protected void setConnections() {
	    for(int i=0;i< cols*rows;i++)
	      {
	      if (neurons[i].isInhibitory())
	        setConnectionsRandomly(i,20,0.01);
	      else
	        setConnectionsRandomly(i,20,0.01);
	      }
	  }

	  //Fatigue should be relatively random to start.
	  public void setInitialFatigue() {
	    for(int i=0;i< cols*rows;i++) {
	      float newFatigue = Main.random.nextFloat();
	      newFatigue *= getActivationThreshold()*2;
	      newFatigue -= getActivationThreshold();
	      neurons[i].setFatigue(newFatigue);
	    }
	  }

	  //set fatigue between threshold and bottomVal
	  public void setInitialFatigue(double bottomVal) {
	    double range = getActivationThreshold() - bottomVal;
	    for(int i=0;i< cols*rows;i++) {
	      float newFatigue = Main.random.nextFloat();
	      newFatigue *= range;
	      newFatigue += bottomVal;
	      neurons[i].setFatigue(newFatigue);
	    }
	  }

	  private void setInputTopology() {
	      //setConnections(); no internal connections
	  }
	  
	private void setSOMTopology() {
		for (int fromNeuron = 0; fromNeuron < getSize(); fromNeuron++) {
			int internalSynapses;
			neurons[fromNeuron].setInhibitory(false);
			internalSynapses = InternalSomSynapses;
			for (int synapses = 0; synapses < internalSynapses; synapses++) {
				int toNeuron = Main.random.nextInt(getSize());
				addConnection(fromNeuron, toNeuron, 0.02);
			}
		}
	}
	
	private void setHiddenTopology() {
		for (int fromNeuron = 0; fromNeuron < getSize(); fromNeuron++) {
			int internalSynapses;
			neurons[fromNeuron].setInhibitory(false);
			internalSynapses = InternalHiddenSynapses;
			for (int synapses = 0; synapses < internalSynapses; synapses++) {
				int toNeuron = Main.random.nextInt(getSize());
				addConnection(fromNeuron, toNeuron, 0.02);
			}
		}
	}
	  
	private void setOutputTopology() {
		for (int fromNeuron = 0; fromNeuron < getSize(); fromNeuron++) {
			int internalSynapses;
			neurons[fromNeuron].setInhibitory(false);
			internalSynapses = InternalOutputSynapses;
			for (int synapses = 0; synapses < internalSynapses; synapses++) {
				int toNeuron = Main.random.nextInt(getSize());
				addConnection(fromNeuron, toNeuron, 0.02);
			}
		}
	}

	public void initializeNeurons() {
		// set up topologies.
		createNeurons();

		if (topology == 1) {
			// System.out.println("iris3 input topology ");
			setInputTopology();
		} else if (topology == 2) {
			// System.out.println("iris3 som topology ");
			setSOMTopology();
		} else if (topology == 3) {
			// System.out.println("iris3 hidden topology ");
			setHiddenTopology();
		} else if (topology == 4) {
			// System.out.println("iris3 output topology ");
			setOutputTopology();
		} else
			System.out.println("bad toppology specified " + topology);
	}

	  private double getTotalWeights(CANTNeuron neuron) {
	    double toWeight = 0.0;
	    for (int synapse = 0; synapse < neuron.getCurrentSynapses(); synapse ++) {
	      toWeight += neuron.synapses[synapse].getWeight();
	    }
	    return (toWeight);
	  }


	  public void learn() {
		int learningOn = getLearningOn();
	    if (learningOn == 0) return;
	    else if (learningOn == 1)
	    {
		    int totalNeurons = size();
		    for (int neuronIndex = 0; neuronIndex < totalNeurons; neuronIndex++) 
		      {
		      String netName = getName();
		      if (netName.equals("BaseNet"))  		
		        neurons[neuronIndex].learn4();
		      else if ((netName.equals("SomNet")) || (netName.equals("HiddenNet")) ||
		    		   (netName.equals("OutputNet")))
	//if ((neuronIndex == 0) && neurons[0].getFired())	    	  
	//System.out.println(" neuron 0 fired " + iris3CANT.CANTStep); 
			    neurons[neuronIndex].learnPostCompense();
		      }
	    }
	    else 
	    	System.out.println(" bad learning on in iris3Net learn " + learningOn); 
	 }
	  
	  

	  public void kludge () {
	    System.out.println("kludge " + iris3CANT.kludge );
	    iris3Net net = (iris3Net) Main.experiment.getNet("SomNet");

	    
	    if (iris3CANT.kludge == 0) {
	      net = (iris3Net) Main.experiment.getNet("BaseNet");
	      for (int i= 0; i < 10; i++) 
	        {
	       double totalWeights = getTotalWeights(net.neurons[i]);
	        System.out.println(i + " " + totalWeights + " " +  net.neurons[i].getFatigue()); 
	        }
	    }
	    else if (iris3CANT.kludge == 1) {
	    	iris3CANT.experiment.debugPrintTrainTestNets();
	      }

	    else if (iris3CANT.kludge == 3) {
	        for (int i= 0; i < 10; i++) 
	          {
	          System.out.println(i + " " +  net.neurons[i].getFatigue()); 
	          }
	      }
	    else if (iris3CANT.kludge == 4) {
	    	iris3CANT.experiment.printTrainTestNet();
	      }
	    else {
	        for (int i= 0; i < 10; i++) 
	        {
	        System.out.println(i + " act " +  net.neurons[i].getActivation()); 
	        }
	    }
	    /*
	           double avgWeights;
	      double totalWeights = getTotalWeights(net.neurons[i]);
	      avgWeights = getTotalWeights(net.neurons[i],"OutputNet");
	      System.out.println(i + " " + avgWeights + " " + totalWeights); 
	      //System.out.println(i + " " + this.neurons[i].getActivation() + 
	      //                      " " + this.neurons[i].getFatigue());
	      */
	  }
	  
	  public void measure(int currentStep) {
	    System.out.println("measure " + neurons[0].getActivation() + " " + 
	      neurons[0].getFired() + " " + 
		  currentStep);
	  }
	}