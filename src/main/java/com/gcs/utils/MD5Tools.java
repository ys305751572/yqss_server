package com.gcs.utils;

import java.security.MessageDigest;

/**
 * MD5鍔犲瘑宸ュ叿绫�
 * <鍔熻兘璇︾粏鎻忚堪>
 * 
 * @author  chenlujun
 * @version  [鐗堟湰鍙�, 2014骞�10鏈�1鏃
 * @see  [鐩稿叧绫�/鏂规硶]
 * @since  [浜у搧/妯″潡鐗堟湰]
 */
public abstract class MD5Tools
{
    public final static String MD5(String pwd) {
        //鐢ㄤ簬鍔犲瘑鐨勫瓧绗�
        char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            //浣跨敤骞冲彴鐨勯粯璁ゅ瓧绗﹂泦灏嗘 String 缂栫爜涓� byte搴忓垪锛屽苟灏嗙粨鏋滃瓨鍌ㄥ埌涓�涓柊鐨� byte鏁扮粍涓�
            byte[] btInput = pwd.getBytes("utf-8");
             
            //淇℃伅鎽樿鏄畨鍏ㄧ殑鍗曞悜鍝堝笇鍑芥暟锛屽畠鎺ユ敹浠绘剰澶у皬鐨勬暟鎹紝骞惰緭鍑哄浐瀹氶暱搴︾殑鍝堝笇鍊笺��
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
             
            //MessageDigest瀵硅薄閫氳繃浣跨敤 update鏂规硶澶勭悊鏁版嵁锛� 浣跨敤鎸囧畾鐨刡yte鏁扮粍鏇存柊鎽樿
            mdInst.update(btInput);
             
            // 鎽樿鏇存柊涔嬪悗锛岄�氳繃璋冪敤digest锛堬級鎵ц鍝堝笇璁＄畻锛岃幏寰楀瘑鏂�
            byte[] md = mdInst.digest();
             
            // 鎶婂瘑鏂囪浆鎹㈡垚鍗佸叚杩涘埗鐨勫瓧绗︿覆褰㈠紡
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {   //  i = 0
                byte byte0 = md[i];  //95
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5 
                str[k++] = md5String[byte0 & 0xf];   //   F
            }
             
            //杩斿洖缁忚繃鍔犲瘑鍚庣殑瀛楃涓�
            return new String(str);
             
        } catch (Exception e) {
            return null;
        }
    }
    public static void main(String[] args) {
//    	String s = new String("POSThttp://msg.umeng.com/api/send{\"appkey\":\"54339e42fd98c5bbf70189e7\",\"timestamp\":\"1420973679\",\"type\":\"customizedcast\", \"alias_type\": \"鍋ュ悍鎶ょ収\",\"alias\":\"f9aa885b483bbe7201483bbe72690000\",\"payload\":{\"aps\": { \"alert\": \"test\"}},\"production_mode\":\"false\",\"description\": \"浜戝钩鍙版帹閫乗",\"thirdparty_id\": \"webpush\"}bjovetekgm3rleagc9idbtupda3c841d");
//		System.out.println(s);
//    	System.out.println(MD5(s));
	}
}
