package simon.demo.core.util.jsonresult;

import java.util.List;

public class ExtGrid<T> implements JsonResult {
	private List<T> invdata;
	private int total;
	private Object info;
	
	public ExtGrid() {
	}
	
	public ExtGrid(List<T> invdata) {
		this.invdata = invdata;
		this.total = invdata.size();
	}
	
	public ExtGrid(List<T> invdata, int total) {
		this.invdata = invdata;
		this.total = total;
	}
	
	public ExtGrid(List<T> invdata, int total, Object info) {
		this.invdata = invdata;
		this.total = total;
		this.info = info;
	}
	
	public List<T> getInvdata() {
		return invdata;
	}
	public void setInvdata(List<T> invdata) {
		this.invdata = invdata;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}
	
}
