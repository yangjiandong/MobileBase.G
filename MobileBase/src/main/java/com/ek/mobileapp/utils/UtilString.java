package com.ek.mobileapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.commons.lang.StringUtils;

public final class UtilString {

    // 数学运算
    public String eval(String exp) throws Exception {
        List<String> list = infixExpToPostExp(exp);// 转化成后缀表达式
        return doEval(list);// 真正求值
    }

    // 遇到操作符压栈，遇到表达式从后缀表达式中弹出两个数，计算出结果，压入堆栈
    private String doEval(List<String> list) throws Exception {
        Stack<String> stack = new Stack<String>();
        String element;
        double n1, n2, result;
        try {
            for (int i = 0; i < list.size(); i++) {
                element = list.get(i);
                if (isOperator(element)) {
                    n1 = Double.parseDouble(stack.pop() + "");
                    n2 = Double.parseDouble(stack.pop() + "");
                    result = doOperate(n2, n1, element);
                    stack.push(result + "");
                } else {
                    stack.push(element);
                }
            }
            return "" + stack.pop();
        } catch (RuntimeException e) {
            throw new Exception(e.getMessage());
        }
    }

    private double doOperate(double n1, double n2, String operator) {
        if (operator.equals("+"))
            return n1 + n2;
        else if (operator.equals("-"))
            return n1 - n2;
        else if (operator.equals("*"))
            return n1 * n2;
        else
            return n1 / n2;
    }

    private boolean isOperator(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }

    private List<String> infixExpToPostExp(String exp) throws Exception {// 将中缀表达式转化成为后缀表达式
        exp = exp + "#";
        List<String> postExp = new ArrayList<String>();// 存放转化的后缀表达式的链表
        StringBuffer numBuffer = new StringBuffer();// 用来保存一个数的
        Stack<String> opStack = new Stack<String>();// 操作符栈
        char ch;
        String preChar;
        opStack.push("#");
        try {
            for (int i = 0; i < exp.length();) {
                ch = exp.charAt(i);
                switch (ch) {
                case '+':
                case '-':
                case '*':
                case '/':
                    preChar = opStack.peek();
                    // 如果栈里面的操作符优先级比当前的大，则把栈中优先级大的都添加到后缀表达式列表中
                    while (priority(preChar.charAt(0)) >= priority(ch)) {
                        postExp.add("" + preChar);
                        opStack.pop();
                        preChar = opStack.peek();
                    }
                    opStack.push("" + ch);
                    i++;
                    break;
                case '(':
                    // 左括号直接压栈
                    opStack.push("" + ch);
                    i++;
                    break;
                case ')':
                    // 右括号则直接把栈中左括号前面的弹出，并加入后缀表达式链表中
                    String c = opStack.pop();
                    while (c.charAt(0) != '(') {
                        postExp.add("" + c);
                        c = opStack.pop();
                    }
                    i++;
                    break;
                // #号，代表表达式结束，可以直接把操作符栈中剩余的操作符全部弹出，并加入后缀表达式链表中
                case '#':
                    String c1;
                    while (!opStack.isEmpty()) {
                        c1 = opStack.pop();
                        if (c1.charAt(0) != '#')
                            postExp.add("" + c1);
                    }
                    i++;
                    break;
                // 过滤空白符
                case ' ':
                case '\t':
                    i++;
                    break;
                // 数字则凑成一个整数，加入后缀表达式链表中
                default:
                    if (Character.isDigit(ch) || ch == '.') {
                        while (Character.isDigit(ch) || ch == '.') {
                            numBuffer.append(ch);
                            ch = exp.charAt(++i);
                        }
                        postExp.add(numBuffer.toString());
                        numBuffer = new StringBuffer();
                    } else {
                        throw new Exception("illegal operator");
                    }
                }
            }
        } catch (RuntimeException e) {
            throw new Exception(e.getMessage());
        }
        return postExp;
    }

