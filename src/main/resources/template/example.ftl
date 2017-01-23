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
	
    private String selectColumns = "*";//自定义查询
    private LinkedHashMap <String,String> orderBy;//排序字段
    
    Integer start;
    Integer size;
	
    <#list table.fields as key> 
    public static String ${key.field} = "${key.column}";
    </#list>
    
    public static String ORDER_ASC="asc";
    public static String ORDER_DESC="desc";
    
	protected boolean distinct;
	protected List<Criteria> criteriaList;
	
	public ${table.moduleNameCapi}Example() {
		criteriaList = new ArrayList<Criteria>();
	}

	public LinkedHashMap <String,String> getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String field,String type) {
		LinkedHashMap<String, String> orderByMap= new LinkedHashMap<String, String>();
		orderByMap.put(field, type);
		setOrderBy(orderByMap);
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

	public String getSelectColumns() {
		return selectColumns;
	}
	
	public void setSelectColumns(String selectColumns) {
		this.selectColumns = selectColumns;
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
			criteria.add(new Criterion(condition,Criterion.noValue));
		}

		protected void addCriterion(String condition,String valueType, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition,valueType, value));
		}

		protected void addCriterion(String condition,String valueType, Object firstValue, Object secondValue, String property) {
			if (firstValue == null || secondValue == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition,valueType, firstValue, secondValue));
		}
	  <#list table.fields as key> 
		private ${key.fieldType} ${key.field}; //${key.comment}

		public Criteria and${key.field?cap_first}IsNull() {
			addCriterion("${key.column} is null");
			return (Criteria) this;
		}

		public Criteria and${key.field?cap_first}IsNotNull() {
			addCriterion("${key.column} is not null");
			return (Criteria) this;
		}

		public Criteria and${key.field?cap_first}EqualTo(String value) {
			addCriterion("${key.column} =",Criterion.singleValue, value, "${key.field}");
			return (Criteria) this;
		}
		
		public Criteria and${key.field?cap_first}NotEqualTo(String value) {
			addCriterion("${key.column} <>",Criterion.singleValue, value, "${key.field}");
			return (Criteria) this;
		}

		public Criteria and${key.field?cap_first}GreaterThan(String value) {
			addCriterion("${key.column} >",Criterion.singleValue, value, "${key.field}");
			return (Criteria) this;
		}

		public Criteria and${key.field?cap_first}GreaterThanOrEqualTo(String value) {
			addCriterion("${key.column} >=",Criterion.singleValue, value, "${key.field}");
			return (Criteria) this;
		}

		public Criteria and${key.field?cap_first}LessThan(String value) {
			addCriterion("${key.column} <",Criterion.singleValue, value, "${key.field}");
			return (Criteria) this;
		}

		public Criteria and${key.field?cap_first}LessThanOrEqualTo(String value) {
			addCriterion("${key.column} <=",Criterion.singleValue, value, "${key.field}");
			return (Criteria) this;
		}
		
		
		public Criteria and${key.field?cap_first}EqualToField(String fieldName) {
			addCriterion("${key.column} =",Criterion.fieldValue, fieldName, "${key.field}");
			return (Criteria) this;
		}
		
		public Criteria and${key.field?cap_first}NotEqualToField(String fieldName) {
			addCriterion("${key.column} <>",Criterion.fieldValue, fieldName, "${key.field}");
			return (Criteria) this;
		}

		public Criteria and${key.field?cap_first}GreaterThanField(String fieldName) {
			addCriterion("${key.column} >",Criterion.fieldValue, fieldName, "${key.field}");
			return (Criteria) this;
		}

		public Criteria and${key.field?cap_first}GreaterThanOrEqualToField(String fieldName) {
			addCriterion("${key.column} >=",Criterion.fieldValue, fieldName, "${key.field}");
			return (Criteria) this;
		}

		public Criteria and${key.field?cap_first}LessThanField(String fieldName) {
			addCriterion("${key.column} <",Criterion.fieldValue, fieldName, "${key.field}");
			return (Criteria) this;
		}

		public Criteria and${key.field?cap_first}LessThanOrEqualToField(String fieldName) {
			addCriterion("${key.column} <=",Criterion.fieldValue, fieldName, "${key.field}");
			return (Criteria) this;
		}
		

		public Criteria and${key.field?cap_first}Like(String value) {
			addCriterion("${key.column} like",Criterion.singleValue, value, "${key.field}");
			return (Criteria) this;
		}

		public Criteria and${key.field?cap_first}NotLike(String value) {
			addCriterion("${key.column} not like",Criterion.singleValue, value, "${key.field}");
			return (Criteria) this;
		}

		public Criteria and${key.field?cap_first}In(List<String> values) {
			addCriterion("${key.column} in",Criterion.listValue, values, "${key.field}");
			return (Criteria) this;
		}

		public Criteria and${key.field?cap_first}NotIn(List<String> values) {
			addCriterion("${key.column} not in",Criterion.listValue, values, "${key.field}");
			return (Criteria) this;
		}

		public Criteria and${key.field?cap_first}Between(String firstValue, String secondValue) {
			addCriterion("${key.column} between", Criterion.betweenValue,firstValue, secondValue, "${key.field}");
			return (Criteria) this;
		}

		public Criteria and${key.field?cap_first}NotBetween(String firstValue, String secondValue) {
			addCriterion("${key.column} not between",Criterion.betweenValue, firstValue, secondValue, "${key.field}");
			return (Criteria) this;
		}
	    </#list>
	}

	public static class Criterion {
		public static String  noValue ="noValue";
		public static String  singleValue ="singleValue";
		public static String  fieldValue ="fieldValue";
		public static String  betweenValue ="betweenValue";
		public static String  listValue ="listValue";
		
		
		private String condition;
		private Object value;
		private Object secondValue;
		private String valueType;


		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}
		
		public String getValueType() {
			return valueType;
		}
		protected Criterion(String condition,String valueType) {
			super();
			this.condition = condition;
			this.valueType = valueType;
		}
		protected Criterion(String condition,String valueType,Object value) {
			super();
			this.condition = condition;
			this.valueType = valueType;
			this.value = value;
		}
		protected Criterion(String condition,String valueType,Object value,Object secondValue) {
			super();
			this.condition = condition;
			this.valueType = valueType;
			this.value = value;
			this.secondValue = secondValue;
		}
	}


}

