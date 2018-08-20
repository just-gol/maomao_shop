package com.maomao.ssm.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodsExampleCustom {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	protected Integer pageNo = 1;

	protected Integer startRow;

	protected Integer pageSize = 10;

	protected String fields;

	public GoodsExampleCustom() {
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
		this.pageNo = pageNo;
		this.startRow = (pageNo - 1) * this.pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		this.startRow = (pageNo - 1) * this.pageSize;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setFields(String fields) {
		this.fields = fields;
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

		public Criteria andPricePurchaseIsNull() {
			addCriterion("price_purchase is null");
			return (Criteria) this;
		}

		public Criteria andPricePurchaseIsNotNull() {
			addCriterion("price_purchase is not null");
			return (Criteria) this;
		}

		public Criteria andPricePurchaseEqualTo(Long value) {
			addCriterion("price_purchase =", value, "pricePurchase");
			return (Criteria) this;
		}

		public Criteria andPricePurchaseNotEqualTo(Long value) {
			addCriterion("price_purchase <>", value, "pricePurchase");
			return (Criteria) this;
		}

		public Criteria andPricePurchaseGreaterThan(Long value) {
			addCriterion("price_purchase >", value, "pricePurchase");
			return (Criteria) this;
		}

		public Criteria andPricePurchaseGreaterThanOrEqualTo(Long value) {
			addCriterion("price_purchase >=", value, "pricePurchase");
			return (Criteria) this;
		}

		public Criteria andPricePurchaseLessThan(Long value) {
			addCriterion("price_purchase <", value, "pricePurchase");
			return (Criteria) this;
		}

		public Criteria andPricePurchaseLessThanOrEqualTo(Long value) {
			addCriterion("price_purchase <=", value, "pricePurchase");
			return (Criteria) this;
		}

		public Criteria andPricePurchaseIn(List<Long> values) {
			addCriterion("price_purchase in", values, "pricePurchase");
			return (Criteria) this;
		}

		public Criteria andPricePurchaseNotIn(List<Long> values) {
			addCriterion("price_purchase not in", values, "pricePurchase");
			return (Criteria) this;
		}

		public Criteria andPricePurchaseBetween(Long value1, Long value2) {
			addCriterion("price_purchase between", value1, value2, "pricePurchase");
			return (Criteria) this;
		}

		public Criteria andPricePurchaseNotBetween(Long value1, Long value2) {
			addCriterion("price_purchase not between", value1, value2, "pricePurchase");
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

		public Criteria andStockShamIsNull() {
			addCriterion("stock_sham is null");
			return (Criteria) this;
		}

		public Criteria andStockShamIsNotNull() {
			addCriterion("stock_sham is not null");
			return (Criteria) this;
		}

		public Criteria andStockShamEqualTo(Integer value) {
			addCriterion("stock_sham =", value, "stockSham");
			return (Criteria) this;
		}

		public Criteria andStockShamNotEqualTo(Integer value) {
			addCriterion("stock_sham <>", value, "stockSham");
			return (Criteria) this;
		}

		public Criteria andStockShamGreaterThan(Integer value) {
			addCriterion("stock_sham >", value, "stockSham");
			return (Criteria) this;
		}

		public Criteria andStockShamGreaterThanOrEqualTo(Integer value) {
			addCriterion("stock_sham >=", value, "stockSham");
			return (Criteria) this;
		}

		public Criteria andStockShamLessThan(Integer value) {
			addCriterion("stock_sham <", value, "stockSham");
			return (Criteria) this;
		}

		public Criteria andStockShamLessThanOrEqualTo(Integer value) {
			addCriterion("stock_sham <=", value, "stockSham");
			return (Criteria) this;
		}

		public Criteria andStockShamIn(List<Integer> values) {
			addCriterion("stock_sham in", values, "stockSham");
			return (Criteria) this;
		}

		public Criteria andStockShamNotIn(List<Integer> values) {
			addCriterion("stock_sham not in", values, "stockSham");
			return (Criteria) this;
		}

		public Criteria andStockShamBetween(Integer value1, Integer value2) {
			addCriterion("stock_sham between", value1, value2, "stockSham");
			return (Criteria) this;
		}

		public Criteria andStockShamNotBetween(Integer value1, Integer value2) {
			addCriterion("stock_sham not between", value1, value2, "stockSham");
			return (Criteria) this;
		}

		public Criteria andSalesIsNull() {
			addCriterion("sales is null");
			return (Criteria) this;
		}

		public Criteria andSalesIsNotNull() {
			addCriterion("sales is not null");
			return (Criteria) this;
		}

		public Criteria andSalesEqualTo(Integer value) {
			addCriterion("sales =", value, "sales");
			return (Criteria) this;
		}

		public Criteria andSalesNotEqualTo(Integer value) {
			addCriterion("sales <>", value, "sales");
			return (Criteria) this;
		}

		public Criteria andSalesGreaterThan(Integer value) {
			addCriterion("sales >", value, "sales");
			return (Criteria) this;
		}

		public Criteria andSalesGreaterThanOrEqualTo(Integer value) {
			addCriterion("sales >=", value, "sales");
			return (Criteria) this;
		}

		public Criteria andSalesLessThan(Integer value) {
			addCriterion("sales <", value, "sales");
			return (Criteria) this;
		}

		public Criteria andSalesLessThanOrEqualTo(Integer value) {
			addCriterion("sales <=", value, "sales");
			return (Criteria) this;
		}

		public Criteria andSalesIn(List<Integer> values) {
			addCriterion("sales in", values, "sales");
			return (Criteria) this;
		}

		public Criteria andSalesNotIn(List<Integer> values) {
			addCriterion("sales not in", values, "sales");
			return (Criteria) this;
		}

		public Criteria andSalesBetween(Integer value1, Integer value2) {
			addCriterion("sales between", value1, value2, "sales");
			return (Criteria) this;
		}

		public Criteria andSalesNotBetween(Integer value1, Integer value2) {
			addCriterion("sales not between", value1, value2, "sales");
			return (Criteria) this;
		}

		public Criteria andSalesShamIsNull() {
			addCriterion("sales_sham is null");
			return (Criteria) this;
		}

		public Criteria andSalesShamIsNotNull() {
			addCriterion("sales_sham is not null");
			return (Criteria) this;
		}

		public Criteria andSalesShamEqualTo(Integer value) {
			addCriterion("sales_sham =", value, "salesSham");
			return (Criteria) this;
		}

		public Criteria andSalesShamNotEqualTo(Integer value) {
			addCriterion("sales_sham <>", value, "salesSham");
			return (Criteria) this;
		}

		public Criteria andSalesShamGreaterThan(Integer value) {
			addCriterion("sales_sham >", value, "salesSham");
			return (Criteria) this;
		}

		public Criteria andSalesShamGreaterThanOrEqualTo(Integer value) {
			addCriterion("sales_sham >=", value, "salesSham");
			return (Criteria) this;
		}

		public Criteria andSalesShamLessThan(Integer value) {
			addCriterion("sales_sham <", value, "salesSham");
			return (Criteria) this;
		}

		public Criteria andSalesShamLessThanOrEqualTo(Integer value) {
			addCriterion("sales_sham <=", value, "salesSham");
			return (Criteria) this;
		}

		public Criteria andSalesShamIn(List<Integer> values) {
			addCriterion("sales_sham in", values, "salesSham");
			return (Criteria) this;
		}

		public Criteria andSalesShamNotIn(List<Integer> values) {
			addCriterion("sales_sham not in", values, "salesSham");
			return (Criteria) this;
		}

		public Criteria andSalesShamBetween(Integer value1, Integer value2) {
			addCriterion("sales_sham between", value1, value2, "salesSham");
			return (Criteria) this;
		}

		public Criteria andSalesShamNotBetween(Integer value1, Integer value2) {
			addCriterion("sales_sham not between", value1, value2, "salesSham");
			return (Criteria) this;
		}

		public Criteria andRewordIsNull() {
			addCriterion("reword is null");
			return (Criteria) this;
		}

		public Criteria andRewordIsNotNull() {
			addCriterion("reword is not null");
			return (Criteria) this;
		}

		public Criteria andRewordEqualTo(Long value) {
			addCriterion("reword =", value, "reword");
			return (Criteria) this;
		}

		public Criteria andRewordNotEqualTo(Long value) {
			addCriterion("reword <>", value, "reword");
			return (Criteria) this;
		}

		public Criteria andRewordGreaterThan(Long value) {
			addCriterion("reword >", value, "reword");
			return (Criteria) this;
		}

		public Criteria andRewordGreaterThanOrEqualTo(Long value) {
			addCriterion("reword >=", value, "reword");
			return (Criteria) this;
		}

		public Criteria andRewordLessThan(Long value) {
			addCriterion("reword <", value, "reword");
			return (Criteria) this;
		}

		public Criteria andRewordLessThanOrEqualTo(Long value) {
			addCriterion("reword <=", value, "reword");
			return (Criteria) this;
		}

		public Criteria andRewordIn(List<Long> values) {
			addCriterion("reword in", values, "reword");
			return (Criteria) this;
		}

		public Criteria andRewordNotIn(List<Long> values) {
			addCriterion("reword not in", values, "reword");
			return (Criteria) this;
		}

		public Criteria andRewordBetween(Long value1, Long value2) {
			addCriterion("reword between", value1, value2, "reword");
			return (Criteria) this;
		}

		public Criteria andRewordNotBetween(Long value1, Long value2) {
			addCriterion("reword not between", value1, value2, "reword");
			return (Criteria) this;
		}

		public Criteria andRebateIsNull() {
			addCriterion("rebate is null");
			return (Criteria) this;
		}

		public Criteria andRebateIsNotNull() {
			addCriterion("rebate is not null");
			return (Criteria) this;
		}

		public Criteria andRebateEqualTo(Long value) {
			addCriterion("rebate =", value, "rebate");
			return (Criteria) this;
		}

		public Criteria andRebateNotEqualTo(Long value) {
			addCriterion("rebate <>", value, "rebate");
			return (Criteria) this;
		}

		public Criteria andRebateGreaterThan(Long value) {
			addCriterion("rebate >", value, "rebate");
			return (Criteria) this;
		}

		public Criteria andRebateGreaterThanOrEqualTo(Long value) {
			addCriterion("rebate >=", value, "rebate");
			return (Criteria) this;
		}

		public Criteria andRebateLessThan(Long value) {
			addCriterion("rebate <", value, "rebate");
			return (Criteria) this;
		}

		public Criteria andRebateLessThanOrEqualTo(Long value) {
			addCriterion("rebate <=", value, "rebate");
			return (Criteria) this;
		}

		public Criteria andRebateIn(List<Long> values) {
			addCriterion("rebate in", values, "rebate");
			return (Criteria) this;
		}

		public Criteria andRebateNotIn(List<Long> values) {
			addCriterion("rebate not in", values, "rebate");
			return (Criteria) this;
		}

		public Criteria andRebateBetween(Long value1, Long value2) {
			addCriterion("rebate between", value1, value2, "rebate");
			return (Criteria) this;
		}

		public Criteria andRebateNotBetween(Long value1, Long value2) {
			addCriterion("rebate not between", value1, value2, "rebate");
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

		public Criteria andCrateTimeIsNull() {
			addCriterion("crate_time is null");
			return (Criteria) this;
		}

		public Criteria andCrateTimeIsNotNull() {
			addCriterion("crate_time is not null");
			return (Criteria) this;
		}

		public Criteria andCrateTimeEqualTo(Date value) {
			addCriterion("crate_time =", value, "crateTime");
			return (Criteria) this;
		}

		public Criteria andCrateTimeNotEqualTo(Date value) {
			addCriterion("crate_time <>", value, "crateTime");
			return (Criteria) this;
		}

		public Criteria andCrateTimeGreaterThan(Date value) {
			addCriterion("crate_time >", value, "crateTime");
			return (Criteria) this;
		}

		public Criteria andCrateTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("crate_time >=", value, "crateTime");
			return (Criteria) this;
		}

		public Criteria andCrateTimeLessThan(Date value) {
			addCriterion("crate_time <", value, "crateTime");
			return (Criteria) this;
		}

		public Criteria andCrateTimeLessThanOrEqualTo(Date value) {
			addCriterion("crate_time <=", value, "crateTime");
			return (Criteria) this;
		}

		public Criteria andCrateTimeIn(List<Date> values) {
			addCriterion("crate_time in", values, "crateTime");
			return (Criteria) this;
		}

		public Criteria andCrateTimeNotIn(List<Date> values) {
			addCriterion("crate_time not in", values, "crateTime");
			return (Criteria) this;
		}

		public Criteria andCrateTimeBetween(Date value1, Date value2) {
			addCriterion("crate_time between", value1, value2, "crateTime");
			return (Criteria) this;
		}

		public Criteria andCrateTimeNotBetween(Date value1, Date value2) {
			addCriterion("crate_time not between", value1, value2, "crateTime");
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

		public Criteria andTypeIsNull() {
			addCriterion("type is null");
			return (Criteria) this;
		}

		public Criteria andTypeIsNotNull() {
			addCriterion("type is not null");
			return (Criteria) this;
		}

		public Criteria andTypeEqualTo(Byte value) {
			addCriterion("type =", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotEqualTo(Byte value) {
			addCriterion("type <>", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThan(Byte value) {
			addCriterion("type >", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
			addCriterion("type >=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThan(Byte value) {
			addCriterion("type <", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThanOrEqualTo(Byte value) {
			addCriterion("type <=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeIn(List<Byte> values) {
			addCriterion("type in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotIn(List<Byte> values) {
			addCriterion("type not in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeBetween(Byte value1, Byte value2) {
			addCriterion("type between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotBetween(Byte value1, Byte value2) {
			addCriterion("type not between", value1, value2, "type");
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

		public Criteria andLimitTimeIsNull() {
			addCriterion("limit_time is null");
			return (Criteria) this;
		}

		public Criteria andLimitTimeIsNotNull() {
			addCriterion("limit_time is not null");
			return (Criteria) this;
		}

		public Criteria andLimitTimeEqualTo(Date value) {
			addCriterion("limit_time =", value, "limitTime");
			return (Criteria) this;
		}

		public Criteria andLimitTimeNotEqualTo(Date value) {
			addCriterion("limit_time <>", value, "limitTime");
			return (Criteria) this;
		}

		public Criteria andLimitTimeGreaterThan(Date value) {
			addCriterion("limit_time >", value, "limitTime");
			return (Criteria) this;
		}

		public Criteria andLimitTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("limit_time >=", value, "limitTime");
			return (Criteria) this;
		}

		public Criteria andLimitTimeLessThan(Date value) {
			addCriterion("limit_time <", value, "limitTime");
			return (Criteria) this;
		}

		public Criteria andLimitTimeLessThanOrEqualTo(Date value) {
			addCriterion("limit_time <=", value, "limitTime");
			return (Criteria) this;
		}

		public Criteria andLimitTimeIn(List<Date> values) {
			addCriterion("limit_time in", values, "limitTime");
			return (Criteria) this;
		}

		public Criteria andLimitTimeNotIn(List<Date> values) {
			addCriterion("limit_time not in", values, "limitTime");
			return (Criteria) this;
		}

		public Criteria andLimitTimeBetween(Date value1, Date value2) {
			addCriterion("limit_time between", value1, value2, "limitTime");
			return (Criteria) this;
		}

		public Criteria andLimitTimeNotBetween(Date value1, Date value2) {
			addCriterion("limit_time not between", value1, value2, "limitTime");
			return (Criteria) this;
		}

		public Criteria andMinPriceIsNull() {
			addCriterion("min_price is null");
			return (Criteria) this;
		}

		public Criteria andMinPriceIsNotNull() {
			addCriterion("min_price is not null");
			return (Criteria) this;
		}

		public Criteria andMinPriceEqualTo(Long value) {
			addCriterion("min_price =", value, "minPrice");
			return (Criteria) this;
		}

		public Criteria andMinPriceNotEqualTo(Long value) {
			addCriterion("min_price <>", value, "minPrice");
			return (Criteria) this;
		}

		public Criteria andMinPriceGreaterThan(Long value) {
			addCriterion("min_price >", value, "minPrice");
			return (Criteria) this;
		}

		public Criteria andMinPriceGreaterThanOrEqualTo(Long value) {
			addCriterion("min_price >=", value, "minPrice");
			return (Criteria) this;
		}

		public Criteria andMinPriceLessThan(Long value) {
			addCriterion("min_price <", value, "minPrice");
			return (Criteria) this;
		}

		public Criteria andMinPriceLessThanOrEqualTo(Long value) {
			addCriterion("min_price <=", value, "minPrice");
			return (Criteria) this;
		}

		public Criteria andMinPriceIn(List<Long> values) {
			addCriterion("min_price in", values, "minPrice");
			return (Criteria) this;
		}

		public Criteria andMinPriceNotIn(List<Long> values) {
			addCriterion("min_price not in", values, "minPrice");
			return (Criteria) this;
		}

		public Criteria andMinPriceBetween(Long value1, Long value2) {
			addCriterion("min_price between", value1, value2, "minPrice");
			return (Criteria) this;
		}

		public Criteria andMinPriceNotBetween(Long value1, Long value2) {
			addCriterion("min_price not between", value1, value2, "minPrice");
			return (Criteria) this;
		}

		public Criteria andMaxPriceIsNull() {
			addCriterion("max_price is null");
			return (Criteria) this;
		}

		public Criteria andMaxPriceIsNotNull() {
			addCriterion("max_price is not null");
			return (Criteria) this;
		}

		public Criteria andMaxPriceEqualTo(Long value) {
			addCriterion("max_price =", value, "maxPrice");
			return (Criteria) this;
		}

		public Criteria andMaxPriceNotEqualTo(Long value) {
			addCriterion("max_price <>", value, "maxPrice");
			return (Criteria) this;
		}

		public Criteria andMaxPriceGreaterThan(Long value) {
			addCriterion("max_price >", value, "maxPrice");
			return (Criteria) this;
		}

		public Criteria andMaxPriceGreaterThanOrEqualTo(Long value) {
			addCriterion("max_price >=", value, "maxPrice");
			return (Criteria) this;
		}

		public Criteria andMaxPriceLessThan(Long value) {
			addCriterion("max_price <", value, "maxPrice");
			return (Criteria) this;
		}

		public Criteria andMaxPriceLessThanOrEqualTo(Long value) {
			addCriterion("max_price <=", value, "maxPrice");
			return (Criteria) this;
		}

		public Criteria andMaxPriceIn(List<Long> values) {
			addCriterion("max_price in", values, "maxPrice");
			return (Criteria) this;
		}

		public Criteria andMaxPriceNotIn(List<Long> values) {
			addCriterion("max_price not in", values, "maxPrice");
			return (Criteria) this;
		}

		public Criteria andMaxPriceBetween(Long value1, Long value2) {
			addCriterion("max_price between", value1, value2, "maxPrice");
			return (Criteria) this;
		}

		public Criteria andMaxPriceNotBetween(Long value1, Long value2) {
			addCriterion("max_price not between", value1, value2, "maxPrice");
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

		public Criteria andSortIsNull() {
			addCriterion("sort is null");
			return (Criteria) this;
		}

		public Criteria andSortIsNotNull() {
			addCriterion("sort is not null");
			return (Criteria) this;
		}

		public Criteria andSortEqualTo(Integer value) {
			addCriterion("sort =", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortNotEqualTo(Integer value) {
			addCriterion("sort <>", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortGreaterThan(Integer value) {
			addCriterion("sort >", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortGreaterThanOrEqualTo(Integer value) {
			addCriterion("sort >=", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortLessThan(Integer value) {
			addCriterion("sort <", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortLessThanOrEqualTo(Integer value) {
			addCriterion("sort <=", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortIn(List<Integer> values) {
			addCriterion("sort in", values, "sort");
			return (Criteria) this;
		}

		public Criteria andSortNotIn(List<Integer> values) {
			addCriterion("sort not in", values, "sort");
			return (Criteria) this;
		}

		public Criteria andSortBetween(Integer value1, Integer value2) {
			addCriterion("sort between", value1, value2, "sort");
			return (Criteria) this;
		}

		public Criteria andSortNotBetween(Integer value1, Integer value2) {
			addCriterion("sort not between", value1, value2, "sort");
			return (Criteria) this;
		}

		public Criteria andMyCriterion(String sql, String value, String field) {
			addCriterion(sql, value, field);
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