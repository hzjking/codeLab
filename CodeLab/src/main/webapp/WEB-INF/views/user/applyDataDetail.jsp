<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/3
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
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
    <title>信息明细</title>
  <style>
    #dmt_table{
      border: none;
    }
    img{border: 2px solid #FFFFFF;width:125px;height:120px;}
    img:hover{border: none;}

    #dmt_table a{float:left;width:130px;height:125px; font-size: 8px; text-align: center;}
    #dmt_table p{float:left;}
    table tr td.big_img{ width: 200px; height: 180px; overflow:visible;}
    table tr td.big_img img{width:100%; }
    table tr td.big_img img:hover{transition:.3s;transform: scale(1.4,1.4);
      -webkit-transform: scale(1.4,1.4);
      -ms-transform: scale(1.4,1.4);
      -moz-transform: scale(1.4,1.4);
      -o-transform: scale(1.4,1.4);}



  </style>
  <script>

    function showImg(url){
      window.open(url);
    }

  </script>
</head>
<body class="signin">
<!-- Preloader -->
<%--<div id="preloader">--%>
  <%--<div id="status"><i class="fa fa-spinner fa-spin"></i></div>--%>
<%--</div>--%>
<section>
  <div class="panel " style="border: #d0cec2 1px solid;overflow:visible;position:relative; top:50px ">
    <div style="border:1px solid #D3EAF3; border-radius: 10px; padding:5px 5px;position:absolute;top:-15px;left:45px ;z-index:100;text-align:center; background: #fff">
      基础信息
    </div>
    <div><br></div>

    <table class="table   mb30">
      <tr>
        <td width="120px"><span class="pull-left"  >商户号:</span></td>
        <td >${merchant.posMerchantId}</td>
        <td  width="120px"><span class="pull-left">注册名称:</span></td>
        <td>${merchant.shopName}</td>
        <td width="120px"><span class="pull-left"  >营业执照注册号:</span></td>
        <td >${merchant.bizlicense}
          <c:if test="${merchant.sandState==0}"><span style="color: green">电</span></c:if>
        </td>
      </tr>
      <tr>
        <td width="120px"><span class="pull-left"  >申请人姓名:</span></td>
        <td>${merchant.realName}</td>
        <td width="120px"><span class="pull-left"  >申请人身份证:</span></td>
        <td>${merchant.idNo}</td>
        <td width="120px"><span class="pull-left"  >申请人手机号:</span></td>
        <td>${merchant.phone}</td>
      </tr>
      <tr>
        <td width="120px"><span class="pull-left"  >所属行业:</span></td>
        <td>${merchant.industry}</td>
        <td width="120px"><span class="pull-left"  >银行卡号:</span></td>
        <td>${merchant.bankCardNo}</td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td width="120px" ><span class="pull-left"  >商户经营地址:</span></td>
        <td colspan="5">${merchant.businessAddressIds}</td>
      </tr>

    </table>


  </div><!-- panel -->


  <div class="panel " style="border: #d0cec2 1px solid;overflow:visible;position:relative; top:50px ">
    <div style="border:1px solid #D3EAF3; border-radius: 10px; padding:5px 5px;position:absolute;top:-15px;left:45px ;z-index:100;text-align:center; background: #fff">
      详细信息
    </div>
    <div><br></div>
    <div class="table-responsive">
      <table class="table  mb30" id="userTable">
      <tr>
        <td width="120px"><span class=" pull-left"  >融资目的:</span></td>
        <td>${merchant.purposes}</td>
        <td width="120px"><span class=" pull-left"  >期望融资金额:</span></td>
        <td>${merchant.hopeAmt}&nbsp;&nbsp;(元)</td>
        <td width="120px"><span class=" pull-left"  >期望融资期限:</span></td>
        <td>${merchant.hopeTimeName}</td>
      </tr>
      <tr>
        <td width="120px"><span class=" pull-left"  >手机号验证:</span></td>
        <td>${merchant.phone}</td>
        <td width="120px"><span class=" pull-left"  >紧急联系人:</span></td>
        <td>${merchant.ecName}</td>
        <td width="120px"><span class=" pull-left"  >紧急联系人手机:</span></td>
        <td>${merchant.ecPhone}</td>
      </tr>
      <tr>
        <td width="120px"><span class=" pull-left"  >直系亲属类型:</span></td>
        <td>${merchant.fmType}</td>
        <td width="120px"><span class=" pull-left"  >直系亲属姓名:</span></td>
        <td >${merchant.fmName}</td>
        <td width="120px"><span class=" pull-left"  >直系亲属手机:</span></td>
        <td>${merchant.fmPhone}</td>
      </tr>
      <tr>
        <td width="120px"><span class=" pull-left"  >门店数:</span></td>
        <td >${merchant.stores}&nbsp;&nbsp;(家)</td>
        <td width="120px"><span class=" pull-left"  >关联营业执照:</span></td>
        <td>${merchant.additionalBizlicense}</td>
        <td width="120px"><span class=" pull-left"  >关联商户号:</span></td>
        <td>${merchant.additionalPosMerchantId}</td>
      </tr>
        <tr>
          <td width="120px"><span class=" pull-left"  >关联经营地址:</span></td>
          <td colspan="4" style="width: auto;;">${merchant.relateBusinessAddress}</td>
        </tr>

    </table>
      </div>
  </div><!-- panel -->


  <div class="panel " style="border: #d0cec2 1px solid;overflow:visible;position:relative; top:50px ">
    <div style="border:1px solid #D3EAF3; border-radius: 10px; padding:5px 5px;position:absolute;top:-15px;left:45px ;z-index:100;text-align:center; background: #fff">
      征信信息
    </div>
    <div><br></div>
    <div class="table-responsive">
      <table class="table mb30" id="userTable1">
          <tr>
              <td width="120px"><span class=" pull-left"  >征信中心登录名:</span></td>
              <td >${merchant.cicLogin}</td>
              <td width="120px"><span class=" pull-left"  >征信中心密码:</span></td>
              <td>${merchant.cicPwd}</td>
              <td width="120px"><span class=" pull-left"  >身份验证码:</span></td>
              <td>${merchant.cicCertCode}</td>
          </tr>
        </table>
      </div>
  </div><!-- panel -->


  <div class="panel " style="border: #d0cec2 1px solid;overflow:visible;position:relative; top:50px ">
    <div style="border:1px solid #D3EAF3; border-radius: 10px; padding:5px 5px;position:absolute;top:-15px;left:45px ;z-index:100;text-align:center; background: #fff">
      多媒体资料
    </div>
    <div><br></div>

    <div class="table-responsive">
      <table class="table mb30" id="dmt_table">
        <tr>
          <td><span class=" pull-left"  >申请人身份证:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${merchant.idNo}</span></td>
        </tr>
        <tr>
          <td class="big_img">
          <a href="javascript:void(0)" onclick="showImg('${res.idFacePicImgUrl}')"><img src="${res.idFacePicImgUrl}" alt="身份证正面照" title="   身份证正面照  "  class="pull-left"><span style="font-size: 14px;text-decoration-line: none">身份证正面照</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
          <p style="width:20px;"></p>
            <a href="javascript:void(0)" onclick="showImg('${res.idBackPicImgUrl}')"><img src="${res.idBackPicImgUrl}" alt="身份证反面照" title="   身份证反面照  " class="pull-left"><span style="font-size: 14px;text-decoration-line: none">身份证反面照</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
            <p style="width:20px;"></p>
          <a href="javascript:void(0)" onclick="showImg('${res.idHoldPicImgUrl}')"><img src="${res.idHoldPicImgUrl}"  alt="手持身份证半人照" title="   手持身份证半人照   "  class="pull-left"><span style="font-size: 14px;text-decoration-line: none">手持身份证半人照</span></a></td>
          <%--<td></td>--%>
        </tr>
        <tr>
          <td><span class=" pull-left"  >银行卡卡号:</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${merchant.bankCardNo}</td>
        </tr>
        <tr>
          <td class="big_img"><a href="javascript:void(0)" onclick="showImg('${res.bankCardPicImgUrl}')"><img src="${res.bankCardPicImgUrl}" alt="银行卡正面照" title="银行卡正面照"  class="pull-left"><span style="font-size: 14px;text-decoration-line: none">银行卡正面照</span></a></td>
        </tr>
        <tr>
          <td><span>门店租赁信息</span><br/><span>房东手机号：${merchant.landlordPhone}</span></td>
        </tr>
        <tr>
          <td class="big_img">
          <c:choose>
            <c:when test="${not empty res.shopLeaseContractImgUrl}">
              <c:forEach items="${res.shopLeaseContractImgUrl}" var="file">
                <a href="javascript:void(0)" onclick="showImg('${file}')"><img src="${file}" alt="门店租赁合同" title="门店租赁合同" class="pull-left"><span style="font-size: 14px;text-decoration-line: none">门店租赁合同</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
                <p style="width:20px;"></p>
              </c:forEach>
            </c:when>
            <c:otherwise>
                <a href="javascript:void(0)" ><img src="#" alt="门店租赁合同" title="门店租赁合同" class="pull-left"><span style="font-size: 14px;text-decoration-line: none">门店租赁合同</span></a>
            </c:otherwise>
          </c:choose>
          </td>
        </tr>
        <tr>
          <td><span>营业执照：</span></td>
        </tr>
        <tr>
          <td class="big_img"><a href="javascript:void(0)" onclick="showImg('${res.merCertPicImgUrl}')"><img src="${res.merCertPicImgUrl}" alt="营业执照" title="营业执照"  class="pull-left"><span style="font-size: 14px;text-decoration-line: none">营业执照</span></a></td>
        </tr>
        <tr>
          <td><span>店铺照片：</span></td>
        </tr>
        <tr>
          <td class="big_img"><a href="javascript:void(0)" onclick="showImg('${res.storePicImgUrl}')"><img src="${res.storePicImgUrl}" alt="门牌照" title="门牌照"  class="pull-left"><span style="font-size: 14px;text-decoration-line: none">门牌照</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
          <%--<td><a href="javascript:void(0)" onclick="showImg('${res.innerPicImgUrl}')"><img src="${res.innerPicImgUrl}" alt="店内照" title="店内照" class="pull-left"></a></td>--%>
          <c:choose>
            <c:when test="${not empty res.innerPicImgUrl}">
              <c:forEach items="${res.innerPicImgUrl}" var="file">
                <p style="width:20px;"></p>
                <a href="javascript:void(0)" onclick="showImg('${file}')"><img src="${file}" alt="店内照" title="店内照" class="pull-left"><span style="font-size: 14px;text-decoration-line: none">店内照</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
              </c:forEach>
            </c:when>
            <c:otherwise>
              <p style="width:20px;"></p>
              <a href="javascript:void(0)" onclick="showImg('${res.innerPicImgUrl}')"><img src="#" alt="店内照" title="店内照" class="pull-left"><span style="font-size: 14px;text-decoration-line: none">店内照</span></a>
            </c:otherwise>
          </c:choose>
            <p style="width:20px;"></p>
          <a href="javascript:void(0)" onclick="showImg('${res.outerPicImgUrl}')"><img src="${res.outerPicImgUrl}" alt="店外照" title="店外照" class="pull-left"><span style="font-size: 14px;text-decoration-line: none">店外照</span></a>
            <p style="width:20px;"></p>
            <a href="javascript:void(0)" onclick="showImg('${res.posCounterPicImgUrl}')"><img src="${res.posCounterPicImgUrl}" alt="pos机与收银台合照" title="pos机与收银台合照"  class="pull-left"><span style="font-size: 14px;text-decoration-line: none">pos机与收银台合照</span></a>
          </td>
        </tr>
      </table>
    </div>
    <div style="text-align: center">
      <br>
      <br>
      <a href="<%=request.getContextPath()%>/apply/apply_data" id="colose"  class="btn btn-primary" role="button"> 关闭</a>
    </div>
  </div><!-- panel -->
</section>
</body>
</html>
