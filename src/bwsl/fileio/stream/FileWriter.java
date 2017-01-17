package bwsl.fileio.stream;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 
 * @author bowns
 * @email bowns1688@hotmail.com
 * @date 2017.01.17
 *
 */
public class FileWriter {

	private File targetFile;
	private boolean append;
	private BufferedWriter bw;
	
	public FileWriter(String filePath) throws IOException {
		
		this.targetFile = new File(filePath);
		this.append = true;
		java.io.FileWriter fw = new java.io.FileWriter(this.targetFile, this.append);
		this.bw = new BufferedWriter(fw);
	}
	
	public FileWriter(File targetFile) throws IOException {
		
		this.targetFile = targetFile;
		this.append = true;
		java.io.FileWriter fw = new java.io.FileWriter(this.targetFile, this.append);
		this.bw = new BufferedWriter(fw);
	}
	
	public FileWriter(String filePath, boolean append) throws IOException {
		
		this.targetFile = new File(filePath);
		this.append = append;
		java.io.FileWriter fw = new java.io.FileWriter(this.targetFile, this.append);
		this.bw = new BufferedWriter(fw);
	}
	
	public FileWriter(File targetFile, boolean append) throws IOException {
		
		this.targetFile = targetFile;
		this.append = append;
		java.io.FileWriter fw = new java.io.FileWriter(this.targetFile, this.append);
		this.bw = new BufferedWriter(fw);
	}
	
	public FileWriter(String filePath, String encode) throws IOException {
		
		this.targetFile = new File(filePath);
		this.append = true;
		FileOutputStream fos = new FileOutputStream(targetFile, this.append);
		OutputStreamWriter osw = new OutputStreamWriter(fos, encode);
		this.bw = new BufferedWriter(osw);
	}
	
	public FileWriter(File targetFile, String encode) throws IOException {
		
		this.targetFile = targetFile;
		this.append = true;
		FileOutputStream fos = new FileOutputStream(targetFile, this.append);
		OutputStreamWriter osw = new OutputStreamWriter(fos, encode);
		this.bw = new BufferedWriter(osw);
	}
	
	public FileWriter(String filePath, boolean append, String encode) throws IOException {
		
		this.targetFile = new File(filePath);
		this.append = append;
		FileOutputStream fos = new FileOutputStream(targetFile, this.append);
		OutputStreamWriter osw = new OutputStreamWriter(fos, encode);
		this.bw = new BufferedWriter(osw);
	}
	
	public FileWriter(File targetFile, boolean append, String encode) throws IOException {
		
		this.targetFile = targetFile;
		this.append = append;
		FileOutputStream fos = new FileOutputStream(targetFile, this.append);
		OutputStreamWriter osw = new OutputStreamWriter(fos, encode);
		this.bw = new BufferedWriter(osw);
	}
	
	public File getFile() {
		
		return this.targetFile;
	}
	
	public String getFilePath() {
		
		return this.targetFile.getAbsolutePath();
	}
	
	public void write(String inputString) throws IOException {
		
		this.bw.write(inputString);
	}
	
	public void writeln() throws IOException {
		
		this.bw.newLine();
	}
	
	public void writeln(String inputString) throws IOException {
		
		this.bw.write(inputString);
		this.bw.newLine();
	}
	
	public void close() throws IOException {
		
		this.bw.close();
	}
}