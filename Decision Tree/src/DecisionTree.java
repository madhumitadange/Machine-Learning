import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//HW1 - Madhumita Dange
class DecisionTree {
	private int     nodeNum;
	private DecisionTree yesBranch  = null;
	private DecisionTree noBranch   = null;
	private double pPos,pNeg;
	private int rowNo=0,clNo=0;
	private String[][] attr;
	double rootEnt =0;
	public List<String[]> trainingSet = new ArrayList<String[]>();
	public List<String[]> testSet = new ArrayList<String[]>();
	public List<String[]> validationSet = new ArrayList<String[]>();
	private List<String> avlAttrs = new ArrayList<String>();
	Node rootNode = null;
	public int treeArray[]= new int[5000000];
	public int index=0,level=0;
	//String[] ;
	public DecisionTree() {
	}
	public DecisionTree(DecisionTree d) {
		
		// TODO Auto-generated constructor stub
	}
	public void readCsv(List<String[]> inputSet, String csvFile){
		BufferedReader br = null;
		
		String line = "";
		 
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			
				while ((line = br.readLine()) != null) {
					inputSet.add(line.split(",", -1));
				
			}
				int len = inputSet.get(0).length;
				//len=set.size();
				String val = inputSet.get(0)[0];
//				System.out.println(val+len);
		}
				catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (br != null) {
						try {
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
	}
	
	public void createRoot(boolean isVI) {
		rootNode = new Node();	
		
    	for(int a =0; a < trainingSet.get(0).length; a++) {
			avlAttrs.add(trainingSet.get(0)[a]);

    	}

    	calEntropy(trainingSet,rootNode,avlAttrs, 0, 1, isVI);
		 
	}
	
	public void createYesNode(){
		
	}
    public void createNoNode(){
		
	}
    
    public double testTree(Node tree, List<String[]> data) {
    	int correct = 0, wrong = 0;
    	
    	for(int i = 1; i < data.size(); i++) {
    		String[] temp = data.get(i);
    		
    		String output = evaluateSample(tree, temp);
    		if((output.equals("0") && temp[temp.length-1].equals("0"))
    				|| (output.equals("1") && temp[temp.length-1].equals("1"))) {
    			correct++;
    		} else if((output.equals("0") && temp[temp.length-1].equals("1"))
    				|| (output.equals("1") && temp[temp.length-1].equals("0"))){
    			String str = "";
    			for(int j = 0; j < temp.length; j++) {
    				str = str + temp[j] + ",";
    			}
//    			System.out.println("Wrong record: "+str);
    			wrong++;
    		}
    	}
    	

    	return ((double)correct/(correct+wrong));
    }
    
    private String evaluateSample(Node tree, String[] sample) {
    	int column = -1;
    	String retVal = "";
    	
    	if(tree.getData().equals("0") || tree.getData().equals("1")) {
    		return tree.getData();
    	}
    	
    	for(int i = 0; i<sample.length; i++) {
    		if(trainingSet.get(0)[i].equalsIgnoreCase(tree.getData())) {
    			column = i;
    			break;
    		}
    	}
    	
    	if(sample[column].equalsIgnoreCase("0")) {
    		retVal = evaluateSample(tree.getleftChild(), sample);
    	} else {
    		retVal = evaluateSample(tree.getrightChild(), sample);
    	}
    	
    	return retVal;
    }
    
    public void printTree(Node node) {
    	
    	String indentation = "";
    	
    	for(int i = 0; i < node.getLevel(); i++) {
    		indentation = indentation + "|";
    	}
    	
    	if(node.getleftChild().getData().equalsIgnoreCase("0") || node.getleftChild().getData().equalsIgnoreCase("1")) {
    		System.out.println(indentation + node.getData()+" = 0 : "+node.getleftChild().getData());
    	} else {
    		System.out.println(indentation + node.getData()+" = 0 :");
        	printTree(node.getleftChild());
    	}

    	if(node.getrightChild().getData().equalsIgnoreCase("0") || node.getrightChild().getData().equalsIgnoreCase("1")) {
    		System.out.println(indentation + node.getData()+" = 1 : "+node.getrightChild().getData());
    	} else {
    		System.out.println(indentation + node.getData()+" = 1 :");
    		printTree(node.getrightChild());
    	}
    	
    }
    
    public void calEntropy(List<String[]> ss, Node rootNode,List<String> avlAttrs, int level, int index, boolean isVI){
    	
		
		double rootEntropy = 0;
    	
    	int globalPCount = 0, globalNCount = 0;
    	for(int a = 1; a < ss.size(); a++) {
    		if(ss.get(a)[ss.get(a).length-1].equalsIgnoreCase("0"))
    			globalNCount++;
    		else
    			globalPCount++;
    	}
    	if(globalNCount != 0 && globalPCount != 0) {
    		if(isVI) {
    			rootEntropy = ((double)globalPCount/(double)(globalPCount+globalNCount))*((double)globalNCount/(double)(globalPCount+globalNCount));
    		} else {
    			rootEntropy = -((double)globalPCount/(double)(globalPCount+globalNCount)) * (Math.log10((double)globalPCount/(double)(globalPCount+globalNCount)) / Math.log10(2)) 
    					-((double)globalNCount/(double)(globalPCount+globalNCount)) * (Math.log10((double)globalNCount/(double)(globalPCount+globalNCount)) / Math.log10(2));
    		}
    		}
    	
    	rootNode.setEntropy(rootEntropy);
    	rootNode.setData("root");
    	rootNode.setInd(index);
    	rootNode.setpCount(globalPCount);
    	rootNode.setnCount(globalNCount);
    	
//    	System.out.println("rootEntropy: "+rootEntropy+" at index: "+index);
    	
    	
    	if(rootEntropy == 0) {
    		rootNode.setData(""+(globalNCount==0?"1":"0"));
    		return;
    	}
    	
    	if(avlAttrs.size() == 1) {
    		rootNode.setData(""+(globalNCount>globalPCount?"0":"1"));
    		return;
    	}
    	

    	List<String[]> ssL = new ArrayList<String[]>();
    	List<String[]> ssR = new ArrayList<String[]>();
    	List<String> availattrsL= new ArrayList<String>(avlAttrs); 
    	List<String> availattrsR= new ArrayList<String>(avlAttrs); 
    	
    	
    	
		
		int colLength = ss.get(0).length;

		double maximumGain = - 10;
		int columnChosen = -1;
		int pcount = 0,ncount=0;
		double pEntropy = 0, nEntropy = 0;
		int colPCount = 0, colNCount = 0;
			for(int j = 0; j < (colLength - 1); j++) {//&&availattrs.contains(ss.get(0)[j])
			//System.out.println(ss.get(0)[j]);//&&availattrs.contains()
			if(!avlAttrs.contains(ss.get(0)[j])) {
				continue;
			}
			
			pcount = 0; ncount = 0; colNCount = 0;
			for(int i = 1; i <ss.size(); i++) {
				String value = ss.get(i)[colLength-1];
				if(ss.get(i)[j].equalsIgnoreCase("0")) {//&&ss.get(i)[rootInd].equalsIgnoreCase("0")
					colNCount++;
					if(value.equalsIgnoreCase("1")) {
						pcount++;
					} else if(value.equalsIgnoreCase("0")) {
						ncount++;
					}
				}
			}
			double pPos = (double)pcount / (double)(colNCount);
			double pNeg = (double)ncount / (double)(colNCount);
			
			if(pPos > 0 && pNeg > 0) {
				if(isVI) {
					nEntropy = pPos*pNeg;
				} else {
					nEntropy = -(pPos * (Math.log10(pPos) / Math.log10(2))) -(pNeg * (Math.log10(pNeg) / Math.log10(2)));
				}
			}


			pcount = 0; ncount = 0; colPCount = 0;
			for(int i = 1; i <ss.size(); i++) {
				String value = ss.get(i)[colLength-1];
				if(ss.get(i)[j].equalsIgnoreCase("1")) {
					colPCount++;
					if(value.equalsIgnoreCase("1")) {
						pcount++;
					} else if(value.equalsIgnoreCase("0")) {
						ncount++;
					}
				}
			}
			pPos = (double)pcount / (double)(colPCount);
			pNeg = (double)ncount / (double)(colPCount);
			
			if(pPos > 0 && pNeg > 0) {
				if(isVI) {
					pEntropy = pPos*pNeg;
				} else {
					pEntropy = -(pPos * (Math.log10(pPos) / Math.log10(2))) -(pNeg * (Math.log10(pNeg) / Math.log10(2)));
				}
			}
			
			double gain = rootEntropy - (((double)colNCount/(double)(colNCount+colPCount))*nEntropy + ((double)colPCount/(double)(colNCount+colPCount))*pEntropy);

			if(gain > maximumGain) {

				maximumGain = gain;
				columnChosen = j;
			}
			
		}

		ssL.add(ss.get(0));
		ssR.add(ss.get(0));
		
		for(int i=1;i<ss.size();i++){
			
			if(ss.get(i)[columnChosen].equalsIgnoreCase("0")){
				ssL.add(ss.get(i));
			}
			if(ss.get(i)[columnChosen].equalsIgnoreCase("1")){
				ssR.add(ss.get(i));
			}
		}


				rootNode.setData(ss.get(0)[columnChosen]);
				rootNode.setInd(index);
				
				

			int left_ind  = (2 * index);
			int right_ind = (2 * index) + 1;
			treeArray[index]=columnChosen;
			level++;

			
			Node leftNode =  new Node();
			leftNode.setEntropy(nEntropy);
			rootNode.setleftChild(leftNode);
			leftNode.setParent(rootNode);
			leftNode.setUsed(true);
			leftNode.setLevel(level);
			
			if(ssL.size() == 1) {
	    		leftNode.setData(""+(globalNCount>globalPCount?"0":"1"));
			} else {
				availattrsL.remove(availattrsL.indexOf(rootNode.getData()));
				calEntropy(ssL,leftNode,availattrsL,level,left_ind,isVI);
			}
			

			Node rightNode = new Node();
			rightNode.setEntropy(pEntropy);
			rootNode.setrightChild(rightNode);
			rightNode.setParent(rootNode);
			rightNode.setUsed(true);
			rightNode.setLevel(level);
			
			if(ssR.size() == 1) {
	    		rightNode.setData(""+(globalNCount>globalPCount?"0":"1"));
			} else {
				availattrsR.remove(availattrsR.indexOf(rootNode.getData()));
				calEntropy(ssR,rightNode,availattrsR,level,right_ind,isVI);
			}
			
			
			
			//availattrs.remove(availattrs.indexOf(rightNode.getData()));
//			System.out.println("level= "+level);
level--;			
			
			//if(columnChosen != -1)
			//calEntropy(ss,rootNode,availattrs);
//		}
		//}
		
		//		return entropy;
    	
    }
    private void update(List<String> availattrs) {
		// TODO Auto-generated method stub
    	
		
	}
	
    public void addNode(){
    	
    }
    
    

    public void calVar(){
    	
    }
    
    public Node cloneTree(Node src) {
    	Node trg = new Node(src.getData(), src.getGain(), src.getEntropy(), src.getLevel(), src.getInd(), src.getpCount(), src.getnCount());
    	
    	if(src.getleftChild() != null) {
    		trg.setleftChild(cloneTree(src.getleftChild()));
    	}
    	
    	if(src.getrightChild() != null) {
        	trg.setrightChild(cloneTree(src.getrightChild()));
    	}

    	return trg;
    }

    public int getNonLeafNodeCount(Node node) {
    	return (node.getleftChild()!=null?getNonLeafNodeCount(node.getleftChild()):0) + (node.getrightChild()!=null?getNonLeafNodeCount(node.getrightChild()):0) + (node.getData().equals("0")||node.getData().equals("1")?0:1);
    }
    
//    public int getNonLeafNodeCount(Node node, int cnt) {
//    	if(node.getleftChild() != null && !(node.getleftChild().getData().equals("0") || node.getleftChild().getData().equals("1"))) {
//    		cnt = cnt + getNonLeafNodeCount(node.getleftChild(), cnt);
//    	}
//    	if(node.getrightChild() != null && !(node.getrightChild().getData().equals("0") || node.getrightChild().getData().equals("1"))) {
//    		cnt = cnt + getNonLeafNodeCount(node.getrightChild(), cnt);
//    	}
//    	return cnt+1;
//    }
    
    public Node postPrune(int L, int K){
		
    	Node dBest = cloneTree(rootNode);
		double bestAccuracy = testTree(dBest, validationSet);
		//System.out.println("Best Accuary: "+bestAccuracy);
		
		for(int i=0;i<L;i++) {
			Node dLocal = cloneTree(rootNode);

			int M= (int) (Math.floor(Math.random()*K)+1);

			int N = getNonLeafNodeCount(dLocal);
			//System.out.println("Non leaf node count: "+N);

			for(int j=0;j<M;j++){
				
				int P =(int)(Math.floor(Math.random()*N)+1);
				//System.out.println("Node chosen to be pruned: "+P);
				
				pruneNode(dLocal, P);
				
			}

			double accuracy = testTree(dLocal, validationSet);
			//System.out.println("Local Accuary at round: "+(i+1)+" is: "+accuracy);
			if(accuracy>bestAccuracy){
				//System.out.println("Resetting best accuracy with accuracy");
				dBest=dLocal;
				bestAccuracy=accuracy;
			}
		}
		
		return dBest;
	}

	private double calAcc(Node rootNode2, List<String[]> validationSet2) {
		// TODO Auto-generated method stub
		return 0;
	}

	private int pruneNode(Node node, int P) {
		
		//System.out.println("Value of P: "+P);
		if(P==1) {
			P--;
			node.setData(node.getnCount()>node.getpCount()?"0":"1");
			node.setleftChild(null);
			node.setrightChild(null);
		} else {
			P--;
			if(P>=1 && node.getleftChild() != null && !(node.getleftChild().getData().equals("0") || node.getleftChild().getData().equals("1"))) {
				P = pruneNode(node.getleftChild(), P);
			}
			
			if(P>=1 && node.getrightChild() != null && !(node.getrightChild().getData().equals("0") || node.getrightChild().getData().equals("1"))) {
				P = pruneNode(node.getrightChild(), P);
			}
		
		}
		
		return P;
		
	}

	

	private int getNonleafs(DecisionTree ddash) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}