<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>公募基本表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="pfBaseController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${pfBasePage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">名称</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							基金简称:
						</label>
					</td>
					<td class="value">
					     	 <input id="shortName" name="shortName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">基金简称</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							成立日期:
						</label>
					</td>
					<td class="value">
							   <input id="estaDate" name="estaDate" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"  ignore="ignore" />    
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">成立日期</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							托管人:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="tuoguanPerson" type="list" 
Expression po.dictTable is undefined on line 163, column 319 in table/single/cgform_jspTemplate_add.ftl.
The problematic instruction:
----------
==> ${po.dictTable} [on line 163, column 317 in table/single/cgform_jspTemplate_add.ftl]
 in user-directive dictInfo [on line 163, column 295 in table/single/cgform_jspTemplate_add.ftl]
----------

Java backtrace for programmers:
----------
freemarker.core.InvalidReferenceException: Expression po.dictTable is undefined on line 163, column 319 in table/single/cgform_jspTemplate_add.ftl.
	at freemarker.core.TemplateObject.assertNonNull(TemplateObject.java:125)
	at freemarker.core.Expression.getStringValue(Expression.java:118)
	at freemarker.core.Expression.getStringValue(Expression.java:93)
	at freemarker.core.DollarVariable.accept(DollarVariable.java:76)
	at freemarker.core.Environment.visit(Environment.java:221)
	at freemarker.core.MixedContent.accept(MixedContent.java:92)
	at freemarker.core.Environment.visit(Environment.java:221)
	at freemarker.core.Environment.renderElementToString(Environment.java:1594)
	at freemarker.core.StringLiteral.getStringValue(StringLiteral.java:101)
	at freemarker.core.StringLiteral._getAsTemplateModel(StringLiteral.java:86)
	at freemarker.core.Expression.getAsTemplateModel(Expression.java:89)
	at freemarker.core.Environment.visit(Environment.java:568)
	at freemarker.core.UnifiedCall.accept(UnifiedCall.java:106)
	at freemarker.core.Environment.visit(Environment.java:221)
	at freemarker.core.MixedContent.accept(MixedContent.java:92)
	at freemarker.core.Environment.visit(Environment.java:221)
	at freemarker.core.IfBlock.accept(IfBlock.java:82)
	at freemarker.core.Environment.visit(Environment.java:221)
	at freemarker.core.MixedContent.accept(MixedContent.java:92)
	at freemarker.core.Environment.visit(Environment.java:221)
	at freemarker.core.IteratorBlock$Context.runLoop(IteratorBlock.java:179)
	at freemarker.core.Environment.visit(Environment.java:428)
	at freemarker.core.IteratorBlock.accept(IteratorBlock.java:102)
	at freemarker.core.Environment.visit(Environment.java:221)
	at freemarker.core.MixedContent.accept(MixedContent.java:92)
	at freemarker.core.Environment.visit(Environment.java:221)
	at freemarker.core.Environment.process(Environment.java:199)
	at freemarker.template.Template.process(Template.java:259)
	at org.jeecgframework.codegenerate.generate.CgformCodeFactory.generateFileUserDefined(CgformCodeFactory.java:87)
	at org.jeecgframework.codegenerate.generate.CgformCodeFactory.invokeUserDefined(CgformCodeFactory.java:158)
	at org.jeecgframework.codegenerate.generate.CgformCodeGenerate.generateToFileUserDefined(CgformCodeGenerate.java:273)
	at org.jeecgframework.web.cgform.controller.generate.GenerateController.dogenerate(GenerateController.java:192)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:221)
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:137)
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:104)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandleMethod(RequestMappingHandlerAdapter.java:747)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:676)
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85)
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:938)
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:870)
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:961)
	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:863)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:661)
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:837)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	at org.jeecgframework.core.aop.GZipFilter.doFilter(GZipFilter.java:119)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:140)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:88)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	at org.springframework.orm.hibernate4.support.OpenSessionInViewFilter.doFilterInternal(OpenSessionInViewFilter.java:150)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198)
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:493)
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)
	at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:650)
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:800)
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:806)
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1498)
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:745)
