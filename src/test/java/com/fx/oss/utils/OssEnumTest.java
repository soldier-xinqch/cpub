package com.fx.oss.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class OssEnumTest {

	@Test
	public void test() {
		assertEquals("MSG_SUCCESS",OssEnum.MSG.MSG_SUCCESS.name());
	}

}
