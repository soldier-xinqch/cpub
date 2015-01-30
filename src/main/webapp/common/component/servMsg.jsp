<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/include.jsp"%>
<c:if test="${errMsgs!=null}">
    <div class="alert alert-error">
        <button class="close" data-dismiss="alert" type="button">×</button>
        <h4>错误</h4>
    <c:forEach items="${errMsgs}" var="msg">
       <li>${msg}</li>
    </c:forEach>
    </div>
</c:if>
<c:if test="${promptMsgs!=null}">
    <div class="alert alert-info">
        <button class="close" data-dismiss="alert" type="button">×</button>
        <h4>提示</h4>
    <c:forEach items="${prmptMsgs}" var="msg">
        <li>${msg}</li>
    </c:forEach>
    </div>
</c:if>
<c:if test="${okMsgs!=null}">
    <div class="alert alert-success">
        <button class="close" data-dismiss="alert" type="button">×</button>
        <h4>成功</h4>
        <ul>
    <c:forEach items="${okMsgs}" var="msg">
        <li>${msg}</li>
    </c:forEach>
        </ul>
    </div>
</c:if>