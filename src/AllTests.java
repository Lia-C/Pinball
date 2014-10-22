import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ AbsorberTest.class, BallTest.class, BoardTest.class,
        CircleBumperTest.class, OuterWallTest.class, SquareBumperTest.class,
        TriangleBumperTest.class })
public class AllTests {

}