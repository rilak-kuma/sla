/*******************************************************************************
*  Project      : BGF Retail Mobile Store Management
*  Program ID   : CommonDAO.java
*  Description  : DAO의 자동화 설정
*
********************************************************************************
*  Program History
*  Date        Author    Description
*  ----------  --------  --------------------------------------------------------
*  2018-06-15  LEEC.J    Created.
*******************************************************************************/

package kr.maxerve.admin.framework;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class BaseDAOSupport extends SqlSessionDaoSupport {
	/**
     * CommonDAO 는 base class 로만 사용되며 해당 인스턴스를 직접 생성할 수 없도록 protected constructor 로 선언하였음.
     */
	protected BaseDAOSupport(){

	}

	@Resource(name = "sqlSessionTemplate")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
}
