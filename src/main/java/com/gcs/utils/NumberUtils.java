package com.gcs.utils;

import java.text.DecimalFormat;

public class NumberUtils {

	/**
	 * 保留2位小数 
     * @Title: format
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param @param value
     * @param @return    参数
     * @return double    返回类型
	 * @throws
	 */
	public static double format(Double value) {
		return Double.parseDouble(new DecimalFormat("0.##").format(value));
	}
}
