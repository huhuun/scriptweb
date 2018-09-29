package com.yoyu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {
	
		File file = new File("");
		FileInputStream inputStream = new FileInputStream(file);
	}
}
