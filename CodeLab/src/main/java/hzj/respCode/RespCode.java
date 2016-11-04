package hzj.respCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/8.
 */
public class RespCode {
    //**********************************系统响应码***********************************
    public final static String SUCCESS = "0";//使用过
    /**
     * 无效的请求
     */
    public final static String REQUEST_INVALID = "0001";//使用过
    /**
     * 无效的参数
     */
    public final static String PARAM_INVALID = "0002";//使用过
    /**
     * 无效的token
     */
    public final static String TOKEN_INVALID = "0003";
    /**
     * 无效的会话
     */
    public final static String SESSION_INVALID = "0004";//使用过
    /**
     * 通讯错误
     */
    public final static String CONNECT_ERROR = "0005";
    /**
     * 无效的验证码
     */
    public final static String invalid_verifyCode = "0006";//无效的验证码
    /**
     * 验证码错误
     */
    public final static String verifyCode_error = "0007";//验证码有误
    /**
     * 编码异常
     */
    public final static String ENCODE_ERROR = "0008";//编码异常
    /**
     *
     */
    public final static String DECODE_ERROR = "00010";//解码异常
    /**
     * 不知道的错误
     */
    public final static String UNKNOWN_ERROR = "error";//未知的错误
    /**
     * 操作失败
     */
    public final static String OPPER_FAIL = "OPPER_FAIL";//操作失败
    /**
     * 没有结果集
     */
    public final static String NO_MORE_DATA = "0009";//无结果集


    // -----------登录相关--9---------
    /**
     * 账号为空
     */
    public final static String ACCOUNT_IS_NULL = "9001";//账号为空
    /**
     * 账号不存在
     */
    public final static String ACCOUNT_NOT_EXIST = "9002";//账号不存在
    /**
     * 密码错误
     */
    public final static String PASSWD_ERR = "9003";//密码有误
    /**
     * 原 密码错误
     */
    public final static String ORIGINAL_PASSWD_ERR = "90010";//原密码有误
    /**
     * 账号未激活
     */
    public final static String ACCOUNT_NOT_ACTIVATED = "9004";
    /**
     * 账号不匹配
     */
    public final static String ACCOUNT_NOT_MATCH = "9006";//账号不匹配
    /**
     * 账号变更与之前不符
     */
    public final static String ACCOUNTLENGTH_NO_MATCH = "90011";//账号变更与之前不符
    /**
     * 该商户号已经审核通过
     */
    public final static String MERCHANT_CODE_IS_EXSIT = "9007";//该商户已审核通过
    /**
     * 商户号错误
     */
    public final static String SHOP_NUM_ERROR = "9008";//商户号有误
    /**
     * 账号过长
     */

    public final static String ACCOUNT_ERROR = "90009";//账号输入过长
    /**
     * 账号不匹配
     */

    public final static String ACCOUNT_IS_EXIST = "9005";//账号不匹配

    // -----------交易相关--8---------
    /**
     * 交易失败
     */
    public final static String TRADE_ERROR = "8000";
    /**
     * 余额不足
     */
    public final static String BALANCE_INSUFFICIENT = "8001";
    /**
     * 保证金账户余额不足
     */
    public final static String BOND_INSUFFICIENT = "8002";
    /**
     * 库存不足
     */
    public final static String STOCK_INSUFFICIENT = "8003";

    // -----------查询--2---------
    /**
     * 无效的订单
     */
    public final static String ORDER_INVALID = "2001";

    /**
     * 无记录
     */
    public final static String RECORD_IS_NULL = "2002";//记录为空

    /**
     * 商户号为空
     */
    public final static String MERCHANTCODE_IS_NULL = "2003";//商户号为空

    /**
     * 信息太短
     */
    public final static String MESSAGE_IS_SHORT = "2004";//信息头太短


