import java.nio.*;
	import java.io.*;
import java.nio.channels.*;
class FileReader{
	
	  private String filename;
	  private String content;
	  
	  public FileReader(File file,String path) throws IOException {
	    filename = path+file.getName();
	    //System.out.println(filename);
	    readContent();
	  } 
	  
	  public void readContent() throws IOException {
	    
	    FileInputStream fis = new FileInputStream(filename);
	    FileChannel fc = fis.getChannel();
	    ByteBuffer buff = ByteBuffer.allocate((int)fc.size());
	    fc.read(buff);
	    fc.close();
	    content = new String(buff.array());
	  }
	  
	  public String getContent() {
	    return content;
	  }
	




}