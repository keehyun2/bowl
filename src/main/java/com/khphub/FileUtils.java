package com.khphub;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * @author khpark
 *	File과 관련된 쓸만한 함수들을 모아 놓았습니다. <br/>
 * A collection of useful functions related to File
 */
public class FileUtils {

	/**
	 * text 형식의 파일명을 전달받아서 중복된 줄 은 제거합니다. 10000 줄 이상인 경우 에러 발생할수 있습니다.<br/>
	 * Takes the file name of text format and removes duplicate lines. An error of more than 10000 lines may occur.
	 * @param textFile
	 * @throws Exception
	 */
	public static void stripDuplicatesFromFile(String textFile) throws Exception {
	    BufferedReader reader = new BufferedReader(new FileReader(textFile));
	    Set<String> lines = new HashSet<String>(10000); // maybe should be bigger
	    String line;
	    while ((line = reader.readLine()) != null) {
	        lines.add(line);
	    }
	    reader.close();
	    BufferedWriter writer = new BufferedWriter(new FileWriter(textFile));
	    for (String unique : lines) {
	        writer.write(unique);
	        writer.newLine();
	    }
	    writer.close();
	}
	
}