    // -----------检查--1---------
    /**
     * 订单号为空
     */
    public final static String ORDERID_IS_NULL = "1002";
    /**
     * 商品明细编号为空
     */
    public final static String DETAILID_IS_NULL = "1004";
    /**
     * 分页参数为空
     */
    public final static String PAGINATION_IS_NULL = "1005";
    /**
     * 业务编号为空
     */
    public final static String BUSINESSID_IS_NULL = "1006";
    /**
     * 企业编号为空
     */
    public final static String ENTERPRISE_ID_IS_NULL = "1007";
    /**
     * 留言内容为空
     */
    public final static String MESSAGE_IS_NULL = "1008";
    /**
     * 充值金额为空
     */
    public final static String RECHARGE_AMT_IS_NULL = "1009";
    /**
     * 无效的业务类型
     */
    public final static String BUSINESSID_INVALID = "1010";
    /**
     * 保证金账户不存在
     */
    public final static String BONDACT_NOT_EXIST = "1011";
    /**
     * 无权购买该商品
     */
    public final static String MERCH_AUTH_INSUFFICIENT = "1017";
    /**
     * 订单总金额为空
     */
    public final static String TOTALAMT_IS_NULL = "1019";
    /**
     * 重要金额不能为0
     */
    public final static String AMOUNT_IS_ZERO = "1020";
    /**
     * 请求时参数为空
     */
    public final static String PARAMS_IS_NULL = "1021";//参数为空
    /**
     * 请求时参数错误
     */
    public final static String PARAMS_IS_ERROR = "1022";//参数错误


    /**
     * 法人代表姓名为空
     */
    public final static String NAME_IS_NULL = "1031";//法人代表姓名为空
    /**
     * 法人代表身份证号为空
     */
    public final static String CERTID_IS_NULL = "1032";//法人代表身份证为空
    /**
     * 法人代表办公地址为空
     */
    public final static String ADDR_IS_NULL = "1033";//法人代表办公地址为空
    /**
     * 经营范围为空
     */
    public final static String SCOPE_IS_NULL = "1034";//经营范围为空
    /**
     * 联系人为空
     */
    public final static String LINMAN_IS_NULL = "1035";//联系人为空
    /**
     * 联系人电话
     */
    public final static String CONTACT_IS_NULL = "1036";//联系人电话为空
    /**
     * 商户注册名称
     */
    public final static String MERCHNAME_IS_NULL = "1037";//商户注册名称
    /**
     * 营业执照注册号
     */
    public final static String BIZLICENSE_IS_NULL = "1038";//营业执照注册号为空
    /**
     * 营业执照正本扫描件
     */
    public final static String BIZIMG_IS_NULL = "1039";//营业执照正本扫描件为空
    /**
     * 税务登记证件扫描件
     */
    public final static String TAXIMG_IS_NULL = "1040";//税务登记证件扫描app暂时撤销
    /**
     * 营业场所们店面照
     */
    public final static String SHOPIMG_IS_NULL = "1041";//营业场所店面照
    /**
     * 结算信息
     */
    public final static String TERMINALS_IS_INULL = "1055";//结算信息

    /**
     * 银行开户证明扫描件
     */
    public final static String OPEMBANKIMG_IS_NULL = "1042";//银行开户证明扫描件
    /**
     * 税务登记证件号
     */
    public final static String TAXLICENSE_IS_NULL = "1043";//税务登记证件扫描件app暂时撤销
    /**
     * 申办人省份证号正面照编号为空
     */
    public final static String certImgPositive_IS_NULL = "1044";//申办人身份证正面照编号为空
    /**
     * 申办人省份证号反面照编号为空
     */
    public final static String certImgOpposite_IS_NULL = "1045";//申办人身份证反面照编号为空
    /**
     * 结算方式为空
     */
    public final static String STLWAY_IS_NULL = "1046";//结算方式为空
    /**
     * 结算户主
     */
    public final static String BANKNAME_IS_NULL = "1047";//结算户主为空
    /**
     * 结算银行编号
     */
    public final static String BANKIS_NULL = "1048";//结算银行编号
    /**
     * 结算银行账号
     */
    public final static String ACOUNT_IS_NULL = "1049";
    /**
     * 门店名称
     */
    public final static String STORE_IS_NULL = "1050";//门店名称为空
    /**
     * 门店装机地址
     */
    public final static String TERMINALADDR_IS_NULL = "1051";//门店装机地址为空
    /**
     * 机具信息为空
     */
    public final static String POSINFO_IS_NULL = "1052";//机具信息为空
    /**
     * 机具型号
     */
    public final static String DEVICE_IS_NULL = "1053";//机具型号为空
    /**
     * 台数
     */
    public final static String COUNT_IS_NULL = "1054";
    /**
     * 入账银行卡扫描
     */
    public final static String ACCCOUNTIMG_IS_NULL = "1054";//入账银行卡扫描

