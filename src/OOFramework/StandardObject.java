package OOFramework;

public abstract class StandardObject extends BaseObject
{
    private boolean usesInput;
    private boolean usesMain;
    private boolean usesRenderer;

    protected StandardObject(FrameworkProgram frameworkProgram)
    {
        this(frameworkProgram, true, true, true, true);
    }

    protected StandardObject(FrameworkProgram frameworkProgram, boolean usesInput, boolean usesMain, boolean usesRenderer, boolean startsActivated)
    {
        super(frameworkProgram, startsActivated);
        this.usesInput = usesInput;
        this.usesMain = usesMain;
        this.usesRenderer = usesRenderer;
        this.AddToLists();
        //System.out.println("Standard");

    }

    protected void InputLoop(double deltaTime)
    {

    }

    protected void MainLoop(double deltaTime)
    {

    }

    protected void RenderLoop(double deltaTime)
    {

    }

    @Override
    protected void RemoveFromLists()
    {
        super.RemoveFromLists();
        if (usesInput) {
            getFrameworkProgram().getInputObjectsToBeRemoved().add(this);
            getFrameworkProgram().setShouldRemoveFromInputList(true);
        }

        if (usesMain)
        {
            getFrameworkProgram().getMainObjectsToBeRemoved().add(this);
            getFrameworkProgram().setShouldRemoveFromMainGroup(true);
        }

        if (usesRenderer) {
            getFrameworkProgram().getRenderObjectsToBeRemoved().add(this);
            getFrameworkProgram().setShouldRemoveFromRenderGroup(true);
        }
    }

    @Override
    protected void AddToLists()
    {
        super.AddToLists();
        if (usesInput) {
            getFrameworkProgram().getInputObjectsToBeAdded().add(this);
            getFrameworkProgram().setShouldAddToInputList(true);
        }

        if (usesMain)
        {
            getFrameworkProgram().getMainObjectsToBeAdded().add(this);
            getFrameworkProgram().setShouldAddToMainGroup(true);
        }

        if (usesRenderer) {
            getFrameworkProgram().getRenderObjectsToBeAdded().add(this);
            getFrameworkProgram().setShouldAddToRenderGroup(true);
        }
    }

    @Override
    protected void Destroy()
    {
        super.Destroy();
    }


    //beyond this point just getters/setters

    public boolean UsesInput()
    {
        return usesInput;
    }

    public void setUsesInput(boolean usesInput)
    {
        this.usesInput = usesInput;
    }

    public boolean UsesMain()
    {
        return usesMain;
    }

    public void setUsesMain(boolean usesMain)
    {
        this.usesMain = usesMain;
    }

    public boolean UsesRenderer()
    {
        return usesRenderer;
    }

    public void setUsesRenderer(boolean usesRenderer)
    {
        this.usesRenderer = usesRenderer;
    }
}

