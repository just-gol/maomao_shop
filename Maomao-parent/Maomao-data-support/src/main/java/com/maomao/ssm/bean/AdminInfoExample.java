package com.maomao.ssm.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public AdminInfoExample() {
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

        public Criteria andLeaglPersonIsNull() {
            addCriterion("leagl_person is null");
            return (Criteria) this;
        }

        public Criteria andLeaglPersonIsNotNull() {
            addCriterion("leagl_person is not null");
            return (Criteria) this;
        }

        public Criteria andLeaglPersonEqualTo(String value) {
            addCriterion("leagl_person =", value, "leaglPerson");
            return (Criteria) this;
        }

        public Criteria andLeaglPersonNotEqualTo(String value) {
            addCriterion("leagl_person <>", value, "leaglPerson");
            return (Criteria) this;
        }

        public Criteria andLeaglPersonGreaterThan(String value) {
            addCriterion("leagl_person >", value, "leaglPerson");
            return (Criteria) this;
        }

        public Criteria andLeaglPersonGreaterThanOrEqualTo(String value) {
            addCriterion("leagl_person >=", value, "leaglPerson");
            return (Criteria) this;
        }

        public Criteria andLeaglPersonLessThan(String value) {
            addCriterion("leagl_person <", value, "leaglPerson");
            return (Criteria) this;
        }

        public Criteria andLeaglPersonLessThanOrEqualTo(String value) {
            addCriterion("leagl_person <=", value, "leaglPerson");
            return (Criteria) this;
        }

        public Criteria andLeaglPersonLike(String value) {
            addCriterion("leagl_person like", value, "leaglPerson");
            return (Criteria) this;
        }

        public Criteria andLeaglPersonNotLike(String value) {
            addCriterion("leagl_person not like", value, "leaglPerson");
            return (Criteria) this;
        }

        public Criteria andLeaglPersonIn(List<String> values) {
            addCriterion("leagl_person in", values, "leaglPerson");
            return (Criteria) this;
        }

        public Criteria andLeaglPersonNotIn(List<String> values) {
            addCriterion("leagl_person not in", values, "leaglPerson");
            return (Criteria) this;
        }

        public Criteria andLeaglPersonBetween(String value1, String value2) {
            addCriterion("leagl_person between", value1, value2, "leaglPerson");
            return (Criteria) this;
        }

        public Criteria andLeaglPersonNotBetween(String value1, String value2) {
            addCriterion("leagl_person not between", value1, value2, "leaglPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonMobileIsNull() {
            addCriterion("legal_person_mobile_ is null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonMobileIsNotNull() {
            addCriterion("legal_person_mobile_ is not null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonMobileEqualTo(String value) {
            addCriterion("legal_person_mobile_ =", value, "legalPersonMobile");
            return (Criteria) this;
        }

        public Criteria andLegalPersonMobileNotEqualTo(String value) {
            addCriterion("legal_person_mobile_ <>", value, "legalPersonMobile");
            return (Criteria) this;
        }

        public Criteria andLegalPersonMobileGreaterThan(String value) {
            addCriterion("legal_person_mobile_ >", value, "legalPersonMobile");
            return (Criteria) this;
        }

        public Criteria andLegalPersonMobileGreaterThanOrEqualTo(String value) {
            addCriterion("legal_person_mobile_ >=", value, "legalPersonMobile");
            return (Criteria) this;
        }

        public Criteria andLegalPersonMobileLessThan(String value) {
            addCriterion("legal_person_mobile_ <", value, "legalPersonMobile");
            return (Criteria) this;
        }

        public Criteria andLegalPersonMobileLessThanOrEqualTo(String value) {
            addCriterion("legal_person_mobile_ <=", value, "legalPersonMobile");
            return (Criteria) this;
        }

        public Criteria andLegalPersonMobileLike(String value) {
            addCriterion("legal_person_mobile_ like", value, "legalPersonMobile");
            return (Criteria) this;
        }

        public Criteria andLegalPersonMobileNotLike(String value) {
            addCriterion("legal_person_mobile_ not like", value, "legalPersonMobile");
            return (Criteria) this;
        }

        public Criteria andLegalPersonMobileIn(List<String> values) {
            addCriterion("legal_person_mobile_ in", values, "legalPersonMobile");
            return (Criteria) this;
        }

        public Criteria andLegalPersonMobileNotIn(List<String> values) {
            addCriterion("legal_person_mobile_ not in", values, "legalPersonMobile");
            return (Criteria) this;
        }

        public Criteria andLegalPersonMobileBetween(String value1, String value2) {
            addCriterion("legal_person_mobile_ between", value1, value2, "legalPersonMobile");
            return (Criteria) this;
        }

        public Criteria andLegalPersonMobileNotBetween(String value1, String value2) {
            addCriterion("legal_person_mobile_ not between", value1, value2, "legalPersonMobile");
            return (Criteria) this;
        }

        public Criteria andBankAccountIsNull() {
            addCriterion("bank_account is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountIsNotNull() {
            addCriterion("bank_account is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountEqualTo(String value) {
            addCriterion("bank_account =", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotEqualTo(String value) {
            addCriterion("bank_account <>", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountGreaterThan(String value) {
            addCriterion("bank_account >", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountGreaterThanOrEqualTo(String value) {
            addCriterion("bank_account >=", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLessThan(String value) {
            addCriterion("bank_account <", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLessThanOrEqualTo(String value) {
            addCriterion("bank_account <=", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountLike(String value) {
            addCriterion("bank_account like", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotLike(String value) {
            addCriterion("bank_account not like", value, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountIn(List<String> values) {
            addCriterion("bank_account in", values, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotIn(List<String> values) {
            addCriterion("bank_account not in", values, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountBetween(String value1, String value2) {
            addCriterion("bank_account between", value1, value2, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankAccountNotBetween(String value1, String value2) {
            addCriterion("bank_account not between", value1, value2, "bankAccount");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("bank_name is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("bank_name =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("bank_name <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("bank_name >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_name >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("bank_name <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("bank_name <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("bank_name like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("bank_name not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("bank_name in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("bank_name not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("bank_name between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("bank_name not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankUserIsNull() {
            addCriterion("bank_user is null");
            return (Criteria) this;
        }

        public Criteria andBankUserIsNotNull() {
            addCriterion("bank_user is not null");
            return (Criteria) this;
        }

        public Criteria andBankUserEqualTo(String value) {
            addCriterion("bank_user =", value, "bankUser");
            return (Criteria) this;
        }

        public Criteria andBankUserNotEqualTo(String value) {
            addCriterion("bank_user <>", value, "bankUser");
            return (Criteria) this;
        }

        public Criteria andBankUserGreaterThan(String value) {
            addCriterion("bank_user >", value, "bankUser");
            return (Criteria) this;
        }

        public Criteria andBankUserGreaterThanOrEqualTo(String value) {
            addCriterion("bank_user >=", value, "bankUser");
            return (Criteria) this;
        }

        public Criteria andBankUserLessThan(String value) {
            addCriterion("bank_user <", value, "bankUser");
            return (Criteria) this;
        }

        public Criteria andBankUserLessThanOrEqualTo(String value) {
            addCriterion("bank_user <=", value, "bankUser");
            return (Criteria) this;
        }

        public Criteria andBankUserLike(String value) {
            addCriterion("bank_user like", value, "bankUser");
            return (Criteria) this;
        }

        public Criteria andBankUserNotLike(String value) {
            addCriterion("bank_user not like", value, "bankUser");
            return (Criteria) this;
        }

        public Criteria andBankUserIn(List<String> values) {
            addCriterion("bank_user in", values, "bankUser");
            return (Criteria) this;
        }

        public Criteria andBankUserNotIn(List<String> values) {
            addCriterion("bank_user not in", values, "bankUser");
            return (Criteria) this;
        }

        public Criteria andBankUserBetween(String value1, String value2) {
            addCriterion("bank_user between", value1, value2, "bankUser");
            return (Criteria) this;
        }

        public Criteria andBankUserNotBetween(String value1, String value2) {
            addCriterion("bank_user not between", value1, value2, "bankUser");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankAccountIsNull() {
            addCriterion("repayment_bank_account is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankAccountIsNotNull() {
            addCriterion("repayment_bank_account is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankAccountEqualTo(String value) {
            addCriterion("repayment_bank_account =", value, "repaymentBankAccount");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankAccountNotEqualTo(String value) {
            addCriterion("repayment_bank_account <>", value, "repaymentBankAccount");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankAccountGreaterThan(String value) {
            addCriterion("repayment_bank_account >", value, "repaymentBankAccount");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankAccountGreaterThanOrEqualTo(String value) {
            addCriterion("repayment_bank_account >=", value, "repaymentBankAccount");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankAccountLessThan(String value) {
            addCriterion("repayment_bank_account <", value, "repaymentBankAccount");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankAccountLessThanOrEqualTo(String value) {
            addCriterion("repayment_bank_account <=", value, "repaymentBankAccount");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankAccountLike(String value) {
            addCriterion("repayment_bank_account like", value, "repaymentBankAccount");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankAccountNotLike(String value) {
            addCriterion("repayment_bank_account not like", value, "repaymentBankAccount");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankAccountIn(List<String> values) {
            addCriterion("repayment_bank_account in", values, "repaymentBankAccount");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankAccountNotIn(List<String> values) {
            addCriterion("repayment_bank_account not in", values, "repaymentBankAccount");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankAccountBetween(String value1, String value2) {
            addCriterion("repayment_bank_account between", value1, value2, "repaymentBankAccount");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankAccountNotBetween(String value1, String value2) {
            addCriterion("repayment_bank_account not between", value1, value2, "repaymentBankAccount");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankNameIsNull() {
            addCriterion("repayment_bank_name is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankNameIsNotNull() {
            addCriterion("repayment_bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankNameEqualTo(String value) {
            addCriterion("repayment_bank_name =", value, "repaymentBankName");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankNameNotEqualTo(String value) {
            addCriterion("repayment_bank_name <>", value, "repaymentBankName");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankNameGreaterThan(String value) {
            addCriterion("repayment_bank_name >", value, "repaymentBankName");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("repayment_bank_name >=", value, "repaymentBankName");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankNameLessThan(String value) {
            addCriterion("repayment_bank_name <", value, "repaymentBankName");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankNameLessThanOrEqualTo(String value) {
            addCriterion("repayment_bank_name <=", value, "repaymentBankName");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankNameLike(String value) {
            addCriterion("repayment_bank_name like", value, "repaymentBankName");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankNameNotLike(String value) {
            addCriterion("repayment_bank_name not like", value, "repaymentBankName");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankNameIn(List<String> values) {
            addCriterion("repayment_bank_name in", values, "repaymentBankName");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankNameNotIn(List<String> values) {
            addCriterion("repayment_bank_name not in", values, "repaymentBankName");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankNameBetween(String value1, String value2) {
            addCriterion("repayment_bank_name between", value1, value2, "repaymentBankName");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankNameNotBetween(String value1, String value2) {
            addCriterion("repayment_bank_name not between", value1, value2, "repaymentBankName");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankUserIsNull() {
            addCriterion("repayment_bank_user is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankUserIsNotNull() {
            addCriterion("repayment_bank_user is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankUserEqualTo(String value) {
            addCriterion("repayment_bank_user =", value, "repaymentBankUser");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankUserNotEqualTo(String value) {
            addCriterion("repayment_bank_user <>", value, "repaymentBankUser");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankUserGreaterThan(String value) {
            addCriterion("repayment_bank_user >", value, "repaymentBankUser");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankUserGreaterThanOrEqualTo(String value) {
            addCriterion("repayment_bank_user >=", value, "repaymentBankUser");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankUserLessThan(String value) {
            addCriterion("repayment_bank_user <", value, "repaymentBankUser");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankUserLessThanOrEqualTo(String value) {
            addCriterion("repayment_bank_user <=", value, "repaymentBankUser");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankUserLike(String value) {
            addCriterion("repayment_bank_user like", value, "repaymentBankUser");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankUserNotLike(String value) {
            addCriterion("repayment_bank_user not like", value, "repaymentBankUser");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankUserIn(List<String> values) {
            addCriterion("repayment_bank_user in", values, "repaymentBankUser");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankUserNotIn(List<String> values) {
            addCriterion("repayment_bank_user not in", values, "repaymentBankUser");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankUserBetween(String value1, String value2) {
            addCriterion("repayment_bank_user between", value1, value2, "repaymentBankUser");
            return (Criteria) this;
        }

        public Criteria andRepaymentBankUserNotBetween(String value1, String value2) {
            addCriterion("repayment_bank_user not between", value1, value2, "repaymentBankUser");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeIsNull() {
            addCriterion("company_code is null");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeIsNotNull() {
            addCriterion("company_code is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeEqualTo(String value) {
            addCriterion("company_code =", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotEqualTo(String value) {
            addCriterion("company_code <>", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeGreaterThan(String value) {
            addCriterion("company_code >", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("company_code >=", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeLessThan(String value) {
            addCriterion("company_code <", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeLessThanOrEqualTo(String value) {
            addCriterion("company_code <=", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeLike(String value) {
            addCriterion("company_code like", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotLike(String value) {
            addCriterion("company_code not like", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeIn(List<String> values) {
            addCriterion("company_code in", values, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotIn(List<String> values) {
            addCriterion("company_code not in", values, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeBetween(String value1, String value2) {
            addCriterion("company_code between", value1, value2, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotBetween(String value1, String value2) {
            addCriterion("company_code not between", value1, value2, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIsNull() {
            addCriterion("company_address is null");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIsNotNull() {
            addCriterion("company_address is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressEqualTo(String value) {
            addCriterion("company_address =", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotEqualTo(String value) {
            addCriterion("company_address <>", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressGreaterThan(String value) {
            addCriterion("company_address >", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressGreaterThanOrEqualTo(String value) {
            addCriterion("company_address >=", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLessThan(String value) {
            addCriterion("company_address <", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLessThanOrEqualTo(String value) {
            addCriterion("company_address <=", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLike(String value) {
            addCriterion("company_address like", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotLike(String value) {
            addCriterion("company_address not like", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIn(List<String> values) {
            addCriterion("company_address in", values, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotIn(List<String> values) {
            addCriterion("company_address not in", values, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressBetween(String value1, String value2) {
            addCriterion("company_address between", value1, value2, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotBetween(String value1, String value2) {
            addCriterion("company_address not between", value1, value2, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andEnclosureIsNull() {
            addCriterion("enclosure is null");
            return (Criteria) this;
        }

        public Criteria andEnclosureIsNotNull() {
            addCriterion("enclosure is not null");
            return (Criteria) this;
        }

        public Criteria andEnclosureEqualTo(String value) {
            addCriterion("enclosure =", value, "enclosure");
            return (Criteria) this;
        }

        public Criteria andEnclosureNotEqualTo(String value) {
            addCriterion("enclosure <>", value, "enclosure");
            return (Criteria) this;
        }

        public Criteria andEnclosureGreaterThan(String value) {
            addCriterion("enclosure >", value, "enclosure");
            return (Criteria) this;
        }

        public Criteria andEnclosureGreaterThanOrEqualTo(String value) {
            addCriterion("enclosure >=", value, "enclosure");
            return (Criteria) this;
        }

        public Criteria andEnclosureLessThan(String value) {
            addCriterion("enclosure <", value, "enclosure");
            return (Criteria) this;
        }

        public Criteria andEnclosureLessThanOrEqualTo(String value) {
            addCriterion("enclosure <=", value, "enclosure");
            return (Criteria) this;
        }

        public Criteria andEnclosureLike(String value) {
            addCriterion("enclosure like", value, "enclosure");
            return (Criteria) this;
        }

        public Criteria andEnclosureNotLike(String value) {
            addCriterion("enclosure not like", value, "enclosure");
            return (Criteria) this;
        }

        public Criteria andEnclosureIn(List<String> values) {
            addCriterion("enclosure in", values, "enclosure");
            return (Criteria) this;
        }

        public Criteria andEnclosureNotIn(List<String> values) {
            addCriterion("enclosure not in", values, "enclosure");
            return (Criteria) this;
        }

        public Criteria andEnclosureBetween(String value1, String value2) {
            addCriterion("enclosure between", value1, value2, "enclosure");
            return (Criteria) this;
        }

        public Criteria andEnclosureNotBetween(String value1, String value2) {
            addCriterion("enclosure not between", value1, value2, "enclosure");
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

        public Criteria andModifiedTimeIsNull() {
            addCriterion("modified_time is null");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIsNotNull() {
            addCriterion("modified_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeEqualTo(Date value) {
            addCriterion("modified_time =", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotEqualTo(Date value) {
            addCriterion("modified_time <>", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeGreaterThan(Date value) {
            addCriterion("modified_time >", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modified_time >=", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeLessThan(Date value) {
            addCriterion("modified_time <", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeLessThanOrEqualTo(Date value) {
            addCriterion("modified_time <=", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIn(List<Date> values) {
            addCriterion("modified_time in", values, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotIn(List<Date> values) {
            addCriterion("modified_time not in", values, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeBetween(Date value1, Date value2) {
            addCriterion("modified_time between", value1, value2, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotBetween(Date value1, Date value2) {
            addCriterion("modified_time not between", value1, value2, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andRepayTimeIsNull() {
            addCriterion("repay_time is null");
            return (Criteria) this;
        }

        public Criteria andRepayTimeIsNotNull() {
            addCriterion("repay_time is not null");
            return (Criteria) this;
        }

        public Criteria andRepayTimeEqualTo(Date value) {
            addCriterion("repay_time =", value, "repayTime");
            return (Criteria) this;
        }

        public Criteria andRepayTimeNotEqualTo(Date value) {
            addCriterion("repay_time <>", value, "repayTime");
            return (Criteria) this;
        }

        public Criteria andRepayTimeGreaterThan(Date value) {
            addCriterion("repay_time >", value, "repayTime");
            return (Criteria) this;
        }

        public Criteria andRepayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("repay_time >=", value, "repayTime");
            return (Criteria) this;
        }

        public Criteria andRepayTimeLessThan(Date value) {
            addCriterion("repay_time <", value, "repayTime");
            return (Criteria) this;
        }

        public Criteria andRepayTimeLessThanOrEqualTo(Date value) {
            addCriterion("repay_time <=", value, "repayTime");
            return (Criteria) this;
        }

        public Criteria andRepayTimeIn(List<Date> values) {
            addCriterion("repay_time in", values, "repayTime");
            return (Criteria) this;
        }

        public Criteria andRepayTimeNotIn(List<Date> values) {
            addCriterion("repay_time not in", values, "repayTime");
            return (Criteria) this;
        }

        public Criteria andRepayTimeBetween(Date value1, Date value2) {
            addCriterion("repay_time between", value1, value2, "repayTime");
            return (Criteria) this;
        }

        public Criteria andRepayTimeNotBetween(Date value1, Date value2) {
            addCriterion("repay_time not between", value1, value2, "repayTime");
            return (Criteria) this;
        }

        public Criteria andMortageIsNull() {
            addCriterion("mortage is null");
            return (Criteria) this;
        }

        public Criteria andMortageIsNotNull() {
            addCriterion("mortage is not null");
            return (Criteria) this;
        }

        public Criteria andMortageEqualTo(Long value) {
            addCriterion("mortage =", value, "mortage");
            return (Criteria) this;
        }

        public Criteria andMortageNotEqualTo(Long value) {
            addCriterion("mortage <>", value, "mortage");
            return (Criteria) this;
        }

        public Criteria andMortageGreaterThan(Long value) {
            addCriterion("mortage >", value, "mortage");
            return (Criteria) this;
        }

        public Criteria andMortageGreaterThanOrEqualTo(Long value) {
            addCriterion("mortage >=", value, "mortage");
            return (Criteria) this;
        }

        public Criteria andMortageLessThan(Long value) {
            addCriterion("mortage <", value, "mortage");
            return (Criteria) this;
        }

        public Criteria andMortageLessThanOrEqualTo(Long value) {
            addCriterion("mortage <=", value, "mortage");
            return (Criteria) this;
        }

        public Criteria andMortageIn(List<Long> values) {
            addCriterion("mortage in", values, "mortage");
            return (Criteria) this;
        }

        public Criteria andMortageNotIn(List<Long> values) {
            addCriterion("mortage not in", values, "mortage");
            return (Criteria) this;
        }

        public Criteria andMortageBetween(Long value1, Long value2) {
            addCriterion("mortage between", value1, value2, "mortage");
            return (Criteria) this;
        }

        public Criteria andMortageNotBetween(Long value1, Long value2) {
            addCriterion("mortage not between", value1, value2, "mortage");
            return (Criteria) this;
        }

        public Criteria andLoanIsNull() {
            addCriterion("loan is null");
            return (Criteria) this;
        }

        public Criteria andLoanIsNotNull() {
            addCriterion("loan is not null");
            return (Criteria) this;
        }

        public Criteria andLoanEqualTo(Long value) {
            addCriterion("loan =", value, "loan");
            return (Criteria) this;
        }

        public Criteria andLoanNotEqualTo(Long value) {
            addCriterion("loan <>", value, "loan");
            return (Criteria) this;
        }

        public Criteria andLoanGreaterThan(Long value) {
            addCriterion("loan >", value, "loan");
            return (Criteria) this;
        }

        public Criteria andLoanGreaterThanOrEqualTo(Long value) {
            addCriterion("loan >=", value, "loan");
            return (Criteria) this;
        }

        public Criteria andLoanLessThan(Long value) {
            addCriterion("loan <", value, "loan");
            return (Criteria) this;
        }

        public Criteria andLoanLessThanOrEqualTo(Long value) {
            addCriterion("loan <=", value, "loan");
            return (Criteria) this;
        }

        public Criteria andLoanIn(List<Long> values) {
            addCriterion("loan in", values, "loan");
            return (Criteria) this;
        }

        public Criteria andLoanNotIn(List<Long> values) {
            addCriterion("loan not in", values, "loan");
            return (Criteria) this;
        }

        public Criteria andLoanBetween(Long value1, Long value2) {
            addCriterion("loan between", value1, value2, "loan");
            return (Criteria) this;
        }

        public Criteria andLoanNotBetween(Long value1, Long value2) {
            addCriterion("loan not between", value1, value2, "loan");
            return (Criteria) this;
        }

        public Criteria andAccountsBankIsNull() {
            addCriterion("accounts_bank is null");
            return (Criteria) this;
        }

        public Criteria andAccountsBankIsNotNull() {
            addCriterion("accounts_bank is not null");
            return (Criteria) this;
        }

        public Criteria andAccountsBankEqualTo(String value) {
            addCriterion("accounts_bank =", value, "accountsBank");
            return (Criteria) this;
        }

        public Criteria andAccountsBankNotEqualTo(String value) {
            addCriterion("accounts_bank <>", value, "accountsBank");
            return (Criteria) this;
        }

        public Criteria andAccountsBankGreaterThan(String value) {
            addCriterion("accounts_bank >", value, "accountsBank");
            return (Criteria) this;
        }

        public Criteria andAccountsBankGreaterThanOrEqualTo(String value) {
            addCriterion("accounts_bank >=", value, "accountsBank");
            return (Criteria) this;
        }

        public Criteria andAccountsBankLessThan(String value) {
            addCriterion("accounts_bank <", value, "accountsBank");
            return (Criteria) this;
        }

        public Criteria andAccountsBankLessThanOrEqualTo(String value) {
            addCriterion("accounts_bank <=", value, "accountsBank");
            return (Criteria) this;
        }

        public Criteria andAccountsBankLike(String value) {
            addCriterion("accounts_bank like", value, "accountsBank");
            return (Criteria) this;
        }

        public Criteria andAccountsBankNotLike(String value) {
            addCriterion("accounts_bank not like", value, "accountsBank");
            return (Criteria) this;
        }

        public Criteria andAccountsBankIn(List<String> values) {
            addCriterion("accounts_bank in", values, "accountsBank");
            return (Criteria) this;
        }

        public Criteria andAccountsBankNotIn(List<String> values) {
            addCriterion("accounts_bank not in", values, "accountsBank");
            return (Criteria) this;
        }

        public Criteria andAccountsBankBetween(String value1, String value2) {
            addCriterion("accounts_bank between", value1, value2, "accountsBank");
            return (Criteria) this;
        }

        public Criteria andAccountsBankNotBetween(String value1, String value2) {
            addCriterion("accounts_bank not between", value1, value2, "accountsBank");
            return (Criteria) this;
        }

        public Criteria andRepaymentAccountsBankIsNull() {
            addCriterion("repayment_accounts_bank is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentAccountsBankIsNotNull() {
            addCriterion("repayment_accounts_bank is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentAccountsBankEqualTo(String value) {
            addCriterion("repayment_accounts_bank =", value, "repaymentAccountsBank");
            return (Criteria) this;
        }

        public Criteria andRepaymentAccountsBankNotEqualTo(String value) {
            addCriterion("repayment_accounts_bank <>", value, "repaymentAccountsBank");
            return (Criteria) this;
        }

        public Criteria andRepaymentAccountsBankGreaterThan(String value) {
            addCriterion("repayment_accounts_bank >", value, "repaymentAccountsBank");
            return (Criteria) this;
        }

        public Criteria andRepaymentAccountsBankGreaterThanOrEqualTo(String value) {
            addCriterion("repayment_accounts_bank >=", value, "repaymentAccountsBank");
            return (Criteria) this;
        }

        public Criteria andRepaymentAccountsBankLessThan(String value) {
            addCriterion("repayment_accounts_bank <", value, "repaymentAccountsBank");
            return (Criteria) this;
        }

        public Criteria andRepaymentAccountsBankLessThanOrEqualTo(String value) {
            addCriterion("repayment_accounts_bank <=", value, "repaymentAccountsBank");
            return (Criteria) this;
        }

        public Criteria andRepaymentAccountsBankLike(String value) {
            addCriterion("repayment_accounts_bank like", value, "repaymentAccountsBank");
            return (Criteria) this;
        }

        public Criteria andRepaymentAccountsBankNotLike(String value) {
            addCriterion("repayment_accounts_bank not like", value, "repaymentAccountsBank");
            return (Criteria) this;
        }

        public Criteria andRepaymentAccountsBankIn(List<String> values) {
            addCriterion("repayment_accounts_bank in", values, "repaymentAccountsBank");
            return (Criteria) this;
        }

        public Criteria andRepaymentAccountsBankNotIn(List<String> values) {
            addCriterion("repayment_accounts_bank not in", values, "repaymentAccountsBank");
            return (Criteria) this;
        }

        public Criteria andRepaymentAccountsBankBetween(String value1, String value2) {
            addCriterion("repayment_accounts_bank between", value1, value2, "repaymentAccountsBank");
            return (Criteria) this;
        }

        public Criteria andRepaymentAccountsBankNotBetween(String value1, String value2) {
            addCriterion("repayment_accounts_bank not between", value1, value2, "repaymentAccountsBank");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(String value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(String value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(String value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(String value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(String value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(String value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLike(String value) {
            addCriterion("area like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotLike(String value) {
            addCriterion("area not like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<String> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<String> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(String value1, String value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(String value1, String value2) {
            addCriterion("area not between", value1, value2, "area");
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
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