package com.maomao.ssm.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminLoanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public AdminLoanExample() {
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

        public Criteria andLoanOldIsNull() {
            addCriterion("loan_old is null");
            return (Criteria) this;
        }

        public Criteria andLoanOldIsNotNull() {
            addCriterion("loan_old is not null");
            return (Criteria) this;
        }

        public Criteria andLoanOldEqualTo(Long value) {
            addCriterion("loan_old =", value, "loanOld");
            return (Criteria) this;
        }

        public Criteria andLoanOldNotEqualTo(Long value) {
            addCriterion("loan_old <>", value, "loanOld");
            return (Criteria) this;
        }

        public Criteria andLoanOldGreaterThan(Long value) {
            addCriterion("loan_old >", value, "loanOld");
            return (Criteria) this;
        }

        public Criteria andLoanOldGreaterThanOrEqualTo(Long value) {
            addCriterion("loan_old >=", value, "loanOld");
            return (Criteria) this;
        }

        public Criteria andLoanOldLessThan(Long value) {
            addCriterion("loan_old <", value, "loanOld");
            return (Criteria) this;
        }

        public Criteria andLoanOldLessThanOrEqualTo(Long value) {
            addCriterion("loan_old <=", value, "loanOld");
            return (Criteria) this;
        }

        public Criteria andLoanOldIn(List<Long> values) {
            addCriterion("loan_old in", values, "loanOld");
            return (Criteria) this;
        }

        public Criteria andLoanOldNotIn(List<Long> values) {
            addCriterion("loan_old not in", values, "loanOld");
            return (Criteria) this;
        }

        public Criteria andLoanOldBetween(Long value1, Long value2) {
            addCriterion("loan_old between", value1, value2, "loanOld");
            return (Criteria) this;
        }

        public Criteria andLoanOldNotBetween(Long value1, Long value2) {
            addCriterion("loan_old not between", value1, value2, "loanOld");
            return (Criteria) this;
        }

        public Criteria andLoanNewIsNull() {
            addCriterion("loan_new is null");
            return (Criteria) this;
        }

        public Criteria andLoanNewIsNotNull() {
            addCriterion("loan_new is not null");
            return (Criteria) this;
        }

        public Criteria andLoanNewEqualTo(Long value) {
            addCriterion("loan_new =", value, "loanNew");
            return (Criteria) this;
        }

        public Criteria andLoanNewNotEqualTo(Long value) {
            addCriterion("loan_new <>", value, "loanNew");
            return (Criteria) this;
        }

        public Criteria andLoanNewGreaterThan(Long value) {
            addCriterion("loan_new >", value, "loanNew");
            return (Criteria) this;
        }

        public Criteria andLoanNewGreaterThanOrEqualTo(Long value) {
            addCriterion("loan_new >=", value, "loanNew");
            return (Criteria) this;
        }

        public Criteria andLoanNewLessThan(Long value) {
            addCriterion("loan_new <", value, "loanNew");
            return (Criteria) this;
        }

        public Criteria andLoanNewLessThanOrEqualTo(Long value) {
            addCriterion("loan_new <=", value, "loanNew");
            return (Criteria) this;
        }

        public Criteria andLoanNewIn(List<Long> values) {
            addCriterion("loan_new in", values, "loanNew");
            return (Criteria) this;
        }

        public Criteria andLoanNewNotIn(List<Long> values) {
            addCriterion("loan_new not in", values, "loanNew");
            return (Criteria) this;
        }

        public Criteria andLoanNewBetween(Long value1, Long value2) {
            addCriterion("loan_new between", value1, value2, "loanNew");
            return (Criteria) this;
        }

        public Criteria andLoanNewNotBetween(Long value1, Long value2) {
            addCriterion("loan_new not between", value1, value2, "loanNew");
            return (Criteria) this;
        }

        public Criteria andLoanChangeIsNull() {
            addCriterion("loan_change is null");
            return (Criteria) this;
        }

        public Criteria andLoanChangeIsNotNull() {
            addCriterion("loan_change is not null");
            return (Criteria) this;
        }

        public Criteria andLoanChangeEqualTo(Long value) {
            addCriterion("loan_change =", value, "loanChange");
            return (Criteria) this;
        }

        public Criteria andLoanChangeNotEqualTo(Long value) {
            addCriterion("loan_change <>", value, "loanChange");
            return (Criteria) this;
        }

        public Criteria andLoanChangeGreaterThan(Long value) {
            addCriterion("loan_change >", value, "loanChange");
            return (Criteria) this;
        }

        public Criteria andLoanChangeGreaterThanOrEqualTo(Long value) {
            addCriterion("loan_change >=", value, "loanChange");
            return (Criteria) this;
        }

        public Criteria andLoanChangeLessThan(Long value) {
            addCriterion("loan_change <", value, "loanChange");
            return (Criteria) this;
        }

        public Criteria andLoanChangeLessThanOrEqualTo(Long value) {
            addCriterion("loan_change <=", value, "loanChange");
            return (Criteria) this;
        }

        public Criteria andLoanChangeIn(List<Long> values) {
            addCriterion("loan_change in", values, "loanChange");
            return (Criteria) this;
        }

        public Criteria andLoanChangeNotIn(List<Long> values) {
            addCriterion("loan_change not in", values, "loanChange");
            return (Criteria) this;
        }

        public Criteria andLoanChangeBetween(Long value1, Long value2) {
            addCriterion("loan_change between", value1, value2, "loanChange");
            return (Criteria) this;
        }

        public Criteria andLoanChangeNotBetween(Long value1, Long value2) {
            addCriterion("loan_change not between", value1, value2, "loanChange");
            return (Criteria) this;
        }

        public Criteria andBizIdIsNull() {
            addCriterion("biz_id is null");
            return (Criteria) this;
        }

        public Criteria andBizIdIsNotNull() {
            addCriterion("biz_id is not null");
            return (Criteria) this;
        }

        public Criteria andBizIdEqualTo(Integer value) {
            addCriterion("biz_id =", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdNotEqualTo(Integer value) {
            addCriterion("biz_id <>", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdGreaterThan(Integer value) {
            addCriterion("biz_id >", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("biz_id >=", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdLessThan(Integer value) {
            addCriterion("biz_id <", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdLessThanOrEqualTo(Integer value) {
            addCriterion("biz_id <=", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdIn(List<Integer> values) {
            addCriterion("biz_id in", values, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdNotIn(List<Integer> values) {
            addCriterion("biz_id not in", values, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdBetween(Integer value1, Integer value2) {
            addCriterion("biz_id between", value1, value2, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdNotBetween(Integer value1, Integer value2) {
            addCriterion("biz_id not between", value1, value2, "bizId");
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