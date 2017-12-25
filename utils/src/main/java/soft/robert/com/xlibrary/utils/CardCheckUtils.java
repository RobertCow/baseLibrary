package soft.robert.com.xlibrary.utils;

/**
 * 银行卡校验工具类
 * Created by M 4700 on 2017/7/16.
 */

public class CardCheckUtils {


    /**
     * 该校验的过程：
     * 1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加。
     * 2、从卡号最后一位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，则将其减去9），再求和。
     * 3、将奇数位总和加上偶数位总和，结果应该可以被10整除。
     *
     * @param num
     * @return
     */
    public static boolean isCardNum(String num) {
        if (num == null) {
            ToastUtils.showShortToast("卡号为空！");
            return false;
        } else {
            int sumOdd = 0;
            int sumEven = 0;
            String number = num;
            int length = number.length();
            int[] wei = new int[length];
            for (int i = 0; i < number.length(); i++) {
                wei[i] = Integer.parseInt(number.substring(length - i - 1, length - i));// 从最末一位开始提取，每一位上的数值
            }
            for (int i = 0; i < length / 2; i++) {
                sumOdd += wei[2 * i];
                if ((wei[2 * i + 1] * 2) > 9)
                    wei[2 * i + 1] = wei[2 * i + 1] * 2 - 9;
                else
                    wei[2 * i + 1] *= 2;
                sumEven += wei[2 * i + 1];
            }
            if ((sumOdd + sumEven) % 10 == 0)
                return true;
            else {
                return false;
            }

        }
    }

}
