package com.chengtao.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.chengtao.entity.ChinaNode;

/**
 * the util to read file to get city info
 * @author ChengTao
 *
 */
public class FileUtils {
	private final static String FILE_PATH= "TreeInfo.txt";
	/**
	 * the list of nodes
	 */
	private static ArrayList<ChinaNode> chinaNodes = null;
	/**
	 * read three item info from file
	 * @param filePath the path of the file
	 */
	public static ArrayList<ChinaNode> readTreeInfoFromFile() {
		if (chinaNodes == null) {
			synchronized (FileUtils.class) {
				if (chinaNodes == null) {
					chinaNodes = new ArrayList<>();
					File treeInfoFile = new File(FILE_PATH);
					FileReader fr = null;
					BufferedReader br = null;
					try {
						fr = new FileReader(treeInfoFile);
						br = new BufferedReader(fr);
						String lineInfo = "";
						String infoArr[] = null;
						while(true){
							lineInfo = br.readLine();
							if (lineInfo == null || lineInfo.equals("")) {
								break;
							}
							infoArr = lineInfo.split(", ");
							chinaNodes.add(new ChinaNode(infoArr[0], infoArr[1]));
						}
					} catch (IOException e) {
						e.printStackTrace();
					}finally {
						if (br != null) {
							try {
								br.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						if (fr != null) {
							try {
								fr.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		return chinaNodes;
	}
}