    /**
     * 结算方式
     */
    public final static String SETTLE_IS_NULL = "1055";//结算方式

    /**
     * 用户id为空
     */
    public final static String USERID_IS_NULL = "1056";//用户编号为空
    /**
     * 签约人为空
     */

    public final static String AGENT_EMP_IS_NUll = "1057";
    /**
     * 单笔限额为空
     */
    public final static String ONE_MAX_MONEY_IS_NULL = "1058";
    /**
     * 固定电话区号为空
     */

    public final static String AREA_CODE_IS_NULL = "1059";
    /**
     * 固定电话为空
     */

    public final static String TELEPHONE_IS_NULL = "1060";
    /**
     * 账户性质为空
     */
    public final static String OPENTYPE_IS_NULL = "1061";
    /**
     * 固话区号无法匹配
     * */

    public final static String AREA_CODE_NOT_MACTH = "1062";
    /**
     * 固话号码又有误
     * */

    public final static String TEL_PHONE_NOT_MACTH = "1064";
    /**
     * 营业执照号无效
     * */
    public final static String  BIZLICENSE_NOT_INVALID="1063";
     /**
     * 進件反饋狀態無效
     */
    public final static String ORDER_STATE_INVALID = "40002";//进件状态反馈无效


    public static String getCodeDesc(String code) {

        String desc = codeDesc.get(code);
        return desc != null ? desc : "";
    }

    private final static Map<String, String> codeDesc = new HashMap<String, String>();

