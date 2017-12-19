package com.shop.user.model.pojo;

import java.util.Date;

public class ShopUser {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_user.id
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_user.name
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_user.nick_name
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    private String nickName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_user.sex
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    private Integer sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_user.mobile
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_user.statu
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    private Integer statu;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_user.reg_comefrom
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    private Integer regComefrom;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_user.reg_time
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    private Date regTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_user.last_login_time
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    private Date lastLoginTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_user.token
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    private String token;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_user.token_expire_time
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    private Date tokenExpireTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_user.create_time
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop_user.modified_time
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    private Date modifiedTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_user.id
     *
     * @return the value of shop_user.id
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_user.id
     *
     * @param id the value for shop_user.id
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_user.name
     *
     * @return the value of shop_user.name
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_user.name
     *
     * @param name the value for shop_user.name
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_user.nick_name
     *
     * @return the value of shop_user.nick_name
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_user.nick_name
     *
     * @param nickName the value for shop_user.nick_name
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_user.sex
     *
     * @return the value of shop_user.sex
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_user.sex
     *
     * @param sex the value for shop_user.sex
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_user.mobile
     *
     * @return the value of shop_user.mobile
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_user.mobile
     *
     * @param mobile the value for shop_user.mobile
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_user.statu
     *
     * @return the value of shop_user.statu
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public Integer getStatu() {
        return statu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_user.statu
     *
     * @param statu the value for shop_user.statu
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public void setStatu(Integer statu) {
        this.statu = statu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_user.reg_comefrom
     *
     * @return the value of shop_user.reg_comefrom
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public Integer getRegComefrom() {
        return regComefrom;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_user.reg_comefrom
     *
     * @param regComefrom the value for shop_user.reg_comefrom
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public void setRegComefrom(Integer regComefrom) {
        this.regComefrom = regComefrom;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_user.reg_time
     *
     * @return the value of shop_user.reg_time
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public Date getRegTime() {
        return regTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_user.reg_time
     *
     * @param regTime the value for shop_user.reg_time
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_user.last_login_time
     *
     * @return the value of shop_user.last_login_time
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_user.last_login_time
     *
     * @param lastLoginTime the value for shop_user.last_login_time
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_user.token
     *
     * @return the value of shop_user.token
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_user.token
     *
     * @param token the value for shop_user.token
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_user.token_expire_time
     *
     * @return the value of shop_user.token_expire_time
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public Date getTokenExpireTime() {
        return tokenExpireTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_user.token_expire_time
     *
     * @param tokenExpireTime the value for shop_user.token_expire_time
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public void setTokenExpireTime(Date tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_user.create_time
     *
     * @return the value of shop_user.create_time
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_user.create_time
     *
     * @param createTime the value for shop_user.create_time
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop_user.modified_time
     *
     * @return the value of shop_user.modified_time
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop_user.modified_time
     *
     * @param modifiedTime the value for shop_user.modified_time
     *
     * @mbggenerated Sun Dec 17 16:10:34 CST 2017
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}