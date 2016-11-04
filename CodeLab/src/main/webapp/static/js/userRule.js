function userNameFocus(){
    document.getElementById("userName").style.borderColor="#D3EAF3 ";
}


function userNameBlur(){
    document.getElementById("userName").style.borderColor="d0cec2 ";
    var userName=document.getElementById("userName");
    var userNameDiv=document.getElementById("userNameDiv");
    var userNameReg=/^[a-zA-Z]\w{3,50}$/;
    userNameDiv.className="col-sm-3";
    userNameDiv.innerHTML="";
    if(!userNameReg.test(userName.value) && userName.value!=""){
        document.getElementById("userNameDiv").style.color="#b20910";
        userNameDiv.innerHTML="用户名只能以字母、数字、下划线组合!";
        return false;
    }else if(userNameReg.test(userName.value) && userName.value!=""){
        document.getElementById("userNameDiv").style.color="#24b92b";
        document.getElementById("userNameDiv").className='col-sm-3 fa fa-check';
        return true;
    }
}
function userPhoneFocus(){
    document.getElementById("userPhone").style.borderColor="#D3EAF3 ";
}


function userPhoneBlur(){
    document.getElementById("userPhone").style.borderColor="d0cec2 ";
    var userPhoneReg=/^1[3|4|5|7|8][0-9]\d{8}$/;
    var userPhoneDiv=document.getElementById("userPhoneDiv");
    userPhoneDiv.className="col-sm-3";
    userPhoneDiv.innerHTML="";
    var userPhone=document.getElementById("userPhone");
    if(!userPhoneReg.test(userPhone.value) && userPhone.value!=""){
        document.getElementById("userPhoneDiv").style.color="#b20910";
        userPhoneDiv.innerHTML="请检查手机号格式！";
        return false;
    }else if(userPhoneReg.test(userPhone.value) && userPhone.value!=""){
        document.getElementById("userPhoneDiv").style.color="#24b92b";
        document.getElementById("userPhoneDiv").className='col-sm-3 fa fa-check';
        return true;
    }



}
function startTimeFocus(){
    document.getElementById("startTime").style.borderColor="#D3EAF3 ";
    document.getElementById("startTimeDiv").innerHTML="";
}


function startTimeBlur(){
    document.getElementById("startTimeDiv").innerHTML="*";
    document.getElementById("startTimeDiv").style.color="#b20910";
    document.getElementById("startTime").style.borderColor="d0cec2 ";
    var startTimeDiv=document.getElementById("startTimeDiv");
    var startTime=document.getElementById("startTime");
    var endTimeDiv=document.getElementById("endTimeDiv");
    var endTime=document.getElementById("endTime");

    var arr = startTime.value.split("/");
    var stime = new Date(arr[0], arr[1], arr[2]);
    var stimes = stime.getTime();
    var arrs = endTime.value.split("/");
    var dtime = new Date(arrs[0], arrs[1], arrs[2]);
    var dtimes = dtime.getTime();
    if(stimes>=dtimes && endTime.value!=""){
        document.getElementById("endTimeDiv").innerHTML="开始时间不能大于结束时间！";
        document.getElementById("endTimeDiv").style.color="#b20910";
        return false
    }else{
        document.getElementById("endTimeDiv").innerHTML="*";
        document.getElementById("endTimeDiv").style.color="#b20910";
        return true;
    }



}
function endTimeFocus(){
    document.getElementById("endTime").style.borderColor="#D3EAF3 ";
    document.getElementById("endTimeDiv").innerHTML="";
}



function endTimeBlur(){
    var nowDate=new Date();
    var dd=nowDate.getDate();
    var mm=nowDate.getMonth()+1;
    var yy=nowDate.getFullYear();

    var startTimeDiv=document.getElementById("startTimeDiv");
    var startTime=document.getElementById("startTime");
    var endTimeDiv=document.getElementById("endTimeDiv");
    var endTime=document.getElementById("endTime");
    document.getElementById("endTimeDiv").innerHTML="*";
    document.getElementById("endTimeDiv").style.color="#b20910";
    document.getElementById("endTime").style.borderColor="d0cec2 ";


    var arr = startTime.value.split("/");
    var stime = new Date(arr[0], arr[1], arr[2]);
    var stimes = stime.getTime();
    var arrs = endTime.value.split("/");
    var dtime = new Date(arrs[0], arrs[1], arrs[2]);
    var dtimes = dtime.getTime();
    if(startTime.value==""||endTime.value==""){
        alert("未选择开始或结束时间会默认输入当前系统的日期！");
    }
    if(startTime.value==""){
        startTime.value=mm+"/"+dd+"/"+yy;
    }
    if(endTime.value==""){
        endTime.value=mm+"/"+dd+"/"+yy;
    }
    if(stimes>=dtimes){
        document.getElementById("endTimeDiv").innerHTML="开始时间不能大于结束时间！";
        document.getElementById("endTimeDiv").style.color="#b20910";
        return false
    }
    return true;


}
function usersubmit(){
    if( startTimeBlur() && endTimeBlur()){
        return true;
    }
    return false;
}