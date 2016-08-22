package com.leeo.sphinx;

/**
 * Created by simpletour_java on 2016/8/10.
 */
public class ClientTest {
    public static void main(String[] args) throws SphinxException {
        Test.run(new String[]{"-e2","@product_name_index \"到成都\"/1","-l","200000"});
    }
}
