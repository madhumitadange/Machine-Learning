/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ml_project;
import weka.core.*;
import weka.core.FastVector;
import weka.classifiers.meta.FilteredClassifier;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

/**
 *
 * @author madhumita
 */


    /**
     * @param args the command line arguments
     */


public class ML_Project {

        String testInput;
        Instances instances;
        FilteredClassifier classifier;

        public String classify(String testFileName, String modelFileName) throws Exception {
				BufferedReader reader = new BufferedReader(new FileReader(testFileName));
				String str;
				testInput = "";
				while ((str = reader.readLine()) != null) {
					testInput = testInput + " " + str;
				}
				reader.close();

				ObjectInputStream in = new ObjectInputStream(new FileInputStream(modelFileName));
				Object tmp = in.readObject();
				classifier = (FilteredClassifier) tmp;
				in.close();

				Attribute testInputAttr = new Attribute("testInput",(FastVector) null);

				FastVector classVals = new FastVector(6);
                classVals.addElement("Course");
                classVals.addElement("Department");
                classVals.addElement("Faculty");
                classVals .addElement("Project");
                classVals.addElement("Staff");
                classVals.addElement("Student");
                Attribute classAttr = new Attribute("class", classVals);

				FastVector attrs = new FastVector(2);
                attrs.addElement(testInputAttr);
                attrs.addElement(classAttr);

				instances = new Instances("Test relation", attrs, 1);           
				instances.setClassIndex(1);
                Instance instance = new Instance(2);
                instance.setValue(testInputAttr, testInput);
                instances.add(instance);

				double pred = classifier.classifyInstance(instances.instance(0));
				return instances.classAttribute().value((int) pred);
        }

       public static void main (String[] args)  throws Exception {
                ML_Project c = new ML_Project();
                if(args.length<2)
                {
                    System.out.println("plaese enter <test file path> <model path>");
                }else{
                       // System.out.println("Class Predicted: "+c.classify("F:\\http_^^www.cs.utexas.edu^users^almstrum^classes^cs336^fall96^","F:\\smo3.model"));
				System.out.println("Class Predicted: "+c.classify(args[0],args[1]));
        }
       }
}  
    

