package com.krzem.logic_gates;



import java.util.List;
import java.util.ArrayList;
import java.lang.Math;



public class LogicEngine{
	private static final String DOUBLE_V_LINE_CHAR="║";
	private static final String DOUBLE_H_LINE_CHAR="═";
	private static final String DOUBLE_DL_CORNER_CHAR="╔";
	private static final String DOUBLE_DR_CORNER_CHAR="╗";
	private static final String DOUBLE_UL_CORNER_CHAR="╚";
	private static final String DOUBLE_UR_CORNER_CHAR="╝";
	private static final String DOUBLE_V_LINE_RIGHT_LINE_CHAR="╟";
	private static final String DOUBLE_V_LINE_LEFT_LINE_CHAR="╢";
	private static final String DOUBLE_V_LINE_H_LINE_CHAR="╫";
	private static final String SINGLE_H_LINE_CHAR="─";
	private static final String SINGLE_DOUBLE_LU_CORNER_CHAR="╜";
	private static final String SINGLE_DOUBLE_LD_CORNER_CHAR="╖";
	private static final String DOUBLE_U_LINE_SINGLE_H_LINE_CHAR="╨";
	private static final String DOUBLE_D_LINE_SINGLE_H_LINE_CHAR="╥";
	private int in;
	private List<LogicGate> lgl;
	private int op;
	private boolean[] ol;



	public LogicEngine(int in){
		this.in=in;
		this.lgl=new ArrayList<LogicGate>();
		this.measure();
	}



	public void add(String op,int... il){
		this.lgl.add(new LogicGate(op,il));
	}



	public void measure(int... ol){
		this.ol=new boolean[this.in];
		this.op=ol.length;
		for (int a:ol){
			this.ol[a]=true;
		}
	}



	public boolean[] evaluate(boolean[] in){
		for (LogicGate lg:this.lgl){
			in=lg._eval(in);
		}
		boolean[] o=new boolean[this.op];
		int j=0;
		for (int i=0;i<this.in;i++){
			if (this.ol[i]==true){
				o[j]=in[i];
				j++;
			}
		}
		return o;
	}



	public void draw(){
		int pad=Integer.toString(this.in-1).length();
		int opad=Integer.toString(this.op-1).length();
		List<String> sl=new ArrayList<String>();
		List<String> spl=new ArrayList<String>();
		for (int i=0;i<this.in;i++){
			if (i!=0){
				spl.add(this._fill(this.lgl.size()*2+2," "));
			}
			sl.add(String.format("[%s]>%s%s",this._center(Integer.toString(i),pad),DOUBLE_V_LINE_RIGHT_LINE_CHAR,this._fill(this.lgl.size()*2+2,SINGLE_H_LINE_CHAR)));
		}
		int idx=1;
		int idxp=pad+5;
		for (LogicGate lg:this.lgl){
			this._charAt(sl,lg.il[0],idxp,lg._abbr());
			if (lg.il.length==2){
				if (lg.il[0]<lg.il[1]){
					this._charAt(sl,lg.il[1],idxp,DOUBLE_U_LINE_SINGLE_H_LINE_CHAR);
					for (int i=lg.il[1]-1;i>lg.il[0]-1;i--){
						this._charAt(spl,i,idx,DOUBLE_V_LINE_CHAR);
					}
					for (int i=lg.il[1]-1;i>lg.il[0];i--){
						this._charAt(sl,i,idxp,DOUBLE_V_LINE_H_LINE_CHAR);
					}
				}
				else{
					this._charAt(sl,lg.il[1],idxp,DOUBLE_D_LINE_SINGLE_H_LINE_CHAR);
					for (int i=lg.il[1];i<lg.il[0];i++){
						this._charAt(spl,i,idx,DOUBLE_V_LINE_CHAR);
					}
					for (int i=lg.il[1]+1;i<lg.il[0];i++){
						this._charAt(sl,i,idxp,DOUBLE_V_LINE_H_LINE_CHAR);
					}
				}
			}
			idx+=2;
			idxp+=2;
		}
		List<String> osl=new ArrayList<String>();
		String pd=this._fill(pad+3," ");
		String hb=this._fill(this.lgl.size()*2+2,DOUBLE_H_LINE_CHAR);
		osl.add(String.format("%s%s%s%s",pd,DOUBLE_DL_CORNER_CHAR,hb,DOUBLE_DR_CORNER_CHAR));
		int oi=0;
		for (int i=0;i<this.in;i++){
			if (i!=0){
				osl.add(String.format("%s%s%s%s",pd,DOUBLE_V_LINE_CHAR,spl.get(i-1),DOUBLE_V_LINE_CHAR));
			}
			osl.add(sl.get(i)+(this.ol[i]==true?String.format("%s>[%s]",DOUBLE_V_LINE_LEFT_LINE_CHAR,this._center(Integer.toString(oi),opad)):DOUBLE_V_LINE_LEFT_LINE_CHAR));
			if (this.ol[i]==true){
				oi++;
			}
		}
		osl.add(String.format("%s%s%s%s",pd,DOUBLE_UL_CORNER_CHAR,hb,DOUBLE_UR_CORNER_CHAR));
		System.out.println(String.join("\n",osl));
	}



	private String _fill(int l,String chr){
		String o="";
		for (int i=0;i<l;i++){
			o+=chr;
		}
		return o;
	}


	private String _center(String s,int l){
		int a=(l-s.length())/2;
		if (a==0){
			return String.format((l-s.length()==1?" ":"")+"%-"+(l-(l-s.length()==1?1:0))+"s",s);
		}
		return String.format("%"+a+"s%-"+(l-a)+"s","",s);
	}


	private String _charAt(String o,int i,String chr){
		return o.substring(0,i)+chr+(i+1<o.length()?o.substring(i+1):"");
	}



	private void _charAt(List<String> o,int i,int j,String chr){
		o.set(i,this._charAt(o.get(i),j,chr));
	}
}