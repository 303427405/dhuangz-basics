package com.dhuangz.validate;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类，验证数据是否符合规范
 * @date:2014年8月7日
 */
public class RegexValidator {

	//手机号正则表达式
	public static final String MOBILE_NUMBER = "^1[0-9]{10}$";

	/**中文+数字*/
	public static final String EFFECTUAL_CHARACTER = "[a-zA-Z0-9~!@#$￥%^&*()-_+\\u0391-\\uFFE5]+$";

    /**正则表达式：验证URL*/
    public static final String REGEX_URL = "[http|https]+[://]+[0-9A-Za-z:/[-]_#[?][=][.][&]]*";

    /***正则表达式：验证网络图片*/
    public static final String REGEX_IMG_URL = "https?://.+\\.*";

    /**正则表达式 验证日期*/
    public static final String REGEXDATEFORMET =  "(?:19|20)[0-9][0-9]-(?:(?:0[1-9])|(?:1[0-2]))-(?:(?:[0-2][1-9])|(?:[1-3][0-1])) (?:(?:[0-2][0-3])|(?:[0-1][0-9])):[0-5][0-9]:[0-5][0-9]";

	/**日期格式 2015-05-07 22:23*/
	public static final String REGEX_DATE_FORMAT_MINUTE =  "^(?:19|20)[0-9][0-9]-(?:(?:0[1-9])|(?:1[0-2]))-(?:(?:[0-2][1-9])|(?:[1-3][0-1])) (?:(?:[0-2][0-3])|(?:[0-1][0-9])):[0-5][0-9]$";

	/**金额格式*/
	public static final String REGEX_MONEY = "^(([1-9]{1}\\d{0,4})|([0]{1}))(\\.(\\d){0,2})?$";

	/**
	 * 判断字符串是否符合正则表达式
	 * 
	 * @author : chenssy
	 * @date : 2016年6月1日 下午12:43:05
	 *
	 * @param str
	 * @param regex
	 * @return
	 */
	public static boolean find(String str, String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		boolean b = m.find();
		return b;
	}

	/**
	 * 判断输入的字符串是否符合Email格式.
	 * @autor:chenssy
	 * @date:2014年8月7日
	 *
	 * @param email
	 * 				传入的字符串
	 * @return 符合Email格式返回true，否则返回false
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.length() < 1 || email.length() > 256) {
			return false;
		}
		Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
		return pattern.matcher(email).matches();
	}
	
	/**
	 * 判断输入的字符串是否为纯汉字
	 * @autor:chenssy
	 * @date:2014年8月7日
	 *
	 * @param value
	 * 				传入的字符串
	 * @return
	 */
	public static boolean isChinese(String value) {
		Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
		return pattern.matcher(value).matches();
	}
	
	/**
	 * 判断是否为浮点数，包括double和float
	 * @autor:chenssy
	 * @date:2014年8月7日
	 *
	 * @param value
	 * 			传入的字符串
	 * @return
	 */
	public static boolean isDouble(String value) {
		Pattern pattern = Pattern.compile("^[-\\+]?\\d+\\.\\d+$");
		return pattern.matcher(value).matches();
	}
	
	/**
	 * 判断是否为整数
	 * @autor:chenssy
	 * @date:2014年8月7日
	 *
	 * @param value
	 * 			传入的字符串
	 * @return
	 */
	public static boolean isInteger(String value) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
		return pattern.matcher(value).matches();
	}

	/**
	 * 验证手机号是否正确
	 */
	public static boolean validateMobile(String mobileNumber) {
		if (StringUtils.isNotBlank(mobileNumber) && mobileNumber.matches(MOBILE_NUMBER)) {
			return true;
		}
		return false;
	}

	/**
	 * 有效字符校验
	 * 数字，中文，字母，_
	 * @param value
	 * @return
	 */
	public static boolean isEffectualCharacter(String value) {
		if (StringUtils.isEmpty(value)){
			return true;
		}
		/**只要不为空串就正则匹配*/
		Pattern pattern = Pattern.compile(EFFECTUAL_CHARACTER);
		return pattern.matcher(value).matches();
	}


    /**
     * 校验URL
     *
     * @param url
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }

    /**
     * 校验网络图片
     */
    public static boolean isInterImg(String imgUrl){
        return Pattern.matches(REGEX_IMG_URL , imgUrl);
    }

    /**
	 * 校验金额格式
	 * */
    public static boolean isMoney(String price){
		return Pattern.matches(REGEX_MONEY,price);
	}

}
