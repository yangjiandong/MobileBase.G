package com.ek.mobileapp.utils.bluetooth;

import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import android.util.Log;

public class UIDSBluetooth {

    String g_msg;
    int bt_cnt = 0;
    public byte[] bt_buf;
    int tmpcnt;
    byte[][] bytetmp;
    byte[] packet;

    public String changeBarCode(InputStream in) {
        String barCode = "";
        byte[] buffer = new byte[1024];

        try {

            int bytes = in.read(buffer);

            barCode = process1(bytes, buffer);
            tmpcnt += 1;
            tmpcnt %= 50;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return barCode;

    }

    private String process1(int len, byte[] readBuf) {

        bt_buf = new byte[80000];
        int chkc = 0;

        for (chkc = 0; chkc < len; chkc++) {
            bt_buf[(bt_cnt + chkc)] = readBuf[chkc];
        }
        bt_cnt += len;

        int packet_detected = 0;
        int error = 0;
        for (int opop = 0; opop < 3; opop++) {
            if (bt_cnt <= 4) {
                return "";
            }

            for (chkc = 0; chkc < bt_cnt; chkc++)
                if (bt_buf[chkc] == 1) {
                    int lenx = 0;

                    for (int endc = chkc + 1; endc < bt_cnt; endc++) {
                        if (bt_buf[endc] == 4) {
                            lenx = endc - chkc + 1;
                            packet_detected = 1;

                            break;
                        }
                    }
                    if (packet_detected == 1) {
                        packet_detected = 0;

                        if (dle_process(chkc, lenx) == 1)
                            error = 1;
                        else {
                            error = 0;
                        }

                        if (chkc != 0) {
                            int uu = 0;

                            for (uu = chkc; uu < bt_cnt; uu++) {
                                bt_buf[(uu - chkc)] = this.bt_buf[uu];
                            }

                            bt_cnt -= chkc;
                        }

                        if ((error == 0))
                            return g_msg;
                        else {
                            Log.e("[SQUALL]", "btlib > dle_process error !! ");
                        }

                        for (int uux = lenx; uux < bt_cnt; uux++) {
                            bt_buf[(uux - lenx)] = bt_buf[uux];
                        }

                        bt_cnt -= lenx;
                    }
                }
        }
        return "";
    }

    private int dle_process(int start, int lenx) {
        int j = 0;
        lenx -= 2;
        byte[] tmp_packet = new byte[lenx];

        for (int i = start + 1; i < lenx + start + 1; i++) {
            if (this.bt_buf[i] == 16) {
                i++;

                tmp_packet[j] = ((byte) (this.bt_buf[i] - 32));
                j++;
            } else {
                tmp_packet[j] = this.bt_buf[i];
                j++;
            }
        }

        this.packet = new byte[j];
        for (int i = 0; i < j; i++) {
            this.packet[i] = tmp_packet[i];
        }

        j -= 10;
        byte[] msg;
        if (this.packet[2] == 1)
            msg = new byte[j + 16];
        else {
            msg = new byte[j];
        }

        for (int i = 0; i < j; i++) {
            msg[i] = this.packet[(i + 7)];
        }

        byte[] padd = { -76, -96, 90, 125, 30, -114, 8, 61, 8, 77, 2, -67, 30, -51, -90, -43 };

        if (this.packet[2] == 1) {
            for (int i = j; i < j + 16; i++) {
                msg[i] = padd[(i - j)];
            }
        }

        if (this.packet[2] == 1) {
            byte[] dat = (byte[]) null;
            byte[] pwd = { 0, 17, 34, 51, 68, 85, 102, 119, 0, 17, 34, 51, 68, 85, 102, 119, 0, 17, 34, 51, 68, 85,
                    102, 119, 0, 17, 34, 51, 68, 85, 102, 119 };

            Cipher cipher = null;
            try {
                cipher = Cipher.getInstance("AES");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            }

            SecretKeySpec skeySpec = new SecretKeySpec(pwd, "AES");
            try {
                cipher.init(2, skeySpec);
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
            try {
                dat = cipher.doFinal(msg);
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            }

            if (dat == null) {
                return 1;
            }

            int padlen = this.packet[6];

            if (dat != null) {
                byte[] real_data = new byte[dat.length - padlen];

                for (int i = 0; i < dat.length - padlen; i++) {
                    real_data[i] = dat[i];
                }

                this.g_msg = new String(real_data);
            }
        } else {
            this.g_msg = new String(msg);
        }

        return 0;
    }

    public static String EvaluateCardNumber(String str) {
        String stemp = "";
        try {
            if (str.startsWith("B")) {
                stemp = str.substring(1).replace("\r", "/");
                String[] aStr = stemp.split("/");
                stemp = aStr[0].toString();
            } else
                return "";

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stemp;
    }
}
