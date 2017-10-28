package ai_week2;

public class perceptronB{
	public static void main(String[] args)
	{
		perceptron1 als = new perceptron1();
		
		System.out.println("pairs");

		als.initWeights();
		als.printClassifier();
		als.printWeights();

		als.train();
		als.test();
	}
}

