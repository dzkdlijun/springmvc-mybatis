package com.leeo.sphinx;

/**
 * Created by simpletour_java on 2016/8/10.
 */
public class ClientTest {
    public static void main(String[] args) throws SphinxException {
        Test.run(new String[]{"-e2","@name \"九\"/1 @resourceType \"CATERING\"/8 ","-l","1000"});
        System.out.println();
        System.out.println("@name \"成都\"/1 @resourceType \"SCENIC\"/6");
    }
}
