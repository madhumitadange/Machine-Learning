//HW1 - Madhumita Dange
public class Node {
	private Node parent;
	private String data;
	private boolean isUsed;
	private double gain;
	private double entropy;
	private Node leftChild;
	private Node rightChild;
	private int index;
	private int level;
	private int pCount;
	private int nCount;
	
	public Node(String data, double gain, double entropy, int index, int level, int pCount, int nCount) {
		setData(data);
		setGain(gain);
		setEntropy(entropy);
		setInd(index);
		setLevel(level);
		setpCount(pCount);
		setnCount(nCount);
	}
	
	public Node() {
		
		this.data = new String();
		setEntropy(0.0);
		setGain(0.0);
		setInd(-1);
		setParent(null);
		setleftChild(null);
		setrightChild(null);
		setUsed(false);
		setLevel(0);
		setpCount(0);
		setnCount(0);
		
	}
	
	public void setnCount(int nCount) {
		
		this.nCount=nCount;
		
	}

	public void setpCount(int pCount) {
		this.pCount=pCount;
		
	}
	public int getpCount() {
		return this.pCount;
	}
	public int getnCount() {
		return this.nCount;
	}

	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node getParent() {
		return parent;
	}
	

	public void setData(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}
	public void setInd(int index) {
		this.index = index;
	}

	public int getInd() {
		return index;
	}

	public void setGain(double gain) {
		this.gain = gain;
	}

	public double getEntropy() {
		return entropy;
	}
	public void setEntropy(double entropy) {
		this.entropy = entropy;
	}

	public double getGain() {
		return gain;
	}

	public void setleftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	public Node getleftChild() {
		return leftChild;
	}
	public void setrightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

	public Node getrightChild() {
		return rightChild;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public boolean isUsed() {
		return isUsed;
	}
	
	
}
