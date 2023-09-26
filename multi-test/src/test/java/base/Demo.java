package base;

import com.google.common.collect.Sets;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

import java.util.HashSet;

/**
 * @author qindongfang
 * @date 2023/9/25
 **/

public class Demo {

    @Test
    public void test1(){
        int[] a = new int[3];
        a[0] = 1;
        a[1] = 2;
        a[2] = 3;

        int[] b = new int[]{1,2,3,4,5};

        int[] c = {1,2,3,4,5};

        HashSet<@Nullable Object> objects = Sets.newHashSet();
        System.out.println(objects.add(1));
        System.out.println(objects.add(1));
    }

}