    private int priority(char op) throws Exception {// 定义优先级
        switch (op) {
        case '+':
        case '-':
            return 1;
        case '*':
        case '/':
            return 2;
        case '(':
        case '#':
            return 0;
        }
        throw new Exception("Illegal operator");
    }

    //"1,2,3" to list
    public static ArrayList stringToArrayList(String list, String separator) {
        String[] pieces = list.split(separator);
        for (int i = pieces.length - 1; i >= 0; i--) {
            pieces[i] = pieces[i].trim();
        }
        return new ArrayList(Arrays.asList(pieces));
    }

    /*
    * 计算字符串的字节长度(字母数字计1，汉字及标点计2)
    *
    */
    public static int byteLength(String string) {
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (Integer.toHexString(string.charAt(i)).length() == 4) {
                count += 2;
            } else {
                count++;
            }
        }
        return count;
    }

    /*
    * 按指定长度，省略字符串部分字符
    * @para String 字符串
    * @para length 保留字符串长度
    * @return 省略后的字符串
    */
    public static String omitString(String string, int length) {
        StringBuffer sb = new StringBuffer();
        if (byteLength(string) > length) {
            int count = 0;
            for (int i = 0; i < string.length(); i++) {
                char temp = string.charAt(i);
                if (Integer.toHexString(temp).length() == 4) {
                    count += 2;
                } else {
                    count++;
                }
                if (count < length - 3) {
                    sb.append(temp);
                }
                if (count == length - 3) {
                    sb.append(temp);
                    break;
                }
                if (count > length - 3) {
                    sb.append(" ");
                    break;
                }
            }
            sb.append("...");
        } else {
            sb.append(string);
        }
        return sb.toString();
    }

    /**
     * EMPTY_STRING
     */
    public final static String EMPTY_STRING = "";

    /**
     * 取得完整类名的最后一截，也就是类本身名称。例如，输入java.lang.Object,则返回Object
     */
    public static String unqualify(String qualifiedName) {
        return qualifiedName.substring(qualifiedName.lastIndexOf(".") + 1);
    }

    /**
     * 例如，输入java.lang.Object,则返回java.lang
     */
    public static String qualifier(String qualifiedName) {
        int loc = qualifiedName.lastIndexOf(".");
        return (loc < 0) ? "" : qualifiedName.substring(0, loc);
    }

    /**
     * 组成完整类名
     */
    public static String qualify(String prefix, String name) {
        if (name == null || prefix == null) {
            throw new NullPointerException();
        }
        return new StringBuffer(prefix.length() + name.length() + 1).append(prefix).append('.').append(name).toString();
    }

    /**
     * 组成完整类名
     */
    public static String[] qualify(String prefix, String[] names) {
        if (prefix == null) {
            return names;
        }
        int len = names.length;
        String[] qualified = new String[len];
        for (int i = 0; i < len; i++) {
            qualified[i] = qualify(prefix, names[i]);
        }
        return qualified;
    }

    /**
     * 去除字符串中的&lt;和&gt;标记.
     * @param src 给定源字符串
     * @return 去除tags后的字符串。
     */
    public static String stripTags(String src) {
        //if (StringUtils.isBlank(src)) {
        //    return src;
        //}
        return src.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }

    /**
     * 判断字符串是否为字母或数字组合
     * @param str 需要判断的字符串
     * @return 是否匹配 true,false
     * @deprecated instead of
     * {@link org.apache.commons.lang.StringUtils.isAlphanumeric(String)}
     */
    public static boolean isAlphanumeric(String str) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 将一个数字转化为字符串，并且在前面按照一定的长度补0，比如，serial=9, length=3
     * 则返回"009"
     * @param serial 给定的整数
     * @param length 返回字符串的长度
     */
    //public static String zeroPadding(Integer serial, int length) {
    //    if (serial == null) {
    //        return StringUtils.leftPad("", length, '0');
    //    }

    //    String serialStr = serial.toString();
    //    return StringUtils.leftPad(serialStr, length, '0');
    //}

    /**
     * 从给定的Serial字符串中取得数字，例如，给出"00089",则返回98,如果不能转换，则返回null
     * @param serial
     * @return
     */
    public static Integer getNumFromSerial(String serial) {
        //if (StringUtils.isBlank(serial) || !StringUtils.isNumeric(serial)) {
        //    return null;
        //}
        return Integer.valueOf(serial);
    }

    public static final String GETTER_PREFIX = "get";

    public static final String SETTER_PREFIX = "set";

    public static final int ACCESSOR_PREFIX_LENGTH = "get".length();

    /**
     * 判断一个String是否是一个合法的javaBean的setter方法的名字。
     */
    public static boolean isSetter(String s) {
        return s.indexOf(SETTER_PREFIX) == 0 && Character.isUpperCase(s.charAt(ACCESSOR_PREFIX_LENGTH));
    }

    /**
     * 判断一个String是否是一个合法的javaBean的getter方法的名字。
     */
    public static boolean isGetter(String s) {
        return s.indexOf(GETTER_PREFIX) == 0 && Character.isUpperCase(s.charAt(ACCESSOR_PREFIX_LENGTH));
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    //oschina

    private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    //private final static SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //private final static SimpleDateFormat dateFormater2 = new SimpleDateFormat("yyyy-MM-dd");

    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * 将字符串转位日期类型
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 以友好的方式显示时间
     * @param sdate
     * @return
     */
    public static String friendly_time(String sdate) {
        Date time = toDate(sdate);
        if(time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        //判断是否是同一天
        String curDate = dateFormater2.get().format(cal.getTime());
        String paramDate = dateFormater2.get().format(time);
        if(curDate.equals(paramDate)){
            int hour = (int)((cal.getTimeInMillis() - time.getTime())/3600000);
            if(hour == 0)
                ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000,1)+"分钟前";
            else
                ftime = hour+"小时前";
            return ftime;
        }

        long lt = time.getTime()/86400000;
        long ct = cal.getTimeInMillis()/86400000;
        int days = (int)(ct - lt);
        if(days == 0){
            int hour = (int)((cal.getTimeInMillis() - time.getTime())/3600000);
            if(hour == 0)
                ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000,1)+"分钟前";
            else
                ftime = hour+"小时前";
        }
        else if(days == 1){
            ftime = "昨天";
        }
        else if(days == 2){
            ftime = "前天";
        }
        else if(days > 2 && days <= 10){
            ftime = days+"天前";
        }
        else if(days > 10){
            ftime = dateFormater2.get().format(time);
        }
        return ftime;
    }

    /**
     * 判断给定字符串时间是否为今日
     * @param sdate
     * @return boolean
     */
    public static boolean isToday(String sdate){
        boolean b = false;
        Date time = toDate(sdate);
        Date today = new Date();
        if(time != null){
            String nowDate = dateFormater2.get().format(today);
            String timeDate = dateFormater2.get().format(time);
            if(nowDate.equals(timeDate)){
                b = true;
            }
        }
        return b;
    }

    /**
     * 判断给定字符串是否空白串。
     * 空白串是指由空格、制表符、回车符、换行符组成的字符串
     * 若输入字符串为null或空字符串，返回true
     * @param input
     * @return boolean
     */
    public static boolean isEmpty( String input )
    {
        if ( input == null || "".equals( input ) )
            return true;

        for ( int i = 0; i < input.length(); i++ )
        {
            char c = input.charAt( i );
            if ( c != ' ' && c != '\t' && c != '\r' && c != '\n' )
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        if(email == null || email.trim().length()==0)
            return false;
        return emailer.matcher(email).matches();
    }
    /**
     * 字符串转整数
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try{
            return Integer.parseInt(str);
        }catch(Exception e){}
        return defValue;
    }
    /**
     * 对象转整数
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if(obj==null) return 0;
        return toInt(obj.toString(),0);
    }
    /**
     * 对象转整数
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try{
            return Long.parseLong(obj);
        }catch(Exception e){}
        return 0;
    }
    /**
     * 字符串转布尔值
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try{
            return Boolean.parseBoolean(b);
        }catch(Exception e){}
        return false;
    }

}
