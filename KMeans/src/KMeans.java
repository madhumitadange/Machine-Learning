/*** Author :Vibhav Gogate
The University of Texas at Dallas
*****/


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
 

public class KMeans {
    public static void main(String [] args){
	if (args.length < 3){
	    System.out.println("Usage: Kmeans <input-image> <k> <output-image>");
	    return;
	}
	int a[] = {2,5,10,15,20};
	//for(int n=0;n<5;n++ ){
	//for(int l=0;l<5;l++ ){

	try{
		//File f1=new File("Koala.jpg");
	    BufferedImage originalImage =ImageIO.read(new File(args[0])); //ImageIO.read(new File(args[0]));
	   int  k=Integer.parseInt(args[1]);//k=a[n];
	    BufferedImage kmeansJpg = kmeans_helper(originalImage,k);
	   // File f2=new File("PoutK="+a[n]+"l="+l+".jpg");
	    ImageIO.write(kmeansJpg, "jpg", new File(args[2])); 
	   // double CR =(double)f1.length()/(double)f2.length();
		//System.out.println("K= "+a[n]+" it= "+l+" size : "+(double)f2.length()+" CR: "+CR);
	    
	}catch(IOException e){
	    System.out.println(e.getMessage());
	}
	
//	}
  //  }
    }
    
    private static BufferedImage kmeans_helper(BufferedImage originalImage, int k){
	int w=originalImage.getWidth();
	int h=originalImage.getHeight();
	BufferedImage kmeansImage = new BufferedImage(w,h,originalImage.getType());
	Graphics2D g = kmeansImage.createGraphics();
	g.drawImage(originalImage, 0, 0, w,h , null);
	// Read rgb values from the image
	int[] rgb=new int[w*h];
	int count=0;
	for(int i=0;i<w;i++){
	    for(int j=0;j<h;j++){
		rgb[count++]=kmeansImage.getRGB(i,j);
	    }
	}
	// Call kmeans algorithm: update the rgb values
	kmeans(rgb,k);

	// Write the new rgb values to the image
	count=0;
	for(int i=0;i<w;i++){
	    for(int j=0;j<h;j++){
		kmeansImage.setRGB(i,j,rgb[count++]);
	    }
	}
	return kmeansImage;
    }

    // Your k-means code goes here
    // Update the array rgb by assigning each entry in the rgb array to its cluster center
//    Cluster initialization:  initCenters()
//    Distance computation: computeDistances()
//    Cluster assignment:  makeAssignments()
//    Cluster center computation: computeCenters()
    private static void kmeans(int[] rgb, int k){
    	int ck[]  =initCenters(rgb,k);
		for(int i = 0; i < ck.length; i++) {
			//System.out.println(ck[i])		;	
		}
		//System.out.println("ck length: "+ck.length);
		//System.out.println("k: "+k);
    	double[][] assignment=new double[rgb.length][2];
    	
    	for(int it=0;it<50;it++){
	    	for(int i=0;i<rgb.length;i++){
	    		
	    		double dist=computeDistances(rgb[i],ck[0]);
	    		assignment[i][0]=dist;
	    		assignment[i][1]=0;
	    		for(int j=0;j<ck.length;j++)
	    		{
		    		 double dist1 = computeDistances(rgb[i],ck[j]);
		    		 //System.out.println("dist: "+dist1);
		    		 if(dist1<assignment[i][0]){
		    			//dist= dist1;
		    		 	assignment[i][0]=dist1;
		     			assignment[i][1]=j;
		    		 }
	    		}
	    		
	    	}

	    	for(int j=0;j<k;j++) {
    		
	    		int[] s= new int[3];
	    		int c=0;
	    		for(int m=0;m<rgb.length;m++) {
	    			if(assignment[m][1]==j)
	    			{
	    				Color temp = new Color(rgb[m]);
	    				s[0]=s[0]+temp.getRed();
	    				s[1]=s[1]+temp.getBlue();
	    				s[2]=s[2]+temp.getGreen();
	    				c++;
	    			}
	    		}
	    		
	    		if(c!=0){
	    			Color temp = new Color(s[0]/c, s[1]/c, s[2]/c);
	    			ck[j]= temp.getRGB();
	    		//System.out.println(""+rgb[k+555]);
	    		}

	    	}
    	}
    	
    	for (int i = 0; i < rgb.length; i++) {
			for(int j=0;j<k;j++) {
				if(assignment[i][1]==j){
					rgb[i]=ck[j];
					break;
				}
			}
		}
    	
    	
    }

	private static double computeDistances(int x, int i) {
		// TODO Auto-generated method stub
		Color c=new Color(x);
		
		Color ck = new Color(i);
		double  blue= (ck.getBlue()-c.getBlue())*(ck.getBlue()-c.getBlue());
		double red= (ck.getRed()-c.getRed())*(ck.getRed()-c.getRed());
		double green = (ck.getGreen()-c.getGreen())*(ck.getGreen()-c.getGreen());
		//System.out.println(red+" "+blue+" "+green);
		double sum= blue+red+green;
		
		
		return sum;
	}

	private static int[] initCenters(int[] rgb, int k) {
		// TODO Auto-generated method stub
		int ck[]=new int[k];
		int l= rgb.length;
		for(int i=0;i< k;i++){
			int temp =rgb[(int)(Math.floor(l*Math.random()))];
			boolean done = false;
			while(!done) {
				for(int j=0;j<i-1;j++){
					if(ck[j]==temp) {
						temp =rgb[(int)(Math.floor(l*Math.random()))];
						break;
					}
				}
				done = true;
			}		
			ck[i]=temp;//ck[i-1] +5983;//
		}	
		
		return ck;
	}

}
