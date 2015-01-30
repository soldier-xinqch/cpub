YUI().use('charts','charts-legend', function (Y){
    //dataProvider source
    var myDataValues = [
        {date:"1/1/2010", 成功点播数:2, 失败点播数:3, 庭审总数:2},
        {date:"2/1/2010", 成功点播数:5, 失败点播数:9, 庭审总数:1},
        {date:"3/1/2010", 成功点播数:4, 失败点播数:1, 庭审总数:1},
        {date:"4/1/2010", 成功点播数:3, 失败点播数:3, 庭审总数:2},
        {date:"5/1/2010", 成功点播数:5, 失败点播数:7, 庭审总数:2},
        {date:"6/1/2010", 成功点播数:3, 失败点播数:3, 庭审总数:2},
        {date:"7/1/2010", 成功点播数:5, 失败点播数:7, 庭审总数:2},
        {date:"8/1/2010", 成功点播数:3, 失败点播数:4, 庭审总数:1} 
       /*  {date:"1/1/2010", 成功点播数:2},
        {date:"2/1/2010", 成功点播数:5},
        {date:"3/1/2010", 成功点播数:4},
        {date:"4/1/2010", 成功点播数:3},
        {date:"5/1/2010", 成功点播数:5},
        {date:"6/1/2010", 成功点播数:3} */
    ];
		    var styleDef = {
		        series:{
		        	成功点播数:{
		                marker:{
		                    fill:{//点的颜色
		                        color:"#DD9EEA"
		                    },
		                    border:{//鼠标没有扫过时点边框的颜色
		                        color:"#9EEAC3"
		                    },
		                    over:{
		                        fill:{//鼠标扫过    点的颜色
		                            color:"#B09EEA"
		                        },
		                        border:{//鼠标扫过点边框的颜色
		                            color:"#00FFCC"
		                        },
		                        width: 12,
		                        height: 12
		                    }
		                },
		                line:{//线的颜色
		                    color:"#1EF032"
		                }
		            },	失败点播数:{
		                marker:{
		                    fill:{//点的颜色
		                        color:"#7092BE"
		                    },
		                    border:{//鼠标没有扫过时点边框的颜色
		                        color:"#450AA5"
		                    },
		                    over:{
		                        fill:{//鼠标扫过    点的颜色
		                            color:"#E7E0F3"
		                        },
		                        border:{//鼠标扫过点边框的颜色
		                            color:"#2D2B32"
		                        },
		                        width: 12,
		                        height: 12
		                    }
		                },
		                line:{//线的颜色
		                    color:"#C3C3C3"
		                }
		            },	庭审总数:{
		                marker:{
		                    fill:{//点的颜色
		                        color:"#70C7DA"
		                    },
		                    border:{//鼠标没有扫过时点边框的颜色
		                        color:"#E6B60E"
		                    },
		                    over:{
		                        fill:{//鼠标扫过    点的颜色
		                            color:"#A349A4"
		                        },
		                        border:{//鼠标扫过点边框的颜色
		                            color:"#00FFCC"
		                        },
		                        width: 12,
		                        height: 12
		                    }
		                },
		                line:{//线的颜色
		                    color:"#7072DA"
		                }
		            }
		        }
		    }; 
		    //Define our axes for the chart.
		    var myAxes = {
		    	sales:{
		            keys:["成功点播数","失败点播数","庭审总数"],
		            position:"left",
		            type:"numeric",
		            title:"庭审数",
		            styles:{
		                majorTicks:{
		                    display: "none"
		                }
		            },
					labelFormat: {
						decimalPlaces: 0
						}
			        },
			    category:{
		            keys:["date"],
		            position:"bottom",
		            type:"category",
		            title:"日期",
		            styles:{
		                majorTicks:{
		                    display: "none"
		                },
		                label: {
		                    rotation:0,//倾斜角度
		                    margin:{top:0},
		              		rotation: 0
		                }
		            }
		        }
		    };
		    var myLegend = {
		    		 position: "bottom",
                     width: 300,
                     height: 300,
                     styles: {
                         hAlign: "center",
                         hSpacing: 4
                     }
                 };
		    
		    var mychart = new Y.Chart({
		                                dataProvider:myDataValues,
		                                interactionType:"planar",
		                                categoryKey:"date",
		                                axes:myAxes,
		                                legend:myLegend,
		                                styles:styleDef,
		                                horizontalGridlines:true,
		                                verticalGridlines:true,
		                                render:"#mychart"
		                            }); 

	    //*************************************************************************************************************************
	    // Create data
	    var myDataValues = [
	            {day:"Wednesday", taxes:4000},
	            {day:"Thursday", taxes:200},
	            {day:"Friday", taxes:2000}
	    ];

	    var myLegend = {
	    		position: "right",
                width: 300,
                height: 300,
                styles: {
                    hAlign: "center",
                    hSpacing: 4
                }
            };
	    var pieGraph = new Y.Chart({
	            render:"#planTotal",
	            categoryKey:"day",
	            seriesKeys:["taxes"],
	            legend:myLegend,
	            dataProvider:myDataValues,
	            type:"pie",
	            seriesCollection:[
	                {
	                    categoryKey:"day",
	                    valueKey:"taxes"
	                }
	            ]
	        });
	}); 