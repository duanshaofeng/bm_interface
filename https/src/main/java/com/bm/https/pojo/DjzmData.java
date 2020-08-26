package com.bm.https.pojo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**登记证明实体
 * Created by 86133 on 2020/6/10.
 */

public class DjzmData {


    private String bh;
    @NotBlank(message = "登记证明号不能为空")
    private String djzmh;
    @NotBlank(message = "电子证照标识不能为空")
    private String djzzbs;
    @NotBlank(message = "登记结构名称不能为空")
    private String djjg;
    @NotBlank(message = "登记结构代码不能为空")
    private String djjgdm;
    @NotBlank(message = "登记时间不能为空")
    private String djsj;
    @NotBlank(message = "权利人名称不能为空")
    private String qlr;
    @NotBlank(message = "权利人证件号码不能为空")
    private String qlrzjhm;
    @NotBlank(message = "权利人证件号码种类不能为空")
    private String qlrzjhmlx;
    private String cqzsh;
    @NotBlank(message = "不动产单元号不能为空")
    private String dyh;
    @NotBlank(message = "义务人名称不能为空")
    private String ywr;
    @NotBlank(message = "义务人证件号码不能为空")
    private String ywrzjhm;
    @NotBlank(message = "义务人证件号码种类不能为空")
    private String ywrzjhmlx;
    @NotBlank(message = "坐落不能为空")
    private String zl;
    @NotBlank(message = "证明权力或事项不能为空")
    private String zmqlhsx;
    @NotBlank(message = "权力或事项编码不能为空")
    private String qlhsxdm;
    @NotBlank(message = "其他不能为空")
    private String qt;
    private String fj;
    @NotBlank(message = "信息二维码不能为空")
    private StringBuffer xxewm;
    @NotBlank(message = "查询二维码不能为空")
    private StringBuffer cxewm;

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getDjzmh() {
        return djzmh;
    }

    public void setDjzmh(String djzmh) {
        this.djzmh = djzmh;
    }

    public String getDjzzbs() {
        return djzzbs;
    }

    public void setDjzzbs(String djzzbs) {
        this.djzzbs = djzzbs;
    }

    public String getDjjg() {
        return djjg;
    }

    public void setDjjg(String djjg) {
        this.djjg = djjg;
    }

    public String getDjjgdm() {
        return djjgdm;
    }

    public void setDjjgdm(String djjgdm) {
        this.djjgdm = djjgdm;
    }

    public String getDjsj() {
        return djsj;
    }

    public void setDjsj(String djsj) {
        this.djsj = djsj;
    }

    public String getQlr() {
        return qlr;
    }

    public void setQlr(String qlr) {
        this.qlr = qlr;
    }

    public String getQlrzjhm() {
        return qlrzjhm;
    }

    public void setQlrzjhm(String qlrzjhm) {
        this.qlrzjhm = qlrzjhm;
    }

    public String getQlrzjhmlx() {
        return qlrzjhmlx;
    }

    public void setQlrzjhmlx(String qlrzjhmlx) {
        this.qlrzjhmlx = qlrzjhmlx;
    }

    public String getCqzsh() {
        return cqzsh;
    }

    public void setCqzsh(String cqzsh) {
        this.cqzsh = cqzsh;
    }

    public String getDyh() {
        return dyh;
    }

    public void setDyh(String dyh) {
        this.dyh = dyh;
    }

    public String getYwr() {
        return ywr;
    }

    public void setYwr(String ywr) {
        this.ywr = ywr;
    }

    public String getYwrzjhm() {
        return ywrzjhm;
    }

    public void setYwrzjhm(String ywrzjhm) {
        this.ywrzjhm = ywrzjhm;
    }

    public String getYwrzjhmlx() {
        return ywrzjhmlx;
    }

    public void setYwrzjhmlx(String ywrzjhmlx) {
        this.ywrzjhmlx = ywrzjhmlx;
    }

    public String getZl() {
        return zl;
    }

    public void setZl(String zl) {
        this.zl = zl;
    }

    public String getZmqlhsx() {
        return zmqlhsx;
    }

    public void setZmqlhsx(String zmqlhsx) {
        this.zmqlhsx = zmqlhsx;
    }

    public String getQlhsxdm() {
        return qlhsxdm;
    }

    public void setQlhsxdm(String qlhsxdm) {
        this.qlhsxdm = qlhsxdm;
    }

    public String getQt() {
        return qt;
    }

    public void setQt(String qt) {
        this.qt = qt;
    }

    public String getFj() {
        return fj;
    }

    public void setFj(String fj) {
        this.fj = fj;
    }

    public StringBuffer getXxewm() {
        return xxewm;
    }

    public void setXxewm(StringBuffer xxewm) {
        this.xxewm = xxewm;
    }

    public StringBuffer getCxewm() {
        return cxewm;
    }

    public void setCxewm(StringBuffer cxewm) {
        this.cxewm = cxewm;
    }
}
