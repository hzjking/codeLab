<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/views/common/taglib.jsp"%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="michaelhe">
  <link href="<%=request.getContextPath()%>/static/css/style.default.css" rel="stylesheet">
  <link rel="shortcut icon" href="<%=request.getContextPath()%>/static/images/favicon.png" type="image/png">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/bootstrap-timepicker.min.css" />
  <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/jquery.tagsinput.css" />
  <script src="<%=request.getContextPath()%>/static/js/jquery-1.10.2.min.js"></script>
  <script src="<%=request.getContextPath()%>/static/js/jquery-migrate-1.2.1.min.js"></script>
  <script src="<%=request.getContextPath()%>/static/js/jquery-ui-1.10.3.min.js"></script>
  <script src="<%=request.getContextPath()%>/static/js/bootstrap.min.js"></script>
  <script src="<%=request.getContextPath()%>/static/js/modernizr.min.js"></script>
  <script src="<%=request.getContextPath()%>/static/js/jquery.sparkline.min.js"></script>
  <script src="<%=request.getContextPath()%>/static/js/toggles.min.js"></script>
  <script src="<%=request.getContextPath()%>/static/js/retina.min.js"></script>
  <script src="<%=request.getContextPath()%>/static/js/jquery.cookies.js"></script>
  <script src="<%=request.getContextPath()%>/static/js/jquery.autogrow-textarea.js"></script>
  <script src="<%=request.getContextPath()%>/static/js/bootstrap-timepicker.min.js"></script>
  <script src="<%=request.getContextPath()%>/static/js/jquery.maskedinput.min.js"></script>
  <script src="<%=request.getContextPath()%>/static/js/jquery.tagsinput.min.js"></script>
  <script src="<%=request.getContextPath()%>/static/js/jquery.mousewheel.js"></script>
  <script src="<%=request.getContextPath()%>/static/js/chosen.jquery.min.js"></script>
  <script src="<%=request.getContextPath()%>/static/js/custom.js"></script>
  <script type="text/javascript" language="javascript">

    jQuery(document).ready(function(){
      // Date Picker
      jQuery('#startTime').datepicker();

      jQuery('#endTime').datepicker();

    });


    function queryOrderListForPage(pageNo){
      $("#pageNo").val(pageNo);
      submit();
    }
    function nextPage(pageNo){
      $("#pageNo").val(pageNo+1);
      submit();
    }
    function prevPage(pageNo){
      $("#pageNo").val(pageNo-1);
      submit();
    }
    function submit(){
      document.queryApplyDataParam.action = '<%=request.getContextPath()%>/user/apply_data';
      document.queryApplyDataParam.submit();
    }

    function userPhoneFocus(){
      document.getElementById("userPhone").style.borderColor="#D3EAF3 ";
    }
    function userPhoneBlur(){
      document.getElementById("userPhone").style.borderColor="d0cec2 ";
      var userPhone=document.getElementById("userPhone").value;
      if(isNaN(userPhone)){
        alert("您输入的非数字！请重新输入...");
        document.getElementById("userPhone").onfocus;
        return false;
      }
      return true;

    }

    function userNameFocus(){
      document.getElementById("userName").style.borderColor="D3EAF3 ";
    }
    function userNameBlur(){
      document.getElementById("userName").style.borderColor="d0cec2 ";
    }

    function userShopNameFocus(){
      document.getElementById("shopName").style.borderColor="D3EAF3 ";
    }
    function userShopNameBlur(){
      document.getElementById("shopName").style.borderColor="d0cec2 ";
    }

    function userAppldIdFocus(){
      document.getElementById("applyId").style.borderColor="D3EAF3 ";
    }
    function userAppldIdBlur(){
      document.getElementById("applyId").style.borderColor="d0cec2 ";
      var applyId=document.getElementById("applyId").value;
      if(isNaN(applyId)){
        alert("您输入的非数字！请重新输入...");
        document.getElementById("applyId").onfocus;
        return false;
      }
      return true;
    }

    function userAMTStartFocus(){
      document.getElementById("expectedFinancingAmtStart").style.borderColor="D3EAF3 ";
    }
    function userAMTStartBlur(){
      document.getElementById("expectedFinancingAmtStart").style.borderColor="d0cec2 ";
    }
    function userAMTEndFocus(){
      document.getElementById("expectedFinancingAmtEnd").style.borderColor="D3EAF3 ";
    }
    function userAMTEndBlur(){
      document.getElementById("expectedFinancingAmtEnd").style.borderColor="d0cec2 ";
    }

    function userAmt(){
      var amtEnd=document.getElementById("expectedFinancingAmtEnd").value;
      var amtStart=document.getElementById("expectedFinancingAmtStart").value;
      if(amtEnd==""&& amtStart==""){
        return true;
      }
      if(amtEnd!="" && amtStart=="" ){
        alert("请检查输入的融资金额范围！");
        return false;
      }
      if(amtEnd=="" && amtStart!=""){
        alert("请检查输入的融资金额范围！");
        return false;
      }
      if(amtEnd >= amtStart){
          return true;
      }else{
        alert("请检查输入的融资金额范围！");
        return false;
      }
    }

    function userTimeStartFocus(){
      document.getElementById("startTime").style.borderColor="D3EAF3 ";
    }
    function userTimeStartBlur(){
      document.getElementById("startTime").style.borderColor="d0cec2 ";
    }

    function userTimeEndFocus(){
      document.getElementById("endTime").style.borderColor="D3EAF3 ";
    }
    function userTimeEndBlur(){
      document.getElementById("endTime").style.borderColor="d0cec2 ";
    }

    function userTime(){
      var startTime=document.getElementById("startTime");
      var endTime=document.getElementById("endTime");
      if(startTime.value==""&& endTime.value==""){
        return true;
      }
      var arr = startTime.value.split("/");
      var stime = new Date(arr[0], arr[1], arr[2]);
      var stimes = stime.getTime();
      var arrs = endTime.value.split("/");
      var dtime = new Date(arrs[0], arrs[1], arrs[2]);
      var dtimes = dtime.getTime();
      if(dtimes >= stimes){
        return true;
      }else{
      alert("请检查输入的申请日期范围！");
      return false;
      }
    }

    function checkAll(){
        if(userAmt() && userTime()){
          return true;
        }
      return false;
    }
    function to_detail(param){
      location.href="<%=request.getContextPath()%>/user/apply_data_detail?id="+param;
    }
  </script>
