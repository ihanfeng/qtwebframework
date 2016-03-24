<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!doctype html>
<html>
<head>
    <title>中移全通系统集成有限公司 | 医疗云</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <%--全局css 插件--%>
    <link href="${ctx}/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/simple-line-icons.min.css"
          rel="stylesheet" type="text/css">
    <link href="${ctx}/static/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/css/uniform.default.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/lib/plugins/bootstrap-switch/bootstrap-switch.min.css" rel="stylesheet" type="text/css">
    <%--本页CSS 插件--%>
    <link href="${ctx}/static/lib/plugins/daterangepicker-bs3/daterangepicker-bs3.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/css/fullcalendar.min.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/lib/plugins/jq/jqvmap.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/lib/plugins/morries/morris.css" rel="stylesheet" type="text/css">

    <%--任务工具样式--%>
    <link href="${ctx}/static/css/tasks.css" rel="stylesheet" type="text/css">

    <%--定制化样式表--%>
    <link href="${ctx}/static/css/components-rounded.css" id="style_components" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/css/plugins.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/css/layout.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/static/css/light.css" rel="stylesheet" type="text/css" id="style_color">
    <link rel="shortcut icon" href="${ctx}/static/img/favicon.ico" type="image/x-icon">

    <style type="text/css">.jqstooltip {
        position: absolute;
        left: 0px;
        top: 0px;
        visibility: hidden;
        background: rgb(0, 0, 0);
        background-color: rgba(0, 0, 0, 0.6);
        filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000);
        -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)";
        color: white;
        font: 10px arial, san serif;
        text-align: left;
        white-space: nowrap;
        padding: 5px;
        border: 1px solid white;
        z-index: 10000;
    }

    .jqsfield {
        color: white;
        font: 10px arial, san serif;
        text-align: left;
    }</style>
</head>
<body>

<%--顶部--%>
<jsp:include page="tmpl/header.jsp"></jsp:include>
<%--主页面区--%>
<jsp:include page="tmpl/pagecontainer.jsp"></jsp:include>
</body>
<script type="text/javascript" src="${ctx}/static/lib/jquery.min.js">

</script>
<%--jquery 兼容工具--%>
<script src="${ctx}/static/lib/plugins/jq/jquery-migrate.min.js" type="text/javascript">

</script>
<!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${ctx}/static/lib/plugins/jq/jquery-ui.min.js" type="text/javascript"></script>
<script src="${ctx}/static/lib/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/static/lib/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
        type="text/javascript"></script>
<script src="${ctx}/static/lib/plugins/jq/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${ctx}/static/lib/plugins/jq/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${ctx}/static/lib/plugins/jq/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctx}/static/lib/plugins/jq/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${ctx}/static/lib/plugins/bootstrap-switch/bootstrap-switch.min.js" type="text/javascript"></script>


<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${ctx}/static/lib/plugins/jq/jquery.vmap.js" type="text/javascript"></script>
<script src="${ctx}/static/lib/plugins/jq/jquery.vmap.russia.js" type="text/javascript"></script>
<script src="${ctx}/static/lib/plugins/jq/jquery.vmap.world.js" type="text/javascript"></script>
<script src="${ctx}/static/lib/plugins/jq/jquery.vmap.europe.js" type="text/javascript"></script>
<script src="${ctx}/static/lib/plugins/jq/jquery.vmap.germany.js" type="text/javascript"></script>
<script src="${ctx}/static/lib/plugins/jq/jquery.vmap.usa.js" type="text/javascript"></script>
<script src="${ctx}/static/lib/plugins/jq/jquery.vmap.sampledata.js" type="text/javascript"></script>

<!-- IMPORTANT! fullcalendar depends on jquery-ui.min.js for drag & drop support -->
<script src="${ctx}/static/lib/plugins/morries/morris.min.js" type="text/javascript"></script>
<script src="${ctx}/static/lib/plugins/jq/raphael-min.js" type="text/javascript"></script>
<script src="${ctx}/static/lib/plugins/jq/jquery.sparkline.min.js" type="text/javascript"></script>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${ctx}/static/js/metronic.js" type="text/javascript"></script>
<script src="${ctx}/static/js/layout.js" type="text/javascript"></script>
<script src="${ctx}/static/js/quick-sidebar.js" type="text/javascript"></script>
<script src="${ctx}/static/js/demo.js" type="text/javascript"></script>
<script src="${ctx}/static/js/index3.js" type="text/javascript"></script>
<script src="${ctx}/static/js/tasks.js" type="text/javascript"></script>

<script type="text/javascript">
    jQuery(document).ready(function () {
        Metronic.init(); // init metronic core componets
        Layout.init(); // init layout
        Demo.init(); // init demo features
        QuickSidebar.init(); // init quick sidebar
        Index.init(); // init index page
        Tasks.initDashboardWidget(); // init tash dashboard widget
    });
</script>
</html>
