package com.ht.court.common;

/**
 * @author xinqichao
 *   庭审状态枚举类
 */
public interface CourtCaseStatusEnum {

	public enum TRAIL_STATUS implements CourtCaseStatusEnum{
		SHEDULE("排期"),//排期
		STARTCOURT("开庭"),//开庭
		RESTCOURT("休庭"),//休庭
		ENDCOURT("闭庭"),//闭庭
		PIGEONHOLE("归档");//归档
		
		private String status;
		
		TRAIL_STATUS(String status){
			this.status=status;
		}
		
		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	} 
}
