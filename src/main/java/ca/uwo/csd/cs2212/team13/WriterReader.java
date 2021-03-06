package ca.uwo.csd.cs2212.team13;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * WriterReader does JavaSerialization to actually write records and such
 *
 */
public class WriterReader{

	/**
	 * Write Record from object to filename
	 * 
	 * @param standard 
	 * 			object to write
	 * @param filename 
	 * 			filename to write
	 * @throws Exception if write fails
	 * 
	 * 
	 */
	  public void writeRecord(Object standard, String filename) throws Exception {
		  
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/resources/" + filename));
		out.writeObject(standard);
		out.close();
	  }

	  /**
	   * Loads Record to object
	   * 
	   * @param filename 
	   * 	filename of record
	   *
	   * @return object that was loaded
	   * @throws Exception if load fails
	   */
	 public Object loadRecord(String filename) throws Exception {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
			Object object = in.readObject();
			in.close();	
			return object;
	 }
}
