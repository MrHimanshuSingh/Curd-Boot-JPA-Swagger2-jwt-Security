package com.springboot.serviceImpl;

public class Delete {
	void	xsx() {
		System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
		
	}
	public static void main(String[] args) {
		Delete delete = new Delete();
        delete.xsx();
	}
}
