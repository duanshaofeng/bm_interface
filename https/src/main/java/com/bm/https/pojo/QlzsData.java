package com.bm.https.pojo;

import javax.validation.constraints.NotBlank;

/**权利证书实体
 * Created by 86133 on 2020/6/10.
 */

public class QlzsData {


    private String bh;
    @NotBlank(message = "不动产权号不能为空")
    private String cqzh;
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
    @NotBlank(message = "权利人类型不能为空")
    private String qlrlx;
    @NotBlank(message = "共有情况不能为空")
    private String gyqk;
    private String gyr;
    private String gyrzjhm;
    private String gybl;
    private String yqlrgx;
    private String fbczbs;
    @NotBlank(message = "不动产单元号不能为空")
    private String dyh;
    @NotBlank(message = "坐落不能为空")
    private String zl;
    @NotBlank(message = "用途不能为空")
    private String yt;
    @NotBlank(message = "用途代码不能为空")
    private String ytdm;
    @NotBlank(message = "面积不能为空")
    private String mj;
    @NotBlank(message = "面积单位不能为空")
    private String mjdw;
    @NotBlank(message = "面积单位代码不能为空")
    private String mjdwdm;
    private String ftsm;
    private StringBuffer ft;
    private String ftjzd;
    @NotBlank(message = "权利类型不能为空")
    private String qllx;
    @NotBlank(message = "权利类型代码不能为空")
    private String qllxdm;
    private String qlxz;
    private String qlxzdm;
    private String syqxq;
    private String syqxz;
    private String bz;
    @NotBlank(message = "权利其他情况不能为空")
    private String qlqtqk;
    private String fj;
    private String fjy;
    private String fty;
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

    public String getCqzh() {
        return cqzh;
    }

    public void setCqzh(String cqzh) {
        this.cqzh = cqzh;
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

    public String getQlrlx() {
        return qlrlx;
    }

    public void setQlrlx(String qlrlx) {
        this.qlrlx = qlrlx;
    }

    public String getGyqk() {
        return gyqk;
    }

    public void setGyqk(String gyqk) {
        this.gyqk = gyqk;
    }

    public String getGyr() {
        return gyr;
    }

    public void setGyr(String gyr) {
        this.gyr = gyr;
    }

    public String getGyrzjhm() {
        return gyrzjhm;
    }

    public void setGyrzjhm(String gyrzjhm) {
        this.gyrzjhm = gyrzjhm;
    }

    public String getGybl() {
        return gybl;
    }

    public void setGybl(String gybl) {
        this.gybl = gybl;
    }

    public String getYqlrgx() {
        return yqlrgx;
    }

    public void setYqlrgx(String yqlrgx) {
        this.yqlrgx = yqlrgx;
    }

    public String getFbczbs() {
        return fbczbs;
    }

    public void setFbczbs(String fbczbs) {
        this.fbczbs = fbczbs;
    }

    public String getDyh() {
        return dyh;
    }

    public void setDyh(String dyh) {
        this.dyh = dyh;
    }

    public String getZl() {
        return zl;
    }

    public void setZl(String zl) {
        this.zl = zl;
    }

    public String getYt() {
        return yt;
    }

    public void setYt(String yt) {
        this.yt = yt;
    }

    public String getYtdm() {
        return ytdm;
    }

    public void setYtdm(String ytdm) {
        this.ytdm = ytdm;
    }

    public String getMj() {
        return mj;
    }

    public void setMj(String mj) {
        this.mj = mj;
    }

    public String getMjdw() {
        return mjdw;
    }

    public void setMjdw(String mjdw) {
        this.mjdw = mjdw;
    }

    public String getMjdwdm() {
        return mjdwdm;
    }

    public void setMjdwdm(String mjdwdm) {
        this.mjdwdm = mjdwdm;
    }

    public String getFtsm() {
        return ftsm;
    }

    public void setFtsm(String ftsm) {
        this.ftsm = ftsm;
    }

    public StringBuffer getFt() {
        return ft;
    }

    public void setFt(StringBuffer ft) {
        this.ft = ft;
    }

    public String getFtjzd() {
        return ftjzd;
    }

    public void setFtjzd(String ftjzd) {
        this.ftjzd = ftjzd;
    }

    public String getQllx() {
        return qllx;
    }

    public void setQllx(String qllx) {
        this.qllx = qllx;
    }

    public String getQllxdm() {
        return qllxdm;
    }

    public void setQllxdm(String qllxdm) {
        this.qllxdm = qllxdm;
    }

    public String getQlxz() {
        return qlxz;
    }

    public void setQlxz(String qlxz) {
        this.qlxz = qlxz;
    }

    public String getQlxzdm() {
        return qlxzdm;
    }

    public void setQlxzdm(String qlxzdm) {
        this.qlxzdm = qlxzdm;
    }

    public String getSyqxq() {
        return syqxq;
    }

    public void setSyqxq(String syqxq) {
        this.syqxq = syqxq;
    }

    public String getSyqxz() {
        return syqxz;
    }

    public void setSyqxz(String syqxz) {
        this.syqxz = syqxz;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getQlqtqk() {
        return qlqtqk;
    }

    public void setQlqtqk(String qlqtqk) {
        this.qlqtqk = qlqtqk;
    }

    public String getFj() {
        return fj;
    }

    public void setFj(String fj) {
        this.fj = fj;
    }

    public String getFjy() {
        return fjy;
    }

    public void setFjy(String fjy) {
        this.fjy = fjy;
    }

    public String getFty() {
        return fty;
    }

    public void setFty(String fty) {
        this.fty = fty;
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
