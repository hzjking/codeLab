package hzj.query;


import hzj.page.Page;

/**
 * Created by Administrator on 2016/8/3.
 */
public class ApplyDataQueryDTO extends Page<ApplyDataQueryDTO> {
    private Long id;
    private String realName;
    private String phone;
    private String shopName;
    private String sandState;
    private String createTime;
    private String successTime;
    private String expectedFinancingAmtStart;
    private String expectedFinancingAmtEnd;
    private String createTimeStart;
    private String createTimeEnd;
    private String expectedFinancingTerms;
    private String expectedFinancingAmt;
    private String activeState;
    private String code;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getSandState() {
        return sandState;
    }

    public void setSandState(String sandState) {
        this.sandState = sandState;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(String successTime) {
        this.successTime = successTime;
    }

    public String getExpectedFinancingAmtStart() {
        return expectedFinancingAmtStart;
    }

    public String getExpectedFinancingAmtEnd() {
        return expectedFinancingAmtEnd;
    }

    public String getCreateTimeStart() {
        return createTimeStart;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setExpectedFinancingAmtStart(String expectedFinancingAmtStart) {
        this.expectedFinancingAmtStart = expectedFinancingAmtStart;
    }

    public void setExpectedFinancingAmtEnd(String expectedFinancingAmtEnd) {
        this.expectedFinancingAmtEnd = expectedFinancingAmtEnd;
    }

    public void setCreateTimeStart(String createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getRealName() {
        return realName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getExpectedFinancingTerms() {
        return expectedFinancingTerms;
    }

    public String getExpectedFinancingAmt() {
        return expectedFinancingAmt;
    }

    public void setExpectedFinancingTerms(String expectedFinancingTerms) {
        this.expectedFinancingTerms = expectedFinancingTerms;
    }

    public void setExpectedFinancingAmt(String expectedFinancingAmt) {
        this.expectedFinancingAmt = expectedFinancingAmt;
    }

    public String getActiveState() {
        return activeState;
    }

    public void setActiveState(String activeState) {
        this.activeState = activeState;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}