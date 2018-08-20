package com.maomao.ssm.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodsOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public GoodsOrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo=pageNo;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setStartRow(Integer startRow) {
        this.startRow=startRow;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize=pageSize;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setFields(String fields) {
        this.fields=fields;
    }

    public String getFields() {
        return fields;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrderNumIsNull() {
            addCriterion("order_num is null");
            return (Criteria) this;
        }

        public Criteria andOrderNumIsNotNull() {
            addCriterion("order_num is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNumEqualTo(String value) {
            addCriterion("order_num =", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotEqualTo(String value) {
            addCriterion("order_num <>", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThan(String value) {
            addCriterion("order_num >", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThanOrEqualTo(String value) {
            addCriterion("order_num >=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThan(String value) {
            addCriterion("order_num <", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThanOrEqualTo(String value) {
            addCriterion("order_num <=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLike(String value) {
            addCriterion("order_num like", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotLike(String value) {
            addCriterion("order_num not like", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumIn(List<String> values) {
            addCriterion("order_num in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotIn(List<String> values) {
            addCriterion("order_num not in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumBetween(String value1, String value2) {
            addCriterion("order_num between", value1, value2, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotBetween(String value1, String value2) {
            addCriterion("order_num not between", value1, value2, "orderNum");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(Long value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(Long value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(Long value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(Long value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(Long value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<Long> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<Long> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(Long value1, Long value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(Long value1, Long value2) {
            addCriterion("money not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andPayMoneyIsNull() {
            addCriterion("pay_money is null");
            return (Criteria) this;
        }

        public Criteria andPayMoneyIsNotNull() {
            addCriterion("pay_money is not null");
            return (Criteria) this;
        }

        public Criteria andPayMoneyEqualTo(Long value) {
            addCriterion("pay_money =", value, "payMoney");
            return (Criteria) this;
        }

        public Criteria andPayMoneyNotEqualTo(Long value) {
            addCriterion("pay_money <>", value, "payMoney");
            return (Criteria) this;
        }

        public Criteria andPayMoneyGreaterThan(Long value) {
            addCriterion("pay_money >", value, "payMoney");
            return (Criteria) this;
        }

        public Criteria andPayMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("pay_money >=", value, "payMoney");
            return (Criteria) this;
        }

        public Criteria andPayMoneyLessThan(Long value) {
            addCriterion("pay_money <", value, "payMoney");
            return (Criteria) this;
        }

        public Criteria andPayMoneyLessThanOrEqualTo(Long value) {
            addCriterion("pay_money <=", value, "payMoney");
            return (Criteria) this;
        }

        public Criteria andPayMoneyIn(List<Long> values) {
            addCriterion("pay_money in", values, "payMoney");
            return (Criteria) this;
        }

        public Criteria andPayMoneyNotIn(List<Long> values) {
            addCriterion("pay_money not in", values, "payMoney");
            return (Criteria) this;
        }

        public Criteria andPayMoneyBetween(Long value1, Long value2) {
            addCriterion("pay_money between", value1, value2, "payMoney");
            return (Criteria) this;
        }

        public Criteria andPayMoneyNotBetween(Long value1, Long value2) {
            addCriterion("pay_money not between", value1, value2, "payMoney");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andPayAccountIsNull() {
            addCriterion("pay_account is null");
            return (Criteria) this;
        }

        public Criteria andPayAccountIsNotNull() {
            addCriterion("pay_account is not null");
            return (Criteria) this;
        }

        public Criteria andPayAccountEqualTo(String value) {
            addCriterion("pay_account =", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountNotEqualTo(String value) {
            addCriterion("pay_account <>", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountGreaterThan(String value) {
            addCriterion("pay_account >", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountGreaterThanOrEqualTo(String value) {
            addCriterion("pay_account >=", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountLessThan(String value) {
            addCriterion("pay_account <", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountLessThanOrEqualTo(String value) {
            addCriterion("pay_account <=", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountLike(String value) {
            addCriterion("pay_account like", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountNotLike(String value) {
            addCriterion("pay_account not like", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountIn(List<String> values) {
            addCriterion("pay_account in", values, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountNotIn(List<String> values) {
            addCriterion("pay_account not in", values, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountBetween(String value1, String value2) {
            addCriterion("pay_account between", value1, value2, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountNotBetween(String value1, String value2) {
            addCriterion("pay_account not between", value1, value2, "payAccount");
            return (Criteria) this;
        }

        public Criteria andCouponRecordIdIsNull() {
            addCriterion("coupon_record_id is null");
            return (Criteria) this;
        }

        public Criteria andCouponRecordIdIsNotNull() {
            addCriterion("coupon_record_id is not null");
            return (Criteria) this;
        }

        public Criteria andCouponRecordIdEqualTo(Integer value) {
            addCriterion("coupon_record_id =", value, "couponRecordId");
            return (Criteria) this;
        }

        public Criteria andCouponRecordIdNotEqualTo(Integer value) {
            addCriterion("coupon_record_id <>", value, "couponRecordId");
            return (Criteria) this;
        }

        public Criteria andCouponRecordIdGreaterThan(Integer value) {
            addCriterion("coupon_record_id >", value, "couponRecordId");
            return (Criteria) this;
        }

        public Criteria andCouponRecordIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_record_id >=", value, "couponRecordId");
            return (Criteria) this;
        }

        public Criteria andCouponRecordIdLessThan(Integer value) {
            addCriterion("coupon_record_id <", value, "couponRecordId");
            return (Criteria) this;
        }

        public Criteria andCouponRecordIdLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_record_id <=", value, "couponRecordId");
            return (Criteria) this;
        }

        public Criteria andCouponRecordIdIn(List<Integer> values) {
            addCriterion("coupon_record_id in", values, "couponRecordId");
            return (Criteria) this;
        }

        public Criteria andCouponRecordIdNotIn(List<Integer> values) {
            addCriterion("coupon_record_id not in", values, "couponRecordId");
            return (Criteria) this;
        }

        public Criteria andCouponRecordIdBetween(Integer value1, Integer value2) {
            addCriterion("coupon_record_id between", value1, value2, "couponRecordId");
            return (Criteria) this;
        }

        public Criteria andCouponRecordIdNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_record_id not between", value1, value2, "couponRecordId");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyIsNull() {
            addCriterion("coupon_money is null");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyIsNotNull() {
            addCriterion("coupon_money is not null");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyEqualTo(Long value) {
            addCriterion("coupon_money =", value, "couponMoney");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyNotEqualTo(Long value) {
            addCriterion("coupon_money <>", value, "couponMoney");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyGreaterThan(Long value) {
            addCriterion("coupon_money >", value, "couponMoney");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("coupon_money >=", value, "couponMoney");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyLessThan(Long value) {
            addCriterion("coupon_money <", value, "couponMoney");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyLessThanOrEqualTo(Long value) {
            addCriterion("coupon_money <=", value, "couponMoney");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyIn(List<Long> values) {
            addCriterion("coupon_money in", values, "couponMoney");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyNotIn(List<Long> values) {
            addCriterion("coupon_money not in", values, "couponMoney");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyBetween(Long value1, Long value2) {
            addCriterion("coupon_money between", value1, value2, "couponMoney");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyNotBetween(Long value1, Long value2) {
            addCriterion("coupon_money not between", value1, value2, "couponMoney");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNull() {
            addCriterion("pay_time is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("pay_time is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimeEqualTo(Date value) {
            addCriterion("pay_time =", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotEqualTo(Date value) {
            addCriterion("pay_time <>", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThan(Date value) {
            addCriterion("pay_time >", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_time >=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThan(Date value) {
            addCriterion("pay_time <", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("pay_time <=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIn(List<Date> values) {
            addCriterion("pay_time in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotIn(List<Date> values) {
            addCriterion("pay_time not in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeBetween(Date value1, Date value2) {
            addCriterion("pay_time between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotBetween(Date value1, Date value2) {
            addCriterion("pay_time not between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andAccountTimeIsNull() {
            addCriterion("account_time is null");
            return (Criteria) this;
        }

        public Criteria andAccountTimeIsNotNull() {
            addCriterion("account_time is not null");
            return (Criteria) this;
        }

        public Criteria andAccountTimeEqualTo(Date value) {
            addCriterion("account_time =", value, "accountTime");
            return (Criteria) this;
        }

        public Criteria andAccountTimeNotEqualTo(Date value) {
            addCriterion("account_time <>", value, "accountTime");
            return (Criteria) this;
        }

        public Criteria andAccountTimeGreaterThan(Date value) {
            addCriterion("account_time >", value, "accountTime");
            return (Criteria) this;
        }

        public Criteria andAccountTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("account_time >=", value, "accountTime");
            return (Criteria) this;
        }

        public Criteria andAccountTimeLessThan(Date value) {
            addCriterion("account_time <", value, "accountTime");
            return (Criteria) this;
        }

        public Criteria andAccountTimeLessThanOrEqualTo(Date value) {
            addCriterion("account_time <=", value, "accountTime");
            return (Criteria) this;
        }

        public Criteria andAccountTimeIn(List<Date> values) {
            addCriterion("account_time in", values, "accountTime");
            return (Criteria) this;
        }

        public Criteria andAccountTimeNotIn(List<Date> values) {
            addCriterion("account_time not in", values, "accountTime");
            return (Criteria) this;
        }

        public Criteria andAccountTimeBetween(Date value1, Date value2) {
            addCriterion("account_time between", value1, value2, "accountTime");
            return (Criteria) this;
        }

        public Criteria andAccountTimeNotBetween(Date value1, Date value2) {
            addCriterion("account_time not between", value1, value2, "accountTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("send_time is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("send_time is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Date value) {
            addCriterion("send_time =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterion("send_time <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterion("send_time >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("send_time >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Date value) {
            addCriterion("send_time <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("send_time <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Date> values) {
            addCriterion("send_time in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterion("send_time not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterion("send_time between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("send_time not between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andOverTimeIsNull() {
            addCriterion("over_time is null");
            return (Criteria) this;
        }

        public Criteria andOverTimeIsNotNull() {
            addCriterion("over_time is not null");
            return (Criteria) this;
        }

        public Criteria andOverTimeEqualTo(Date value) {
            addCriterion("over_time =", value, "overTime");
            return (Criteria) this;
        }

        public Criteria andOverTimeNotEqualTo(Date value) {
            addCriterion("over_time <>", value, "overTime");
            return (Criteria) this;
        }

        public Criteria andOverTimeGreaterThan(Date value) {
            addCriterion("over_time >", value, "overTime");
            return (Criteria) this;
        }

        public Criteria andOverTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("over_time >=", value, "overTime");
            return (Criteria) this;
        }

        public Criteria andOverTimeLessThan(Date value) {
            addCriterion("over_time <", value, "overTime");
            return (Criteria) this;
        }

        public Criteria andOverTimeLessThanOrEqualTo(Date value) {
            addCriterion("over_time <=", value, "overTime");
            return (Criteria) this;
        }

        public Criteria andOverTimeIn(List<Date> values) {
            addCriterion("over_time in", values, "overTime");
            return (Criteria) this;
        }

        public Criteria andOverTimeNotIn(List<Date> values) {
            addCriterion("over_time not in", values, "overTime");
            return (Criteria) this;
        }

        public Criteria andOverTimeBetween(Date value1, Date value2) {
            addCriterion("over_time between", value1, value2, "overTime");
            return (Criteria) this;
        }

        public Criteria andOverTimeNotBetween(Date value1, Date value2) {
            addCriterion("over_time not between", value1, value2, "overTime");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdIsNull() {
            addCriterion("sales_user_id is null");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdIsNotNull() {
            addCriterion("sales_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdEqualTo(Integer value) {
            addCriterion("sales_user_id =", value, "salesUserId");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdNotEqualTo(Integer value) {
            addCriterion("sales_user_id <>", value, "salesUserId");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdGreaterThan(Integer value) {
            addCriterion("sales_user_id >", value, "salesUserId");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sales_user_id >=", value, "salesUserId");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdLessThan(Integer value) {
            addCriterion("sales_user_id <", value, "salesUserId");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("sales_user_id <=", value, "salesUserId");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdIn(List<Integer> values) {
            addCriterion("sales_user_id in", values, "salesUserId");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdNotIn(List<Integer> values) {
            addCriterion("sales_user_id not in", values, "salesUserId");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdBetween(Integer value1, Integer value2) {
            addCriterion("sales_user_id between", value1, value2, "salesUserId");
            return (Criteria) this;
        }

        public Criteria andSalesUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sales_user_id not between", value1, value2, "salesUserId");
            return (Criteria) this;
        }

        public Criteria andOrderSrcIsNull() {
            addCriterion("order_src is null");
            return (Criteria) this;
        }

        public Criteria andOrderSrcIsNotNull() {
            addCriterion("order_src is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSrcEqualTo(Byte value) {
            addCriterion("order_src =", value, "orderSrc");
            return (Criteria) this;
        }

        public Criteria andOrderSrcNotEqualTo(Byte value) {
            addCriterion("order_src <>", value, "orderSrc");
            return (Criteria) this;
        }

        public Criteria andOrderSrcGreaterThan(Byte value) {
            addCriterion("order_src >", value, "orderSrc");
            return (Criteria) this;
        }

        public Criteria andOrderSrcGreaterThanOrEqualTo(Byte value) {
            addCriterion("order_src >=", value, "orderSrc");
            return (Criteria) this;
        }

        public Criteria andOrderSrcLessThan(Byte value) {
            addCriterion("order_src <", value, "orderSrc");
            return (Criteria) this;
        }

        public Criteria andOrderSrcLessThanOrEqualTo(Byte value) {
            addCriterion("order_src <=", value, "orderSrc");
            return (Criteria) this;
        }

        public Criteria andOrderSrcIn(List<Byte> values) {
            addCriterion("order_src in", values, "orderSrc");
            return (Criteria) this;
        }

        public Criteria andOrderSrcNotIn(List<Byte> values) {
            addCriterion("order_src not in", values, "orderSrc");
            return (Criteria) this;
        }

        public Criteria andOrderSrcBetween(Byte value1, Byte value2) {
            addCriterion("order_src between", value1, value2, "orderSrc");
            return (Criteria) this;
        }

        public Criteria andOrderSrcNotBetween(Byte value1, Byte value2) {
            addCriterion("order_src not between", value1, value2, "orderSrc");
            return (Criteria) this;
        }

        public Criteria andExpressIsNull() {
            addCriterion("express is null");
            return (Criteria) this;
        }

        public Criteria andExpressIsNotNull() {
            addCriterion("express is not null");
            return (Criteria) this;
        }

        public Criteria andExpressEqualTo(String value) {
            addCriterion("express =", value, "express");
            return (Criteria) this;
        }

        public Criteria andExpressNotEqualTo(String value) {
            addCriterion("express <>", value, "express");
            return (Criteria) this;
        }

        public Criteria andExpressGreaterThan(String value) {
            addCriterion("express >", value, "express");
            return (Criteria) this;
        }

        public Criteria andExpressGreaterThanOrEqualTo(String value) {
            addCriterion("express >=", value, "express");
            return (Criteria) this;
        }

        public Criteria andExpressLessThan(String value) {
            addCriterion("express <", value, "express");
            return (Criteria) this;
        }

        public Criteria andExpressLessThanOrEqualTo(String value) {
            addCriterion("express <=", value, "express");
            return (Criteria) this;
        }

        public Criteria andExpressLike(String value) {
            addCriterion("express like", value, "express");
            return (Criteria) this;
        }

        public Criteria andExpressNotLike(String value) {
            addCriterion("express not like", value, "express");
            return (Criteria) this;
        }

        public Criteria andExpressIn(List<String> values) {
            addCriterion("express in", values, "express");
            return (Criteria) this;
        }

        public Criteria andExpressNotIn(List<String> values) {
            addCriterion("express not in", values, "express");
            return (Criteria) this;
        }

        public Criteria andExpressBetween(String value1, String value2) {
            addCriterion("express between", value1, value2, "express");
            return (Criteria) this;
        }

        public Criteria andExpressNotBetween(String value1, String value2) {
            addCriterion("express not between", value1, value2, "express");
            return (Criteria) this;
        }

        public Criteria andExpressNumIsNull() {
            addCriterion("express_num is null");
            return (Criteria) this;
        }

        public Criteria andExpressNumIsNotNull() {
            addCriterion("express_num is not null");
            return (Criteria) this;
        }

        public Criteria andExpressNumEqualTo(String value) {
            addCriterion("express_num =", value, "expressNum");
            return (Criteria) this;
        }

        public Criteria andExpressNumNotEqualTo(String value) {
            addCriterion("express_num <>", value, "expressNum");
            return (Criteria) this;
        }

        public Criteria andExpressNumGreaterThan(String value) {
            addCriterion("express_num >", value, "expressNum");
            return (Criteria) this;
        }

        public Criteria andExpressNumGreaterThanOrEqualTo(String value) {
            addCriterion("express_num >=", value, "expressNum");
            return (Criteria) this;
        }

        public Criteria andExpressNumLessThan(String value) {
            addCriterion("express_num <", value, "expressNum");
            return (Criteria) this;
        }

        public Criteria andExpressNumLessThanOrEqualTo(String value) {
            addCriterion("express_num <=", value, "expressNum");
            return (Criteria) this;
        }

        public Criteria andExpressNumLike(String value) {
            addCriterion("express_num like", value, "expressNum");
            return (Criteria) this;
        }

        public Criteria andExpressNumNotLike(String value) {
            addCriterion("express_num not like", value, "expressNum");
            return (Criteria) this;
        }

        public Criteria andExpressNumIn(List<String> values) {
            addCriterion("express_num in", values, "expressNum");
            return (Criteria) this;
        }

        public Criteria andExpressNumNotIn(List<String> values) {
            addCriterion("express_num not in", values, "expressNum");
            return (Criteria) this;
        }

        public Criteria andExpressNumBetween(String value1, String value2) {
            addCriterion("express_num between", value1, value2, "expressNum");
            return (Criteria) this;
        }

        public Criteria andExpressNumNotBetween(String value1, String value2) {
            addCriterion("express_num not between", value1, value2, "expressNum");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("pay_type is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(Byte value) {
            addCriterion("pay_type =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(Byte value) {
            addCriterion("pay_type <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(Byte value) {
            addCriterion("pay_type >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("pay_type >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(Byte value) {
            addCriterion("pay_type <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(Byte value) {
            addCriterion("pay_type <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<Byte> values) {
            addCriterion("pay_type in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<Byte> values) {
            addCriterion("pay_type not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(Byte value1, Byte value2) {
            addCriterion("pay_type between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("pay_type not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andGetWayIsNull() {
            addCriterion("get_way is null");
            return (Criteria) this;
        }

        public Criteria andGetWayIsNotNull() {
            addCriterion("get_way is not null");
            return (Criteria) this;
        }

        public Criteria andGetWayEqualTo(Byte value) {
            addCriterion("get_way =", value, "getWay");
            return (Criteria) this;
        }

        public Criteria andGetWayNotEqualTo(Byte value) {
            addCriterion("get_way <>", value, "getWay");
            return (Criteria) this;
        }

        public Criteria andGetWayGreaterThan(Byte value) {
            addCriterion("get_way >", value, "getWay");
            return (Criteria) this;
        }

        public Criteria andGetWayGreaterThanOrEqualTo(Byte value) {
            addCriterion("get_way >=", value, "getWay");
            return (Criteria) this;
        }

        public Criteria andGetWayLessThan(Byte value) {
            addCriterion("get_way <", value, "getWay");
            return (Criteria) this;
        }

        public Criteria andGetWayLessThanOrEqualTo(Byte value) {
            addCriterion("get_way <=", value, "getWay");
            return (Criteria) this;
        }

        public Criteria andGetWayIn(List<Byte> values) {
            addCriterion("get_way in", values, "getWay");
            return (Criteria) this;
        }

        public Criteria andGetWayNotIn(List<Byte> values) {
            addCriterion("get_way not in", values, "getWay");
            return (Criteria) this;
        }

        public Criteria andGetWayBetween(Byte value1, Byte value2) {
            addCriterion("get_way between", value1, value2, "getWay");
            return (Criteria) this;
        }

        public Criteria andGetWayNotBetween(Byte value1, Byte value2) {
            addCriterion("get_way not between", value1, value2, "getWay");
            return (Criteria) this;
        }

        public Criteria andPayWayIsNull() {
            addCriterion("pay_way is null");
            return (Criteria) this;
        }

        public Criteria andPayWayIsNotNull() {
            addCriterion("pay_way is not null");
            return (Criteria) this;
        }

        public Criteria andPayWayEqualTo(Byte value) {
            addCriterion("pay_way =", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayNotEqualTo(Byte value) {
            addCriterion("pay_way <>", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayGreaterThan(Byte value) {
            addCriterion("pay_way >", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayGreaterThanOrEqualTo(Byte value) {
            addCriterion("pay_way >=", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayLessThan(Byte value) {
            addCriterion("pay_way <", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayLessThanOrEqualTo(Byte value) {
            addCriterion("pay_way <=", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayIn(List<Byte> values) {
            addCriterion("pay_way in", values, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayNotIn(List<Byte> values) {
            addCriterion("pay_way not in", values, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayBetween(Byte value1, Byte value2) {
            addCriterion("pay_way between", value1, value2, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayNotBetween(Byte value1, Byte value2) {
            addCriterion("pay_way not between", value1, value2, "payWay");
            return (Criteria) this;
        }

        public Criteria andAdminIdIsNull() {
            addCriterion("admin_id is null");
            return (Criteria) this;
        }

        public Criteria andAdminIdIsNotNull() {
            addCriterion("admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdminIdEqualTo(Integer value) {
            addCriterion("admin_id =", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotEqualTo(Integer value) {
            addCriterion("admin_id <>", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdGreaterThan(Integer value) {
            addCriterion("admin_id >", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("admin_id >=", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdLessThan(Integer value) {
            addCriterion("admin_id <", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdLessThanOrEqualTo(Integer value) {
            addCriterion("admin_id <=", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdIn(List<Integer> values) {
            addCriterion("admin_id in", values, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotIn(List<Integer> values) {
            addCriterion("admin_id not in", values, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdBetween(Integer value1, Integer value2) {
            addCriterion("admin_id between", value1, value2, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotBetween(Integer value1, Integer value2) {
            addCriterion("admin_id not between", value1, value2, "adminId");
            return (Criteria) this;
        }

        public Criteria andRefundAddressIsNull() {
            addCriterion("refund_address is null");
            return (Criteria) this;
        }

        public Criteria andRefundAddressIsNotNull() {
            addCriterion("refund_address is not null");
            return (Criteria) this;
        }

        public Criteria andRefundAddressEqualTo(String value) {
            addCriterion("refund_address =", value, "refundAddress");
            return (Criteria) this;
        }

        public Criteria andRefundAddressNotEqualTo(String value) {
            addCriterion("refund_address <>", value, "refundAddress");
            return (Criteria) this;
        }

        public Criteria andRefundAddressGreaterThan(String value) {
            addCriterion("refund_address >", value, "refundAddress");
            return (Criteria) this;
        }

        public Criteria andRefundAddressGreaterThanOrEqualTo(String value) {
            addCriterion("refund_address >=", value, "refundAddress");
            return (Criteria) this;
        }

        public Criteria andRefundAddressLessThan(String value) {
            addCriterion("refund_address <", value, "refundAddress");
            return (Criteria) this;
        }

        public Criteria andRefundAddressLessThanOrEqualTo(String value) {
            addCriterion("refund_address <=", value, "refundAddress");
            return (Criteria) this;
        }

        public Criteria andRefundAddressLike(String value) {
            addCriterion("refund_address like", value, "refundAddress");
            return (Criteria) this;
        }

        public Criteria andRefundAddressNotLike(String value) {
            addCriterion("refund_address not like", value, "refundAddress");
            return (Criteria) this;
        }

        public Criteria andRefundAddressIn(List<String> values) {
            addCriterion("refund_address in", values, "refundAddress");
            return (Criteria) this;
        }

        public Criteria andRefundAddressNotIn(List<String> values) {
            addCriterion("refund_address not in", values, "refundAddress");
            return (Criteria) this;
        }

        public Criteria andRefundAddressBetween(String value1, String value2) {
            addCriterion("refund_address between", value1, value2, "refundAddress");
            return (Criteria) this;
        }

        public Criteria andRefundAddressNotBetween(String value1, String value2) {
            addCriterion("refund_address not between", value1, value2, "refundAddress");
            return (Criteria) this;
        }

        public Criteria andRefundNameIsNull() {
            addCriterion("refund_name is null");
            return (Criteria) this;
        }

        public Criteria andRefundNameIsNotNull() {
            addCriterion("refund_name is not null");
            return (Criteria) this;
        }

        public Criteria andRefundNameEqualTo(String value) {
            addCriterion("refund_name =", value, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundNameNotEqualTo(String value) {
            addCriterion("refund_name <>", value, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundNameGreaterThan(String value) {
            addCriterion("refund_name >", value, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundNameGreaterThanOrEqualTo(String value) {
            addCriterion("refund_name >=", value, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundNameLessThan(String value) {
            addCriterion("refund_name <", value, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundNameLessThanOrEqualTo(String value) {
            addCriterion("refund_name <=", value, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundNameLike(String value) {
            addCriterion("refund_name like", value, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundNameNotLike(String value) {
            addCriterion("refund_name not like", value, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundNameIn(List<String> values) {
            addCriterion("refund_name in", values, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundNameNotIn(List<String> values) {
            addCriterion("refund_name not in", values, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundNameBetween(String value1, String value2) {
            addCriterion("refund_name between", value1, value2, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundNameNotBetween(String value1, String value2) {
            addCriterion("refund_name not between", value1, value2, "refundName");
            return (Criteria) this;
        }

        public Criteria andRefundMobileIsNull() {
            addCriterion("refund_mobile is null");
            return (Criteria) this;
        }

        public Criteria andRefundMobileIsNotNull() {
            addCriterion("refund_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andRefundMobileEqualTo(String value) {
            addCriterion("refund_mobile =", value, "refundMobile");
            return (Criteria) this;
        }

        public Criteria andRefundMobileNotEqualTo(String value) {
            addCriterion("refund_mobile <>", value, "refundMobile");
            return (Criteria) this;
        }

        public Criteria andRefundMobileGreaterThan(String value) {
            addCriterion("refund_mobile >", value, "refundMobile");
            return (Criteria) this;
        }

        public Criteria andRefundMobileGreaterThanOrEqualTo(String value) {
            addCriterion("refund_mobile >=", value, "refundMobile");
            return (Criteria) this;
        }

        public Criteria andRefundMobileLessThan(String value) {
            addCriterion("refund_mobile <", value, "refundMobile");
            return (Criteria) this;
        }

        public Criteria andRefundMobileLessThanOrEqualTo(String value) {
            addCriterion("refund_mobile <=", value, "refundMobile");
            return (Criteria) this;
        }

        public Criteria andRefundMobileLike(String value) {
            addCriterion("refund_mobile like", value, "refundMobile");
            return (Criteria) this;
        }

        public Criteria andRefundMobileNotLike(String value) {
            addCriterion("refund_mobile not like", value, "refundMobile");
            return (Criteria) this;
        }

        public Criteria andRefundMobileIn(List<String> values) {
            addCriterion("refund_mobile in", values, "refundMobile");
            return (Criteria) this;
        }

        public Criteria andRefundMobileNotIn(List<String> values) {
            addCriterion("refund_mobile not in", values, "refundMobile");
            return (Criteria) this;
        }

        public Criteria andRefundMobileBetween(String value1, String value2) {
            addCriterion("refund_mobile between", value1, value2, "refundMobile");
            return (Criteria) this;
        }

        public Criteria andRefundMobileNotBetween(String value1, String value2) {
            addCriterion("refund_mobile not between", value1, value2, "refundMobile");
            return (Criteria) this;
        }

        public Criteria andUnpayMoneyIsNull() {
            addCriterion("unpay_money is null");
            return (Criteria) this;
        }

        public Criteria andUnpayMoneyIsNotNull() {
            addCriterion("unpay_money is not null");
            return (Criteria) this;
        }

        public Criteria andUnpayMoneyEqualTo(Long value) {
            addCriterion("unpay_money =", value, "unpayMoney");
            return (Criteria) this;
        }

        public Criteria andUnpayMoneyNotEqualTo(Long value) {
            addCriterion("unpay_money <>", value, "unpayMoney");
            return (Criteria) this;
        }

        public Criteria andUnpayMoneyGreaterThan(Long value) {
            addCriterion("unpay_money >", value, "unpayMoney");
            return (Criteria) this;
        }

        public Criteria andUnpayMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("unpay_money >=", value, "unpayMoney");
            return (Criteria) this;
        }

        public Criteria andUnpayMoneyLessThan(Long value) {
            addCriterion("unpay_money <", value, "unpayMoney");
            return (Criteria) this;
        }

        public Criteria andUnpayMoneyLessThanOrEqualTo(Long value) {
            addCriterion("unpay_money <=", value, "unpayMoney");
            return (Criteria) this;
        }

        public Criteria andUnpayMoneyIn(List<Long> values) {
            addCriterion("unpay_money in", values, "unpayMoney");
            return (Criteria) this;
        }

        public Criteria andUnpayMoneyNotIn(List<Long> values) {
            addCriterion("unpay_money not in", values, "unpayMoney");
            return (Criteria) this;
        }

        public Criteria andUnpayMoneyBetween(Long value1, Long value2) {
            addCriterion("unpay_money between", value1, value2, "unpayMoney");
            return (Criteria) this;
        }

        public Criteria andUnpayMoneyNotBetween(Long value1, Long value2) {
            addCriterion("unpay_money not between", value1, value2, "unpayMoney");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andStatusSettlementIsNull() {
            addCriterion("status_settlement is null");
            return (Criteria) this;
        }

        public Criteria andStatusSettlementIsNotNull() {
            addCriterion("status_settlement is not null");
            return (Criteria) this;
        }

        public Criteria andStatusSettlementEqualTo(Byte value) {
            addCriterion("status_settlement =", value, "statusSettlement");
            return (Criteria) this;
        }

        public Criteria andStatusSettlementNotEqualTo(Byte value) {
            addCriterion("status_settlement <>", value, "statusSettlement");
            return (Criteria) this;
        }

        public Criteria andStatusSettlementGreaterThan(Byte value) {
            addCriterion("status_settlement >", value, "statusSettlement");
            return (Criteria) this;
        }

        public Criteria andStatusSettlementGreaterThanOrEqualTo(Byte value) {
            addCriterion("status_settlement >=", value, "statusSettlement");
            return (Criteria) this;
        }

        public Criteria andStatusSettlementLessThan(Byte value) {
            addCriterion("status_settlement <", value, "statusSettlement");
            return (Criteria) this;
        }

        public Criteria andStatusSettlementLessThanOrEqualTo(Byte value) {
            addCriterion("status_settlement <=", value, "statusSettlement");
            return (Criteria) this;
        }

        public Criteria andStatusSettlementIn(List<Byte> values) {
            addCriterion("status_settlement in", values, "statusSettlement");
            return (Criteria) this;
        }

        public Criteria andStatusSettlementNotIn(List<Byte> values) {
            addCriterion("status_settlement not in", values, "statusSettlement");
            return (Criteria) this;
        }

        public Criteria andStatusSettlementBetween(Byte value1, Byte value2) {
            addCriterion("status_settlement between", value1, value2, "statusSettlement");
            return (Criteria) this;
        }

        public Criteria andStatusSettlementNotBetween(Byte value1, Byte value2) {
            addCriterion("status_settlement not between", value1, value2, "statusSettlement");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryIsNull() {
            addCriterion("order_category is null");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryIsNotNull() {
            addCriterion("order_category is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryEqualTo(Integer value) {
            addCriterion("order_category =", value, "orderCategory");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryNotEqualTo(Integer value) {
            addCriterion("order_category <>", value, "orderCategory");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryGreaterThan(Integer value) {
            addCriterion("order_category >", value, "orderCategory");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_category >=", value, "orderCategory");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryLessThan(Integer value) {
            addCriterion("order_category <", value, "orderCategory");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryLessThanOrEqualTo(Integer value) {
            addCriterion("order_category <=", value, "orderCategory");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryIn(List<Integer> values) {
            addCriterion("order_category in", values, "orderCategory");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryNotIn(List<Integer> values) {
            addCriterion("order_category not in", values, "orderCategory");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryBetween(Integer value1, Integer value2) {
            addCriterion("order_category between", value1, value2, "orderCategory");
            return (Criteria) this;
        }

        public Criteria andOrderCategoryNotBetween(Integer value1, Integer value2) {
            addCriterion("order_category not between", value1, value2, "orderCategory");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}