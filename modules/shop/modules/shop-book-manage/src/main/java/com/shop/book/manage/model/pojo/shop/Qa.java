package com.shop.book.manage.model.pojo.shop;

import com.shop.base.model.BasePojo;
import java.util.Date;

public class Qa extends BasePojo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qa.id
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qa.question
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    private String question;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qa.type
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qa.answer
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    private String answer;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qa.create_time
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qa.is_show
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    private Integer isShow;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qa.sort
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    private Integer sort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qa.modified_time
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    private Date modifiedTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qa.id
     *
     * @return the value of qa.id
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qa.id
     *
     * @param id the value for qa.id
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qa.question
     *
     * @return the value of qa.question
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    public String getQuestion() {
        return question;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qa.question
     *
     * @param question the value for qa.question
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qa.type
     *
     * @return the value of qa.type
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qa.type
     *
     * @param type the value for qa.type
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qa.answer
     *
     * @return the value of qa.answer
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qa.answer
     *
     * @param answer the value for qa.answer
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qa.create_time
     *
     * @return the value of qa.create_time
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qa.create_time
     *
     * @param createTime the value for qa.create_time
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qa.is_show
     *
     * @return the value of qa.is_show
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    public Integer getIsShow() {
        return isShow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qa.is_show
     *
     * @param isShow the value for qa.is_show
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qa.sort
     *
     * @return the value of qa.sort
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qa.sort
     *
     * @param sort the value for qa.sort
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qa.modified_time
     *
     * @return the value of qa.modified_time
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qa.modified_time
     *
     * @param modifiedTime the value for qa.modified_time
     *
     * @mbggenerated Fri Dec 28 10:40:19 CST 2018
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}