package com.example.nightorlightdemo3;

import java.util.HashMap;

public class MemCacheHelper {
	private static final MemCacheHelper single = new MemCacheHelper();
	/**
     * ȫ��ͨ�õ���ʱ�������
     * ʹ�÷�ʽ��key�ǰ���+��������value���������ͣ�ֵ�������װ����int-��Integer
     */
	public HashMap<String, Object> mTempVariable = new HashMap<>();
	public static MemCacheHelper getInstance() {
        return single;
    }

    /**
     * �洢һ����ʱ����
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        mTempVariable.put(key, value);
    }
    /**
     * ��ȡһ����ʱ����
     *
     * @param key
     * @return
     */
    public <T> T get(String key) {
        return (T) mTempVariable.get(key);
    }
    /**
     * ɾ����ʱ����
     *
     * @param key
     */
    public void remove(String key) {
        mTempVariable.remove(key);
    }
}
