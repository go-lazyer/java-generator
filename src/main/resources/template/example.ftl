package ${entityPackage};

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
/**
 * ${table.moduleName}实体类
 * @author ${author}
 * @date ${updateTime}
 */
public class ${table.moduleNameCapi}Example{
    private String columnStr = "*";//需要查询的字段默认为所有
    
    private LinkedHashMap <String,String> orderBy;//排序字段
    
    Integer start;
    
    Integer size;
	
	protected boolean distinct;
	protected List<Criteria> criteriaList;
	public ${table.moduleNameCapi}Example() {
		criteriaList = new ArrayList<Criteria>();
	}
	
	public String getColumnStr() {
		return columnStr;
	}
	public void setColumnStr(String columnStr) {
		this.columnStr = columnStr;
	}
	public LinkedHashMap <String,String> getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(LinkedHashMap <String,String> orderBy) {
		this.orderBy = orderBy;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	
	
	
	
	
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}
	public boolean isDistinct() {
		return distinct;
	}
	public List<Criteria> getCriteriaList() {
		return criteriaList;
	}
	public void or(Criteria criteria) {
		criteriaList.add(criteria);
	}
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		criteriaList.add(criteria);
		return criteria;
	}
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (criteriaList.size() == 0) {
			criteriaList.add(criteria);
		}
		return criteria;
	}
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}
	public void clear() {
		criteriaList.clear();
		distinct = false;
	}
	public class Criteria {
		protected List<Criterion> criteria;

		Criteria() {
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

		protected void addCriterion(String condition, Object firstValue, Object secondValue, String property) {
			if (firstValue == null || secondValue == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, firstValue, secondValue));
		}
	  <#list table.fields as key> 
		private ${key.attrType} ${key.attribute}; //${key.comment}

		public Criteria and${key.attribute?cap_first}IsNull() {
			addCriterion("${table.tableName}.${key.field} is null");
			return (Criteria) this;
		}

		public Criteria and${key.attribute?cap_first}IsNotNull() {
			addCriterion("${table.tableName}.${key.field} is not null");
			return (Criteria) this;
		}

		public Criteria and${key.attribute?cap_first}EqualTo(String value) {
			addCriterion("${table.tableName}.${key.field} =", value, "${key.attribute}");
			return (Criteria) this;
		}

		public Criteria and${key.attribute?cap_first}NotEqualTo(String value) {
			addCriterion("${table.tableName}.${key.field} <>", value, "${key.attribute}");
			return (Criteria) this;
		}

		public Criteria and${key.attribute?cap_first}GreaterThan(String value) {
			addCriterion("${table.tableName}.${key.field} >", value, "${key.attribute}");
			return (Criteria) this;
		}

		public Criteria and${key.attribute?cap_first}GreaterThanOrEqualTo(String value) {
			addCriterion("${table.tableName}.${key.field} >=", value, "${key.attribute}");
			return (Criteria) this;
		}

		public Criteria and${key.attribute?cap_first}LessThan(String value) {
			addCriterion("${table.tableName}.${key.field} <", value, "${key.attribute}");
			return (Criteria) this;
		}

		public Criteria and${key.attribute?cap_first}LessThanOrEqualTo(String value) {
			addCriterion("${table.tableName}.${key.field} <=", value, "${key.attribute}");
			return (Criteria) this;
		}

		public Criteria and${key.attribute?cap_first}Like(String value) {
			addCriterion("${table.tableName}.${key.field} like", value, "${key.attribute}");
			return (Criteria) this;
		}

		public Criteria and${key.attribute?cap_first}NotLike(String value) {
			addCriterion("${table.tableName}.${key.field} not like", value, "${key.attribute}");
			return (Criteria) this;
		}

		public Criteria and${key.attribute?cap_first}In(List<String> values) {
			addCriterion("${table.tableName}.${key.field} in", values, "${key.attribute}");
			return (Criteria) this;
		}

		public Criteria and${key.attribute?cap_first}NotIn(List<String> values) {
			addCriterion("${table.tableName}.${key.field} not in", values, "${key.attribute}");
			return (Criteria) this;
		}

		public Criteria and${key.attribute?cap_first}Between(String firstValue, String secondValue) {
			addCriterion("${table.tableName}.${key.field} between", firstValue, secondValue, "${key.attribute}");
			return (Criteria) this;
		}

		public Criteria and${key.attribute?cap_first}NotBetween(String firstValue, String secondValue) {
			addCriterion("${table.tableName}.${key.field} not between", firstValue, secondValue, "${key.attribute}");
			return (Criteria) this;
		}
	    </#list>
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

