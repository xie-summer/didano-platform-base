package cn.didano.base.model;

import java.util.Date;

public class Vd_auth_oss {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vd_auth_oss.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vd_auth_oss.vd_channel_id
     *
     * @mbg.generated
     */
    private Integer vdChannelId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vd_auth_oss.created
     *
     * @mbg.generated
     */
    private Date created;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vd_auth_oss.status
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vd_auth_oss.temp_oss_key
     *
     * @mbg.generated
     */
    private String tempOssKey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vd_auth_oss.updated
     *
     * @mbg.generated
     */
    private Date updated;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vd_auth_oss.id
     *
     * @return the value of vd_auth_oss.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vd_auth_oss.id
     *
     * @param id the value for vd_auth_oss.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vd_auth_oss.vd_channel_id
     *
     * @return the value of vd_auth_oss.vd_channel_id
     *
     * @mbg.generated
     */
    public Integer getVdChannelId() {
        return vdChannelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vd_auth_oss.vd_channel_id
     *
     * @param vdChannelId the value for vd_auth_oss.vd_channel_id
     *
     * @mbg.generated
     */
    public void setVdChannelId(Integer vdChannelId) {
        this.vdChannelId = vdChannelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vd_auth_oss.created
     *
     * @return the value of vd_auth_oss.created
     *
     * @mbg.generated
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vd_auth_oss.created
     *
     * @param created the value for vd_auth_oss.created
     *
     * @mbg.generated
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vd_auth_oss.status
     *
     * @return the value of vd_auth_oss.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vd_auth_oss.status
     *
     * @param status the value for vd_auth_oss.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vd_auth_oss.temp_oss_key
     *
     * @return the value of vd_auth_oss.temp_oss_key
     *
     * @mbg.generated
     */
    public String getTempOssKey() {
        return tempOssKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vd_auth_oss.temp_oss_key
     *
     * @param tempOssKey the value for vd_auth_oss.temp_oss_key
     *
     * @mbg.generated
     */
    public void setTempOssKey(String tempOssKey) {
        this.tempOssKey = tempOssKey == null ? null : tempOssKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vd_auth_oss.updated
     *
     * @return the value of vd_auth_oss.updated
     *
     * @mbg.generated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vd_auth_oss.updated
     *
     * @param updated the value for vd_auth_oss.updated
     *
     * @mbg.generated
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}