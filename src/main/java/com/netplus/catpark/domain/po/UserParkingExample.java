package com.netplus.catpark.domain.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserParkingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserParkingExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
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

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andParkingInfoIdIsNull() {
            addCriterion("parking_info_id is null");
            return (Criteria) this;
        }

        public Criteria andParkingInfoIdIsNotNull() {
            addCriterion("parking_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andParkingInfoIdEqualTo(Long value) {
            addCriterion("parking_info_id =", value, "parkingInfoId");
            return (Criteria) this;
        }

        public Criteria andParkingInfoIdNotEqualTo(Long value) {
            addCriterion("parking_info_id <>", value, "parkingInfoId");
            return (Criteria) this;
        }

        public Criteria andParkingInfoIdGreaterThan(Long value) {
            addCriterion("parking_info_id >", value, "parkingInfoId");
            return (Criteria) this;
        }

        public Criteria andParkingInfoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parking_info_id >=", value, "parkingInfoId");
            return (Criteria) this;
        }

        public Criteria andParkingInfoIdLessThan(Long value) {
            addCriterion("parking_info_id <", value, "parkingInfoId");
            return (Criteria) this;
        }

        public Criteria andParkingInfoIdLessThanOrEqualTo(Long value) {
            addCriterion("parking_info_id <=", value, "parkingInfoId");
            return (Criteria) this;
        }

        public Criteria andParkingInfoIdIn(List<Long> values) {
            addCriterion("parking_info_id in", values, "parkingInfoId");
            return (Criteria) this;
        }

        public Criteria andParkingInfoIdNotIn(List<Long> values) {
            addCriterion("parking_info_id not in", values, "parkingInfoId");
            return (Criteria) this;
        }

        public Criteria andParkingInfoIdBetween(Long value1, Long value2) {
            addCriterion("parking_info_id between", value1, value2, "parkingInfoId");
            return (Criteria) this;
        }

        public Criteria andParkingInfoIdNotBetween(Long value1, Long value2) {
            addCriterion("parking_info_id not between", value1, value2, "parkingInfoId");
            return (Criteria) this;
        }

        public Criteria andPaymentIsNull() {
            addCriterion("payment is null");
            return (Criteria) this;
        }

        public Criteria andPaymentIsNotNull() {
            addCriterion("payment is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentEqualTo(Integer value) {
            addCriterion("payment =", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotEqualTo(Integer value) {
            addCriterion("payment <>", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentGreaterThan(Integer value) {
            addCriterion("payment >", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentGreaterThanOrEqualTo(Integer value) {
            addCriterion("payment >=", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentLessThan(Integer value) {
            addCriterion("payment <", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentLessThanOrEqualTo(Integer value) {
            addCriterion("payment <=", value, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentIn(List<Integer> values) {
            addCriterion("payment in", values, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotIn(List<Integer> values) {
            addCriterion("payment not in", values, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentBetween(Integer value1, Integer value2) {
            addCriterion("payment between", value1, value2, "payment");
            return (Criteria) this;
        }

        public Criteria andPaymentNotBetween(Integer value1, Integer value2) {
            addCriterion("payment not between", value1, value2, "payment");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNull() {
            addCriterion("deleted is null");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNotNull() {
            addCriterion("deleted is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedEqualTo(Boolean value) {
            addCriterion("deleted =", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualTo(Boolean value) {
            addCriterion("deleted <>", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThan(Boolean value) {
            addCriterion("deleted >", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("deleted >=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThan(Boolean value) {
            addCriterion("deleted <", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("deleted <=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedIn(List<Boolean> values) {
            addCriterion("deleted in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotIn(List<Boolean> values) {
            addCriterion("deleted not in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("deleted between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("deleted not between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andBeginBookTimeIsNull() {
            addCriterion("begin_book_time is null");
            return (Criteria) this;
        }

        public Criteria andBeginBookTimeIsNotNull() {
            addCriterion("begin_book_time is not null");
            return (Criteria) this;
        }

        public Criteria andBeginBookTimeEqualTo(Date value) {
            addCriterion("begin_book_time =", value, "beginBookTime");
            return (Criteria) this;
        }

        public Criteria andBeginBookTimeNotEqualTo(Date value) {
            addCriterion("begin_book_time <>", value, "beginBookTime");
            return (Criteria) this;
        }

        public Criteria andBeginBookTimeGreaterThan(Date value) {
            addCriterion("begin_book_time >", value, "beginBookTime");
            return (Criteria) this;
        }

        public Criteria andBeginBookTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("begin_book_time >=", value, "beginBookTime");
            return (Criteria) this;
        }

        public Criteria andBeginBookTimeLessThan(Date value) {
            addCriterion("begin_book_time <", value, "beginBookTime");
            return (Criteria) this;
        }

        public Criteria andBeginBookTimeLessThanOrEqualTo(Date value) {
            addCriterion("begin_book_time <=", value, "beginBookTime");
            return (Criteria) this;
        }

        public Criteria andBeginBookTimeIn(List<Date> values) {
            addCriterion("begin_book_time in", values, "beginBookTime");
            return (Criteria) this;
        }

        public Criteria andBeginBookTimeNotIn(List<Date> values) {
            addCriterion("begin_book_time not in", values, "beginBookTime");
            return (Criteria) this;
        }

        public Criteria andBeginBookTimeBetween(Date value1, Date value2) {
            addCriterion("begin_book_time between", value1, value2, "beginBookTime");
            return (Criteria) this;
        }

        public Criteria andBeginBookTimeNotBetween(Date value1, Date value2) {
            addCriterion("begin_book_time not between", value1, value2, "beginBookTime");
            return (Criteria) this;
        }

        public Criteria andEndBookTimeIsNull() {
            addCriterion("end_book_time is null");
            return (Criteria) this;
        }

        public Criteria andEndBookTimeIsNotNull() {
            addCriterion("end_book_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndBookTimeEqualTo(Date value) {
            addCriterion("end_book_time =", value, "endBookTime");
            return (Criteria) this;
        }

        public Criteria andEndBookTimeNotEqualTo(Date value) {
            addCriterion("end_book_time <>", value, "endBookTime");
            return (Criteria) this;
        }

        public Criteria andEndBookTimeGreaterThan(Date value) {
            addCriterion("end_book_time >", value, "endBookTime");
            return (Criteria) this;
        }

        public Criteria andEndBookTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_book_time >=", value, "endBookTime");
            return (Criteria) this;
        }

        public Criteria andEndBookTimeLessThan(Date value) {
            addCriterion("end_book_time <", value, "endBookTime");
            return (Criteria) this;
        }

        public Criteria andEndBookTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_book_time <=", value, "endBookTime");
            return (Criteria) this;
        }

        public Criteria andEndBookTimeIn(List<Date> values) {
            addCriterion("end_book_time in", values, "endBookTime");
            return (Criteria) this;
        }

        public Criteria andEndBookTimeNotIn(List<Date> values) {
            addCriterion("end_book_time not in", values, "endBookTime");
            return (Criteria) this;
        }

        public Criteria andEndBookTimeBetween(Date value1, Date value2) {
            addCriterion("end_book_time between", value1, value2, "endBookTime");
            return (Criteria) this;
        }

        public Criteria andEndBookTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_book_time not between", value1, value2, "endBookTime");
            return (Criteria) this;
        }

        public Criteria andParkingTypeIsNull() {
            addCriterion("parking_type is null");
            return (Criteria) this;
        }

        public Criteria andParkingTypeIsNotNull() {
            addCriterion("parking_type is not null");
            return (Criteria) this;
        }

        public Criteria andParkingTypeEqualTo(Byte value) {
            addCriterion("parking_type =", value, "parkingType");
            return (Criteria) this;
        }

        public Criteria andParkingTypeNotEqualTo(Byte value) {
            addCriterion("parking_type <>", value, "parkingType");
            return (Criteria) this;
        }

        public Criteria andParkingTypeGreaterThan(Byte value) {
            addCriterion("parking_type >", value, "parkingType");
            return (Criteria) this;
        }

        public Criteria andParkingTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("parking_type >=", value, "parkingType");
            return (Criteria) this;
        }

        public Criteria andParkingTypeLessThan(Byte value) {
            addCriterion("parking_type <", value, "parkingType");
            return (Criteria) this;
        }

        public Criteria andParkingTypeLessThanOrEqualTo(Byte value) {
            addCriterion("parking_type <=", value, "parkingType");
            return (Criteria) this;
        }

        public Criteria andParkingTypeIn(List<Byte> values) {
            addCriterion("parking_type in", values, "parkingType");
            return (Criteria) this;
        }

        public Criteria andParkingTypeNotIn(List<Byte> values) {
            addCriterion("parking_type not in", values, "parkingType");
            return (Criteria) this;
        }

        public Criteria andParkingTypeBetween(Byte value1, Byte value2) {
            addCriterion("parking_type between", value1, value2, "parkingType");
            return (Criteria) this;
        }

        public Criteria andParkingTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("parking_type not between", value1, value2, "parkingType");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateIsNull() {
            addCriterion("gmt_update is null");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateIsNotNull() {
            addCriterion("gmt_update is not null");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateEqualTo(Date value) {
            addCriterion("gmt_update =", value, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateNotEqualTo(Date value) {
            addCriterion("gmt_update <>", value, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateGreaterThan(Date value) {
            addCriterion("gmt_update >", value, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_update >=", value, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateLessThan(Date value) {
            addCriterion("gmt_update <", value, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_update <=", value, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateIn(List<Date> values) {
            addCriterion("gmt_update in", values, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateNotIn(List<Date> values) {
            addCriterion("gmt_update not in", values, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateBetween(Date value1, Date value2) {
            addCriterion("gmt_update between", value1, value2, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtUpdateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_update not between", value1, value2, "gmtUpdate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
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