package com.company;

public class globalInhibition {
	
	private int minNeuronsFiring;
	private String netName;
	
	public globalInhibition(){
		minNeuronsFiring = 20;
		netName = new String("OutputNet");
	}
	
	public globalInhibition(String netToInhibit,int minNeurons){
		minNeuronsFiring = minNeurons;
		netName = new String(netToInhibit);
	}

	public void applyGlobalInhibition(){
	    CANTNet sampleNet = Main.experiment.getNet(netName);
	    int numFired = 0;
	    for (int i = 0; i < sampleNet.getSize(); i ++) {
	    	if (sampleNet.neurons[i].getFired()) numFired++;
	    }
	    if (numFired > minNeuronsFiring) {
	      double inhibition = (numFired-minNeuronsFiring)/2;
		    for (int i = 0; i < sampleNet.getSize(); i ++) {
		    	double currentActivation = sampleNet.neurons[i].getActivation();
		    	sampleNet.neurons[i].setActivation(currentActivation-inhibition);	    	
		    }
	    }
        //System.out.println(" inib Firing " + numFired);    
	}
}
