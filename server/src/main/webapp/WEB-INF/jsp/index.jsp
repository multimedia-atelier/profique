<%@
        page contentType="text/html; charset=UTF-8" language="java" %><%@
        taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
        taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"
%>
<html>
  <head>
    <title></title>
  </head>
  <body>

  <h1>Multimedia Atelier Mobile User Testing</h1>

  <h2>Registered devices</h2>
  <c:if test="${not empty devices}">
      <table border="1">
          <tr>
              <th>registered</th>
              <th>name</th>
              <th></th>
          </tr>
              <%--@elvariable id="devices" type="ma.profique.domain.DummyEntity[]"--%>
          <c:forEach var="d" items="${devices}">
              <tr>
                  <td><fmt:formatDate value="${d.dateRegistered}" dateStyle="MEDIUM" timeStyle="MEDIUM" type="BOTH"/></td>
                  <td>${d.deviceName}</td>
                  <td><a href="/admin/?deviceId=${d.deviceId}">events</a></td>
              </tr>
          </c:forEach>
      </table>
  </c:if>

  <%--@elvariable id="events" type="cz.hiring.domain.EventEntity[]"--%>
  <c:if test="${not empty events}">
  <h2>Device events</h2>

      <table border="1">
          <tr>
              <th>timestamp</th>
              <th>checkpoint</th>
              <th>app</th>
              <th>screenshot</th>
          </tr>
      <c:forEach var="e" items="${events}">
          <tr>
            <td><fmt:formatDate value="${e.timestamp}" dateStyle="MEDIUM" timeStyle="MEDIUM" type="BOTH"/></td>
            <td>${e.checkpoint}</td>
            <td>${e.app}</td>
            <td><c:if test="${not empty e.screenshot}"><a href="/api/events/screenshots/${e.app}/${e.screenshot}"><img src="/api/events/screenshots/${e.app}/${e.screenshot}" width="50"/></a></c:if></td>
          </tr>
      </c:forEach>
      </table>

  </c:if>

  </body>
</html>