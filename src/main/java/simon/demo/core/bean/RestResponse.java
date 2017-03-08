package simon.demo.core.bean;

import java.util.List;

public class RestResponse {
	private Object msg;
	private List<Product> result;
	private Object total;
	private Object success;
	
	public Object getMsg() {
		return msg;
	}
	public void setMsg(Object msg) {
		this.msg = msg;
	}
	public Object getTotal() {
		return total;
	}
	public void setTotal(Object total) {
		this.total = total;
	}
	public Object getSuccess() {
		return success;
	}
	public void setSuccess(Object success) {
		this.success = success;
	}
	public List<Product> getResult() {
		return result;
	}
	public void setResult(List<Product> result) {
		this.result = result;
	}
	
	
}