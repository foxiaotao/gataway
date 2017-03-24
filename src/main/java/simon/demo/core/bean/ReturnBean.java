package simon.demo.core.bean;

public class ReturnBean {
	private boolean success;
	private String msg;
	private Object result;
	private int total;
	
	public ReturnBean(boolean success,String msg){
		this.success = success;
		this.msg = msg;
	}
	public ReturnBean(boolean success,String msg,Object result,int total){
		this.success = success;
		this.msg = msg;
		this.result = result;
		this.total = total;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
