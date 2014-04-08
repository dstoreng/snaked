package src.snaked.Entities;

public class Pair<L, R> {
	private L l;
	private R r;
	
	public Pair(L l, R r){
		this.l = l;
		this.r = r;
	}
	
	public L getL(){
		return l;
	}
	
	public R getR(){
		return r;
	}
	
	public void setL(L l){
		this.l = l;
	}
	
	public void setR(R r){
		this.r = r;
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null)
			return false;
		if(!(o instanceof Pair))
			return false;
		Pair obj = (Pair)o;
		if((this.l.equals(obj.l)) && (this.r.equals(obj.r)))
			return true;
		else
			return false;
	}
	
	
}
