import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ AbsorberTest.class, BoardTest.class, CircleBumperTest.class,
        FlipperTest.class, OuterWallTest.class, SquareBumperTest.class,
        TriangleBumperTest.class })
public class AllTests {

}
