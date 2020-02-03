package OOFramework;

public abstract class BaseObject
{
    /**
     * should this object be active
     */
    private boolean active = false;

    /**
     * is this object actually active
     */
    private boolean activated = false;

    /**
     * should this object be destroyed
     */
    private boolean shouldDestruct = false;

    /**
     * this is the program that contains this object
     */
    private FrameworkProgram frameworkProgram;

    protected BaseObject(FrameworkProgram frameworkProgram, boolean startsActivated)
    {
        //System.out.println("base");
        this.frameworkProgram = frameworkProgram;
        this.setShouldDestruct(false);
        this.Start();

        if (startsActivated) {
            this.setActive(true);
            this.setActivated(true);
            this.Awake();
        } else {
            this.setActive(false);
            this.setActivated(false);
        }

        frameworkProgram.getObjects().add(this);
    }

    /**
     * this is called when the object gets created
     * after the constructor but before the awake and the loops
     */
    protected void Start()
    {

    }

    /**
     * this is called when the object gets set to active
     * if the object is created active awake will run right after the start method but before the loops
     * if the object is created inactive awake will run when the object is set to active which happens right after the program loops
     * Awake will run every time the object goes from inactive to active
     */
    protected void Awake()
    {

    }

    /**
     * this is called when the object gets set to inactive
     * Sleep will run every time the object goes from active to inactive which happens right after the program loops
     */
    protected void Sleep()
    {

    }

    protected void RemoveFromLists()
    {

    }

    protected void AddToLists()
    {

    }

    protected void Destroy()
    {
        this.RemoveFromLists();
    }


    //beyond this point just getters/setters

    public boolean isActive()
    {
        return this.active;
    }

    public void setActive(boolean m_active)
    {
        this.active = m_active;
    }

    public boolean isActivated()
    {
        return this.activated;
    }

    public void setActivated(boolean activated)
    {
        this.activated = activated;
    }

    public boolean ShouldDestruct()
    {
        return this.shouldDestruct;
    }

    public void setShouldDestruct(boolean shouldDestruct)
    {
        this.shouldDestruct = shouldDestruct;
    }

    public FrameworkProgram getFrameworkProgram()
    {
        return this.frameworkProgram;
    }

    public void setFrameworkProgram(FrameworkProgram frameworkProgram)
    {
        this.frameworkProgram = frameworkProgram;
    }
}
