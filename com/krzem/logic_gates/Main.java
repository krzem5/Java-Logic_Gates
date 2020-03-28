package com.krzem.logic_gates;



public class Main{
	public static void main(String[] args){
		new Main();
	}



	public Main(){
		LogicEngine e=new LogicEngine(4);
		e.add("not",1);
		e.add("and",0,2);
		e.add("or",1,0);
		e.add("xor",3,1);
		e.add("not",2);
		e.add("xor",1,2);
		e.measure(1,3);
		e.draw2();
		boolean[] o=e.evaluate(new boolean[]{true,true,false,true});
		System.out.printf("%b %b\n",o[0],o[1]);
	}
}