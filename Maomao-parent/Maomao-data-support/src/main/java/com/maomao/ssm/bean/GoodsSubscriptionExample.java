package com.maomao.ssm.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodsSubscriptionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer pageNo = 1;

    protected Integer startRow;

    protected Integer pageSize = 10;

    protected String fields;

    public GoodsSubscriptionExample() {
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

        public Criteria andItemIdIsNull() {
            addCriterion("item_id is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("item_id is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(Long value) {
            addCriterion("item_id =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(Long value) {
            addCriterion("item_id <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(Long value) {
            addCriterion("item_id >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(Long value) {
            addCriterion("item_id >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(Long value) {
            addCriterion("item_id <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(Long value) {
            addCriterion("item_id <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<Long> values) {
            addCriterion("item_id in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<Long> values) {
            addCriterion("item_id not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(Long value1, Long value2) {
            addCriterion("item_id between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(Long value1, Long value2) {
            addCriterion("item_id not between", value1, value2, "itemId");
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

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Integer value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Integer value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Integer value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Integer value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Integer> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Integer> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
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

        public Criteria andBrandIdIsNull() {
            addCriterion("brand_id is null");
            return (Criteria) this;
        }

        public Criteria andBrandIdIsNotNull() {
            addCriterion("brand_id is not null");
            return (Criteria) this;
        }

        public Criteria andBrandIdEqualTo(Integer value) {
            addCriterion("brand_id =", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotEqualTo(Integer value) {
            addCriterion("brand_id <>", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThan(Integer value) {
            addCriterion("brand_id >", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("brand_id >=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThan(Integer value) {
            addCriterion("brand_id <", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThanOrEqualTo(Integer value) {
            addCriterion("brand_id <=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdIn(List<Integer> values) {
            addCriterion("brand_id in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotIn(List<Integer> values) {
            addCriterion("brand_id not in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdBetween(Integer value1, Integer value2) {
            addCriterion("brand_id between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotBetween(Integer value1, Integer value2) {
            addCriterion("brand_id not between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andServiceIdIsNull() {
            addCriterion("service_id is null");
            return (Criteria) this;
        }

        public Criteria andServiceIdIsNotNull() {
            addCriterion("service_id is not null");
            return (Criteria) this;
        }

        public Criteria andServiceIdEqualTo(String value) {
            addCriterion("service_id =", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotEqualTo(String value) {
            addCriterion("service_id <>", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdGreaterThan(String value) {
            addCriterion("service_id >", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdGreaterThanOrEqualTo(String value) {
            addCriterion("service_id >=", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLessThan(String value) {
            addCriterion("service_id <", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLessThanOrEqualTo(String value) {
            addCriterion("service_id <=", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLike(String value) {
            addCriterion("service_id like", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotLike(String value) {
            addCriterion("service_id not like", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdIn(List<String> values) {
            addCriterion("service_id in", values, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotIn(List<String> values) {
            addCriterion("service_id not in", values, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdBetween(String value1, String value2) {
            addCriterion("service_id between", value1, value2, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotBetween(String value1, String value2) {
            addCriterion("service_id not between", value1, value2, "serviceId");
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

        public Criteria andCheckAdminIsNull() {
            addCriterion("check_admin is null");
            return (Criteria) this;
        }

        public Criteria andCheckAdminIsNotNull() {
            addCriterion("check_admin is not null");
            return (Criteria) this;
        }

        public Criteria andCheckAdminEqualTo(Integer value) {
            addCriterion("check_admin =", value, "checkAdmin");
            return (Criteria) this;
        }

        public Criteria andCheckAdminNotEqualTo(Integer value) {
            addCriterion("check_admin <>", value, "checkAdmin");
            return (Criteria) this;
        }

        public Criteria andCheckAdminGreaterThan(Integer value) {
            addCriterion("check_admin >", value, "checkAdmin");
            return (Criteria) this;
        }

        public Criteria andCheckAdminGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_admin >=", value, "checkAdmin");
            return (Criteria) this;
        }

        public Criteria andCheckAdminLessThan(Integer value) {
            addCriterion("check_admin <", value, "checkAdmin");
            return (Criteria) this;
        }

        public Criteria andCheckAdminLessThanOrEqualTo(Integer value) {
            addCriterion("check_admin <=", value, "checkAdmin");
            return (Criteria) this;
        }

        public Criteria andCheckAdminIn(List<Integer> values) {
            addCriterion("check_admin in", values, "checkAdmin");
            return (Criteria) this;
        }

        public Criteria andCheckAdminNotIn(List<Integer> values) {
            addCriterion("check_admin not in", values, "checkAdmin");
            return (Criteria) this;
        }

        public Criteria andCheckAdminBetween(Integer value1, Integer value2) {
            addCriterion("check_admin between", value1, value2, "checkAdmin");
            return (Criteria) this;
        }

        public Criteria andCheckAdminNotBetween(Integer value1, Integer value2) {
            addCriterion("check_admin not between", value1, value2, "checkAdmin");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNull() {
            addCriterion("check_time is null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNotNull() {
            addCriterion("check_time is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeEqualTo(Date value) {
            addCriterion("check_time =", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotEqualTo(Date value) {
            addCriterion("check_time <>", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThan(Date value) {
            addCriterion("check_time >", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("check_time >=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThan(Date value) {
            addCriterion("check_time <", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThanOrEqualTo(Date value) {
            addCriterion("check_time <=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIn(List<Date> values) {
            addCriterion("check_time in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotIn(List<Date> values) {
            addCriterion("check_time not in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeBetween(Date value1, Date value2) {
            addCriterion("check_time between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotBetween(Date value1, Date value2) {
            addCriterion("check_time not between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckReasonIsNull() {
            addCriterion("check_reason is null");
            return (Criteria) this;
        }

        public Criteria andCheckReasonIsNotNull() {
            addCriterion("check_reason is not null");
            return (Criteria) this;
        }

        public Criteria andCheckReasonEqualTo(String value) {
            addCriterion("check_reason =", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonNotEqualTo(String value) {
            addCriterion("check_reason <>", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonGreaterThan(String value) {
            addCriterion("check_reason >", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonGreaterThanOrEqualTo(String value) {
            addCriterion("check_reason >=", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonLessThan(String value) {
            addCriterion("check_reason <", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonLessThanOrEqualTo(String value) {
            addCriterion("check_reason <=", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonLike(String value) {
            addCriterion("check_reason like", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonNotLike(String value) {
            addCriterion("check_reason not like", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonIn(List<String> values) {
            addCriterion("check_reason in", values, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonNotIn(List<String> values) {
            addCriterion("check_reason not in", values, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonBetween(String value1, String value2) {
            addCriterion("check_reason between", value1, value2, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonNotBetween(String value1, String value2) {
            addCriterion("check_reason not between", value1, value2, "checkReason");
            return (Criteria) this;
        }

        public Criteria andUploadAdminIsNull() {
            addCriterion("upload_admin is null");
            return (Criteria) this;
        }

        public Criteria andUploadAdminIsNotNull() {
            addCriterion("upload_admin is not null");
            return (Criteria) this;
        }

        public Criteria andUploadAdminEqualTo(Integer value) {
            addCriterion("upload_admin =", value, "uploadAdmin");
            return (Criteria) this;
        }

        public Criteria andUploadAdminNotEqualTo(Integer value) {
            addCriterion("upload_admin <>", value, "uploadAdmin");
            return (Criteria) this;
        }

        public Criteria andUploadAdminGreaterThan(Integer value) {
            addCriterion("upload_admin >", value, "uploadAdmin");
            return (Criteria) this;
        }

        public Criteria andUploadAdminGreaterThanOrEqualTo(Integer value) {
            addCriterion("upload_admin >=", value, "uploadAdmin");
            return (Criteria) this;
        }

        public Criteria andUploadAdminLessThan(Integer value) {
            addCriterion("upload_admin <", value, "uploadAdmin");
            return (Criteria) this;
        }

        public Criteria andUploadAdminLessThanOrEqualTo(Integer value) {
            addCriterion("upload_admin <=", value, "uploadAdmin");
            return (Criteria) this;
        }

        public Criteria andUploadAdminIn(List<Integer> values) {
            addCriterion("upload_admin in", values, "uploadAdmin");
            return (Criteria) this;
        }

        public Criteria andUploadAdminNotIn(List<Integer> values) {
            addCriterion("upload_admin not in", values, "uploadAdmin");
            return (Criteria) this;
        }

        public Criteria andUploadAdminBetween(Integer value1, Integer value2) {
            addCriterion("upload_admin between", value1, value2, "uploadAdmin");
            return (Criteria) this;
        }

        public Criteria andUploadAdminNotBetween(Integer value1, Integer value2) {
            addCriterion("upload_admin not between", value1, value2, "uploadAdmin");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStatusIsNull() {
            addCriterion("subscription_status is null");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStatusIsNotNull() {
            addCriterion("subscription_status is not null");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStatusEqualTo(Byte value) {
            addCriterion("subscription_status =", value, "subscriptionStatus");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStatusNotEqualTo(Byte value) {
            addCriterion("subscription_status <>", value, "subscriptionStatus");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStatusGreaterThan(Byte value) {
            addCriterion("subscription_status >", value, "subscriptionStatus");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("subscription_status >=", value, "subscriptionStatus");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStatusLessThan(Byte value) {
            addCriterion("subscription_status <", value, "subscriptionStatus");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStatusLessThanOrEqualTo(Byte value) {
            addCriterion("subscription_status <=", value, "subscriptionStatus");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStatusIn(List<Byte> values) {
            addCriterion("subscription_status in", values, "subscriptionStatus");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStatusNotIn(List<Byte> values) {
            addCriterion("subscription_status not in", values, "subscriptionStatus");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStatusBetween(Byte value1, Byte value2) {
            addCriterion("subscription_status between", value1, value2, "subscriptionStatus");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("subscription_status not between", value1, value2, "subscriptionStatus");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStartTimeIsNull() {
            addCriterion("subscription_start_time is null");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStartTimeIsNotNull() {
            addCriterion("subscription_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStartTimeEqualTo(Date value) {
            addCriterion("subscription_start_time =", value, "subscriptionStartTime");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStartTimeNotEqualTo(Date value) {
            addCriterion("subscription_start_time <>", value, "subscriptionStartTime");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStartTimeGreaterThan(Date value) {
            addCriterion("subscription_start_time >", value, "subscriptionStartTime");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("subscription_start_time >=", value, "subscriptionStartTime");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStartTimeLessThan(Date value) {
            addCriterion("subscription_start_time <", value, "subscriptionStartTime");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("subscription_start_time <=", value, "subscriptionStartTime");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStartTimeIn(List<Date> values) {
            addCriterion("subscription_start_time in", values, "subscriptionStartTime");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStartTimeNotIn(List<Date> values) {
            addCriterion("subscription_start_time not in", values, "subscriptionStartTime");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStartTimeBetween(Date value1, Date value2) {
            addCriterion("subscription_start_time between", value1, value2, "subscriptionStartTime");
            return (Criteria) this;
        }

        public Criteria andSubscriptionStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("subscription_start_time not between", value1, value2, "subscriptionStartTime");
            return (Criteria) this;
        }

        public Criteria andSubscriptionEndTimeIsNull() {
            addCriterion("subscription_end_time is null");
            return (Criteria) this;
        }

        public Criteria andSubscriptionEndTimeIsNotNull() {
            addCriterion("subscription_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andSubscriptionEndTimeEqualTo(Date value) {
            addCriterion("subscription_end_time =", value, "subscriptionEndTime");
            return (Criteria) this;
        }

        public Criteria andSubscriptionEndTimeNotEqualTo(Date value) {
            addCriterion("subscription_end_time <>", value, "subscriptionEndTime");
            return (Criteria) this;
        }

        public Criteria andSubscriptionEndTimeGreaterThan(Date value) {
            addCriterion("subscription_end_time >", value, "subscriptionEndTime");
            return (Criteria) this;
        }

        public Criteria andSubscriptionEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("subscription_end_time >=", value, "subscriptionEndTime");
            return (Criteria) this;
        }

        public Criteria andSubscriptionEndTimeLessThan(Date value) {
            addCriterion("subscription_end_time <", value, "subscriptionEndTime");
            return (Criteria) this;
        }

        public Criteria andSubscriptionEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("subscription_end_time <=", value, "subscriptionEndTime");
            return (Criteria) this;
        }

        public Criteria andSubscriptionEndTimeIn(List<Date> values) {
            addCriterion("subscription_end_time in", values, "subscriptionEndTime");
            return (Criteria) this;
        }

        public Criteria andSubscriptionEndTimeNotIn(List<Date> values) {
            addCriterion("subscription_end_time not in", values, "subscriptionEndTime");
            return (Criteria) this;
        }

        public Criteria andSubscriptionEndTimeBetween(Date value1, Date value2) {
            addCriterion("subscription_end_time between", value1, value2, "subscriptionEndTime");
            return (Criteria) this;
        }

        public Criteria andSubscriptionEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("subscription_end_time not between", value1, value2, "subscriptionEndTime");
            return (Criteria) this;
        }

        public Criteria andPriceSalesIsNull() {
            addCriterion("price_sales is null");
            return (Criteria) this;
        }

        public Criteria andPriceSalesIsNotNull() {
            addCriterion("price_sales is not null");
            return (Criteria) this;
        }

        public Criteria andPriceSalesEqualTo(Long value) {
            addCriterion("price_sales =", value, "priceSales");
            return (Criteria) this;
        }

        public Criteria andPriceSalesNotEqualTo(Long value) {
            addCriterion("price_sales <>", value, "priceSales");
            return (Criteria) this;
        }

        public Criteria andPriceSalesGreaterThan(Long value) {
            addCriterion("price_sales >", value, "priceSales");
            return (Criteria) this;
        }

        public Criteria andPriceSalesGreaterThanOrEqualTo(Long value) {
            addCriterion("price_sales >=", value, "priceSales");
            return (Criteria) this;
        }

        public Criteria andPriceSalesLessThan(Long value) {
            addCriterion("price_sales <", value, "priceSales");
            return (Criteria) this;
        }

        public Criteria andPriceSalesLessThanOrEqualTo(Long value) {
            addCriterion("price_sales <=", value, "priceSales");
            return (Criteria) this;
        }

        public Criteria andPriceSalesIn(List<Long> values) {
            addCriterion("price_sales in", values, "priceSales");
            return (Criteria) this;
        }

        public Criteria andPriceSalesNotIn(List<Long> values) {
            addCriterion("price_sales not in", values, "priceSales");
            return (Criteria) this;
        }

        public Criteria andPriceSalesBetween(Long value1, Long value2) {
            addCriterion("price_sales between", value1, value2, "priceSales");
            return (Criteria) this;
        }

        public Criteria andPriceSalesNotBetween(Long value1, Long value2) {
            addCriterion("price_sales not between", value1, value2, "priceSales");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionIsNull() {
            addCriterion("price_subscription is null");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionIsNotNull() {
            addCriterion("price_subscription is not null");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionEqualTo(Long value) {
            addCriterion("price_subscription =", value, "priceSubscription");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionNotEqualTo(Long value) {
            addCriterion("price_subscription <>", value, "priceSubscription");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionGreaterThan(Long value) {
            addCriterion("price_subscription >", value, "priceSubscription");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionGreaterThanOrEqualTo(Long value) {
            addCriterion("price_subscription >=", value, "priceSubscription");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionLessThan(Long value) {
            addCriterion("price_subscription <", value, "priceSubscription");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionLessThanOrEqualTo(Long value) {
            addCriterion("price_subscription <=", value, "priceSubscription");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionIn(List<Long> values) {
            addCriterion("price_subscription in", values, "priceSubscription");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionNotIn(List<Long> values) {
            addCriterion("price_subscription not in", values, "priceSubscription");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionBetween(Long value1, Long value2) {
            addCriterion("price_subscription between", value1, value2, "priceSubscription");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionNotBetween(Long value1, Long value2) {
            addCriterion("price_subscription not between", value1, value2, "priceSubscription");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionAvgIsNull() {
            addCriterion("price_subscription_avg is null");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionAvgIsNotNull() {
            addCriterion("price_subscription_avg is not null");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionAvgEqualTo(Long value) {
            addCriterion("price_subscription_avg =", value, "priceSubscriptionAvg");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionAvgNotEqualTo(Long value) {
            addCriterion("price_subscription_avg <>", value, "priceSubscriptionAvg");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionAvgGreaterThan(Long value) {
            addCriterion("price_subscription_avg >", value, "priceSubscriptionAvg");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionAvgGreaterThanOrEqualTo(Long value) {
            addCriterion("price_subscription_avg >=", value, "priceSubscriptionAvg");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionAvgLessThan(Long value) {
            addCriterion("price_subscription_avg <", value, "priceSubscriptionAvg");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionAvgLessThanOrEqualTo(Long value) {
            addCriterion("price_subscription_avg <=", value, "priceSubscriptionAvg");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionAvgIn(List<Long> values) {
            addCriterion("price_subscription_avg in", values, "priceSubscriptionAvg");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionAvgNotIn(List<Long> values) {
            addCriterion("price_subscription_avg not in", values, "priceSubscriptionAvg");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionAvgBetween(Long value1, Long value2) {
            addCriterion("price_subscription_avg between", value1, value2, "priceSubscriptionAvg");
            return (Criteria) this;
        }

        public Criteria andPriceSubscriptionAvgNotBetween(Long value1, Long value2) {
            addCriterion("price_subscription_avg not between", value1, value2, "priceSubscriptionAvg");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionTotalIsNull() {
            addCriterion("num_subscription_total is null");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionTotalIsNotNull() {
            addCriterion("num_subscription_total is not null");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionTotalEqualTo(Integer value) {
            addCriterion("num_subscription_total =", value, "numSubscriptionTotal");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionTotalNotEqualTo(Integer value) {
            addCriterion("num_subscription_total <>", value, "numSubscriptionTotal");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionTotalGreaterThan(Integer value) {
            addCriterion("num_subscription_total >", value, "numSubscriptionTotal");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("num_subscription_total >=", value, "numSubscriptionTotal");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionTotalLessThan(Integer value) {
            addCriterion("num_subscription_total <", value, "numSubscriptionTotal");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionTotalLessThanOrEqualTo(Integer value) {
            addCriterion("num_subscription_total <=", value, "numSubscriptionTotal");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionTotalIn(List<Integer> values) {
            addCriterion("num_subscription_total in", values, "numSubscriptionTotal");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionTotalNotIn(List<Integer> values) {
            addCriterion("num_subscription_total not in", values, "numSubscriptionTotal");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionTotalBetween(Integer value1, Integer value2) {
            addCriterion("num_subscription_total between", value1, value2, "numSubscriptionTotal");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("num_subscription_total not between", value1, value2, "numSubscriptionTotal");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionIsNull() {
            addCriterion("num_subscription is null");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionIsNotNull() {
            addCriterion("num_subscription is not null");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionEqualTo(Integer value) {
            addCriterion("num_subscription =", value, "numSubscription");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionNotEqualTo(Integer value) {
            addCriterion("num_subscription <>", value, "numSubscription");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionGreaterThan(Integer value) {
            addCriterion("num_subscription >", value, "numSubscription");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionGreaterThanOrEqualTo(Integer value) {
            addCriterion("num_subscription >=", value, "numSubscription");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionLessThan(Integer value) {
            addCriterion("num_subscription <", value, "numSubscription");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionLessThanOrEqualTo(Integer value) {
            addCriterion("num_subscription <=", value, "numSubscription");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionIn(List<Integer> values) {
            addCriterion("num_subscription in", values, "numSubscription");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionNotIn(List<Integer> values) {
            addCriterion("num_subscription not in", values, "numSubscription");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionBetween(Integer value1, Integer value2) {
            addCriterion("num_subscription between", value1, value2, "numSubscription");
            return (Criteria) this;
        }

        public Criteria andNumSubscriptionNotBetween(Integer value1, Integer value2) {
            addCriterion("num_subscription not between", value1, value2, "numSubscription");
            return (Criteria) this;
        }

        public Criteria andSalesAwardIsNull() {
            addCriterion("sales_award is null");
            return (Criteria) this;
        }

        public Criteria andSalesAwardIsNotNull() {
            addCriterion("sales_award is not null");
            return (Criteria) this;
        }

        public Criteria andSalesAwardEqualTo(Long value) {
            addCriterion("sales_award =", value, "salesAward");
            return (Criteria) this;
        }

        public Criteria andSalesAwardNotEqualTo(Long value) {
            addCriterion("sales_award <>", value, "salesAward");
            return (Criteria) this;
        }

        public Criteria andSalesAwardGreaterThan(Long value) {
            addCriterion("sales_award >", value, "salesAward");
            return (Criteria) this;
        }

        public Criteria andSalesAwardGreaterThanOrEqualTo(Long value) {
            addCriterion("sales_award >=", value, "salesAward");
            return (Criteria) this;
        }

        public Criteria andSalesAwardLessThan(Long value) {
            addCriterion("sales_award <", value, "salesAward");
            return (Criteria) this;
        }

        public Criteria andSalesAwardLessThanOrEqualTo(Long value) {
            addCriterion("sales_award <=", value, "salesAward");
            return (Criteria) this;
        }

        public Criteria andSalesAwardIn(List<Long> values) {
            addCriterion("sales_award in", values, "salesAward");
            return (Criteria) this;
        }

        public Criteria andSalesAwardNotIn(List<Long> values) {
            addCriterion("sales_award not in", values, "salesAward");
            return (Criteria) this;
        }

        public Criteria andSalesAwardBetween(Long value1, Long value2) {
            addCriterion("sales_award between", value1, value2, "salesAward");
            return (Criteria) this;
        }

        public Criteria andSalesAwardNotBetween(Long value1, Long value2) {
            addCriterion("sales_award not between", value1, value2, "salesAward");
            return (Criteria) this;
        }

        public Criteria andBonusAvgIsNull() {
            addCriterion("bonus_avg is null");
            return (Criteria) this;
        }

        public Criteria andBonusAvgIsNotNull() {
            addCriterion("bonus_avg is not null");
            return (Criteria) this;
        }

        public Criteria andBonusAvgEqualTo(Long value) {
            addCriterion("bonus_avg =", value, "bonusAvg");
            return (Criteria) this;
        }

        public Criteria andBonusAvgNotEqualTo(Long value) {
            addCriterion("bonus_avg <>", value, "bonusAvg");
            return (Criteria) this;
        }

        public Criteria andBonusAvgGreaterThan(Long value) {
            addCriterion("bonus_avg >", value, "bonusAvg");
            return (Criteria) this;
        }

        public Criteria andBonusAvgGreaterThanOrEqualTo(Long value) {
            addCriterion("bonus_avg >=", value, "bonusAvg");
            return (Criteria) this;
        }

        public Criteria andBonusAvgLessThan(Long value) {
            addCriterion("bonus_avg <", value, "bonusAvg");
            return (Criteria) this;
        }

        public Criteria andBonusAvgLessThanOrEqualTo(Long value) {
            addCriterion("bonus_avg <=", value, "bonusAvg");
            return (Criteria) this;
        }

        public Criteria andBonusAvgIn(List<Long> values) {
            addCriterion("bonus_avg in", values, "bonusAvg");
            return (Criteria) this;
        }

        public Criteria andBonusAvgNotIn(List<Long> values) {
            addCriterion("bonus_avg not in", values, "bonusAvg");
            return (Criteria) this;
        }

        public Criteria andBonusAvgBetween(Long value1, Long value2) {
            addCriterion("bonus_avg between", value1, value2, "bonusAvg");
            return (Criteria) this;
        }

        public Criteria andBonusAvgNotBetween(Long value1, Long value2) {
            addCriterion("bonus_avg not between", value1, value2, "bonusAvg");
            return (Criteria) this;
        }

        public Criteria andStockIsNull() {
            addCriterion("stock is null");
            return (Criteria) this;
        }

        public Criteria andStockIsNotNull() {
            addCriterion("stock is not null");
            return (Criteria) this;
        }

        public Criteria andStockEqualTo(Integer value) {
            addCriterion("stock =", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotEqualTo(Integer value) {
            addCriterion("stock <>", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockGreaterThan(Integer value) {
            addCriterion("stock >", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockGreaterThanOrEqualTo(Integer value) {
            addCriterion("stock >=", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLessThan(Integer value) {
            addCriterion("stock <", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLessThanOrEqualTo(Integer value) {
            addCriterion("stock <=", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockIn(List<Integer> values) {
            addCriterion("stock in", values, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotIn(List<Integer> values) {
            addCriterion("stock not in", values, "stock");
            return (Criteria) this;
        }

        public Criteria andStockBetween(Integer value1, Integer value2) {
            addCriterion("stock between", value1, value2, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotBetween(Integer value1, Integer value2) {
            addCriterion("stock not between", value1, value2, "stock");
            return (Criteria) this;
        }

        public Criteria andSalesEndTimeIsNull() {
            addCriterion("sales_end_time is null");
            return (Criteria) this;
        }

        public Criteria andSalesEndTimeIsNotNull() {
            addCriterion("sales_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andSalesEndTimeEqualTo(Date value) {
            addCriterion("sales_end_time =", value, "salesEndTime");
            return (Criteria) this;
        }

        public Criteria andSalesEndTimeNotEqualTo(Date value) {
            addCriterion("sales_end_time <>", value, "salesEndTime");
            return (Criteria) this;
        }

        public Criteria andSalesEndTimeGreaterThan(Date value) {
            addCriterion("sales_end_time >", value, "salesEndTime");
            return (Criteria) this;
        }

        public Criteria andSalesEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sales_end_time >=", value, "salesEndTime");
            return (Criteria) this;
        }

        public Criteria andSalesEndTimeLessThan(Date value) {
            addCriterion("sales_end_time <", value, "salesEndTime");
            return (Criteria) this;
        }

        public Criteria andSalesEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("sales_end_time <=", value, "salesEndTime");
            return (Criteria) this;
        }

        public Criteria andSalesEndTimeIn(List<Date> values) {
            addCriterion("sales_end_time in", values, "salesEndTime");
            return (Criteria) this;
        }

        public Criteria andSalesEndTimeNotIn(List<Date> values) {
            addCriterion("sales_end_time not in", values, "salesEndTime");
            return (Criteria) this;
        }

        public Criteria andSalesEndTimeBetween(Date value1, Date value2) {
            addCriterion("sales_end_time between", value1, value2, "salesEndTime");
            return (Criteria) this;
        }

        public Criteria andSalesEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("sales_end_time not between", value1, value2, "salesEndTime");
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

        public Criteria andDepositIsNull() {
            addCriterion("deposit is null");
            return (Criteria) this;
        }

        public Criteria andDepositIsNotNull() {
            addCriterion("deposit is not null");
            return (Criteria) this;
        }

        public Criteria andDepositEqualTo(Long value) {
            addCriterion("deposit =", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotEqualTo(Long value) {
            addCriterion("deposit <>", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThan(Long value) {
            addCriterion("deposit >", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThanOrEqualTo(Long value) {
            addCriterion("deposit >=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThan(Long value) {
            addCriterion("deposit <", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThanOrEqualTo(Long value) {
            addCriterion("deposit <=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositIn(List<Long> values) {
            addCriterion("deposit in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotIn(List<Long> values) {
            addCriterion("deposit not in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositBetween(Long value1, Long value2) {
            addCriterion("deposit between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotBetween(Long value1, Long value2) {
            addCriterion("deposit not between", value1, value2, "deposit");
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

        public Criteria andSellStatusIsNull() {
            addCriterion("sell_status is null");
            return (Criteria) this;
        }

        public Criteria andSellStatusIsNotNull() {
            addCriterion("sell_status is not null");
            return (Criteria) this;
        }

        public Criteria andSellStatusEqualTo(Byte value) {
            addCriterion("sell_status =", value, "sellStatus");
            return (Criteria) this;
        }

        public Criteria andSellStatusNotEqualTo(Byte value) {
            addCriterion("sell_status <>", value, "sellStatus");
            return (Criteria) this;
        }

        public Criteria andSellStatusGreaterThan(Byte value) {
            addCriterion("sell_status >", value, "sellStatus");
            return (Criteria) this;
        }

        public Criteria andSellStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("sell_status >=", value, "sellStatus");
            return (Criteria) this;
        }

        public Criteria andSellStatusLessThan(Byte value) {
            addCriterion("sell_status <", value, "sellStatus");
            return (Criteria) this;
        }

        public Criteria andSellStatusLessThanOrEqualTo(Byte value) {
            addCriterion("sell_status <=", value, "sellStatus");
            return (Criteria) this;
        }

        public Criteria andSellStatusIn(List<Byte> values) {
            addCriterion("sell_status in", values, "sellStatus");
            return (Criteria) this;
        }

        public Criteria andSellStatusNotIn(List<Byte> values) {
            addCriterion("sell_status not in", values, "sellStatus");
            return (Criteria) this;
        }

        public Criteria andSellStatusBetween(Byte value1, Byte value2) {
            addCriterion("sell_status between", value1, value2, "sellStatus");
            return (Criteria) this;
        }

        public Criteria andSellStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("sell_status not between", value1, value2, "sellStatus");
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