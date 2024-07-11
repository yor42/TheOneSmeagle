package mcjty.theoneprobe.api;

/**
 * A mode that indicates what kind of information we want to display.
 * In your IProbeInfoAccessor or IProbeInfoProvider you can use this mode
 * to show different information.
 */
public enum ProbeMode {
    /**Normal display. What a user probably expects to see*/
    NORMAL,
    /**Extended. This is shown when the player is sneaking*/
    EXTENDED,
    /**Creative only. This is shown when the player holds a creative probe*/
    DEBUG
}