</head>

<body class="signin">

<!-- Preloader -->
<div id="preloader">
  <div id="status"><i class="fa fa-spinner fa-spin"></i></div>
</div>

<section>
  <div class="panel panel-default">
    <div class="panel-heading">
      <div class="panel-btns">
        <a href="#" class="panel-close">&times;</a>
        <a href="#" class="minimize">&minus;</a>
      </div><!-- panel-btns -->
      <h3 class="panel-title">申请资料列表</h3>
    </div>
    <form id="queryApplyDataParam" name="queryApplyDataParam" action="<%=request.getContextPath()%>/user/apply_data_param" method="post" onsubmit="return checkAll()" >
      <div class="form-group">
        <div class="col-sm-3">
          <label class="control-label">申请用户:</label>
          <input id="userPhone" name="userPhone"   placeholder="请输入手机号" type="text"class="form-control" onfocus="userPhoneFocus()"  onblur="userPhoneBlur()"/>
        </div>

        <div class="col-sm-3">
          <label class="control-label">申请人姓名:</label>
          <input id="userName" name="userName" type="text" placeholder="请输入姓名" class="form-control" onfocus="userNameFocus()" onblur="userNameBlur()"/>
        </div>

        <div class="col-sm-3">
          <label class="control-label">经营名称:</label>
          <input id="shopName" name="shopName" type="text" placeholder="请输入经营名称" class="form-control" onfocus="userShopNameFocus()" onblur="userShopNameBlur()"/>
        </div>

      </div>

      <div class="form-group">

        <div class="col-sm-3">
          <label class="control-label">申请编号:</label>
          <input id="applyId" name="applyId" type="text" placeholder="请输入申请编号" class="form-control" onfocus="userAppldIdFocus()" onblur="userAppldIdBlur()"/>
        </div>

        <div class="col-sm-3">
          <label class="control-label">期望融资金额：</label>
          <div class="input-group">
            <input type="text"  placeholder="请输入起始融资金额" id="expectedFinancingAmtStart" name="expectedFinancingAmtStart" class="form-control"  onfocus="userAMTStartFocus()" onblur="userAMTStartBlur()">
            <input type="text" placeholder="请输入终止融资金额"  class="form-control"  id="expectedFinancingAmtEnd"   name="expectedFinancingAmtEnd"    onfocus="userAMTEndFocus()" onblur="userAMTEndBlur()">
          </div>
        </div>

        <div class="col-sm-3">
          <label class="control-label">申请日期：</label>
          <div class="input-group">
            <input type="text" placeholder="请输入开始日期" class="form-control" id="startTime" name="startTime" onfocus="userTimeStartFocus()" onblur="userTimeStartBlur()">
            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
          </div>

          <div class="input-group">
            <input type="text" placeholder="请输入结束日期" class="form-control" id="endTime" name="endTime"  onfocus="userTimeEndFocus()" onblur="userTimeEndBlur()">
            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
          </div>
        </div>



        <div class="form-group">
          <div class="col-sm-6">
            <label class="control-label">商户类型：</label>
            <select id="type" name="type" class="form-contol mb15">
              <option value="">全部</option>
              <option value="0">杉德收单</option>
              <option value="1">非杉德收单</option>
            </select>
          </div>
        </div>
      </div>


      <div class="panel-footer">
        <div class="row pull-right"  style="padding-left: 10px">
          <div class="col-sm-6 col-sm-offset-3">
            <button type="reset" id="modify" class="btn btn-primary" onclick="updateUser();" > 重置</button>
          </div>
        </div>
        <div class="row pull-right"   style="padding-left: 10px">
          <div class="col-sm-6 col-sm-offset-3">
            <button type="submit"  class="btn btn-primary" > 搜索</button>
          </div>

        </div>
      </div>
      <div class="panel-body">

        <div id="turn_pages_s.earch">
          <input type="hidden" id="pageNo" name="pageNo">
          <c:choose>
            <c:when test="${page.pageNo==1 || page.pageNo==null}">
              <a href="javascript:void(0);"><img src="<%=request.getContextPath()%>/static/images/previous.gif" border="0" /></a>
            </c:when>
            <c:otherwise>
              <a href="javascript:void(0);prevPage(${page.pageNo});"><img src="<%=request.getContextPath()%>/static/images/previous.gif" border="0" /></a>
            </c:otherwise>
          </c:choose>

          <c:forEach items="${page.pageNums}" var="i">
            <c:choose>
              <c:when test="${page.pageNo==i}">
                <a href="javascript:queryOrderListForPage(${i});void(0);">${i}</a>
              </c:when>
              <c:otherwise>
                <a href="javascript:queryOrderListForPage(${i});void(0);">${i}</a>
              </c:otherwise>
            </c:choose>
          </c:forEach>

          <c:choose>
            <c:when test="${page.pageNo==page.totalPage}">
              <a href="javascript:void(0);"><img src="<%=request.getContextPath()%>/static/images/next.gif" border="0" /></a>
            </c:when>
            <c:otherwise>
              <a href="javascript:void(0);nextPage(${page.pageNo});"><img src="<%=request.getContextPath()%>/static/images/next.gif" border="0" /></a>
            </c:otherwise>
          </c:choose>

        </div>


        <div class="table-responsive">
          <table class="table  table-striped table-hover mb30" id="userTable">
            <thead>
            <tr>
              <th>申请用户</th>
              <th>申请人姓名</th>
              <th>经营名称</th>
              <th>商户类型</th>
              <th>期望融资期限</th>
              <th>期望融资金额</th>
              <th>申请日期</th>
              <th>状态</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${!empty applyDatas}">
              <c:forEach items="${applyDatas}" var="apply">
                <tr class="odd gradeX" onclick="to_detail('${apply.id}');">
                  <td>${apply.phone}</td>
                  <td>${apply.realName}</td>
                  <td>${apply.shopName}</td>
                  <c:if test="${apply.sandState=='0'}">
                    <td>杉德收单</td>
                  </c:if>
                  <c:if test="${apply.sandState=='1'}">
                    <td>非杉德收单</td>
                  </c:if>
                  <td>${apply.expectedFinancingTerms}</td>
                  <td>${apply.expectedFinancingAmt}</td>
                  <td>${apply.createTime}</td>
                  <c:if test="${apply.activeState=='0'}">
                    <td>当前</td>
                  </c:if>
                  <c:if test="${apply.activeState=='1'}">
                    <td>历史</td>
                  </c:if>
                </tr>
              </c:forEach>
            </c:if>
            <c:if test="${empty applyDatas}">
              <tr class="odd gradeX">
                <td colspan="8" align="center"><span style="color: #b20910">没有符合条件的结果，请确认查询条件！</span></td>
              </tr>
            </c:if>
            </tbody>
          </table>




        </div>
      </div><!-- panel-body -->
    </form>
  </div><!-- panel -->
</section>


</body>
</html>

