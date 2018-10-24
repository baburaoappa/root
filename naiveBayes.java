package naiveBayes;

import weka.core.Instances;
import weka.classifiers.*;
import weka.classifiers.bayes.NaiveBayes;


import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Exception;
import java.util.Random;

public class NB
{
	public NB()
	{
		try
		{
			BufferedReader trainReader = new BufferedReader(new FileReader("/home/dell/Desktop/weka.arff"));//File with text examples
			BufferedReader classifyReader = new BufferedReader(new FileReader("/home/dell/Desktop/weka.arff"));//File with text to classify

			Instances trainInsts = new Instances(trainReader);
			Instances classifyInsts = new Instances(classifyReader);

			trainInsts.setClassIndex(trainInsts.numAttributes() - 1);
			classifyInsts.setClassIndex(classifyInsts.numAttributes() -1);

			NaiveBayes model=new NaiveBayes();
			model.buildClassifier(trainInsts);
			//System.out.println(model);
	        Evaluation eTest = new Evaluation(classifyInsts);
	        eTest.evaluateModel(model, classifyInsts);
			
	        eTest.crossValidateModel(model, trainInsts, 10, new Random(1));
	       // System.out.println(eTest.toSummaryString("\n\nRESULT = \n\n", true));
			
			for (int i=0; i<trainInsts.numClasses(); i++)
			{
				System.out.println("Class "+ i);
				System.out.println("Precision " +eTest.precision(i));
				System.out.println("Recall "+eTest.recall(i));
				System.out.println("True Positve"+eTest.truePositiveRate(i));
				System.out.println("True Negative"+eTest.trueNegativeRate(i));
				System.out.println("false Positve"+eTest.falsePositiveRate(i));
				System.out.println("False Negative"+eTest.falseNegativeRate(i));
				System.out.println("True Positve"+eTest.numTruePositives(i));
				System.out.println("True Negatives"+eTest.numTrueNegatives(i));
				System.out.println("Error Rate"+eTest.errorRate());
				
				
				//System.out.println("	Area under ROC "+eTest.areaUnderROC(i));
				System.out.println();
			}
			
		}
		catch (Exception o)
		{
			System.err.println(o.getMessage());
		}
	}


	public static void main(String args[])
	{
		new NB();
	} 
}

