package simon.demo.core.util.fastexcel;
import java.io.Serializable;

public class RandFFutrueBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@MapperCell(cellName="订单ID")
	private String ad_contract_id;	//订单ID
	@MapperCell(cellName="订单名称")
	private String contract_name;	//订单name
	@MapperCell(cellName="投放ID")
	private String ad_cast_id;	//投放id
	@MapperCell(cellName="投放名称")
	private String cast_name;	//投放name *
	@MapperCell(cellName="素材ID")
	private String ad_creative_id;	//素材id *
	@MapperCell(cellName="素材名称")
	private String creative_name;	//素材name *
	@MapperCell(cellName="省份")
	private String province_name;	//省份
	@MapperCell(cellName="城市")
	private String city_name;	//省份
	@MapperCell(cellName="AD曝光1+UV")
	private String show1;	//ad曝光
	@MapperCell(cellName="AD曝光2+UV")
	private String show2;	//ad曝光
	@MapperCell(cellName="AD曝光3+UV")
	private String show3;	//ad曝光
	@MapperCell(cellName="AD曝光4+UV")
	private String show4;	//ad曝光
	@MapperCell(cellName="AD曝光5+UV")
	private String show5;	//ad曝光
	@MapperCell(cellName="AD曝光6+以上UV")
	private String show6;	//ad曝光
	@MapperCell(cellName="优土点击(次)")
	private String click_yt;	//优土点击
	@MapperCell(cellName="AD点击(次)")
	private String click_admaster;	//ad点击
	@MapperCell(cellName="AD点击1+UV")
	private String click1;	//点击差异
	@MapperCell(cellName="AD点击2+UV")
	private String click2;	//点击差异
	@MapperCell(cellName="AD点击3+UV")
	private String click3;	//点击差异
	@MapperCell(cellName="AD点击4+UV")
	private String click4;	//点击差异
	@MapperCell(cellName="AD点击5+UV")
	private String click5;	//点击差异
	@MapperCell(cellName="AD点击6+以上UV")
	private String click6;	//点击差异
	public String getAd_contract_id() {
		return ad_contract_id;
	}
	public void setAd_contract_id(String ad_contract_id) {
		this.ad_contract_id = ad_contract_id;
	}
	public String getContract_name() {
		return contract_name;
	}
	public void setContract_name(String contract_name) {
		this.contract_name = contract_name;
	}
	public String getAd_cast_id() {
		return ad_cast_id;
	}
	public void setAd_cast_id(String ad_cast_id) {
		this.ad_cast_id = ad_cast_id;
	}
	public String getCast_name() {
		return cast_name;
	}
	public void setCast_name(String cast_name) {
		this.cast_name = cast_name;
	}
	public String getAd_creative_id() {
		return ad_creative_id;
	}
	public void setAd_creative_id(String ad_creative_id) {
		this.ad_creative_id = ad_creative_id;
	}
	public String getCreative_name() {
		return creative_name;
	}
	public void setCreative_name(String creative_name) {
		this.creative_name = creative_name;
	}
	public String getProvince_name() {
		return province_name;
	}
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getShow1() {
		return show1;
	}
	public void setShow1(String show1) {
		this.show1 = show1;
	}
	public String getShow2() {
		return show2;
	}
	public void setShow2(String show2) {
		this.show2 = show2;
	}
	public String getShow3() {
		return show3;
	}
	public void setShow3(String show3) {
		this.show3 = show3;
	}
	public String getShow4() {
		return show4;
	}
	public void setShow4(String show4) {
		this.show4 = show4;
	}
	public String getShow5() {
		return show5;
	}
	public void setShow5(String show5) {
		this.show5 = show5;
	}
	public String getShow6() {
		return show6;
	}
	public void setShow6(String show6) {
		this.show6 = show6;
	}
	public String getClick_yt() {
		return click_yt;
	}
	public void setClick_yt(String click_yt) {
		this.click_yt = click_yt;
	}
	public String getClick_admaster() {
		return click_admaster;
	}
	public void setClick_admaster(String click_admaster) {
		this.click_admaster = click_admaster;
	}
	public String getClick1() {
		return click1;
	}
	public void setClick1(String click1) {
		this.click1 = click1;
	}
	public String getClick2() {
		return click2;
	}
	public void setClick2(String click2) {
		this.click2 = click2;
	}
	public String getClick3() {
		return click3;
	}
	public void setClick3(String click3) {
		this.click3 = click3;
	}
	public String getClick4() {
		return click4;
	}
	public void setClick4(String click4) {
		this.click4 = click4;
	}
	public String getClick5() {
		return click5;
	}
	public void setClick5(String click5) {
		this.click5 = click5;
	}
	public String getClick6() {
		return click6;
	}
	public void setClick6(String click6) {
		this.click6 = click6;
	}
}