    static {
        codeDesc.put(SUCCESS, "ok");//登录成功
        codeDesc.put(ACCOUNT_NOT_ACTIVATED, "账户未激活");
        codeDesc.put(REQUEST_INVALID, "请求无效");
        codeDesc.put(PASSWD_ERR, "密码错误");
        codeDesc.put(PARAM_INVALID, "无效参数");
        codeDesc.put(invalid_verifyCode, "验证码已失效");
        codeDesc.put(verifyCode_error, "验证码错误");
        codeDesc.put(TOKEN_INVALID, "无效的Token");
        codeDesc.put(SESSION_INVALID, "无效会话");
        codeDesc.put(UNKNOWN_ERROR, "不知道的错误");
        codeDesc.put(ACCOUNT_NOT_EXIST, "用户不存在");
        codeDesc.put(ACCOUNT_IS_NULL, "用户为空");
        codeDesc.put(ACCOUNT_IS_EXIST, "该手机号已被使用过");
        codeDesc.put(TRADE_ERROR, "交易失败");
        codeDesc.put(BALANCE_INSUFFICIENT, "余额不足");
        codeDesc.put(STOCK_INSUFFICIENT, "库存不足");
        codeDesc.put(ORDER_INVALID, "无效订单");
        codeDesc.put(PAGINATION_IS_NULL, "分页参数为空");
        codeDesc.put(BUSINESSID_IS_NULL, "业务编号为空");
        codeDesc.put(ENTERPRISE_ID_IS_NULL, "企业编号为空");
        codeDesc.put(MESSAGE_IS_NULL, "信息内容为空");
        codeDesc.put(RECHARGE_AMT_IS_NULL, "充值金额为空");
        codeDesc.put(BONDACT_NOT_EXIST, "保证金账户不存在");
        codeDesc.put(TOTALAMT_IS_NULL, "无效的订单总金额");
        codeDesc.put(RECORD_IS_NULL, "查询记录为空");
        codeDesc.put(MERCHANTCODE_IS_NULL, "商户号为空");
        codeDesc.put(OPPER_FAIL, "操作失败");
        codeDesc.put(PARAMS_IS_NULL, "参数为空");

        codeDesc.put(NAME_IS_NULL, "法人代表姓名为空");
        codeDesc.put(CERTID_IS_NULL, "法人代表身份证号为空");
        codeDesc.put(ADDR_IS_NULL, "法人代表办公地址为空");
        codeDesc.put(SCOPE_IS_NULL, "经营范围为空");
        codeDesc.put(LINMAN_IS_NULL, "联系人为空");

        codeDesc.put(CONTACT_IS_NULL, "联系人电话为空");
        codeDesc.put(MERCHNAME_IS_NULL, "商户注册名称为空");
        codeDesc.put(BIZLICENSE_IS_NULL, "营业执照注册号为空");
        codeDesc.put(BIZIMG_IS_NULL, "营业执照正本扫描件为空");
        codeDesc.put(TAXIMG_IS_NULL, "税务登记证件扫描件为空");
        codeDesc.put(SHOPIMG_IS_NULL, "营业场所们店面照为空");
        codeDesc.put(OPEMBANKIMG_IS_NULL, "银行开户证明扫描件为空");
        codeDesc.put(TAXLICENSE_IS_NULL, "税务登记证件号为空");
        codeDesc.put(certImgPositive_IS_NULL, "申办人省份证号正面照编号为空");
        codeDesc.put(certImgOpposite_IS_NULL, "申办人省份证号反面照编号为空");
        codeDesc.put(STLWAY_IS_NULL, "结算方式为空");
        codeDesc.put(BANKNAME_IS_NULL, "结算户主为空");
        codeDesc.put(BANKIS_NULL, "结算银行编号为空");
        codeDesc.put(ACOUNT_IS_NULL, "结算银行账号为空");
        codeDesc.put(STORE_IS_NULL, "门店名称为空");
        codeDesc.put(TERMINALADDR_IS_NULL, "门店装机地址为空");
        codeDesc.put(DEVICE_IS_NULL, "机具型号为空");
        codeDesc.put(COUNT_IS_NULL, "台数为空");
        codeDesc.put(AGENT_EMP_IS_NUll,"签约人为空");
        codeDesc.put(AREA_CODE_IS_NULL,"固话区为空");
        codeDesc.put(TELEPHONE_IS_NULL,"固话为空");
        codeDesc.put(OPENTYPE_IS_NULL,"账户性质为空");
        codeDesc.put(ONE_MAX_MONEY_IS_NULL,"单笔限额为空");
        codeDesc.put(MESSAGE_IS_SHORT, "信息太短无法匹配");
        codeDesc.put(ACCCOUNTIMG_IS_NULL, "入账银行卡扫描");
        codeDesc.put(NO_MORE_DATA, "无查询结果");
        codeDesc.put(TERMINALS_IS_INULL, "结算信息为空");
        codeDesc.put(SETTLE_IS_NULL, "结算方式为空");
        codeDesc.put(USERID_IS_NULL, "用户id为空");
        codeDesc.put(POSINFO_IS_NULL, "机具信息为空");
        codeDesc.put(ORDER_STATE_INVALID, "订单状态无效");
        codeDesc.put(ACCOUNT_NOT_MATCH, "商户号或银行账号有误");
        codeDesc.put(MERCHANT_CODE_IS_EXSIT, "该商户号已绑定");
        codeDesc.put(PARAMS_IS_ERROR, "参数有误");
        codeDesc.put(SHOP_NUM_ERROR, "商户号有误");
        codeDesc.put(ORIGINAL_PASSWD_ERR, "原密码错误");
        codeDesc.put(ACCOUNT_ERROR, "银行账号有误");
        codeDesc.put(ACCOUNTLENGTH_NO_MATCH, "商户验证失败");
        codeDesc.put(ENCODE_ERROR, "编码异常");
        codeDesc.put(DECODE_ERROR, "转码异常");
        codeDesc.put(AREA_CODE_NOT_MACTH,"固话区号不匹配");
        codeDesc.put(TEL_PHONE_NOT_MACTH,"固话号码有误");
        codeDesc.put(BIZLICENSE_NOT_INVALID,"营业执照号无效");


    }

}
