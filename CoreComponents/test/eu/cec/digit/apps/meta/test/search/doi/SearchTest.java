package eu.cec.digit.apps.meta.test.search.doi;

import java.util.Date;

import com.business.apps.core.data.search.criteria.Operator;
import com.business.apps.core.data.search.engine.SearchEngineFactory;
import com.business.apps.core.data.search.engine.SearchEntity;


public class SearchTest {
	
	private static final String CHECK_1 = "select t from DigitEntity t where 1=1  AND t.dateUpdated>:dateUpdated AND t.updatedByUser=:updatedByUser";

	public static void main(String[] args){
		new SearchTest().test1();
		//new SearchTest().testIgnoreNullValuePredicates();
		//new SearchTest().testOracleDates();
	}
	
	
	public void test1(){
		SearchDigitEntityRequest sb=new SearchDigitEntityRequest();
		sb.getUser().setOperator(Operator.EQUALS_TO);
		sb.getUser().setValue("abc");
		sb.getDateUpdated().setOperator(Operator.GREATER_THEN);
		sb.getDateUpdated().setValue(new Date());
		SearchEntity sr=(SearchEntity)new SearchEngineFactory().engineFactory(sb);
		String buildQuery = sr.buildQueryString(sb);
		System.out.println(buildQuery);
		//Assert....
		boolean equals = buildQuery.equals(CHECK_1);
		if (!equals)
			throw new RuntimeException("Test1 failed: generated query is not the right one");
		
	}
	
//	public void testIgnoreNullValuePredicates(){
//		SearchDigitEntityRequest sb=new SearchDigitEntityRequest();
//		sb.getUser().setOperator(Operator.EQUALS_TO);
//		sb.getUser().setValue("abc");
//		sb.getDateUpdated().setOperator(Operator.GREATER_THEN);
//		sb.getDateUpdated().setValue(null);
//		String buildQuery = sb.buildQueryString();
//		System.out.println(buildQuery);
//		boolean equals = buildQuery.equals("select t from DigitEntity t where 1=1  AND t.updatedByUser=:updatedByUser");
//		if (!equals)
//			throw new RuntimeException("testIgnoreNullValuePredicates failed: generated query is not the right one");
//		
//		
//	}
//	public void testOracleDates(){
//		SearchDigitEntityRequest.SEARCH_CRITERIA_FACTORY_CLASS=new PredicateFactoryDialectOracle();
//		SearchDigitEntityRequest sb=new SearchDigitEntityRequest();
//		
//		sb.getDateUpdated().setOperator(Operator.GREATER_THEN);
//		sb.getDateUpdated().setValue(new Date());
//		String buildQuery = sb.buildQueryString();
//		System.out.println(buildQuery);
//		boolean equals = buildQuery.equals("select t from DigitEntity t where 1=1  AND TRUNC(t.dateUpdated)>:dateUpdated");
//		if (!equals)
//			throw new RuntimeException("testOracleDates failed: generated query is not the right one");
//		
//	}
	
	
}
