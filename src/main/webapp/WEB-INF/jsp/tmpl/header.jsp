<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<div class="page-header navbar ">
    <div class="page-logo">
        <a href="#">
            <h1><b>中移全通</b></h1>
        </a>
        <div class="menu-toggler sidebar-toggler">
            <!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
        </div>
    </div>
    <!-- BEGIN RESPONSIVE MENU TOGGLER -->
    <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse"
       data-target=".navbar-collapse">
    </a>
    <!-- END RESPONSIVE MENU TOGGLER -->
    <!-- DOC: Remove "hide" class to enable the page header actions -->
    <div class="page-actions">
        <div class="btn-group">
            <button type="button" class="btn red-haze btn-sm dropdown-toggle" data-toggle="dropdown"
                    data-hover="dropdown" data-close-others="true">
                <span class="hidden-sm hidden-xs">操作&nbsp;</span><i class="fa fa-angle-down"></i>
            </button>
            <ul class="dropdown-menu" role="menu">
                <li>
                    <a href="javascript:;">
                        <i class="icon-docs"></i> 新岗位 </a>
                </li>
                <li>
                    <a href="javascript:;">
                        <i class="icon-tag"></i> 新评论 </a>
                </li>
                <li>
                    <a href="javascript:;">
                        <i class="icon-share"></i> 分享 </a>
                </li>
                <li class="divider">
                </li>
                <li>
                    <a href="javascript:;">
                        <i class="icon-flag"></i> 评论 <span class="badge badge-success">4</span>
                    </a>
                </li>
                <li>
                    <a href="javascript:;">
                        <i class="icon-users"></i> 反馈 <span class="badge badge-danger">2</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!-- END PAGE ACTIONS -->

    <!-- BEGIN PAGE TOP -->
    <div class="page-top">
        <!-- BEGIN HEADER SEARCH BOX -->
        <!-- DOC: Apply "search-form-expanded" right after the "search-form" class to have half expanded search box -->
        <form action="" class="search-form" method="post">
            <div class="input-group">
                <input type="text" placeholder="搜索..." class="form-control input-sm" name="query">
                <span class="input-group-btn">
                    <a href="javascript:;" class="btn submit">
                        <i class="icon-magnifier"></i>
                    </a>
                </span>
            </div>
        </form>
        <!-- END HEADER SEARCH BOX -->
        <!-- BEGIN TOP NAVIGATION MENU -->
        <div class="top-menu">
            <ul class="nav navbar-nav pull-right">
                <li class="separator hide">
                </li>
                <!-- BEGIN NOTIFICATION DROPDOWN -->
                <%--通知--%>
                <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                <li class="dropdown dropdown-extended dropdown-notification dropdown-dark" id="header_notification_bar">
                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                       data-close-others="true">
                        <i class="icon-bell"></i>
                        <span class="badge badge-success">7</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="external">
                            <h3><span class="bold">12 待处理</span> 通知</h3>
                            <a href="javascript:;">查看所有</a>
                        </li>
                        <li>
                            <div class="slimScrollDiv"
                                 style="position: relative; overflow: hidden; width: auto; height: 250px;">
                                <ul class="dropdown-menu-list scroller"
                                    style="height: 250px; overflow: hidden; width: auto;" data-handle-color="#637283"
                                    data-initialized="1">
                                    <li>
                                        <a href="javascript:;">
                                            <span class="time">just now</span>
                                                    <span class="details">
										<span class="label label-sm label-icon label-success">
										<i class="fa fa-plus"></i>
										</span> 有新用户注册. </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                            <span class="time">3 mins</span>
                                                    <span class="details">
										<span class="label label-sm label-icon label-danger">
										<i class="fa fa-bolt"></i>
										</span> Server #12 过载. </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                            <span class="time">10 mins</span>
                                                    <span class="details">
										<span class="label label-sm label-icon label-warning">
										<i class="fa fa-bell-o"></i>
										</span> Server #2 无响应. </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                            <span class="time">14 hrs</span>
                                                    <span class="details">
										<span class="label label-sm label-icon label-info">
										<i class="fa fa-bullhorn"></i>
										</span> 应用程序错误. </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                            <span class="time">2 days</span>
                                                    <span class="details">
										<span class="label label-sm label-icon label-danger">
										<i class="fa fa-bolt"></i>
										</span> 数据库已经使用 68%. </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                            <span class="time">3 days</span>
                                                    <span class="details">
										<span class="label label-sm label-icon label-danger">
										<i class="fa fa-bolt"></i>
										</span> 用户IP锁定. </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                            <span class="time">4 days</span>
                                                    <span class="details">
										<span class="label label-sm label-icon label-warning">
										<i class="fa fa-bell-o"></i>
										</span> Storage Server #4 无响应. </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                            <span class="time">5 days</span>
                                                    <span class="details">
										<span class="label label-sm label-icon label-info">
										<i class="fa fa-bullhorn"></i>
										</span> 系统错误 </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                            <span class="time">9 days</span>
                                                    <span class="details">
										<span class="label label-sm label-icon label-danger">
										<i class="fa fa-bolt"></i>
										</span> 存储空间不足 </span>
                                        </a>
                                    </li>
                                </ul>
                                <div class="slimScrollBar"
                                     style="width: 7px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px; background: rgb(99, 114, 131);"></div>
                                <div class="slimScrollRail"
                                     style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; opacity: 0.2; z-index: 90; right: 1px; background: rgb(234, 234, 234);"></div>
                            </div>
                        </li>
                    </ul>
                </li>
                <!-- END NOTIFICATION DROPDOWN -->
                <li class="separator hide">
                </li>
                <!-- BEGIN INBOX DROPDOWN -->
                <%--消息--%>
                <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                <li class="dropdown dropdown-extended dropdown-inbox dropdown-dark" id="header_inbox_bar">
                    <%--图标--%>
                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                       data-close-others="true">
                        <i class="icon-envelope-open"></i>
                                <span class="badge badge-danger">
						4 </span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="external">
                            <h3>你有 <span class="bold">7 </span> 消息</h3>
                            <a href="javascript:;">查看所有</a>
                        </li>
                        <li>
                            <div class="slimScrollDiv"
                                 style="position: relative; overflow: hidden; width: auto; height: 275px;">
                                <ul class="dropdown-menu-list scroller"
                                    style="height: 275px; overflow: hidden; width: auto;" data-handle-color="#637283"
                                    data-initialized="1">
                                    <li>
                                        <a href="javascript:;">
                                                    <span class="photo">
										<img src="${ctx}/static/img/avatar/avatar2.jpg"
                                             class="img-circle" alt="avatar">
										</span>
                                                    <span class="subject">
										<span class="from">
										Lisa Wong </span>
                                                    <span class="time">Just Now </span>
                                                    </span>
                                                    <span class="message">
										Vivamus sed auctor nibh congue nibh. auctor nibh auctor nibh... </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                                    <span class="photo">
										<img src="${ctx}/static/img/avatar/avatar3.jpg"
                                             class="img-circle" alt="">
										</span>
                                                    <span class="subject">
										<span class="from">
										Richard Doe </span>
                                                    <span class="time">16 mins </span>
                                                    </span>
                                                    <span class="message">
										Vivamus sed congue nibh auctor nibh congue nibh. auctor nibh auctor nibh... </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                                    <span class="photo">
										<img src="${ctx}/static/img/avatar/avatar1.jpg"
                                             class="img-circle" alt="">
										</span>
                                                    <span class="subject">
										<span class="from">
										Bob Nilson </span>
                                                    <span class="time">2 hrs </span>
                                                    </span>
                                                    <span class="message">
										Vivamus sed nibh auctor nibh congue nibh. auctor nibh auctor nibh... </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                                    <span class="photo">
										<img src="${ctx}/static/img/avatar/avatar2.jpg"
                                             class="img-circle" alt="">
										</span>
                                                    <span class="subject">
										<span class="from">
										Lisa Wong </span>
                                                    <span class="time">40 mins </span>
                                                    </span>
                                                    <span class="message">
										Vivamus sed auctor 40% nibh congue nibh... </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                                    <span class="photo">
										<img src="${ctx}/static/img/avatar/avatar3.jpg"
                                             class="img-circle" alt="">
										</span>
                                                    <span class="subject">
										<span class="from">
										Richard Doe </span>
                                                    <span class="time">46 mins </span>
                                                    </span>
                                                    <span class="message">
										Vivamus sed congue nibh auctor nibh congue nibh. auctor nibh auctor nibh... </span>
                                        </a>
                                    </li>
                                </ul>
                                <div class="slimScrollBar"
                                     style="width: 7px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px; background: rgb(99, 114, 131);"></div>
                                <div class="slimScrollRail"
                                     style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; opacity: 0.2; z-index: 90; right: 1px; background: rgb(234, 234, 234);"></div>
                            </div>
                        </li>
                    </ul>
                </li>
                <!-- END INBOX DROPDOWN -->
                <li class="separator hide">
                </li>
                <!-- BEGIN TODO DROPDOWN -->
                <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->

                <li class="dropdown dropdown-extended dropdown-tasks dropdown-dark" id="header_task_bar">
                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                       data-close-others="true">
                        <i class="icon-calendar"></i>
                                <span class="badge badge-primary">
						3 </span></a>
                    <ul class="dropdown-menu extended tasks">
                        <li class="external">
                            <h3>你有 <span class="bold">12 待处理</span> 的任务</h3>
                            <a href="http://localhost/v4.1.0/theme/templates/admin4/page_todo.html">view all</a>
                        </li>
                        <li>
                            <div class="slimScrollDiv"
                                 style="position: relative; overflow: hidden; width: auto; height: 275px;">
                                <ul class="dropdown-menu-list scroller"
                                    style="height: 275px; overflow: hidden; width: auto;" data-handle-color="#637283"
                                    data-initialized="1">
                                    <li>
                                        <a href="javascript:;">
                                                    <span class="task">
										<span class="desc">New release v1.2 </span>
                                                    <span class="percent">30%</span>
                                                    </span>
                                                    <span class="progress">
										<span style="width: 40%;" class="progress-bar progress-bar-success"
                                              aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"><span
                                                class="sr-only">40% Complete</span></span>
                                                    </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                                    <span class="task">
										<span class="desc">Application deployment</span>
                                                    <span class="percent">65%</span>
                                                    </span>
                                                    <span class="progress">
										<span style="width: 65%;" class="progress-bar progress-bar-danger"
                                              aria-valuenow="65" aria-valuemin="0" aria-valuemax="100"><span
                                                class="sr-only">65% Complete</span></span>
                                                    </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                                    <span class="task">
										<span class="desc">Mobile app release</span>
                                                    <span class="percent">98%</span>
                                                    </span>
                                                    <span class="progress">
										<span style="width: 98%;" class="progress-bar progress-bar-success"
                                              aria-valuenow="98" aria-valuemin="0" aria-valuemax="100"><span
                                                class="sr-only">98% Complete</span></span>
                                                    </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                                    <span class="task">
										<span class="desc">Database migration</span>
                                                    <span class="percent">10%</span>
                                                    </span>
                                                    <span class="progress">
										<span style="width: 10%;" class="progress-bar progress-bar-warning"
                                              aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"><span
                                                class="sr-only">10% Complete</span></span>
                                                    </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                                    <span class="task">
										<span class="desc">Web server upgrade</span>
                                                    <span class="percent">58%</span>
                                                    </span>
                                                    <span class="progress">
										<span style="width: 58%;" class="progress-bar progress-bar-info"
                                              aria-valuenow="58" aria-valuemin="0" aria-valuemax="100"><span
                                                class="sr-only">58% Complete</span></span>
                                                    </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                                    <span class="task">
										<span class="desc">Mobile development</span>
                                                    <span class="percent">85%</span>
                                                    </span>
                                                    <span class="progress">
										<span style="width: 85%;" class="progress-bar progress-bar-success"
                                              aria-valuenow="85" aria-valuemin="0" aria-valuemax="100"><span
                                                class="sr-only">85% Complete</span></span>
                                                    </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;">
                                                    <span class="task">
										<span class="desc">New UI release</span>
                                                    <span class="percent">38%</span>
                                                    </span>
                                                    <span class="progress progress-striped">
										<span style="width: 38%;" class="progress-bar progress-bar-important"
                                              aria-valuenow="18" aria-valuemin="0" aria-valuemax="100"><span
                                                class="sr-only">38% Complete</span></span>
                                                    </span>
                                        </a>
                                    </li>
                                </ul>
                                <div class="slimScrollBar"
                                     style="width: 7px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px; background: rgb(99, 114, 131);"></div>
                                <div class="slimScrollRail"
                                     style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; opacity: 0.2; z-index: 90; right: 1px; background: rgb(234, 234, 234);"></div>
                            </div>
                        </li>
                    </ul>
                    <!-- END TODO DROPDOWN -->
                <li class="separator hide">
                </li>
                <!-- BEGIN USER LOGIN DROPDOWN -->
                <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                <li class="dropdown dropdown-user dropdown-dark">
                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                       data-close-others="true">
                                <span class="username username-hide-on-mobile">
						Nick </span>
                        <!-- DOC: Do not remove below empty space(&nbsp;) as its purposely used -->
                        <img alt="" class="img-circle" src="${ctx}/static/img/avatar/avatar5.jpg">
                    </a>
                    <ul class="dropdown-menu dropdown-menu-default">
                        <li>
                            <a href="javascript:;">
                                <i class="icon-user"></i> My Profile </a>
                        </li>
                        <li>
                            <a href="javascript:;">
                                <i class="icon-calendar"></i> My Calendar </a>
                        </li>
                        <li>
                            <a href="javascript:;">
                                <i class="icon-envelope-open"></i> My Inbox <span class="badge badge-danger">
								3 </span>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:;">
                                <i class="icon-rocket"></i> My Tasks <span class="badge badge-success">
								7 </span>
                            </a>
                        </li>
                        <li class="divider">
                        </li>
                        <li>
                            <a href="javascript:;">
                                <i class="icon-lock"></i> Lock Screen </a>
                        </li>
                        <li>
                            <a href="javascript:;">
                                <i class="icon-key"></i> Log Out </a>
                        </li>
                    </ul>
                </li>
                <!-- END USER LOGIN DROPDOWN -->
                <!-- BEGIN USER LOGIN DROPDOWN -->
                <li class="dropdown dropdown-extended quick-sidebar-toggler">
                    <span class="sr-only">Toggle Quick Sidebar</span>
                    <i class="icon-logout"></i>
                </li>
                <!-- END USER LOGIN DROPDOWN -->
            </ul>
        </div>
    </div>
</div>