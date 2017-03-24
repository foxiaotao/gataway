package simon.demo.core.bean;

import java.math.BigDecimal;
import java.util.Date;

public class ExchangeRate {
	
	private String zhubiaoId;//jsp页面显示主表选择id的,没什么逻辑作用
	
    private BigDecimal id;

    private String yearMonth;

    private BigDecimal multIplier;

    private String currency;

    private String isphantom;

    private Date updatetime;

    private String approvalStatus;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth == null ? null : yearMonth.trim();
    }

    public BigDecimal getMultIplier() {
        return multIplier;
    }

    public void setMultIplier(BigDecimal multIplier) {
        this.multIplier = multIplier;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getIsphantom() {
        return isphantom;
    }

    public void setIsphantom(String isphantom) {
        this.isphantom = isphantom == null ? null : isphantom.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus == null ? null : approvalStatus.trim();
    }

	public String getZhubiaoId() {
		return zhubiaoId;
	}

	public void setZhubiaoId(String zhubiaoId) {
		this.zhubiaoId = zhubiaoId;
	}

	@Override
	public String toString() {
		return "ExchangeRate [zhubiaoId=" + zhubiaoId + ", id=" + id
				+ ", yearMonth=" + yearMonth + ", multIplier=" + multIplier
				+ ", currency=" + currency + ", isphantom=" + isphantom
				+ ", updatetime=" + updatetime + ", approvalStatus="
				+ approvalStatus + "]";
	}

}