package com.ht.court.service;

import org.junit.Test;


public class RestTest{
	
	@Test
	public void testMe() throws Exception{
      /*  DefaultClientConfig dcc = new DefaultClientConfig();
	    dcc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client c = Client.create(dcc);
       
        
        _testGet(c);*/
        
	}
	
/*	private void _testGet(Client c) throws Exception{
		WebResource r = c.resource(getBaseURI()).path("courtCase").path("v1")
        		.path("index");
        System.out.println("请求url="+r.getURI().toString());
//        MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
//        queryParams.add("srcContent", "测试值班报告内容" +System.currentTimeMillis());
		ClientResponse response = r.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		
		if(response.getStatus()!= 200){
			throw new Exception();
		}
		
        //r.addFilter(new LoggingFilter());
		System.out.println("调用成功。");
		RestResponse<CourtCaseWithBLOBs> resp = response
				.getEntity(new GenericType<RestResponse<CourtCaseWithBLOBs>>() {
				});
		System.out.println("返回data=" + resp.getData());
	}
	
	private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/cpub/").port(8080).build();
    }*/
}
