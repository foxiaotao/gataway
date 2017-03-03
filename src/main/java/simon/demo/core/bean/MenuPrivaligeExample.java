package simon.demo.core.bean;

import java.util.ArrayList;
import java.util.List;

public class MenuPrivaligeExample {
    protected String join;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MenuPrivaligeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setJoin(String join) {
        this.join = join;
    }

    public String getJoin() {
        return join;
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

        public Criteria andJoinWhere(String whereSql) {
            addCriterion(whereSql);
            return (Criteria) this;
        }

        public Criteria andIdIsNull() {
            addCriterion("menu_pri.id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("menu_pri.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("menu_pri.id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("menu_pri.id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("menu_pri.id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("menu_pri.id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("menu_pri.id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("menu_pri.id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("menu_pri.id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("menu_pri.id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(String sql) {
            addCriterion("menu_pri.id in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(String sql) {
            addCriterion("menu_pri.id not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("menu_pri.id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("menu_pri.id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("menu_pri.name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("menu_pri.name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("menu_pri.name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("menu_pri.name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("menu_pri.name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("menu_pri.name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("menu_pri.name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("menu_pri.name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("menu_pri.name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("menu_pri.name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("menu_pri.name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("menu_pri.name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(String sql) {
            addCriterion("menu_pri.name in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(String sql) {
            addCriterion("menu_pri.name not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("menu_pri.name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("menu_pri.name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("menu_pri.url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("menu_pri.url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("menu_pri.url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("menu_pri.url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("menu_pri.url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("menu_pri.url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("menu_pri.url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("menu_pri.url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("menu_pri.url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("menu_pri.url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("menu_pri.url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("menu_pri.url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(String sql) {
            addCriterion("menu_pri.url in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(String sql) {
            addCriterion("menu_pri.url not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("menu_pri.url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("menu_pri.url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andParentidIsNull() {
            addCriterion("menu_pri.parentid is null");
            return (Criteria) this;
        }

        public Criteria andParentidIsNotNull() {
            addCriterion("menu_pri.parentid is not null");
            return (Criteria) this;
        }

        public Criteria andParentidEqualTo(Long value) {
            addCriterion("menu_pri.parentid =", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotEqualTo(Long value) {
            addCriterion("menu_pri.parentid <>", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThan(Long value) {
            addCriterion("menu_pri.parentid >", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThanOrEqualTo(Long value) {
            addCriterion("menu_pri.parentid >=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThan(Long value) {
            addCriterion("menu_pri.parentid <", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThanOrEqualTo(Long value) {
            addCriterion("menu_pri.parentid <=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidIn(List<Long> values) {
            addCriterion("menu_pri.parentid in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotIn(List<Long> values) {
            addCriterion("menu_pri.parentid not in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidIn(String sql) {
            addCriterion("menu_pri.parentid in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andParentidNotIn(String sql) {
            addCriterion("menu_pri.parentid not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andParentidBetween(Long value1, Long value2) {
            addCriterion("menu_pri.parentid between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotBetween(Long value1, Long value2) {
            addCriterion("menu_pri.parentid not between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andInnerOrderIsNull() {
            addCriterion("menu_pri.inner_order is null");
            return (Criteria) this;
        }

        public Criteria andInnerOrderIsNotNull() {
            addCriterion("menu_pri.inner_order is not null");
            return (Criteria) this;
        }

        public Criteria andInnerOrderEqualTo(String value) {
            addCriterion("menu_pri.inner_order =", value, "innerOrder");
            return (Criteria) this;
        }

        public Criteria andInnerOrderNotEqualTo(String value) {
            addCriterion("menu_pri.inner_order <>", value, "innerOrder");
            return (Criteria) this;
        }

        public Criteria andInnerOrderGreaterThan(String value) {
            addCriterion("menu_pri.inner_order >", value, "innerOrder");
            return (Criteria) this;
        }

        public Criteria andInnerOrderGreaterThanOrEqualTo(String value) {
            addCriterion("menu_pri.inner_order >=", value, "innerOrder");
            return (Criteria) this;
        }

        public Criteria andInnerOrderLessThan(String value) {
            addCriterion("menu_pri.inner_order <", value, "innerOrder");
            return (Criteria) this;
        }

        public Criteria andInnerOrderLessThanOrEqualTo(String value) {
            addCriterion("menu_pri.inner_order <=", value, "innerOrder");
            return (Criteria) this;
        }

        public Criteria andInnerOrderLike(String value) {
            addCriterion("menu_pri.inner_order like", value, "innerOrder");
            return (Criteria) this;
        }

        public Criteria andInnerOrderNotLike(String value) {
            addCriterion("menu_pri.inner_order not like", value, "innerOrder");
            return (Criteria) this;
        }

        public Criteria andInnerOrderIn(List<String> values) {
            addCriterion("menu_pri.inner_order in", values, "innerOrder");
            return (Criteria) this;
        }

        public Criteria andInnerOrderNotIn(List<String> values) {
            addCriterion("menu_pri.inner_order not in", values, "innerOrder");
            return (Criteria) this;
        }

        public Criteria andInnerOrderIn(String sql) {
            addCriterion("menu_pri.inner_order in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andInnerOrderNotIn(String sql) {
            addCriterion("menu_pri.inner_order not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andInnerOrderBetween(String value1, String value2) {
            addCriterion("menu_pri.inner_order between", value1, value2, "innerOrder");
            return (Criteria) this;
        }

        public Criteria andInnerOrderNotBetween(String value1, String value2) {
            addCriterion("menu_pri.inner_order not between", value1, value2, "innerOrder");
            return (Criteria) this;
        }

        public Criteria andIconIsNull() {
            addCriterion("menu_pri.icon is null");
            return (Criteria) this;
        }

        public Criteria andIconIsNotNull() {
            addCriterion("menu_pri.icon is not null");
            return (Criteria) this;
        }

        public Criteria andIconEqualTo(String value) {
            addCriterion("menu_pri.icon =", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotEqualTo(String value) {
            addCriterion("menu_pri.icon <>", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThan(String value) {
            addCriterion("menu_pri.icon >", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThanOrEqualTo(String value) {
            addCriterion("menu_pri.icon >=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThan(String value) {
            addCriterion("menu_pri.icon <", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThanOrEqualTo(String value) {
            addCriterion("menu_pri.icon <=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLike(String value) {
            addCriterion("menu_pri.icon like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotLike(String value) {
            addCriterion("menu_pri.icon not like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconIn(List<String> values) {
            addCriterion("menu_pri.icon in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotIn(List<String> values) {
            addCriterion("menu_pri.icon not in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconIn(String sql) {
            addCriterion("menu_pri.icon in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andIconNotIn(String sql) {
            addCriterion("menu_pri.icon not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andIconBetween(String value1, String value2) {
            addCriterion("menu_pri.icon between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotBetween(String value1, String value2) {
            addCriterion("menu_pri.icon not between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andMenuTypeIsNull() {
            addCriterion("menu_pri.menu_type is null");
            return (Criteria) this;
        }

        public Criteria andMenuTypeIsNotNull() {
            addCriterion("menu_pri.menu_type is not null");
            return (Criteria) this;
        }

        public Criteria andMenuTypeEqualTo(String value) {
            addCriterion("menu_pri.menu_type =", value, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuTypeNotEqualTo(String value) {
            addCriterion("menu_pri.menu_type <>", value, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuTypeGreaterThan(String value) {
            addCriterion("menu_pri.menu_type >", value, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuTypeGreaterThanOrEqualTo(String value) {
            addCriterion("menu_pri.menu_type >=", value, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuTypeLessThan(String value) {
            addCriterion("menu_pri.menu_type <", value, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuTypeLessThanOrEqualTo(String value) {
            addCriterion("menu_pri.menu_type <=", value, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuTypeLike(String value) {
            addCriterion("menu_pri.menu_type like", value, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuTypeNotLike(String value) {
            addCriterion("menu_pri.menu_type not like", value, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuTypeIn(List<String> values) {
            addCriterion("menu_pri.menu_type in", values, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuTypeNotIn(List<String> values) {
            addCriterion("menu_pri.menu_type not in", values, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuTypeIn(String sql) {
            addCriterion("menu_pri.menu_type in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andMenuTypeNotIn(String sql) {
            addCriterion("menu_pri.menu_type not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andMenuTypeBetween(String value1, String value2) {
            addCriterion("menu_pri.menu_type between", value1, value2, "menuType");
            return (Criteria) this;
        }

        public Criteria andMenuTypeNotBetween(String value1, String value2) {
            addCriterion("menu_pri.menu_type not between", value1, value2, "menuType");
            return (Criteria) this;
        }

        public Criteria andIsdeleteIsNull() {
            addCriterion("menu_pri.isDelete is null");
            return (Criteria) this;
        }

        public Criteria andIsdeleteIsNotNull() {
            addCriterion("menu_pri.isDelete is not null");
            return (Criteria) this;
        }

        public Criteria andIsdeleteEqualTo(String value) {
            addCriterion("menu_pri.isDelete =", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteNotEqualTo(String value) {
            addCriterion("menu_pri.isDelete <>", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteGreaterThan(String value) {
            addCriterion("menu_pri.isDelete >", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteGreaterThanOrEqualTo(String value) {
            addCriterion("menu_pri.isDelete >=", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteLessThan(String value) {
            addCriterion("menu_pri.isDelete <", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteLessThanOrEqualTo(String value) {
            addCriterion("menu_pri.isDelete <=", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteLike(String value) {
            addCriterion("menu_pri.isDelete like", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteNotLike(String value) {
            addCriterion("menu_pri.isDelete not like", value, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteIn(List<String> values) {
            addCriterion("menu_pri.isDelete in", values, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteNotIn(List<String> values) {
            addCriterion("menu_pri.isDelete not in", values, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteIn(String sql) {
            addCriterion("menu_pri.isDelete in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andIsdeleteNotIn(String sql) {
            addCriterion("menu_pri.isDelete not in("+sql+")");
            return (Criteria) this;
        }

        public Criteria andIsdeleteBetween(String value1, String value2) {
            addCriterion("menu_pri.isDelete between", value1, value2, "isdelete");
            return (Criteria) this;
        }

        public Criteria andIsdeleteNotBetween(String value1, String value2) {
            addCriterion("menu_pri.isDelete not between", value1, value2, "isdelete");
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