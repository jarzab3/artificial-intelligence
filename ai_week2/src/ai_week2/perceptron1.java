package ai_week2;

import java.io.*;
public class perceptron1{

	public static int NUMBER_OF_WEIGHTS=2;
	public static int MAX_ITERATION=10;
	public static int SAMPLE_SIZE=7;
	public static double theta=0.0;
	public static double threshold=1.0;
	public static double learning_coeff=0.05;
	public int[] x = {0,1,2,3,4,5,6,7,8,9};
	public int[] y = {4,1,4,1,4,1,4,1,4,1};
	public int[] d = {1,-1,1,-1,1,-1,1,-1,1,-1};
	public double[] w = new double[NUMBER_OF_WEIGHTS];	
	
	public void printClassifier()
	{
		for(int i=0; i<10; i++)
		{
			System.out.println("(x,y)=("+x[i]+","+y[i]+"): class:"+d[i]);
		}

	}
	
	public void printWeights()
	{
		System.out.println("Number of Weights:"+NUMBER_OF_WEIGHTS);
		for (int i=0; i<NUMBER_OF_WEIGHTS; i++)
		{
			System.out.println("w["+i+"]="+w[i]);
		}
	}

	public void initWeights()
	{
		for (int i=0; i<NUMBER_OF_WEIGHTS; i++)
		{
			w[i] = (Math.random()-.5)/10.0;
		}
		
	}	

	public int step(double dot_product)
	{	if (dot_product > threshold)
			return 1;
		else
		 	return -1;}

	public void printVector(int[] w)
	{
		System.out.println("Number of Weights:"+this.NUMBER_OF_WEIGHTS);
		for (int i=0; i<this.SAMPLE_SIZE; i++)
		{
			System.out.println("w["+i+"]="+w[i]);
		}
	}


	public void readData(String filename)
	{
		String str="";
		try{
			InputStream ips = new FileInputStream(filename);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			String [] parts = new String[SAMPLE_SIZE];
			int lineCtr=0;
			while ((line = br.readLine()) != null)
			{
				parts=line.split(",");
				x[lineCtr]=Integer.parseInt(parts[0]);
				y[lineCtr]=Integer.parseInt(parts[1]);		
				if(parts[2].compareTo("A")>0) 
				{ d[lineCtr] = 1; }
				else
				{ d[lineCtr] = -1; }
				str += line + "\n";
				lineCtr++;
			}
			br.close();
		}
		catch (Exception e)
		{
            		System.out.println(e.toString());
            	}
            	System.out.println("File "+filename);
            	System.out.println(str);
            	System.out.println("Vectors");
            	printVector(x);
            	printVector(y);
            	printVector(d);
        }
        
        

	public void train()
	{
		//take first half for training
		int max_train = 10;
		double sse=0.0;
		int itr=0;
		double sum=0.0;
		double error=0.0;
		do
		{
			itr++;	
			sse=0.0;
			for (int i=0; i< max_train; i++)
			{
				error=0;	
				sum = step((x[i] * w[0])	+    (y[i] * w[1]) + theta);
				error=d[i]-sum;
				if(error!=0)
				{		
					System.out.println("error="+error);			
					w[0]+=learning_coeff*x[i]*error;
					w[1]+=learning_coeff*y[i]*error;
					sse+=Math.pow(error,2.0);
				}
			
			}
			System.out.println("error="+sse+" iteration="+itr);	
		}while((sse>0.01)&&(itr<MAX_ITERATION));
	}
	
	public void test()
	{
		//take second half for testing
		this.printWeights();
		int max_test = 10;
		int start=0;
		double error=0;
		double sse=0;
		double sum=0;
		for(int i=start; i<max_test; i++)
		{
			error=0;
			sum=step(x[i] * w[0]	+ y[i] * w[1]+theta);
			error=d[i]-sum;
			sse+=Math.pow(error,2.0);
			System.out.println("idx="+i+"(x,y)=("+x[i]+","+y[i]+") actual="+sum+" desired="+d[i]);
		}
		System.out.println("Total error="+sse);
	}
}

