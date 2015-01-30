/* 字数验证*/
function Checkval(textNum) 
{ 
var s=textNum;
var n=0; 
for(var i=0;i<s.length;i++) 
{ 
//charCodeAt()可以返回指定位置的unicode编码,这个返回值是0-65535之间的整数 
if(s.charCodeAt(i)<128) 
{ n++; } 
else 
{ n+=3;} 
} 
alert(n); 
} 

