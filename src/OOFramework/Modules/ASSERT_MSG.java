package OOFramework.Modules;

public class ASSERT_MSG
{
    static void ASSERT_MSG(boolean condition, String msg)
    {
        if (CONSTANTS.DEBUG_MODE) {
            if (condition) {
                System.out.println(msg);
            }
        }
    }

    static void ASSERT_MSG_TERMINATE(boolean condition, String msg)
    {
        if(CONSTANTS.DEBUG_MODE) {
            if (condition) {
                System.out.println(msg);
                System.exit(0);
            }
        }
    }
}
