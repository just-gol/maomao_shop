package com.maomao.ssm.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailyDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DailyDetailExample() {
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

        public Criteria andOrderPayMoneyIsNull() {
            addCriterion("order_pay_money is null");
            return (Criteria) this;
        }

        public Criteria andOrderPayMoneyIsNotNull() {
            addCriterion("order_pay_money is not null");
            return (Criteria) this;
        }

        public Criteria andOrderPayMoneyEqualTo(Long value) {
            addCriterion("order_pay_money =", value, "orderPayMoney");
            return (Criteria) this;
        }

        public Criteria andOrderPayMoneyNotEqualTo(Long value) {
            addCriterion("order_pay_money <>", value, "orderPayMoney");
            return (Criteria) this;
        }

        public Criteria andOrderPayMoneyGreaterThan(Long value) {
            addCriterion("order_pay_money >", value, "orderPayMoney");
            return (Criteria) this;
        }

        public Criteria andOrderPayMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("order_pay_money >=", value, "orderPayMoney");
            return (Criteria) this;
        }

        public Criteria andOrderPayMoneyLessThan(Long value) {
            addCriterion("order_pay_money <", value, "orderPayMoney");
            return (Criteria) this;
        }

        public Criteria andOrderPayMoneyLessThanOrEqualTo(Long value) {
            addCriterion("order_pay_money <=", value, "orderPayMoney");
            return (Criteria) this;
        }

        public Criteria andOrderPayMoneyIn(List<Long> values) {
            addCriterion("order_pay_money in", values, "orderPayMoney");
            return (Criteria) this;
        }

        public Criteria andOrderPayMoneyNotIn(List<Long> values) {
            addCriterion("order_pay_money not in", values, "orderPayMoney");
            return (Criteria) this;
        }

        public Criteria andOrderPayMoneyBetween(Long value1, Long value2) {
            addCriterion("order_pay_money between", value1, value2, "orderPayMoney");
            return (Criteria) this;
        }

        public Criteria andOrderPayMoneyNotBetween(Long value1, Long value2) {
            addCriterion("order_pay_money not between", value1, value2, "orderPayMoney");
            return (Criteria) this;
        }

        public Criteria andOrderRefundMoneyIsNull() {
            addCriterion("order_refund_money is null");
            return (Criteria) this;
        }

        public Criteria andOrderRefundMoneyIsNotNull() {
            addCriterion("order_refund_money is not null");
            return (Criteria) this;
        }

        public Criteria andOrderRefundMoneyEqualTo(Long value) {
            addCriterion("order_refund_money =", value, "orderRefundMoney");
            return (Criteria) this;
        }

        public Criteria andOrderRefundMoneyNotEqualTo(Long value) {
            addCriterion("order_refund_money <>", value, "orderRefundMoney");
            return (Criteria) this;
        }

        public Criteria andOrderRefundMoneyGreaterThan(Long value) {
            addCriterion("order_refund_money >", value, "orderRefundMoney");
            return (Criteria) this;
        }

        public Criteria andOrderRefundMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("order_refund_money >=", value, "orderRefundMoney");
            return (Criteria) this;
        }

        public Criteria andOrderRefundMoneyLessThan(Long value) {
            addCriterion("order_refund_money <", value, "orderRefundMoney");
            return (Criteria) this;
        }

        public Criteria andOrderRefundMoneyLessThanOrEqualTo(Long value) {
            addCriterion("order_refund_money <=", value, "orderRefundMoney");
            return (Criteria) this;
        }

        public Criteria andOrderRefundMoneyIn(List<Long> values) {
            addCriterion("order_refund_money in", values, "orderRefundMoney");
            return (Criteria) this;
        }

        public Criteria andOrderRefundMoneyNotIn(List<Long> values) {
            addCriterion("order_refund_money not in", values, "orderRefundMoney");
            return (Criteria) this;
        }

        public Criteria andOrderRefundMoneyBetween(Long value1, Long value2) {
            addCriterion("order_refund_money between", value1, value2, "orderRefundMoney");
            return (Criteria) this;
        }

        public Criteria andOrderRefundMoneyNotBetween(Long value1, Long value2) {
            addCriterion("order_refund_money not between", value1, value2, "orderRefundMoney");
            return (Criteria) this;
        }

        public Criteria andCouponGetMoneyIsNull() {
            addCriterion("coupon_get_money is null");
            return (Criteria) this;
        }

        public Criteria andCouponGetMoneyIsNotNull() {
            addCriterion("coupon_get_money is not null");
            return (Criteria) this;
        }

        public Criteria andCouponGetMoneyEqualTo(Long value) {
            addCriterion("coupon_get_money =", value, "couponGetMoney");
            return (Criteria) this;
        }

        public Criteria andCouponGetMoneyNotEqualTo(Long value) {
            addCriterion("coupon_get_money <>", value, "couponGetMoney");
            return (Criteria) this;
        }

        public Criteria andCouponGetMoneyGreaterThan(Long value) {
            addCriterion("coupon_get_money >", value, "couponGetMoney");
            return (Criteria) this;
        }

        public Criteria andCouponGetMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("coupon_get_money >=", value, "couponGetMoney");
            return (Criteria) this;
        }

        public Criteria andCouponGetMoneyLessThan(Long value) {
            addCriterion("coupon_get_money <", value, "couponGetMoney");
            return (Criteria) this;
        }

        public Criteria andCouponGetMoneyLessThanOrEqualTo(Long value) {
            addCriterion("coupon_get_money <=", value, "couponGetMoney");
            return (Criteria) this;
        }

        public Criteria andCouponGetMoneyIn(List<Long> values) {
            addCriterion("coupon_get_money in", values, "couponGetMoney");
            return (Criteria) this;
        }

        public Criteria andCouponGetMoneyNotIn(List<Long> values) {
            addCriterion("coupon_get_money not in", values, "couponGetMoney");
            return (Criteria) this;
        }

        public Criteria andCouponGetMoneyBetween(Long value1, Long value2) {
            addCriterion("coupon_get_money between", value1, value2, "couponGetMoney");
            return (Criteria) this;
        }

        public Criteria andCouponGetMoneyNotBetween(Long value1, Long value2) {
            addCriterion("coupon_get_money not between", value1, value2, "couponGetMoney");
            return (Criteria) this;
        }

        public Criteria andCouponUseMoneyIsNull() {
            addCriterion("coupon_use_money is null");
            return (Criteria) this;
        }

        public Criteria andCouponUseMoneyIsNotNull() {
            addCriterion("coupon_use_money is not null");
            return (Criteria) this;
        }

        public Criteria andCouponUseMoneyEqualTo(Long value) {
            addCriterion("coupon_use_money =", value, "couponUseMoney");
            return (Criteria) this;
        }

        public Criteria andCouponUseMoneyNotEqualTo(Long value) {
            addCriterion("coupon_use_money <>", value, "couponUseMoney");
            return (Criteria) this;
        }

        public Criteria andCouponUseMoneyGreaterThan(Long value) {
            addCriterion("coupon_use_money >", value, "couponUseMoney");
            return (Criteria) this;
        }

        public Criteria andCouponUseMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("coupon_use_money >=", value, "couponUseMoney");
            return (Criteria) this;
        }

        public Criteria andCouponUseMoneyLessThan(Long value) {
            addCriterion("coupon_use_money <", value, "couponUseMoney");
            return (Criteria) this;
        }

        public Criteria andCouponUseMoneyLessThanOrEqualTo(Long value) {
            addCriterion("coupon_use_money <=", value, "couponUseMoney");
            return (Criteria) this;
        }

        public Criteria andCouponUseMoneyIn(List<Long> values) {
            addCriterion("coupon_use_money in", values, "couponUseMoney");
            return (Criteria) this;
        }

        public Criteria andCouponUseMoneyNotIn(List<Long> values) {
            addCriterion("coupon_use_money not in", values, "couponUseMoney");
            return (Criteria) this;
        }

        public Criteria andCouponUseMoneyBetween(Long value1, Long value2) {
            addCriterion("coupon_use_money between", value1, value2, "couponUseMoney");
            return (Criteria) this;
        }

        public Criteria andCouponUseMoneyNotBetween(Long value1, Long value2) {
            addCriterion("coupon_use_money not between", value1, value2, "couponUseMoney");
            return (Criteria) this;
        }

        public Criteria andCouponExpireMoneyIsNull() {
            addCriterion("coupon_expire_money is null");
            return (Criteria) this;
        }

        public Criteria andCouponExpireMoneyIsNotNull() {
            addCriterion("coupon_expire_money is not null");
            return (Criteria) this;
        }

        public Criteria andCouponExpireMoneyEqualTo(Long value) {
            addCriterion("coupon_expire_money =", value, "couponExpireMoney");
            return (Criteria) this;
        }

        public Criteria andCouponExpireMoneyNotEqualTo(Long value) {
            addCriterion("coupon_expire_money <>", value, "couponExpireMoney");
            return (Criteria) this;
        }

        public Criteria andCouponExpireMoneyGreaterThan(Long value) {
            addCriterion("coupon_expire_money >", value, "couponExpireMoney");
            return (Criteria) this;
        }

        public Criteria andCouponExpireMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("coupon_expire_money >=", value, "couponExpireMoney");
            return (Criteria) this;
        }

        public Criteria andCouponExpireMoneyLessThan(Long value) {
            addCriterion("coupon_expire_money <", value, "couponExpireMoney");
            return (Criteria) this;
        }

        public Criteria andCouponExpireMoneyLessThanOrEqualTo(Long value) {
            addCriterion("coupon_expire_money <=", value, "couponExpireMoney");
            return (Criteria) this;
        }

        public Criteria andCouponExpireMoneyIn(List<Long> values) {
            addCriterion("coupon_expire_money in", values, "couponExpireMoney");
            return (Criteria) this;
        }

        public Criteria andCouponExpireMoneyNotIn(List<Long> values) {
            addCriterion("coupon_expire_money not in", values, "couponExpireMoney");
            return (Criteria) this;
        }

        public Criteria andCouponExpireMoneyBetween(Long value1, Long value2) {
            addCriterion("coupon_expire_money between", value1, value2, "couponExpireMoney");
            return (Criteria) this;
        }

        public Criteria andCouponExpireMoneyNotBetween(Long value1, Long value2) {
            addCriterion("coupon_expire_money not between", value1, value2, "couponExpireMoney");
            return (Criteria) this;
        }

        public Criteria andOrderPayNumIsNull() {
            addCriterion("order_pay_num is null");
            return (Criteria) this;
        }

        public Criteria andOrderPayNumIsNotNull() {
            addCriterion("order_pay_num is not null");
            return (Criteria) this;
        }

        public Criteria andOrderPayNumEqualTo(Integer value) {
            addCriterion("order_pay_num =", value, "orderPayNum");
            return (Criteria) this;
        }

        public Criteria andOrderPayNumNotEqualTo(Integer value) {
            addCriterion("order_pay_num <>", value, "orderPayNum");
            return (Criteria) this;
        }

        public Criteria andOrderPayNumGreaterThan(Integer value) {
            addCriterion("order_pay_num >", value, "orderPayNum");
            return (Criteria) this;
        }

        public Criteria andOrderPayNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_pay_num >=", value, "orderPayNum");
            return (Criteria) this;
        }

        public Criteria andOrderPayNumLessThan(Integer value) {
            addCriterion("order_pay_num <", value, "orderPayNum");
            return (Criteria) this;
        }

        public Criteria andOrderPayNumLessThanOrEqualTo(Integer value) {
            addCriterion("order_pay_num <=", value, "orderPayNum");
            return (Criteria) this;
        }

        public Criteria andOrderPayNumIn(List<Integer> values) {
            addCriterion("order_pay_num in", values, "orderPayNum");
            return (Criteria) this;
        }

        public Criteria andOrderPayNumNotIn(List<Integer> values) {
            addCriterion("order_pay_num not in", values, "orderPayNum");
            return (Criteria) this;
        }

        public Criteria andOrderPayNumBetween(Integer value1, Integer value2) {
            addCriterion("order_pay_num between", value1, value2, "orderPayNum");
            return (Criteria) this;
        }

        public Criteria andOrderPayNumNotBetween(Integer value1, Integer value2) {
            addCriterion("order_pay_num not between", value1, value2, "orderPayNum");
            return (Criteria) this;
        }

        public Criteria andCouponGetNumIsNull() {
            addCriterion("coupon_get_num is null");
            return (Criteria) this;
        }

        public Criteria andCouponGetNumIsNotNull() {
            addCriterion("coupon_get_num is not null");
            return (Criteria) this;
        }

        public Criteria andCouponGetNumEqualTo(Integer value) {
            addCriterion("coupon_get_num =", value, "couponGetNum");
            return (Criteria) this;
        }

        public Criteria andCouponGetNumNotEqualTo(Integer value) {
            addCriterion("coupon_get_num <>", value, "couponGetNum");
            return (Criteria) this;
        }

        public Criteria andCouponGetNumGreaterThan(Integer value) {
            addCriterion("coupon_get_num >", value, "couponGetNum");
            return (Criteria) this;
        }

        public Criteria andCouponGetNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_get_num >=", value, "couponGetNum");
            return (Criteria) this;
        }

        public Criteria andCouponGetNumLessThan(Integer value) {
            addCriterion("coupon_get_num <", value, "couponGetNum");
            return (Criteria) this;
        }

        public Criteria andCouponGetNumLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_get_num <=", value, "couponGetNum");
            return (Criteria) this;
        }

        public Criteria andCouponGetNumIn(List<Integer> values) {
            addCriterion("coupon_get_num in", values, "couponGetNum");
            return (Criteria) this;
        }

        public Criteria andCouponGetNumNotIn(List<Integer> values) {
            addCriterion("coupon_get_num not in", values, "couponGetNum");
            return (Criteria) this;
        }

        public Criteria andCouponGetNumBetween(Integer value1, Integer value2) {
            addCriterion("coupon_get_num between", value1, value2, "couponGetNum");
            return (Criteria) this;
        }

        public Criteria andCouponGetNumNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_get_num not between", value1, value2, "couponGetNum");
            return (Criteria) this;
        }

        public Criteria andCouponUseNumIsNull() {
            addCriterion("coupon_use_num is null");
            return (Criteria) this;
        }

        public Criteria andCouponUseNumIsNotNull() {
            addCriterion("coupon_use_num is not null");
            return (Criteria) this;
        }

        public Criteria andCouponUseNumEqualTo(Integer value) {
            addCriterion("coupon_use_num =", value, "couponUseNum");
            return (Criteria) this;
        }

        public Criteria andCouponUseNumNotEqualTo(Integer value) {
            addCriterion("coupon_use_num <>", value, "couponUseNum");
            return (Criteria) this;
        }

        public Criteria andCouponUseNumGreaterThan(Integer value) {
            addCriterion("coupon_use_num >", value, "couponUseNum");
            return (Criteria) this;
        }

        public Criteria andCouponUseNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_use_num >=", value, "couponUseNum");
            return (Criteria) this;
        }

        public Criteria andCouponUseNumLessThan(Integer value) {
            addCriterion("coupon_use_num <", value, "couponUseNum");
            return (Criteria) this;
        }

        public Criteria andCouponUseNumLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_use_num <=", value, "couponUseNum");
            return (Criteria) this;
        }

        public Criteria andCouponUseNumIn(List<Integer> values) {
            addCriterion("coupon_use_num in", values, "couponUseNum");
            return (Criteria) this;
        }

        public Criteria andCouponUseNumNotIn(List<Integer> values) {
            addCriterion("coupon_use_num not in", values, "couponUseNum");
            return (Criteria) this;
        }

        public Criteria andCouponUseNumBetween(Integer value1, Integer value2) {
            addCriterion("coupon_use_num between", value1, value2, "couponUseNum");
            return (Criteria) this;
        }

        public Criteria andCouponUseNumNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_use_num not between", value1, value2, "couponUseNum");
            return (Criteria) this;
        }

        public Criteria andCouponExpireNumIsNull() {
            addCriterion("coupon_expire_num is null");
            return (Criteria) this;
        }

        public Criteria andCouponExpireNumIsNotNull() {
            addCriterion("coupon_expire_num is not null");
            return (Criteria) this;
        }

        public Criteria andCouponExpireNumEqualTo(Integer value) {
            addCriterion("coupon_expire_num =", value, "couponExpireNum");
            return (Criteria) this;
        }

        public Criteria andCouponExpireNumNotEqualTo(Integer value) {
            addCriterion("coupon_expire_num <>", value, "couponExpireNum");
            return (Criteria) this;
        }

        public Criteria andCouponExpireNumGreaterThan(Integer value) {
            addCriterion("coupon_expire_num >", value, "couponExpireNum");
            return (Criteria) this;
        }

        public Criteria andCouponExpireNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_expire_num >=", value, "couponExpireNum");
            return (Criteria) this;
        }

        public Criteria andCouponExpireNumLessThan(Integer value) {
            addCriterion("coupon_expire_num <", value, "couponExpireNum");
            return (Criteria) this;
        }

        public Criteria andCouponExpireNumLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_expire_num <=", value, "couponExpireNum");
            return (Criteria) this;
        }

        public Criteria andCouponExpireNumIn(List<Integer> values) {
            addCriterion("coupon_expire_num in", values, "couponExpireNum");
            return (Criteria) this;
        }

        public Criteria andCouponExpireNumNotIn(List<Integer> values) {
            addCriterion("coupon_expire_num not in", values, "couponExpireNum");
            return (Criteria) this;
        }

        public Criteria andCouponExpireNumBetween(Integer value1, Integer value2) {
            addCriterion("coupon_expire_num between", value1, value2, "couponExpireNum");
            return (Criteria) this;
        }

        public Criteria andCouponExpireNumNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_expire_num not between", value1, value2, "couponExpireNum");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterion("time not between", value1, value2, "time");
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