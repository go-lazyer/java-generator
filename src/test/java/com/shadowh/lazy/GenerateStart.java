package com.shadowh.lazy;

import org.junit.Test;

public class GenerateStart {
	
	public static void main(String[] args) {
		GenerateCode gencode = new GenerateCode();
		try {
			String str="";
			gencode.gencode(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void Test(){
		GenerateCode gencode = new GenerateCode();
		try {
			String str="lazy-config.xml";
			gencode.gencode(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
