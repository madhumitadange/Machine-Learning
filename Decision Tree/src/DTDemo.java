//HW1 - Madhumita Dange
class DTDemo{
	
	public static void main(String args[]){
		//Csv csv = null;
		
		int L=	Integer.parseInt(args[0]);
		int K= Integer.parseInt(args[1]);
		String training_set =args[2];
		String validation_set =args[3];
		String test_set = args[4];
		String to_print = args[5];
		
//		System.out.println(L+K+training_set+validation_set+test_set+to_print);
		DecisionTree dT =new DecisionTree();
//		dT.readCsv(dT.trainingSet,"F:\\training_set.csv");
//		dT.readCsv(dT.testSet, "F:\\test_set.csv");
//		dT.readCsv(dT.validationSet, "F:\\test_set.csv");
		dT.readCsv(dT.trainingSet,training_set);
		dT.readCsv(dT.testSet, test_set);
		dT.readCsv(dT.validationSet,validation_set);
		dT.createRoot(false);
		if(to_print.equalsIgnoreCase("yes"))
		{
		System.out.println("Entropy Tree");
		dT.printTree(dT.rootNode);
		}
		double accBefPrune = dT.testTree(dT.rootNode, dT.testSet)*100;
		double accAftPrune = dT.testTree(dT.postPrune(L, K), dT.testSet)*100;
		
//		System.out.println("Accuracy before pruning: "+accBefPrune);
//		System.out.println("Accuracy after pruning: "+accAftPrune);
		DecisionTree dT2 =new DecisionTree();
//		dT.readCsv(dT.trainingSet,"F:\\training_set.csv");
//		dT.readCsv(dT.testSet, "F:\\test_set.csv");
//		dT.readCsv(dT.validationSet, "F:\\test_set.csv");
		// For variance
		dT2.readCsv(dT2.trainingSet,training_set);
		dT2.readCsv(dT2.testSet, test_set);
		dT2.readCsv(dT2.validationSet,validation_set);
		dT2.createRoot(true);
		if(to_print.equalsIgnoreCase("yes"))
		{
		System.out.println("Variance Tree");
		dT2.printTree(dT2.rootNode);
		
		}
		double accBefPruneVI = dT2.testTree(dT2.rootNode, dT2.testSet)*100;
		double accAftPruneVI = dT2.testTree(dT2.postPrune(L, K), dT2.testSet)*100;
		System.out.println("Accuracy before pruning using Entropy : "+accBefPrune);
		System.out.println("Accuracy after pruning using Entropy : "+accAftPrune);
		System.out.println("Accuracy before pruning using VI : "+accBefPruneVI);
		System.out.println("Accuracy after pruning using VI: "+accAftPruneVI);
		
	}
}