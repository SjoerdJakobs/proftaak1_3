package OOFramework.Modules;

public class ASSERT_MSG
{
    public static void ASSERT_MSG(boolean condition, String msg)
    {
        if (CONSTANTS.DEBUG_MODE) {
            if (!condition) {
                System.out.println(msg);
            }
        }
    }

    public static void ASSERT_MSG_TERMINATE(boolean condition, String msg)
    {
        if(CONSTANTS.DEBUG_MODE) {
            if (!condition) {
                System.out.println(msg);
                System.exit(0);
            }
        }
    }
}
