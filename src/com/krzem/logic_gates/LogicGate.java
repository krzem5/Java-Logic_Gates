package com.krzem.logic_gates;



public class LogicGate{
	public String type;
	public int[] il;



	public LogicGate(String type,int[] il){
		this.type=type.toLowerCase();
		this.il=il;
	}



	public String _abbr(){
		if (this.type.equals("not")){
			return "!";
		}
		if (this.type.equals("and")){
			return "&";
		}
		if (this.type.equals("or")){
			return "|";
		}
		if (this.type.equals("xor")){
			return "^";
		}
		return "_";
	}



	public boolean[] _eval(boolean[] in){
		if (this.type.equals("not")){
			in[this.il[0]]=!in[this.il[0]];
		}
		else if (this.type.equals("and")){
			in[this.il[0]]=in[this.il[0]]&&in[this.il[1]];
		}
		else if (this.type.equals("or")){
			in[this.il[0]]=in[this.il[0]]||in[this.il[1]];
		}
		else if (this.type.equals("xor")){
			in[this.il[0]]=in[this.il[0]]^in[this.il[1]];
		}
		return in;
	}
}